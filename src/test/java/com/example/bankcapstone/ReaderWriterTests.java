package com.example.bankcapstone;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class ReaderWriterTests {

    Bank bank;
    Bank b;
    ReaderWriter uut;
    ReaderWriter r;

    @BeforeEach
    public void setUut(){
        uut = new ReaderWriter();
        bank = Bank.getInstance();
        b = mock(Bank.class);
        r = mock(ReaderWriter.class);
    }

    @AfterEach
    public void tearDown(){
        uut = null;
        bank = null;
    }

    @Test
    public void testInitialAccountCount(){
        int expectedResult = 1000000;
        int actualResult = uut.getLatestAccountNumber();
    }
    @Test
    public void testInitialLoanCount(){
        int expectedResult = 2000000;
        int actualResult = uut.getLatestLoanNumber();
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

    @ParameterizedTest
    @CsvSource({"2023, 10, 4, 1, false", "2022, 10, 4, 1, true", "2022, 10, 4, 2, false","2022, 10, 6, 1, false", "2022, 10, 5, 1, true"})
    public void hasMaturedTests(int year, int month, int day, int termLength, boolean expectedResult){

        boolean actualResult = uut.hasMatured(LocalDate.of(year, month,day), termLength);
        assertEquals(expectedResult, actualResult);
    }
    
}
