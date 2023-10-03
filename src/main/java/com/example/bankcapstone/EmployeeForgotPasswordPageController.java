package com.example.bankcapstone;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeForgotPasswordPageController {
    public Button returnToLoginScreen;

    public void ReturnToLoginScreen(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) returnToLoginScreen.getScene().getWindow();
        stage.close();
        Stage stage2 = new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader(XYZBankApplication.class.getResource("employee-login-page.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 650, 650);
        stage2.setTitle("Employee Login");
        stage2.setScene(scene2);
        stage2.show();
    }
}
