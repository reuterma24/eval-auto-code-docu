import os.path
import re
import shutil

import javalang
import regex
import sklearn.model_selection as ms

#import datasets.funcom_filtered_reduced.load as funcom
#SWITCH BETWEEN THOSE FOR TESTING AND REAL RUN
import datasets.funcom_filtered.load as funcom

global invalid_fids
global comment_dict
global code_dict

umlaute_dict = {
    b'\xc3\xa4': b'ae',  # U+00E4	   \xc3\xa4
    b'\xc3\xb6': b'oe',  # U+00F6	   \xc3\xb6
    b'\xc3\xbc': b'ue',  # U+00FC	   \xc3\xbc
    b'\xc3\x84': b'Ae',  # U+00C4	   \xc3\x84
    b'\xc3\x96': b'Oe',  # U+00D6	   \xc3\x96
    b'\xc3\x9c': b'Ue',  # U+00DC	   \xc3\x9c
    b'\xc3\x9f': b'ss',  # U+00DF	   \xc3\x9f
}


def __replace_umlauts(text: str):
    result = text.encode("utf-8")
    for k in umlaute_dict.keys():
        result = result.replace(k, umlaute_dict[k])
    return result.decode()

def __print_invalid_ids():
    with open("invalid_fids.txt", "w", encoding="utf-8") as errs:
        for i in invalid_fids:
            errs.write(i + ",")

def __print_comments():
    with open("comments.txt", "w", encoding="utf-8") as com:
        for k, v in comment_dict.items():
            com.write(str(k) + ": " + str(v))

def __print_functions():
    with open("code.txt", "w", encoding="utf-8") as func:
        for k, v in code_dict.items():
            func.write(str(k) + ": " + str(v))

def preprocess():
    global comment_dict
    comment_dict = dict()

    global code_dict
    code_dict = dict()

    global invalid_fids
    invalid_fids = list()

    data = funcom.load()
    code_dict = data[0]
    comment_dict = data[1]

    pattern = re.compile(r'([^a-zA-Z0-9 ])|([a-z0-9_][A-Z])')

    #process pairs
    for k in list(code_dict.keys()):
        code = __replace_umlauts(code_dict[k])
        comment = __replace_umlauts(comment_dict[k])
        if '.' in comment:
            comment = comment.split('.')[0]
        comment = comment + " */\n"
        comment = pattern.sub(' ', comment)
        #comment = comment.translate({ord(c): " " for c in "\"!@#$%^&()[]{};:,<>?\|`~-=_+"})
        comment = regex.sub(' +', ' ', comment.replace('\n', "").replace('\t', ""))
        #comment = regex.sub(' +\* +', ' ', comment) #replaces * inside java doc comments
        comment = comment.lower()


        lineCommentPattern = "//(.*?)\r?\n"
        code = regex.sub(lineCommentPattern, "\n", code)  # remove inline comments
        docCommentPattern = "/\*\**((?:[^*]+|\*[^/])*)\*/"
        code = regex.sub(docCommentPattern, "", code)
        code = str(code).strip().replace("\n", "").replace('\t', " ")
        code = regex.sub(' +', ' ', code)  # multiple whitespaces with one

        commentCount = comment.split()
        if len(commentCount) > 13 or len(commentCount) < 3:
            invalid_fids.append(str(k))
            del comment_dict[k]
            del code_dict[k]
            continue

        codeCount = code.split()
        if len(codeCount) > 100:
            invalid_fids.append(str(k))
            del comment_dict[k]
            del code_dict[k]
            continue

        pair = comment + '\n' + code
        toParse = "class Parse {" + pair + '}'

        try:
            pair.encode('UTF-8', 'strict')
            javalang.parse.parse(toParse)
        except Exception:
            invalid_fids.append(str(k))
            del comment_dict[k]
            del code_dict[k]
            continue

        code_dict[k] = code
        comment_dict[k] = comment

    __print_invalid_ids()
    __print_comments()
    __print_functions()
