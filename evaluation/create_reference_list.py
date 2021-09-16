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

  #  for pred in preds:
   #     a, b = str(pred).split("<s>")
    #    c = b.split("</s>")
    #    print(a.strip())
    #    print(str(a))
    #    fid.append(int(a))
    #    prediciton.append(str(c[0]))

    preds = dict()
    predicts = open(prediction_file, 'r')
    for c, line in enumerate(predicts):
        (fid, pred) = line.split('\t')
        fid = int(fid)
        pred = pred.split()
        preds[fid] = pred
    predicts.close()


    with open("test.txt", 'w') as f:
        for k, v in preds.items():
            f.writelines(str(k) + ": " + str(v))
