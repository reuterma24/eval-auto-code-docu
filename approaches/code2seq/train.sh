###########################################################
# Change the following values to train a new model.
# type: the name of the new model, only affects the saved file name.
# dataset: the name of the dataset, as was preprocessed using preprocess.sh
# test_data: by default, points to the validation set, since this is the set that
#   will be evaluated after each training iteration. If you wish to test
#   on the final (held-out) test set, change 'val' to 'test'.
type=funcom-model
dataset_name=funcom
data_dir=data/funcom
data=${data_dir}/${dataset_name}
test_data=${data_dir}/${dataset_name}.val.c2s #change this in order to select the dataset for the training validation after each epoch
model_dir=models/${type}

BASEDIR=$(dirname "$0")

mkdir -p ${model_dir}
set -e
python -u ${BASEDIR}/code2seq.py --data ${BASEDIR}/${data} --test ${BASEDIR}/${test_data} --save_prefix ${BASEDIR}/${model_dir}/model
