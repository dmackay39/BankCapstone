package com.example.bankcapstone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bank {

    private HashMap<String, Customer> customerHashMap = new HashMap<>();
    private List<Account> accountList = new ArrayList<>();
    private List<Loan> loanList = new ArrayList<>();
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
    }


    public void populateAccountList(){
        for (String key: customerHashMap.keySet()){
            for (int i=0; i <customerHashMap.get(key).getAccountList().size(); i++ ){
                accountList.add(customerHashMap.get(key).getAccountList().get(i));
            }
        }
    }

    List<Loan> loanList = new ArrayList<>();

    public void populateLoanList(){
        for (String key: customerHashMap.keySet()){
            for (int i=0; i <customerHashMap.get(key).getLoanList().size(); i++ ){
                loanList.add(customerHashMap.get(key).getLoanList().get(i));
            }
        }
    }


    public void calculateTotalDeposits(){
        double total = 0;
        for (int i = 0; i < accountList.size();i++){
            total += accountList.get(i).getBalance();
        }
        totalDeposits = total;
    }

    public void calculateTotalLending(){
        double total = 0;
        for (int i=0; i < loanList.size(); i++){
            total += loanList.get(i).getBalance();
        }
        totalLending = total;
    }

    public void createNewCustomer(String firstName, String lastName, String email, String password){
        customerHashMap.put(email,new Customer(firstName,lastName,email,password));
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
}
