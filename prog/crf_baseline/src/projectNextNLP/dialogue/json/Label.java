package projectNextNLP.dialogue.json;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Label {
	@JsonProperty("breakdown")
	private String breakdown;

	@JsonProperty("prob-O")
	private double probO;

	@JsonProperty("prob-T")
	private double probT;

	@JsonProperty("prob-X")
	private double probX;


	public Label() { }


	public Label(String breakdown, double probO, double probT, double probX) {
		this.breakdown = breakdown;


		this.probO = probO;

		this.probT = probT;
		this.probX = probX;
	}




	/**
	 * breakdownを取得します。
	 * @return breakdown
	 */
	public String getBreakdown() {
		return breakdown;
	}


	/**
	 * breakdownを設定します。
	 * @param breakdown breakdown
	 */
	public void setBreakdown(String breakdown) {
		this.breakdown = breakdown;
	}


	/**
	 * probOを取得します。
	 * @return probO
	 */
	public double getProbO() {
	    return probO;
	}


	/**
	 * probOを設定します。
	 * @param probO probO
	 */
	public void setProbO(double probO) {
	    this.probO = probO;
	}


	/**
	 * probTを取得します。
	 * @return probT
	 */
	public double getProbT() {
	    return probT;
	}


	/**
	 * probTを設定します。
	 * @param probT probT
	 */
	public void setProbT(double probT) {
	    this.probT = probT;
	}


	/**
	 * probXを取得します。
	 * @return probX
	 */
	public double getProbX() {
	    return probX;
	}


	/**
	 * probXを設定します。
	 * @param probX probX
	 */
	public void setProbX(double probX) {
	    this.probX = probX;
	}

	@Override
	public String toString() {
	    StringBuilder builder = new StringBuilder();

	    builder.append("Class[").append(getClass().getName()).append("] ");
	    builder.append("breakdown = ").append(breakdown).append(" ");

	    return builder.toString();
	}
}