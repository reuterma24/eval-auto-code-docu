import preprocessing.preprocess_code2seq as code2seq
import datasets.funcom_filtered.load as funcom


#THIS RIGHT NOW IS SOMEHOW A REDUCTION + CODE2SEQ PREPROCESS PROCEDURE
# RETHINK WHOLE STRUCTURE of preprocessing

# Read data from dataset and make it accessible
# change that to load from reduced created set
data = funcom.load()

source_code = data[0]
comments = data[1]

root = 'preprocessing\\funcom_preprocessed_tiny\\split\\'


# make it usable for code2seq somehow

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

code2seq.main()
