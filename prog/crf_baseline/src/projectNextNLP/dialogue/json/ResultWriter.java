package projectNextNLP.dialogue.json;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ResultWriter {


	public static void main(String[] args) {
		Result result = new Result("1404365812");
		List<Turn> turns = new ArrayList<Turn>();

		Turn turn0 = new Turn(0);
		turn0.addLabel(new Label("O",1.0, 0, 0));
		result.addTurn(turn0);

		Turn turn2 = new Turn(2);
		turn2.addLabel(new Label("X",0, 0, 1.0));
		result.addTurn(turn2);

		Turn turn4 = new Turn(4);
		turn4.addLabel(new Label("T",0, 1.0, 0));
		result.addTurn(turn4);
		
		Turn turn6 = new Turn(6);
		turn6.addLabel(new Label("O",0.5, 0.25, 0.25));
		result.addTurn(turn6);


		System.out.println(result.toString());


        try (FileOutputStream fos = new FileOutputStream("data/result.json");
                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                BufferedWriter writer = new BufferedWriter(osw)) {

               ObjectMapper mapper = new ObjectMapper();
               mapper.enable(SerializationFeature.INDENT_OUTPUT);
               mapper.writeValue(writer, result);

           } catch (IOException e) {
               e.printStackTrace();
           }
	}
}
