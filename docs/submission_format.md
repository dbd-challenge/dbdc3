# Submission format

Each participant must submit the dialogue breakdown detection results in JSON format.
The evaluation script accepts this format.

## Naming convention for input/output files

The input dialogue file for dialogue breakdown detection is `<dialogue-id>.log.json`.
The output result file will be `<dialogue-id>.labels.json`.
For example, if an input dialogue file is `1408278293.log.json`, then the output result
file will be `1408278293.labels.json`.

## Format of the output result file

The output result file should be in a JSON format．An example is given below:

~~~~~~~~
{
  "dialogue-id" : "1408278293",
  "turns" : [ {
    "turn-index" : 0,
    "labels" : [ {
      "breakdown" : "O",
      "prob-O" : 0.8,
      "prob-T" : 0.2,
      "prob-X" : 0.0
    } ]
  }, {
    "turn-index" : 2,
    "labels" : [ {
      "breakdown" : "O",
      "prob-O" : 0.75,
      "prob-T" : 0.15,
      "prob-X" : 0.10
    } ]
  }, {
    "turn-index" : 4,
    "labels" : [ {
      "breakdown" : "X",
      "prob-O" : 0.0,
      "prob-T" : 0.0,
      "prob-X" : 1.0
    } ]
  }, {
         ・・・
  }, {
    "turn-index" : 18,
    "labels" : [ {
      "breakdown" : "T",
      "prob-O" : 0.2,
      "prob-T" : 0.6,
      "prob-X" : 0.2
    } ]
  }, {
    "turn-index" : 20,
    "labels" : [ {
      "breakdown" : "O",
      "prob-O" : 1.0,
      "prob-T" : 0.0,
      "prob-X" : 0.0
    } ]
  } ]
}

~~~~~~~~

## Description of each field

* dialogue-id ： This ID should match that of the input dialogue file.
* turns ： This field contains all the dialogue breakdown detection results for all turns.
* turn-index ： This field denotes the index of a turn and should match that of the input dialogue file. 
* labels ： This field contains the dialogue breakdown detection results for a turn.
* breakdown ： This can be one of O or T or X, corresponding to not a breakdown, possible breakdown, and breakdown.
* prob-O ： Probability that the dialogue breakdown detection result is O (not a breakdown).
* prob-T ： Probability that the dialogue breakdown detection result is T (possible breakdown).
* prob-X ： Probability that the dialogue breakdown detection result is X (breakdown).
