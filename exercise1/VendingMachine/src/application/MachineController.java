package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import interfaces.IAutomata;
import interfaces.IState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import utils.Automata;
import utils.State;
import utils.Transition;

public class MachineController implements Initializable {
	
	@FXML
	private BorderPane rootPane;
	
	@FXML
	private Label sumVal;
	
	@FXML
	private Label changeVal;
	
	private IAutomata automata;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		automata = buildAutomata();

		if (automata.isFinal()) {
			// end program
		}

	}
	
	@FXML
	private void loadTea(MouseEvent event) throws IOException {
		BorderPane coinsPane = (BorderPane) FXMLLoader.load(getClass().getResource("Coins.fxml"));
		rootPane.getChildren().setAll(coinsPane);
	}
	
	@FXML
	private void loadCoffee(MouseEvent event) throws IOException {
		BorderPane coinsPane = (BorderPane) FXMLLoader.load(getClass().getResource("Coins.fxml"));
		rootPane.getChildren().setAll(coinsPane);
	}
	
	@FXML
	private void coinOne(MouseEvent event) throws IOException {
		automata = automata.switchState(1);
		sumVal.setText("1");
	}
	
	@FXML
	private void coinTwo(MouseEvent event) throws IOException {
		automata = automata.switchState(2);
	}
	
	@FXML
	private void coinFive(MouseEvent event) throws IOException {
		automata = automata.switchState(5);
	}
	
	private static IAutomata buildAutomata() {
		IState q0 = new State("q0");
		IState q1 = new State("q1");
		IState q2 = new State("q2");
		IState q3 = new State("q3");
		IState q4 = new State("q4");
		IState q5 = new State("q5", true);
		IState q6 = new State("q6");
		IState q7 = new State("q7", true);
		IState q8 = new State("q8");

		q0.addTransition(new Transition(1, q1));
		q0.addTransition(new Transition(2, q2));
		q0.addTransition(new Transition(5, q5));

		q1.addTransition(new Transition(1, q2));
		q1.addTransition(new Transition(2, q3));
		q1.addTransition(new Transition(5, q6));

		q2.addTransition(new Transition(1, q3));
		q2.addTransition(new Transition(2, q4));
		q2.addTransition(new Transition(5, q7));

		q3.addTransition(new Transition(1, q4));
		q3.addTransition(new Transition(2, q5));
		q3.addTransition(new Transition(5, q8));

		q4.addTransition(new Transition(1, q5));
		q4.addTransition(new Transition(2, q6));
		q4.addTransition(new Transition(5, q8));

		q5.addTransition(new Transition(1, q6));
		q5.addTransition(new Transition(2, q7));
		q5.addTransition(new Transition(5, q8));

		q6.addTransition(new Transition(1, q7));
		q6.addTransition(new Transition(2, q8));
		q6.addTransition(new Transition(5, q8));

		q7.addTransition(new Transition(1, q8));
		q7.addTransition(new Transition(2, q8));
		q7.addTransition(new Transition(5, q8));

		q8.addTransition(new Transition(1, q8));
		q8.addTransition(new Transition(2, q8));
		q8.addTransition(new Transition(5, q8));

		return new Automata(q0);
	}
}
