DROP SCHEMA IF EXISTS CarportTest;
CREATE SCHEMA CarportTest;
USE CarportTest;
DROP TABLE IF EXISTS material_lengths;
DROP TABLE IF EXISTS materials_withlength;
DROP TABLE IF EXISTS materials_nolength;
DROP TABLE IF EXISTS rooflength;
DROP TABLE IF EXISTS rooftype;
DROP TABLE IF EXISTS users_personalinfo;
DROP TABLE IF EXISTS users;

CREATE TABLE materials_withlength (
	material_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    unit VARCHAR(10) NOT NULL,
    PRIMARY KEY (material_id)
    );

CREATE TABLE material_lengths (
	material_id INT NOT NULL,
    length INT NOT NULL, 
    price INT NOT NULL,
    stock INT NOT NULL,
    CONSTRAINT material_lengths_ibfk_1 FOREIGN KEY (material_id) REFERENCES materials_withlength(material_id)
    );
    
CREATE TABLE materials_nolength (
	material_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    unit VARCHAR(10) NOT NULL,
    price INT NOT NULL,
    stock INT NOT NULL,
    PRIMARY KEY (material_id)
    );
    
    CREATE TABLE rooftype (
	roof_id INT(50) NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
  	inclined INT(1) NOT NULL,
	PRIMARY KEY (roof_id)
);

    CREATE TABLE rooflength(
	roof_id INT NOT NULL,
    roof_length INT NOT NULL,
    price INT NOT NULL,
    CONSTRAINT rooflength_ibfk_1 FOREIGN KEY (roof_id) REFERENCES rooftype(roof_id)
    );
    INSERT INTO rooftype(name, inclined) VALUES
    ('inclined_roof', 1),
    ('flat_roof', 0);
    
    INSERT INTO rooflength(roof_id, roof_length, price) VALUES
    (1, 270,1000),
    (1, 300,1100),
    (2, 270,1000),
    (2, 300,1100);

    CREATE TABLE users (
	user_id INT(10) NOT NULL AUTO_INCREMENT,
    seller INT(1) NOT NULL DEFAULT 0,
	email VARCHAR(100) NOT NULL UNIQUE,
	password VARCHAR(50) NOT NULL,
	PRIMARY KEY (user_id)
);

CREATE TABLE users_personalinfo (
	user_id INT(50) NOT NULL,
	firstname VARCHAR(50) NOT NULL,
	lastname VARCHAR(50) NOT NULL,
	address VARCHAR(50) NOT NULL,
  	zipcode INT(4) NOT NULL,
	city VARCHAR(50) NOT NULL,
	gender VARCHAR(1) NOT NULL, /* M/Y */
	CONSTRAINT users_address_ibfk_1 FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT INTO materials_withlength VALUES 
(1, "25x150 mm. trykimp. Bræt", "Stk.");

INSERT INTO material_lengths VALUES 
(1, 270, 1000, 1000),
(1, 300, 1100, 1000),
(1, 330, 1200, 1000),
(1, 360, 1300, 1000);

INSERT INTO materials_nolength (name, unit, price, stock) VALUES 
("universal 190 mm højre", "Stk.", 10,1000);

INSERT INTO users (seller, email,  password) VALUES
(0,"test@test.dk","test");

INSERT INTO users_personalinfo (user_id,  firstname, lastname, address, zipcode, city, gender)
VALUES (1, "Peter","Petersen", "Tagensvej 100", 2200, "København N", "m");
