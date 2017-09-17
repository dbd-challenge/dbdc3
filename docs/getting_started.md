---
title: Getting started
---

# Getting started

## About this challenge

The participant of this challenge will be given dialogue data between humans and machines, and develope algorithms that can detect 
dialogue breakdowns (points in dialogue where users cannot continue the conversation smooothly). You can use [developement data](datasets) (dialogue data
with dialogue breakdown annotations) to develop your own dialogue breakdown detection algorithms.

For the challenge participants, at the time of the formal-run, dialogue data without dialogue breakdown annotations are provided.
The participants will submit their dialogue breakdown detection results using their own algorithms.

## About dialogue data and dialogue breakdown detection

The dialogues in the development data are annotated with dialogue breakdown detection labels. There are three types of labels:
O (not a breakdown), T (possible breakdown), and X (breakdown). For most of the dialogues, each utterance of the dialogues are annotated by 30 annotators.
The task of dialogue breakdown detection is to decide a single dialogue breakdown label (e.g., X) for an utterance as well as the distribution of O T, and X
(e.g., [0.1, 0.2, 0.7]) for the utterance.

## Using a baseline

This section shows how to run a baseline detector that the organizers prepared.

### Execution environment

To run the baseline program and the evaluation script, the following must be installed.

~~~~
JavaSE-1.7+
Python 2.7.x
Execute the program on the command prompt (Windows) / Terminal (Mac, Linux). 
You can check whether each program is correctly installed by executing the following command.  
`$java -version`  
`$python -V`
~~~~

### How to run the baseline program

The baseline program uses words included in each utterance as features (Bag-of-Words) and detects dialogue breakdowns by using Conditional Random Fields (CRFs).
This program outputs three kinds of labels, O (not a breakdown), T (possible breakdown,) and X (breakdown) with probability distributions; however,
since this is a simple baseline, the output distribution is deterministic, i.e., 1.0 for the output label, 0.0 for the others.

By following the steps below, you can train a simple dialogue breakdown detector and evaluate its performance. This example uses IRIS dataset in
the [development data](datasets).

1. Download the [baseline program](https://dbd-challenge.github.io/dbdc3/) and the [evaluation script](https://dbd-challenge.github.io/dbdc3/).
2. Place the `eval.py` in `eval_script.zip` in the same directory as the baseline program.
3. Split 100 json files of IRIS_100 into two sets of 50 files each and place them under `baseline/train/` and `baseline/test/`.
   Here, we sorted the json files alphabetically and put the initial 50 files (from 1407219916log.json to 1408219169log.json) under `train` and
   the remaining 50 files (from iris_00014.log.json to iris_00105.log.json) to `test`.
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
5. Let's train a dialogue breakdown detector by using the data in baseline/train/. After training,
the created model detects dialogue breakdowns on the JSON files for the test data
specified by -p and outputs the results to the directory specified by -o in JSON format. This can be
done by the following command:

`$java -jar DBDBaseline.jar -l ./train/ -p ./test/ -o ./out/ -t 0.5`

### How to run the evaluation script

This script evaluates the output of a dialogue breakdown detector. The script can be executed by the following steps.

1. To evaluate the output by the baseline program above, run the following command.

`$python eval.py -p ./test/ -o ./out/ -t 0.5`

2. The performance of the dialogue breakdown detector is displayed. Here, the number of Label Num shows the
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

The meaning of the evaluation metrics can be found in the [evaluation metrics section](https://dbd-challenge.github.io/dbdc3/evaluation_metrics).

### Create your own dialogue breakdown detector

After this getteing-started section, I hope you can get the idea of what to do. We, the organizers,
hope that many good dialogue breakdown detection algorithms be submitted at the formal-run.

See [this section](https://dbd-challenge.github.io/dbdc3/how_to_submit_runs) to how to submit runs at the formal-run.




