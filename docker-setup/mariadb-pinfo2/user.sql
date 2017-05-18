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
	ROLE ENUM('USER','MANAGER','SYSTEMADMIN') NOT NULL	
);


INSERT IGNORE INTO RegularUser VALUES 
(1,'Thomas','Martin','ThomasMartin','PasswordTM',1,'MANAGER'),
(2,'Vincent','Cabrini','VincentCabrini','PasswordVC',1,'USER'),
(3,'Nicola','Papale','NicolaPapale','PasswordNP',1,'SYSTEMADMIN')
;

SELECT * FROM RegularUser
