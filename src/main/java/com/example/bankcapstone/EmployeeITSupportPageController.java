package com.example.bankcapstone;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeITSupportPageController {
    public Button returnToLoginScreen;

    public void ReturnToLoginScreen(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) returnToLoginScreen.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(XYZBankApplication.class.getResource("employee-login-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Employee Login");
        stage.setScene(scene);
    }
}
