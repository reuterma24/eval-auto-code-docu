TRAIN_DIR=preprocessing/code2seq/preprocessed_data/split/training

echo "Extracting paths from specific training files "
for number in 5 8 14 15 47 62 72 73 90 98 99 100 120 128 132
do
  echo "train file ${number}"
  ${PYTHON} approaches/code2seq/JavaExtractor/extract.py --file "${TRAIN_DIR}/train${number}.java" --max_path_length 8 --max_path_width 2 --num_threads ${NUM_THREADS} --jar ${EXTRACTOR_JAR} | shuf > ${COUNTER}${TRAIN_DATA_FILE} 2>> error_log.txt
  ((COUNTER = COUNTER+1))
done
echo "Finished extracting paths"