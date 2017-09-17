# Evaluation metrics

We use two types of evaluation metrics: classification-related
and distribution-related. Here, O means "not a breakdown", T "possible breakdown", and X "breakdown".

## Classification-related metrics

Classification-related metrics evaluate the accuracy related
to the classification of the breakdown labels. Here, the accuracy
is calculated by comparing the output of the detector
and the gold label determined by majority voting. We use a
threshold t to obtain the gold label.

* Accuracy

The number of correctly classified labels
divided by the total number of labels to be classified.

* Precision, Recall, F-measure (X)

The precision, recall,
and F-measure for the classification of the X labels.

* Precision, Recall, F-measure (T+X)

The precision,
recall, and F-measure for the classification of T + X
labels; that is, PB and B labels are treated as a single
label.

## Distribution-related metrics

Distribution-related metrics evaluate the similarity of the
distribution of the breakdown labels, which is calculated
by comparing the predicted distribution of the labels with
that of the gold labels.

* JS divergence (O, T, X)

Distance between the predicted
distribution of the three labels and that of the
gold labels calculated by Jensen-Shannon Divergence.

* JS divergence (O, T+X)

JS divergence when T and X are regarded as a single label.

* JS divergence (O+T, X)

JS divergence when O and T are regarded as a single label.

* Mean squared error (O, T, X)

Distance between
the predicted distribution of the three labels and that
of the gold labels calculated by mean squared error.

* Mean squared error (O, T+X)

Mean squared error
when T and X are regarded as a single label.

* Mean squared error (O+T, X)

Mean squared error
when O and T are regarded as a single label.

## How to evaluate your dialogue breakdown detector

By using the script we are distributing, you can evaluate your dialogue breakdown detector
by using the above evaluation metrics.
Refer to [this page](getting_started) or the readme file
in the evaluation package (eval_script.zip).
When you execute the evaluation script, evaluation results in several evaluation
metrics are obtained. 

