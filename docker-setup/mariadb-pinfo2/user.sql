#!/usr/bin/env mysql --password=admin
CREATE DATABASE IF NOT EXISTS projetinfodb;
USE projetinfodb;
CREATE TABLE IF NOT EXISTS RegularUser
(
	firstName VARCHAR(25) NOT NULL,
	lastName VARCHAR(25) NOT NULL,
	username VARCHAR(25) PRIMARY KEY NOT NULL,
	password VARCHAR(25) NOT NULL,
	role VARCHAR(50) NOT NULL,
	token VARCHAR(50) NOT NULL,
	status BOOLEAN NOT NULL
);


INSERT IGNORE INTO RegularUser VALUES ('Vincent','Cabrini','VincentCabrini','PasswordVC','regularUser', 'abcdefghijk', FALSE);
INSERT IGNORE INTO RegularUser VALUES ('admin','admin','admin','admin','admin', 'qwertzuiop', FALSE);
INSERT IGNORE INTO RegularUser VALUES ('Beni','Broohm','benibroohm','123','regularUser', 'zzzzzzzzz', FALSE);


CREATE TABLE IF NOT EXISTS Device
(
	id VARCHAR(25) PRIMARY KEY NOT NULL,
	workstation VARCHAR(25),
	type ENUM('HUB','LIGHT','SOCKET') NOT NULL
);

SELECT * FROM RegularUser;

INSERT IGNORE INTO Device VALUES ('Light1','WS1','LIGHT'),('Light2','WS2','LIGHT');

INSERT IGNORE INTO Device VALUES ('Socket1','WS1','SOCKET'),('Socket2','WS1','SOCKET'),('Socket3','WS1','SOCKET'),('Socket4','WS1','SOCKET'),('Socket5','WS1','SOCKET'),('Socket6','WS1','SOCKET');

INSERT IGNORE INTO Device VALUES ('Socket7','WS2','SOCKET'),('Socket8','WS2','SOCKET'),('Socket9','WS2','SOCKET'),('Socket10','WS2','SOCKET'),('Socket11','WS2','SOCKET'),('Socket12','WS2','SOCKET');

INSERT IGNORE INTO Device VALUES ('Hub1','WS1','HUB'), ('Hub2','WS2','HUB');
