package com.example.bankcapstone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class XYZBankApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        Bank bank = Bank.getInstance();
        bank.populateBankDatabase();


        FXMLLoader fxmlLoader = new FXMLLoader(XYZBankApplication.class.getResource("landing-page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("XYZ Bank - landing page");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}