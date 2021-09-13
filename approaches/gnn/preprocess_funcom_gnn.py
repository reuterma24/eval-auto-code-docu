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
print("invalid id's:" + str(len(invalid_fids)))
print('Loading .pkl')
seqdata = pickle.load(open('{}/dataset.pkl'.format(path), 'rb'))
print("done loading ...")
print("start removing")


new_dict = dict(seqdata)

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


print("initial length: " + str(len(ctrain) + len(ctest) + len(cval)))
for i in invalid_fids:
    print("ID: " + i)
    del new_dict['cval'][i]
    del new_dict['dsval'][i]
    del new_dict['dtval'][i]

    del new_dict['ctest'][i]
    del new_dict['dstest'][i]
    del new_dict['dttest'][i]

    del new_dict['ctrain'][i]
    del new_dict['dstrain'][i]
    del new_dict['dttrain'][i]


#seqdata['cval'] = cval
#seqdata['ctest'] = ctest
#seqdata['ctrain'] = ctrain

#seqdata['dsval'] = dsval
#seqdata['dstest'] = dstest
#seqdata['dstrain'] = dstrain

#seqdata['dtval'] = dtval
#seqdata['dttest'] = dttest
#seqdata['dttrain'] = dttrain

print("final length: " + str(len(new_dict['ctrain']) + len(new_dict['ctest']) + len(new_dict['cval'])))

with open(path + "/dataset_filtered.pkl", "wb") as outfile:
    pickle.dump(new_dict, outfile)
print("done")
