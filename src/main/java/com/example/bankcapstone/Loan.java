package com.example.bankcapstone;

import java.time.LocalDate;

public abstract class Loan {
    private final double interestRate = 0.1;
    private double balance;

    private String email;

    public LoanTypeEnum getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanTypeEnum loanType) {
        this.loanType = loanType;
    }

    private LoanTypeEnum loanType;
    private int loanNumber;
    private LocalDate startDate;
    private static int superLoanNumber = 1000000;
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
    public void setSuperLoanNumber(int number){
        superLoanNumber = number;
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
    public Loan(double balance, String email) {
        this.balance = -balance;
        this.startDate = LocalDate.now();
        this.email = email;
        superLoanNumber++;
    }
    public Loan (double balance, LocalDate startDate, String email){
        this.balance = balance;
        this.startDate = startDate;
        this.email = email;
    }
    public abstract void chargeInterest();
    public abstract int getLoanNumber();
    public abstract LocalDate getEndDate();
}
