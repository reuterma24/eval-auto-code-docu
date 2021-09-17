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
#refs, preds = c2s.load()
#evaluate(preds, refs)


# AttnFC EVAL
print("--- EVALUATING ATTNFC ---")
refs_dict = reflist.get_all_references()
preds_dict = predFormatter.format_prediction("approaches/attnToFc/outdir/predictions/predict-attendgru-fc_E07_1631652083.txt")

print("selecting relevant references and sorting ...")
unique_keys = set(refs_dict.keys()).symmetric_difference(set(preds_dict.keys()))
for k in list(unique_keys):
    if k in preds_dict:
        del preds_dict[k]
    else:
        del refs_dict[k]

refs = list()
preds = list()
for key in sorted(refs_dict):
    refs.append(refs_dict[key])
    preds.append(preds_dict[key])

print("done")

evaluate(preds, refs)
