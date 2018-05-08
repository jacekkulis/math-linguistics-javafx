package application;
	
import java.util.ArrayList;
import java.util.List;

import interfaces.IAutomata;
import interfaces.IState;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.Automata;
import utils.State;
import utils.Transition;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main {
//	@Override
//	public void start(Stage primaryStage) {
//		try {
//			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Machine.fxml"));
//			Scene scene = new Scene(root, 640, 480);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
	public static void main(String[] args) {
		List<Integer> values = new ArrayList<Integer>();
		values.add(1);
		values.add(2);
		values.add(2);
		
		IAutomata automata = buildAutomata();
		
		for (Integer val : values) {
			automata = automata.switchState(val);
		}
		
		if (automata.canStop()) {
			System.out.println("Automata is in final state");
		} else {
			System.out.println("Automata is not in final state");
		}
		
		//launch(args);
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
		
		
		q0.with(new Transition(1, q1));
		q0.with(new Transition(2, q2));
		q0.with(new Transition(5, q5));
		
		q1.with(new Transition(1, q2));
		q1.with(new Transition(2, q3));
		q1.with(new Transition(5, q6));
		
		q2.with(new Transition(1, q3));
		q2.with(new Transition(2, q4));
		q2.with(new Transition(5, q7));
		
		q3.with(new Transition(1, q4));
		q3.with(new Transition(2, q5));
		q3.with(new Transition(5, q8));
		
		q4.with(new Transition(1, q5));
		q4.with(new Transition(2, q6));
		q4.with(new Transition(5, q8));
		
		q5.with(new Transition(1, q6));
		q5.with(new Transition(2, q7));
		q5.with(new Transition(5, q8));
		
		q6.with(new Transition(1, q7));
		q6.with(new Transition(2, q8));
		q6.with(new Transition(5, q8));
		
		q7.with(new Transition(1, q8));
		q7.with(new Transition(2, q8));
		q7.with(new Transition(5, q8));
		
		q8.with(new Transition(1, q8));
		q8.with(new Transition(2, q8));
		q8.with(new Transition(5, q8));
		
		return new Automata(q0);
	}
}
