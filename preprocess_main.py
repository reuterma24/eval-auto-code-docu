import os.path
import preprocessing.code2seq.preprocess_code2seq as code2seq
from subprocess import Popen

root = os.path.dirname(__file__)

N = 200  # number of code comment pairs

train_size = N * 0.8
test_size = N * 0.1
validation_size = N * 0.1
# pass theses as parameters

msg = "%s Code Comment Pairs used in general. %s for training, %s for testing and %s for validating." \
      % (str(N), str(train_size), str(test_size), str(validation_size))
print(msg)


reduce_script = root + ("/datasets/reduce_funcom.sh %s" % (str(N)))
process = Popen(reduce_script, shell=True)
process.wait()

code2seq.preprocess(N)
