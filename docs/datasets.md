# Dataset

Below shows the descriptions of the development/evaluation data for English and Japanese. This page also describes
the file format for the dialogues in the dataset.

## Development Data

### Development Data for English

* [iris_100.zip](https://github.com/dbd-challenge/dbdc3/raw/master/data/dev/iris_100.zip) (100 dialogues)
The original dialogue data is from the [WOCHAT dataset](http://workshop.colips.org/wochat/data/index.html).
* [ticktock_100.zip](https://github.com/dbd-challenge/dbdc3/raw/master/data/dev/ticktock_100.zip) (100 dialogues)
The original dialogue data is from the [WOCHAT dataset](http://workshop.colips.org/wochat/data/index.html).
* [CIC_json_data.zip](https://github.com/dbd-challenge/dbdc3/raw/master/data/dev/CIC_json_data.zip) (115 dialogues)
The original dialogue data is from the human evaluation round of [The Conversational Intelligence Challenge](http://convai.io/) (CIC). Note that various chatbots (those participating in CIC)
are used in the dialogues.
* [YI_json_data.zip](https://github.com/dbd-challenge/dbdc3/raw/master/data/dev/YI_json_data.zip) (100 dialogues)
The dialogue data we collected by using Yura and Idris's chatbot (bot#1337), which is participating in CIC.

Each zip file contains 100-115 dialogue sessions as individual JSON files. All utterances are annotated by 30 annotators with dialogue breakdown labels.

For CIC dataset, context files are also provided. They served as the topics of the conversation during the dialogue.
The context comes from [SQuAD dataset](https://rajpurkar.github.io/SQuAD-explorer/).

### Development Data for Japanese

The following data can be used for development data for Japanese. These data are those used in the past DBDCs.

* [Chat dialogue corpus](https://sites.google.com/site/dialoguebreakdowndetection/chat-dialogue-corpus) (1,146 dialogues)
The dialogues are collected by using NTT Docomo's chat API (DCM). 100 dialogues are annotated by 24 annotators and the rest of the dialogues are
annotated by 2-3 annotators.
* [Developement data for DBDC1](https://sites.google.com/site/dialoguebreakdowndetection/dev_data/dev.zip?attredirects=0) (20 dialogues)
The dialogues are collected by using DCM. 
* [Evaluation data for DBDC1](https://sites.google.com/site/dialoguebreakdowndetection/dev_data/eval.zip?attredirects=0) (80 dialogues)
The dialogues are collected by using DCM. 
* [Developement data for DBDC2](https://sites.google.com/site/dialoguebreakdowndetection2/downloads/DBDC2_dev.zip?attredirects=0&d=1) (150 dialogues)
The dialogues are collected by using DCM, DIT (Denso IT Laboratories' system) and IRS (IR-status based system by Ritter et al.'s paper) systems. 
* [Evaluation data for DBDC2](https://sites.google.com/site/dialoguebreakdowndetection2/downloads/DBDC2_ref.zip?attredirects=0&d=1) (150 dialogues)
The same as the developement data for DBDC2.

## Evaluation Data

### Evaluation Data for English

At the time of the formal-run, we will distribute 50 dialogues each for IRIS, TickTock, CIC and YI. (200 dialogues in total)

### Evaluation Data for Japanese

At the time of the formal-run, we will distribute 50 dialogues each for DCM, DIT, and IRS. (150 dialogues in total)

## Format of the JSON file 

### File name

Each JSON file contains one dialogue session, which conforms with the naming convention: `<dialogue-id>.log.json`.
Each context file (CIC dataset only) has the name `<dialogue-id>.log.context`, which is a plain text file.

### File format

Each file is in JSON format with UTF-8 encoding. A user and the system converse interchangeably and both utter ten times.

Following are the top-level fields:
* dialogue-id: dialogue ID in the original dataset
* group-id: (not used)
* speaker-id: speaker (user) ID in the original dataset
* turns: array of utterances from the user and the system with breakdown annotations

Each element of the 'turns' field contains the following fields:
* utterance: the contents of an utterance 
* turn-index: index from 0 to 19 for one of the 20 utterance sequence
* time: (not used) 
* speaker: the speaker of this utterance. "U" indicates user and "S" indicates the system.
* annotations: breakdown annotations by 30 annotators (only for system utterances)

Each element of the 'annotations' field contains the following fields:
* annotator-id: crowd-worker ID in CrowdFlower
* breakdown: one of the following three labels:
~~~~~~~~
    O: Not a breakdown
    T: Possible breakdown
    X: Breakdown
~~~~~~~~
* ungrammatical-sentence: (not used)
* comment: (not used) 

NOTE: Only the `turn-index` field is numerical. All the others are textual.

### Speakers in the dialogues

For IRIS and TickTock datasets, refer to [WOCHAT website](http://workshop.colips.org/wochat/data/index.html).
For CIC, refer also to [CIC website](http://convai.io/). For YI, the speakers were from AMT.

### Annotators

For IRIS and TickTock datasets, we used crowd workers from CrowdFlower for annotation.
They are 'level-2' annotators from Australia, Canada, New Zealand, United Kingdom, and United States. 
We asked the non-native English speaking workers to refrain from joining this annotation task but this is not guaranteed.
For CIC and YI datasets, we used crowd workers from AMT.

### Miscellaneous Notes

Due to the subjective nature of this task, we did not provide any check question to be used in CrowdFlower.
Actual IRIS dialogue sessions start with a fixed system prompt. We cut out the initial prompt.

### Acknowledgements

The development of these datasets were supported by the track sponsors and [Japanese Society of Artificial Intelligence](https://www.ai-gakkai.or.jp/en/).
We thank these supporters and the providers of the original dialogue data.
