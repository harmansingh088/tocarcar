CREATE DATABASE tocarcar;

USE tocarcar;

CREATE TABLE user (
                      userId INTEGER NOT NULL AUTO_INCREMENT,
                      firstName VARCHAR(100) NOT NULL,
                      lastName VARCHAR(100) NOT NULL,
                      email VARCHAR(100) NOT NULL,
                      password VARCHAR(100) NOT NULL,
                      userType VARCHAR(10) NOT NULL,
                      phoneNumber VARCHAR(100) NOT NULL,
                      age INTEGER NOT NULL,
                      PRIMARY KEY (userId),
                      CONSTRAINT checkUserType CHECK (userType="admin" OR userType='customer')
);

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


CREATE TABLE carPosting (
                            carPostingId INTEGER NOT NULL AUTO_INCREMENT,
                            postingDate DATE NOT NULL,
                            description VARCHAR(500) NOT NULL,
                            price double NOT NULL,
                            carId INTEGER NOT NULL,
                            ownerId INTEGER NOT NULL,
                            renteeId INTEGER,
                            status VARCHAR(50) NOT NULL,
                            PRIMARY KEY (carPostingId),
                            FOREIGN KEY (carId) REFERENCES car(carId),
                            FOREIGN KEY (ownerId) REFERENCES user(userId),
                            FOREIGN KEY (renteeId) REFERENCES user(userId)
);






