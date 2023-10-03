package com.example.bankcapstone;

import java.time.LocalDate;

public class LoanFactory {
    public Loan createLoan(LoanTypeEnum type, int term, double amount, String email){
        Loan newLoan = null;
        switch (type){
            case CAR -> newLoan = new CarLoan(amount, term, email);
            case HOME -> newLoan = new HomeLoan(amount, term, email);
            case PERSONAL -> newLoan = new PersonalLoan(amount, email);
        }
        return newLoan;
    }
    public Loan createLoan(LoanTypeEnum type, int term, double amount, LocalDate startDate, int loanNumber,
                           String email){
        Loan newLoan = null;
        switch (type){
            case CAR -> newLoan = new CarLoan(amount, term, startDate, loanNumber, email);
            case HOME -> newLoan = new HomeLoan(amount, term, startDate, loanNumber, email);
            case PERSONAL -> newLoan = new PersonalLoan(amount, startDate, loanNumber, email);
        }
        return newLoan;
    }
}
