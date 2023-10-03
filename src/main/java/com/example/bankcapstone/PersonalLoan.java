package com.example.bankcapstone;

import java.time.LocalDate;

public class PersonalLoan extends Loan {
    private int loanNumber;
    public PersonalLoan(double amount, String email) {
        super(amount, email);
        this.setMaximumAmount(-45000);
        this.loanNumber = getSuperLoanNumber();
    }
    public PersonalLoan(double amount, LocalDate startDate, int loanNumber, String email) {
        super(amount, startDate, email);
        this.setMaximumAmount(-45000);
        this.loanNumber = loanNumber;
    }

    @Override
    public void chargeInterest() {
    }

    public int getLoanNumber(){
        return this.loanNumber;
    }

    @Override
    public LocalDate getEndDate() {
        return null;
    }

}
