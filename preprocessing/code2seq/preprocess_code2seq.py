import os
import datasets.funcom_filtered_reduced.load as funcom
# import datasets.funcom_filtered.load as funcom


def preprocess(n):
    length = n

    data = funcom.load()
    codes = data[0]
    comments = data[1]

    root = 'preprocessing/code2seq/preprocessed_data/split/'
    os.makedirs(root, exist_ok=True)
    os.makedirs(root + 'training/', exist_ok=True)
    os.makedirs(root + 'testing/', exist_ok=True)
    os.makedirs(root + 'evaluating/', exist_ok=True)

    train = open(root + "training/train.java", "w")
    test = open(root + "testing/test.java", "w")
    validation = open(root + "evaluating/evaluate.java", "w")

    train_size = length * 0.8  # needs to be reworked -- calculation safe, makes sense????
    test_size = length * 0.1 + train_size  # needs to be reworked

    index = 0
    for i in codes:
        if index < train_size:
            train.writelines(comments[i] + '\n' + codes[i] + '\n')
        elif index < test_size:
            test.writelines(comments[i] + '\n' + codes[i] + '\n')
        else:
            validation.writelines(comments[i] + '\n' + codes[i] + '\n')

        index += 1


#execute in git bash not cmd
os.system("approaches\\code2seq\\preprocess.sh")
