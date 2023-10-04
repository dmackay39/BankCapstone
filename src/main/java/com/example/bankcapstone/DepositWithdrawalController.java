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

    Customer customer = Bank.getInstance().getCustomerHashMap().get("bobby.ayvazov@email.com");
    List<Account> accounts = customer.getAccountList();
    List<Loan> loans = customer.getLoanList();
    public void depositWithdrawalClicked(ActionEvent actionEvent) {
        try {
            double moneyToDW = 0;
            moneyToDW += Integer.parseInt(depositWithdrawalPounds.getText().trim());
            moneyToDW += (Integer.parseInt(depositWithdrawalPennies.getText().trim())) / 100.0;

            Integer accountNumberToDW = (Integer) depositWithdrawalAccountChoice.getValue();
            Account accountToDW = customer.getAccountHashMap().get(accountNumberToDW);
            if (DepositWithdrawal.getSelectedToggle().equals(depositRadioButton)) {
                String result = customer.depositOrWithdraw(accountToDW, moneyToDW, "deposit");
                System.out.println(result);
            } else if (DepositWithdrawal.getSelectedToggle().equals(withdrawalRadioButton)) {
                String result = customer.depositOrWithdraw(accountToDW, moneyToDW, "withdraw");
                System.out.println(result);

            }
            Stage stage = (Stage) depositWithdrawalSubmitButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(XYZBankApplication.class.getResource("customer-account.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 650, 650);
            stage.setTitle("Customer Account");
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
    }
}
