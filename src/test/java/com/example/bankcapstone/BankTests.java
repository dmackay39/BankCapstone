package com.example.bankcapstone;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BankTests {

    BankStub uut;

    Customer testCustomer;

    @BeforeEach
    public void setUp(){
        uut = new BankStub();
        testCustomer = new Customer("first","surname","email", "pass");
        uut.setTotalDeposits(100000);
        uut.setTotalLending(70000);
    }

    @AfterEach
    public void tearDown(){
        uut = null;
        testCustomer = null;
    }

    @ParameterizedTest
    @CsvSource({"20001,CAR,false","19999,CAR,true","20000,CAR,false"
    ,"20001,PERSONAL,false","19999,PERSONAL,true","20000,PERSONAL,false",
            "20001,HOME,false","19999,HOME,true","20000,HOME,false"})
    public void testLoanExceedsBankDepositLimit(double loanBalance, LoanTypeEnum loanType, boolean expectedResult){
        boolean actualResult = uut.verifyLoan(testCustomer,loanBalance,loanType);
        boolean compare = actualResult == expectedResult;
        Assertions.assertTrue(compare);
    }
}
