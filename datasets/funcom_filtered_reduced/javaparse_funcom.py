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

        if "/**" or "*/" not in comment:
            invalid_idx.append(str(k))



def main():
    data = funcom.load()
    global codes_raw
    codes_raw = data[0]
    global comments_raw
    comments_raw = data[0]

    __refactor_codes()
    __refactor_comments()

    return invalid_idx


if __name__ == '__main__':
    main()
