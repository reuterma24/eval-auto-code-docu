def format_prediction(prediction_file):
    f = open(prediction_file, 'r')
    preds = dict()
    for _, line in enumerate(f):
        a, b = line.split("\t")
        b = b.split()
        c = ''
        for word in b:
            if '<' not in word:
                c.join(word)
        preds[int(a)] = c
    f.close()

    return preds
