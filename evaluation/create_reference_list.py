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
    print("examples: " + str(len(preds)))
    f.close()
    fid = list()
    prediciton = list()

    for pred in preds:
        print(pred)
        a, b = str(pred).split("<s>")
        fid.append(a)
        prediciton.append(b)

    with open("test.txt", 'wa') as f:
        for i in enumerate(fid):
            f.writelines(fid[i] + ": " + prediciton[i])
