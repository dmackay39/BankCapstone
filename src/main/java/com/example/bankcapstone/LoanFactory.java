package com.example.bankcapstone;

public class LoanFactory {
    public Loan createLoan(LoanTypeEnum type, int term, double amount){
        Loan newLoan = null;
        switch (type){
            case CAR -> newLoan = new CarLoan(amount, term);
            case HOME -> newLoan = new HomeLoan(amount, term);
            case PERSONAL -> newLoan = new PersonalLoan(amount);
        }
        return newLoan;
    }
}
