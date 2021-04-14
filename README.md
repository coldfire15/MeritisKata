# MeritisKata

##Bank account kata

Think of your personal bank account experience. When in doubt, go for the simplest solution

Requirements

Deposit and Withdrawal
Account statement (date, amount, balance)
Statement printing
The expected result is a service API, and its underlying implementation, that meets the expressed needs.
Nothing more, especially no UI, no persistence.

##User Stories

###US 1:

In order to save money
As a bank client
I want to make a deposit in my account

###US 2:

In order to retrieve some or all of my savings
As a bank client
I want to make a withdrawal from my account

###US 3:

In order to check my operations
As a bank client
I want to see the history (operation, date, amount, balance) of my operations


###
POST http://localhost:8080/client/create

{
    "client_id":1,
    "name":"Toto",
    "last_name":"TOTO",
    "birthday":"1995-07-09",
    "history":"History"

}

###
GET /account

###
POST http://localhost:8080/account/createAccount/{{id}}
{
   "account_id":1,
   "balance":2000.00,
   "amount":00.00,
   "date":"2021-04-14"

}
###
GET /client

###
GET http://localhost:8080/client/history/{{id}}

###
POST http://localhost:8080/account/withdrawal/{{clientid}}/{{accountid}}/{{amount}}

###
POST http://localhost:8080/account/saveMoney/{{clientid}}/{{accountid}}/{{amount}}
