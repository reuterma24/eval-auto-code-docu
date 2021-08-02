import nltk
import numpy as np
from rouge import Rouge
from nltk.translate.bleu_score import corpus_bleu
from nltk.translate.meteor_score import meteor_score
from nltk.corpus import wordnet as wn


def rouge(result, reference):
    r = Rouge()
    return r.get_scores(result, reference, avg=True)


def corpus_meteor(references, predictions):
    meteor_score_sentences_list = list()
    [meteor_score_sentences_list.append(
        meteor_score(reference, prediction, wordnet=wn, stemmer=nltk.stem.porter.PorterStemmer())
        )for reference, prediction in zip(references, predictions)]

    meteor_score_res = np.mean(meteor_score_sentences_list)
    return meteor_score_res


def meteor(result, reference):
    return corpus_meteor(reference, result)


def bleu(result, reference):
    list_of_references = []
    for r in reference:
        a = [list(r.split())]
        list_of_references.append(a)

    list_of_results = []
    for r in result:
        list_of_results.append(list(r.split()))

    bleu1 = round(corpus_bleu(list_of_references, list_of_results, weights=(1.0, 0, 0, 0)), 8)
    bleu2 = round(corpus_bleu(list_of_references, list_of_results, weights=(0, 1.0, 0, 0)), 8)
    bleu3 = round(corpus_bleu(list_of_references, list_of_results, weights=(0, 0, 1.0, 0)), 8)
    bleu4 = round(corpus_bleu(list_of_references, list_of_results, weights=(0, 0, 0, 1.0)), 8)

    return bleu1, bleu2, bleu3, bleu4


def main():
    print("Downloading Wordnet for Meteor Stemming.")
    nltk.download('wordnet')


if __name__ == '__main__':
    main()

