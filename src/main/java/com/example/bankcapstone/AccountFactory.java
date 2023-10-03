package com.example.bankcapstone;


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

    public static Account createNewAccount(AccountType accountType, String username, Double balance) {
        switch (accountType) {
            case CHECKING -> {
                return new CheckingAccount(username, balance);
            }
            case SAVINGS -> {
                return new SavingsAccount(username, balance);
            }
            case CD -> {
                return new CdAccount(username, balance);
            }
            default -> {
                return new CheckingAccount(username, balance);
            }
        }
    }

}
