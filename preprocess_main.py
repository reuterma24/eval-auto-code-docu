import os.path
import preprocessing.code2seq.preprocess_code2seq as code2seq
import datasets.funcom_filtered_reduced.preprocess_funcom as preproc_funcom
from subprocess import Popen

from datasets.funcom_filtered_reduced import javaparse_funcom

root = os.path.dirname(os.path.abspath(__file__))

#reduce_script = root + ("/datasets/reduce_funcom_filtered.sh %s" % (str(N)))
#process = Popen(reduce_script, shell=True)
#process.wait()

# REMOVE INVALID SYNTAX AND ONLY TAKE FIRST SENTENCE OF COMMENT
#print("Startin to preprocess funcom - finding invalid function/comment ids")
#preproc_funcom.main()
#print("done...")

# RUN CODE2SEQ PREPROCESSING
code2seq.preprocess()
