package com.example.bankcapstone;

import java.time.LocalDate;

public class LoanFactory {
    public Loan createLoan(LoanTypeEnum type, int term, double amount, String email) {
        Loan newLoan = null;
        switch (type) {
            case CAR -> newLoan = new CarLoan(amount, term, email);
            case HOME -> newLoan = new HomeLoan(amount, term, email);
        }
        return newLoan;
    }

    public Loan createLoan(LoanTypeEnum type, int term, double amount, LocalDate startDate, int loanNumber,
                           String email) {
        Loan newLoan = null;
        switch (type) {
            case CAR -> newLoan = new CarLoan(amount, term, startDate, loanNumber, email);
            case HOME -> newLoan = new HomeLoan(amount, term, startDate, loanNumber, email);
        }
        return newLoan;
    }

    public Loan createLoan(LoanTypeEnum type, double amount, String email) {
        Loan newLoan = null;
        newLoan = new PersonalLoan(amount, email);
        return newLoan;
    }

    public Loan createLoan(LoanTypeEnum type, double amount, LocalDate startDate, int loanNumber,
                           String email) {
        switch (type) {
            case PERSONAL ->{
                return new PersonalLoan(amount, startDate, loanNumber, email);
            }
            case CAR -> {
                return new CarLoan(amount, startDate, loanNumber, email);
            }
            case HOME -> {
                return new HomeLoan(amount, startDate, loanNumber, email);
            }
            default -> {
                return new PersonalLoan(amount, startDate, loanNumber, email);
            }
        }
    }

}
