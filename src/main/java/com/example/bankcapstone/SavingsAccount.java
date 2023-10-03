package com.example.bankcapstone;

import java.time.LocalDate;

public class SavingsAccount extends Account{

    private double interestRate = 0.05;
    private final AccountType accountType = AccountType.SAVINGS;


    public SavingsAccount(String username){
        super(username);
    }
    public SavingsAccount(String username, double balance){
        super(username, balance);
    }

    public SavingsAccount(String username, int accountNumber, double balance, LocalDate accountStartDate){
        super(username, accountNumber, balance, accountStartDate);
    }

    public double getInterestRate() {
        return interestRate;
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
        this.interestRate = interestRate;
    }

    @Override
    public void setInterestPaidDate(LocalDate interestPaidDate) {

    }


}
