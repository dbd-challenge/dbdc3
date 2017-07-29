package projectNextNLP.dialogue.sample;

import java.util.ArrayList;
import java.util.List;

public class Dialogue {
	private List<Utterance> utteranceList;
	private int id;

	public Dialogue(List<Utterance> utteranceList, int id) {
		this.utteranceList = utteranceList;
		this.id = id;
	}

	public Dialogue(int id) {
		this.utteranceList = new ArrayList<Utterance>();
		this.id = id;
	}

	/**
	 * utteranceListを取得します。
	 * @return utteranceList
	 */
	public List<Utterance> getUtteranceList() {
	    return utteranceList;
	}
	/**
	 * utteranceListを設定します。
	 * @param utteranceList utteranceList
	 */
	public void setUtteranceList(List<Utterance> utteranceList) {
	    this.utteranceList = utteranceList;
	}

	/**
	 * utteranceを追加します．
	 * @param utterance
	 */
	public void addUtterance(Utterance utterance) {
	    this.utteranceList.add(utterance);
	}

	/**
	 * idを取得します。
	 * @return id
	 */
	public int getId() {
	    return id;
	}
	/**
	 * idを設定します。
	 * @param id id
	 */
	public void setId(int id) {
	    this.id = id;
	}
}
