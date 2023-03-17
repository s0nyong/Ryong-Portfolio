-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: yong
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
-- Table structure for table `song`
--

DROP TABLE IF EXISTS `song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `song` (
  `song_No` int(11) NOT NULL AUTO_INCREMENT,
  `song_Name` varchar(255) NOT NULL,
  `song_Singer` varchar(45) NOT NULL,
  `song_Year` varchar(45) NOT NULL,
  `song_Genre` varchar(45) NOT NULL,
  `song_Mood` varchar(45) NOT NULL,
  `song_Count` int(11) NOT NULL DEFAULT '0',
  `song_Url` varchar(255) NOT NULL,
  `song_Id` varchar(45) NOT NULL,
  PRIMARY KEY (`song_No`),
  KEY `song_Id` (`song_Id`),
  CONSTRAINT `song_ibfk_1` FOREIGN KEY (`song_Id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song`
--

LOCK TABLES `song` WRITE;
/*!40000 ALTER TABLE `song` DISABLE KEYS */;
INSERT INTO `song` VALUES (19,'Attention','New jeans','2020','댄스','신나는',0,'https://youtu.be/js1CtxSY38I','sr'),(20,'Ditto','NewJeans (뉴진스)','2020','댄스','신나는',1,'https://youtu.be/V37TaRdVUQY','sr'),(21,'눈 (EYE) ','던말릭','2020','랩/힙합','신나는',1,'https://youtu.be/tC7S9REcDGE','sr'),(22,'새삥','지코','2020','랩/힙합','편안한',1,'https://youtu.be/azaZt7eccnc','sr'),(23,'Let It Be(Official Music Video)','The Beatles','2000','해외','편안한',2,'https://youtu.be/1LMSOfs10mA','hjw'),(24,'Avenged Sevenfold','A Little Piece Of Heaven','2020','해외','신나는',1,'https://youtu.be/KVjBCT2Lc94','hjw'),(25,'Bring Me To Life ','Evanescence','2000','해외','슬픈',1,'https://youtu.be/3YxaaGgTQYM','hjw'),(26,'7 rings ','Ariana Grande','2010','해외','행복한',1,'https://youtu.be/QYh6mYIJG2Y','hjw'),(27,'Boogie Man(부기맨)','홍진영','2010','트로트','행복한',0,'https://youtu.be/hxOI7wahw7w','ydw'),(28,'빈잔','남진','2020','트로트','슬픈',0,'https://youtu.be/ajA4cInURWI','ydw'),(29,'사랑은 꽃잎처럼','홍진영','2020','트로트','신나는',1,'https://youtu.be/aTx5trrmYCQ','ydw'),(30,'And July','헤이즈 (Heize)','2010','랩/힙합','슬픈',0,'https://youtu.be/rCeM57e2BfU','ldw'),(31,'DEAN ','instagram','2010','랩/힙합','조용한',0,'https://youtu.be/wKyMIrBClYw','ldw'),(32,'윤하','사건의지평선','2020','댄스','신나는',0,'https://youtu.be/BBdC1rl5sKY','ldw'),(33,'Birthday','레드벨벳','2020','댄스','신나는',0,'https://youtu.be/Ut1OzEVUiM4','ldw'),(34,'Ariana Grande','positions','2020','트로트','편안한',0,'https://youtu.be/tcYodQoapMg','ldw'),(35,'Say So','Doja Cat','2020','해외','슬픈',1,'https://youtu.be/pok8H_KF1FA','ldw'),(36,'Black Or White','Michael Jackson','2010','해외','슬픈',2,'https://youtu.be/F2AitTPI5U0','ldw'),(37,'In The End','Linkin Park','2000','해외','신나는',1,'https://youtu.be/eVTXPUF4Oz4','ldw'),(38,'말해줘','엄정화','1990','댄스','신나는',1,'https://youtu.be/F7_zeUePINE','ydh'),(39,'난 알아요','서태지와아이들','1990','랩/힙합','신나는',1,'https://youtu.be/sCmDxFgM7pg','ydh'),(40,'그녀에게 전해주오','소방차','2000','댄스','편안한',0,'https://youtu.be/Yu4Px9DX5Ng','ydh');
/*!40000 ALTER TABLE `song` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-17 16:53:54
