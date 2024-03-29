package com.example.bankcapstone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.abs;

public class Bank {

    private HashMap<String, Customer> customerHashMap = new HashMap<>();
    private double totalDeposits;
    private double totalLending;
    private ReaderWriter readerWriter;

    private static Bank bankInstance;

    private boolean paymentOveride = false;
    private Customer activeCustomer;

    private Bank() {
        readerWriter = new ReaderWriter();
    }

    public static Bank getInstance() {

        if (bankInstance == null) {
            bankInstance = new Bank();
        }
        return bankInstance;
    }

    public void populateBankDatabase() {
        readerWriter.readCustomersFromFile();
        readerWriter.readAccountsFromFile();
        readerWriter.readLoansFromFile();
        Account.setAccountNumCounter(readerWriter.getLatestAccountNumber() + 1);
        Loan.setSuperLoanNumber(readerWriter.getLatestLoanNumber() + 1);
    }

    public void saveDataToFile() {
        readerWriter.writeCustomersToFile();
        readerWriter.writeAccountsToFile();
        readerWriter.writeLoansToFile();
    }


    public void calculateTotalDeposits() {
        double total = 0;
        for (Customer customer : customerHashMap.values()) {
            for (Account account : customer.getAccountList()) {
                total += account.getBalance();
            }
        }
        totalDeposits = total;
    }

    public void calculateTotalLending() {
        double total = 0;
        for (Customer customer : customerHashMap.values()) {
            for (Loan loan : customer.getLoanList()) {
                total += abs(loan.getBalance());
            }
        }
        totalLending = total;
    }

    public boolean verifyLoan(Customer customer, double loanBalance, LoanTypeEnum loanType) {
        double customerLoanOwnership = 0;
        boolean verification;
        double totalCustomerLoans = 0;

        for (int i = 0; i < customer.getLoanList().size(); i++) {
            totalCustomerLoans += abs(customer.getLoanList().get(i).getBalance());
        }
        System.out.println(totalLending);
        calculateTotalLending();
        System.out.println(totalLending);
        calculateTotalDeposits();
        System.out.println(totalDeposits);
        if (totalLending == 0) {
            customerLoanOwnership = 0.0;
        } else {
            customerLoanOwnership = totalCustomerLoans / totalLending;
        }
        System.out.println(customerLoanOwnership);
        if (((abs(loanBalance) + totalLending) < 0.9 * totalDeposits) && (customerLoanOwnership < 0.1)) {
            verification = true;
        } else {
            verification = false;
        }
        System.out.println(verification);

        switch (loanType) {
            case PERSONAL -> {
                if (loanBalance > 45000) {
                    verification = false;
                }
            }
            case HOME -> {
                if (loanBalance > 2000000) {
                    verification = false;
                }
            }
            case CAR -> {
                if (loanBalance > 50000) {
                    verification = false;
                }
            }
        }
        return verification;
    }
    
    public void createNewCustomer(String firstName, String lastName, String email, String password) {
        customerHashMap.put(email, new Customer(firstName, lastName, email, password));
    }

    public void createNewCustomer(Customer customer) {
        this.customerHashMap.put(customer.getEmail(), customer);
    }

    public void createNewAccount(AccountType accountType, Customer customer) {
        customer.addAccount(AccountFactory.createNewAccount(accountType, customer.getEmail()));
    }

    public HashMap<String, Customer> getCustomerHashMap() {
        return customerHashMap;
    }

    public void setCustomerHashMap(HashMap<String, Customer> customerHashMap) {
        this.customerHashMap = customerHashMap;
    }

    public String approveCustomerLoan(String email, double loanBalance, LoanTypeEnum loanType, int term, int accountNumber) {
        Customer customer = customerHashMap.get(email);
        String approvalString;
        if (verifyLoan(customer, loanBalance, loanType)) {
            LoanFactory factory = new LoanFactory();
            customer.addLoan(factory.createLoan(loanType, term, loanBalance, email));
            double currentBalance = customer.getAccountHashMap().get(accountNumber).getBalance();
            customer.getAccountHashMap().get(accountNumber).setBalance(currentBalance + loanBalance);
            approvalString = "Loan approved, see accounts";
        } else {
            approvalString = "Loan rejected.";
        }
        return approvalString;
    }

    public String approveCustomerLoan(String email, double loanBalance, LoanTypeEnum loanType, int accountNumber) {
        Customer customer = customerHashMap.get(email);
        String approvalString;
        if (verifyLoan(customer, loanBalance, loanType)) {
            LoanFactory factory = new LoanFactory();
            customer.addLoan(factory.createLoan(loanType, loanBalance, email));
            double currentBalance = customer.getAccountHashMap().get(accountNumber).getBalance();
            customer.getAccountHashMap().get(accountNumber).setBalance(currentBalance + loanBalance);
            approvalString = "Loan approved, see accounts";
        } else {
            approvalString = "Loan rejected.";
        }
        return approvalString;
    }


    public Customer getActiveCustomer() {
        return activeCustomer;
    }

    public void setActiveCustomer(Customer activeCustomer) {
        this.activeCustomer = activeCustomer;
    }

    public boolean authenticateManager(String user, String password) {
        if (user.equals("admin") && password.equals("password")) {
            paymentOveride = true;
            return true;
        } else {
            return false;
        }
    }

    public String overridePayment(Account account, double amount) {
        String result;
        if (paymentOveride) {
            account.setBalance(account.getBalance() - amount);
            paymentOveride = false;
            result = "Payment Successful";
        } else {
            result = "Payment Failed";
        }
        return result;
    }
}
