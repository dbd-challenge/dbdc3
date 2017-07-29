Baseline program for the task of dialogue breakdown detection
====

The baseline program uses words included in each utterance as features (Bag-of-Words) and detects dialogue breakdowns by using Conditional Random Fields (CRFs).
This program outputs three kinds of labels, O (not a breakdown), T (possible breakdown,) and X (breakdown) with probability distributions.
However, each distribution is deterministic, i.e., 1.0 for the output label, 0.0 for the others.


#Requirements

JavaSE-1.7+


#Launching baseline program

$java -jar DBDBaseline.jar -l (Directory containing training data) [-p (Directory containing test data)] -o (output directory) -t (threshold of concordance rate)


The model is trained using JSON files in the directory containing the training data specified with -t option. When training is finished, the model detects dialogue breakdowns on the JSON files in the directory containing the test data specified by -p and outputs the results to the directory specified by -o in JSON format.

If -p option is not specified, the model uses training data as test data.

-t is the option for determining the correct label for each instance from multiple annotated labels in training the model. The correct label for each instance is basically the majority of the labels given to the instance. Breakdown labels ("T" and "X") are, however, used only if the concordance rate among annotators is greater than or equal to the specified threshold, otherwise not-a-breakdown label ("O") is used. For example, if two annotators give "O", three give "T", and five give "X", then the concordance rate of label "X" is 5 / (2 + 3 + 5) = 0.5 which is the largest of the three. If the threshold specified by "-t" is 0.5 or less, the label of the utterance is determined to be "X", but if it is larger than 0.5 it to be "O".

 
#Acknowledgments

In this program, the MALLET is used as an implementation of Conditional Random Fields.
 McCallum, Andrew Kachites.  "MALLET: A Machine Learning for Language Toolkit." http://mallet.cs.umass.edu. 2002.
 
And lucene-gosen is used as a Japanese morphological analyzer.
 https://code.google.com/p/lucene-gosen/