package com.example.bankcapstone;

import java.time.LocalDate;

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
    public Loan createLoan(LoanTypeEnum type, int term, double amount, LocalDate startDate){
        Loan newLoan = null;
        switch (type){
            case CAR -> newLoan = new CarLoan(amount, term, startDate);
            case HOME -> newLoan = new HomeLoan(amount, term, startDate);
            case PERSONAL -> newLoan = new PersonalLoan(amount, startDate);
        }
        return newLoan;
    }
}
