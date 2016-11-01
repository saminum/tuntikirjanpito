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

/*
ALTER TABLE Kayttajat
DROP COLUMN enabled;
*/
