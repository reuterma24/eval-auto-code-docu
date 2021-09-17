from nltk.translate.bleu_score import corpus_bleu
import evaluation.code2seq_eval as c2s
import evaluation.evaluator as evaluator
import evaluation.create_reference_list as reflist
import evaluation.format_prediction_output as predFormatter


def evaluate(pred, ref):
    # ROUGE
    corpus_rouge = evaluator.rouge(pred, ref)
    print("ROUGE SCORE: " + str(corpus_rouge))

    # METOR
    corpus_meteor = evaluator.meteor(pred, ref)
    print("METEOR SCORE: " + str(corpus_meteor))

    # BLEU
    b1, b2, b3, b4, corpus = evaluator.bleu(pred, ref)
    print("BLEU SCOREs:\n")
    print("BLEU-1: " + str(b1))
    print("BLEU-2: " + str(b2))
    print("BLEU-3: " + str(b3))
    print("BLEU-4: " + str(b4))
    print("Corpus BLEU: " + str(b4))

# Setup
evaluator.main()

# CODE2SEQ EVAL
print("--- EVALUATING CODE2SEQ ---")
#ref, pred = c2s.load()
#evaluate(pred, ref)


# AttnFC EVAL
print("--- EVALUATING ATTNFC ---")
ref = reflist.get_all_references()
pred = predFormatter.format_prediction("approaches/attnToFc/outdir/predictions/predict-attendgru-fc_E07_1631652083.txt")

print("selecting relevant references ...")
keys = ref.keys()
for k in keys:
    if k not in pred:
        del ref[k]
print("done")

evaluate(pred, ref)
