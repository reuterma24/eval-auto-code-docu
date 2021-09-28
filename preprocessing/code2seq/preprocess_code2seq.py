import os.path
import shutil

root = os.path.dirname(os.path.dirname(os.path.abspath(__file__))) + '/'

def preprocess():
    root = os.path.dirname(os.path.dirname(os.path.abspath(__file__))) # parent/parent

    global invalid_fids
    invalid_fids = list()

    # identifying split
    test_fids = open(root + "/split_fids/test_fids.txt", 'r').read().splitlines()
    val_fids = open(root + "/split_fids/val_fids.txt", 'r').read().splitlines()
    train_fids = open(root + "/split_fids/train_fids.txt", 'r').read().splitlines()
    print("train len:" + str(len(train_fids)))
    print("test len:" + str(len(test_fids)))
    print("val len:" + str(len(val_fids)))

    #create dic structure
    root = root + '/preprocessed_data/code2seq/split/'

    os.makedirs(root, exist_ok=True)
    shutil.rmtree(root, ignore_errors=True)

    os.makedirs(root + 'training/', exist_ok=True)
    os.makedirs(root + 'testing/', exist_ok=True)
    os.makedirs(root + 'evaluating/', exist_ok=True)

    comment_dict = dict()
    code_dict = dict()

    f = open('comments.txt', 'r')
    for _, line in enumerate(f):
        a, b = line.split(":")
        comment_dict[int(a)] = ''.join(b.strip())
    f.close()

    f = open('code.txt', 'r')
    for _, line in enumerate(f):
        a, b = line.split(":", 1)
        code_dict[int(a)] = ''.join(b.strip())
    f.close()

    idx = 0
    splitConst = 10000
    for i in train_fids:
        i = int(i)
        separator = idx // splitConst
        with open(root + "training/train{0}.java".format(separator), "a", encoding="utf-8") as train:
            if i in comment_dict and i in code_dict:
                train.writelines(str(comment_dict[i] + '\n' + code_dict[i] + '\n\n'))
                idx += 1

    idx = 0
    for i in test_fids:
        separator = idx // splitConst
        i = int(i)
        with open(root + "testing/test{0}.java".format(separator), "a", encoding="utf-8") as test:
            if i in comment_dict.keys() and i in code_dict.keys():
                test.writelines(str(comment_dict[i] + '\n' + code_dict[i] + '\n\n'))
                idx += 1
    idx = 0
    for i in val_fids:
        separator = idx // splitConst
        i = int(i)
        with open(root + "evaluating/evaluate{0}.java".format(separator), "a", encoding="utf-8") as validation:
            if i in comment_dict.keys() and i in code_dict.keys():
                validation.writelines(str(comment_dict[i] + '\n' + code_dict[i] + '\n\n'))
                idx += 1

   # os.system("sh approaches/code2seq/preprocess.sh")
