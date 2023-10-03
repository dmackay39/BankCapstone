package com.example.bankcapstone;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ReaderWriter {

    private final String customerPath = "src/main/resources/com/example/bankcapstone/customers.csv";
    private final String loanPath = "src/main/resources/com/example/bankcapstone/loans.csv";
    private final String accountPath = "src/main/resources/com/example/bankcapstone/accounts.csv";

    private final String tempFilePath = "src/main/resources/com/example/bankcapstone/temp.csv";

    public void readCustomersFromFile() {
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
//            for (String username : customerDb.keySet()){
//                System.out.println(username + " -> " + customerDb.get(username));
//            }

        } catch (FileNotFoundException e) {
            System.out.println("error reading from file");
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                System.out.println("Cannot close file!");
            }
        }
        Bank.getInstance().setCustomerHashMap(customerDb);
        System.out.println("Customers loaded...");
    }

    public void readAccountsFromFile() {

        FileInputStream fileInputStream = null;
        Scanner scanner = null;

        try {
            fileInputStream = new FileInputStream(accountPath);
            scanner = new Scanner(fileInputStream);
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(",");
                //Arrays.stream(tokens).forEach((token) -> System.out.println(token));

                AccountType accountType = AccountType.valueOf(tokens[0]);
                String username = tokens[1];
                Integer accountNumber = Integer.parseInt(tokens[2]);
                String[] dateNums = tokens[3].split("-");
                LocalDate openingDate = LocalDate.of(Integer.parseInt(dateNums[0]), Integer.parseInt(dateNums[1]), Integer.parseInt(dateNums[2]));
                Double balance = Double.parseDouble(tokens[4]);

                Account newAccount = AccountFactory.createNewAccount(accountType, username, accountNumber, balance, openingDate);

                switch (accountType) {
                    case CD: {
                        newAccount.setTermLength(Integer.parseInt(tokens[7]));
                    }
                    case SAVINGS: {
                        newAccount.setInterestRate(Double.parseDouble(tokens[5]));
                        dateNums = tokens[6].split("-");
                        LocalDate intDate = LocalDate.of(Integer.parseInt(dateNums[0]), Integer.parseInt(dateNums[1]), Integer.parseInt(dateNums[2]));
                        newAccount.setInterestPaidDate(intDate);
                    }
                }
                Bank.getInstance().getCustomerHashMap().get(username).addAccount(newAccount);
            }

        } catch (FileNotFoundException e) {
            System.out.println("error reading from file");
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                System.out.println("Cannot close file!");
            }
        }
        System.out.println("Accounts loaded...");
    }

    public void readLoansFromFile() {

        System.out.println("Loans loaded.");
    }

    public void writeCustomersToFile() {
        HashMap<String, Customer> customerHashMap = Bank.getInstance().getCustomerHashMap();
        List<String> customerDetails = new LinkedList<>();
        try {
            FileWriter fileWriter = new FileWriter(tempFilePath);
            fileWriter.write("First Name,Last Name,Email,Password");
            for (Customer customer : customerHashMap.values()) {
                customerDetails.clear();
                customerDetails.add(customer.getFirstName());
                customerDetails.add(customer.getLastName());
                customerDetails.add(customer.getEmail());
                customerDetails.add(customer.getPassword());
                fileWriter.write("\n" + String.join(",", customerDetails));
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File originalFile = new File(customerPath);
        File tempFile = new File(tempFilePath);
        System.out.println(originalFile.delete() ? "Original Customers File Deleted" : "Failed to Delete Customers File");
        System.out.println(tempFile.renameTo(originalFile) ? "Temp Customers File Renamed" : "Failed to Rename Temp Customers File");
    }

    public void writeAccountsToFile() {
        HashMap<String, Customer> customerHashMap = Bank.getInstance().getCustomerHashMap();
        List<String> accountDetails = new LinkedList<>();
        try {
            FileWriter fileWriter = new FileWriter(tempFilePath);
            fileWriter.write("Account Type,Email,Account Number,Opening Date,Balance,Interest Rate,Interest Paid Date,Term");
            for (Customer customer : customerHashMap.values()) {
                for (Account account : customer.getAccountList()) {
                    accountDetails.clear();
                    accountDetails.add(account.getAccountType().toString());
                    accountDetails.add(customer.getEmail());
                    accountDetails.add(Integer.toString(account.getAccountNumber()));
                    accountDetails.add(account.getAccountStartDate().toString());
                    accountDetails.add(Double.toString(account.getBalance()));
                    accountDetails.add(Double.toString(account.getInterestRate()));
                    accountDetails.add(account.getInterestPaidDate() == null ? "" : account.getInterestPaidDate().toString());
                    accountDetails.add(Integer.toString(account.getTermLength()));
                    fileWriter.write("\n" + String.join(",", accountDetails));
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File originalFile = new File(accountPath);
        File tempFile = new File(tempFilePath);
        System.out.println(originalFile.delete() ? "Original Account File Deleted" : "Failed to Delete Original Account File");
        System.out.println(tempFile.renameTo(originalFile) ? "Temp Account File Renamed" : "Failed to Rename Temp Account File");
    }

    /*public void writeCustomersToFile() {
        HashMap<String, Customer> customerHashMap = Bank.getInstance().getCustomerHashMap();
        List<String> customerDetails = new LinkedList<>();
        try {
            FileWriter fileWriter = new FileWriter(tempFilePath);
            fileWriter.write("First Name,Last Name,Email,Password");
            for (Customer customer : customerHashMap.values()) {
                customerDetails.clear();
                customerDetails.add(customer.getFirstName() == null ? "" : customer.getFirstName());
                customerDetails.add(customer.getLastName() == null ? "" : customer.getLastName());
                customerDetails.add(customer.getEmail() == null ? "" : customer.getEmail());
                customerDetails.add(customer.getPassword() == null ? "" : customer.getPassword());
                fileWriter.write("\n" + String.join(",", customerDetails));
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File originalFile = new File(customerPath);
        File tempFile = new File(tempFilePath);
        System.out.println(originalFile.delete() ? "Original File Deleted" : "Failed to Delete Original File");
        System.out.println(tempFile.renameTo(originalFile) ? "Temp File Renamed" : "Failed to Rename Temp File");
    }*/


    public static void main(String[] args) {
        ReaderWriter readerWriter = new ReaderWriter();
        readerWriter.readCustomersFromFile();

        for (Customer customer : Bank.getInstance().getCustomerHashMap().values()){
            System.out.println(customer);
        }

        readerWriter.writeCustomersToFile();

        readerWriter.readAccountsFromFile();

        for (Account account : Bank.getInstance().getCustomerHashMap().get("bobby.ayvazov@email.com").getAccountList()){
            System.out.println(account);
        }

    }


}

