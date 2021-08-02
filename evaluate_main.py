from nltk.translate.bleu_score import corpus_bleu
import evaluation.code2seq_eval as c2s
import evaluation.evaluator as evaluator



# Setup
evaluator.main()

# CODE2SEQ EVAL
ref, res = c2s.load()

# ROUGE
corpus_rouge = evaluator.rouge(res, ref)
print("ROUGE SCORE: " + str(corpus_rouge))

# METOR
corpus_meteor = evaluator.meteor(res, ref)
print("METEOR SCORE: " + str(corpus_meteor))

# BLEU
b1, b2, b3, b4 = evaluator.bleu(res, ref)
print("BLEU SCORE:\n")
print("Cumulative BLEU 1-gram: " + str(b1))
print("Cumulative BLEU 2-gram: " + str(b2))
print("Cumulative BLEU 3-gram: " + str(b3))
print("Cumulative BLEU 4-gram: " + str(b4))

#next approach