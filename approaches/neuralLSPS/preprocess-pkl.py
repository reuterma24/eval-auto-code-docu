import argparse
import os
import pickle
import random
import sys
import time
import traceback
import numpy as np
import tensorflow as tf
from keras.callbacks import ModelCheckpoint, Callback
import keras.backend as K


def print_keys():
    path = '/vol/tmp/reuterma/extra_data'
    print('Loading .pkl')
    seqdata = pickle.load(open('{}/dataset.pkl'.format(path), 'rb'))

    config = seqdata['config']
    print(seqdata.keys())
    print(config.keys())


def preprocess():
    print("Removing invalid fids from dataset.pkl")

    path = '/vol/tmp/reuterma/extra_data/neuralLSPS'

    print("loading valid fids")
    valid_fids = list()
    with open("comments.txt", 'r') as f:
        for _, line in enumerate(f):
            fid, b = line.split(":")
            valid_fids.append(int(fid))
        print("done")

    print("valid id's: " + str(len(valid_fids)))

    print('Loading .pkl')
    seqdata = pickle.load(open('{}/dataset.pkl'.format(path), 'rb'))
    print("done loading ...")
    print("start removing")

    old_dict = dict(seqdata)

    # removing filtered FIDS
    cval = old_dict['cval']
    dsval = old_dict['dsval']
    dval = old_dict['dval']

    ctest = old_dict['ctest']
    dstest = old_dict['dstest']
    dtest = old_dict['dtest']

    ctrain = old_dict['ctrain']
    dstrain = old_dict['dstrain']
    dtrain = old_dict['dtrain']

    cval_2 = dict()
    dsval_2 = dict()
    dval_2 = dict()

    ctest_2 = dict()
    dstest_2 = dict()
    dtest_2 = dict()

    ctrain_2 = dict()
    dstrain_2 = dict()
    dtrain_2 = dict()

    print("initial length: " + str(len(ctrain) + len(ctest) + len(cval)))
    for i in valid_fids:
        
        val = cval.pop(i, None)
        if val is not None:
            cval_2[i] = val

        val = dsval.pop(i, None)
        if val is not None:
            dsval_2[i] = val

        val = dval.pop(i, None)
        if val is not None:
            dval_2[i] = val

        val = ctest.pop(i, None)
        if val is not None:
            ctest_2[i] = val

        val = dstest.pop(i, None)
        if val is not None:
            dstest_2[i] = val

        val = dtest.pop(i, None)
        if val is not None:
            dtest_2[i] = val

        val = ctrain.pop(i, None)
        if val is not None:
            ctrain_2[i] = val

        val = dstrain.pop(i, None)
        if val is not None:
            dstrain_2[i] = val

        val = dtrain.pop(i, None)
        if val is not None:
            dtrain_2[i] = val

    print("final length: " + str(len(ctrain_2) + len(ctest_2) + len(cval_2)))

    new_dict = dict()
    new_dict['cval'] = cval_2
    new_dict['dsval'] = dsval_2
    new_dict['dval'] = dval_2

    new_dict['ctrain'] = ctrain_2
    new_dict['dstrain'] = dstrain_2
    new_dict['dtrain'] = dtrain_2

    new_dict['ctest'] = ctest_2
    new_dict['dstest'] = dstest_2
    new_dict['dtest'] = dtest_2
    
    with open(path + "/dataset_filtered.pkl", "wb") as outfile:
        pickle.dump(new_dict, outfile)
    print("done")


if __name__ == '__main__':
    print_keys()
