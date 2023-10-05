package com.example.bankcapstone;

import static java.lang.Math.abs;

public class BankStub implements IBank {

    double totalLending;
    double totalDeposits;
    @Override
    public boolean verifyLoan(Customer customer, double loanBalance, LoanTypeEnum loanType) {
        double customerLoanOwnership = 0;
        boolean verification;
        double totalCustomerLoans = 0;

        switch (loanType) {
            case PERSONAL -> {
                if (loanBalance > 45000) {
                    verification = false;
                }
            }
            case HOME -> {
                if (loanBalance > 2000000) {
                    verification = false;
                }
            }
            case CAR -> {
                if (loanBalance > 50000) {
                    verification = false;
                }
            }
        }

        for (int i = 0; i < customer.getLoanList().size(); i++) {
            totalCustomerLoans += abs(customer.getLoanList().get(i).getBalance());
        }
        System.out.println(totalLending);
        System.out.println(totalLending);
        System.out.println(totalDeposits);
        if (totalLending == 0) {
            customerLoanOwnership = 0.0;
        } else {
            customerLoanOwnership = totalCustomerLoans / totalLending;
        }
        System.out.println(customerLoanOwnership);
        if (((abs(loanBalance) + totalLending) < 0.9 * totalDeposits) && (customerLoanOwnership < 0.1)) {
            verification = true;
        } else {
            verification = false;
        }
        System.out.println(verification);
        return verification;
    }

    @Override
    public void setTotalDeposits(double total) {
        this.totalDeposits = total;
    }

    @Override
    public void setTotalLending(double total) {
        this.totalLending = total;
    }
}
