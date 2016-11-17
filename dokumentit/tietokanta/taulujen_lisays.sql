CREATE TABLE Kayttajat(
id INT NOT NULL AUTO_INCREMENT,
kayttajatunnus VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
etunimi VARCHAR(50) NOT NULL,
sukunimi VARCHAR(50) NOT NULL,
salasana VARCHAR(500) NOT NULL,
PRIMARY KEY (id)
) ENGINE=InnoDB CHARACTER SET=latin1;

CREATE TABLE Tunnit(
id INT NOT NULL AUTO_INCREMENT,
tuntien_maara DECIMAL(5,2) NOT NULL,
paivamaara TIMESTAMP,
kuvaus VARCHAR(1000),
kayttaja_id INT,
PRIMARY KEY(id),
FOREIGN KEY (kayttaja_id) REFERENCES Kayttajat(id) 
) ENGINE=InnoDB CHARACTER SET=latin1;

show create table kayttajat;

insert into Kayttajat(kayttajatunnus, email, etunimi, sukunimi, salasana)
values('aaro', 'aaroppi@live.com', 'Aaro', 'Liljelund', 'bbb');

ALTER TABLE Kayttajat
ADD enabled tinyint NOT NULL DEFAULT 1;

CREATE TABLE authority (
  id int(11) NOT NULL AUTO_INCREMENT,
  role varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY role (role)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE Kayttooikeudet (
  id int(11) NOT NULL AUTO_INCREMENT,
  Kayttajat_id int(11) NOT NULL,
  authority_id int(11) NOT NULL,
  PRIMARY KEY (id),
  KEY Kayttajat_id (Kayttajat_id),
  KEY authority_id (authority_id),
  CONSTRAINT Kayttooikeudet_ibfk_1 FOREIGN KEY (Kayttajat_id) REFERENCES Kayttajat (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT Kayttooikeudet_ibfk_2 FOREIGN KEY (authority_id) REFERENCES authority (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE Projekti (
  id int(11) NOT NULL AUTO_INCREMENT,
  nimi varchar(255) NOT NULL,
  kuvaus varchar(255),
  alku_pvm TIMESTAMP,
  loppu_pvm TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into Projekti (id, nimi, kuvaus, alku_pvm, loppu_pvm)
values (null, 'Tuntikirjanpidon kehitysprojekti', 'Softala1 - kurssilla kehitetään prototyyppi järjestelmästä, jonka tarkoitus on tuntien kirjaaminen', '2016-08-20 00:00:01', '2016-12-20 00:00:01');

CREATE TABLE Proj_kayt (
  kayt_id int(11) NOT NULL,
  proj_id int(11) NOT NULL,
  CONSTRAINT proj_kayt_id PRIMARY KEY (kayt_id, proj_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE Tunnit
ADD projekti int(11) NOT NULL;

ALTER TABLE Tunnit
ADD CONSTRAINT proj FOREIGN KEY (projekti) REFERENCES Projekti (id);

update Tunnit set projekti=1 where id > 0;

/*
ALTER TABLE Kayttajat
DROP COLUMN enabled;
*/
