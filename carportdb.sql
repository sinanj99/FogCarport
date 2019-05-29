DROP SCHEMA IF EXISTS FogCarport;
CREATE SCHEMA FogCarport;
USE FogCarport;

CREATE TABLE roofs (
	roof_id INT NOT NULL AUTO_INCREMENT,
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
    ON DELETE CASCADE
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
    ON DELETE CASCADE
    );

CREATE TABLE users (
	user_id INT NOT NULL AUTO_INCREMENT,
    seller INT(1) DEFAULT 0,
    `admin` INT(1) DEFAULT 0,
	email VARCHAR(100) NOT NULL UNIQUE,
	`password` VARCHAR(50) NOT NULL,
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
    ON DELETE CASCADE
);

CREATE TABLE requests (
	request_id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
	dateplaced DATETIME NOT NULL,
	PRIMARY KEY (request_id),
    CONSTRAINT requests_ibfk_1 FOREIGN KEY (user_id) REFERENCES users(user_id)
    ON DELETE CASCADE
);

CREATE TABLE carports (
	carport_id INT NOT NULL AUTO_INCREMENT,
    request_id INT NOT NULL,
	roof_id INT NOT NULL, 
	inclination INT(2) NOT NULL,
  	width INT(4) NOT NULL,
  	length INT(4) NOT NULL,
	PRIMARY KEY (carport_id),
    CONSTRAINT carports_ibfk_1 FOREIGN KEY (roof_id) REFERENCES roofs(roof_id)
    ON DELETE NO ACTION,
    CONSTRAINT carports_ibfk_2 FOREIGN KEY (request_id) REFERENCES requests(request_id)
    ON DELETE CASCADE
);


CREATE TABLE sheds (
	carport_id INT NOT NULL, 
  	width INT(4) NOT NULL,
  	length INT(4) NOT NULL,
	CONSTRAINT sheds_ibfk_1 FOREIGN KEY (carport_id) REFERENCES carports(carport_id)
    ON DELETE CASCADE
);

CREATE TABLE shipping_addresses (
	request_id INT NOT NULL,
	firstname VARCHAR(50) NOT NULL,
	lastname VARCHAR(50) NOT NULL,
	address VARCHAR(50) NOT NULL,
	zipcode INT(4) NOT NULL,
	city VARCHAR(50) NOT NULL,
    CONSTRAINT shipping_addresses_ibfk_1 FOREIGN KEY (request_id) REFERENCES requests(request_id)
    ON DELETE CASCADE
);

CREATE TABLE responses (
	request_id INT NOT NULL UNIQUE,
    seller_id INT NOT NULL,
	dateplaced DATETIME NOT NULL,
    sell_price INT NOT NULL,
    `status` INT(1) DEFAULT 0,
	CONSTRAINT responses_ibfk_1 FOREIGN KEY (request_id) REFERENCES requests(request_id)
    ON DELETE CASCADE
);

CREATE TABLE prebuilt_carports (
  prebuilt_carport_id INT NOT NULL AUTO_INCREMENT,
  img_path VARCHAR(500) NOT NULL,
  carport_width INT(4)NOT NULL,
  carport_length INT(4)  NOT NULL,
  price INT(10) NOT NULL,
  PRIMARY KEY (prebuilt_carport_id)
  );

CREATE TABLE prebuilt_sheds (
  prebuilt_carport_id INT NOT NULL,
  shed_width INT(3)NOT NULL,
  shed_length int(3)  NOT NULL,
  CONSTRAINT prebuilt_sheds_ibfk1 FOREIGN KEY (prebuilt_carport_id) REFERENCES prebuilt_carports(prebuilt_carport_id)
  ON DELETE CASCADE
  );

  
  INSERT INTO prebuilt_carports(img_path, carport_width, carport_length, price)
  VALUES ("/project/images/abc.png",450,750,17779),
  ("/project/images/carport2.png",360,540,13998),
  ("/project/images/carport3.png",360,720,21498),
  ("/project/images/carport4.png",390,750,23498),
  ("/project/images/carport5.png",300,480,3998),
  ("/project/images/carport6.png",600,750,31998),
  ("/project/images/carport7.png",600,540,13798),
  ("/project/images/carport8.png",600,480,10498),
  ("/project/images/carport9.jpg",630,540,15798),
  ("/project/images/carport10.jpg",420,480,5798),
  ("/project/images/carport10.jpg",420,480,5798);
  

 INSERT INTO prebuilt_sheds(prebuilt_carport_id,shed_width, shed_length)
  VALUES (1,420,180),
  (3,330,220),
  (4,240,330),
  (6,210,510),
  (7,270,240),
  (9,570,150);

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
