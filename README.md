# springboot-payment-system-demo

## Installation

The project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies

## Database configuration

Create a MySQL database with the name springbootdb and add the credentials to /resources/application.properties.
The default ones are :
```
spring.datasource.url = jdbc:mysql://localhost:3306/payment
spring.datasource.username = root
spring.datasource.password = 
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto=none
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
```

## Usage

Run the project through the IDE and head out to http://localhost:8080

## Endpoints :

1) Wallet Controller
```
/initiateTransaction  (post)
	#create a new transaction object in DB

/transactions/{user_id}  (get)
	#return all transactions of a particular user(id) from DB

/reverseTransaction (post)
	#create 2 new transactions while transferring money from 1 wallet to another

/transaction/details/{txn_id}  (get)
	#enquire transaction status

```

2) Login Controller
```
/login  (post)
	#authenticate the user by userid and password

/signup  (get)
	#register a new user and initiate a new wallet for them
```
