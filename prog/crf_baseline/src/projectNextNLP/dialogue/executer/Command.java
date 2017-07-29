package projectNextNLP.dialogue.executer;

/**
 * 実行時のコマンド
 *
 * @author inaba
 */
public enum Command {

	/**
	 * 学習データディレクトリ指定コマンド
	 */
	LEARN("-l"),

	/**
	 * テストデータディレクトリ指定コマンド
	 */
	PREDICT("-p"),

	/**
	 * 出力先ディレクトリ指定コマンド
	 */
	OUTPUT("-o"),

	/**
	 * 出力先ディレクトリ指定コマンド
	 */
	THRESHOLD("-t"),

	/**
	 * ラベル付与の際，対象発話以降を使用しない
	 */
	WITHOUTPOST("-w");

	/**
	 * コマンドライン引数として用いる文字列
	 */
	private String str;

	private Command(String str) {
		this.str = str;
	}

	/**
	 * コマンドライン引数として用いる文字列に変換します．
	 * @return 文字列
	 */
	public String toString() {
		return str;
	}

	/**
	 * <pre>
	 * StringからEnumに変換します．
	 *
	 * 該当するものがない場合，nullを返します．
	 * </pre>
	 *
	 * @param str 文字列
	 * @return Enum
	 */
	public static Command toEnum(String str) {

		Command res = null;
		String tempStr = str.toLowerCase();

		for (Command command : Command.values()) {
			if (tempStr.equals(command.toString())) {
				res = command;
				break;
			}
		}
		return res;
	}
}
