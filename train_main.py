import os.path
from subprocess import Popen

root = os.path.dirname(__file__)

# RUN CODE2SEQ TRAINING
process = Popen(root + "/approaches/code2seq/train.sh", shell=True)
process.wait()
