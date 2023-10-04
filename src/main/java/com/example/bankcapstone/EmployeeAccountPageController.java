package com.example.bankcapstone;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeAccountPageController {
    public Button employeeLogoutButton;

    public void EmployeeLogoutClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) employeeLogoutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("landing-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 650);
        stage.setTitle("XYZ Bank - Landing Page");
        stage.setScene(scene);
    }
}