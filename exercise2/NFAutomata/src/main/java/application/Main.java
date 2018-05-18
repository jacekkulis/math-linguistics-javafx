package application;

import interfaces.IAutomata;
import org.apache.commons.io.IOUtils;
import utils.Automata;
import utils.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
        FileReader fileReader = new FileReader("data.txt");
        List<String> data = fileReader.getFileWithUtil();

        data.stream().forEach(line -> {
            System.out.println(line);
            IAutomata automata = new Automata();
            automata.buildAutomata();

            for (int i=0; i < line.length(); i++){
                automata = automata.switchState(String.valueOf(line.charAt(i)));
            }

            automata.isFinal();
        });
	}
	
}
