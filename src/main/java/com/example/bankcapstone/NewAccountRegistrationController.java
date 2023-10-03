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
        private void onSubmitButtonClick() throws IOException {
            String password = enterPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (password.equals(confirmPassword)) {
                // Passwords match, navigate to customer-account.fxml
                Stage stage2 = (Stage) newRegistrationSubmitButton.getScene().getWindow();
                stage2.close();
                Stage stage3 = new Stage();
                FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("customer-account.fxml"));
                Scene scene3 = new Scene(fxmlLoader3.load(), 650, 650);
                stage3.setTitle("Your Account");
                stage3.setScene(scene3);
                stage3.show();
            } else {
                loginText.setText("Passwords do not match. Please try again.");
            }
        }
    }

