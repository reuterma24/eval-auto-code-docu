import os

### COMMENTS ###
f = open("comments.txt" , "r")
print("Comments loaded")

matches = list()

for i, line in enumerate(f):
    fid , comment = line.split(":")
    if "performs the conversion" == str(comment).strip():
        matches.append(int(fid))
    
f.close()

print("number of matches: " + str(len(matches)))

### FUNCTIONS ###
f = open("code.txt" , "r")
print("Functions loaded")

function_dict = dict()

for i, line in enumerate(f):
    fid , func = line.split(":", 1)
    function_dict[int(fid)] = str(func)

f.close()

for idx in matches:
    print(function_dict[idx])

print("done")

