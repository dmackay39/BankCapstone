package com.example.bankcapstone;

import java.time.LocalDate;

public abstract class Loan {
    private final double interestRate = 0.1;
    private double balance;
    private LocalDate startDate;
    private static int superLoanNumber = 0;
    private double maximumAmount;

    public double getInterestRate() {
        return interestRate;
    }
    public double getBalance() {
        return this.balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public int getSuperLoanNumber(){
        return superLoanNumber;
    }
    public void setMaximumAmount(double maximumAmount){
        this.maximumAmount = maximumAmount;
    }
    public double getMaximumAmount(){
        return this.maximumAmount;
    }
    public LocalDate getStartDate(){
        return this.startDate;
    }
    public Loan(double balance) {
        this.balance = balance;
        this.startDate = LocalDate.now();
        superLoanNumber++;
    }
}
