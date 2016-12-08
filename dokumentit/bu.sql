-- MySQL dump 10.14  Distrib 5.5.51-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: a1501110
-- ------------------------------------------------------
-- Server version	5.5.51-MariaDB-1~wheezy

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Kayttajat`
--

DROP TABLE IF EXISTS `Kayttajat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Kayttajat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kayttajatunnus` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `etunimi` varchar(50) NOT NULL,
  `sukunimi` varchar(50) NOT NULL,
  `salasana` varchar(500) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Kayttajat`
--

LOCK TABLES `Kayttajat` WRITE;
/*!40000 ALTER TABLE `Kayttajat` DISABLE KEYS */;
INSERT INTO `Kayttajat` VALUES (1,'aaro','aaroppi@live.com','Aaro','Liljelund','$2a$10$P.Vn1Gg4Z09IPaIQ9HqV0usIySEesYgLUZvAJKVBINTBh5C4rygjO',1),(2,'heikki','heikki_petrell@hotmail.com','Heikki','Petrell','$2a$10$dZy/1wmCdqRux4rvkBLTKuQUO/nWVgyFfiAdjzyP5kRRLFmBK0qsK',1),(3,'sami','a1500928@myy.haaga-helia.fi','Sami','Nummela','$2a$10$x7yhEHeFBMfXAo4nBkXg9u6wUdAdhUmCJf8KkWvNSnmC4amhApQFm',1),(4,'joonas','1500850@myy.haaga-helia.fi','Joonas','Mattila','$2a$10$KM3wVF8yP7PILQcGuR2AfusHtCWuu8Nuyux9pzQD7oyM6kjiqX4ru',1),(5,'jukka','a1500956@myy.haaga-helia.fi','Jukka','Nilsson','$2a$10$l.NtPR1vmFHbDKZonJ1UseVN2q.8NazSueoSjGxMIDFW4/ep2q9Ay',1),(7,'user','sadf@saf.com','user','uuseri','$2a$10$VGjtt4VCJAOW8wlxygaWX.2BkuVjC3d0wNTZdgTBB54NpQHgNSF9u',1),(8,'admin','-','-','-','$2a$10$eM46LG9vtpUY5ZQGFD6Vq.XBxKF61tgthsU8ifQLA8fmppUPa82xe',1),(21,'admin2','admin2@admin.com','-','-','$2a$10$uoW9KIsYY97sh.QSyOchQekcDA7jGVgET4YEFNwSOqF8CeblKJvIK',1),(22,'essiesimerkki','testi.testi@testi.fi','Essi','Esimerkki','$2a$10$V2bGCgG0sf6ev80zlrDTXe/QzsJFtWJF75jhD.Fks5o6w99TXYB6.',1),(23,'jukkawelin','jukkawelin@asdfasdf.com','Jukka','Welin','$2a$10$c.ekFK10lzJY5ASh6VRdI.Gzj/u10nbWLE1u7LBBVpSkhjhZSVliq',1);
/*!40000 ALTER TABLE `Kayttajat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Kayttooikeudet`
--

DROP TABLE IF EXISTS `Kayttooikeudet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Kayttooikeudet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Kayttajat_id` int(11) NOT NULL,
  `authority_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Kayttajat_id` (`Kayttajat_id`),
  KEY `authority_id` (`authority_id`),
  CONSTRAINT `Kayttooikeudet_ibfk_1` FOREIGN KEY (`Kayttajat_id`) REFERENCES `Kayttajat` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Kayttooikeudet_ibfk_2` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Kayttooikeudet`
--

LOCK TABLES `Kayttooikeudet` WRITE;
/*!40000 ALTER TABLE `Kayttooikeudet` DISABLE KEYS */;
INSERT INTO `Kayttooikeudet` VALUES (1,5,1),(2,1,1),(3,3,1),(4,4,1),(5,2,1),(6,8,2),(7,7,1),(13,21,1),(14,22,1),(15,23,1);
/*!40000 ALTER TABLE `Kayttooikeudet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Proj_kayt`
--

DROP TABLE IF EXISTS `Proj_kayt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Proj_kayt` (
  `kayt_id` int(11) NOT NULL,
  `proj_id` int(11) NOT NULL,
  PRIMARY KEY (`kayt_id`,`proj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Proj_kayt`
--

LOCK TABLES `Proj_kayt` WRITE;
/*!40000 ALTER TABLE `Proj_kayt` DISABLE KEYS */;
INSERT INTO `Proj_kayt` VALUES (0,2),(0,3),(1,1),(1,2),(2,1),(2,2),(2,3),(3,1),(3,2),(3,3),(4,1),(4,2),(5,1),(5,2),(22,3),(23,2);
/*!40000 ALTER TABLE `Proj_kayt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Projekti`
--

DROP TABLE IF EXISTS `Projekti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Projekti` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nimi` varchar(255) NOT NULL,
  `kuvaus` varchar(255) DEFAULT NULL,
  `alku_pvm` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `loppu_pvm` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Projekti`
--

LOCK TABLES `Projekti` WRITE;
/*!40000 ALTER TABLE `Projekti` DISABLE KEYS */;
INSERT INTO `Projekti` VALUES (1,'Tuntikirjanpidon kehitysprojekti','Softala1 - kurssilla kehitetään prototyyppi järjestelmästä, jonka tarkoitus on tuntien kirjaaminen','2016-08-19 21:00:01','2016-12-19 22:00:01'),(2,'Testiprojekti','Testiprojekti projektin testaamista varten','2016-11-22 07:09:34','0000-00-00 00:00:00'),(3,'Testiprojekti2','Testiprojekti projektin testaamista varten 2','2016-11-24 09:31:11','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `Projekti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tunnit`
--

DROP TABLE IF EXISTS `Tunnit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tunnit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tuntien_maara` decimal(5,2) NOT NULL,
  `paivamaara` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `kuvaus` varchar(1000) DEFAULT NULL,
  `kayttaja_id` int(11) DEFAULT NULL,
  `projekti` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kayttaja_id` (`kayttaja_id`),
  KEY `proj` (`projekti`),
  CONSTRAINT `proj` FOREIGN KEY (`projekti`) REFERENCES `Projekti` (`id`),
  CONSTRAINT `Tunnit_ibfk_1` FOREIGN KEY (`kayttaja_id`) REFERENCES `Kayttajat` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=465 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tunnit`
--

LOCK TABLES `Tunnit` WRITE;
/*!40000 ALTER TABLE `Tunnit` DISABLE KEYS */;
INSERT INTO `Tunnit` VALUES (319,2.00,'2016-08-31 21:00:01','Yleist&auml; s&auml;&auml;t&ouml;&auml;',2,1),(320,2.00,'2016-08-31 21:00:01','suunnittelua',1,1),(321,3.00,'2016-08-31 21:00:01','suunnittelua',3,1),(322,3.00,'2016-08-31 21:00:01','suunnittelua',4,1),(323,3.00,'2016-08-31 21:00:01','suunnittelua',5,1),(324,3.00,'2016-09-05 21:00:01','M&auml;&auml;rittely&auml;',2,1),(325,3.00,'2016-09-05 21:00:01','suunnittelua ja m&auml;&auml;rittely&auml;',1,1),(326,3.00,'2016-09-05 21:00:01','m&auml;&auml;ritys',3,1),(327,3.00,'2016-09-05 21:00:01','m&auml;&auml;ritys',5,1),(328,4.00,'2016-09-07 21:00:01','M&auml;&auml;rityst&auml;',1,1),(329,4.00,'2016-09-07 21:00:01','M&auml;&auml;rittely&auml;',2,1),(330,4.00,'2016-09-07 21:00:01','m&auml;&auml;ritys',5,1),(331,4.00,'2016-09-07 21:00:01','m&auml;&auml;ritys',3,1),(332,4.00,'2016-09-12 21:00:01','m&auml;&auml;ritys',1,1),(333,4.00,'2016-09-12 21:00:01','M&auml;&auml;rittely&auml;',2,1),(334,4.00,'2016-09-12 21:00:01','toteutus',5,1),(335,4.00,'2016-09-12 21:00:01','m&auml;&auml;ritys',3,1),(336,4.00,'2016-09-12 21:00:01','m&auml;&auml;ritys',4,1),(337,2.00,'2016-09-14 21:00:01','M&auml;&auml;rittely&auml;',2,1),(338,5.00,'2016-09-14 21:00:01','m&auml;&auml;ritys',3,1),(339,5.00,'2016-09-14 21:00:01','m&auml;&auml;ritys',4,1),(340,5.00,'2016-09-14 21:00:01','m&auml;&auml;ritys',1,1),(341,4.00,'2016-09-14 21:00:01','toteutus',5,1),(342,5.00,'2016-09-19 21:00:01','projektin koodasta',1,1),(343,5.00,'2016-09-19 21:00:01','Testaussuunnittelua',2,1),(344,5.00,'2016-09-19 21:00:01','toteutus',5,1),(345,5.00,'2016-09-19 21:00:01','toteutus',3,1),(346,2.00,'2016-09-19 21:00:01','Tunnin poisto tehty, tuntuDAO, PoistaTuntiServlet',4,1),(347,5.00,'2016-09-21 21:00:01','projektin korjausta',3,1),(348,4.00,'2016-09-21 21:00:01','Esittely, pom m&auml;&auml;ritys',1,1),(349,5.00,'2016-09-21 21:00:01','Demo esitys ja sovelluksen korjausta',2,1),(350,5.00,'2016-09-21 21:00:01','Demo esitys ja sovelluksen korjausta',5,1),(351,5.00,'2016-09-21 21:00:01','projektin korjausta, pom kuntoon, projekti protolle',4,1),(352,4.00,'2016-09-26 21:00:01','Ohjelmiston muuntamista MVC muotoon',2,1),(353,4.00,'2016-09-26 21:00:01','Ohjelmiston muuntamista MVC muotoon',3,1),(354,4.00,'2016-09-26 21:00:01','Projekti MVC mallin mukaiseksi',4,1),(355,4.00,'2016-09-26 21:00:01','xml.filet ja dropdown menun arvot kannasta.',5,1),(356,1.00,'2016-09-27 21:00:01','Projektin mvc mallin korjausta, vikoja etsitty ja korjattu. Testaus puuttuu.',4,1),(357,4.00,'2016-09-28 21:00:01','datepickerin lis&auml;ys, luokkien s&auml;&auml;t&ouml;&auml;.',1,1),(358,4.00,'2016-09-28 21:00:01','K&auml;ytt&ouml;tapauksen tekeminen, tuntien yhteenlaskun toteutus, tunnin poistotoiminto',3,1),(359,4.00,'2016-09-28 21:00:01','K&auml;ytt&ouml;tapauksen tekeminen, tuntien yhteenlaskun toteutus, tunnin poistotoiminto',4,1),(360,4.00,'2016-09-28 21:00:01','Tutkimusta k&auml;ytt&ouml;liittym&auml;n elementteihin liittyen.',1,1),(361,4.00,'2016-09-28 21:00:01','Luotu MVC formit ja luotu lis&auml;&auml; tunnit koodi',2,1),(362,4.00,'2016-09-28 21:00:01','Luotu MVC formit ja luotu lis&auml;&auml; tunnit koodi',5,1),(363,4.50,'2016-10-03 21:00:01','Front endin hiomista mm. bootsrappia ja css&auml;&auml; k&auml;ytt&auml;en',3,1),(364,5.00,'2016-10-03 21:00:01','BeanValidationin tekeminen, ongelmia datepickerin ja tiedon kuljettamisen kanssa. Nyt kaikki ok.',4,1),(365,4.00,'2016-10-03 21:00:01','Loggerin teko',5,1),(366,4.00,'2016-10-03 21:00:01','P&auml;iv&auml;m&auml;&auml;rien tulostamisen suunnittelua',1,1),(367,4.00,'2016-10-03 21:00:01','Aaron kanssa p&auml;iv&auml;m&auml;&auml;r&auml;n muokkausta ja yhden henkil&ouml;n tuntienlistauksen suunnittelua',2,1),(368,3.00,'2016-10-05 21:00:01','Mergeily&auml;, s&auml;&auml;t&ouml;&auml; reviewi&auml; varten',1,1),(369,4.00,'2016-10-05 21:00:01','Mergeily&auml; ja valmistelua esityst&auml; varten',2,1),(370,6.00,'2016-10-05 21:00:01','BeanValidationin kanssa tekemist&auml;. Toimivalla mallilla ollaan nyt!',4,1),(371,4.00,'2016-10-05 21:00:01','Frontin hiomista maanantain esityst&auml; varten. Testailua, fixailua..',3,1),(372,3.00,'2016-10-05 21:00:01','Henkil&ouml;tietojen hakutoiminnon luonti, testitapaukset sek&auml; testaus.',5,1),(373,3.50,'2016-10-10 21:00:01','review ja retrospektiivi',4,1),(374,3.50,'2016-10-10 21:00:01','review ja retrospektiivi',2,1),(375,3.50,'2016-10-10 21:00:01','review ja retrospektiivi',3,1),(376,3.50,'2016-10-10 21:00:01','review ja retrospektiivi',5,1),(377,3.50,'2016-10-10 21:00:01','review ja retrospektiivi',1,1),(378,3.50,'2016-10-24 21:00:01','Opiskelua',1,1),(379,3.50,'2016-10-24 21:00:01','Xss escape kuvauksessa kuntoon. Lis&auml;ksi autoin spring boottiin vienniss&auml;.',4,1),(380,3.50,'2016-10-24 21:00:01','Sprint suunnittelua. Projektin muuttamista Spring boot muotoon.',3,1),(381,3.50,'2016-10-24 21:00:01','Spring session',5,1),(382,4.00,'2016-10-24 21:00:01','Spring boot opiskelua',2,1),(383,4.00,'2016-10-26 21:00:01','Springbootiksi muokkamista',3,1),(384,4.00,'2016-10-26 21:00:01','Sessioita',5,1),(385,4.00,'2016-10-26 21:00:01','i18n ohjelmointia',1,1),(386,4.00,'2016-10-26 21:00:01','Spring boot ja spring sec perehtymist&auml;',2,1),(387,4.00,'2016-10-26 21:00:01','sekalaista jeesausta',4,1),(388,4.50,'2016-10-31 22:00:01','JSP session ongelman ratkomista',2,1),(389,3.00,'2016-10-31 22:00:01','Projektin parissa sekalaista tekemist&auml;',4,1),(390,5.00,'2016-10-31 22:00:01','spring boot fixaus',3,1),(391,3.50,'2016-10-31 22:00:01','Kirjautumis-toiminnon luonti',5,1),(392,3.00,'2016-10-31 22:00:01','JSP session ongelman ratkomista',1,1),(393,4.00,'2016-11-02 22:00:01','Session ongelman ratkontaa',2,1),(394,4.00,'2016-11-02 22:00:01','Spring bootin i18n selvittely&auml;, Kielen vaihto ei kadota listaa henkil&ouml;n tunneista fiksattu',4,1),(395,4.00,'2016-11-02 22:00:01','i18n virheilmoitusten ohjelmointi, ongelmien ratkomista',1,1),(396,4.00,'2016-11-02 22:00:01','spring boot i18n',3,1),(397,3.50,'2016-11-02 22:00:01','Spring boot security',5,1),(398,4.00,'2016-11-07 22:00:01','Spring boot spring securityn kanssa kamppailua.',4,1),(399,4.00,'2016-11-07 22:00:01','Kalenterin kieli',1,1),(400,2.00,'2016-11-07 22:00:01','Bcrypt h&auml;sh&auml;ys muutos',3,1),(401,4.00,'2016-11-07 22:00:01','Spring boot spring securityn kanssa kamppailua.',3,1),(402,1.00,'2016-11-08 22:00:01','spring boot frontin korjailua',3,1),(403,3.00,'2016-11-09 22:00:01','SpringBoot war deploy vaikeuksia...',4,1),(404,3.00,'2016-11-09 22:00:01','SpringBoot war deploy vaikeuksia...',3,1),(405,3.00,'2016-11-14 22:00:01','Review ja retrospektri',5,1),(406,3.00,'2016-11-14 22:00:01','Review ja retrospektri',1,1),(408,4.00,'2016-11-16 22:00:01','Tietokannan muuttaminen tukemaan monia projekteja',1,1),(409,3.50,'2016-11-21 22:00:01','Tietokannan sis&auml;ll&ouml;n korjaaminen, p&auml;iv&auml;m&auml;&auml;r&auml;t, englanninnosten korjailua',1,1),(420,4.00,'2016-11-21 22:00:01','Notifikaatioiden luonti',2,1),(422,5.00,'2016-11-21 22:00:01','Rekister&ouml;itymislomakkeen luontia! Transaktiota vaille valmis',4,1),(423,2.50,'2016-11-19 22:00:01','Lauantai/Sunnuntai tehty rekister&ouml;itymislomake laitettu aluille',4,1),(424,3.50,'2016-11-14 22:00:01','SpringBoot deployta koitettu saada toimimaan, Spring review!',4,1),(426,2.00,'2016-11-24 09:57:02','testaus',2,2),(427,2.00,'2016-11-24 10:00:48','testaus',3,3),(429,2.00,'2016-11-24 10:02:00','testaus',2,3),(431,2.00,'2016-11-23 22:00:01','Ankkurointi, tunnit yhteens&auml;, virheelliset kent&auml;t punaisiksi',5,1),(436,2.00,'2016-11-28 22:00:01','T&Auml;M&Auml;N EI PIT&Auml;ISI N&Auml;KY&Auml; MUISSA PROJEKTEISSA',3,2),(438,3.00,'2016-11-14 22:00:01','rewiev ja retro',3,1),(439,4.00,'2016-11-16 22:00:01','spring boot deploy',3,1),(440,4.00,'2016-11-21 22:00:01','Admin toiminnallisuuksia, moniprojektituki',3,1),(441,1.00,'2016-11-23 22:00:01','moniprojektituki',3,1),(442,3.00,'2016-11-28 22:00:01','Uuden projektin lis&auml;ys, henkil&ouml;n lis&auml;ys projektiin, projektin ja siihen liittyvien tuntien poisto',3,1),(443,4.00,'2016-11-28 22:00:01','ulkoasu',5,1),(445,4.00,'2016-11-28 22:00:01','Tuntin&auml;kym&auml; projektikohtaisesti',2,1),(446,3.00,'2016-11-14 22:00:01','Review ja retro',2,1),(448,4.00,'2016-11-30 22:00:01','Pient&auml; viilailua',2,1),(449,4.00,'2016-11-30 22:00:01','ulkoasu',5,1),(450,4.50,'2016-11-30 22:00:01','branchien yhdist&auml;minen ja etusivu.jsp validoinnit. Pient&auml; korjailua sielt&auml; t&auml;&auml;lt&auml;',3,1),(451,2.00,'2016-11-23 22:00:01','Aloitettiin salasananvaihto-toimintoa',1,1),(452,4.00,'2016-11-28 22:00:01','Jatkettiin salasanan vaihtotoimintoa',1,1),(453,4.00,'2016-11-30 22:00:01','Jatkettiin salasanan vaihtotoimintoa',1,1),(454,2.00,'2016-11-30 22:00:01','Pient&auml; fiksailua',4,1),(456,5.00,'2016-12-05 22:00:01','k&auml;ytt&ouml;oikeudet: admin kaikki oikeudet, k&auml;ytt&auml;j&auml; voi lis&auml;t&auml; ja poistaa vain omia',3,1),(462,3.00,'2016-12-05 22:00:01','asdfsdfsadf',3,3),(463,1.00,'2016-12-05 22:00:01','Projektin poiston varmistus',4,1);
/*!40000 ALTER TABLE `Tunnit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES (2,'ROLE_ADMIN'),(1,'ROLE_USER');
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-08 10:46:01
