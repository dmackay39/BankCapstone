package com.example.bankcapstone;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class ReaderWriterTests {

    Bank bank;
    ReaderWriter uut;

    @BeforeEach
    public void setUut(){
        uut = new ReaderWriter();
        bank = Bank.getInstance();
    }

    @AfterEach
    public void tearDown(){
        uut = null;
        bank = null;
    }

    @Test
    public void readingCustomersFromFile(){
        uut.readCustomersFromFile();
        assert(bank.getCustomerHashMap().keySet().size() == 7);
    }


    @Test
    public void readingAccountsFromFile(){
        uut.readCustomersFromFile();
        uut.readAccountsFromFile();
        int actualResult = 0;
        int expectedResult = 9;
        for (Customer customer : bank.getCustomerHashMap().values()){
            actualResult += customer.getAccountList().size();
        }
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readingLoansFromFile(){
        uut.readCustomersFromFile();
        uut.readLoansFromFile();
        int actualResult = 0;
        int expectedResult = 1;
        for (Customer customer : bank.getCustomerHashMap().values()){
            actualResult += customer.getLoanList().size();
        }
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readingMaxAccountNumber(){
        uut.readCustomersFromFile();
        uut.readAccountsFromFile();
        int actualResult = uut.getLatestAccountNumber();
        int expectedResult = 1000009;
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readingMaxLoanNumber(){
        uut.readCustomersFromFile();
        uut.readLoansFromFile();
        int actualResult = uut.getLatestLoanNumber();
        int expectedResult = 2000001;
        assertEquals(actualResult, expectedResult);
    }

    
}
