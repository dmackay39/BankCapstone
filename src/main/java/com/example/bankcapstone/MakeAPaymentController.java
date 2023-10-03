package com.example.bankcapstone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MakeAPaymentController implements Initializable {
    @FXML
    public Button paymentTransferSubmitButton;
    @FXML
    public TextField paymentTransferPennies;
    @FXML
    public TextField paymentTransferPounds;
    @FXML
    public ComboBox paymentTransferTo;
    @FXML
    public ComboBox paymentTransferFrom;

    Customer customer = Bank.getInstance().getCustomerHashMap().get("bobby.ayvazov@email.com");
    List<Account> accounts = customer.getAccountList();
    List<Loan> loans = customer.getLoanList();


    public void paymentTransferClicked(ActionEvent actionEvent){
        try {
            double moneyToTransfer = 0;
            moneyToTransfer += Integer.parseInt(paymentTransferPounds.getText().trim());
            moneyToTransfer += (Integer.parseInt(paymentTransferPennies.getText().trim())) / 100.0;

            Integer accountNumberToPay = (Integer) paymentTransferFrom.getValue();
            Integer accountNumberToReceive = (Integer) paymentTransferTo.getValue();

            Account accountToPay = customer.getAccountHashMap().get(accountNumberToPay);
            if (accountNumberToReceive < 1999999) {
                Account accountToReceive = customer.getAccountHashMap().get(accountNumberToReceive);
                customer.payOrTransfer(accountToPay, accountToReceive, moneyToTransfer);
            } else {
                Loan loanToReceive = customer.getLoanHashMap().get(accountNumberToReceive);
                customer.payOrTransfer(accountToPay, loanToReceive, moneyToTransfer);
            }
            Stage stage = (Stage) paymentTransferSubmitButton.getScene().getWindow();
            stage.close();
            Stage stage2 = new Stage();
            FXMLLoader fxmlLoader2 = new FXMLLoader(XYZBankApplication.class.getResource("customer-account.fxml"));
            Scene scene2 = new Scene(fxmlLoader2.load(), 650, 650);
            stage2.setTitle("Customer Account");
            stage2.setScene(scene2);
            stage2.show();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void comboChoiceSelected(ActionEvent actionEvent){
        for (Loan loan: loans){
            paymentTransferTo.getItems().add(loan.getLoanNumber());
        }

        for (Account account : accounts) {
            paymentTransferTo.getItems().remove((Integer)account.getAccountNumber());
        }
        for (Account account : accounts) {
            paymentTransferTo.getItems().add(account.getAccountNumber());
        }
        Integer accountNumberToRemove = (Integer) paymentTransferFrom.getValue();
        paymentTransferTo.getItems().remove(accountNumberToRemove);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Account account : accounts) {
            paymentTransferFrom.getItems().add(account.getAccountNumber());
            paymentTransferTo.getItems().add(account.getAccountNumber());
        }
    }
}
