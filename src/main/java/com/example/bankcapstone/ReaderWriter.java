package com.example.bankcapstone;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ReaderWriter {

    private final String customerPath = "src/main/resources/com/example/bankcapstone/customers.csv";
    private final String loanPath = "src/main/resources/com/example/bankcapstone/loans.csv";
    private final String accountPath = "src/main/resources/com/example/bankcapstone/accounts.csv";

    public void readCustomersFromFile(){
        HashMap<String, Customer> customerDb = new HashMap<>();
        FileInputStream fileInputStream = null;
        Scanner scanner = null;
        try {
            fileInputStream = new FileInputStream(customerPath);
            scanner = new Scanner(fileInputStream);
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(",");
                customerDb.put(tokens[2], new Customer(tokens[0], tokens[1], tokens[2], tokens[3]));
            }
            for (String username : customerDb.keySet()){
                System.out.println(username + " -> " + customerDb.get(username));
            }

        } catch (FileNotFoundException e){
            System.out.println("error reading from file");
        } finally {
            try{
                fileInputStream.close();
            } catch (IOException e){
                System.out.println("Cannot close file!");
            }
        }
        Bank.getInstance().setCustomerHashMap(customerDb);
    }

    public void readAccountsFromFile(){
        //HashMap<String, Customer> customerDb = Bank.getInstance().getCustomerHashMap();
        FileInputStream fileInputStream = null;
        Scanner scanner = null;

        try {
            fileInputStream = new FileInputStream(accountPath);
            scanner = new Scanner(fileInputStream);
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(",");
                Arrays.stream(tokens).forEach((token) -> System.out.println(token));

                AccountType accountType = AccountType.valueOf(tokens[0]);
                String username = tokens[1];
                Integer accountNumber = Integer.parseInt(tokens[2]);
                String[] dateNums = tokens[3].split("/");
                LocalDate openingDate = LocalDate.of(Integer.parseInt(dateNums[2]), Integer.parseInt(dateNums[1]), Integer.parseInt(dateNums[0]));
                Double balance = Double.parseDouble(tokens[4]);

                Account newAccount = AccountFactory.createNewAccount(accountType, username,accountNumber,balance, openingDate);

                switch (accountType){
                    case CD: {
                        newAccount.setTermLength(Integer.parseInt(tokens[7]));
                    }
                    case CHECKING:{
                        newAccount.setInterestRate(Double.parseDouble(tokens[5]));
                        dateNums = tokens[6].split("/");
                        LocalDate intDate = LocalDate.of(Integer.parseInt(dateNums[2]), Integer.parseInt(dateNums[1]), Integer.parseInt(dateNums[0]));
                        newAccount.setInterestPaidDate(intDate);
                    }
                }


//
//                Bank.getInstance().getCustomerHashMap().get(tokens[1]).addAccount(account);

            }
//            List<Account> accounts =  Bank.getInstance().getCustomerHashMap().get("bobby.ayvazov@email.com").getAccountList();
//
//            for(Account account : accounts){
//                System.out.println(account.getClass().getSimpleName() + " " + account.getAccountNumber() + " -> " + account.getBalance());
//            }

        } catch (FileNotFoundException e){
            System.out.println("error reading from file");
        } finally {
            try{
                fileInputStream.close();
            } catch (IOException e){
                System.out.println("Cannot close file!");
            }
        }

    }

    public static void main(String[] args){

        ReaderWriter readerWriter = new ReaderWriter();
        readerWriter.readAccountsFromFile();





//        for(Account account : accounts){
//            System.out.println(account.getClass().getSimpleName() + " " + account.getAccountNumber() + " -> " + account.getBalance());
//        }

    }
}
