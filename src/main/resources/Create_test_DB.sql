
--
-- Host: localhost    Database: cruise_company_test
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `application`
--
DROP schema if exists cruise_company_test;
create schema cruise_company_test;

DROP TABLE IF EXISTS `application`;
DROP TABLE IF EXISTS `company`;
DROP TABLE IF EXISTS `cruise`;
DROP TABLE IF EXISTS `language`;
DROP TABLE IF EXISTS `language_cruise`;
DROP TABLE IF EXISTS `language_route`;
DROP TABLE IF EXISTS `liner`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `route`;
DROP TABLE IF EXISTS `staff`;
DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application` (
                               `application_id` bigint NOT NULL AUTO_INCREMENT,
                               `pass_photo` mediumblob,
                               `approved` enum('YES','NO') NOT NULL DEFAULT 'NO',
                               `paid` enum('PAID','UNPAID') NOT NULL DEFAULT 'UNPAID',
                               `closed` enum('CLOSED','OPENED') NOT NULL DEFAULT 'CLOSED',
                               `ended` enum('YES','NO') NOT NULL DEFAULT 'NO',
                               `fk_user_id` bigint NOT NULL,
                               `fk_cruise_id` bigint NOT NULL,
                               PRIMARY KEY (`application_id`),
                               KEY `fk_application_user1_idx` (`fk_user_id`),
                               KEY `fk_application_cruise1_idx` (`fk_cruise_id`),
                               CONSTRAINT `fk_cruise_id1` FOREIGN KEY (`fk_cruise_id`) REFERENCES `cruise` (`cruise_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                               CONSTRAINT `fk_user_id1` FOREIGN KEY (`fk_user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
INSERT INTO `cruise_company_test`.`application` (`application_id`, `pass_photo`,`approved`, `paid`, `closed`, `ended`, `fk_user_id`, `fk_cruise_id`) VALUES ('1', LOAD_FILE ('C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\fourth.jpg'),'NO', 'UNPAID', 'CLOSED', 'NO', '13', '1');
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
                           `company_id` bigint NOT NULL AUTO_INCREMENT,
                           `company_title` varchar(45) NOT NULL,
                           PRIMARY KEY (`company_id`),
                           UNIQUE KEY `title_UNIQUE` (`company_title`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (2,'Dreams makers'),(3,'Ocean taxi'),(1,'Water pleasure');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cruise`
--

DROP TABLE IF EXISTS `cruise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cruise` (
                          `cruise_id` bigint NOT NULL AUTO_INCREMENT,
                          `start_date` datetime NOT NULL,
                          `end_date` datetime NOT NULL,
                          `available` int NOT NULL,
                          `price` int NOT NULL,
                          `fk_route_id` bigint NOT NULL,
                          `fk_liner_id` bigint NULL,
                          PRIMARY KEY (`cruise_id`),
                          KEY `fk_cruise_liner1_idx` (`fk_liner_id`),
                          KEY `fk_route_id_idx` (`fk_route_id`),
                          CONSTRAINT `fk_liner_id` FOREIGN KEY (`fk_liner_id`) REFERENCES `liner` (`liner_id`) ON DELETE SET NULL ON UPDATE SET NULL,
                          CONSTRAINT `fk_route_id` FOREIGN KEY (`fk_route_id`) REFERENCES `route` (`route_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cruise`
--

LOCK TABLES `cruise` WRITE;
/*!40000 ALTER TABLE `cruise` DISABLE KEYS */;
INSERT INTO `cruise` VALUES (1,'2022-07-20 12:00:00','2022-08-02 12:00:00',1,10000,2,4),(2,'2022-04-04 16:00:00','2022-04-19 12:00:00',0,7500,6,6),(3,'2022-06-01 20:00:00','2022-09-01 12:00:00',1,25000,4,3),(4,'2022-08-12 12:00:00','2022-08-28 12:00:00',1,6000,5,2),(5,'2022-05-10 12:00:00','2022-06-01 12:00:00',1,9000,3,1),(6,'2022-12-17 12:00:00','2023-01-15 12:00:00',1,15000,1,5);
/*!40000 ALTER TABLE `cruise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language` (
                            `language_id` bigint NOT NULL AUTO_INCREMENT,
                            `language` varchar(45) DEFAULT NULL,
                            PRIMARY KEY (`language_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,'English'),(2,'Ukrainian');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language_cruise`
--

DROP TABLE IF EXISTS `language_cruise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language_cruise` (
                                   `fk_lc_language_id` bigint NOT NULL,
                                   `fk_lc_cruise_id` bigint NOT NULL,
                                   `cruise_title` varchar(45) NOT NULL,
                                   PRIMARY KEY (`fk_lc_language_id`,`fk_lc_cruise_id`),
                                   KEY `language_id_idx` (`fk_lc_language_id`),
                                   KEY `cruise_id_idx` (`fk_lc_cruise_id`),
                                   CONSTRAINT `fk_lc_cruise_id` FOREIGN KEY (`fk_lc_cruise_id`) REFERENCES `cruise` (`cruise_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                   CONSTRAINT `fk_lc_language_id` FOREIGN KEY (`fk_lc_language_id`) REFERENCES `language` (`language_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language_cruise`
--

LOCK TABLES `language_cruise` WRITE;
/*!40000 ALTER TABLE `language_cruise` DISABLE KEYS */;
INSERT INTO `language_cruise` VALUES (1,1,'Relax and dream'),(1,2,'Lazy drive'),(1,3,'Lasure wax'),(1,4,'Sweat move'),(1,5,'International'),(1,6,'World cross'),(2,1,'Розслабся і мрій'),(2,2,'Лінівий проїзд'),(2,3,'Лазурний віск'),(2,4,'Солодкий рух'),(2,5,'Інтернаціональний'),(2,6,'Крізь світ');
/*!40000 ALTER TABLE `language_cruise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language_route`
--

DROP TABLE IF EXISTS `language_route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language_route` (
                                  `fk_lr_language_id` bigint NOT NULL,
                                  `fk_lr_route_id` bigint NOT NULL,
                                  `start` varchar(45) NOT NULL,
                                  `end` varchar(45) NOT NULL,
                                  PRIMARY KEY (`fk_lr_language_id`,`fk_lr_route_id`),
                                  KEY `route_id_idx` (`fk_lr_route_id`),
                                  CONSTRAINT `fk_lr_language_id` FOREIGN KEY (`fk_lr_language_id`) REFERENCES `language` (`language_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                  CONSTRAINT `fk_lr_route_id` FOREIGN KEY (`fk_lr_route_id`) REFERENCES `route` (`route_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language_route`
--

LOCK TABLES `language_route` WRITE;
/*!40000 ALTER TABLE `language_route` DISABLE KEYS */;
INSERT INTO `language_route` VALUES (1,1,'Guangzhou','Guangzhou'),(1,2,'Marseille','Barcelona'),(1,3,'Barcelona','Miami'),(1,4,'Miami','Fort-Lauderdale'),(1,5,'Bridgetown','Barcelona'),(1,6,'Montego-Bay','Fort-Lauderdale'),(1,7,'San-Juan','Bridgerown'),(2,1,'Гуанчжоу','Гуанчжоу'),(2,2,'Марсель','Барселона'),(2,3,'Барселона','Майямі'),(2,4,'Майямі','Форт-Лодердейл'),(2,5,'Бріджтаун','Барселона'),(2,6,'Монтего-Бей','Форт-Лодердейл'),(2,7,'Сан-Джуан','Бріджтаун');
/*!40000 ALTER TABLE `language_route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liner`
--

DROP TABLE IF EXISTS `liner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `liner` (
                         `liner_id` bigint NOT NULL AUTO_INCREMENT,
                         `capacity` int NOT NULL,
                         `liner_name` varchar(45) NOT NULL,
                         `deck_amount` varchar(45) NOT NULL,
                         `liner_photo` mediumblob NOT NULL,
                         `fk_company_id` bigint NOT NULL,
                         PRIMARY KEY (`liner_id`),
                         KEY `company_id_idx` (`fk_company_id`),
                         CONSTRAINT `fk_company_id` FOREIGN KEY (`fk_company_id`) REFERENCES `company` (`company_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liner`
--

LOCK TABLES `liner` WRITE;
/*!40000 ALTER TABLE `liner` DISABLE KEYS */;
INSERT INTO `liner` VALUES (1,10000,'Braw','10', LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\first.jpg'),1),(2,12500,'Johnie','13', LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\second.jpg'),1);
INSERT INTO `liner` VALUES (3,20000,'Biggie','18', LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\third.jpg'),1);
INSERT INTO `liner` VALUES (4,4000,'Pride','5', LOAD_FILE ('C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\fourth.jpg'),2), (5, 7500, 'Focal', '12', LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\fifth.jpg'), 2),(6,2500,'Junior','5', LOAD_FILE('C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\sixth.jpg'),3);
/*!40000 ALTER TABLE `liner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `role_id` bigint NOT NULL AUTO_INCREMENT,
                        `type` varchar(45) NOT NULL,
                        PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'User'),(2,'Admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `route` (
                         `route_id` bigint NOT NULL AUTO_INCREMENT,
                         `ports_amount` int NOT NULL,
                         PRIMARY KEY (`route_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,12),(2,4),(3,5),(4,25),(5,3),(6,9),(7,11);
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
                         `staff_id` bigint NOT NULL AUTO_INCREMENT,
                         `staff_name` varchar(45) NOT NULL,
                         `staff_surname` varchar(45) NOT NULL,
                         `post` varchar(45) NOT NULL,
                         `fk_s_liner_id` bigint NULL,
                         PRIMARY KEY (`staff_id`),
                         KEY `fk_staff_liner1_idx` (`fk_s_liner_id`),
                         CONSTRAINT `fk_liner_id1` FOREIGN KEY (`fk_s_liner_id`) REFERENCES `liner` (`liner_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'Tomi','Brandt','Captain',1),(2,'Jade','Pemberton','Captain\'s assistant',1),(3,'Fergus','Flores','Senior mechanic',1),(4,'Kyron','Pace','Boatswain',1),(5,'Preston','Reyna','Sailor',1),(6,'Jordi','Avila','Captain',2),(7,'Kailum','Khan','Captain\'s assistant',2),(8,'Klay','Plant','Senior mechanic',2),(9,'Michael','Castillo','Boatswain',2),(10,'Oakley','Cobb','Sailor',2),(11,'Igor','Marin','Captain',3),(12,'Sami','Vinson','Captain\'s assistant',3),(13,'Malaki','Moody','Senior mechanic',3),(14,'Manuel','Redman','Navigator',3),(15,'Tye','Miles','Engineer',3),(16,'Semen','Castaneda','Cock',3),(17,'Nikhil','Lim','Skipper',3),(18,'Isaiah','Isaiah','Botswain',3),(19,'Anwar','Haworth','Sailor',3),(20,'Jeevan','Jeevan','Shipboy',3),(21,'Kashif','Salas','Captain',4),(22,'Kiki','Keeling','Captain\'s assistant',4),(23,'Arran','Muir','Senior mechanic',4),(24,'Damon','Kendall','Boatswain',4),(25,'Rajan','Mclaughlin','Sailor',4),(26,'Alfie-Lee','Dickens','Captain',5),(27,'Jason','O\'Reilly','Captain\'s assistant',5),(28,'Chester','Fulton','Senior mechanic',5),(29,'Kurtis','Hopper','Boatswain',5),(30,'Jaxx','Kirby','Sailor',5),(31,'Reegan','Collier','Captain',6),(32,'Jobe','Mansell','Captain\'s assistant',6),(33,'Raul','Carroll','Senior mechanic',6),(34,'Carson','Padilla','Boatswain',6),(35,'Hussain','Mclellan','Sailor',6);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `user_id` bigint NOT NULL AUTO_INCREMENT,
                        `name` varchar(45) NOT NULL,
                        `surname` varchar(45) NOT NULL,
                        `email` varchar(65) NOT NULL,
                        `password` varchar(65) NOT NULL,
                        `fk_role_id` bigint NOT NULL DEFAULT '1',
                        `fk_language_id` bigint NOT NULL DEFAULT '1',
                        PRIMARY KEY (`user_id`),
                        UNIQUE KEY `email_UNIQUE` (`email`),
                        KEY `fk_user_roles1_idx` (`fk_role_id`),
                        KEY `fk_language_id_idx` (`fk_language_id`),
                        CONSTRAINT `fk_language_id` FOREIGN KEY (`fk_language_id`) REFERENCES `language` (`language_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                        CONSTRAINT `fk_user_role` FOREIGN KEY (`fk_role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (13,'Bull','Buller','bull@gmail.com','cbfcb3e769c59bd462f35b2a3beb3aa541501c23',1,2),(16,'Changed','Byadmin','newww@gmail.com','071e3f5c708b7d3a5d9133de53b56b133ba6b0db',1,2),(20,'Adm','Admin','adm@gmail.com','1196c1a3b48828f7dddd75969f8c12c11ba1215f',2,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-20 19:07:17
