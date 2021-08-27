import os.path
import shutil

import javalang
import regex
import sklearn.model_selection as ms

import datasets.funcom_filtered_reduced.load as funcom
#SWITCH BETWEEN THOSE FOR TESTING AND REAL RUN
#import datasets.funcom_filtered.load as funcom

root = os.path.dirname(os.path.abspath(__file__)) + '/'
global invalid_fids

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

def __print_invalid_ids():
    with open("invalid_fids.txt", "w", encoding="utf-8") as errs:
        for i in invalid_fids:
            errs.write(i + ",")

        errs.close()

def preprocess():
    root = os.path.dirname(os.path.abspath(__file__))

    data = funcom.load()
    code_dict = data[0]
    comment_dict = data[1]
    global invalid_fids
    invalid_fids = list()



    # identifying split
    test_fids = open(root + "/split_fids/test_fids.txt", 'r').read().splitlines()
    val_fids = open(root + "/split_fids/val_fids.txt", 'r').read().splitlines()
    train_fids = open(root + "/split_fids/train_fids.txt", 'r').read().splitlines()

    #create dic structure
    root = root + '/preprocessed_data/split/'

    os.makedirs(root, exist_ok=True)
    shutil.rmtree(root, ignore_errors=True)

    os.makedirs(root + 'training/', exist_ok=True)
    os.makedirs(root + 'testing/', exist_ok=True)
    os.makedirs(root + 'evaluating/', exist_ok=True)

    #process pairs
    for k in list(code_dict.keys()):
        code = __replace_umlauts(code_dict[k])
        comment = __replace_umlauts(comment_dict[k])
        if '.' in comment:
            comment = comment.split('.')[0] + "\n\t*/\n"
        comment = comment.translate({ord(c): " " for c in "\"!@#$%^&()[]{};:,<>?\|`~-=_+"})

        commentCount = comment.split()
        if len(commentCount) > 13 or len(commentCount) < 3:
            invalid_fids.append(str(k))
            del comment_dict[k]
            del code_dict[k]
            continue

        codeCount = code.split()
        if len(codeCount) > 100:
            invalid_fids.append(str(k))
            del comment_dict[k]
            del code_dict[k]
            continue

        toParse = "class Parse {" + comment + code + '}'

        try:
            javalang.parse.parse(toParse)
        except Exception:
            invalid_fids.append(str(k))
            del comment_dict[k]
            del code_dict[k]
            continue

        lineCommentPattern = "//(.*?)\r?\n"
        code = regex.sub(lineCommentPattern, "\n", code)  # remove inline comments
        docCommentPattern = "/\*\**((?:[^*]+|\*[^/])*)\*/"
        code = regex.sub(docCommentPattern, "", code)
        code = str(code).strip().replace("\n", "")
        code = regex.sub(' +', ' ', code)  # multiple whitespaces with one
        code = code.replace('\n', "").replace('\t', "")

        code_dict[k] = code
        comment_dict[k] = regex.sub(' +', ' ', comment.replace('\n', "").replace('\t', ""))

    __print_invalid_ids()

    print("train len:" + str(len(train_fids)))
    print("test len:" + str(len(test_fids)))
    print("val len:" + str(len(val_fids)))

    idx = 0
    splitConst = 10000
    for i in train_fids:
        separator = idx // splitConst
        with open(root + "training/train{0}.java".format(separator), "a", encoding="utf-8") as train:
            if i in comment_dict and i in code_dict:
                train.writelines(str(comment_dict[i] + '\n' + code_dict[i] + '\n\n'))
                idx += 1

    idx = 0
    for i in test_fids:
        separator = idx // splitConst
        with open(root + "testing/test{0}.java".format(separator), "a", encoding="utf-8") as test:
            if i in comment_dict.keys() and i in code_dict.keys():
                test.writelines(str(comment_dict[i] + '\n' + code_dict[i] + '\n\n'))
                idx += 1
    idx = 0
    for i in val_fids:
        separator = idx // splitConst
        with open(root + "evaluating/evaluate{0}.java".format(separator), "a", encoding="utf-8") as validation:
            if i in comment_dict.keys() and i in code_dict.keys():
                validation.writelines(str(comment_dict[i] + '\n' + code_dict[i] + '\n\n'))
                idx += 1

    os.system("sh approaches/code2seq/preprocess.sh")
