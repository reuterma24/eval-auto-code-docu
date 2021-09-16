import os
import pickle

import regex

import datasets.funcom_filtered_reduced.load as funcom
import javalang

# idea of this script:
# only take first sentence from comment
# remove pairs with invalid syntax

root = os.path.dirname(os.path.abspath(__file__)) + '/'

global codes_raw
global comments_raw

global invalid_idx  # contains all indecies with invalid syntax

umlaute_dict = {
    b'\xc3\xa4': b'ae',  # U+00E4	   \xc3\xa4
    b'\xc3\xb6': b'oe',  # U+00F6	   \xc3\xb6
    b'\xc3\xbc': b'ue',  # U+00FC	   \xc3\xbc
    b'\xc3\x84': b'Ae',  # U+00C4	   \xc3\x84
    b'\xc3\x96': b'Oe',  # U+00D6	   \xc3\x96
    b'\xc3\x9c': b'Ue',  # U+00DC	   \xc3\x9c
    b'\xc3\x9f': b'ss',  # U+00DF	   \xc3\x9f
}

def __file_line(idx, body): #json format
    return "\t'%s': '%s',\n" % (idx, body)

def __replace_umlauts(text: str):
    result = text.encode("utf-8")
    for k in umlaute_dict.keys():
        result = result.replace(k, umlaute_dict[k])
    return result.decode()


def __print_invalid_ids():
    with open(root + "invalid_fids.txt", "w", encoding="utf-8") as errs:
        for i in invalid_idx:
            errs.write(i + ",")

        errs.close()


def __print_valid_pairs():

    with open(root + "comments.txt", "w", encoding="utf-8") as coms:
        for k in codes_raw.keys():
            if str(k) in invalid_idx:
                continue
            coms.write(str(k) + ', ' + comments_raw[k] + "\n")


def __preprocess():

    with open(root + "comments.json", "w", encoding="utf-8") as coms:
        coms.write("{\n")
        with open(root + "functions.json", "w", encoding="utf-8") as funcs:
            funcs.write("{\n")

            for k in codes_raw.keys():
                code = __replace_umlauts(codes_raw[k])
                comment = __replace_umlauts(comments_raw[k])
                if '.' in comment:
                    comment = comment.split('.')[0] + "\n\t*/\n"
                comment = comment.translate({ord(c): " " for c in "\"!@#$%^&()[]{};:,<>?\|`~-=_+"})

                commentCount = comment.split()
                if len(commentCount) > 13 or len(commentCount) < 3:
                    invalid_idx.append(str(k))
                    continue

                codeCount = code.split()
                if len(codeCount) > 100:
                    invalid_idx.append(str(k))
                    continue


                toParse = "class Parse {" + comment + code + '}'

                try:
                    javalang.parse.parse(toParse)
                except Exception:
                    invalid_idx.append(str(k))
                    continue

                lineCommentPattern = "//(.*?)\r?\n"
                code = regex.sub(lineCommentPattern, "\n", code) #remove inline comments
                code = str(code).strip().replace("\n", "")
                code = regex.sub(' +', ' ', code) #multiple whitespaces with one

                coms.write(__file_line(str(k), comment))
                funcs.write(__file_line(str(k), code))

            funcs.write("\n}")
            funcs.close()
        coms.write("\n}")
        coms.close()

def main():
    data = funcom.load()
    global codes_raw
    codes_raw = data[0]
    global comments_raw
    comments_raw = data[1]
    global invalid_idx
    invalid_idx = []
    __preprocess()
    __print_invalid_ids()
    __print_valid_pairs()


if __name__ == '__main__':
    main()
