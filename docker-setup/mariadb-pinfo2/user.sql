#!/usr/bin/env mysql --password=admin
CREATE DATABASE IF NOT EXISTS meskyne;
USE meskyne;
CREATE TABLE IF NOT EXISTS RegularUser
(
	id INT PRIMARY KEY NOT NULL,
	firstNane VARCHAR(25) NOT NULL,
	lastName VARCHAR(25) NOT NULL,
	username VARCHAR(25) NOT NULL,
	password VARCHAR(25) NOT NULL,
	token INT NOT NULL,
);


INSERT IGNORE INTO RegularUser VALUES 
(1,'Thomas','Martin','ThomasMartin','PasswordTM',1),
(2,'Vincent','Cabrini','VincentCabrini','PasswordVC',1),
(3,'Nicola','Papale','NicolaPapale','PasswordNP',1)
;

SELECT * FROM RegularUser
