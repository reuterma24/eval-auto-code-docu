import os.path
import preprocessing.code2seq.preprocess_code2seq as code2seq
import preprocessing.preprocess_general as general
from subprocess import Popen


#root = os.path.dirname(os.path.abspath(__file__))

#reduce_script = root + ("/datasets/reduce_funcom_filtered.sh %s" % (str(3000)))
#process = Popen(reduce_script, shell=True)
#process.wait()


# GENERAL PREPROCESSING
print("Applying general preprocessing")
#general.preprocess()

# RUN CODE2SEQ PREPROCESSING
print("Preprocessing data for code2seq")
code2seq.preprocess()
