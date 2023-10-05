package com.example.bankcapstone;

public interface IBank {
    boolean verifyLoan(Customer customer, double loanBalance, LoanTypeEnum loanType);
    void setTotalDeposits(double total);
    void setTotalLending(double total);
}
