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
    public Button employeeBackButton;
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
    private void CallEmployeeITSupportClick() throws IOException {
        // Navigate to IT-support-info-page.fxml
        Stage stage = (Stage) ITSupportClickField.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IT-support-info-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 650);
        stage.setTitle("IT Support");
        stage.setScene(scene);
    }

    @FXML
    private void onForgotPasswordLinkClick() throws IOException {
        // Navigate to forgot-password-page.fxml
        Stage stage = (Stage) forgotPasswordField.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("forgot-password-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 650);
        stage.setTitle("Forgot Password");
        stage.setScene(scene);
    }

    @FXML
    private void employeeSubmitButtonClick() throws IOException {
        // Navigate to employee-account-page.fxml
        Stage stage = (Stage) employeeSubmitButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employee-account-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 650);
        stage.setTitle("Your Account");
        stage.setScene(scene);
    }

    public void employeeBackButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) employeeBackButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("landing-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 650);
        stage.setTitle("XYZ Bank - Landing Page");
        stage.setScene(scene);
    }
}

