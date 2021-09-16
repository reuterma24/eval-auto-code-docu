import os
import unidecode

import datasets.funcom_filtered_reduced.load as funcom
import javalang

# idea of this script:
# only take first sentence from comment
# remove pairs with invalid syntax

root = os.path.dirname(os.path.abspath(__file__)) + '/'

global codes_raw
global comments_raw

invalid_idx = []  # contains all indecies with invalid syntax

umlaute_dict = {
    b'\xc3\xa4': b'ae',  # U+00E4	   \xc3\xa4
    b'\xc3\xb6': b'oe',  # U+00F6	   \xc3\xb6
    b'\xc3\xbc': b'ue',  # U+00FC	   \xc3\xbc
    b'\xc3\x84': b'Ae',  # U+00C4	   \xc3\x84
    b'\xc3\x96': b'Oe',  # U+00D6	   \xc3\x96
    b'\xc3\x9c': b'Ue',  # U+00DC	   \xc3\x9c
    b'\xc3\x9f': b'ss',  # U+00DF	   \xc3\x9f
}


def __replace_umlauts(text: str):
    result = text.encode("utf-8")
    for k in umlaute_dict.keys():
        result = result.replace(k, umlaute_dict[k])
    return result.decode()


def __file_line(idx, body):
    return "\t\"%s\": \"%s\"" % (idx, body)


def __refactor_codes():
    with open(root + "functions.json", "w", encoding="utf-8") as funcs:
        funcs.write("{\n")

        for i, (k, v) in enumerate(codes_raw.items()):
            code = str(v)
            codeParse = code.replace('\n', "").replace("\t", "")
            toParse = "class Parse {" + codeParse + '}'

            codeWrite = __replace_umlauts(code)
            codeWrite = unidecode.unidecode(codeWrite)

            codeWrite = codeWrite.encode("unicode_escape").decode("utf-8").replace('\"', '\\"')

            try:
                javalang.parse.parse(toParse)
                funcs.write(__file_line(k, codeWrite))
                if i != (len(codes_raw) - 1):
                    funcs.write(",\n")
            except Exception:
                invalid_idx.append(str(k))
                del comments_raw[k]
                continue

        funcs.write("\n}")
        funcs.close()


def __refactor_comments():
    with open(root + "comments.json", "w", encoding="utf-8") as coms:
        coms.write("{\n")

        for i, (k, v) in enumerate(comments_raw.items()):
            comment = str(v)

            # if no '.' I assume its a single sentence
            if '.' in comment:
                split = comment.split(sep='.')
                comment = split[0] + "*/\n"

            comment = __replace_umlauts(comment)
            comment = unidecode.unidecode(comment)
            comment = comment.encode("unicode_escape").decode("utf-8").replace('\"', '\\"')
            coms.writelines(__file_line(k, comment))
            if i != (len(comments_raw) - 1):
                coms.write(",\n")

        coms.write("\n}")
        coms.close()


def __print_invalid_ids():
    with open(root + "invalid_ids.txt", "w", encoding="utf-8") as errs:
        errs.write("INVALID ID'S:\n")
        for i in invalid_idx:
            errs.write(i + ",\n")

        errs.close()


def main():
    data = funcom.load()
    global codes_raw
    codes_raw = data[0]
    global comments_raw
    comments_raw = data[1]
    __refactor_codes()
    __refactor_comments()
    __print_invalid_ids()


if __name__ == '__main__':
    main()
