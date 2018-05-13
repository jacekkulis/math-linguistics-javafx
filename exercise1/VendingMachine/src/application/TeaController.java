package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import interfaces.IAutomata;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import models.Tea;
import utils.Automata;

public class TeaController implements Initializable {
	
	@FXML private BorderPane rootPane;
	
	@FXML HBox coinContainer;
	
	@FXML private Label labSum;
	
	@FXML private Label labChange;
	
	@FXML private Label labChangeVal;
	
	@FXML private Button btnGetProduct;
	
	@FXML private Label labGetProduct;

	private IAutomata automata;
	private int sum;
	private int change;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		automata = new Automata();
		automata.setProduct(new Tea());
		automata.buildAutomata();
		
		btnGetProduct.setVisible(false);
		labChange.setVisible(false);
		labChangeVal.setVisible(false);
		
		btnGetProduct.setOnAction((event) -> {
			coinContainer.setVisible(false);
			
			labGetProduct.setText("Wydano napój, pamiêtaj o zabraniu reszty.");
		    
			sum = 0;
			change = 0;
			
			labChangeVal.setText(String.valueOf(change));
			labSum.setText(String.valueOf(sum));
		});
	}
	
	@FXML
	private void coinOne(MouseEvent event) throws IOException {
		automata = automata.switchState(1);
		sum += 1;
		
		labSum.setText(String.valueOf(sum));
		
		if (automata.isFinal()) {
			btnGetProduct.setVisible(true);
			labChange.setVisible(true);
			labChangeVal.setVisible(true);
			
			change = sum - 5;
			labChangeVal.setText(String.valueOf(change));
		}
	}
	
	@FXML
	private void coinTwo(MouseEvent event) throws IOException {
		automata = automata.switchState(2);
		sum += 2;
		
		labSum.setText(String.valueOf(sum));
		
		if (automata.isFinal()) {
			btnGetProduct.setVisible(true);
			labChange.setVisible(true);
			labChangeVal.setVisible(true);
			
			change = sum - 5;
			labChangeVal.setText(String.valueOf(change));
		}
	}
	
	@FXML
	private void coinFive(MouseEvent event) throws IOException {
		automata = automata.switchState(5);
		sum += 5;
		
		labSum.setText(String.valueOf(sum));
		
		if (automata.isFinal()) {
			btnGetProduct.setVisible(true);
			labChange.setVisible(true);
			labChangeVal.setVisible(true);
			
			change = sum - 5;
			labChangeVal.setText(String.valueOf(change));
		}
	}
	
}
