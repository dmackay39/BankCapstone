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
            String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
            String firstName = enterFirstNameField.getText();
            String lastName = enterLastNameField.getText();
            String email = enterEmailField.getText();
            String password = enterPasswordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (!email.matches(emailPattern)){
                loginText.setText("Invalid Email. Please try again.");
            } else if (!password.equals(confirmPassword) || password.length() < 8) {
                loginText.setText("Passwords do not match. Please try again.");
            } else {
                Bank.getInstance().createNewCustomer(new Customer(firstName, lastName, email, password));
                Stage stage2 = (Stage) newRegistrationSubmitButton.getScene().getWindow();

                FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("customer-account.fxml"));
                Scene scene3 = new Scene(fxmlLoader3.load(), 650, 650);
                stage2.setTitle("Your Account");
                stage2.setScene(scene3);
            }
        }
    }

