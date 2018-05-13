package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class MachineController implements Initializable {
	
	@FXML
	private BorderPane rootPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	@FXML
	private void loadTea(MouseEvent event) throws IOException {
		BorderPane coinsPane = (BorderPane) FXMLLoader.load(getClass().getResource("Tea.fxml"));
		rootPane.getChildren().setAll(coinsPane);
	}
	
	@FXML
	private void loadCoffee(MouseEvent event) throws IOException {
		BorderPane coinsPane = (BorderPane) FXMLLoader.load(getClass().getResource("Coffee.fxml"));
		rootPane.getChildren().setAll(coinsPane);
	}
}
