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

    public String payOrTransfer(Account accountFrom, Account accountTo, double amount) {
        if ((accountFrom.getBalance() - amount) > 0) {
            accountFrom.setBalance(accountFrom.getBalance() - amount);
            accountTo.setBalance(accountTo.getBalance() + amount);
            return "Transfer Successful";
        } else if (getAccessibleBalance() > 0) {
            return "Transfer Failed. You may transfer funds to Account #" + accountFrom.getAccountNumber() + " from your other checking/savings accounts";
        } else {
            return "Transfer Failed";
        }
    }

    // add something for transferring to loan account.


    public double getAccessibleBalance() {
        double totalAccessibleBalance = 0;
        for (Account account : accountList) {
            if (!(account instanceof CdAccount)) {
                totalAccessibleBalance += account.getBalance();
            }
        }
        return totalAccessibleBalance;
    }


    public String depositOrWithdraw(Account account, double amount, String option) {
        switch (option) {
            case "deposit" -> {
                account.setBalance(account.getBalance() + amount);
                return "Deposit Successful";
            }
            case "withdraw" -> {
                if (account.getBalance() - amount > 0) {
                    account.setBalance(account.getBalance() - amount);
                    return "Withdrawal Successful";
                }
            }
            default -> {
                return "Cannot withdraw more than account balance";
            }
        }
        return "";
    }

}