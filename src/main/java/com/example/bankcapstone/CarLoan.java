package com.example.bankcapstone;


import java.time.LocalDate;

public class CarLoan extends Loan{
    private int loanNumber;
    private LocalDate endDate;
    private final LoanTypeEnum loanType = LoanTypeEnum.CAR;

    public CarLoan(double amount, int term, String email) {
        super(amount, email);
        this.endDate = this.getStartDate().plusYears(term);
        this.setMaximumAmount(-50000);
        this.loanNumber = super.getSuperLoanNumber();
    }
    public CarLoan(double amount, int term, LocalDate startDate, int loanNumber, String email) {
        super(amount, startDate, email);
        this.endDate = this.getStartDate().plusYears(term);
        this.setMaximumAmount(-50000);
        this.loanNumber = loanNumber;
    }

    public CarLoan(double amount, LocalDate startDate, int loanNumber, String email) {
        super(amount, startDate,loanNumber,email);
    }

    @Override
    public LoanTypeEnum getLoanType() {
        return loanType;
    }

    public LocalDate getEndDate(){
        return this.endDate;
    }

    @Override
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


    public void chargeInterest(){
        double currentBalance = getBalance();
        this.setBalance(currentBalance*getInterestRate());
    }
}
