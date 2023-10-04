package com.example.bankcapstone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerCredentialsController {

    @FXML
    public Button managerOverrideCancelButton;
    @FXML
    public TextField enterManagerEmailField;
    @FXML
    public PasswordField enterManagerPasswordField;


    public void onCancelButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) managerOverrideCancelButton.getScene().getWindow();
        stage.close();

        Stage previousStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(XYZBankApplication.class.getResource("payment-overdraw-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 650);
        previousStage.setTitle("Payment Overdraw");
        previousStage.setScene(scene);
        previousStage.show();
    }

    public void onManagerOverrideButtonClick(ActionEvent actionEvent) {
        String managerEmail = enterManagerEmailField.getText();
        String managerPassword = enterManagerPasswordField.getText();


        if (Bank.getInstance().authenticateManager(managerEmail, managerPassword)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Payment Override Completed");
            alert.setHeaderText(null);
            alert.setContentText("Payment Override completed");
            alert.showAndWait();

            Stage stage = (Stage) managerOverrideCancelButton.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Authentication Failed");
            alert.setHeaderText(null);
            alert.setContentText("Authentication Failed");
            alert.showAndWait();

            Stage stage = (Stage) managerOverrideCancelButton.getScene().getWindow();
            stage.close();
        }


    }
}
