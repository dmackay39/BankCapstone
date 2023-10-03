package com.example.bankcapstone;

import java.time.LocalDate;

public class CdAccount extends Account {

    private double interestRate = 0.07;
    private int termLength = 1;
    private final AccountType accountType = AccountType.CD;


    public CdAccount(String username) {
        super(username);
    }

    public CdAccount(String username, double balance) {
        super(username, balance);
    }

    public CdAccount(String username, int accountNumber, double balance, LocalDate accountStartDate) {
        super(username, accountNumber, balance, accountStartDate);
    }

    public CdAccount(String username, int accountNumber, double balance, LocalDate accountStartDate, int termLength) {
        super(username, accountNumber, balance, accountStartDate);
        this.termLength = termLength;
    }

    public void setTermLength(int termLength) {
        this.termLength = termLength;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getTermLength() {
        return termLength;
    }

    @Override
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

}
