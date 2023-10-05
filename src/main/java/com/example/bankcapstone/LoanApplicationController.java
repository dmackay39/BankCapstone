package com.example.bankcapstone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoanApplicationController implements Initializable {
    @FXML
    public Button loanApplicationSubmitButton;
    @FXML
    public TextField loanApplicationPennies;
    @FXML
    public TextField loanApplicationPounds;
    @FXML
    public ComboBox loanChoicePicker;
    @FXML
    public ComboBox loanApplicationAccountPicker;
    public Button loanApplicationCancelButton;
    public Label loanLabel;

    Customer customer = Bank.getInstance().getActiveCustomer();
    List<Account> accounts = customer.getAccountList();

    private LoanTypeEnum loanType;
    private int term = 0;
    private int accountChoice;


    @FXML
    public void loanApplicationSubmitClicked(ActionEvent actionEvent) throws IllegalArgumentException {

        try {
            String lApoundsString = loanApplicationPounds.getText().trim();
            String lApenniesString = loanApplicationPennies.getText().trim();
            double moneyToLoan = Integer.parseInt(lApoundsString) + Integer.parseInt(lApenniesString) / 100.0;
            if (moneyToLoan >0) {
                String loanApproval;
                if (term == 0) {
                    loanApproval = Bank.getInstance().approveCustomerLoan(customer.getEmail(), moneyToLoan, loanType, accountChoice);
                    loanLabel.setText(loanApproval);
                } else {
                    loanApproval = Bank.getInstance().approveCustomerLoan(customer.getEmail(), moneyToLoan, loanType, term, accountChoice);
                    loanLabel.setText(loanApproval);
                }
                // Put code here to submit the loan application with the selected loan type and amount in pounds and pennies

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Loan Application");
                alert.setHeaderText(null);
                alert.setContentText("Loan application submitted");

                alert.showAndWait();

                Stage stage = (Stage) loanApplicationSubmitButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(XYZBankApplication.class.getResource("customer-account.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 700, 700);
                stage.setTitle("Customer Account");
                stage.setScene(scene);
            } else{
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            loanLabel.setText("Invalid amount entered");
        }
    }

    @FXML
    public void onLoanTypePicker(ActionEvent actionEvent) {
        String selectedLoanType = loanChoicePicker.getValue().toString();

        if (selectedLoanType != null) {
            if (selectedLoanType.equals("Home Loan - 15 years")) {
                // 15-year home loan
                loanType = LoanTypeEnum.HOME;
                term = 15;
            } else if (selectedLoanType.equals("Home Loan - 20 years")) {
                // 20-year home loan
                loanType = LoanTypeEnum.HOME;
                term = 20;
            } else if (selectedLoanType.equals("Home Loan - 30 years")) {
                // 30-year home loan
                loanType = LoanTypeEnum.HOME;
                term = 30;
            } else if (selectedLoanType.equals("Car Loan - 3 years")) {
                // 3-year car loan
                loanType = LoanTypeEnum.CAR;
                term = 3;
            } else if (selectedLoanType.equals("Car Loan - 4 years")) {
                // 4-year car loan
                loanType = LoanTypeEnum.CAR;
                term = 4;
            } else if (selectedLoanType.equals("Car Loan - 5 years")) {
                // 5-year car loan
                loanType = LoanTypeEnum.CAR;
                term = 5;
            } else if (selectedLoanType.equals("Personal Loan")) {
                // Personal loan
                loanType = LoanTypeEnum.PERSONAL;
                term = 0;
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loanChoicePicker.getItems().add("Home Loan - 15 years");
        loanChoicePicker.getItems().add("Home Loan - 20 years");
        loanChoicePicker.getItems().add("Home Loan - 30 years");
        loanChoicePicker.getItems().add("Car Loan - 3 years");
        loanChoicePicker.getItems().add("Car Loan - 4 years");
        loanChoicePicker.getItems().add("Car Loan - 5 years");
        loanChoicePicker.getItems().add("Personal Loan");

        for (Account account:accounts){
            loanApplicationAccountPicker.getItems().add(account.getAccountNumber());
        }
    }


    public void onAccountTypePicker(ActionEvent actionEvent) {
        accountChoice = (Integer) loanApplicationAccountPicker.getValue();
    }

    public void loanApplicationCancelClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) loanApplicationCancelButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customer-account.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Your Account");
        stage.setScene(scene);
    }
}
