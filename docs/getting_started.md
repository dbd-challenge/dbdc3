# Getting started

## About this challenge

The participant of this challenge will be given dialogue data between humans and machines, and develope algorithms that can detect 
dialogue breakdowns (points in dialogue where users cannot continue the conversation smooothly). On this website, dialogue data with
dialogue breakdown annotations (in JSON format) are distributed. You can use these data for develop your own algorithms.

For the challenge participants, at the time of the formal-run, dialogue data without dialogue breakdown annotations are provided.
The participants will submit their dialogue breakdown detection results using their own algorithms.

## About dialogue data and dialogue breakdown

In each of the dialogue data, each utterance is annotated with dialogue breakdown detection labels. There are three types of labels:
O (not a breakdown), T (possible breakdown), and X (breakdown). In the developement data for English, each utterance is annotated by 30 annotators.
For example, an utterance can be labled with O by 3 annotators, T by 6 annotators, X by 21 annotators. The task of dialogue breakdown detection
is to decide a single dialogue breakdown label (e.g., X) for an utterance as well as the distribution
(e.g., [0.1, 0.2, 0.7]) of the labels for the utterance.

## Using a baseline

This section shows how to run a baseline detector program that the organizers prepared.

### Execution environment

To run the baseline program and evaluation script, the following must be installed.

~~~~
JavaSE-1.7+
Python 2.7.x
Execute the program on the command prompt (Windows) / Terminal (Mac, Linux). 
You can check whether each program is correctly installed by executing the following command.  
`$java -version`  
`$python -V`
~~~~

### How to run the program

The baseline program uses words included in each utterance as features (Bag-of-Words) and detects dialogue breakdowns by using Conditional Random Fields (CRFs).
This program outputs three kinds of labels, O (not a breakdown), T (possible breakdown,) and X (breakdown) with probability distributions; however,
since this is a simple baseline program, the output distribution is deterministic, i.e., 1.0 for the output label, 0.0 for the others.

By following the steps below, you can try training a simple dialogue breakdown detector and evaluate its performance. This example uses IRIS dataset.

1. Download the baseline program and the evaluation script.
2. Place the eval.py in eval_script.zip in the same directory as the baseline program
3. Split 100 json files of IRIS_100 into two sets of 50 files each and place them under `baseline/train/` and `baseline/test/`.
   Here, we sorted the json files alphabetically and put the initial 50 files (1407219916log.json to 1408219169log.json) under train and
   the remaining 50 files (iris_00014.log.json to iris_00105.log.json) to test.
4. Now the directories should look like below．
~~~~
baseline/
    | DBDBaseline.jar 
    | eval.py
   ├ train/
    |    | iris_00014.log.json
    |    | iris_00016.log.json
    |    | ・・・
    |   └iris_00105.log.json
   └ test/
         | iris_00106.log.json
         | iris_00107.log.json
         | ・・・
        └iris_00161.log.json
~~~~
5. Let's train a dialogue breakdown detector by using the data in baseline/train/. 
`$java -jar DBDBaseline.jar -l ./train/ -p ./test/ -o ./out/ -t 0.5`

6. When training is finished, the model detects dialogue breakdowns on the JSON files for the test data
specified by -p and outputs the results to the directory specified by -o in JSON format.

### How to run the evaluation script

This script evaluates the output of a dialogue breakdown detector. The script can be executed by the following steps.

1. To evaluate the output by the baseline program above, run the following command.
`$python eval.py -p ./test/ -o ./out/ -t 0.5`

2. The performance of the dialogue breakdown detector is desplayed. Here, the number of Label Num shows the
number of labels decided by the majority voting. This number does not match the actual number of dialogue breakdown
labels by human annotators.
~~~~
######### Data Stats #########
File Num : 		50
System Utterance Num : 	500
O Label Num : 		315
T Label Num : 		17
X Label Num : 		168

######### Results #########
Accuracy : 		0.440000 (220/500)
Precision (X) : 	0.329004 (76/231)
Recall    (X) : 	0.452381 (76/168)
F-measure (X) : 	0.380952

precision (T+X) : 	0.790441 (215/272)
Recall    (T+X) : 	0.555556 (215/387)
F-measure (T+X) : 	0.652504

JS divergence (O,T,X) : 	0.468422
JS divergence (O,T+X) : 	0.339554
JS divergence (O+T,X) : 	0.333161

Mean squared error (O,T,X) : 	0.241287
Mean squared error (O,T+X) : 	0.306044
Mean squared error (O+T,X) : 	0.293160
###########################
~~~~
