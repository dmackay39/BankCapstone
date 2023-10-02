package com.example.bankcapstone;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class XYZBankController {
    public RadioButton customerLogIn;
    public RadioButton customerRegistration;
    public RadioButton employeeLogIn;

    public Button landingPageButton;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}