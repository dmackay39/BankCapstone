package com.example.bankcapstone;

import java.time.LocalDate;

public abstract class Account {
    private static int accountNumCounter;
    private LocalDate accountStartDate;
    private String username;

    private AccountType accountType;

    private int accountNumber;
    private double balance;

    public Account(String username){
        this.accountNumber = accountNumCounter++;
        this.balance = 0.0;
        this.accountStartDate = LocalDate.now();
        this.username = username;
    }

    public Account(String email, double balance){
        this.accountNumber = accountNumCounter++;
        this.balance = balance;
        this.accountStartDate = LocalDate.now();
        this.username = username;
    }

    public Account(String username, int accountNumber, double balance, LocalDate accountStartDate){
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountStartDate = accountStartDate;
        this.username = username;
    }

    private LocalDate interestPaidDate = null;

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDate getAccountStartDate() {
        return accountStartDate;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public abstract double getInterestRate();
    public abstract int getTermLength();
    public abstract void setTermLength(int termLength);
    public abstract void setInterestRate(double interestRate);

    public LocalDate getInterestPaidDate(){
        return this.interestPaidDate;
    }

    public void setInterestPaidDate(LocalDate interestPaidDate){
        this.interestPaidDate = interestPaidDate;
    }

    public void payInterest(){
        if (interestPaidDate == null){
            interestPaidDate = getAccountStartDate();}

        if (LocalDate.now().isAfter(interestPaidDate.plusYears(1))){
            setBalance(balance*(1+getInterestRate()));
            interestPaidDate = LocalDate.now();
        }
    }

}
