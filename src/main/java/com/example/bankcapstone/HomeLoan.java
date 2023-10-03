package com.example.bankcapstone;

import java.time.LocalDate;

public class HomeLoan extends Loan{
    private int loanNumber;
    private LocalDate endDate;
    public HomeLoan(double amount, int term, String email) {
        super(amount, email);
        this.endDate = this.getStartDate().plusYears(term);
        this.setMaximumAmount(-2000000);
        this.loanNumber = getSuperLoanNumber();
    }
    // for new loans
    public HomeLoan(double amount, int term, LocalDate startDate, int loanNumber, String email) {
        super(amount, startDate, email);
        this.endDate = this.getStartDate().plusYears(term);
        this.setMaximumAmount(-2000000);
        this.loanNumber = loanNumber;
    }
    //for existing loans to be read
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
