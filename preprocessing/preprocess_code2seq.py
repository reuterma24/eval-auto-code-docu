import os
import datasets.funcom_filtered.load as funcom

data = funcom.load()
# THIS RIGHT NOW IS SOMEHOW A REDUCTION + CODE2SEQ PREPROCESS PROCEDURE
# RETHINK WHOLE STRUCTURE of preprocessing
# Read data from dataset and make it accessible
# change that to load from reduced created set
source_code = data[0]
comments = data[1]

root = '.\\preprocessed_data\\code2seq\\split\\'

train = open(root + "funcom.train.raw.txt", "w")
test = open(root + "funcom.test.raw.txt", "w")
validation = open(root + "funcom.val.raw.txt", "w")

for i in source_code:
    index = int(i)
    if index < 400:
        train.writelines(comments[i] + '\n' + source_code[i] + '\n')
    elif index < 450:
        test.writelines(comments[i] + '\n' + source_code[i] + '\n')
    else:
        validation.writelines(comments[i] + '\n' + source_code[i] + '\n')

# code2seq.main()

#  os.system("approaches\\code2seq\\preprocess.sh")
