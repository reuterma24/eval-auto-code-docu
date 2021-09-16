import argparse
import os
import pickle

if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='')
    parser.add_argument('path', type=str, default=None)
    args = parser.parse_args()
    prediction_file = args.path

    f = open(prediction_file, 'rb')
    preds = f.readlines()
    print("examples: " + str(len(preds)))
    f.close()
    fid = list()
    prediciton = list()

    for pred in preds:
        a, b = str(pred).split("<s>")
        c = b.split("</s>")
        print(c)
        print(str(c))
        fid.append(int(a))
        prediciton.append(str(c))

    with open("test.txt", 'w') as f:
        for i in enumerate(fid):
            f.writelines(fid[i] + ": " + prediciton[i])
