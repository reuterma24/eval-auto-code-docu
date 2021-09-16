import os;
import pickle;
import argparse

if __name__ == '__main__':
        parser = argparse.ArgumentParser(description='')
        parser.add_argument('path' ,type=str, default=None)
        args=parser.parse_args()
        path = args.path

        with open(path, "rb") as hist:
                data = pickle.load(hist)
                print(data.keys())
                val_scores = data['val_accuracy']
                for i, v in enumerate(val_scores):
                        number = str(v)
                        print("epoch " + str(i+1)+ ': ' + number[0:6])

