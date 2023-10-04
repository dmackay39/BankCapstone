package com.example.bankcapstone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerAccountController implements Initializable {

    @FXML
    public Button makeAPayment;
    @FXML
    public Button applyForALoan;
    @FXML
    public Button createAccount;
    @FXML
    public Button depositWithdrawal;
    @FXML
    public VBox accountsScrollVBox;
    @FXML
    public VBox LoansScrollVBox;



    @FXML
    public void MakeAPaymentClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) makeAPayment.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("make-a-payment.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 650);
        stage.setTitle("Make A Payment");
        stage.setScene(scene);
    }

    @FXML
    public void ApplyForALoanClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) applyForALoan.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loan-application.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 650);
        stage.setTitle("Loan Application");
        stage.setScene(scene);
    }

    @FXML
    public void CreateAccountClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) createAccount.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("create-account.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 650);
        stage.setTitle("Create Account");
        stage.setScene(scene);
    }

    @FXML
    public void DepositOrWithdrawalClicked(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) depositWithdrawal.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("deposit-withdrawal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 650);
        stage.setTitle("Your Account");
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Account> accounts = Bank.getInstance().getCustomerHashMap().get("bobby.ayvazov@email.com").getAccountList();
        for (int i = 0; i < accounts.size(); i++) {
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(0,10,0,10));
            Label accountName = new Label(accounts.get(i).getAccountType().toString());
            accountName.setFont(Font.font("Verdana", FontWeight.BOLD,20));
            grid.add(accountName,0,0);
            Label accountNumber = new Label("Account Number: " + Integer.toString(accounts.get(i).getAccountNumber()));
            accountNumber.setFont(Font.font("Verdana", FontWeight.NORMAL,10));
            grid.add(accountNumber,0,1);
            Label amountInAccount = new Label("£" + String.format("%.2f",accounts.get(i).getBalance()));
            amountInAccount.setFont(Font.font("Verdana", FontWeight.BOLD,20));
            grid.add(amountInAccount,20,0);
            grid.setStyle("-fx-padding: 10;" +
                    "-fx-border-style: solid inside;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-insets: 5;" +
                    "-fx-border-radius: 5;" +
                    "-fx-border-color: #0E1E2B;");
            accountsScrollVBox.getChildren().add(grid);
        }

        List<Loan> loans = Bank.getInstance().getCustomerHashMap().get("bobby.ayvazov@email.com").getLoanList();
        for (int i = 0; i < loans.size(); i++) {
            GridPane gridLoans = new GridPane();
            gridLoans.setHgap(10);
            gridLoans.setVgap(10);
            gridLoans.setPadding(new Insets(0,10,0,10));
            Label loanName = new Label(loans.get(i).getLoanType().toString());
            loanName.setFont(Font.font("Verdana", FontWeight.BOLD,20));
            gridLoans.add(loanName,0,0);
            Label loanNumber = new Label("Loan Number: " + Integer.toString(loans.get(i).getLoanNumber()));
            loanNumber.setFont(Font.font("Verdana", FontWeight.NORMAL,10));
            gridLoans.add(loanNumber,0,1);
            Label amountOfLoanUnpaid = new Label("- £" + String.format("%.2f",(loans.get(i).getBalance()*-1)));
            amountOfLoanUnpaid.setFont(Font.font("Verdana", FontWeight.BOLD,20));
            gridLoans.add(amountOfLoanUnpaid,20,0);
            gridLoans.setStyle("-fx-padding: 10;" +
                    "-fx-border-style: solid inside;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-insets: 5;" +
                    "-fx-border-radius: 5;" +
                    "-fx-border-color: #0E1E2B;");
            LoansScrollVBox.getChildren().add(gridLoans);
        }
    }
}
