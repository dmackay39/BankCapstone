package com.example.bankcapstone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.abs;

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

    public boolean verifyLoan(Customer customer, double loanBalance){
        double customerLoanOwnership = 0;
        boolean verification;
        double totalCustomerLoans = 0;
        for (int i = 0; i < customer.getLoanList().size(); i++){
            totalCustomerLoans += customer.getLoanList().get(i).getBalance();
        }
        customerLoanOwnership = totalCustomerLoans/totalLending;
        if ((abs(loanBalance + totalLending) < 0.9*totalDeposits) && (customerLoanOwnership < 0.1)){
            verification = true;
        }
        else {
            verification = false;
        }
        return verification;
    }

}
