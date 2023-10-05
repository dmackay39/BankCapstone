package com.example.bankcapstone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {
    @FXML
    public Button createAccountRequestButton;
    @FXML
    public ComboBox createAccountType;
    @FXML
    public Button createAccountCancelButton;
    @FXML
    public Label statusText;
    @FXML
    public Label accountInfo;

    private AccountType accountType;
    Customer customer = Bank.getInstance().getActiveCustomer();

    public void createAccountClicked(ActionEvent actionEvent) throws IOException {
        //create account - needs added
        Bank.getInstance().createNewAccount(accountType,customer);
        statusText.setText("New account successfully created");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account Application");
        alert.setHeaderText(null);
        alert.setContentText("New Account application submitted");

        alert.showAndWait();

        Stage stage = (Stage) createAccountRequestButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(XYZBankApplication.class.getResource("customer-account.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Customer Account");
        stage.setScene(scene);
    }

    public void createAccountTypeClicked(ActionEvent actionEvent) {
        //need some help getting this working
        if (createAccountType.getValue().toString().equals("Savings Account")) {
            accountInfo.setText("An account to save your pennies for a rainy day. Pays 5% interest");
            accountType = AccountType.SAVINGS;
        } else if (createAccountType.getValue().toString().equals("Checking Account")) {
            accountInfo.setText("A simple current account for your day-to-day spending");
            accountType = AccountType.CHECKING;
        } else if (createAccountType.getValue().toString().equals("CD Account - 1 year fixed term")) {
            accountInfo.setText("A 1 year fixed term savings account. Pays 7% interest");
            accountType = AccountType.CD;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createAccountType.getItems().add("Savings Account");
        createAccountType.getItems().add("Checking Account");
        createAccountType.getItems().add("CD Account - 1 year fixed term");
    }

    public void createAccountCancelClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) createAccountCancelButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customer-account.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Your Account");
        stage.setScene(scene);
    }
}
