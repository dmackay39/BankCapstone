package com.example.bankcapstone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class PaymentOverdrawController {
    @FXML
    public Button paymentOverdrawOverrideButton;

    @FXML
    public Button paymentOverdrawCancelButton;


    public void onPaymentOverdrawCancelButtonClick(ActionEvent actionEvent) throws IOException {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setHeaderText(null);
        confirmationDialog.setContentText("Are you sure you want to cancel?");

        Optional<ButtonType> result = confirmationDialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) paymentOverdrawCancelButton.getScene().getWindow();
            stage.close();

            Stage stage2 = new Stage();
            FXMLLoader fxmlLoader2 = new FXMLLoader(XYZBankApplication.class.getResource("make-a-payment.fxml"));
            Scene scene2 = new Scene(fxmlLoader2.load(), 700, 700);
            stage2.setTitle("Make a Payment");
            stage2.setScene(scene2);
            stage2.show();
        }
    }

    public void onPaymentOverdrawOverrideButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) paymentOverdrawOverrideButton.getScene().getWindow();
        stage.close();

        // Open the "manager-credentials.fxml" screen
        Stage stage2 = new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader(XYZBankApplication.class.getResource("manager-credentials.fxml"));
        Scene scene2 = null;
        try {
            scene2 = new Scene(fxmlLoader2.load(), 700, 700);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage2.setTitle("Manager Credentials");
        stage2.setScene(scene2);
        stage2.show();
    }
}


