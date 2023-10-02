package com.example.bankcapstone;

public class PersonalLoan extends Loan {
    private int loanNumber;
    public PersonalLoan(double amount) {
        super(amount);
        this.setMaximumAmount(45000);
        this.loanNumber = getSuperLoanNumber();
    }
    public int getLoanNumber(){
        return this.loanNumber;
    }

}
