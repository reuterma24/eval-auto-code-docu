import os.path
import shutil

import javalang
import sklearn.model_selection as ms

import datasets.funcom_filtered_reduced.load as funcom

# import datasets.funcom_filtered.load as funcom

root = os.path.dirname(os.path.abspath(__file__)) + '/'


def preprocess(n, ):
    data = funcom.load()
    codes_raw = data[0]
    comments_raw = data[1]
    invalid_ids = data[2]

    root = os.path.dirname(os.path.abspath(__file__)) + '/preprocessed_data/split/'
    os.makedirs(root, exist_ok=True)
    shutil.rmtree(root, ignore_errors=True)

    os.makedirs(root + 'training/', exist_ok=True)
    os.makedirs(root + 'testing/', exist_ok=True)
    os.makedirs(root + 'evaluating/', exist_ok=True)

    pairs = list()
    for k in codes_raw.keys():
        if str(k) in invalid_ids:
            continue
        comment = comments_raw[k]

        if '.' in comment:
            comment = comment.split('.')[0] + " */\n"

        code = (comment + codes_raw[k] + '\n')
        pairs.append(code)

    train_data, test_val_data = ms.train_test_split(pairs, test_size=0.2, train_size=0.8, shuffle=False)
    test_data, val_data = ms.train_test_split(test_val_data, test_size=0.5, train_size=0.5, shuffle=False)
    print("train len:" + str(len(train_data)))
    print("test len:" + str(len(test_data)))
    print("val len:" + str(len(val_data)))

    splitConst = 10000
    for idx, i in enumerate(train_data):
        separator = idx // splitConst
        with open(root + "training/train{0}.java".format(separator), "a", encoding="utf-8") as train:
            train.writelines(str(i))

    for idx, i in enumerate(test_data):
        separator = idx // splitConst
        with open(root + "testing/test{0}.java".format(separator), "a", encoding="utf-8") as test:
            test.writelines(str(i))

    for idx, i in enumerate(val_data):
        separator = idx // splitConst
        with open(root + "evaluating/evaluate{0}.java".format(separator), "a", encoding="utf-8") as validation:
            validation.writelines(str(i))

    os.system("sh approaches/code2seq/preprocess.sh")
