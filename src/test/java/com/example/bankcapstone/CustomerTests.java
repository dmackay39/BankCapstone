package com.example.bankcapstone;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class CustomerTests {

    Customer uut;

    @BeforeEach
    public void setUp() {
        uut = new Customer("first", "last", "first.last@email.com", "password");
    }

    @AfterEach
    public void tearDown() {
        uut = null;
    }

    @ParameterizedTest
    @CsvSource({"10000.0, 0.0, 500.0, Transfer Successful",
            "1000.0, 0.0, 2000.0, You do not have sufficient funds to make this transfer."})
    public void testPayOrTransferAccountToAccount(double balanceFrom, double balanceTo, double amount, String expectedResult) {
        CheckingAccount mockAccountFrom = new CheckingAccount(uut.getEmail());
        mockAccountFrom.setBalance(balanceFrom);
        System.out.println(mockAccountFrom.getBalance());
        System.out.println(amount);

        CheckingAccount mockAccountTo = new CheckingAccount(uut.getEmail());
        mockAccountTo.setBalance(balanceTo);

        String actualResult = uut.payOrTransfer(mockAccountFrom, mockAccountTo, amount);

        Assertions.assertEquals(expectedResult, actualResult);
    }


    @ParameterizedTest
    @CsvSource({"0.0, 0.0, 500.0"})
    public void testPayOrTransferAccountToAccountWithGetAccessibleBalance(double balanceFrom, double balanceTo, double amount) {
        CheckingAccount mockAccountFrom = new CheckingAccount(uut.getEmail());
        mockAccountFrom.setBalance(balanceFrom);
        uut.addAccount(mockAccountFrom);

        CheckingAccount mockAccountTo = new CheckingAccount(uut.getEmail());
        mockAccountTo.setBalance(balanceTo);
        uut.addAccount(mockAccountTo);

        CheckingAccount mockThirdAccount = new CheckingAccount(uut.getEmail());
        mockThirdAccount.setBalance(1000.00);
        uut.addAccount(mockThirdAccount);

        String actualResult = uut.payOrTransfer(mockAccountFrom, mockAccountTo, amount);

        String expectedResult = "Transfer Failed. You may transfer funds to Account #" + mockAccountFrom.getAccountNumber() + " from your other checking/savings accounts.";

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({"1000.0, -500.0, 50.0, Payment Successful",
            "1000.0, -500.0, 505.0, You can't pay more than you owe.",
            "400.0,-500.0,450.0,You do not have sufficient funds to make this payment."})
    public void testPayOrTransferAccountToLoan(double balanceFrom, double balanceTo, double amount, String expectedResult) {
        CheckingAccount mockAccountFrom = new CheckingAccount(uut.getEmail());
        mockAccountFrom.setBalance(balanceFrom);
        uut.addAccount(mockAccountFrom);

        CarLoan mockLoanTo = new CarLoan(1000.0, 5, uut.getEmail());
        mockLoanTo.setBalance(balanceTo);
        uut.addLoan(mockLoanTo);

        String actualResult = uut.payOrTransfer(mockAccountFrom, mockLoanTo, amount);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({"100.0, -500.0, 150.0"})
    public void testPayOrTransferAccountToLoanWithGetAccessibleBalance(double balanceFrom, double balanceTo, double amount) {
        CheckingAccount mockAccountFrom = new CheckingAccount(uut.getEmail());
        mockAccountFrom.setBalance(balanceFrom);
        uut.addAccount(mockAccountFrom);

        CarLoan mockLoanTo = new CarLoan(1000.0, 5, uut.getEmail());
        mockLoanTo.setBalance(balanceTo);
        uut.addLoan(mockLoanTo);

        CheckingAccount mockThirdAccount = new CheckingAccount(uut.getEmail());
        mockThirdAccount.setBalance(1000.0);
        uut.addAccount(mockThirdAccount);

        String actualResult = uut.payOrTransfer(mockAccountFrom, mockLoanTo, amount);

        String expectedResult = "Payment Failed. You may transfer funds to Account #" + mockAccountFrom.getAccountNumber() + " from your other checking/savings accounts";

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({"1000.0, -500.0, 500.0"})
    public void testPayOrTransferAccountToLoanAndRemovingPaidOffLoan(double balanceFrom, double balanceTo, double amount) {
        CheckingAccount mockAccountFrom = new CheckingAccount(uut.getEmail());
        mockAccountFrom.setBalance(balanceFrom);
        uut.addAccount(mockAccountFrom);

        CarLoan mockLoanTo = new CarLoan(1000.0, 5, uut.getEmail());
        mockLoanTo.setBalance(balanceTo);
        uut.addLoan(mockLoanTo);


        String actualResult = uut.payOrTransfer(mockAccountFrom, mockLoanTo, amount);

        String expectedResult = "Payment successful. Loan #" + mockLoanTo.getLoanNumber() + " has been fully paid off and removed from your account.";

        Assertions.assertEquals(expectedResult, actualResult);
    }



    @ParameterizedTest
    @CsvSource({"1000.0, 500.0, Payment Successful",
    "1000.0,2000.0, This payment will overdraw your account and requires bank manager approval"})
    public void testPayOrTransferAccountToBills(double balanceFrom, double amount, String expectedResult) {
        CheckingAccount mockAccountFrom = new CheckingAccount(uut.getEmail());
        mockAccountFrom.setBalance(balanceFrom);
        uut.addAccount(mockAccountFrom);

        String actualResult = uut.payOrTransfer(mockAccountFrom, amount);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({"0.0, 500.0"})
    public void testPayOrTransferAccountToBillsWithGetAccessibleBalance(double balanceFrom, double amount) {
        CheckingAccount mockAccountFrom = new CheckingAccount(uut.getEmail());
        mockAccountFrom.setBalance(balanceFrom);
        uut.addAccount(mockAccountFrom);

        CheckingAccount mockThirdAccount = new CheckingAccount(uut.getEmail());
        mockThirdAccount.setBalance(1000.0);
        uut.addAccount(mockThirdAccount);

        String expectedResult = "Payment Failed. You may transfer funds to Account #" + mockAccountFrom.getAccountNumber() + " from your other checking/savings accounts";

        String actualResult = uut.payOrTransfer(mockAccountFrom, amount);

        Assertions.assertEquals(expectedResult, actualResult);
    }



}
