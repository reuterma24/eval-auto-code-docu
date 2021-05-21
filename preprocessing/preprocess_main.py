import funcom_processed.load
import preprocess_code2seq

# Read data from dataset and make it accessible

data = funcom_processed.load.load()

source_code = data[0]
comments = data[1]

root = 'D:\\AutomatedCodeDocumentationApproaches\\datasets\\funcom_processed_tiny\\split\\'


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

preprocess_code2seq.main()
