import os.path
import preprocessing.code2seq.preprocess_code2seq as code2seq
import preprocessing.preprocess_general as general
import preprocessing.preprocess_pkl_caller as caller
import preprocessing.preprocess_pkl_caller2 as caller2
from subprocess import Popen

### HELPFUL SCRIPT TO PRODUCE A SUBSET OF FUNCOM FOR WORKING ON A LOCAL MACHINE ###
#root = os.path.dirname(os.path.abspath(__file__))
#reduce_script = root + ("/datasets/reduce_funcom_filtered.sh %s" % (str(3000)))
#process = Popen(reduce_script, shell=True)
#process.wait()


# GENERAL PREPROCESSING
print("Applying general preprocessing")
general.preprocess()

# RUN CODE2SEQ PREPROCESSING
print("Preprocessing data for code2seq")
code2seq.preprocess()


### THE FOLLOWING 'CALLERS' ARE NEEDED BECAUSE OF OCCURING PICKLE ERRORS
# RUN .PKL PREPROCESSING FOR GNN, ATTNFC
caller.preprocess()

# RUN .PKL PREPROCESSING FOR NEURALLSPS
caller2.preprocess()

