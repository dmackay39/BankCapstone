package com.example.bankcapstone;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
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
    private void onHelloButtonClick() throws IOException {
        String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
        String email = enterEmailField.getText();
        String password = enterPasswordField.getText();

        if (email.matches(emailPattern) && password.length() >= 8) {
            // Valid email and password, navigate to the mainAccountPage
             FXMLLoader loader = new FXMLLoader(getClass().getResource("mainAccountPage.fxml"));
             Parent root = loader.load();
             Scene scene = new Scene(root);
             Stage stage = (Stage) customerSubmitButton.getScene().getWindow();
             stage.setScene(scene);
             stage.show();
        } else {
            System.out.println("Incorrect username or password. Try again!");
        }
    }

    @FXML
    private void onCreateAccountLinkClick() {
        // Navigate to the newAccountRegistrationPage, similar to the mainAccountPage navigation logic
    }

    @FXML
    private void onForgotPasswordLinkClick() {
        // Navigate to the forgotPasswordPage
    }
}

