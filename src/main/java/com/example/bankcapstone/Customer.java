package com.example.bankcapstone;

import java.util.HashMap;
import java.util.List;

public class Customer {

    private String firstName, lastName, email, password;

    private HashMap<Integer,Account> accountHashMap = new HashMap<>();
    private HashMap<Integer,Loan> loanHashMap = new HashMap<>();

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

    public HashMap<Integer, Account> getAccountHashMap() {
        return accountHashMap;
    }

    public HashMap<Integer, Loan> getLoanHashMap() {
        return loanHashMap;
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
        return accountHashMap.values().stream().toList();
    }

    public void addAccount(Account account) {
        accountHashMap.put(account.getAccountNumber(),account);
    }

    public String getPassword() {
        return password;
    }

    public List<Loan> getLoanList() {
        return loanHashMap.values().stream().toList();
    }

    public void addLoan(Loan loan) {
        loanHashMap.put(loan.getLoanNumber(),loan);
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
            return "This payment will overdraw your account and requires bank manager approval";
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
            return "This payment will overdraw your account and requires bank manager approval";
        }
    }

    public double getAccessibleBalance() {
        double totalAccessibleBalance = 0;
        for (Account account : accountHashMap.values()) {
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