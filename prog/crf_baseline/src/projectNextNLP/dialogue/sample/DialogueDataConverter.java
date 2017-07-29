package projectNextNLP.dialogue.sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.java.sen.SenFactory;
import net.java.sen.StringTagger;
import net.java.sen.dictionary.Token;

/**
 * DialogueデータをMallet読み込み用に変換します.
 *
 * @author inaba
 *
 */
public class DialogueDataConverter {

	private StringTagger tagger;

	public DialogueDataConverter() {
		this.tagger = SenFactory.getStringTagger(null);
	}

	public String convert(Dialogue d) {
		String out = "";
		for(Utterance u : d.getUtteranceList()) {
			List<Token> tokenList = new ArrayList<Token>();

			try{
				tagger.analyze(u.getText(), tokenList);

				for(Token t : tokenList) {
					out += t.getSurface() + " ";
				}

				if(u.getSpeaker().equals("S")) {
					out += "Speaker:S ";
				}else if(u.getSpeaker().equals("U")) {
					out += "Speaker:U ";
				}

				if(u.getLabel().length() != 0) {
					out += u.getLabel() + "\n";
				}else{
					out += "UNKNOWN \n";
				}

			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return out;
	}

	public String convert(List<Dialogue> dialogueList) {
		String out = "";

		for(Dialogue d : dialogueList) {
			out += this.convert(d) + "\n";
		}

		return out;

	}
}
