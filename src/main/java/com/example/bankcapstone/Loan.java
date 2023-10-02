package com.example.bankcapstone;

import java.time.LocalDate;

public abstract class Loan {
    private final double interestRate = 0.1;
    private double amount;
    private LocalDate startDate;
    private static int superLoanNumber = 0;
    private double maximumAmount;

    public double getInterestRate() {
        return interestRate;
    }
    public double getAmount() {
        return this.amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
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
    public Loan(double amount) {
        this.amount = amount;
        this.startDate = LocalDate.now();
        superLoanNumber++;
    }
}
