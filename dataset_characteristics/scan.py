import os

### COMMENTS ###

wc = []
unique_words = set()
for i in range(14):
    wc.append(0)

f = open("comments.txt" , "r")
print("Comments loaded")
word_count = 0
for i, line in enumerate(f):
    _, comment = line.split(":")
    words = str(comment).split()
    word_count += len(words)
    wc[len(words)] += 1
    unique_words.update(set(words))
    

f.close()
length = i + 1

print("Comments: " + str(length))
print("Comments word count: " + str(word_count))
print("average comment length: " + str(word_count/length))
for i in range(len(wc)):
    print("{}-words comments: ".format(i) + str(wc[i]))
print("unique words :" + str(len(unique_words))) 
### FUNCTIONS ###

f = open("code.txt" , "r")
print("Functions loaded")
token_count = 0
for i, line in enumerate(f):
    _, func = line.split(":", 1)
    token_count += len(str(func).split())

f.close()
length = i + 1

print("Comments: " + str(length))
print("Function word count: " + str(token_count))
print("average function length: " + str(token_count/length))
