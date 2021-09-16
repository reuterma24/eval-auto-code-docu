import argparse
import os
import pickle

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='')
    parser.add_argument('path', type=str, default=None)
    args = parser.parse_args()
    prediction_file = args.path

    f = open(prediction_file, 'r')
    preds = dict()
    for _, line in enumerate(f):
        a, b = line.split("\t")
        c = b.split("</s>")
        d = str(c[0]).replace("<s>", "").strip()
        preds[int(a)] = ''.join(d)


    with open("test.txt", 'w') as f:
        for k, v in preds.items():
            f.write(str(k) + ": " + str(v) + '\n')
