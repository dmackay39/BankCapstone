package com.example.bankcapstone;

public class SavingsAccount extends Account{

    private final double interestRate = 0.05;

    public SavingsAccount(String username){
        super(username);
    }

    public SavingsAccount(String username, Double balance){
        super(username, balance);
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
