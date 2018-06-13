package org.iis.app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    private StringBuilder builder;
    private RegexMatcher regexMatcher;

    @FXML
    private TextField inputExpr;

    @FXML
    private TextArea outputField;

    @FXML
    protected void computeAction() {
        outputField.clear();
        String s = inputExpr.getText();
        builder.append("Computing " + s + " expression.\n");
        inputExpr.clear();

        if (regexMatcher.compute(s)){
            builder.append("This string is accepted by matcher.");
        } else {
            builder.append("This string is not accepted by matcher!");
        }

        outputField.setText(builder.toString());
        builder.setLength(0);
    }

    public void initialize(){
        builder = new StringBuilder();
        regexMatcher = new RegexMatcher();
    }
}
