package projectNextNLP.dialogue.sample;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DialogueDataReader {

	public static List<Dialogue> readDir(String dirPath, double threshold) throws JsonProcessingException, IOException {
		List<Dialogue> dialogueList = new ArrayList<Dialogue>();

		File file = new File(dirPath);

		for(File f : file.listFiles()) {
			if(f.toString().endsWith("log.json")) {


				ObjectMapper mapper = new ObjectMapper();
				JsonNode root = mapper.readTree(f);

				int dlgId = root.get("dialogue-id").asInt();
				Dialogue dialogue = new Dialogue(dlgId);

				//ユーザ用ラベルのために，直前のラベルを保持
				String prevLabel = "O";

				for(JsonNode turn : root.get("turns")) {
					if(turn.get("speaker").asText().equals("S")) {
						String label = decideLabel(turn.get("annotations"),threshold);
						Utterance u = new Utterance(turn.get("turn-index").asInt(), turn.get("utterance").asText(), turn.get("speaker").asText(), label);
						dialogue.addUtterance(u);

						prevLabel = label;
					}else if (turn.get("speaker").asText().equals("U")) {
						Utterance u = new Utterance(turn.get("turn-index").asInt(), turn.get("utterance").asText(), turn.get("speaker").asText(), "U-PREV-" + prevLabel);
						dialogue.addUtterance(u);
					}else{
						System.err.println("Speaker Property Error " + dlgId);
					}

				}

				dialogueList.add(dialogue);
			}
		}


		return dialogueList;
	}

	public static Dialogue readFile(String filePath, double threshold) throws JsonProcessingException, IOException {

		File f = new File(filePath);

		if(f.toString().endsWith("log.json")) {


			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(f);

			int dlgId = root.get("dialogue-id").asInt();
			Dialogue dialogue = new Dialogue(dlgId);

			//ユーザ用ラベルのために，直前のラベルを保持
			String prevLabel = null;

			for(JsonNode turn : root.get("turns")) {
				//システム側の発話の場合
				if(turn.get("speaker").asText().equals("S")) {
					String label = decideLabel(turn.get("annotations"),threshold);
					Utterance u = new Utterance(turn.get("turn-index").asInt(), turn.get("utterance").asText(), turn.get("speaker").asText(), label);
					dialogue.addUtterance(u);

					prevLabel = label;

				//ユーザ側の発話の場合
				}else if (turn.get("speaker").asText().equals("U")) {
					//直前のシステムのラベルに依存したユーザ専用のラベルを付与(U-PREV-O or T or X)
					Utterance u = new Utterance(turn.get("turn-index").asInt(), turn.get("utterance").asText(), turn.get("speaker").asText(), "U-PREV-" + prevLabel);
					dialogue.addUtterance(u);
				}else{
					System.err.println("Speaker Property Error " + dlgId);
				}

			}

			return dialogue;
		}


		return null;
	}

	/**
	 * アノテーションを元に，発話に付与する単一のラベルを決定します
	 * @param annotations 複数人によるアノテーション
	 * @return ラベルを示す文字列
	 */
	private static String decideLabel(JsonNode annotations, double threshold) {
		String label = null;

		int countO = 0;
		int countT = 0;
		int countX = 0;

		for(JsonNode annotation : annotations) {
			switch(annotation.get("breakdown").asText()) {
				case "O":
					countO++;
					break;
				case "T":
					countT++;
					break;
				case "X":
					countX++;
					break;
//				case "1":
//					countO++;
//					break;
//				case "2":
//					countT++;
//					break;
//				case "3":
//					countX++;
//					break;
				default:
					System.err.println("Annotation is NULL");
				}
		}

		if(countO >= countT && countO >= countX && countO*1.0 / (countO + countT + countX)*1.0 >= threshold) {
			label = "O";
		}else if(countT >= countO && countT >= countX && countT*1.0 / (countO + countT + countX)*1.0 >= threshold) {
			label = "T";
		}else if(countX >= countO && countX >= countT && countX*1.0 / (countO + countT + countX)*1.0 >= threshold) {
			label = "X";
		}else{
			label = "O";
		}
//		if(countO >= countT && countO >= countX && countO*1.0 / (countO + countT + countX)*1.0 >= threshold) {
//			label = "1";
//		}else if(countT >= countO && countT >= countX && countT*1.0 / (countO + countT + countX)*1.0 >= threshold) {
//			label = "2";
//		}else if(countX >= countO && countX >= countT && countX*1.0 / (countO + countT + countX)*1.0 >= threshold) {
//			label = "3";
//		}else{
//			label = "1";
//		}

		return label;
	}

	public static void main(String[] args) {
		try {
			List<Dialogue> dlist = DialogueDataReader.readDir("jsontest",0.0);
			List<Utterance> ulist =  dlist.get(1).getUtteranceList();
			for(Utterance u : ulist) {
				//System.out.println(u.getText() + " " + u.getLabel());
			}

			DialogueDataConverter ddc = new DialogueDataConverter();
			System.out.println(ddc.convert(dlist.get(0)));

		} catch (JsonProcessingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
