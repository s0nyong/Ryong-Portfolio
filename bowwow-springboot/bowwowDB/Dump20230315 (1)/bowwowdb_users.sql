-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: bowwowdb
-- ------------------------------------------------------
-- Server version	8.0.13

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
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'SEOUL','admin@email.com',_binary '','admin','admin','$2a$10$Cec8c.mO3I5.EoTlroRwSO03MSAy6FaRNk44aDNERwjXBsCCYyMtW','11111111',0,'ADMIN'),(2,'SEOUL','manager@email.com',_binary '','manager','manager','$2a$10$whGdHcWO65M.2Zc4gg83XujLR0iq9rmymrjrbpLfF1R/ug6G1u5qW','11111111',0,'MANAGER'),(3,'SEOUL','shipper@email.com',_binary '','shipper','shipper','$2a$10$XXOz.6X6rSasJDssjou5ueqR4MZ2MpyRBeG7I5atlmwDuDwvGFBMi','11111111',0,'SHIPPER'),(4,'SEOUL','editor@email.com',_binary '','editor','editor','$2a$10$SMsCuwEeJU7sJuggLxcw2O9bXGWJBntv5vyjLKIKCjouiJ0hx72wy','11111111',0,'EDITOR'),(5,'SEOUL','assistant@email.com',_binary '','assistant','assistant','$2a$10$qdidUqMmHwrObqk7aUQ7vur4NgKlCODX17Z9BTEg6JDIumYG6U/VK','11111111',0,'ASSISTANT'),(6,'SEOUL','sss@email.com',_binary '','subin','subini','$2a$10$oqWwWzOY/rY6hiGd/Z53zed76deb.Z9eVTulXKhpcVN5pM09c29mm','11111111',0,'일반');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-15 17:36:26
