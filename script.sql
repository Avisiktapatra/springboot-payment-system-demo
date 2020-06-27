use payment;
DROP TABLE IF EXISTS user_account;
CREATE TABLE user_account(id INT PRIMARY KEY AUTO_INCREMENT , email_id VARCHAR(255), password VARCHAR(255));

use payment;
DROP TABLE IF EXISTS user_details;
CREATE TABLE user_details(id INT PRIMARY KEY AUTO_INCREMENT ,first_name VARCHAR(255), last_name VARCHAR(255), phone_number bigint, email_id VARCHAR(255), password VARCHAR(255), city VARCHAR(255), country VARCHAR(255));

use payment;
ALTER TABLE user_details
ADD FOREIGN KEY (wallet_id) REFERENCES wallet(wallet_id); 

use payment;
DROP TABLE IF EXISTS wallet;
CREATE TABLE wallet(wallet_id INT PRIMARY KEY AUTO_INCREMENT ,balance bigint, created_date timestamp);

use payment;
DROP TABLE IF EXISTS txn_details;
CREATE TABLE txn_details(txn_id INT PRIMARY KEY AUTO_INCREMENT, wallet_id int not null, balance bigint, created_date timestamp, to_user_id int, from_user_id int, status varchar(255),FOREIGN KEY (wallet_id)
REFERENCES wallet (wallet_id));
