import os
import datasets.funcom_filtered_tiny.load as funcom_tiny

data = funcom_tiny.load()

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
