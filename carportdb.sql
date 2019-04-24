DROP TABLE IF EXISTS CarportDB.rooftype;
CREATE TABLE CarportDB.rooftype (
	roof_id INT(50) NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
  	price INT(10) NOT NULL,
  	inclined INT(1) NOT NULL,
	PRIMARY KEY (roof_id)
);

insert into CarportDB.rooftype (name,  price, inclined) values 
("Plasttrapezplader - 240", 0,  0),
("Plasttrapezplader - 270", 0,  0),
("Plasttrapezplader - 300", 0,  0),
("Plasttrapezplader - 330", 0,  0),
("Plasttrapezplader - 360", 0,  0),
("Plasttrapezplader - 390", 0,  0),
("Plasttrapezplader - 420", 0,  0),
("Plasttrapezplader - 450", 0,  0),
("Plasttrapezplader - 480", 0,  0),
("Plasttrapezplader - 510", 0,  0),
("Plasttrapezplader - 540", 0,  0),
("Plasttrapezplader - 570", 0,  0),
("Plasttrapezplader - 600", 0,  0),
("Plasttrapezplader - 630", 0,  0),
("Plasttrapezplader - 660", 0,  0),
("Plasttrapezplader - 690", 0,  0),
("Plasttrapezplader - 720", 0,  0),
("Plasttrapezplader - 750", 0,  0),
("Betonstagsten - Rød - 240", 0,  1),
("Betonstagsten - Rød - 270", 0,  1),
("Betonstagsten - Rød - 300", 0,  1),
("Betonstagsten - Rød - 330", 0,  1),
("Betonstagsten - Rød - 360", 0,  1),
("Betonstagsten - Rød - 390", 0,  1),
("Betonstagsten - Rød - 420", 0,  1),
("Betonstagsten - Rød - 450", 0,  1),
("Betonstagsten - Rød - 480", 0,  1),
("Betonstagsten - Rød - 510", 0,  1),
("Betonstagsten - Rød - 540", 0,  1),
("Betonstagsten - Rød - 570", 0,  1),
("Betonstagsten - Rød - 600", 0,  1),
("Betonstagsten - Rød - 630", 0,  1),
("Betonstagsten - Rød - 660", 0,  1),
("Betonstagsten - Rød - 690", 0,  1),
("Betonstagsten - Rød - 720", 0,  1),
("Betonstagsten - Rød - 750", 0,  1),
("Betonstagsten - Teglrød - 240", 0,  1),
("Betonstagsten - Teglrød - 270", 0,  1),
("Betonstagsten - Teglrød - 300", 0,  1),
("Betonstagsten - Teglrød - 330", 0,  1),
("Betonstagsten - Teglrød - 360", 0,  1),
("Betonstagsten - Teglrød - 390", 0,  1),
("Betonstagsten - Teglrød - 420", 0,  1),
("Betonstagsten - Teglrød - 450", 0,  1),
("Betonstagsten - Teglrød - 480", 0,  1),
("Betonstagsten - Teglrød - 510", 0,  1),
("Betonstagsten - Teglrød - 540", 0,  1),
("Betonstagsten - Teglrød - 570", 0,  1),
("Betonstagsten - Teglrød - 600", 0,  1),
("Betonstagsten - Teglrød - 630", 0,  1),
("Betonstagsten - Teglrød - 660", 0,  1),
("Betonstagsten - Teglrød - 690", 0,  1),
("Betonstagsten - Teglrød - 720", 0,  1),
("Betonstagsten - Teglrød - 750", 0,  1),
("Betonstagsten - Brun - 240", 0,  1),
("Betonstagsten - Brun - 270", 0,  1),
("Betonstagsten - Brun - 300", 0,  1),
("Betonstagsten - Brun - 330", 0,  1),
("Betonstagsten - Brun - 360", 0,  1),
("Betonstagsten - Brun - 390", 0,  1),
("Betonstagsten - Brun - 420", 0,  1),
("Betonstagsten - Brun - 450", 0,  1),
("Betonstagsten - Brun - 480", 0,  1),
("Betonstagsten - Brun - 510", 0,  1),
("Betonstagsten - Brun - 540", 0,  1),
("Betonstagsten - Brun - 570", 0,  1),
("Betonstagsten - Brun - 600", 0,  1),
("Betonstagsten - Brun - 630", 0,  1),
("Betonstagsten - Brun - 660", 0,  1),
("Betonstagsten - Brun - 690", 0,  1),
("Betonstagsten - Brun - 720", 0,  1),
("Betonstagsten - Brun - 750", 0,  1),
("Betonstagsten - Sort - 240", 0,  1),
("Betonstagsten - Sort - 270", 0,  1),
("Betonstagsten - Sort - 300", 0,  1),
("Betonstagsten - Sort - 330", 0,  1),
("Betonstagsten - Sort - 360", 0,  1),
("Betonstagsten - Sort - 390", 0,  1),
("Betonstagsten - Sort - 420", 0,  1),
("Betonstagsten - Sort - 450", 0,  1),
("Betonstagsten - Sort - 480", 0,  1),
("Betonstagsten - Sort - 510", 0,  1),
("Betonstagsten - Sort - 540", 0,  1),
("Betonstagsten - Sort - 570", 0,  1),
("Betonstagsten - Sort - 600", 0,  1),
("Betonstagsten - Sort - 630", 0,  1),
("Betonstagsten - Sort - 660", 0,  1),
("Betonstagsten - Sort - 690", 0,  1),
("Betonstagsten - Sort - 720", 0,  1),
("Eternittag B6 - Grå - 240", 0,  1),
("Eternittag B6 - Grå - 270", 0,  1),
("Eternittag B6 - Grå - 300", 0,  1),
("Eternittag B6 - Grå - 330", 0,  1),
("Eternittag B6 - Grå - 360", 0,  1),
("Eternittag B6 - Grå - 390", 0,  1),
("Eternittag B6 - Grå - 420", 0,  1),
("Eternittag B6 - Grå - 450", 0,  1),
("Eternittag B6 - Grå - 480", 0,  1),
("Eternittag B6 - Grå - 510", 0,  1),
("Eternittag B6 - Grå - 540", 0,  1),
("Eternittag B6 - Grå - 570", 0,  1),
("Eternittag B6 - Grå - 600", 0,  1),
("Eternittag B6 - Grå - 630", 0,  1),
("Eternittag B6 - Grå - 660", 0,  1),
("Eternittag B6 - Grå - 690", 0,  1),
("Eternittag B6 - Grå - 720", 0,  1),
("Eternittag B6 - Grå - 750", 0,  1),
("Eternittag B6 - Sort - 240", 0,  1),
("Eternittag B6 - Sort - 270", 0,  1),
("Eternittag B6 - Sort - 300", 0,  1),
("Eternittag B6 - Sort - 330", 0,  1),
("Eternittag B6 - Sort - 360", 0,  1),
("Eternittag B6 - Sort - 390", 0,  1),
("Eternittag B6 - Sort - 420", 0,  1),
("Eternittag B6 - Sort - 450", 0,  1),
("Eternittag B6 - Sort - 480", 0,  1),
("Eternittag B6 - Sort - 510", 0,  1),
("Eternittag B6 - Sort - 540", 0,  1),
("Eternittag B6 - Sort - 570", 0,  1),
("Eternittag B6 - Sort - 600", 0,  1),
("Eternittag B6 - Sort - 630", 0,  1),
("Eternittag B6 - Sort - 660", 0,  1),
("Eternittag B6 - Sort - 690", 0,  1),
("Eternittag B6 - Sort - 720", 0,  1),
("Eternittag B6 - Sort - 750", 0,  1),
("Eternittag B6 - Mokka (brun) - 240", 0,  1),
("Eternittag B6 - Mokka (brun) - 270", 0,  1),
("Eternittag B6 - Mokka (brun) - 300", 0,  1),
("Eternittag B6 - Mokka (brun) - 330", 0,  1),
("Eternittag B6 - Mokka (brun) - 360", 0,  1),
("Eternittag B6 - Mokka (brun) - 390", 0,  1),
("Eternittag B6 - Mokka (brun) - 420", 0,  1),
("Eternittag B6 - Mokka (brun) - 450", 0,  1),
("Eternittag B6 - Mokka (brun) - 480", 0,  1),
("Eternittag B6 - Mokka (brun) - 510", 0,  1),
("Eternittag B6 - Mokka (brun) - 540", 0,  1),
("Eternittag B6 - Mokka (brun) - 570", 0,  1),
("Eternittag B6 - Mokka (brun) - 600", 0,  1),
("Eternittag B6 - Mokka (brun) - 630", 0,  1),
("Eternittag B6 - Mokka (brun) - 660", 0,  1),
("Eternittag B6 - Mokka (brun) - 690", 0,  1),
("Eternittag B6 - Mokka (brun) - 720", 0,  1),
("Eternittag B6 - Mokka (brun) - 750", 0,  1),
("Eternittag B6 - Rødbrun - 240", 0,  1),
("Eternittag B6 - Rødbrun - 270", 0,  1),
("Eternittag B6 - Rødbrun - 300", 0,  1),
("Eternittag B6 - Rødbrun - 330", 0,  1),
("Eternittag B6 - Rødbrun - 360", 0,  1),
("Eternittag B6 - Rødbrun - 390", 0,  1),
("Eternittag B6 - Rødbrun - 420", 0,  1),
("Eternittag B6 - Rødbrun - 450", 0,  1),
("Eternittag B6 - Rødbrun - 480", 0,  1),
("Eternittag B6 - Rødbrun - 510", 0,  1),
("Eternittag B6 - Rødbrun - 540", 0,  1),
("Eternittag B6 - Rødbrun - 570", 0,  1),
("Eternittag B6 - Rødbrun - 600", 0,  1),
("Eternittag B6 - Rødbrun - 630", 0,  1),
("Eternittag B6 - Rødbrun - 660", 0,  1),
("Eternittag B6 - Rødbrun - 690", 0,  1),
("Eternittag B6 - Rødbrun - 720", 0,  1),
("Eternittag B6 - Rødbrun - 750", 0,  1),
("Eternittag B6 - Teglrød - 240", 0,  1),
("Eternittag B6 - Teglrød - 270", 0,  1),
("Eternittag B6 - Teglrød - 300", 0,  1),
("Eternittag B6 - Teglrød - 330", 0,  1),
("Eternittag B6 - Teglrød - 360", 0,  1),
("Eternittag B6 - Teglrød - 390", 0,  1),
("Eternittag B6 - Teglrød - 420", 0,  1),
("Eternittag B6 - Teglrød - 450", 0,  1),
("Eternittag B6 - Teglrød - 480", 0,  1),
("Eternittag B6 - Teglrød - 510", 0,  1),
("Eternittag B6 - Teglrød - 540", 0,  1),
("Eternittag B6 - Teglrød - 570", 0,  1),
("Eternittag B6 - Teglrød - 600", 0,  1),
("Eternittag B6 - Teglrød - 630", 0,  1),
("Eternittag B6 - Teglrød - 660", 0,  1),
("Eternittag B6 - Teglrød - 690", 0,  1),
("Eternittag B6 - Teglrød - 720", 0,  1),
("Eternittag B6 - Teglrød - 750", 0,  1),
("Eternittag B7 - Grå - 240", 0,  1),
("Eternittag B7 - Grå - 270", 0,  1),
("Eternittag B7 - Grå - 300", 0,  1),
("Eternittag B7 - Grå - 330", 0,  1),
("Eternittag B7 - Grå - 360", 0,  1),
("Eternittag B7 - Grå - 390", 0,  1),
("Eternittag B7 - Grå - 420", 0,  1),
("Eternittag B7 - Grå - 450", 0,  1),
("Eternittag B7 - Grå - 480", 0,  1),
("Eternittag B7 - Grå - 510", 0,  1),
("Eternittag B7 - Grå - 540", 0,  1),
("Eternittag B7 - Grå - 570", 0,  1),
("Eternittag B7 - Grå - 600", 0,  1),
("Eternittag B7 - Grå - 630", 0,  1),
("Eternittag B7 - Grå - 660", 0,  1),
("Eternittag B7 - Grå - 690", 0,  1),
("Eternittag B7 - Grå - 720", 0,  1),
("Eternittag B7 - Grå - 750", 0,  1),
("Eternittag B7 - Sort - 240", 0,  1),
("Eternittag B7 - Sort - 270", 0,  1),
("Eternittag B7 - Sort - 300", 0,  1),
("Eternittag B7 - Sort - 330", 0,  1),
("Eternittag B7 - Sort - 360", 0,  1),
("Eternittag B7 - Sort - 390", 0,  1),
("Eternittag B7 - Sort - 420", 0,  1),
("Eternittag B7 - Sort - 450", 0,  1),
("Eternittag B7 - Sort - 480", 0,  1),
("Eternittag B7 - Sort - 510", 0,  1),
("Eternittag B7 - Sort - 540", 0,  1),
("Eternittag B7 - Sort - 570", 0,  1),
("Eternittag B7 - Sort - 600", 0,  1),
("Eternittag B7 - Sort - 630", 0,  1),
("Eternittag B7 - Sort - 660", 0,  1),
("Eternittag B7 - Sort - 690", 0,  1),
("Eternittag B7 - Sort - 720", 0,  1),
("Eternittag B7 - Sort - 750", 0,  1),
("Eternittag B7 - Mokka (brun) - 240", 0,  1),
("Eternittag B7 - Mokka (brun) - 270", 0,  1),
("Eternittag B7 - Mokka (brun) - 300", 0,  1),
("Eternittag B7 - Mokka (brun) - 330", 0,  1),
("Eternittag B7 - Mokka (brun) - 360", 0,  1),
("Eternittag B7 - Mokka (brun) - 390", 0,  1),
("Eternittag B7 - Mokka (brun) - 420", 0,  1),
("Eternittag B7 - Mokka (brun) - 450", 0,  1),
("Eternittag B7 - Mokka (brun) - 480", 0,  1),
("Eternittag B7 - Mokka (brun) - 510", 0,  1),
("Eternittag B7 - Mokka (brun) - 540", 0,  1),
("Eternittag B7 - Mokka (brun) - 570", 0,  1),
("Eternittag B7 - Mokka (brun) - 600", 0,  1),
("Eternittag B7 - Mokka (brun) - 630", 0,  1),
("Eternittag B7 - Mokka (brun) - 660", 0,  1),
("Eternittag B7 - Mokka (brun) - 690", 0,  1),
("Eternittag B7 - Mokka (brun) - 720", 0,  1),
("Eternittag B7 - Mokka (brun) - 750", 0,  1),
("Eternittag B7 - Rødbrun - 240", 0,  1),
("Eternittag B7 - Rødbrun - 270", 0,  1),
("Eternittag B7 - Rødbrun - 300", 0,  1),
("Eternittag B7 - Rødbrun - 330", 0,  1),
("Eternittag B7 - Rødbrun - 360", 0,  1),
("Eternittag B7 - Rødbrun - 390", 0,  1),
("Eternittag B7 - Rødbrun - 420", 0,  1),
("Eternittag B7 - Rødbrun - 450", 0,  1),
("Eternittag B7 - Rødbrun - 480", 0,  1),
("Eternittag B7 - Rødbrun - 510", 0,  1),
("Eternittag B7 - Rødbrun - 540", 0,  1),
("Eternittag B7 - Rødbrun - 570", 0,  1),
("Eternittag B7 - Rødbrun - 600", 0,  1),
("Eternittag B7 - Rødbrun - 630", 0,  1),
("Eternittag B7 - Rødbrun - 660", 0,  1),
("Eternittag B7 - Rødbrun - 690", 0,  1),
("Eternittag B7 - Rødbrun - 720", 0,  1),
("Eternittag B7 - Rødbrun - 750", 0,  1),
("Eternittag B7 - Teglrød - 240", 0,  1),
("Eternittag B7 - Teglrød - 270", 0,  1),
("Eternittag B7 - Teglrød - 300", 0,  1),
("Eternittag B7 - Teglrød - 330", 0,  1),
("Eternittag B7 - Teglrød - 360", 0,  1),
("Eternittag B7 - Teglrød - 390", 0,  1),
("Eternittag B7 - Teglrød - 420", 0,  1),
("Eternittag B7 - Teglrød - 450", 0,  1),
("Eternittag B7 - Teglrød - 480", 0,  1),
("Eternittag B7 - Teglrød - 510", 0,  1),
("Eternittag B7 - Teglrød - 540", 0,  1),
("Eternittag B7 - Teglrød - 570", 0,  1),
("Eternittag B7 - Teglrød - 600", 0,  1),
("Eternittag B7 - Teglrød - 630", 0,  1),
("Eternittag B7 - Teglrød - 660", 0,  1),
("Eternittag B7 - Rødflammet - 240", 0, 1),
("Eternittag B7 - Rødflammet - 270", 0, 1),
("Eternittag B7 - Rødflammet - 300", 0,  1),
("Eternittag B7 - Rødflammet - 330", 0,  1),
("Eternittag B7 - Rødflammet - 360", 0,  1),
("Eternittag B7 - Rødflammet - 390", 0,  1),
("Eternittag B7 - Rødflammet - 420", 0,  1),
("Eternittag B7 - Rødflammet - 450", 0,  1),
("Eternittag B7 - Rødflammet - 480", 0,  1),
("Eternittag B7 - Rødflammet - 510", 0,  1),
("Eternittag B7 - Rødflammet - 540", 0,  1),
("Eternittag B7 - Rødflammet - 570", 0,  1),
("Eternittag B7 - Rødflammet - 600", 0,  1),
("Eternittag B7 - Rødflammet - 630", 0,  1),
("Eternittag B7 - Rødflammet - 660", 0,  1),
("Eternittag B7 - Rødflammet - 690", 0,  1),
("Eternittag B7 - Rødflammet - 720", 0,  1),
("Eternittag B7 - Rødflammet - 750", 0,  1);

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

DROP TABLE IF EXISTS CarportDB.sheds;
DROP TABLE IF EXISTS CarportDB.carports;
DROP TABLE IF EXISTS CarportDB.requests;
DROP TABLE IF EXISTS CarportDB.users_personalinfo;
DROP TABLE IF EXISTS CarportDB.users;
CREATE TABLE CarportDB.users (
	user_id INT(50) NOT NULL AUTO_INCREMENT,
	email VARCHAR(320) NOT NULL UNIQUE,
	password VARCHAR(50) NOT NULL,
	PRIMARY KEY (user_id)
);

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
	dateaccepted DATETIME NOT NULL,
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