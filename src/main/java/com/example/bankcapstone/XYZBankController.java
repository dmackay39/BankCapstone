package com.example.bankcapstone;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class XYZBankController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}