import preprocessing.code2seq.preprocess_code2seq as code2seq
from subprocess import Popen

N = 500  # number of code comment pairs

#process = Popen("D:/AutomatedCodeDocumentation/eval-auto-code-docu/datasets/reduce_funcom.sh %s" % (str(N)), shell=True)
#process.wait()

code2seq.preprocess(N)
