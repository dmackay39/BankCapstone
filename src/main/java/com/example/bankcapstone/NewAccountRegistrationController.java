package com.example.bankcapstone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.io.IOException;

    public class NewAccountRegistrationController {

        public Button newRegistrationCancelButton;
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

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                loginText.setText("Please complete all fields. ");
            } else if (!email.matches(emailPattern)) {
                loginText.setText("Invalid Email. Please try again.");
            } else if (Bank.getInstance().getCustomerHashMap().containsKey(email)) {
                loginText.setText("Customer account already exists.");
            } else if (password.length() < 8) {
                loginText.setText("Password should be at least 8 characters long.");
            } else if (!password.equals(confirmPassword)) {
                loginText.setText("Confirm Password does not match Password. Please re-enter passwords.");
            } else {
                Customer newCustomer = new Customer(firstName, lastName, email, password);
                Bank.getInstance().createNewCustomer(newCustomer);
                Bank.getInstance().setActiveCustomer(newCustomer);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("New Account Created");
                alert.setHeaderText(null);
                alert.setContentText("Welcome to XYZ - thanks for choosing to bank with us.\n\nYour new account has been successfully created!");

                alert.showAndWait();
                Stage stage = (Stage) newRegistrationSubmitButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customer-account.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 700, 700);
                stage.setTitle("Your Account");
                stage.setScene(scene);
            }
        }

        public void onCancelButtonClick(ActionEvent actionEvent) throws IOException {
            Stage stage = (Stage) newRegistrationCancelButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("landing-page.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 700);
            stage.setTitle("XYZ Bank - Landing Page");
            stage.setScene(scene);
        }
    }

