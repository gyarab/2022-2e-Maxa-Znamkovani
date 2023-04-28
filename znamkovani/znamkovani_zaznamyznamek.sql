-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: znamkovani
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `zaznamyznamek`
--

DROP TABLE IF EXISTS `zaznamyznamek`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zaznamyznamek` (
  `zaznamZnamky_id` int NOT NULL AUTO_INCREMENT,
  `id_trida_predmet_ucitel` varchar(45) DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `test` varchar(45) DEFAULT NULL,
  `znamka` varchar(45) DEFAULT NULL,
  `hodnota` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`zaznamZnamky_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zaznamyznamek`
--

LOCK TABLES `zaznamyznamek` WRITE;
/*!40000 ALTER TABLE `zaznamyznamek` DISABLE KEYS */;
INSERT INTO `zaznamyznamek` VALUES (75,'1',1,'Zlomky','1','3'),(76,'1',2,'Zlomky','2','3'),(77,'1',4,'Zlomky','5','3'),(79,'1',5,'Zlomky','3','3'),(80,'20',17,'Vzorce 1','1','3'),(81,'20',20,'Vzorce 1','2','3'),(82,'20',18,'Vzorce 1','5','3'),(83,'20',19,'Vzorce 1','2','3'),(84,'20',16,'Vzorce 1','3','3'),(85,'20',16,'Zkoušení 1','1','4'),(86,'20',17,'Zkoušení 1','2','5'),(87,'20',18,'Zkoušení 1','1','5'),(88,'20',19,'Zkoušení 1','4','2'),(89,'20',20,'Zkoušení 1','5','5'),(90,'24',16,'Šplh','1','7'),(91,'24',17,'Šplh','1','7'),(92,'24',18,'Šplh','5','7'),(93,'24',19,'Šplh','2','7'),(95,'24',16,'Rozcvička','3','3'),(96,'24',17,'Rozcvička','2','3'),(97,'24',18,'Rozcvička','1','3'),(98,'24',19,'Rozcvička','2','3'),(99,'24',20,'Rozcvička','4','3');
/*!40000 ALTER TABLE `zaznamyznamek` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-27 21:53:48
