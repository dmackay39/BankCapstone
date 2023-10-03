package com.example.bankcapstone;

import java.time.LocalDate;

public class SavingsAccount extends Account{

    private final double interestRate = 0.05;

    public SavingsAccount(String username){
        super(username);
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


}
