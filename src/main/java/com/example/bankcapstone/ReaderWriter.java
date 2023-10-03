package com.example.bankcapstone;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
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
}
