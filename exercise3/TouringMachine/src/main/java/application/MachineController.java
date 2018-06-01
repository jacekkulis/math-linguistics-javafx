package application;

import interfaces.IAutomata;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utils.Automata;

import java.net.URL;
import java.util.ResourceBundle;

public class MachineController implements Initializable {

    private IAutomata automata;

    @FXML
    private BorderPane rootPane;

    @FXML
    private HBox numContainer;

    @FXML
    private Button compileBtn;

    @FXML
    private TextField inputNumber;

    @FXML
    private Label path;

    @FXML
    private Button startBtn;

    @FXML
    private Button nextBtn;

    @FXML
    private Button resetBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void compileBtn(MouseEvent event) {
        String input = inputNumber.getText();

        String temp = "";
        for (int i = 0; i < input.length() + 1; i++) {
            temp += "ϴ";
        }

        input = temp.concat(input);
        System.out.println(input);

        automata = new Automata(this, input);
        automata.buildAutomata();

        for (int i = 0; i < input.length(); i++) {
            Label label = new Label(String.valueOf(input.charAt(i)));
            label.setId("id" + i);
            numContainer.getChildren().add(label);
        }

        //ϴ

        compileBtn.setDisable(true);
        inputNumber.setDisable(true);

    }

    @FXML
    private void nextBtn(MouseEvent event) {
        startBtn.setDisable(true);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                next();
            }
        });
    }

    @FXML
    private void resetBtn(MouseEvent event) {
        startBtn.setDisable(false);
        nextBtn.setDisable(false);
        compileBtn.setDisable(false);
        inputNumber.setDisable(false);

        path.setText("");
        inputNumber.setText("");
        numContainer.getChildren().clear();

        automata = null;
        automata = new Automata(this, inputNumber.getText());
        automata.buildAutomata();
    }

    void next() {
        numContainer.getChildren().forEach(l -> {
            Label lab = (Label) l;
            ((Label) l).setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        });
        automata.checkNext();

        path.setText(automata.getPath());
    }

    @FXML
    void startBtn(MouseEvent event) {
        nextBtn.setDisable(true);

        int len = automata.getInputLenght();

        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < len; i++) {
                    Platform.runLater(() -> next());
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();

    }


    public void setBoldLabelText(int id, String text) {
        Label lab = (Label) numContainer.getChildren().get(id);
        lab.setText(text);
        lab.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    }
}
