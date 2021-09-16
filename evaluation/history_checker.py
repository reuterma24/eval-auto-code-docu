import os;
import pickle;

with open("attendgru_hist_1631539477.pkl", "rb") as hist:
	data = pickle.load(hist)
	print(data.keys())
	val_scores = data['val_accuracy']
	for i, v in enumerate(val_scores):
		number = str(v)
		print("epoch " + str(i+1)+ ': ' + number[0:6])

