import argparse
import os
import pickle

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='')
    parser.add_argument('path', type=str, default=None)
    args = parser.parse_args()
    prediction_file = args.path

    f = open(prediction_file, 'rb')
    preds = f.read().splitlines()
    f.close()
    fid = list()
    prediciton = list()
    for pred in preds:
        res = pred.split('<s>')
        fid.append(res[0])
        prediciton.append(res[1])

    with open("test.txt") as f:
        for i in enumerate(fid):
            f.writelines(fid[i] + ": " + prediciton[i])
