DROP TABLE IF EXISTS CarportDB.sheds;
DROP TABLE IF EXISTS CarportDB.carports;
DROP TABLE IF EXISTS CarportDB.requests;
DROP TABLE IF EXISTS CarportDB.users_personalinfo;
DROP TABLE IF EXISTS CarportDB.users;
DROP TABLE IF EXISTS `rooflength`;
DROP TABLE IF EXISTS CarportDB.rooftype;

CREATE TABLE CarportDB.rooftype (
	roof_id INT(50) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
  	price INT(10) NOT NULL,
  	inclined INT(1) NOT NULL,
	PRIMARY KEY (roof_id)
);

insert into CarportDB.rooftype (`name`,  price, inclined) values 
("Plasttrapezplader", 0,  0),
("Betonstagsten - Rød", 0,  1),
("Betonstagsten - Teglrød", 0,  1),
("Betonstagsten - Brun", 0,  1),
("Betonstagsten - Sort", 0,  1),
("Eternittag B6 - Grå", 0,  1),
("Eternittag B6 - Sort", 0,  1),
("Eternittag B6 - Mokka (brun)", 0,  1),
("Eternittag B6 - Rødbrun", 0,  1),
("Eternittag B6 - Teglrød", 0,  1),
("Eternittag B7 - Grå", 0,  1),
("Eternittag B7 - Sort", 0,  1),
("Eternittag B7 - Mokka (brun)", 0,  1),
("Eternittag B7 - Rødbrun", 0,  1),
("Eternittag B7 - Teglrød", 0,  1),
("Eternittag B7 - Rødflammet", 0, 1);

CREATE TABLE `rooflength`(
	`roof_id` INT NOT NULL AUTO_INCREMENT,
    `roof_length` INT NOT NULL,
    CONSTRAINT `rooflength_ibfk_1` FOREIGN KEY (`roof_id`) REFERENCES rooftype(`roof_id`)
    );


DROP TABLE IF EXISTS material;

CREATE TABLE CarportDB.material (
  material_id INT(50) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  length INT(10),
  unit VARCHAR(10)  NOT NULL,
  price INT(10) NOT NULL,
  PRIMARY KEY (material_id));



insert into CarportDB.material (name, length, unit,  price)
values ("45x195mm spærtræ. ubh. ",240,"stk", 20),
("45x195mm spærtræ. ubh. ",270,"stk",  23),
("45x195mm spærtræ. ubh. ",300,"stk",  26),
("45x195mm spærtræ. ubh. ",330,"stk",  29),
("45x195mm spærtræ. ubh. ",360,"stk",  32),
("45x195mm spærtræ. ubh. ",390,"stk",  35),
("45x195mm spærtræ. ubh. ",420,"stk",  38),
("45x195mm spærtræ. ubh. ",450,"stk",  41),
("45x195mm spærtræ. ubh. ",480,"stk",  44),
("45x195mm spærtræ. ubh. ",510,"stk",  47),
("45x195mm spærtræ. ubh. ",540,"stk",  50),
("45x195mm spærtræ. ubh. ",570,"stk",  53),
("45x195mm spærtræ. ubh. ",600,"stk",  56),
("45x195mm spærtræ. ubh. ",630,"stk",  59),
("45x195mm spærtræ. ubh. ",660,"stk",  62),
("45x195mm spærtræ. ubh. ",690,"stk",  65),
("45x195mm spærtræ. ubh. ",720,"stk",  68),
("45x195mm spærtræ. ubh. ",750,"stk",  71),
("97x97mm trykimp. Stolpe",300,"stk",  35),
("25x200mm trykimp. brædt",250,"stk", 22),
("25x125m trykimp. brædt",250,"stk", 22),
("19x100mm trykimp. brædt",250,"stk", 22),
("19x100mm trykimp. brædt",210,"stk", 2),
("38x73mm lægte. ubh.",420,"stk", 45),
("45x95mm reglar. ub.",150,"stk", 11),
("45x95mm reglar. ub.",180,"stk", 14),
("45x95mm reglar. ub.",210,"stk", 17),
("45x95mm reglar. ub.",240,"stk", 20),
("45x95mm reglar. ub.",270,"stk", 23),
("45x95mm reglar. ub.",300,"stk", 26),
("45x95mm reglar. ub.",330,"stk", 29),
("45x95mm reglar. ub.",360,"stk", 32),
("45x95mm reglar. ub.",390,"stk", 35),
("45x95mm reglar. ub.",420,"stk", 38),
("45x95mm reglar. ub.",450,"stk", 41),
("45x95mm reglar. ub.",480,"stk", 44),
("45x95mm reglar. ub.",510,"stk", 47),
("45x95mm reglar. ub.",540,"stk", 50),
("45x95mm reglar. ub.",570,"stk", 53),
("45x95mm reglar. ub.",600,"stk", 56),
("45x95mm reglar. ub.",630,"stk", 59),
("45x95mm reglar. ub.",660,"stk", 62),
("45x95mm reglar. ub.",690,"stk", 65),
("45x95mm reglar. ub.",720,"stk", 68);

insert into CarportDB.material (name,  unit, price)
values ("10x120mm brædderbolt","stk",  4),
("universal 190mm venstre","stk",  5),
("universal 190mm højre","stk",  5),
("4.0x50mm beslagskruer 250 stk","pakke",  30),
("50x75mm stalddørsgreb","sæt",  135),
("390mm t-hængsel","stk",  168),
("vinkelbeslag 35","stk",  4),
("hulbånd 1x20 mm. 10 mtr.", "rulle", 10);

CREATE TABLE CarportDB.users (
	user_id INT(50) NOT NULL AUTO_INCREMENT,
	email VARCHAR(320) NOT NULL UNIQUE,
	password VARCHAR(50) NOT NULL,
	PRIMARY KEY (user_id)
);
select * from users;
insert into CarportDB.users (email,  password)
values ("test@test.dk","test");

CREATE TABLE CarportDB.users_personalinfo (
	user_id INT(50) NOT NULL UNIQUE,
	firstname VARCHAR(50) NOT NULL,
	lastname VARCHAR(50) NOT NULL,
	address VARCHAR(50) NOT NULL,
  	zipcode INT(4) NOT NULL,
	city VARCHAR(50) NOT NULL,
	gender VARCHAR(1) NOT NULL, /* M/Y */
	CONSTRAINT `users_address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES users(`user_id`)
);

insert into CarportDB.users_personalinfo (user_id,  firstname, lastname, address, zipcode, city, gender)
values (1, "Peter","Petersen", "Tagensvej 100", 2200, "København N", "m");

CREATE TABLE CarportDB.requests (
	request_id INT(50) NOT NULL AUTO_INCREMENT,
	user_id int(50) NOT NULL,
	dateplaced DATETIME NOT NULL,
	dateaccepted DATETIME DEFAULT NULL,
  	price INT(10) NOT NULL,
	PRIMARY KEY (request_id),
	CONSTRAINT `requests_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES users(`user_id`)
);

CREATE TABLE CarportDB.sheds (
	shed_id INT(50) NOT NULL AUTO_INCREMENT,
	request_id INT(50) NOT NULL,
  	width INT(50) NOT NULL,
  	length INT(50) NOT NULL,
	PRIMARY KEY (shed_id),
	CONSTRAINT `sheds_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES requests(`request_id`)
);

CREATE TABLE CarportDB.carports (
	carport_id INT(50) NOT NULL AUTO_INCREMENT,
	request_id INT(50) NOT NULL,
	roof_id int(50) NOT NULL,
	inclined int(1) NOT NULL,
  	width INT(50) NOT NULL,
  	length INT(50) NOT NULL,
  	shed int(1) NOT NULL,
	PRIMARY KEY (carport_id),
	CONSTRAINT `carports_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES requests(`request_id`)
);