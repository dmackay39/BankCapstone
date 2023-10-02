package com.example.bankcapstone;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

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
                Stage stage = (Stage) landingPageButton.getScene().getWindow();
                stage.close();
                Stage stage2 = new Stage();
                FXMLLoader fxmlLoader2 = new FXMLLoader(XYZBankApplication.class.getResource("customer-login.fxml"));
                Scene scene2 = new Scene(fxmlLoader2.load(), 500, 500);
                stage2.setTitle("Customer Login");
                stage2.setScene(scene2);
                stage2.show();
            } else if (landingPageGroup.getSelectedToggle().equals(customerRegistration)) {
                welcomeText.setText("customer registration selected");
            } else if (landingPageGroup.getSelectedToggle().equals(employeeLogIn)) {
                welcomeText.setText("employee Log in selected");
            }
        }
        catch(RuntimeException re){
            welcomeText.setText("Invalid, please select an option to continue.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}