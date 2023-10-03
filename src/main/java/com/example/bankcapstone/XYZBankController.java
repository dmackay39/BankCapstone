package com.example.bankcapstone;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class XYZBankController implements Initializable {
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
    protected void onHelloButtonClick(){
        try {
            if (landingPageGroup.getSelectedToggle().equals(customerLogIn)) {
                Stage stage = (Stage) landingPageButton.getScene().getWindow();
                stage.close();
                Stage stage2 = new Stage();
                FXMLLoader fxmlLoader2 = new FXMLLoader(XYZBankApplication.class.getResource("customer-login.fxml"));
                Scene scene2 = new Scene(fxmlLoader2.load(), 650, 650);
                stage2.setTitle("Customer Login");
                stage2.setScene(scene2);
                stage2.show();
            } else if (landingPageGroup.getSelectedToggle().equals(customerRegistration)) {
                Stage stage = (Stage) landingPageButton.getScene().getWindow();
                stage.close();
                Stage stage2 = new Stage();
                FXMLLoader fxmlLoader2 = new FXMLLoader(XYZBankApplication.class.getResource("new-account-registration.fxml"));
                Scene scene2 = new Scene(fxmlLoader2.load(), 650, 650);
                stage2.setTitle("Customer Registration");
                stage2.setScene(scene2);
                stage2.show();
            } else if (landingPageGroup.getSelectedToggle().equals(employeeLogIn)) {
                welcomeText.setText("employee Log in selected");
                /*Stage stage = (Stage) landingPageButton.getScene().getWindow();
                stage.close();
                Stage stage2 = new Stage();
                FXMLLoader fxmlLoader2 = new FXMLLoader(XYZBankApplication.class.getResource("employee-login.fxml"));
                Scene scene2 = new Scene(fxmlLoader2.load(), 650, 650);
                stage2.setTitle("Employee Login");
                stage2.setScene(scene2);
                stage2.show();*/
            }
        }
        catch(RuntimeException re){
            welcomeText.setText("Invalid, please select an option to continue.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialise the bank when we run the application
        Bank bank = Bank.getInstance();
        bank.populateBankDatabase();

    }
}