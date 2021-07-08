import argparse
import collections
import random
import math
import os
import itertools

root = os.path.dirname(__file__)

def load_pid():
    f = root +'/fid_pid'
    pidtofid = collections.defaultdict(list)
    for line in open(f, 'r').readlines()[1:]:
        t = line.split('\t')
        fid = int(t[0])
        pid = int(t[1])
        pidtofid[pid].append(fid)

    return pidtofid


def load_data(fname):
    data = {}
    fname = root + '/' + fname
    for line in open(fname):
        tmp = line.split('\t')
        fid = int(tmp[0])
        value = tmp[1]
        data[fid] = value
    return data


def write(data, fname):
    fname = root + '/' + fname
    fo = open(fname, 'w')
    for fid, string in data.items():
        fo.write("{}\t{}\n".format(fid, string))
    fo.close()


def main(valid_size, test_size, samples = None, seed = None):
    print("Making new train/valid/test split")

    # set seed for random splits
    if seed is not None:
        print("Using seed {}".format(seed))
    else:
        print("Using random seed")

    random.seed(a=seed)

    f1 = 'comments'
    f2 = 'functions'

    pidlist = load_pid()
    coms = load_data(f1)
    src = load_data(f2)

    shuffle_list = list(pidlist.keys())
    random.shuffle(shuffle_list)

    testnum = math.ceil(len(shuffle_list) * test_size)
    validnum = math.ceil(len(shuffle_list) * valid_size)

    testset = shuffle_list[:testnum]
    validset = shuffle_list[testnum:(testnum + validnum)]
    trainset = shuffle_list[(testnum + validnum):]

    print("Project counts:")
    print("Train: {} Valid: {} Test: {}".format(len(trainset), len(validset), len(testset)))

    trainfun = {}
    validfun = {}
    testfun = {}

    traincom = {}
    validcom = {}
    testcom = {}

    for pid in trainset:
        for fid in pidlist[pid]:
            traincom[fid] = coms[fid].strip()
            trainfun[fid] = src[fid].strip()
    for pid in validset:
        for fid in pidlist[pid]:
            validcom[fid] = coms[fid].strip()
            validfun[fid] = src[fid].strip()
    for pid in testset:
        for fid in pidlist[pid]:
            testcom[fid] = coms[fid].strip()
            testfun[fid] = src[fid].strip()


    ftrain = './train/functions.train'
    fvalid = './valid/functions.valid'
    ftest = './test/functions.test'

    ctrain = './train/comments.train'
    cvalid = './valid/comments.valid'
    ctest = './test/comments.test'

    if samples is not None:
        train_samples = math.ceil(samples * (1 - (test_size + valid_size)))
        test_samples = math.ceil(samples * test_size)
        valid_samples = math.ceil(samples * valid_size)

        trainfun = dict(itertools.islice(trainfun.items(), train_samples))
        traincom = dict(itertools.islice(traincom.items(), train_samples))

        testfun = dict(itertools.islice(testfun.items(), test_samples))
        testcom = dict(itertools.islice(testcom.items(), test_samples))

        validfun = dict(itertools.islice(validfun.items(), valid_samples))
        validcom = dict(itertools.islice(validcom.items(), valid_samples))

    write(trainfun, ftrain)
    write(validfun, fvalid)
    write(testfun, ftest)

    write(traincom, ctrain)
    write(validcom, cvalid)
    write(testcom, ctest)


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description='')
    parser.add_argument('--seed', type=int, default=None)
    parser.add_argument('--valid-size', type=float, default=0.05)
    parser.add_argument('--test-size', type=float, default=0.05)
    parser.add_argument('--samples', type=int, default=None)
    args = parser.parse_args()

    # Get args ####
    random_seed = args.seed
    val = args.valid_size
    test = args.test_size
    n = args.samples  # to limit the overall number of samples taken

    main(val, test, n, random_seed)

