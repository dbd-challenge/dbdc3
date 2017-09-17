# About this challenge

対話破綻検出チャレンジの参加者は，人間と対話システムの対話データが与えられ，そこからシステムが文脈的に不適切な応答をした箇所を検出する(対話破綻検出)アルゴリズムを開発します．当サイトでは現在，対話破綻情報がアノテーションされている対話データ(json形式)が公開されており，このデータを学習用・開発用に使用することができます．チャレンジの参加者には，対話破綻情報が付与されていない対話データが後日配布され，そのデータの破綻検出結果を用いて開発したアルゴリズムの性能を競います．

当サイトでは，ベースラインとなる破綻検出プログラム，および破綻検出結果を評価するための評価スクリプトを公開しています．
以下ではそれぞれの使い方について解説します．


# About dialogue data and dialogue breakdown

当サイトで配布している対話データには，1つの対話システムの発話に対し，複数のアノテータが付与した複数の対話破綻情報が付与されています．対話破綻情報はO(破綻ではない)，T(破綻とは言い切れないが，違和感を感じる)，X(破綻)の3種類となっています．
例えば，公開しているデータのうち，IRIS_100の対話データでは，1つの発話に対し30名のアノテータがアノテーションを行っていますので，ある発話に対し，3名がO，6名がT，21名がXを付与したといった情報が発話ごとにアノテーションされています．
チャレンジの参加者は各発話に対して多数を占めるアノテーションは何かを当てる(先の例では，Xが正解となる)と同時に，3種類のそれぞれの割合(先の例では[0.1, 0.2, 0.7]が正解となる)を当てるタスクに取り組むことになります．


# Execution environment

To run the baseline program and evaluation script, the following must be installed.

JavaSE-1.7+
Python 2.7.x
Execute the program on the command prompt (Windows) / Terminal (Mac, Linux). 
You can check whether each program is correctly installed by executing the following command.
`$java -version`
`$python -V`


#Launching baseline program

The baseline program uses words included in each utterance as features (Bag-of-Words) and detects dialogue breakdowns by using Conditional Random Fields (CRFs).
This program outputs three kinds of labels, O (not a breakdown), T (possible breakdown,) and X (breakdown) with probability distributions.
However, each distribution is deterministic, i.e., 1.0 for the output label, 0.0 for the others.

1. まず，対話データ，[baseline script]，[eval script]をダウンロードしてください．
2. baselineと同じディレクトリ内にeval_script.zipにあるeval.pyを配置してください．
3. 次に，IRIS_100ディレクトリ内の100個のjsonを2つに分け，それぞれ baseline/train/ と baseline/test/ ディレクトリに入れてください．
4. ディレクトリ構成は以下のようになるはずです． 以下の例では，IRIS_100のデータを名前順にソートし，先頭の80個（1407219916log.json～1408219169log.json）をbaseline/train/ に，残りの50個（1408261480log.json～1409463385log.json）を baseline/test/ に配置しました．
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
5. baseline/train/ 内のデータを学習データとし，baseline/test/ 内のテストデータに対して破綻検出を行います．ターミナルでbaselineディレクトリに移動し，以下のコマンドを実行してください．
	`$java -jar DBDBaseline.jar -l ./train/ -p ./test/ -o ./out/ -t 0.5`

6. When training is finished, the model detects dialogue breakdowns on the JSON files in the directory containing the test data specified by -p and outputs the results to the directory specified by -o in JSON format.

[baseline]:https://github.com/dbd-challenge/dbdc3/tree/master/prog/crf_baseline  "baseline"
[eval script]:https://github.com/dbd-challenge/dbdc3/tree/master/prog/eval_script "eval script"


# How to run the evaluation script
本スクリプトは，破綻検出アルゴリズムが出力した破綻検出結果と，その正解データである対話データに対するアノテーションを照合することにより，アルゴリズムの性能評価を行います．
実行方法は以下の通りです．

1. 上で行ったベースラインプログラムによる破綻検出の結果を評価します．以下のコマンドを実行してください．
`$python eval.py -p ./test/ -o ./out/ -t 0.5`

2. 正解データに関する情報と破綻検出の性能評価結果が表示されます．なお，正解データに関する情報の「O ～ X Label Num」は，アノテータ間の多数決により決定されたラベルの数を意味しており，実際に対話データに含まれている複数人によって付与されたアノテーションの総数とは異なる値です．
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

	Precision (T+X) : 	0.790441 (215/272)
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
