#SCRIPT TO REDUCE FUNCOMS SICE

N=$1

root=$(dirname $0)
source_path=${root}/funcom_filtered
dest_path=${root}/funcom_filtered_reduced

#reads first N entries (+ opening brace at start) and removes last comma with closing brace at the end
head -n $((N+1)) ${source_path}/fid_pid.json | sed '$s/,$/\n}/' > ${dest_path}/fid_pid.json
echo "Extracted first ${N} lines from fid_pid.json."

head -n $((N+1)) ${source_path}/comments.json | sed '$s/,$/\n}/' > ${dest_path}/comments.json
echo "Extracted first ${N} lines from comments.json."

head -n $((N+1)) ${source_path}/functions.json | sed '$s/,$/\n}/' > ${dest_path}/functions.json
echo "Extracted first ${N} lines from functions.json."

echo "Successfully reduced funcom's size - results are stored at ${dest_path}."