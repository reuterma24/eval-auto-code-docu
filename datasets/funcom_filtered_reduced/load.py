import json
import os
import os.path


def load():
    """
    Loads json data from files and returns a dictionary for each data type
    where the key is function_id and the value is the source code or comment
    """
    root = os.path.dirname(os.path.abspath(__file__)) + '/'

    src_path = 'functions.json'
    com_path = 'comments.json'
    invalid_path = 'invalid_ids.txt'

    with open(root + src_path, 'r') as fp:
        src = json.load(fp, parse_int=True)

    with open(root + com_path, 'r') as fp:
        com = json.load(fp)

    if os.path.isfile(root + invalid_path):
        with open(root + invalid_path, 'r') as inval:
            text = inval.read()
            ids = text.split(',')

        return (src, com, ids)

    return (src, com)


def load_comment():
    """
    Loads json data from files and returns a dictionary
    where the key is function_id and the value is the comment
    """
    com_path = 'comments.json'

    with open(com_path, 'r') as fp:
        com = json.load(fp)

    return com


def load_function():
    """
    Loads json data from files and returns a dictionary
    where the key is function_id and the value is the function
    """
    src_path = 'functions.json'

    with open(src_path, 'r') as fp:
        src = json.load(fp)

    return src


def load_invalid_ids():
    src_path = 'invalid_ids.txt'

    with open(src_path, 'r') as inval:
        src = inval.read()
        ids = src.split(',')

    return ids


if __name__ == "__main__":
    dats, come = load()

    for k, v in dats.items():
        print(k, v)
