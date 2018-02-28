# Dataset for DBDC3

Below shows the Dataset made for DBDC3. This dataset includes data for both development and evaluation. After DBDC3 workshop, we revised the annotation and added the new data into this dataset.

## Download
- [DBDC3.zip](https://dbd-challenge.github.io/dbdc3/data/DBDC3.zip)
- [DBDC3_context.zip](https://dbd-challenge.github.io/dbdc3/data/DBDC3_context.zip)

## Main data (DBDC3.zip)

This dataset was made by DBDC3 Task Organizers. The data can be used for both profit and nonprofit purposes under the MIT license.  The data contain both English and Japanese dialogues with dialogue breakdown annotations for each system utterance.

### English data
The following four datasets were used as development data in DBDC3.

- CIC_115: 115 dialogues collected in the human evaluation round of the [Conversational Intelligence Challenge (CIC)](http://convai.io/). We randomly selected a subset of dialogues and used the initial part of the dialogues for annotation.
- IRIS_100: 100 dialogues selected from the [WOCHAT dataset](http://workshop.colips.org/wochat/data/index.html).
- TKTK_100: 100 dialogues selected from the [WOCHAT dataset](http://workshop.colips.org/wochat/data/index.html).
- YI_100: 100 dialogues collected by using a chatbot developed at the Moscow Institute of Physics and Technology.

The following four datasets were used as evaluation data in DBDC3.

- CIC_50: 50 dialogues collected by CIC.
- IRIS_50: 50 dialogues provided by the developer of IRIS.
- TKTK_50: 50 dialogues we (the organizers) collected by using TickTock.
- YI_50: 50 dialogues we collected in the same way as YI_100.

Each dialogue in CIC_115 and CIC_50 was collected by showing a context represented by a short paragraph to a user before the dialogue.

### Japanese data

The following three datasets were used as evaluation data in DBDC3.

- DCM: 50 dialogues we collected by using NTT DOCOMO's chat API.
- DIT: 50 dialogues we collected by using DIT (Denso IT Laboratory's system).
- IRS: 50 dialogues we collected by using IRS (IR-status based system).

You can also refer to [here](https://dbd-challenge.github.io/dbdc3/datasets) for additional datasets in Japanese used in DBDC1 and DBDC2.

### Revision after DBDC3

There are two folders, "dbdc3" and "dbdc3_revised", in the data folder.  "dbdc3" is the one used for the DBDC3 workshop and "dbdc3_revised" is the one we revised after the workshop.

The four datasets, CIC_115, YI_100 in dbdc3/en/dev/ and CIC_50, YI_50 in dbdc3/en/test/ were re-annotated and are stored under dbdc3_revised folder.  In the original data, each annotator was allowed to annotate a part of a dialogue; however, in the revised data, each annotator was obliged to annotate all utterances of a dialogue in a row. This revision slightly increased the inter-annotator agreement.


## Context data (DBDC3_context.zip)

This dataset was made by DBDC3 Task Organizers based on [Stanford Question Answering Dataset (SQuAD)](https://rajpurkar.github.io/SQuAD-explorer/).  The data can be used for both profit and nonprofit purposes under the CC BY-SA 4.0 license. The data contain short paragraphs used as context for DBDC3.


# Sponsors
We gratefully acknowledge the generous support provided by the following:

<a href="https://www.d-itlab.co.jp/?lang=en"><img src="https://dbd-challenge.github.io/dbdc3/images/IT_LAB_logo_20111221.png" width="300"></a>

<a href="http://nextremer.com/en/"><img src="https://dbd-challenge.github.io/dbdc3/images/nextremer_color01.png" width="300"></a>

<a href="http://www.jp.honda-ri.com/english"><img src="https://dbd-challenge.github.io/dbdc3/images/HRI.jpg" width="300"></a>

<img src="https://dbd-challenge.github.io/dbdc3/images/docomo.JPG" width="300">

This track is endorsed by [Special Interest Group on Spoken Language Understanding and Dialogue Processing (SIG-SLUD)](https://jsai-slud.github.io/sig-slud/) of [the Japanese Society of Artificial Intelligence (JSAI)](https://www.ai-gakkai.or.jp/en/).
