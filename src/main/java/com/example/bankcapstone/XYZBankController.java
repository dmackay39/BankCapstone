package com.example.bankcapstone;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class XYZBankController {
    @FXML
    public RadioButton customerLogIn;
    @FXML
    public RadioButton customerRegistration;
    @FXML
    public RadioButton employeeLogIn;
    @FXML
    public ToggleGroup landingPageGroup;
    @FXML
    public Button landingPageButton;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        try {
            if (landingPageGroup.getSelectedToggle().equals(customerLogIn)) {
                welcomeText.setText("customer Log in selected");
            } else if (landingPageGroup.getSelectedToggle().equals(customerRegistration)) {
                welcomeText.setText("customer registration selected");
            } else if (landingPageGroup.getSelectedToggle().equals(employeeLogIn)) {
                welcomeText.setText("employee Log in selected");
            }
        }
        catch(RuntimeException re){
            welcomeText.setText("Invalid, please select an option to continue.");
        }

    }
}