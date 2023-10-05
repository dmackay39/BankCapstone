package com.example.bankcapstone;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerForgotPasswordController {

    public Button returnToLoginScreen;

    public void ReturnToLoginScreen(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) returnToLoginScreen.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(XYZBankApplication.class.getResource("customer-login-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 650);
        stage.setTitle("Customer Login");
        stage.setScene(scene);
    }

}
