import os

root = os.path.dirname(os.path.dirname(os.path.abspath(__file__))) + '/'
source = root + "/approaches/code2seq/models/funcom_reduced-model/"


def last_model_iteration():
    max_iter = 0
    for f in os.scandir(source):
        if f.is_dir():
            max_iter = max_iter + 1

    return source + "epoch{}/".format(max_iter)


def load():
    path = last_model_iteration()
    ref_path = path + "ref.txt"
    pred_path = path + "pred.txt"

    f_ref = open(ref_path, 'r')
    ref = f_ref.read().split('\n')
    f_ref.close()

    p_ref = open(pred_path, 'r')
    pred = p_ref.read().split('\n')
    p_ref.close()

    del ref[-1]
    del pred[-1]

    return ref, pred
