package com.example.bankcapstone;

import java.time.LocalDate;

public abstract class Account {
    private static int accountNumCounter;
    private LocalDate accountStartDate;

    private int accountNumber;
    private double balance;

    public Account(){
        this.accountNumber = accountNumCounter++;
        this.balance = 0.0;
        this.accountStartDate = LocalDate.now();
    }

    public Account(double balance){
        this.accountNumber = accountNumCounter++;
        this.balance = balance;
        this.accountStartDate = LocalDate.now();
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

}
