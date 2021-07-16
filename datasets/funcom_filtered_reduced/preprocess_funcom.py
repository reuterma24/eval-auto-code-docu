import os

import datasets.funcom_filtered_reduced.load as funcom
import javalang

# idea of this script:
# only take first sentence from comment
# remove pairs with invalid syntax

data = funcom.load()
codes_raw = data[0]
comments_raw = data[1]

invalid_idx = []  # contains all indecies with invalid syntax

os.makedirs("./test", exist_ok=True)


def __file_line(idx, body):
    return "\t\"%s\": \"%s\",\n" % (idx, body)


def __refactor_codes():
    with open("test/functions.json", "w", encoding="utf-8") as funcs:
        funcs.write("{\n")

        for i in codes_raw:
            code = str(codes_raw[i])
            codeParse = code.replace('\n', "").replace("\t", "")
            codeWrite = code.replace('\n', "\\n").replace("\t", "\\t")
            toParse = "class Parse {" + codeParse + '}'

            try:
                javalang.parse.parse(toParse)
                funcs.write(__file_line(i, codeWrite))
            except Exception:
                invalid_idx.append(str(i))
                del comments_raw[i]
                continue

        funcs.write("}")
        funcs.close()


def __refactor_comments():
    with open("test/comments.json", "w", encoding="utf-8") as coms:
        coms.write("{\n")

        for i in comments_raw:
            comment = str(comments_raw[i])

            # if no '.' I assume its a single sentence
            if '.' in comment:
                split = comment.split(sep='.')
                comment = split[0] + "*/\n"

            comment = comment.replace('\n', "\\n").replace("\t", "\\t")
            coms.writelines(__file_line(i, comment))

        coms.write("}")
        coms.close()


def __print_invalid_ids():
    with open("invalid_ids.txt", "w", encoding="utf-8") as errs:
        errs.write("INVALID ID'S:\n")
        for i in invalid_idx:
            errs.write(i + ",\n")

        errs.close()


def main():
    __refactor_codes()
    __refactor_comments()
    __print_invalid_ids()


if __name__ == '__main__':
    main()
