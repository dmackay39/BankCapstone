package com.example.bankcapstone;

import java.time.LocalDate;

public abstract class Account {
    private static int accountNumCounter = 1000000;
    private LocalDate accountStartDate;
    private String username;

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


    public abstract double getInterestRate();
    public abstract int getTermLength();
    public abstract void setTermLength(int termLength);

}
