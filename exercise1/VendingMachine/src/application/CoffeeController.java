package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import interfaces.IAutomata;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import models.Coffee;
import models.Tea;
import utils.Automata;

public class CoffeeController implements Initializable {
	
	@FXML private BorderPane rootPane;
	
	@FXML HBox coinContainer;
	
	@FXML private Label labSum;
	
	@FXML private Label labSumVal;
	
	@FXML private Label labChange;
	
	@FXML private Label labChangeVal;
	
	@FXML private Label labGetProduct;

	private IAutomata automata;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		automata = new Automata();
		automata.setProduct(new Coffee());
		automata.buildAutomata();
		
		labChange.setVisible(false);
		labChangeVal.setVisible(false);
		
	}
	
	@FXML
	private void coinOne(MouseEvent event) throws IOException {
		automata = automata.switchState(1);
		
		labSum.setText(String.valueOf(Integer.parseInt(automata.getCurrentState().getId().replaceAll("[\\D]", ""))));
		
		if (automata.isFinal()) {
			labChange.setVisible(true);
			labChangeVal.setVisible(true);
			
			labSum.setVisible(false);
			labSumVal.setVisible(false);
			coinContainer.setVisible(false);
			
			labChangeVal.setText(String.valueOf(automata.getCurrentState().getChange()));
			labGetProduct.setText("Wydano napój, pamiêtaj o zabraniu reszty.");
		}
	}
	
	@FXML
	private void coinTwo(MouseEvent event) throws IOException {
		automata = automata.switchState(2);
	
		labSum.setText(String.valueOf(Integer.parseInt(automata.getCurrentState().getId().replaceAll("[\\D]", ""))));
		
		if (automata.isFinal()) {
			labChange.setVisible(true);
			labChangeVal.setVisible(true);
			
			labSum.setVisible(false);
			labSumVal.setVisible(false);
			coinContainer.setVisible(false);
			
			labChangeVal.setText(String.valueOf(automata.getCurrentState().getChange()));
			labGetProduct.setText("Wydano napój, pamiêtaj o zabraniu reszty.");
		}
	}
	
	@FXML
	private void coinFive(MouseEvent event) throws IOException {
		automata = automata.switchState(5);

		labSum.setText(String.valueOf(Integer.parseInt(automata.getCurrentState().getId().replaceAll("[\\D]", ""))));
		
		if (automata.isFinal()) {
			labChange.setVisible(true);
			labChangeVal.setVisible(true);
			
			labSum.setVisible(false);
			labSumVal.setVisible(false);
			coinContainer.setVisible(false);
			
			labChangeVal.setText(String.valueOf(automata.getCurrentState().getChange()));
			labGetProduct.setText("Wydano napój, pamiêtaj o zabraniu reszty.");
		}
	}
	
}
