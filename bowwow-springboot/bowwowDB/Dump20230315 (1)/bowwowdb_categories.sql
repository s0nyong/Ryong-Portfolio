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
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'사료',NULL),(2,'건식사료',1),(3,'소프트사료',1),(4,'회식/습식사료',1),(5,'에어/동결사료',1),(6,'간식',NULL),(7,'껌',6),(8,'육포/사시미',6),(9,'저키/트릿',6),(10,'비스켓/시리얼',6),(11,'소시지',6),(12,'캔/파우치',6),(13,'뼈/건조간식',6),(14,'수제간식',6),(15,'파우더',6),(16,'우유/음료',6),(17,'건강관리',NULL),(18,'종합영양제',17),(19,'치아',17),(20,'피부/피모',17),(21,'뼈/관절',17),(22,'눈/귀',17),(23,'장/소화기',17),(24,'신장/요로',17),(25,'심장/심신안정',17),(26,'해충방지',17),(27,'영양간식',17),(28,'위생/배변',NULL),(29,'배변패드',28),(30,'배변판',28),(31,'기저귀/팬티',28),(32,'탈취/소독',28),(33,'물티슈/크리너',28),(34,'배변봉투/집게',28),(35,'배변유도제',28),(36,'미용/목욕',NULL),(37,'샴푸/린스',36),(38,'에센스/향수',36),(39,'브러쉬/거치대',36),(40,'클리퍼',36),(41,'미용가위',36),(42,'발톱/발 관리',36),(43,'타월/가운',36),(44,'염색약/그루밍파우더',36),(45,'급식기/급수기',NULL),(46,'식기/식탁',45),(47,'자동급식기',45),(48,'급수기/물병',45),(49,'정수기/필터',45),(50,'보관통/사료스푼',45),(51,'식기매트',45),(52,'젖병',45),(53,'하우스/울타리',NULL),(54,'하우스',53),(55,'방석/매트',53),(56,'계단',53),(57,'철장/울타리',53),(58,'안전문',53),(59,'이동장',NULL),(60,'이동가방',59),(61,'유모차',59),(62,'차량용',59),(63,'목줄/인식표/리드줄',NULL),(64,'목줄',63),(65,'가슴줄',63),(66,'인식표',63),(67,'리드줄',63),(68,'장난감',NULL),(69,'봉제장난감',68),(70,'치실장난감',68),(71,'고무장난감',68),(72,'공',68),(73,'원반',68),(74,'노즈워크',68),(75,'훈련',NULL),(76,'짖음 제어',75),(77,'행동 제어',75),(78,'클리커 트레이닝',75),(79,'서적',75),(80,'생활/가전',NULL),(81,'생활용품',80),(82,'청소용품',80),(83,'가전',80);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-15 17:36:25
