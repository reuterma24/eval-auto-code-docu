import pickle
import os


def main():
    #bad_fid = pickle.load(open('autogenfid.pkl', 'rb'))

    root = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__)))) + '/'

    bad_fid = open(root + "datasets/funcom_filtered_reduced/invalid_ids.txt").read()
    bad_fid = bad_fid.split(',')

    comdata = root + "datasets/funcom_filtered_reduced/comments.txt"
    good_fid = []
    outfile = './output/dataset.coms'

    fo = open(outfile, 'w')
    for line in open(comdata):
        tmp = line.split(',')
        fid = int(tmp[0].strip())
        if fid in bad_fid:
            continue
        com = tmp[1].strip()
        com = com.split()
        if len(com) > 13 or len(com) < 3:
            continue
        com = ' '.join(com)
        fo.write('{}, <s> {} </s>\n'.format(fid, com))


    fo.close()
