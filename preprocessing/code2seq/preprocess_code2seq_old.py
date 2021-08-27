import os.path
import shutil

import sklearn.model_selection as ms

import datasets.funcom_filtered_reduced.load as funcom

# import datasets.funcom_filtered.load as funcom

root = os.path.dirname(os.path.abspath(__file__)) + '/'

umlaute_dict = {
    b'\xc3\xa4': b'ae',  # U+00E4	   \xc3\xa4
    b'\xc3\xb6': b'oe',  # U+00F6	   \xc3\xb6
    b'\xc3\xbc': b'ue',  # U+00FC	   \xc3\xbc
    b'\xc3\x84': b'Ae',  # U+00C4	   \xc3\x84
    b'\xc3\x96': b'Oe',  # U+00D6	   \xc3\x96
    b'\xc3\x9c': b'Ue',  # U+00DC	   \xc3\x9c
    b'\xc3\x9f': b'ss',  # U+00DF	   \xc3\x9f
}


def __replace_umlauts(text: str):
    result = text.encode("utf-8")
    for k in umlaute_dict.keys():
        result = result.replace(k, umlaute_dict[k])
    return result.decode()


def preprocess(n):
    root = os.path.dirname(os.path.abspath(__file__)) + '/preprocessed_data/split/'

    data = funcom.load()
    codes_raw = data[0]
    comments_raw = data[1]
    invalid_fids = data[2]

    os.makedirs(root, exist_ok=True)
    shutil.rmtree(root, ignore_errors=True)

    os.makedirs(root + 'training/', exist_ok=True)
    os.makedirs(root + 'testing/', exist_ok=True)
    os.makedirs(root + 'evaluating/', exist_ok=True)

    pairs = list()
    for k in codes_raw.keys():
        if str(k) in invalid_fids:
            continue

        comment = comments_raw[k]
        if '.' in comment:
            comment = comment.split('.')[0] + "\n\t*/\n"

        comment = comment.translate({ord(c): " " for c in "\"!@#$%^&()[]{};:,<>?\|`~-=_+"})

        code = (comment + codes_raw[k] + '\n')
        code = __replace_umlauts(code)
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
