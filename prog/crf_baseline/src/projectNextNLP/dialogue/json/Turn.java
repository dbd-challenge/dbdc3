package projectNextNLP.dialogue.json;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Turn {
	@JsonProperty("turn-index")
	private int turnIndex;

	@JsonProperty("labels")
	private List<Label> labels = new ArrayList<Label>();;

	public Turn() { }


	public Turn(int turnIndex) {
		this.turnIndex = turnIndex;
	}


	public int getTurnIndex() {
		return turnIndex;
	}
	public void setTurnIndex(int turnIndex) {
		this.turnIndex = turnIndex;
	}
	public List<Label> getLabels() {
		return labels;
	}
	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

	public void addLabel(Label label) {
		this.labels.add(label);
	}

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Class[").append(getClass().getName()).append("] ");
        builder.append("turn-index = ").append(turnIndex).append(", ");
        builder.append("labels[").append(labels.getClass().getName()).append("] = ").append(labels.toString());

        return builder.toString();
    }
}
