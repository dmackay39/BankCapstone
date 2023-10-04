package com.example.bankcapstone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {
    @FXML
    public Button createAccountRequestButton;
    @FXML
    public TextField createAccountInfo;
    @FXML
    public ComboBox createAccountType;
    public Button createAccountCancelButton;

    @FXML
    public void createAccountClicked(ActionEvent actionEvent) throws IOException {
        //create account - needs added
        Stage stage = (Stage) createAccountRequestButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(XYZBankApplication.class.getResource("customer-account.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 650);
        stage.setTitle("Customer Account");
        stage.setScene(scene);
    }

    public void createAccountTypeClicked(ActionEvent actionEvent) {
        //need some help getting this working
        if (createAccountType.getValue().toString().equals("Savings Account")) {
            createAccountInfo.setText("An account to save your pennies for a rainy day.");
        } else if (createAccountType.getValue().toString().equals("Checking Account")) {
            createAccountInfo.setText("An simple current account for your day-to-day spending");
        } else if (createAccountType.getValue().toString().equals("CD Account - 1 year fixed term")) {
            createAccountInfo.setText("A 1 year fixed term savings account");
        } else if (createAccountType.getValue().toString().equals("CD Account - 2 year fixed term")) {
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

    public void createAccountCancelClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) createAccountCancelButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customer-account.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 650);
        stage.setTitle("Your Account");
        stage.setScene(scene);
    }
}
