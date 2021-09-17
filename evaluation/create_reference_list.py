def get_all_references():
    print("loading all references...")
    refs = dict()

    with open("comments.txt", 'r') as f:
        for _, line in enumerate(f):
            a, b = line.split(":")
            b = b.split()
            c = ''
            for word in b:
                if '<' not in word:
                    c += (' ' + word)
            refs[int(a)] = c

        print("done")

        return refs
