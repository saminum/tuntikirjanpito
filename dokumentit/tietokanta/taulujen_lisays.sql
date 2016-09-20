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

