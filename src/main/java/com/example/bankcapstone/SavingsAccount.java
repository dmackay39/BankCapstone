package com.example.bankcapstone;

public class SavingsAccount extends Account{

    private final double interestRate = 0.05;

    public SavingsAccount(){
        super();
    }

    public SavingsAccount(Double balance){
        super(balance);
    }

    public double getInterestRate() {
        return interestRate;
    }
}
