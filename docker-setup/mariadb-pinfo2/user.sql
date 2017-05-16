#!/usr/bin/env mysql --password=admin
CREATE DATABASE IF NOT EXISTS meskyne;
USE meskyne;
CREATE TABLE IF NOT EXISTS RegularUser
(
	username VARCHAR(25) PRIMARY KEY NOT NULL,
	firstName VARCHAR(25) NOT NULL,
	lastName VARCHAR(25) NOT NULL,
	password VARCHAR(25) NOT NULL,
	token INT,
);


INSERT IGNORE INTO RegularUser VALUES 
('Thomas','Martin','ThomasMartin','PasswordTM'),
('Vincent','Cabrini','VincentCabrini','PasswordVC'),
('Nicola','Papale','NicolaPapale','PasswordNP')
;

SELECT * FROM RegularUser
