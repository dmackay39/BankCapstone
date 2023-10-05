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

    @ParameterizedTest
    @CsvSource({"50000,CAR,true","50000.01,CAR,false"
            ,"45000,PERSONAL,true","45000.01,PERSONAL,false",
            "2000000,HOME,true","2000000.01,HOME,false"})
    public void testLoanExceedsAllowedType(double loanBalance, LoanTypeEnum loanType, boolean expectedResult){
        uut.setTotalDeposits(10000000);
        boolean actualResult = uut.verifyLoan(testCustomer,loanBalance,loanType);
        boolean compare = actualResult == expectedResult;
        Assertions.assertTrue(compare);
    }

    @ParameterizedTest
    @CsvSource({"1,CAR,false","1,PERSONAL,false",
            "1,HOME,false"})
    public void testLoanExceedsCustomerOwnership(double loanBalance, LoanTypeEnum loanType, boolean expectedResult){
        uut.setTotalLending(100);
        boolean actualResult = uut.verifyLoan(testCustomer,loanBalance,loanType);
        boolean compare = actualResult == expectedResult;
        Assertions.assertTrue(compare);
    }
    @ParameterizedTest
    @CsvSource({"1,CAR,true","1,PERSONAL,true",
            "1,HOME,true"})
    public void testLoanUnderCustomerOwnership(double loanBalance, LoanTypeEnum loanType, boolean expectedResult){
        uut.setTotalLending(101);
        boolean actualResult = uut.verifyLoan(testCustomer,loanBalance,loanType);
        boolean compare = actualResult == expectedResult;
        Assertions.assertTrue(compare);
    }
}
