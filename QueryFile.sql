drop database tocarcar;

create database tocarcar;

use tocarcar;

CREATE TABLE user (
    userId INTEGER NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    userType VARCHAR(10),
    phoneNumber VARCHAR(100) NOT NULL,
    age INTEGER NOT NULL,
    PRIMARY KEY (userId),
	CONSTRAINT checkUserType CHECK (userType="admin" OR userType='customer')
);

select * from user;

DROP TABLE carPhoto;
DROP TABLE car;

CREATE TABLE car (
    carId INTEGER NOT NULL AUTO_INCREMENT,
    company VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    colour VARCHAR(100) NOT NULL,
    year INTEGER NOT NULL,
    description VARCHAR(500) NOT NULL,
    price double NOT NULL,
    userId INTEGER NOT NULL,
    PRIMARY KEY (carId),
    FOREIGN KEY (userId) REFERENCES user(userId)
);

CREATE TABLE carPhoto (
    photoId INTEGER NOT NULL AUTO_INCREMENT,
    photo MEDIUMBLOB NOT NULL,
    carId INTEGER NOT NULL,
    PRIMARY KEY (photoId),
    FOREIGN KEY (carId) REFERENCES car(carId)
);




