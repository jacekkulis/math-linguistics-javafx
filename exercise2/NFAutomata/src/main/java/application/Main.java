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
            IAutomata automata = new Automata();
            automata.buildAutomata();

            automata.accepts(String.valueOf(line));
        });
	}
	
}
