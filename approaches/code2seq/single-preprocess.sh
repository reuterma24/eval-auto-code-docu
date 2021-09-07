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
TRAIN_DIR=preprocessing/code2seq/preprocessed_data/split/training
EXTRACTOR_JAR=approaches/code2seq/JavaExtractor/JPredict/target/JavaExtractor-0.0.1-SNAPSHOT.jar


echo "Extracting paths from specific training files "
for number in 5 8 14 15 47 62 72 73 90 98 99 100 120 128 132
do
  echo "train file ${number}"
  ${PYTHON} approaches/code2seq/JavaExtractor/extract.py --file "${TRAIN_DIR}/train${number}.java" --max_path_length 8 --max_path_width 2 --num_threads ${NUM_THREADS} --jar ${EXTRACTOR_JAR} | shuf > ${number}${TRAIN_DATA_FILE} 2>> error_log.txt

done
echo "Finished extracting single paths"

#concatenating all files back together
cat ./*${DATASET_NAME}.train.raw.txt >> ${TRAIN_DATA_FILE}
cat ./*${DATASET_NAME}.test.raw.txt >> ${TEST_DATA_FILE}
cat ./*${DATASET_NAME}.val.raw.txt >> ${VAL_DATA_FILE}


TARGET_HISTOGRAM_FILE=approaches/code2seq/data/${DATASET_NAME}/${DATASET_NAME}.histo.tgt.c2s
SOURCE_SUBTOKEN_HISTOGRAM=approaches/code2seq/data/${DATASET_NAME}/${DATASET_NAME}.histo.ori.c2s
NODE_HISTOGRAM_FILE=approaches/code2seq/data/${DATASET_NAME}/${DATASET_NAME}.histo.node.c2s

echo "Creating histograms from the training data"
cat ${TRAIN_DATA_FILE} | cut -d' ' -f1 | tr '|' '\n' | awk '{n[$0]++} END {for (i in n) print i,n[i]}' > ${TARGET_HISTOGRAM_FILE}
cat ${TRAIN_DATA_FILE} | cut -d' ' -f2- | tr ' ' '\n' | cut -d',' -f1,3 | tr ',|' '\n' | awk '{n[$0]++} END {for (i in n) print i,n[i]}' > ${SOURCE_SUBTOKEN_HISTOGRAM}
cat ${TRAIN_DATA_FILE} | cut -d' ' -f2- | tr ' ' '\n' | cut -d',' -f2 | tr '|' '\n' | awk '{n[$0]++} END {for (i in n) print i,n[i]}' > ${NODE_HISTOGRAM_FILE}

${PYTHON} approaches/code2seq/preprocess.py --train_data ${TRAIN_DATA_FILE} --test_data ${TEST_DATA_FILE} --val_data ${VAL_DATA_FILE} \
  --max_contexts ${MAX_CONTEXTS} --max_data_contexts ${MAX_DATA_CONTEXTS} --subtoken_vocab_size ${SUBTOKEN_VOCAB_SIZE} \
  --target_vocab_size ${TARGET_VOCAB_SIZE} --subtoken_histogram ${SOURCE_SUBTOKEN_HISTOGRAM} \
  --node_histogram ${NODE_HISTOGRAM_FILE} --target_histogram ${TARGET_HISTOGRAM_FILE} --output_name approaches/code2seq/data/${DATASET_NAME}/${DATASET_NAME}

# If all went well, the raw data files can be deleted, because preprocess.py creates new files
# with truncated and padded number of paths for each example.
#rm ./*${TRAIN_DATA_FILE} ./*${VAL_DATA_FILE} ./*${TEST_DATA_FILE} ${TARGET_HISTOGRAM_FILE} ${SOURCE_SUBTOKEN_HISTOGRAM} \
  #${NODE_HISTOGRAM_FILE}


