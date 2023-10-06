package com.example.bankcapstone;

import com.example.bankcapstone.Account;
import com.example.bankcapstone.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DepositOrWithdrawTests {

    private Customer customer;
    private Account checkingAccount;

    @BeforeEach
    public void setUp() {

        customer = new Customer("Bryan", "Hansen", "johndoe@example.com", "password");
        checkingAccount = new CheckingAccount(customer.getEmail());
        customer.addAccount(checkingAccount);
    }

    @Test
    public void testDeposit() {
        // Deposit 100 into the checking account
        double initialBalance = checkingAccount.getBalance();
        double depositAmount = 100.0;
        String result = customer.depositOrWithdraw(checkingAccount, depositAmount, "deposit");

        assertEquals("Deposit Successful", result);
        assertEquals(initialBalance + depositAmount, checkingAccount.getBalance());
    }

    @Test
    public void testWithdrawalWithSufficientFunds() {
        // Deposit 200
        double initialBalance = checkingAccount.getBalance();
        double depositAmount = 200.0;
        customer.depositOrWithdraw(checkingAccount, depositAmount, "deposit");

        // Withdraw 100
        double withdrawalAmount = 100.0;
        String result = customer.depositOrWithdraw(checkingAccount, withdrawalAmount, "withdraw");

        assertEquals("Withdrawal Successful", result);
        assertEquals(initialBalance + depositAmount - withdrawalAmount, checkingAccount.getBalance());
    }

    @Test
    public void testWithdrawalWithInsufficientFunds() {
        // Deposit 50
        double initialBalance = checkingAccount.getBalance();
        double depositAmount = 50.0;
        customer.depositOrWithdraw(checkingAccount, depositAmount, "deposit");

        // withdraw $100 - insufficient funds
        double withdrawalAmount = 100.0;
        String result = customer.depositOrWithdraw(checkingAccount, withdrawalAmount, "withdraw");

        System.out.println("Actual result: " + result);

        assertEquals("Cannot withdraw more than account balance", result);
        assertEquals(initialBalance + depositAmount, checkingAccount.getBalance());
    }

    @Test
    public void testTransferBetweenAccounts() {
        // Deposit $300 into the checking account
        double initialCheckingBalance = checkingAccount.getBalance();
        double depositAmount = 300.0;
        customer.depositOrWithdraw(checkingAccount, depositAmount, "deposit");
        double postDepositCheckingBalance = checkingAccount.getBalance();

        // Create a savings account and deposit $200
        Account savingsAccount2 = new CheckingAccount(customer.getEmail());
        double initialSavingsBalance = savingsAccount2.getBalance();
        customer.addAccount(savingsAccount2);
        customer.depositOrWithdraw(savingsAccount2, 200.0, "deposit");
        double postDepositSavingsBalance = savingsAccount2.getBalance();

        // Print initial balances
        System.out.println("Initial Checking Balance: " + initialCheckingBalance);
        System.out.println("Initial Savings Balance: " + initialSavingsBalance);
        System.out.println("Post Deposit Checking Balance: " + postDepositCheckingBalance);
        System.out.println("Post Deposit Savings Balance: " + postDepositSavingsBalance);

        // Transfer 100
        double transferAmount = 100.0;
        String result = customer.payOrTransfer(checkingAccount, savingsAccount2, transferAmount);

        System.out.println("Transfer Result: " + result);
        System.out.println("Updated Checking Balance: " + checkingAccount.getBalance());
        System.out.println("Updated Savings Balance: " + savingsAccount2.getBalance());

        assertEquals((postDepositCheckingBalance - transferAmount), checkingAccount.getBalance());
        assertEquals((postDepositSavingsBalance + transferAmount), savingsAccount2.getBalance());

        assertEquals((savingsAccount2.getBalance() - transferAmount), checkingAccount.getBalance());
        assertEquals((checkingAccount.getBalance() + transferAmount), savingsAccount2.getBalance());

    }


}
