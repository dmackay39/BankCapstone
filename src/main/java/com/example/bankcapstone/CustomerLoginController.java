package com.example.bankcapstone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerLoginController {

    @FXML
    public Button customerSubmitButton;
    @FXML
    public TextField enterEmailField;
    @FXML
    public TextField enterPasswordField;
    @FXML
    public Hyperlink forgotPasswordField;
    @FXML
    public Hyperlink createAccountField;
    @FXML
    public Label loginText;
    @FXML
    public Button customerLoginCancelButton;

    @FXML
    private void onHelloButtonClick() throws IOException {
        String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
        String email = enterEmailField.getText();
        String password = enterPasswordField.getText();

        if (email.matches(emailPattern) && Bank.getInstance().getCustomerHashMap().containsKey(email)){
            Customer customer = Bank.getInstance().getCustomerHashMap().get(email);
            if (customer.getPassword().equals(password)){
                Bank.getInstance().setActiveCustomer(customer);
                Stage stage = (Stage) customerSubmitButton.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customer-account.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 700, 700);
                stage.setTitle("Your Account");
                stage.setScene(scene);
            } else {
                loginText.setText("Incorrect username or password. Try again!");
            }
        } else {
            loginText.setText("Incorrect username or password. Try again!");
        }
    }

    @FXML
    private void onCreateAccountLinkClick() throws IOException {
        Stage stage = (Stage) createAccountField.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(XYZBankApplication.class.getResource("new-account-registration.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Customer Registration");
        stage.setScene(scene);
    }

    @FXML
    private void onForgotPasswordLinkClick() throws IOException {
        Stage stage = (Stage) forgotPasswordField.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(XYZBankApplication.class.getResource("forgot-password-customer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Customer Forgot Password");
        stage.setScene(scene);
    }

    public void onCancelButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) customerLoginCancelButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("landing-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("XYZ Bank - Landing Page");
        stage.setScene(scene);
    }
}

