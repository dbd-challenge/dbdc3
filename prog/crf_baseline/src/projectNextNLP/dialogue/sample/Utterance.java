package projectNextNLP.dialogue.sample;

public class Utterance {
	private String text;
	private String speaker;
	private String label;
	private int turnIndex;

	public Utterance(int turnIndex, String text, String speaker, String label) {
		this.turnIndex = turnIndex;
		this.text = text;
		this.speaker = speaker;
		this.label = label;
	}

	/**
	 * textを取得します。
	 * @return text
	 */
	public String getText() {
	    return text;
	}
	/**
	 * textを設定します。
	 * @param text text
	 */
	public void setText(String text) {
	    this.text = text;
	}

	/**
	 * speakerを取得します。
	 * @return speaker
	 */
	public String getSpeaker() {
		return speaker;
	}


	/**
	 * speakerを設定します。
	 * @param speaker speaker
	 */
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	/**
	 * labelを取得します。
	 * @return label
	 */
	public String getLabel() {
	    return label;
	}

	/**
	 * labelを設定します。
	 * @param label label
	 */
	public void setLabel(String label) {
	    this.label = label;
	}

	/**
	 * turnIndexを取得します。
	 * @return turnIndex
	 */
	public int getTurnIndex() {
	    return turnIndex;
	}

	/**
	 * turnIndexを設定します。
	 * @param turnIndex turnIndex
	 */
	public void setTurnIndex(int turnIndex) {
	    this.turnIndex = turnIndex;
	}


}
