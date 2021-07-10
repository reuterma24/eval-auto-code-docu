import os.path
import preprocessing.code2seq.preprocess_code2seq as code2seq
import datasets.funcom_tokenized.shuffle as shuffle
from subprocess import Popen

root = os.path.dirname(os.path.abspath(__file__))

N = 5000  # number of code comment pairs
train_size = 0.8
test_size = 0.1
validation_size = 0.1

msg = "%s Code Comment Pairs used in general. %s for training, %s for testing and %s for validating." \
     % (str(N), str(train_size * N), str(test_size * N), str(validation_size * N))
print(msg)

reduce_script = root + ("/datasets/reduce_funcom_filtered.sh %s" % (str(N)))
process = Popen(reduce_script, shell=True)
process.wait()

# shuffle.main(validation_size, test_size)

# RUN CODE2SEQ PREPROCESSING
code2seq.preprocess(N)
