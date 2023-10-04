package com.example.bankcapstone;

import java.time.LocalDate;

public class HomeLoan extends Loan{
    private int loanNumber;
    private LocalDate endDate;
    private final LoanTypeEnum loanType = LoanTypeEnum.HOME;

    public HomeLoan(double amount, LocalDate startDate, int loanNumber, String email) {
        super(amount,startDate,loanNumber,email);
    }

    @Override
    public LoanTypeEnum getLoanType() {
        return this.loanType;
    }

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

    public LocalDate getEndDate(){
        return endDate;
    }

    @Override
    public void setEndDate(LocalDate localDate) {
        this.endDate = localDate;
    }

    public void chargeInterest(){
        double currentBalance = getBalance();
        this.setBalance(currentBalance*getInterestRate());
    }
}
