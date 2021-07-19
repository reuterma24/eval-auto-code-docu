import os

import datasets.funcom_filtered_reduced.load as funcom
import javalang

# idea of this script:
# only take first sentence from comment
# remove pairs with invalid syntax

root = os.path.dirname(os.path.abspath(__file__)) + '/'

global codes_raw
global comments_raw

invalid_idx = []  # contains all indecies with invalid syntax


def __refactor_codes():
    for i, (k, v) in enumerate(codes_raw.items()):
        code = str(v)
        codeParse = code.replace('\n', "").replace("\t", "")
        toParse = "class Parse {" + codeParse + '}'

        try:
            javalang.parse.parse(toParse)
        except Exception:
            invalid_idx.append(str(k))
            continue


def __refactor_comments():
    for i, (k, v) in enumerate(comments_raw.items()):
        comment = str(v)

        if "/**" not in comment or "*/" not in comment:
            if str(k) not in invalid_idx:
                invalid_idx.append(str(k))


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

    __refactor_comments()
    __refactor_codes()

    __print_invalid_ids()
    return invalid_idx


if __name__ == '__main__':
    main()
