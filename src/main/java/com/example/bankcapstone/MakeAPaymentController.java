package com.example.bankcapstone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
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

    @FXML
    public Label accountFromFundsLabel;
    @FXML
    public Label accountToFundsLabel;
    public Button paymentTransferCancelButton;
    public Label availableFunds;
    public Label insufficientFundsLabel;

    Customer customer = Bank.getInstance().getActiveCustomer();
    List<Account> accounts = customer.getAccountList();

    List<Loan> loans ;
    List<Account> filteredAccounts = new ArrayList<>();



    public void paymentTransferClicked(ActionEvent actionEvent) throws IllegalArgumentException {
        try {
            double moneyToTransfer = 0;
            moneyToTransfer += Integer.parseInt(paymentTransferPounds.getText().trim());
            moneyToTransfer += (Integer.parseInt(paymentTransferPennies.getText().trim())) / 100.0;

            if (moneyToTransfer > 0) {
                Integer accountNumberToPay = (Integer) paymentTransferFrom.getValue();
                Account accountToPay = customer.getAccountHashMap().get(accountNumberToPay);
                if (paymentTransferTo.getValue().equals("Bills")) {
                    String result = customer.payOrTransfer(accountToPay, moneyToTransfer);
                    insufficientFundsLabel.setText(result);
                    if (result.equals("This payment will overdraw your account and requires bank manager approval")) {

                        //bring up manager authenticate window
                        Stage stage2 = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("manager-credentials.fxml"));
                        Scene scene2 = new Scene(fxmlLoader.load(), 400, 400);
                        stage2.setTitle("Manager Authentication");
                        stage2.setScene(scene2);
                        stage2.showAndWait();

                        insufficientFundsLabel.setText(Bank.getInstance().overridePayment(accountToPay, moneyToTransfer));

                    }
                } else {
                    Integer accountNumberToReceive = (Integer) paymentTransferTo.getValue();

                    if (accountNumberToReceive < 1999999) {
                        Account accountToReceive = customer.getAccountHashMap().get(accountNumberToReceive);
                        String result = customer.payOrTransfer(accountToPay, accountToReceive, moneyToTransfer);
                        insufficientFundsLabel.setText(result);
                        System.out.println(result);
                    } else {
                        Loan loanToReceive = customer.getLoanHashMap().get(accountNumberToReceive);
                        String result = customer.payOrTransfer(accountToPay, loanToReceive, moneyToTransfer);
                        insufficientFundsLabel.setText(result);
                        System.out.println(result);
                    }
                }
                reInitialize();
            } else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e){
            insufficientFundsLabel.setText("Invalid amount entered");
        }


    }

    public void comboChoiceSelected(ActionEvent actionEvent) {
        paymentTransferTo.getItems().clear();
        for (Account account : accounts) {
           paymentTransferTo.getItems().remove((Integer) account.getAccountNumber());
       }
       for (Account account : accounts) {
            paymentTransferTo.getItems().add(account.getAccountNumber());
        }
        loans = customer.getLoanList();
        for (Loan loan : loans)
            paymentTransferTo.getItems().add(loan.getLoanNumber());

        paymentTransferTo.getItems().add("Bills");

        Integer accountNumberToRemove = (Integer) paymentTransferFrom.getValue();
        paymentTransferTo.getItems().remove(accountNumberToRemove);

        if (paymentTransferFrom.getValue() == null) {
            accountFromFundsLabel.setText("");
        } else {
            Account accountFrom = customer.getAccountHashMap().get((Integer) paymentTransferFrom.getValue());
            accountFromFundsLabel.setText("Available Funds: £" + accountFrom.getBalance());
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        filteredAccounts = accounts.stream().filter((Account account) -> {
            if (account.getAccountType().equals(AccountType.SAVINGS) || account.getAccountType().equals(AccountType.CHECKING)) {
                return true;
            }
            if (account.getAccountType().equals(AccountType.CD)) {
                LocalDate openDate = account.getAccountStartDate();
                int closeDate = account.getTermLength();
                if (Math.abs(openDate.until(LocalDate.now()).getYears()) >= account.getTermLength()) {
                    return true;
                }
            }
            return false;
        }).toList();


        reInitialize();
    }

    public void reInitialize() {
        paymentTransferTo.getItems().clear();
        paymentTransferFrom.getItems().clear();

        for (Account account : filteredAccounts)
            paymentTransferFrom.getItems().add(account.getAccountNumber());

        accountFromFundsLabel.setText("Select an account to display funds.");
        accountToFundsLabel.setText("");

    }


    public void paymentTransferCancelClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) paymentTransferCancelButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customer-account.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Your Account");
        stage.setScene(scene);
    }

    public void paymentTransferToClicked(ActionEvent actionEvent) {
        if (paymentTransferTo.getValue() == null) {
            accountToFundsLabel.setText("");
        } else if (paymentTransferTo.getValue().toString().equals("Bills")) {
            accountToFundsLabel.setText("");
        } else {
            Account accountTo = customer.getAccountHashMap().get((Integer) paymentTransferTo.getValue());
            Loan loanTo = customer.getLoanHashMap().get((Integer) paymentTransferTo.getValue());
            if (accountTo == null) {
                accountToFundsLabel.setText("Balance: - £" + ((-1) * loanTo.getBalance()));
            } else {
                accountToFundsLabel.setText("Balance: £" + (accountTo.getBalance()));
            }
        }
    }
}
