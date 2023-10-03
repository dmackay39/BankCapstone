package com.example.bankcapstone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bank {
    HashMap<String, Customer> customerHashMap = new HashMap<>();
    List<Account> accountList = new ArrayList<>();
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

    private double totalDeposits;
    private double totalLending;
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

}
