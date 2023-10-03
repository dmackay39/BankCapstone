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
        Stage stage3 = (Stage) makeAPayment.getScene().getWindow();
        stage3.close();
        Stage stage4 = new Stage();
        FXMLLoader fxmlLoader4 = new FXMLLoader(getClass().getResource("make-a-payment.fxml"));
        Scene scene4 = new Scene(fxmlLoader4.load(), 650, 650);
        stage4.setTitle("Make A Payment");
        stage4.setScene(scene4);
        stage4.show();
    }

    @FXML
    public void ApplyForALoanClicked(ActionEvent actionEvent) throws IOException {
        Stage stage3 = (Stage) applyForALoan.getScene().getWindow();
        stage3.close();
        Stage stage5 = new Stage();
        FXMLLoader fxmlLoader5 = new FXMLLoader(getClass().getResource("loan-application.fxml"));
        Scene scene5 = new Scene(fxmlLoader5.load(), 650, 650);
        stage5.setTitle("Loan Application");
        stage5.setScene(scene5);
        stage5.show();
    }

    @FXML
    public void CreateAccountClicked(ActionEvent actionEvent) throws IOException {
        Stage stage3 = (Stage) createAccount.getScene().getWindow();
        stage3.close();
        Stage stage6 = new Stage();
        FXMLLoader fxmlLoader6 = new FXMLLoader(getClass().getResource("create-account.fxml"));
        Scene scene6 = new Scene(fxmlLoader6.load(), 650, 650);
        stage6.setTitle("Create Account");
        stage6.setScene(scene6);
        stage6.show();
    }

    @FXML
    public void DepositOrWithdrawalClicked(ActionEvent actionEvent) throws IOException {
        Stage stage3 = (Stage) depositWithdrawal.getScene().getWindow();
        stage3.close();
        Stage stage7 = new Stage();
        FXMLLoader fxmlLoader7 = new FXMLLoader(getClass().getResource("deposit-withdrawal.fxml"));
        Scene scene7 = new Scene(fxmlLoader7.load(), 650, 650);
        stage7.setTitle("Your Account");
        stage7.setScene(scene7);
        stage7.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Account> accounts = Bank.getInstance().getCustomerHashMap().get("bobby.ayvazov@email.com").getAccountList();
        for (int i = 0; i < accounts.size(); i++) {
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(0,10,0,10));
            Label accountName = new Label("ACCOUNT TYPE");
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

        /*List<Loan> loans = Bank.getInstance().getCustomerHashMap().get("bilal.khan@email.com").getLoanList();
        for (int i = 0; i < loans.size(); i++) {
            GridPane gridLoans = new GridPane();
            gridLoans.setHgap(10);
            gridLoans.setVgap(10);
            gridLoans.setPadding(new Insets(0,10,0,10));
            Label loanName = new Label("LOAN TYPE");
            loanName.setFont(Font.font("Verdana", FontWeight.BOLD,20));
            gridLoans.add(loanName,0,0);
            Label amountOfLoanUnpaid = new Label("- £" + String.format("%.2f",(loans.get(i).getBalance()*-1)));
            amountOfLoanUnpaid.setFont(Font.font("Verdana", FontWeight.BOLD,20));
            gridLoans.setStyle("-fx-padding: 10;" +
                    "-fx-border-style: solid inside;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-insets: 5;" +
                    "-fx-border-radius: 5;" +
                    "-fx-border-color: #0E1E2B;");
            LoansScrollVBox.getChildren().add(gridLoans);
        }*/
    }
}
