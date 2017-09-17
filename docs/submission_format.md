# Output format for dialogue breakdown detection results

This page shows the output format for dialogue breakdown detection results.
The evaluation script can only accept this format. Refer also to
[this section](https://dbd-challenge.github.io/dbdc3/how_to_submit_runs) for the
information about how to submit your runs at the formal-run.

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
* breakdown ： This can be one of O or T or X, corresponding to not a breakdown, possible breakdown, and breakdown, corresponding to the hard-decision made by a dialogue breakdown detector.
* prob-O ： Probability that the dialogue breakdown detection result is O (not a breakdown).
* prob-T ： Probability that the dialogue breakdown detection result is T (possible breakdown).
* prob-X ： Probability that the dialogue breakdown detection result is X (breakdown).
