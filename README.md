# BankCapstone

We will simulate the workings of a Bank.  The bank will have customers and accounts and make loans.

This is a large project.  Break into sub teams and have each sub team handle a part of the solution.  You may have either 2 or 3 sub teams.

Work on one feature at a time.  Do not try to design the entire application at once.
(Example:  Work on adding a new customer.  One sub team builds the UI for this.  The other creates the customer class 
and figures out how to add the new customer to the banks list of customers)


Project details
We will need the following classes
	Bank
	Customer
	Account
	Loan

Account will have 3 sub classes:
	SavingsAccount
	CheckingAccount
	CDAccount
		CD accounts have a specified term

Loan will have 3 sub classes
	HomeLoan (aka mortgage)
		Home loans will be for 15, 20 or 30 years
		Maximum amount for a home loan is 2,000,000
	CarLoan
		Car loans will be for 3, 4 or 5 years
		Maximum amount for a car loan is 50,000
	PersonalLoan
		Personal loans will not have a term
		Maximum amount for a personal loan is 45,000

The Bank will be responsible for knowing all of its Customers, Loans and Accounts


Restrictions and Features
	All interactions with the app will be through a UI
	The initial information on customers and accounts will be read in from files
		BONUS:  Enter the relavent information in an Excel spreadsheet rather than a text file.  Export the Excel file as a .csv and read from it
	We can add new customers and new accounts
	The system will assign accounts unique account numbers when an account is created
	Customers can make deposits and withdrawals from their accounts
	BONUS:  Customers can transfer money between their accounts.  The transfer may not cause an overdraw condition
	Customers can make payments on their loans
	Savings accounts will pay interest.  Checking accounts will not
	We can make new loans
		The bank may only lend 90% of the sum of all its deposits.
		No customer can have more than 10% of the total loans the bank has made
	
	Customers can pay bills from their checking account
		If a making a payment would overdraw the checking account, we will ask the customer
		if they would like to move money from their savings account to cover the overdraw if:
			the customer has a savings account
			the savings account has sufficient funds to cover the overdraw
		Otherwise, we will decline the payment.  
		(BONUS:  use an exception to have this situation handled on a case by case basis.  A window will pop up and the bank 
		manager will log in and either approve or disapprove of the payment)

Hints (Pssible ways to solve some problems)
Account:  
	Have two constructors,  One will take the account number and balance as parameters.  Use this constructor when creating accounts from the files
	The other will will take no parameters and auto-assign the account number and set the balance to 0.0

	Store the next account number as a static variable.  The actual account number for each account will be kept in the account object
