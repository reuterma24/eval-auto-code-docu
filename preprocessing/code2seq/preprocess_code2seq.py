import os
import shutil
import sklearn.model_selection as ms

import datasets.funcom_filtered_reduced.load as funcom
# import datasets.funcom_filtered.load as funcom



def preprocess(n):
    data = funcom.load()
    codes_raw = data[0]
    comments_raw = data[1]

    root = 'preprocessing/code2seq/preprocessed_data/split/'
    os.makedirs(root, exist_ok=True)
    os.makedirs(root + 'training/', exist_ok=True)
    os.makedirs(root + 'testing/', exist_ok=True)
    os.makedirs(root + 'evaluating/', exist_ok=True)

    with open(root + "training/train.java", "w", encoding="utf-8") as train:
        with open(root + "testing/test.java", "w", encoding="utf-8") as test:
            with open(root + "evaluating/evaluate.java", "w", encoding="utf-8") as validation:

                pairs = list()
                for i in codes_raw:
                    pairs.append(comments_raw[i] + codes_raw[i])

                train_data, test_val_data = ms.train_test_split(pairs, test_size=0.2, train_size=0.8, shuffle=False)

                print("train len:" + str(len(train_data)))

                test_data, val_data = ms.train_test_split(test_val_data, test_size=0.5, train_size=0.5, shuffle=False)

                print("test len:" + str(len(test_data)))
                print("val len:" + str(len(val_data)))
                for i in train_data:
                    train.writelines(str(i))

                for i in test_data:
                    test.writelines(str(i))

                for i in val_data:
                    validation.writelines(str(i))

    os.system("approaches\\code2seq\\preprocess.sh")
