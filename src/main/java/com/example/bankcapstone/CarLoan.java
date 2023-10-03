package com.example.bankcapstone;


import java.time.LocalDate;

public class CarLoan extends Loan{
    private int loanNumber;
    private LocalDate endDate;
    public CarLoan(double amount, int term) {
        super(amount);
        this.endDate = this.getStartDate().plusYears(term);
        this.setMaximumAmount(-50000);
        this.loanNumber = getSuperLoanNumber();
    }
    public CarLoan(double amount, int term, LocalDate startDate) {
        super(amount, startDate);
        this.endDate = this.getStartDate().plusYears(term);
        this.setMaximumAmount(-50000);
        this.loanNumber = getSuperLoanNumber();
    }
    public int getLoanNumber(){
        return loanNumber;
    }
    public LocalDate getEndDate(){
        return endDate;
    }

    public void chargeInterest(){
        double currentBalance = getBalance();
        this.setBalance(currentBalance*getInterestRate());
    }
}
