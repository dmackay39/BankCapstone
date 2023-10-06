package com.example.bankcapstone;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountTests {

    AccountTestingStub uut;

    @BeforeEach
    public void setUut(){
        uut = new AccountTestingStub();
    }

    @AfterEach
    public void tearDown(){
        uut = null;
    }

    @Test
    public void test(){
        uut.setBalance(100.00);
        uut.setStartDate(LocalDate.of(2021, 10, 04));
        uut.setInterestPaidDate(LocalDate.of(2021, 10, 04));
        uut.payInterest();
        assertEquals(105.00, uut.getBalance());
    }
    @Test
    public void testTwo(){
        uut.setBalance(100.00);
        uut.setStartDate(LocalDate.of(2023, 10, 04));
        uut.setInterestPaidDate(LocalDate.of(2023, 10, 04));
        uut.payInterest();
        assertEquals(100.00, uut.getBalance());
    }
    @Test
    public void testThree(){
        uut.setBalance(100.00);
        uut.setStartDate(LocalDate.now());
        uut.setInterestPaidDate(LocalDate.now());
        uut.payInterest();
        assertEquals(100.00, uut.getBalance());
    }
    @Test
    public void testFour(){
        uut.setBalance(100.00);
        uut.setStartDate(LocalDate.of(2022, 10, 04));
        uut.setInterestPaidDate(LocalDate.of(2022, 10, 04));
        uut.payInterest();
        assertEquals(105.00, uut.getBalance());
    }


}
