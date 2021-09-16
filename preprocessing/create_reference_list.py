import argparse
import datasets.funcom_filtered.load as funcom
import os
import regex
import re

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


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='')
    parser.add_argument('path', type=str, default=None)
    args = parser.parse_args()
    prediction_file = args.path

    data = funcom.load()
    comments = data[1]
    pattern = re.compile(r'([^a-zA-Z0-9 ])|([a-z0-9_][A-Z])')

    root = os.path.dirname(os.path.abspath(__file__))
    f = open(root + "/split_fids/train_fids.txt", 'r')
    train_fids = f.read().splitlines()
    f.close()
    with open("test_refs.txt", 'w') as f:
        for fid in train_fids:
            if fid in comments:
                comment = __replace_umlauts(comments[fid])

                if '.' in comment:
                    comment = comment.split('.')[0]
                comment = pattern.sub(' ', comment)
                comment = regex.sub(' +', ' ', comment.replace('\n', "").replace('\t', ""))
                comment = comment.lower()

                f.write(str(fid) + ": " + comment + '\n')

    f = open(prediction_file, 'r')
    preds = dict()
    for _, line in enumerate(f):
        a, b = line.split("\t")
        c = b.split("</s>")
        d = str(c[0]).replace("<s>", "").strip()
        preds[int(a)] = ''.join(d)
    f.close()

    with open("test.txt", 'w') as f:
        for k, v in preds.items():
            f.write(str(k) + ": " + str(v))
