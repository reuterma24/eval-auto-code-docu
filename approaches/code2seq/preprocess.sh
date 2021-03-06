#!/usr/bin/env bash
###########################################################
# Change the following values to preprocess a new dataset.
# TRAIN_DIR, VAL_DIR and TEST_DIR should be paths to      
#   directories containing sub-directories with .java files
# DATASET_NAME is just a name for the currently extracted 
#   dataset.                                              
# MAX_DATA_CONTEXTS is the number of contexts to keep in the dataset for each 
#   method (by default 1000). At training time, these contexts
#   will be downsampled dynamically to MAX_CONTEXTS.
# MAX_CONTEXTS - the number of actual contexts (by default 200) 
# that are taken into consideration (out of MAX_DATA_CONTEXTS)
# every training iteration. To avoid randomness at test time, 
# for the test and validation sets only MAX_CONTEXTS contexts are kept 
# (while for training, MAX_DATA_CONTEXTS are kept and MAX_CONTEXTS are
# selected dynamically during training).
# SUBTOKEN_VOCAB_SIZE, TARGET_VOCAB_SIZE -   
#   - the number of subtokens and target words to keep 
#   in the vocabulary (the top occurring words and paths will be kept). 
# NUM_THREADS - the number of parallel threads to use. It is 
#   recommended to use a multi-core machine for the preprocessing 
#   step and set this value to the number of cores.
# PYTHON - python3 interpreter alias.

TRAIN_DIR=preprocessing/code2seq/preprocessed_data/split/training
VAL_DIR=preprocessing/code2seq/preprocessed_data/split/evaluating
TEST_DIR=preprocessing/code2seq/preprocessed_data/split/testing

DATASET_NAME=funcom
MAX_DATA_CONTEXTS=1000
MAX_CONTEXTS=200
SUBTOKEN_VOCAB_SIZE=186277
TARGET_VOCAB_SIZE=26347
NUM_THREADS=64
PYTHON=python
###########################################################

TRAIN_DATA_FILE=${DATASET_NAME}.train.raw.txt
VAL_DATA_FILE=${DATASET_NAME}.val.raw.txt
TEST_DATA_FILE=${DATASET_NAME}.test.raw.txt
EXTRACTOR_JAR=approaches/code2seq/JavaExtractor/JPredict/target/JavaExtractor-0.0.1-SNAPSHOT.jar

mkdir -p approaches/code2seq/data
mkdir -p approaches/code2seq/data/${DATASET_NAME}

echo "Extracting paths from validation set..."
COUNTER=0
for entry in ${VAL_DIR}/*.java
do
  echo "val file ${COUNTER}"
  ${PYTHON} approaches/code2seq/JavaExtractor/extract.py --file "$entry" --max_path_length 8 --max_path_width 2 --num_threads ${NUM_THREADS} --jar ${EXTRACTOR_JAR} | shuf > ${COUNTER}${VAL_DATA_FILE} 2>> error_log.txt
  ((COUNTER = COUNTER+1))
done
echo "Finished extracting paths from validation set"
#${PYTHON} approaches/code2seq/JavaExtractor/extract.py --dir ${VAL_DIR} --max_path_length 8 --max_path_width 2 --num_threads ${NUM_THREADS} --jar ${EXTRACTOR_JAR} > ${VAL_DATA_FILE} 2>> error_log.txt


echo "Extracting paths from test set..."
COUNTER=0
for entry in ${TEST_DIR}/*.java
do
  echo "test file ${COUNTER}"
  ${PYTHON} approaches/code2seq/JavaExtractor/extract.py --file "$entry" --max_path_length 8 --max_path_width 2 --num_threads ${NUM_THREADS} --jar ${EXTRACTOR_JAR} | shuf > ${COUNTER}${TEST_DATA_FILE} 2>> error_log.txt
  ((COUNTER = COUNTER+1))
done
echo "Finished extracting paths from test set"
#${PYTHON} approaches/code2seq/JavaExtractor/extract.py --dir ${TEST_DIR} --max_path_length 8 --max_path_width 2 --num_threads ${NUM_THREADS} --jar ${EXTRACTOR_JAR} > ${TEST_DATA_FILE} 2>> error_log.txt


echo "Extracting paths from training set..."
COUNTER=0
for entry in ${TRAIN_DIR}/*.java
do
  echo "train file ${COUNTER}"
  ${PYTHON} approaches/code2seq/JavaExtractor/extract.py --file "$entry" --max_path_length 8 --max_path_width 2 --num_threads ${NUM_THREADS} --jar ${EXTRACTOR_JAR} | shuf > ${COUNTER}${TRAIN_DATA_FILE} 2>> error_log.txt
  ((COUNTER = COUNTER+1))
done
echo "Finished extracting paths from training set"
#${PYTHON} approaches/code2seq/JavaExtractor/extract.py --dir ${TRAIN_DIR} --max_path_length 8 --max_path_width 2 --num_threads ${NUM_THREADS} --jar ${EXTRACTOR_JAR} | shuf > ${TRAIN_DATA_FILE} 2>> error_log.txt
echo "Done with extracting"

#concatenating all files back together
#cat ./*${DATASET_NAME}.train.raw.txt >> ${TRAIN_DATA_FILE}
#cat ./*${DATASET_NAME}.test.raw.txt >> ${TEST_DATA_FILE}
#cat ./*${DATASET_NAME}.val.raw.txt >> ${VAL_DATA_FILE}


TARGET_HISTOGRAM_FILE=approaches/code2seq/data/${DATASET_NAME}/${DATASET_NAME}.histo.tgt.c2s
SOURCE_SUBTOKEN_HISTOGRAM=approaches/code2seq/data/${DATASET_NAME}/${DATASET_NAME}.histo.ori.c2s
NODE_HISTOGRAM_FILE=approaches/code2seq/data/${DATASET_NAME}/${DATASET_NAME}.histo.node.c2s

#echo "Creating histograms from the training data"
#cat ${TRAIN_DATA_FILE} | cut -d' ' -f1 | tr '|' '\n' | awk '{n[$0]++} END {for (i in n) print i,n[i]}' > ${TARGET_HISTOGRAM_FILE}
#cat ${TRAIN_DATA_FILE} | cut -d' ' -f2- | tr ' ' '\n' | cut -d',' -f1,3 | tr ',|' '\n' | awk '{n[$0]++} END {for (i in n) print i,n[i]}' > ${SOURCE_SUBTOKEN_HISTOGRAM}
#cat ${TRAIN_DATA_FILE} | cut -d' ' -f2- | tr ' ' '\n' | cut -d',' -f2 | tr '|' '\n' | awk '{n[$0]++} END {for (i in n) print i,n[i]}' > ${NODE_HISTOGRAM_FILE}

#${PYTHON} approaches/code2seq/preprocess.py --train_data ${TRAIN_DATA_FILE} --test_data ${TEST_DATA_FILE} --val_data ${VAL_DATA_FILE} \
#  --max_contexts ${MAX_CONTEXTS} --max_data_contexts ${MAX_DATA_CONTEXTS} --subtoken_vocab_size ${SUBTOKEN_VOCAB_SIZE} \
#  --target_vocab_size ${TARGET_VOCAB_SIZE} --subtoken_histogram ${SOURCE_SUBTOKEN_HISTOGRAM} \
#  --node_histogram ${NODE_HISTOGRAM_FILE} --target_histogram ${TARGET_HISTOGRAM_FILE} --output_name approaches/code2seq/data/${DATASET_NAME}/${DATASET_NAME}
    
# If all went well, the raw data files can be deleted, because preprocess.py creates new files 
# with truncated and padded number of paths for each example.
#rm ./*${TRAIN_DATA_FILE} ./*${VAL_DATA_FILE} ./*${TEST_DATA_FILE} ${TARGET_HISTOGRAM_FILE} ${SOURCE_SUBTOKEN_HISTOGRAM} \
  #${NODE_HISTOGRAM_FILE}

