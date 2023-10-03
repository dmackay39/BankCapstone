package com.example.bankcapstone;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {
    public Button createAccountRequestButton;
    public TextArea createAccountInfo;
    public ComboBox createAccountType;

    public void createAccountClicked(ActionEvent actionEvent) throws IOException {
        //create account - needs added
        Stage stage = (Stage) createAccountRequestButton.getScene().getWindow();
        stage.close();
        Stage stage2 = new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader(XYZBankApplication.class.getResource("customer-account.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 650, 650);
        stage2.setTitle("Customer Account");
        stage2.setScene(scene2);
        stage2.show();
    }

    public void createAccountInfoClicked(ActionEvent actionEvent) {
        if(createAccountType.equals("Savings Account")){
            createAccountInfo.setText("An account to save your pennies for a rainy day.");
        }
        else if(createAccountType.equals("Checking Account")){
            createAccountInfo.setText("An simple current account for your day-to-day spending");
        }
        else if(createAccountType.equals("CD Account - 1 year fixed term")){
            createAccountInfo.setText("A 1 year fixed term savings account");
        }
        else if(createAccountType.equals("CD Account - 2 year fixed term")){
            createAccountInfo.setText("A 2 year fixed term savings account");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        createAccountType.getItems().add("Savings Account");
        createAccountType.getItems().add("Checking Account");
        createAccountType.getItems().add("CD Account - 1 year fixed term");
        createAccountType.getItems().add("CD Account - 2 year fixed term");

    }
}
