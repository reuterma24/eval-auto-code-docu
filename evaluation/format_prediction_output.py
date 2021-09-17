def format_prediction(prediction_file):
    print("formatting predictions...")
    preds = dict()
    with open(prediction_file, 'r') as f:
        for _, line in enumerate(f):
            a, b = line.split("\t")
            b = b.split()
            c = ''
            for word in b:
                if '<' not in word:
                    c += (' ' + word)
            if not c:
                c = "<empty>"
            preds[int(a)] = c

        f.close()

        print("done")

        return preds
