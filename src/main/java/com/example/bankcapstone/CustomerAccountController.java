package com.example.bankcapstone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerAccountController implements Initializable {

    @FXML
    public Button makeAPayment;
    @FXML
    public Button applyForALoan;
    @FXML
    public Button createAccount;
    @FXML
    public Button depositWithdrawal;
    @FXML
    public VBox accountsScrollVBox;
    @FXML
    public VBox LoansScrollVBox;



    @FXML
    public void MakeAPaymentClicked(ActionEvent actionEvent) throws IOException {
        Stage stage3 = (Stage) makeAPayment.getScene().getWindow();
        stage3.close();
        Stage stage4 = new Stage();
        FXMLLoader fxmlLoader4 = new FXMLLoader(getClass().getResource("make-a-payment.fxml"));
        Scene scene4 = new Scene(fxmlLoader4.load(), 650, 650);
        stage4.setTitle("Make A Payment");
        stage4.setScene(scene4);
        stage4.show();
    }

    @FXML
    public void ApplyForALoanClicked(ActionEvent actionEvent) throws IOException {
        Stage stage3 = (Stage) applyForALoan.getScene().getWindow();
        stage3.close();
        Stage stage5 = new Stage();
        FXMLLoader fxmlLoader5 = new FXMLLoader(getClass().getResource("loan-application.fxml"));
        Scene scene5 = new Scene(fxmlLoader5.load(), 650, 650);
        stage5.setTitle("Loan Application");
        stage5.setScene(scene5);
        stage5.show();
    }

    @FXML
    public void CreateAccountClicked(ActionEvent actionEvent) throws IOException {
        Stage stage3 = (Stage) createAccount.getScene().getWindow();
        stage3.close();
        Stage stage6 = new Stage();
        FXMLLoader fxmlLoader6 = new FXMLLoader(getClass().getResource("create-account.fxml"));
        Scene scene6 = new Scene(fxmlLoader6.load(), 650, 650);
        stage6.setTitle("Create Account");
        stage6.setScene(scene6);
        stage6.show();
    }

    @FXML
    public void DepositOrWithdrawalClicked(ActionEvent actionEvent) throws IOException {
        Stage stage3 = (Stage) depositWithdrawal.getScene().getWindow();
        stage3.close();
        Stage stage7 = new Stage();
        FXMLLoader fxmlLoader7 = new FXMLLoader(getClass().getResource("deposit-withdrawal.fxml"));
        Scene scene7 = new Scene(fxmlLoader7.load(), 650, 650);
        stage7.setTitle("Your Account");
        stage7.setScene(scene7);
        stage7.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            Bank bank = Bank.getInstance();
            List<Customer> customers
            VBox account = new VBox();
            Label accountName = new Label("RAINY DAY");
            Label accountNumber = new Label("12345");
            TextField amountInAccount = new TextField("£50.00");
            account.getChildren().addAll(accountName,accountNumber,amountInAccount);



    }
}
