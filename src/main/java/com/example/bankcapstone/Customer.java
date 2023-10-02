package com.example.bankcapstone;

import java.util.List;

public class Customer {

    private String firstName, lastName, email, password;

    private List<Account> accountList;
    private List<Loan> loanList;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean authenticatePassword(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void addAccount(Account account) {
        accountList.add(account);
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void addLoan(Loan loan) {
        loanList.add(loan);
    }

    public void payOrTransfer(Account accountFrom, Account accountTo, double amount) {

    }

    public void depositOrWithdraw(Account account, double amount) {

    }

}