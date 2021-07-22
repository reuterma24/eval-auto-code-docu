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


def __replace_umlauts(text: str):
    result = text.encode("utf-8")
    for k in umlaute_dict.keys():
        result = result.replace(k, umlaute_dict[k])
    return result.decode()


def __print_invalid_ids():
    with open(root + "invalid_ids.txt", "w", encoding="utf-8") as errs:
        for i in invalid_idx:
            errs.write(i + ",")

        errs.close()


def __filter_invalid_syntax():
    for k in codes_raw.keys():
        code = (comments_raw[k] + codes_raw[k] + '\n')
        code = __replace_umlauts(code)
        toParse = "class Parse {" + code + '}'

        try:
            javalang.parse.parse(toParse)
        except Exception:
            invalid_idx.append(str(k))
            continue

    __print_invalid_ids()


def main():
    data = funcom.load()
    global codes_raw
    codes_raw = data[0]
    global comments_raw
    comments_raw = data[1]
    global invalid_idx
    invalid_idx = []
    __filter_invalid_syntax()


if __name__ == '__main__':
    main()
