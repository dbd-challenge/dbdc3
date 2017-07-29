package projectNextNLP.dialogue.sample;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import cc.mallet.fst.CRF;
import cc.mallet.fst.CRFTrainerByLabelLikelihood;
import cc.mallet.fst.Transducer;
import cc.mallet.pipe.Pipe;
import cc.mallet.pipe.SerialPipes;
import cc.mallet.pipe.SimpleTaggerSentence2TokenSequence;
import cc.mallet.pipe.TokenSequence2FeatureVectorSequence;
import cc.mallet.pipe.iterator.LineGroupIterator;
import cc.mallet.pipe.tsf.OffsetConjunctions;
import cc.mallet.pipe.tsf.TokenFirstPosition;
import cc.mallet.types.Instance;
import cc.mallet.types.InstanceList;
import cc.mallet.types.Sequence;

/**
 * @author inaba
 *
 */
public class CRFBreakdownTagger {
	Pipe pipe;
	Transducer transducer;

	public CRFBreakdownTagger() {
		ArrayList<Pipe> pipes = new ArrayList<Pipe>();

		//Tokenの系列に変換
		pipes.add(new SimpleTaggerSentence2TokenSequence());

		//1発話前と後の素性を判定に使用
		int[][] conjunctions = new int[2][];
		conjunctions[0] = new int[] { -1 }; //1発話前
		conjunctions[1] = new int[] { 1 }; //1発話後

		pipes.add(new OffsetConjunctions(conjunctions));



		//系列の最初の要素に対し，系列の最初だというtokenを追加する．
		pipes.add(new TokenFirstPosition("FIRSTTOKEN"));

		//tokenの系列を素性ベクトルに変換
		pipes.add(new TokenSequence2FeatureVectorSequence());

		this.pipe = new SerialPipes(pipes);
	}

	public void learn(String inputLine) {


		InstanceList trainingInstances = new InstanceList(this.pipe);
		trainingInstances.addThruPipe(new LineGroupIterator(new StringReader(inputLine), Pattern.compile("^\\s*$"), true));


		CRF crf = new CRF(pipe, null);
		crf.addStatesForLabelsConnectedAsIn(trainingInstances); //付与するラベルは直前のラベルに，素性は直前のラベルと付与するラベルの両方に依存する
		crf.addStartState();

		CRFTrainerByLabelLikelihood trainer = new CRFTrainerByLabelLikelihood(crf);
		trainer.setGaussianPriorVariance(10.0);


		trainer.train(trainingInstances);

		transducer = trainer.getTransducer();


	}

//	public List<Sequence> predict(String testLine) {
//		List<Sequence> predOutput = new ArrayList<Sequence>();
//
//		InstanceList testingInstances = new InstanceList(pipe);
//		testingInstances.addThruPipe(new LineGroupIterator(new StringReader(testLine), Pattern.compile("^\\s*$"), true));
//		for(Instance ins : testingInstances) {
//			//System.out.println(ins.getData());
//			Sequence input = (Sequence) ins.getData();
//
//			//Sequence trueOutput = (Sequence) ins.getTarget();
//
//			predOutput.add(transducer.transduce(input));
//		}
//		System.out.println(predOutput.size());
//		return predOutput;
//
//	}

	public List<String> predict(String testLine) {
		List<String> predLabelList = new ArrayList<String>();

		InstanceList testingInstances = new InstanceList(pipe);
		testingInstances.addThruPipe(new LineGroupIterator(new StringReader(testLine), Pattern.compile("^\\s*$"), true));
		for(Instance ins : testingInstances) {
			//System.out.println(ins.getData());
			Sequence input = (Sequence) ins.getData();

			//Sequence trueOutput = (Sequence) ins.getTarget();

			Sequence seq = transducer.transduce(input);
			for(int i = 0; i < seq.size(); i++) {
				String predictLabel = seq.get(i).toString();
				predLabelList.add(predictLabel);
			}

		}

		return predLabelList;

	}

	public List<String> predictOneByOne(String testLine) {
		List<String> predOutput = new ArrayList<String>();


		while(testLine.contains("\n")) {
			InstanceList testingInstances = new InstanceList(pipe);
			testingInstances.addThruPipe(new LineGroupIterator(new StringReader(testLine), Pattern.compile("^\\s*$"), true));


			Sequence input = (Sequence) testingInstances.get(0).getData();

			Sequence result = transducer.transduce(input);

			//最後のラベルのみを追加
			predOutput.add(0, result.get(result.size()-1).toString());

			//ラストを1行ずつ削っていく
			testLine = testLine.replaceAll("^.*\n$", "");
			testLine = testLine.replaceAll("\n.*\n$", "\n");



		}

		return predOutput;

	}

	public static void main(String[] args) {

		String inputLine = "Bill CAPITALIZED noun\n" + "slept non-noun\n" + "here LOWERCASE STOPWORD non-noun\n"+"\n"+
							"abad noun\n"+"slept non-noun\n"+"here LOWERCASE STOPWORD non-noun\n"+"\n"+
							"tes noun\n"+"here LOWERCASE non-noun\n";
		System.out.println("a " + inputLine);
		inputLine = inputLine.replaceAll("\n.*\n$", "\n");
		inputLine = inputLine.replaceAll("\n.*\n$", "\n");
		inputLine = inputLine.replaceAll("^.*\n$", "");
		System.out.println("b " + inputLine);

		CRFBreakdownTagger cbt = new CRFBreakdownTagger();
		cbt.learn(inputLine);

		String testLine = "Bill CAPITALIZED noun\n"+"slept non-noun\n"+
				"here LOWERCASE STOPWORD non-noun\n"+"\n"+"abad noun\n"+"slept non-noun\n"+"here LOWERCASE STOPWORD non-noun\n"+"\n"+"tes noun\n"+"here LOWERCASE non-noun\n";

		//List<Sequence> plist = cbt.predict(testLine);

//		for(Sequence seq : plist) {
//			for(int i = 0; i < seq.size(); i++) {
//				System.out.println(seq.get(i));
//			}
//		}
	}


}
