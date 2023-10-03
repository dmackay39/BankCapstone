package com.example.bankcapstone;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

    public class NewAccountRegistrationController {

        @FXML
        private TextField enterFirstNameField;
        @FXML
        private TextField enterLastNameField;
        @FXML
        private TextField enterEmailField;
        @FXML
        private PasswordField enterPasswordField;
        @FXML
        private PasswordField confirmPasswordField;
        @FXML
        private Button newRegistrationSubmitButton;
        @FXML
        private Label loginText;

        @FXML
        private void onSubmitButtonClick() {
            String password = enterPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (password.equals(confirmPassword)) {
                // Passwords match, navigate to customer-account.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("customer-account.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(root);
                 Stage stage = (Stage) newRegistrationSubmitButton.getScene().getWindow();
                 stage.setScene(scene);
                 stage.show();
            } else {
                loginText.setText("Passwords do not match. Please try again.");
            }
        }
    }

