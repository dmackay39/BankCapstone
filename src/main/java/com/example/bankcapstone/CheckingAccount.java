package com.example.bankcapstone;

import java.time.LocalDate;

public class CheckingAccount extends Account{

    public CheckingAccount(String username){
        super(username);
    }

    public CheckingAccount(String username, double balance){
        super(username, balance);
    }

    @Override
    public double getInterestRate() {
        return 0;
    }

    @Override
    public int getTermLength() {
        return 0;
    }

    @Override
    public void setTermLength(int termLength) {

    }

}
