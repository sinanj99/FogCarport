DROP SCHEMA IF EXISTS CarportTest;
CREATE SCHEMA CarportTest;
USE CarportTest;

DROP TABLE IF EXISTS prebuilt_carport;
DROP TABLE IF EXISTS responses;
DROP TABLE IF EXISTS sheds;
DROP TABLE IF EXISTS carports;
DROP TABLE IF EXISTS personal_info;
DROP TABLE IF EXISTS shipping_addresses;
DROP TABLE IF EXISTS requests;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roof_lengths;
DROP TABLE IF EXISTS roofs;
DROP TABLE IF EXISTS material_lengths;
DROP TABLE IF EXISTS wood_materials;
DROP TABLE IF EXISTS fittings_and_screws;

CREATE TABLE roofs (
	roof_id INT(50) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
  	inclined INT(1) NOT NULL,
	PRIMARY KEY (roof_id)
);

CREATE TABLE roof_lengths(
	roof_id INT NOT NULL,
    length INT NOT NULL,
    price INT NOT NULL,
    stock INT NOT NULL,
    CONSTRAINT roof_lengths_ibfk_1 FOREIGN KEY (roof_id) REFERENCES roofs(roof_id)
    );

CREATE TABLE wood_materials (
	material_id INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    unit VARCHAR(10) NOT NULL,
    PRIMARY KEY (material_id)
    );
CREATE TABLE fittings_and_screws (
	fitting_id INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    unit VARCHAR(10) NOT NULL,
    price INT NOT NULL,
    stock INT NOT NULL,
    PRIMARY KEY (fitting_id)
    );
    
CREATE TABLE material_lengths (
	material_id INT NOT NULL,
    length INT NOT NULL, 
    price INT NOT NULL,
    stock INT NOT NULL,
    CONSTRAINT material_lengths_ibfk_1 FOREIGN KEY (material_id) REFERENCES wood_materials(material_id)
    );
	

CREATE TABLE users (
  	user_id INT NOT NULL AUTO_INCREMENT,
  	seller INT(1) NOT NULL DEFAULT 0,
  	admin INT(1) NOT NULL DEFAULT 0,
  	email VARCHAR(320) NOT NULL UNIQUE,
  	password VARCHAR(50) NOT NULL,
  	PRIMARY KEY (user_id)
);

CREATE TABLE personal_info (
  	user_id INT NOT NULL,
  	firstname VARCHAR(50) NOT NULL,
  	lastname VARCHAR(50) NOT NULL,
  	address VARCHAR(50) NOT NULL,
  	zipcode INT(4) NOT NULL,
  	city VARCHAR(50) NOT NULL,
  	gender VARCHAR(1) NOT NULL,
  	CONSTRAINT personal_info_ibfk_1 FOREIGN KEY (user_id) REFERENCES users(user_id)
);



CREATE TABLE requests (
  	request_id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
  	dateplaced DATETIME NOT NULL,
  	PRIMARY KEY (request_id),
    CONSTRAINT requests_ibfk_1 FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE carports (
  	carport_id INT NOT NULL AUTO_INCREMENT,
    request_id INT NOT NULL,
  	roof_id INT NOT NULL, 
  	inclination INT(1) NOT NULL,
    width INT(3) NOT NULL,
    length INT(3) NOT NULL,
  	PRIMARY KEY (carport_id),
    CONSTRAINT carports_ibfk_1 FOREIGN KEY (roof_id) REFERENCES roofs(roof_id),
    CONSTRAINT carports_ibfk_2 FOREIGN KEY (request_id) REFERENCES requests(request_id)
);

CREATE TABLE sheds (
    carport_id INT NOT NULL, 
    width INT(3) NOT NULL,
    length INT(3) NOT NULL,
  	CONSTRAINT sheds_ibfk_1 FOREIGN KEY (carport_id) REFERENCES carports(carport_id)
);

CREATE TABLE shipping_addresses (
  	request_id INT NOT NULL,
  	firstname VARCHAR(50) NOT NULL,
  	lastname VARCHAR(50) NOT NULL,
  	address VARCHAR(50) NOT NULL,
  	zipcode INT(4) NOT NULL,
  	city VARCHAR(50) NOT NULL,
    CONSTRAINT shipping_addresses_ibfk_1 FOREIGN KEY (request_id) REFERENCES requests(request_id)
);


CREATE TABLE responses (
  	request_id INT NOT NULL UNIQUE,
    seller_id INT NOT NULL,
  	dateplaced DATETIME NOT NULL,
    sell_price INT NOT NULL,
    status INT(1) DEFAULT 0,
  	CONSTRAINT responses_ibfk_1 FOREIGN KEY (request_id) REFERENCES requests(request_id)
);

CREATE TABLE prebuilt_carport (
  id INT(50) NOT NULL AUTO_INCREMENT,
  img_path VARCHAR(1000) NOT NULL,
  carport_width INT(10)NOT NULL,
  carport_length int(10)  NOT NULL,
  shed BOOLEAN,
  shed_width INT(10) NOT NULL,
  shed_length INT(10) NOT NULL,
  price INT(10) NOT NULL,
  PRIMARY KEY (id));
  
  INSERT INTO prebuilt_carport(img_path, carport_width, carport_length, shed, shed_width, shed_length, price)
  VALUES ("/project/images/abc.png",450,750,true,420,180,17779),
  ("/project/images/carport2.png",360,540,false,0,0,13998),
  ("/project/images/carport3.png",360,720,true,330,220,21498),
  ("/project/images/carport4.png",390,750,true,240,330,23498),
  ("/project/images/carport5.png",300,480,false,0,0,3998),
  ("/project/images/carport6.png",600,750,true,210,510,31998),
  ("/project/images/carport7.png",600,540,true,270,240,13798),
  ("/project/images/carport8.png",600,480,false,0,0,10498),
  ("/project/images/carport9.jpg",630,540,true,570,150,15798),
  ("/project/images/carport10.jpg",420,480,false,0,0,5798),
  ("/project/images/carport10.jpg",420,480,false,0,0,5798);


INSERT INTO roofs (`name`, inclined) VALUES 
("Plasttrapezplader - Blåtonet", 0),
("Plasttrapezplader - Gråtonet", 0),
("Plasttrapezplader - Rødbrun",  0),
("Plasttrapezplader - Mocca",  0),
("Betonstagsten - Rød", 1),
("Betonstagsten - Teglrød", 1),
("Betonstagsten - Brun", 1),
("Betonstagsten - Sort", 1),
("Eternittag B6 - Grå",  1),
("Eternittag B6 - Sort", 1),
("Eternittag B6 - Mokka (brun)", 1),
("Eternittag B6 - Rødbrun", 1),
("Eternittag B6 - Teglrød", 1),
("Eternittag B7 - Grå",  1),
("Eternittag B7 - Sort", 1),
("Eternittag B7 - Mokka (brun)",  1),
("Eternittag B7 - Rødbrun", 1),
("Eternittag B7 - Teglrød", 1),
("Eternittag B7 - Rødflammet", 1);

INSERT INTO fittings_and_screws (`name`, unit, price, stock) VALUES 
("universal 190 mm højre", "Stk.", 10,1000),
("universal 190 mm venstre", "Stk.", 10,1000),
("Stalddørsgreb 50x75", "Sæt.", 20,1000),
("T-hængsel 390 mm.", "Stk.", 20,1000),
("vinkelbeslag", "Stk.", 20,1000),
("4,5 x 60 mm. Skruer 200 stk.", "Pakke", 20,1000),
("5,0 x 40 mm. beslagskruer 250 stk.", "Pakke", 20,1000),
("5,0 x 100 mm. skruer 100 stk.", "Pakke", 20,1000),
("bræddebolt 10 x 120 mm.", "Stk.", 20,1000),
("firkantskiver 40x40x11mm", "Stk.", 20,1000),
("4,5 x 70 mm. Skruer 200 stk.", "Pakke", 20,1000),
("4,5 x 50 mm. Skruer 350 stk.", "Pakke", 20,1000),
("Toplægteholder", "Stk.", 50, 1000),
("Rygstensbeslag", "Stk.", 50, 1000),
("Tagstensbindere & nakkekroge", "Pakke", 50, 1000),
("hulbånd 1x20 mm. 10 mtr.", "rule.", 270,1000);

INSERT INTO wood_materials (name, unit) VALUES 
("45x195mm spærtræ. ubh.", "stk"),
("25x150mm spærtræ. ubh.", "stk"),
("97x97mm trykimp. Stolpe", "stk"),
("25x200mm trykimp. brædt", "stk"),
("25x150 mm. trykimp. Bræt", "stk"),
("25x125m trykimp. brædt", "stk"),
("19x100mm trykimp. brædt", "stk"),
("38x73mm lægte. ubh.", "stk"),
("45x95mm reglar. ub.", "stk"),
("38x73mm. taglægte T1", "stk");

INSERT INTO users (`admin`, seller, email,  `password`)
VALUES 
(0, 0, "test@fog.dk","test"),
(1, 0, "admin@fog.dk","admin"),
(0, 1, "seller@fog.dk","seller");

INSERT INTO personal_info (user_id,  firstname, lastname, address, zipcode, city, gender)
VALUES (1, "Peter","Petersen", "Tagensvej 100", 2200, "KBH", "m");

INSERT INTO material_lengths VALUES 
(1, 270, 1000, 1000),
(1, 300, 1100, 1000),
(1, 330, 1200, 1000),
(1, 360, 1300, 1000);

INSERT INTO roof_lengths(roof_id, length, price, stock) VALUES
(1, 270,1000, 300),
(1, 300,1100, 300),
(2, 270,1000, 300),
(2, 300,1100, 300);

/* POPULATE REQUEST TEST-TABLE */
INSERT INTO requests (user_id, dateplaced) VALUES
(1, "2019-05-22 12:00:00"),
(1, "2019-05-22 12:10:00"),
(1, "2019-05-22 12:20:00"),
(1, "2019-05-22 12:30:00"),
(1, "2019-05-22 12:40:00");

/* POPULATE CARPORT TEST-TABLE */
INSERT INTO carports (request_id, roof_id, inclination, width, length) VALUES
(1, 1, 0, 600, 600),
(2, 2, 0, 450, 600),
(3, 3, 0, 480, 630),
(4, 4, 0, 510, 660),
(5, 5, 0, 540, 690);

/* POPULATE SHIPPINGADDRESS TEST-TABLE */
INSERT INTO shipping_addresses (request_id, firstname, lastname, address, zipcode, city) VALUES
(1, "Billy", "Russo", "Tagensvej 24", "2200", "Koebenhavn N"),
(2, "Obaydah", "Mohamad", "Ringertoften", "2400", "Koebenhavn NV"),
(3, "Michael", "Jackson", "Aaboulevarden", "2200", "Koebenhavn N"),
(4, "Michael", "Jensen", "Victor Bendix Gade", "2100", "Koebenhavn OE"),
(5, "Mathias", "Pedersen", "Nørrebrogade 183", "2200", "Koebenhavn N");

/* POPULATE SHED TEST-TABLE */
INSERT INTO sheds VALUES
(1, 240, 330),
(3, 360, 270),
(5, 240, 300);

/* POPULATE RESPONSE TEST-TABLE */
INSERT INTO responses VALUES
(1, 3, "2019-05-22 15:00:00", 40000, 0),
(2, 3, "2019-05-22 15:10:00", 40000, 0),
(3, 3, "2019-05-22 15:20:00", 69850, 0),
(4, 3, "2019-05-22 15:30:00", 40000, 0);
