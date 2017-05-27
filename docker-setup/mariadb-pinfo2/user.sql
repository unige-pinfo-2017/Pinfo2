#!/usr/bin/env mysql --password=admin
CREATE DATABASE IF NOT EXISTS projetinfodb;
USE projetinfodb;
CREATE TABLE IF NOT EXISTS RegularUser
(
	ID INT PRIMARY KEY NOT NULL,
	FIRSTNAME VARCHAR(25) NOT NULL,
	LASTNAME VARCHAR(25) NOT NULL,
	USERNAME VARCHAR(25) NOT NULL,
	PASSWORD VARCHAR(25) NOT NULL,
	TOKEN INT NOT NULL,
	ROLE VARCHAR(50) NOT NULL	
);


INSERT IGNORE INTO RegularUser VALUES 
(1,'Thomas','Martin','ThomasMartin', SHA2('PasswordTM', 256), 1,'regularUser'),
(2,'Vincent','Cabrini','VincentCabrini',SHA2('PasswordVC', 256), 1,'regularUser'),
(3,'Nicola','Papale','NicolaPapale',SHA2('PasswordNP', 256),1,'regularUser')
;

SELECT * FROM RegularUser
