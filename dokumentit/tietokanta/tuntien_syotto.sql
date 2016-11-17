INSERT INTO Tunnit(tuntien_maara, kuvaus, kayttaja_id)VALUES(2, 'suunnittelua', 1);
INSERT INTO Tunnit (tuntien_maara, kuvaus, kayttaja_id) VALUES(?,?,?);
----Multiprojektituen jälkeen syöttö-------
INSERT INTO Tunnit(tuntien_maara, kuvaus, kayttaja_id, projekti)VALUES(2, 'suunnittelua', 1, 1);