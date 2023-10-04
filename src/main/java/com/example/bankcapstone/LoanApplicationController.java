package com.example.bankcapstone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
    public void loanApplicationSubmitClicked(ActionEvent actionEvent) throws IOException {
        String selectedLoanType = loanChoicePicker.getValue().toString();

        String lApoundsString = loanApplicationPounds.getText();
        String lApenniesString = loanApplicationPennies.getText();

        // Put code here to submit the loan application with the selected loan type and amount in pounds and pennies

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Loan Application");
        alert.setHeaderText(null);
        alert.setContentText("Loan application submitted");

        alert.showAndWait();

        Stage stage = (Stage) loanApplicationSubmitButton.getScene().getWindow();
        stage.close();

        Stage stage2 = new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader(XYZBankApplication.class.getResource("customer-account.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 650, 650);
        stage2.setTitle("Customer Account");
        stage2.setScene(scene2);
        stage2.show();
    }

    @FXML
    public void onLoanTypePicker(ActionEvent actionEvent) {
        String selectedLoanType = loanChoicePicker.getValue().toString();

        if (selectedLoanType != null) {
            if (selectedLoanType.equals("Home Loan - 15 years")) {
                // 15-year home loan
            } else if (selectedLoanType.equals("Home Loan - 20 years")) {
                // 20-year home loan
            } else if (selectedLoanType.equals("Home Loan - 30 years")) {
                // 30-year home loan
            } else if (selectedLoanType.equals("Car Loan - 3 years")) {
                // 3-year car loan
            } else if (selectedLoanType.equals("Car Loan - 4 years")) {
                // 4-year car loan
            } else if (selectedLoanType.equals("Car Loan - 5 years")) {
                // 5-year car loan
            } else if (selectedLoanType.equals("Personal Loan")) {
                // Personal loan
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
    }


}
