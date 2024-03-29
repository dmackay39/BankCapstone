package com.example.bankcapstone;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ReaderWriter {

    private final String customerPath = "src/main/resources/com/example/bankcapstone/customers.csv";
    private final String loanPath = "src/main/resources/com/example/bankcapstone/loans.csv";
    private final String accountPath = "src/main/resources/com/example/bankcapstone/accounts.csv";

    private int maxAccountNumber = 1000000;
    private int maxLoanNumber = 2000000;

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
                maxAccountNumber = Math.max(maxAccountNumber,accountNumber);
                String[] dateNums = tokens[3].split("-");
                LocalDate openingDate = LocalDate.of(Integer.parseInt(dateNums[0]), Integer.parseInt(dateNums[1]), Integer.parseInt(dateNums[2]));
                Double balance = Double.parseDouble(tokens[4]);

                // checking if the account has matured and if do then turn it to a Savings Account
                if (accountType.equals(AccountType.CD) && hasMatured(openingDate, Integer.parseInt(tokens[7]))){
                    accountType = AccountType.SAVINGS;
                    // Need to update the interest as well
                    tokens[5] = "0.05";
                }
                
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

                newAccount.payInterest();
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
        FileInputStream fileInputStream = null;
        Scanner scanner = null;

        try {
            fileInputStream = new FileInputStream(loanPath);
            scanner = new Scanner(fileInputStream);
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String[] tokens = scanner.nextLine().split(",");
//                Arrays.stream(tokens).forEach((token) -> System.out.println(token));

                LoanTypeEnum loanType = LoanTypeEnum.valueOf(tokens[0]);
                String username = tokens[1];
                Integer loanNumber = Integer.parseInt(tokens[2]);
                maxLoanNumber = Math.max(maxLoanNumber, loanNumber);
                String[] dateNums = tokens[3].split("-");
                LocalDate openingDate = LocalDate.of(Integer.parseInt(dateNums[0]), Integer.parseInt(dateNums[1]), Integer.parseInt(dateNums[2]));
                Double balance = Double.parseDouble(tokens[4]);
                Double interestRate = Double.parseDouble(tokens[5]);

                dateNums = tokens[6].split("-");
                LocalDate interestAddedDate = LocalDate.of(Integer.parseInt(dateNums[0]), Integer.parseInt(dateNums[1]), Integer.parseInt(dateNums[2]));

                LoanFactory loanFactory = new LoanFactory();
                Loan newLoan = loanFactory.createLoan(loanType, balance, openingDate, loanNumber, username);
                newLoan.setInterestPaidDate(interestAddedDate);
                newLoan.setInterestRate(interestRate);

                switch (loanType) {
                    case CAR, HOME: {
                        dateNums = tokens[7].split("-");
                        LocalDate endDate = LocalDate.of(Integer.parseInt(dateNums[0]), Integer.parseInt(dateNums[1]), Integer.parseInt(dateNums[2]));
                        newLoan.setEndDate(endDate);
                    }
                }
                newLoan.addInterest();
                Bank.getInstance().getCustomerHashMap().get(username).addLoan(newLoan);
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
        System.out.println("Loans loaded.");
    }

    public void writeCustomersToFile() {
        HashMap<String, Customer> customerHashMap = Bank.getInstance().getCustomerHashMap();
        List<String> customerDetails = new LinkedList<>();
        try {
            FileWriter fileWriter = new FileWriter(customerPath);
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
    }

    public void writeAccountsToFile() {
        HashMap<String, Customer> customerHashMap = Bank.getInstance().getCustomerHashMap();
        List<String> accountDetails = new LinkedList<>();
        try {
            FileWriter fileWriter = new FileWriter(accountPath);
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
                    accountDetails.add(account.getInterestPaidDate() == null ? account.getAccountStartDate().toString() : account.getInterestPaidDate().toString());
                    accountDetails.add(Integer.toString(account.getTermLength()));
                    fileWriter.write("\n" + String.join(",", accountDetails));
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeLoansToFile() {
        HashMap<String, Customer> customerHashMap = Bank.getInstance().getCustomerHashMap();
        List<String> loanDetails = new LinkedList<>();
        try {
            FileWriter fileWriter = new FileWriter(loanPath);
            fileWriter.write("Loan Type,Email,Loan Number,Opening Date,Balance,Interest Rate,Interest Added Date,End Date");
            for (Customer customer : customerHashMap.values()) {
                for (Loan loans : customer.getLoanList()) {
                    loanDetails.clear();
                    loanDetails.add(loans.getLoanType().toString());
                    loanDetails.add(customer.getEmail());
                    loanDetails.add(Integer.toString(loans.getLoanNumber()));
                    loanDetails.add(loans.getStartDate().toString());
                    loanDetails.add(Double.toString(loans.getBalance()));
                    loanDetails.add(Double.toString(loans.getInterestRate()));
                    loanDetails.add(loans.getInterestPaidDate().toString());
                    loanDetails.add(loans.getEndDate() == null ? "" : loans.getEndDate().toString());
                    fileWriter.write("\n" + String.join(",", loanDetails));
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getLatestAccountNumber(){
        return this.maxAccountNumber;
    }

    public int getLatestLoanNumber(){
        return this.maxLoanNumber;
    }


    private boolean hasMatured(LocalDate openDate,int termLength){
        if (Math.abs(openDate.until(LocalDate.now()).getYears()) >= termLength) {
            return true;
        }
        return false;
    }
}




