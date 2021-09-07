TRAIN_DIR=preprocessing/code2seq/preprocessed_data/split/training
TRAIN_DATA_FILE=funcom.train.raw.txt
EXTRACTOR_JAR=approaches/code2seq/JavaExtractor/JPredict/target/JavaExtractor-0.0.1-SNAPSHOT.jar
NUM_THREADS=64
PYTHON=python

echo "Extracting paths from specific training files "
for number in 5
#8 14 15 47 62 72 73 90 98 99 100 120 128 132
do
  echo "train file ${number}"
  ${PYTHON} approaches/code2seq/JavaExtractor/extract.py --file "${TRAIN_DIR}/train${number}.java" --max_path_length 8 --max_path_width 2 --num_threads ${NUM_THREADS} --jar ${EXTRACTOR_JAR} | shuf > ${number}${TRAIN_DATA_FILE} 2>> error_log.txt

done
echo "Finished extracting paths"