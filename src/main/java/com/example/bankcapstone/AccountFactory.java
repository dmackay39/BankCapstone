package com.example.bankcapstone;


import java.time.LocalDate;

public class AccountFactory {
    public static Account createNewAccount(AccountType accountType, String username) {
        switch (accountType) {
            case CHECKING -> {
                return new CheckingAccount(username);
            }
            case SAVINGS -> {
                return new SavingsAccount(username);
            }
            case CD -> {
                return new CdAccount(username);
            }
            default -> {
                return new CheckingAccount(username);
            }
        }
    }

    public static Account createNewAccount(AccountType accountType, String username, int accountNumber, double balance, LocalDate accountStartDate) {
        switch (accountType) {
            case CHECKING -> {
                return new CheckingAccount(username, accountNumber, balance, accountStartDate);
            }
            case SAVINGS -> {
                return new SavingsAccount(username, accountNumber, balance, accountStartDate);
            }
            case CD -> {
                return new CdAccount(username, accountNumber, balance, accountStartDate);
            }
            default -> {
                return new CheckingAccount(username, accountNumber, balance, accountStartDate);
            }
        }
    }

}
