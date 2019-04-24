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
