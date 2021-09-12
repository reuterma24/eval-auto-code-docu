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

if __name__ == '__main__':

    parser = argparse.ArgumentParser(description='')
    parser.add_argument('--gpu', type=str, help='0 or 1', default='0')
    parser.add_argument('--batch-size', dest='batch_size', type=int, default=200)
    parser.add_argument('--epochs', dest='epochs', type=int, default=30)
    parser.add_argument('--modeltype', dest='modeltype', type=str, default='codegnngru')
    parser.add_argument('--data', dest='dataprep', type=str, default='/vol/tmp/reuterma/extra_data')
    parser.add_argument('--outdir', dest='outdir', type=str, default='./modelout')
    parser.add_argument('--asthops', dest='hops', type=int, default=2)
    args = parser.parse_args()

    outdir = args.outdir
    dataprep = args.dataprep
    gpu = args.gpu
    batch_size = args.batch_size
    epochs = args.epochs
    modeltype = args.modeltype
    asthops = args.hops

    # set gpu here
    init_tf(gpu)

    # Load tokenizers
    tdatstok = pickle.load(open('{}/tdats.tok'.format(dataprep), 'rb'), encoding='UTF-8')
    comstok = pickle.load(open('{}/coms.tok'.format(dataprep), 'rb'), encoding='UTF-8')
    asttok = pickle.load(open('{}/smls.tok'.format(dataprep), 'rb'), encoding='UTF-8')

    tdatvocabsize = tdatstok.vocab_size
    comvocabsize = comstok.vocab_size
    astvocabsize = asttok.vocab_size

    # TODO: setup config
    config = dict()
    config['asthops'] = asthops
    config['tdatvocabsize'] = tdatvocabsize
    config['comvocabsize'] = comvocabsize
    config['smlvocabsize'] = astvocabsize

    # set sequence length for our input
    config['tdatlen'] = 50
    config['maxastnodes'] = 100
    config['comlen'] = 13

    config['batch_size'] = batch_size
    config['epochs'] = epochs

    # Load data
    seqdata = pickle.load(open('{}/dataset.pkl'.format(dataprep), 'rb'))


    #removing filtered FIDS
    cval = seqdata['cval']
    dsval = seqdata['dsval']
    dtval = seqdata['dtval']

    ctest = seqdata['ctest']
    dstest = seqdata['dstest']
    dttest = seqdata['dttest']

    ctrain = seqdata['ctrain']
    dstrain = seqdata['dstrain']
    dttrain = seqdata['dttrain']

    with open("invalid_fids.txt", "r") as f:
        invalid_fids = f.read().split(",")
        for i in invalid_fids:
            if int(i) in cval.keys():
                del cval[i]
            if int(i) in dsval.keys():
                del dsval[i]
            if int(i) in dtval.keys():
                del dtval[i]

            if int(i) in ctest.keys():
                del ctest[i]
            if int(i) in dstest.keys():
                del dstest[i]
            if int(i) in dttest.keys():
                del dttest[i]

            if int(i) in ctrain.keys():
                del ctrain[i]
            if int(i) in dstrain.keys():
                del dstrain[i]
            if int(i) in dttrain.keys():
                del dttrain[i]

        f.close()


    seqdata['cval'] = cval
    seqdata['ctest'] = ctest
    seqdata['ctrain'] = ctrain

    seqdata['dsval'] = dsval
    seqdata['dstest'] = dstest
    seqdata['dstrain'] = dstrain

    seqdata['dtval'] = dtval
    seqdata['dttest'] = dttest
    seqdata['dttrain'] = dttrain

    node_data = seqdata['strain_nodes']
    edges = seqdata['strain_edges']
    config['edge_type'] = 'sml'

    # model parameters
    steps = int(len(seqdata['ctrain']) / batch_size) + 1
    valsteps = int(len(seqdata['cval']) / batch_size) + 1

    # Print information
    print('tdatvocabsize {}'.format(tdatvocabsize))
    print('comvocabsize {}'.format(comvocabsize))
    print('smlvocabsize {}'.format(astvocabsize))
    print('batch size {}'.format(batch_size))
    print('steps {}'.format(steps))
    print('training data size {}'.format(steps * batch_size))
    print('vaidation data size {}'.format(valsteps * batch_size))
    print('------------------------------------------')

    # create model
    config, model = create_model(modeltype, config)

    print(model.summary())
    gen = batch_gen(seqdata, 'train', config, nodedata=node_data, edgedata=edges)

    checkpoint = ModelCheckpoint(outdir + "/models/" + modeltype + "_E{epoch:02d}.h5")

    valgen = batch_gen(seqdata, 'val', config, nodedata=seqdata['sval_nodes'], edgedata=seqdata['sval_edges'])
    callbacks = [checkpoint]

    model.fit_generator(gen, steps_per_epoch=steps, epochs=epochs, verbose=1, max_queue_size=4,
                        callbacks=callbacks, validation_data=valgen, validation_steps=valsteps)
