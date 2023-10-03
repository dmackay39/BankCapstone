package com.example.bankcapstone;

import java.time.LocalDate;

public class CheckingAccount extends Account {

    private final AccountType accountType = AccountType.CHECKING;

    public CheckingAccount(String username) {
        super(username);
    }

    public CheckingAccount(String username, double balance) {
        super(username, balance);
    }

    public CheckingAccount(String username, int accountNumber, double balance, LocalDate accountStartDate) {
        super(username, accountNumber, balance, accountStartDate);
    }

    @Override
    public double getInterestRate() {
        return 0;
    }

    @Override
    public int getTermLength() {
        return 0;
    }

    @Override
    public void setTermLength(int termLength) {

    }

    @Override
    public void setInterestRate(double interestRate) {

    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public String toString() {
        return this.getAccountNumber() + " " + getAccountStartDate() + " " + getAccountType() + " " + getBalance();
    }

}
