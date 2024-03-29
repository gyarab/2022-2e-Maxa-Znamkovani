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
-- Table structure for table `trida_predmet_ucitel`
--

DROP TABLE IF EXISTS `trida_predmet_ucitel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trida_predmet_ucitel` (
  `id_trida_predmet_ucitel` int NOT NULL AUTO_INCREMENT,
  `trida_id` int DEFAULT NULL,
  `ucitel_id` int DEFAULT NULL,
  `predmet_id` int DEFAULT NULL,
  PRIMARY KEY (`id_trida_predmet_ucitel`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trida_predmet_ucitel`
--

LOCK TABLES `trida_predmet_ucitel` WRITE;
/*!40000 ALTER TABLE `trida_predmet_ucitel` DISABLE KEYS */;
INSERT INTO `trida_predmet_ucitel` VALUES (1,1,1,1),(2,1,2,2),(3,1,3,3),(8,1,4,4),(9,1,5,5),(10,2,1,1),(11,2,2,2),(12,2,3,3),(13,2,4,4),(14,2,5,5),(15,5,1,1),(16,5,2,2),(17,5,3,3),(18,5,4,4),(19,5,5,5),(20,6,1,1),(21,6,2,2),(22,6,3,3),(23,6,4,4),(24,6,5,5);
/*!40000 ALTER TABLE `trida_predmet_ucitel` ENABLE KEYS */;
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
