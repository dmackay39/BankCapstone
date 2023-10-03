package com.example.bankcapstone;

public class CdAccount extends Account{

    private final double interestRate = 0.07;
    private int termLength = 1;

    public CdAccount(String username){
        super(username);
    }

    public CdAccount(String username, double balance) {
        super(username, balance);
    }

    public CdAccount(String username, double balance, int termLength){
        super(username, balance);

        this.termLength = termLength;
    }

    public void setTermLength(int termLength) {
        this.termLength = termLength;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getTermLength() {
        return termLength;
    }
}
