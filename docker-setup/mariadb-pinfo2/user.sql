#!/usr/bin/env mysql --password=admin
CREATE DATABASE IF NOT EXISTS projetinfodb;
USE projetinfodb;
CREATE TABLE IF NOT EXISTS RegularUser
(
	username VARCHAR(25) PRIMARY KEY NOT NULL,
	firstName VARCHAR(25) NOT NULL,
	lastName VARCHAR(25) NOT NULL,
	password VARCHAR(25) NOT NULL,
	role VARCHAR(50) NOT NULL,
	token VARCHAR(50)
);

INSERT IGNORE INTO RegularUser VALUES("VincentCabrini", "Cabrini", "Vincent", sha2("PasswordVC", 256), "RegularUser", "weRzz");
INSERT IGNORE INTO RegularUser VALUES("ThomasMartin", "Martin", "Thomas", sha2("PasswordTM", 256), "RegularUser", "Kieohg");
INSERT IGNORE INTO RegularUser VALUES("VincentCabrini2", "Cabrini", "Vincent", "PasswordVC", "RegularUser", "weRzz");