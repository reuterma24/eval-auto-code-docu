import argparse
import os
import pickle

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='')
    parser.add_argument('path', type=str, default=None)
    args = parser.parse_args()
    prediction_file = args.path

    f = open(prediction_file, 'rb')
    preds = str(f.read().splitlines())
    f.close()
    fid = list()
    prediciton = list()

    for pred in preds:
        a, b, c = pred.split("<s>")
        fid.append(a)
        prediciton.append(b)

    with open("test.txt") as f:
        for i in enumerate(fid):
            f.writelines(fid[i] + ": " + prediciton[i])
