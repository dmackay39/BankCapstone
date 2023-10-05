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
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class DepositWithdrawalController implements Initializable {
    @FXML
    public ToggleGroup DepositWithdrawal;
    @FXML
    public ComboBox depositWithdrawalAccountChoice;
    @FXML
    public TextField depositWithdrawalPounds;
    @FXML
    public TextField depositWithdrawalPennies;
    @FXML
    public Button depositWithdrawalSubmitButton;
    @FXML
    public RadioButton depositRadioButton;
    @FXML
    public RadioButton withdrawalRadioButton;
    @FXML
    public Button depositWithdrawalCancelButton;
    @FXML
    public Label availableFunds;
    @FXML
    public Label insufficientFundsLabel;
    @FXML
    public Label accountType;

    private Customer customer = Bank.getInstance().getActiveCustomer();
    private List<Account> accounts = customer.getAccountList();
    private List<Loan> loans = customer.getLoanList();

    public void depositWithdrawalClicked(ActionEvent actionEvent) throws IllegalArgumentException {
        try {
            double moneyToDW = 0;

            moneyToDW += Integer.parseInt(depositWithdrawalPounds.getText().trim());
            moneyToDW += (Integer.parseInt(depositWithdrawalPennies.getText().trim())) / 100.0;

            if (moneyToDW > 0) {
                Integer accountNumberToDW = (Integer) depositWithdrawalAccountChoice.getValue();
                Account accountToDW = customer.getAccountHashMap().get(accountNumberToDW);

                if (DepositWithdrawal.getSelectedToggle().equals(depositRadioButton)) {
                    String result = customer.depositOrWithdraw(accountToDW, moneyToDW, "deposit");
                    insufficientFundsLabel.setText(result);
                    System.out.println(result);

                } else if (DepositWithdrawal.getSelectedToggle().equals(withdrawalRadioButton)) {
                    String result = customer.depositOrWithdraw(accountToDW, moneyToDW, "withdraw");
                    System.out.println(result);
                    insufficientFundsLabel.setText(result);
                }
                Stage stage = (Stage) depositWithdrawalSubmitButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(XYZBankApplication.class.getResource("customer-account.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 700, 700);
                stage.setTitle("Customer Account");
                stage.setScene(scene);
            }
            else {
                throw new IllegalArgumentException();
            }
        } catch (Exception e) {
            insufficientFundsLabel.setText("Invalid amount entered");
        }
        ;

        /*catch(RuntimeException re){
            welcomeText.setText("Invalid, please select an option to continue.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Account account : accounts) {
            depositWithdrawalAccountChoice.getItems().add(account.getAccountNumber());
        }
        accountType.setText("Select an account to display the account type and funds.");
    }

    public void depositWithdrawalCancelClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) depositWithdrawalCancelButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customer-account.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Your Account");
        stage.setScene(scene);
    }

    public void withdrawalSelected(ActionEvent actionEvent){
        depositWithdrawalAccountChoice.getItems().clear();
        accounts = accounts.stream()
                .filter((Account account) -> {
                    if (account.getAccountType().equals(AccountType.SAVINGS) || account.getAccountType().equals(AccountType.CHECKING)){
                        return true;
                    }
                    if (account.getAccountType().equals(AccountType.CD)){
                        LocalDate openDate = account.getAccountStartDate();
                        int closeDate = account.getTermLength();
                        if(Math.abs(openDate.until(LocalDate.now()).getYears()) >= account.getTermLength()){
                            return true;
                        }
                    }
                    return false;

                }).toList();

        for (Account account : accounts) {
            depositWithdrawalAccountChoice.getItems().add(account.getAccountNumber());
        }
    }

    public void onAccountChoiceClicked(ActionEvent actionEvent) {
        Integer chosenAccountNumber = (Integer) depositWithdrawalAccountChoice.getValue();
        Account accountChoice = customer.getAccountHashMap().get(chosenAccountNumber);
        accountType.setText("Available Funds in your " + accountChoice.getAccountType().name() + " account: £");
        availableFunds.setText(String.format("%.2f",accountChoice.getBalance()));

    }
}
