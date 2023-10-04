package com.example.bankcapstone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.abs;

public class Bank {

    private HashMap<String, Customer> customerHashMap = new HashMap<>();
//    private List<Account> accountList = new ArrayList<>();
//    private List<Loan> loanList = new ArrayList<>();
    private double totalDeposits;
    private double totalLending;
    private ReaderWriter readerWriter;

    private static Bank bankInstance;

    private Bank(){
        readerWriter = new ReaderWriter();
    }

    public static Bank getInstance(){
        if (bankInstance == null){
            bankInstance = new Bank();
        }
        return bankInstance;
    }

    public void populateBankDatabase(){
        readerWriter.readCustomersFromFile();
        readerWriter.readAccountsFromFile();
        readerWriter.readLoansFromFile();
        Account.setAccountNumCounter(readerWriter.getLatestAccountNumber());
        Loan.setSuperLoanNumber(readerWriter.getLatestLoanNumber());

    }

    public void saveDataToFile(){
        readerWriter.writeCustomersToFile();
        readerWriter.writeAccountsToFile();
        readerWriter.writeLoansToFile();
    }


//    public void populateAccountList(){
//        for (String key: customerHashMap.keySet()){
//            for (int i = 0; i <customerHashMap.get(key).getAccountList().size(); i++ ){
//                accountList.add(customerHashMap.get(key).getAccountList().get(i));
//            }
//        }
//    }

//    public void populateLoanList(){
//        for (String key: customerHashMap.keySet()){
//            for (int i=0; i <customerHashMap.get(key).getLoanList().size(); i++ ){
//                loanList.add(customerHashMap.get(key).getLoanList().get(i));
//            }
//        }
//    }


    public void calculateTotalDeposits(){
        double total = 0;
        for (Customer customer: customerHashMap.values()){
            for (Account account: customer.getAccountList()){
                total += account.getBalance();
            }
        }
        totalDeposits = total;
    }

    public void calculateTotalLending(){
        double total = 0;
        for (Customer customer: customerHashMap.values()){
            for (Loan loan: customer.getLoanList()){
                total += abs(loan.getBalance());
            }
        }
        totalLending = total;
    }
    
    public boolean verifyLoan(Customer customer, double loanBalance, LoanTypeEnum loanType){
        double customerLoanOwnership = 0;
        boolean verification;
        double totalCustomerLoans = 0;

        switch (loanType){
            case PERSONAL -> {
                if(loanBalance > 45000){verification = false;}
            }
            case HOME -> {
                if (loanBalance > 2000000){verification = false;}
            }
            case CAR -> {
                if (loanBalance > 50000) {verification = false;}}
        }

        for (int i = 0; i < customer.getLoanList().size(); i++){
            totalCustomerLoans += abs(customer.getLoanList().get(i).getBalance());
        }
        System.out.println(totalLending);
        calculateTotalLending();
        System.out.println(totalLending);
        calculateTotalDeposits();
        System.out.println(totalDeposits);
        if (totalLending ==0){
            customerLoanOwnership = 0.0;
        } else{
            customerLoanOwnership = totalCustomerLoans/totalLending;
        }
        System.out.println(customerLoanOwnership);
        if (((abs(loanBalance) + totalLending) < 0.9*totalDeposits) && (customerLoanOwnership < 0.1)){
            verification = true;
        }
        else {
            verification = false;
        }
        System.out.println(verification);
        return verification;
    }

    public void createNewCustomer(String firstName, String lastName, String email, String password){
        customerHashMap.put(email,new Customer(firstName,lastName,email,password));
    }
    public void createNewCustomer(Customer customer){
        this.customerHashMap.put(customer.getEmail(), customer);
    }

    public void createNewAccount(AccountType accountType, Customer customer){
        customer.addAccount(AccountFactory.createNewAccount(accountType,customer.getEmail()));
        }

    public HashMap<String, Customer> getCustomerHashMap() {
        return customerHashMap;
    }

    public void setCustomerHashMap(HashMap<String, Customer> customerHashMap) {
        this.customerHashMap = customerHashMap;
    }
    public void approveCustomerLoan(String email, double loanBalance, LoanTypeEnum loanType, int term, int accountNumber){
        Customer customer = customerHashMap.get(email);
        if (verifyLoan(customer, loanBalance, loanType)){
            LoanFactory factory = new LoanFactory();
            customer.addLoan(factory.createLoan(loanType,term,loanBalance,email));
            double currentBalance = customer.getAccountHashMap().get(accountNumber).getBalance();
            customer.getAccountHashMap().get(accountNumber).setBalance(currentBalance + loanBalance);
        }
    }
    public void approveCustomerLoan(String email, double loanBalance, LoanTypeEnum loanType, int accountNumber){
        Customer customer = customerHashMap.get(email);
        if (verifyLoan(customer, loanBalance, loanType)){
            LoanFactory factory = new LoanFactory();
            customer.addLoan(factory.createLoan(loanType,loanBalance,email));
            double currentBalance = customer.getAccountHashMap().get(accountNumber).getBalance();
            customer.getAccountHashMap().get(accountNumber).setBalance(currentBalance + loanBalance);
        }
    }

    public boolean authenticateManager(String user, String password){
        return (user.equals("admin") && password.equals("password"));
    }

    public String overridePayment(Account account, double amount){
        account.setBalance(account.getBalance()-amount);
        return "Payment Successful";
    }
}
