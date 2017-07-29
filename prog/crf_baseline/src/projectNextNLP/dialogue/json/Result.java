package projectNextNLP.dialogue.json;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {
	@JsonProperty("dialogue-id")
	private String dlgId;

	@JsonProperty("turns")
	private List<Turn> turns = new ArrayList<Turn>();;

	public Result() { }


	public Result(String dlgId) {
		this.dlgId = dlgId;
	}

	public Result(int dlgId) {
		this.dlgId = String.valueOf(dlgId);
	}


	public String getDlgId() {
		return dlgId;
	}
	public void setDlgId(String dlgId) {
		this.dlgId = dlgId;
	}
	public List<Turn> getTurns() {
		return turns;
	}
	public void setTurns(List<Turn> turns) {
		this.turns = turns;
	}

	public void addTurn(Turn turn) {
		this.turns.add(turn);
	}


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Class[").append(getClass().getName()).append("] ");
        builder.append("dlgId = ").append(dlgId).append(", ");
        builder.append("turns[").append(turns.getClass().getName()).append("] = ").append(turns.toString());

        return builder.toString();
    }


}
