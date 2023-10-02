package com.example.bankcapstone;

public class CdAccount extends Account{

    private final double interestRate = 0.07;
    private int termLenght;

    public CdAccount(int termLenght){
        super();
        this.termLenght = termLenght;
    }

    public CdAccount(double balance, int termLenght){
        super(balance);
        this.termLenght = termLenght;
    }

    public void setTermLenght(int termLenght) {
        this.termLenght = termLenght;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getTermLenght() {
        return termLenght;
    }
}
