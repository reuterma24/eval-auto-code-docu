from nltk.translate.bleu_score import corpus_bleu
import evaluation.code2seq_eval as c2s
import evaluation.evaluator as evaluator
import evaluation.create_reference_list as reflist
import evaluation.format_prediction_output as predFormatter


def evaluate(pred, ref):
    # ROUGE
    corpus_rouge = evaluator.rouge(pred, ref)
    print("ROUGE SCORE:\n" + str(corpus_rouge))

    # METOR
    corpus_meteor = evaluator.meteor(pred, ref)
    print("METEOR SCORE:\n" + str(corpus_meteor))

    # BLEU
    b1, b2, b3, b4, corpus = evaluator.bleu(pred, ref)
    print("BLEU SCOREs:")
    print("BLEU-1: " + str(b1))
    print("BLEU-2: " + str(b2))
    print("BLEU-3: " + str(b3))
    print("BLEU-4: " + str(b4))
    print("Corpus BLEU: " + str(corpus))

# Setup
evaluator.main()
refs_dict = reflist.get_all_references()

# CODE2SEQ EVAL
print("--- EVALUATING CODE2SEQ ---")
refs, preds = c2s.load()
print(len(refs))
print(len(preds))
evaluate(preds, refs)


# AttnFC EVAL
print("--- EVALUATING ATTNFC ---")
preds_dict = predFormatter.format_prediction("approaches/attnToFc/outdir/predictions/predict-attendgru-fc_E07_1631652083.txt")

print("selecting relevant references and sorting for next approaches...")
unique_keys = set(refs_dict.keys()).symmetric_difference(set(preds_dict.keys()))
for k in list(unique_keys):
    if k in preds_dict:
        del preds_dict[k]
    else:
        del refs_dict[k]

refs = list()
preds = list()
for key in sorted(refs_dict.keys()):
    refs.append(refs_dict[key])
    preds.append(preds_dict[key])

print("refs :" + str((len(refs))))
print("preds :" + str((len(preds))))
print("done")

#evaluate(preds, refs)

# GNN EVAL
print("--- EVALUATING GNN ---")
#preds_dict = predFormatter.format_prediction("approaches/gnn/modelout/predictions/predict-codegnnbilstm.txt")
for k in list(unique_keys):
    if k in preds_dict:
        del preds_dict[k]

preds.clear()
for key in sorted(refs_dict.keys()):
    preds.append(preds_dict[key])

#evaluate(preds, refs)

# NEURALLSPS EVAL
print("--- EVALUATING NEURALLSPS ---")
preds_dict = predFormatter.format_prediction("approaches/funcom_approach/outdir/predictions/predict-attendgru_E07_1631539477.txt")
for k in list(unique_keys):
    if k in preds_dict:
        del preds_dict[k]

preds.clear()
for key in sorted(refs_dict.keys()):
    preds.append(preds_dict[key])

#evaluate(preds, refs)

