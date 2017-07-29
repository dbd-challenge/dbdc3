package projectNextNLP.dialogue.json;


public class TestResult {
	private String dlgId;
	private Turn turn;

	public TestResult() { }


	public TestResult(String dlgId) {
		this.dlgId = dlgId;
	}




	public String getDlgId() {
		return dlgId;
	}
	public void setDlgId(String dlgId) {
		this.dlgId = dlgId;
	}



    public Turn getTurn() {
		return turn;
	}


	public void setTurn(Turn turn) {
		this.turn = turn;
	}


	@Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Class[").append(getClass().getName()).append("] ");
        builder.append("dlg-id = ").append(dlgId).append(", ");
        builder.append("turn = ").append(turn.getTurnIndex()).append(" ");

        return builder.toString();
    }


}
