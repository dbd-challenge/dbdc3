package projectNextNLP.dialogue.executer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import projectNextNLP.dialogue.json.Label;
import projectNextNLP.dialogue.json.Result;
import projectNextNLP.dialogue.json.Turn;
import projectNextNLP.dialogue.sample.CRFBreakdownTagger;
import projectNextNLP.dialogue.sample.Dialogue;
import projectNextNLP.dialogue.sample.DialogueDataConverter;
import projectNextNLP.dialogue.sample.DialogueDataReader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class Executer {
	public static void main(String[] args) throws JsonProcessingException, IOException {
		String learnPath = null;
		String predictPath = null;
		String outputPath = null;
		double threshold = 0.0;
		boolean withoutPost = true;

		for (int i = 0; i < args.length; i++) {

			Command command = Command.toEnum(args[i]);
			if (command != null) {
				switch (command) {

				case LEARN:
					learnPath = args[i + 1];

					if(!learnPath.endsWith("/")) {
						learnPath += "/";
					}
					break;

				case PREDICT:
					predictPath = args[i + 1];
					if(!predictPath.endsWith("/")) {
						predictPath += "/";
					}
					break;


				case OUTPUT:
					outputPath = args[i + 1];
					if(!outputPath.endsWith("/")) {
						outputPath += "/";
					}
					break;
				case THRESHOLD:
					threshold = Double.parseDouble(args[i + 1]);
					break;
					
				default:
					System.err.println("未知のコマンドが指定されています");
					System.exit(0);
					break;
				}
			}
		}


		if(learnPath == null) {
			throw new IllegalArgumentException("Missing learn directory path");
		}else if(outputPath == null) {
			throw new IllegalArgumentException("Missing output directory path");
		}

		//predictPathが未入力の場合はlearnPathと同じにする
		if(predictPath == null) {
			predictPath = learnPath;
		}

		//出力先フォルダが無ければ作成
		File outputDir = new File(outputPath);
		if(!outputDir.exists()) {
			outputDir.mkdirs();
		}




		//学習
		List<Dialogue> learnDList = DialogueDataReader.readDir(learnPath,threshold);
		DialogueDataConverter ddc = new DialogueDataConverter();
		CRFBreakdownTagger cbt = new CRFBreakdownTagger();

		cbt.learn(ddc.convert(learnDList));

		//ラベリング
		File file = new File(predictPath);


		for(File f : file.listFiles()) {
			if(f.toString().endsWith("json")) {
				Dialogue tmpDlg = DialogueDataReader.readFile(f.toString(),threshold);
				List<String> seq;

				//当該システム発話より後の発話を使用しない場合
				if(withoutPost){
					seq	= cbt.predictOneByOne(ddc.convert(tmpDlg));
				}else{//当該システム発話より後の発話を使用する場合
					seq = cbt.predict(ddc.convert(tmpDlg));
				}

				Result result = new Result(tmpDlg.getId());
				List<Turn> turns = new ArrayList<Turn>();

				for(int i = 0; i < seq.size(); i++) {
					if(tmpDlg.getUtteranceList().get(i).getSpeaker().equals("U")) {
						continue;
					}
					String predictLabel = seq.get(i).toString();

					Turn turn = new Turn(i);

					switch(predictLabel) {
					case "O":
						turn.addLabel(new Label("O",1.0, 0, 0));
						result.addTurn(turn);
						break;
					case "T":
						turn.addLabel(new Label("T",0.0, 1.0, 0));
						result.addTurn(turn);
						break;
					case "X":
						turn.addLabel(new Label("X",0, 0, 1.0));
						result.addTurn(turn);
						break;
					default:
						System.err.println("Annotation ERROR");
						System.out.println(predictLabel + " " + f.getName());
					}

					FileOutputStream fos = new FileOutputStream(outputPath + tmpDlg.getId() + ".labels.json");
					OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
					BufferedWriter writer = new BufferedWriter(osw);

					ObjectMapper mapper = new ObjectMapper();
					mapper.enable(SerializationFeature.INDENT_OUTPUT);
					mapper.writeValue(writer, result);

				}

			}
		}








//		String testLine = "";
//		List<Dialogue> targetDList = DialogueDataReader.readDir(predictPath);
//		List<Sequence> plist = cbt.predict(ddc.convert(targetDList));
//
//
//
//
//
//		for(Dialogue d : targetDList) {
//			for(Sequence seq : plist) {
//				for(int i = 0; i < seq.size(); i++) {
//					System.out.print(d.getUtteranceList().get(i).getText() + " " + d.getUtteranceList().get(i).getLabel() + " ");
//					System.out.println(seq.get(i));
//				}
//			}
//		}








	}
}
