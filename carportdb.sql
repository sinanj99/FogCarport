DROP TABLE IF EXISTS CarportDB.sheds;
DROP TABLE IF EXISTS CarportDB.carports;
DROP TABLE IF EXISTS CarportDB.users_personalinfo;
DROP TABLE IF EXISTS `shipping_address`;
DROP TABLE IF EXISTS CarportDB.requests;
DROP TABLE IF EXISTS CarportDB.users;
DROP TABLE IF EXISTS `rooflength`;
DROP TABLE IF EXISTS CarportDB.rooftype;

CREATE TABLE CarportDB.rooftype (
	roof_id INT(50) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
  	inclined INT(1) NOT NULL,
	PRIMARY KEY (roof_id)
);
select * from material_lengths;
insert into CarportDB.rooftype (`name`, inclined) values 
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

CREATE TABLE `rooflength`(
	`roof_id` INT NOT NULL,
    `roof_length` INT NOT NULL,
    `price`INT NOT NULL,
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

DROP TABLE IF EXISTS `material_lengths`;
DROP TABLE IF EXISTS `materials_withlength`;
DROP TABLE IF EXISTS `materials_nolength`;


CREATE TABLE `materials_withlength` (
	`material_id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `unit` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`material_id`)
    );
CREATE TABLE `materials_nolength` (
	`material_id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `unit` VARCHAR(10) NOT NULL,
    `price` INT NOT NULL,
    `stock` INT NOT NULL,
    PRIMARY KEY (`material_id`)
    );
    
INSERT INTO `materials_nolength` (`name`, `unit`, `price`, `stock`) VALUES 
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

INSERT INTO `materials_withlength` (`name`, `unit`) VALUES 
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
;

CREATE TABLE `material_lengths` (
	`material_id` INT NOT NULL,
    `length` INT NOT NULL, 
    `price` INT NOT NULL,
    `stock` INT NOT NULL,
    CONSTRAINT `material_lengths_ibfk_1` FOREIGN KEY (`material_id`) REFERENCES materials_withlength(`material_id`)
    );
    
insert into CarportDB.material_lengths(material_id,length,price,stock)
  values (1,240,24,300),
  (1,270,27,300),
  (1,300,30,300),
  (1,330,33,300),
  (1,360,36,300),
  (1,390,39,300),
  (1,420,42,300),
  (1,450,45,300),
  (1,480,48,300),
  (1,510,51,300),
  (1,540,54,300),
  (1,570,57,300),
  (1,600,60,300),
  (1,630,63,300),
  (1,660,66,300),
  (1,690,69,300),
  (1,720,72,300),
  (1,750,75,300),
  (2,240,15,300),
  (2,270,18,300),
  (2,300,21,300),
  (2,330,24,300),
  (2,360,27,300),
  (2,390,30,300),
  (2,420,33,300),
  (2,450,36,300),
  (2,480,39,300),
  (2,510,42,300),
  (2,540,45,300),
  (2,570,48,300),
  (2,600,51,300),
  (2,630,54,300),
  (2,660,57,300),
  (2,690,60,300),
  (2,720,63,300),
  (2,750,66,300),
  (3,300,50,300),
  (4,250,15,300),
  (5,540,32,300),
  (6,250,22,300),
  (7,250,22,300),
  (7,210,17,300),
  (8,420,29,300),
  (9,150,15,300),
  (9,180,18,300),
  (9,210,21,300),
  (9,240,24,300),
  (9,270,27,300),
  (9,300,30,300),
  (9,330,33,300),
  (9,360,36,300),
  (9,390,39,300),
  (9,420,42,300),
  (9,450,45,300),
  (9,480,48,300),
  (9,510,51,300),
  (9,540,54,300),
  (9,570,57,300),
  (9,600,60,300),
  (9,630,63,300),
  (9,660,66,300),
  (9,690,69,300),
  (9,720,72,300),
  (10,240,60,300),
  (10,270,70,300),
  (10,300,80,300),
  (10,330,90,300),
  (10,360,100,300),
  (10,390,110,300),
  (10,420,120,300),
  (10,450,130,300),
  (10,480,140,300),
  (10,510,150,300),
  (10,540,160,300),
  (10,570,170,300),
  (10,600,180,300),
  (10,630,190,300),
  (10,660,200,300),
  (10,690,210,300),
  (10,720,220,300),
  (10,750,230,300),
  (10,780,240,300);    

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
("38x73 mm. taglægte T1",540,"stk", 45),
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
("45x95mm reglar. ub.",720,"stk", 68),
("25x150mm spærtræ. ubh. ",270,"stk",  23),
("25x150mm spærtræ. ubh. ",300,"stk",  26),
("25x150mm spærtræ. ubh. ",330,"stk",  29),
("25x150mm spærtræ. ubh. ",360,"stk",  32),
("25x150mm spærtræ. ubh. ",390,"stk",  35),
("25x150mm spærtræ. ubh. ",420,"stk",  38),
("25x150mm spærtræ. ubh. ",450,"stk",  41),
("25x150mm spærtræ. ubh. ",480,"stk",  44),
("25x150mm spærtræ. ubh. ",510,"stk",  47),
("25x150mm spærtræ. ubh. ",540,"stk",  50),
("25x150mm spærtræ. ubh. ",570,"stk",  53),
("25x150mm spærtræ. ubh. ",600,"stk",  56),
("25x150mm spærtræ. ubh. ",630,"stk",  59),
("25x150mm spærtræ. ubh. ",660,"stk",  62),
("25x150mm spærtræ. ubh. ",690,"stk",  65),
("25x150mm spærtræ. ubh. ",720,"stk",  68),
("25x150mm spærtræ. ubh. ",750,"stk",  71),
("19x100 mm. trykimp. Bræt",150,"stk", 11),
("19x100 mm. trykimp. Bræt",180,"stk", 14),
("19x100 mm. trykimp. Bræt",210,"stk", 17),
("19x100 mm. trykimp. Bræt",240,"stk", 20),
("19x100 mm. trykimp. Bræt",270,"stk", 23),
("19x100 mm. trykimp. Bræt",300,"stk", 26),
("19x100 mm. trykimp. Bræt",330,"stk", 29),
("19x100 mm. trykimp. Bræt",360,"stk", 32),
("19x100 mm. trykimp. Bræt",390,"stk", 35),
("19x100 mm. trykimp. Bræt",420,"stk", 38),
("19x100 mm. trykimp. Bræt",450,"stk", 41),
("19x100 mm. trykimp. Bræt",480,"stk", 44),
("19x100 mm. trykimp. Bræt",510,"stk", 47),
("19x100 mm. trykimp. Bræt",540,"stk", 50),
("19x100 mm. trykimp. Bræt",570,"stk", 53),
("19x100 mm. trykimp. Bræt",600,"stk", 56),
("19x100 mm. trykimp. Bræt",630,"stk", 59),
("19x100 mm. trykimp. Bræt",660,"stk", 62),
("19x100 mm. trykimp. Bræt",690,"stk", 65),
("19x100 mm. trykimp. Bræt",720,"stk", 68),
("25x150 mm. trykimp. Bræt",540,"stk", 68);


insert into CarportDB.material (name,  unit, price)
values ("10x120mm brædderbolt","stk",  4),
("universal 190mm venstre","stk",  5),
("universal 190mm højre","stk",  5),
("4.0x50mm beslagskruer 250 stk","pakke",  30),
("50x75mm stalddørsgreb","sæt",  135),
("390mm t-hængsel","stk",  168),
("vinkelbeslag 35","stk",  4),
("4,5 x 60 mm. Skruer
200 stk.","pakke",  4),
("hulbånd 1x20 mm. 10 mtr.", "rulle", 10),
("5,0 x 40 mm. beslagskruer 250 stk.", "pakke", 10),
("5,0 x 100 mm. skruer 100 stk.", "pakke", 10),
("4,5 x 50 mm. Skruer 350 stk.", "pakke", 10);




CREATE TABLE CarportDB.users (
	user_id INT(50) NOT NULL AUTO_INCREMENT,
    seller INT(1) NOT NULL DEFAULT 0,
    `admin` INT(1) NOT NULL DEFAULT 0,
	email VARCHAR(320) NOT NULL UNIQUE,
	`password` VARCHAR(50) NOT NULL,
	PRIMARY KEY (user_id)
);

insert into CarportDB.users (`admin`, seller, email,  `password`)
values 
(0, 0, "test@test.dk","test"),
(1, 0, "admin@fog.dk","test"),
(0, 1, "seller@fog.dk","seller");


CREATE TABLE CarportDB.requests (
	request_id INT(50) NOT NULL AUTO_INCREMENT,
	user_id int(50) NOT NULL,
	dateplaced DATETIME NOT NULL,
	PRIMARY KEY (request_id),
	CONSTRAINT `requests_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES users(`user_id`)
);

CREATE TABLE CarportDB.responses (
	response_id INT(50) NOT NULL AUTO_INCREMENT,
	request_id int(50) NOT NULL UNIQUE, 
	user_id int(50) NOT NULL,
	emp_id int(50) NOT NULL,
	dateplaced DATETIME NOT NULL,
	carport_id int(50) NOT NULL,
	shed_id int(50) DEFAULT NULL,
	productionprice int NOT NULL,
	sellprice int NOT NULL,
    status int(1) DEFAULT 0,
	PRIMARY KEY (response_id),
	CONSTRAINT `responses_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES requests(`request_id`),
	CONSTRAINT `responses_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES users(`user_id`),
	CONSTRAINT `responses_ibfk_3` FOREIGN KEY (`emp_id`) REFERENCES users(`user_id`),
	CONSTRAINT `responses_ibfk_4` FOREIGN KEY (`carport_id`) REFERENCES carports(`carport_id`),
	CONSTRAINT `responses_ibfk_5` FOREIGN KEY (`shed_id`) REFERENCES sheds(`shed_id`)
);

CREATE TABLE CarportDB.users_personalinfo (
	user_id INT(50) NOT NULL,
	firstname VARCHAR(50) NOT NULL,
	lastname VARCHAR(50) NOT NULL,
	address VARCHAR(50) NOT NULL,
  	zipcode INT(4) NOT NULL,
	city VARCHAR(50) NOT NULL,
	gender VARCHAR(1) NOT NULL, /* M/Y */
	CONSTRAINT `users_address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES users(`user_id`)
);

CREATE TABLE `shipping_address` (
	`request_id` INT NOT NULL,
	`firstname` VARCHAR(50) NOT NULL,
	`lastname` VARCHAR(50) NOT NULL,
	`address` VARCHAR(50) NOT NULL,
	`zipcode` INT(4) NOT NULL,
	`city` VARCHAR(50) NOT NULL,
    CONSTRAINT `shipping_address_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES requests(`request_id`)
);


insert into CarportDB.users_personalinfo (user_id,  firstname, lastname, address, zipcode, city, gender)
values (1, "Peter","Petersen", "Tagensvej 100", 2200, "København N", "m");


CREATE TABLE CarportDB.carports (
	carport_id INT(50) NOT NULL AUTO_INCREMENT,
	request_id INT(50) NOT NULL,
	roof_id INT(50) NOT NULL, 
	inclination INT(1) NOT NULL,
  	width INT(50) NOT NULL,
  	length INT(50) NOT NULL,
	PRIMARY KEY (carport_id),
	CONSTRAINT `carports_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES requests(`request_id`),
    CONSTRAINT `carports_ibfk_2` FOREIGN KEY (`roof_id`) REFERENCES rooftype(`roof_id`)
);


SELECT * FROM materials_withlength INNER JOIN material_lengths USING(material_id);

CREATE TABLE CarportDB.sheds (
	shed_id INT(50) NOT NULL AUTO_INCREMENT,
	carport_id INT(50) NOT NULL, -- bør shed ikke have carport_id i stedet for request id?
  	width INT(50) NOT NULL,
  	length INT(50) NOT NULL,
	PRIMARY KEY (shed_id),
	CONSTRAINT `sheds_ibfk_1` FOREIGN KEY (`carport_id`) REFERENCES carports(`carport_id`)
);

CREATE TABLE CarportDB.prebuilt_carport (
  id INT(50) NOT NULL AUTO_INCREMENT,
  img_path VARCHAR(1000) NOT NULL,
  carport_width INT(10)NOT NULL,
  carport_length int(10)  NOT NULL,
  shed boolean,
  shed_width int(10) NOT NULL,
  shed_length int(10) NOT NULL,
  price INT(10) NOT NULL,
  PRIMARY KEY (id));
  
  insert into CarportDB.prebuilt_carport(img_path, carport_width, carport_length, shed, shed_width, shed_length, price)
  values ("/project/images/abc.png",450,750,true,420,180,17779),
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

