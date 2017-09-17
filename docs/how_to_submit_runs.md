
# How to submit runs

## Distribution of evaluation data

At the time of the formal-run, we will distribute evaluation data.
Please freeze your programs once you have unzipped the evaluation data.
You can only fix trivial mistakes after you see the evaluation data.

See [Dataset](datasets) for the descriptions of the data in the formal-run.

To make the evaluation process as fair as possible, we do not include reference dialogue
breakdown labels in the evaluation data. We replace all reference labels with "`O`".
Once we receive your dialogue breakdown detection results, we (the organizers)
will run the evaluation script on your result files and let you know the evaluation results as soon as possible.

## How to submit your runs

Please send your run files by email to:
`dbdc3-organizers@googlegroups.com`.

The subject of the email should be `DBDC3 formal-run <your-team-ID>`.

For each language, each team can submit upto 3 runs.
The run file should have the name `DBDC3-<your-team-ID>-<lang>-<run#>.zip`. `<lang>` should be either `en` or `jp`.

Each zip file should have the following directory structure.

### For English

~~~~
en/-IRIS/
    |-<dialogue-id>.labels.json
    |-...
    |-<dialogue-id>.labels.json
  |-TickTock/
    |-<dialogue-id>.labels.json
    |-...
    |-<dialogue-id>.labels.json
  |-CIC/
    |-<dialogue-id>.labels.json
    |-...
    |-<dialogue-id>.labels.json
  |-YI/
    |-<dialogue-id>.labels.json
    |-...
    |-<dialogue-id>.labels.json    
~~~~

Each zip file for English should have 200 json files.

`en` is the top-level folder and below that, there are four folders named IRIS, TickTock, CIC and YI.
Under each of these folders, there should be 50 json files.

### For Japanese

~~~~
jp/-DCM/
    |-<dialogue-id>.labels.json
    |-...
    |-<dialogue-id>.labels.json
  |-DIT/
    |-<dialogue-id>.labels.json
    |-...
    |-<dialogue-id>.labels.json
  |-IRS/
    |-<dialogue-id>.labels.json
    |-...
    |-<dialogue-id>.labels.json
~~~~

Each zip file for Japanese should have 150 json files.

`jp` is the top-level folder and below that, there are three folders named DCM, DIT and IRS.
Under each of these folders, there should be 50 json files.

See the [submission format](submission_format) for the details of the `<dialogue-id>.labels.json` file.

As an example, if your team-ID is `AAA` and you submit two runs for English and three runs for Japanese,
you will attach the following files to your email.
~~~~
DBDC3-AAA-en-1.zip
DBDC3-AAA-en-2.zip
DBDC3-AAA-jp-1.zip
DBDC3-AAA-jp-2.zip
DBDC3-AAA-jp-3.zip
~~~~

Please describe the method used for each run in the body of the email. We will take it into
cosideration when we write the overview paper.

## If you have any questions

Please contact: `dbdc3-organizers@googlegroups.com`
