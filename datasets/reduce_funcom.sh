#SCRIPT TO REDUCE FUNCOMS SINCE

N=200

source_path=funcom_filtered
dest_path=funcom_filtered_tiny

#this keeps the , for the last stored entry
( head -n $((N+1)) ${source_path}/fid_pid.json ; echo '}' ) > ${dest_path}/fid_pid.json
echo "Extracted first ${N} lines from fid_pid.json."

( head -n $((N+1)) ${source_path}/comments.json ; echo '}' ) > ${dest_path}/comments.json
echo "Extracted first ${N} lines from comments.json."

( head -n $((N+1)) ${source_path}/functions.json ; echo '}' ) > ${dest_path}/functions.json
echo "Extracted first ${N} lines from functions.json."

echo "Successfully reduced funcom's size - results are stored at datasets/${dest_path}"