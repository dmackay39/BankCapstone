package com.example.bankcapstone;


import net.bytebuddy.asm.Advice;

import java.time.LocalDate;

public class AccountTestingStub  implements IAccount{
    double balance = 100.0;
    LocalDate startDate;
    LocalDate interestPaidDate;

    public LocalDate getInterestPaidDate() {
        return interestPaidDate;
    }

    public void setInterestPaidDate(LocalDate interestPaidDate) {
        this.interestPaidDate = interestPaidDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public LocalDate getAccountStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate date){
        this.startDate = startDate;
    }

    @Override
    public void payInterest() {
        if (interestPaidDate == null){
            interestPaidDate = getAccountStartDate();}

        if (LocalDate.now().isAfter(interestPaidDate.plusYears(1))){
            setBalance(balance*(1+getInterestRate()));
            interestPaidDate = LocalDate.now();
        }
    }
    double interestRate= 0.05;
    private double getInterestRate() {

        return interestRate;
    }
}
