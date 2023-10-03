package com.example.bankcapstone;

import java.time.LocalDate;

public class PersonalLoan extends Loan {
    private int loanNumber;
    public PersonalLoan(double amount) {
        super(amount);
        this.setMaximumAmount(-45000);
        this.loanNumber = getSuperLoanNumber();
    }
    public PersonalLoan(double amount, LocalDate startDate) {
        super(amount, startDate);
        this.setMaximumAmount(-45000);
        this.loanNumber = getSuperLoanNumber();
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
