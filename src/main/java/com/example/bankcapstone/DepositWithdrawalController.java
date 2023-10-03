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

    public void depositWithdrawalClicked(ActionEvent actionEvent) {
        try {
            double moneyToDW = 0;
            moneyToDW += Integer.parseInt(depositWithdrawalPounds.getText().trim());
            moneyToDW += (Integer.parseInt(depositWithdrawalPennies.getText().trim())) / 100.0;

            Integer accountNumberToDW = (Integer) depositWithdrawalAccountChoice.getValue();
            Account accountToDW = Bank.getInstance().getCustomerHashMap().get("bobby.ayvazov@email.com").getAccountHashMap().get(accountNumberToDW);
            if (DepositWithdrawal.getSelectedToggle().equals(depositRadioButton)) {
                Bank.getInstance().getCustomerHashMap().get("bobby.ayvazov@email.com").depositOrWithdraw(accountToDW, moneyToDW, "deposit");
            } else if (DepositWithdrawal.getSelectedToggle().equals(withdrawalRadioButton)) {
                Bank.getInstance().getCustomerHashMap().get("bobby.ayvazov@email.com").depositOrWithdraw(accountToDW, moneyToDW, "withdraw");
            }
            Stage stage = (Stage) depositWithdrawalSubmitButton.getScene().getWindow();
            stage.close();
            Stage stage2 = new Stage();
            FXMLLoader fxmlLoader2 = new FXMLLoader(XYZBankApplication.class.getResource("customer-account.fxml"));
            Scene scene2 = new Scene(fxmlLoader2.load(), 650, 650);
            stage2.setTitle("Customer Account");
            stage2.setScene(scene2);
            stage2.show();
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
        List<Account> accounts = Bank.getInstance().getCustomerHashMap().get("bobby.ayvazov@email.com").getAccountList();
        for (int i = 0; i < accounts.size(); i++) {
            depositWithdrawalAccountChoice.getItems().add(accounts.get(i).getAccountNumber());
        }
    }
}
