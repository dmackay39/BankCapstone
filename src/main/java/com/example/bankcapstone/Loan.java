package com.example.bankcapstone;

import java.time.LocalDate;

public abstract class Loan {
    private double interestRate = 0.1;
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    private LocalDate startDate;

    private static int superLoanNumber = 2000000;
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

    public int getSuperLoanNumber() {
        return superLoanNumber;
    }

    public void setSuperLoanNumber(int number) {
        superLoanNumber = number;
    }

    public void setMaximumAmount(double maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    public double getMaximumAmount() {
        return this.maximumAmount;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public Loan(double balance, String email) {
        this.balance = -balance;
        this.startDate = LocalDate.now();
        this.email = email;
        superLoanNumber++;
    }

    public Loan(double balance, LocalDate startDate, String email) {
        this.balance = balance;
        this.startDate = startDate;
        this.email = email;
    }

 //   public abstract void chargeInterest();

    public abstract int getLoanNumber();

    public abstract LocalDate getEndDate();

    private LocalDate interestPaidDate = null;

    public LocalDate getInterestPaidDate() {
        return interestPaidDate;
    }


    public void addInterest() {
        if (interestPaidDate == null) {
            interestPaidDate = getStartDate();
        }
        while (LocalDate.now().isAfter(interestPaidDate.plusYears(1))) {
            setBalance(balance * (1 + getInterestRate()));
            interestPaidDate = interestPaidDate.plusYears(1);
        }
    }


    public void setInterestPaidDate(LocalDate interestPaidDate) {
        this.interestPaidDate = interestPaidDate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public abstract void setEndDate(LocalDate localDate);
}
