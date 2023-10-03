package com.example.bankcapstone;

import java.util.List;

public class Customer {

    private String firstName, lastName, email, password;

    private List<Account> accountList;
    private List<Loan> loanList;

    public Customer(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean authenticatePassword(String enteredPassword) {
        return this.password.equals(enteredPassword);
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

    //payOrTransfer method for transfers between accounts
    public String payOrTransfer(Account accountFrom, Account accountTo, double amount) {
        if ((accountFrom.getBalance() - amount) > 0) {
            accountFrom.setBalance(accountFrom.getBalance() - amount);
            accountTo.setBalance(accountTo.getBalance() + amount);
            return "Transfer Successful";
        } else if ((getAccessibleBalance()-amount) > 0) {
            return "Transfer Failed. You may transfer funds to Account #" + accountFrom.getAccountNumber() + " from your other checking/savings accounts";
        } else {
            return "Transfer failed as you do not have sufficient funds";
        }
    }

    //payOrTransfer method for payments from accounts to loans
    public String payOrTransfer(Account accountFrom, Loan loanTo, double amount) {
        if ((accountFrom.getBalance() - amount) > 0) {
            accountFrom.setBalance(accountFrom.getBalance() - amount);
            loanTo.setBalance(loanTo.getBalance() + amount);
            return "Payment Successful";
        } else if ((getAccessibleBalance()-amount) > 0) {
            return "Payment Failed. You may transfer funds to Account #" + accountFrom.getAccountNumber() + " from your other checking/savings accounts";
        } else {
            return "This payment with overdraw your account and requires bank manager approval";
        }
    }

    //payOrTransfer method for payments from checking account to pay bills
    public String payOrTransfer(Account accountFrom, double amount) {
        if ((accountFrom.getBalance() - amount) > 0) {
            accountFrom.setBalance(accountFrom.getBalance() - amount);
            return "Payment Successful";
        } else if ((getAccessibleBalance()-amount) > 0) {
            return "Payment Failed. You may transfer funds to Account #" + accountFrom.getAccountNumber() + " from your other checking/savings accounts";
        } else {
            return "This payment with overdraw your account and requires bank manager approval";
        }
    }

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