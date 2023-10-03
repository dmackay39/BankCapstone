package com.example.bankcapstone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeLoginPageController {
    @FXML
    private TextField enterEmailField;
    @FXML
    private PasswordField enterPasswordField;
    @FXML
    private Button employeeSubmitButton;
    @FXML
    private Label loginText;
    @FXML
    private Hyperlink ITSupportClickField;
    @FXML
    private Hyperlink forgotPasswordField;

    @FXML
    private void CallEmployeeITSupportClick() {
        // Navigate to IT-support-info-page.fxml
         FXMLLoader loader = new FXMLLoader(getClass().getResource("IT-support-info-page.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
         Stage stage = (Stage) ITSupportClickField.getScene().getWindow();
         stage.setScene(scene);
         stage.show();
    }

    @FXML
    private void onForgotPasswordLinkClick() {
        // Navigate to forgot-password-page.fxml
         FXMLLoader loader = new FXMLLoader(getClass().getResource("forgot-password-page.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
         Stage stage = (Stage) forgotPasswordField.getScene().getWindow();
         stage.setScene(scene);
         stage.show();
    }

    @FXML
    private void employeeSubmitButtonClick() {
        // Navigate to employee-account-page.fxml
         FXMLLoader loader = new FXMLLoader(getClass().getResource("employee-account-page.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
         Stage stage = (Stage) employeeSubmitButton.getScene().getWindow();
         stage.setScene(scene);
         stage.show();
    }
}

