-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: security1
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(80) DEFAULT NULL,
  `age` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (18,'Test1009','1009','test1009@yandex.ru','$2a$08$d.icLHSY1MkQ76q3Nmoz0OdCbuemf1HBmhnc28q8gNw9lF3OvWf16',1009),(19,'Dmitrii','Zuderman','d.zuderman@yandex.ru','$2a$08$KEegO7ldKLnvojulrzkz3eW282c1nsMpIa0TFNWKQYsdV/vqnvGT2',35),(20,'Test1001 ','1001','test1001@yandex.ru','$2a$08$XLuoWy7clO..CES9wKEzbe1ikoCWxVVVbz/9F203/EqvSqjwMT6TG',1001),(21,'Test1002 ','1002 ','test1002@yandex.ru','$2a$08$h8jOBx/coxq4ovA2P1YUAON8Ldgij.AZ/I0WGjywGbz6CXfxCdXVS',1002),(22,'Test1003','1003','test1003@yandex.ru','$2a$08$CezjV0zbA4VtsVfcAq7s2etuDfFr8UFVHOEFX/E95eXQLYL4oJKLG',1003),(24,'Test1004','1004','test1004@yandex.ru','$2a$08$hINoxW8ye38x9M22DAVXm.WDywJ76K6QY9uKCWmG2l8cRQ1qxJMee',1004),(25,'Test1005','1005','test1005@yandex.ru','$2a$08$XX60g.eIfBjlruAsREvnXePai/MLVWo/LsErBAtuSW0q1fyHdiZY.',1005),(26,'Leonid','Smirnov','smirnov@yandex.ru','$2a$08$P8HqlGeSBhFx4Cly1e80y.oIV4IWceAry7pM3RiyofZgaKmX7.56W',25);
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

-- Dump completed on 2024-01-14 15:20:46
