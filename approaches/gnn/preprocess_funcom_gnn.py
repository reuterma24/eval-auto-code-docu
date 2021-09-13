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
from utils.model import create_model
from utils.myutils import batch_gen, init_tf

print("Removing invalid fids from dataset.pkl")

path = '/vol/tmp/reuterma/extra_data'

print("Loading invalid fids")
f = open("../../invalid_fids.txt", "r")
invalid_fids = f.read().split(",")
f.close()
print('Loading .pkl')
seqdata = pickle.load(open('{}/dataset.pkl'.format(path), 'rb'))
print("done loading ...")
print("start removing")

# removing filtered FIDS
cval = seqdata['cval']
dsval = seqdata['dsval']
dtval = seqdata['dtval']

ctest = seqdata['ctest']
dstest = seqdata['dstest']
dttest = seqdata['dttest']

ctrain = seqdata['ctrain']
dstrain = seqdata['dstrain']
dttrain = seqdata['dttrain']


print("initial length:" + str(len(seqdata)))
for i in invalid_fids:
    cval.pop(i, None)
    dsval.pop(i, None)
    dtval.pop(i, None)

    ctest.pop(i, None)
    dstest.pop(i, None)
    dttest.pop(i, None)

    ctrain.pop(i, None)
    dstrain.pop(i, None)
    dttrain.pop(i, None)

seqdata['cval'] = cval
seqdata['ctest'] = ctest
seqdata['ctrain'] = ctrain

seqdata['dsval'] = dsval
seqdata['dstest'] = dstest
seqdata['dstrain'] = dstrain

seqdata['dtval'] = dtval
seqdata['dttest'] = dttest
seqdata['dttrain'] = dttrain

print("final length:" + str(len(seqdata)))

outfile = open(path + "/dataset_filtered", "wb")
pickle.dump(seqdata, outfile)
print("done")
