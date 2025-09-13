-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sns
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `alram`
--

DROP TABLE IF EXISTS `alram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alram` (
  `alno` int NOT NULL AUTO_INCREMENT,
  `uno` int NOT NULL,
  `rdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` varchar(1) NOT NULL DEFAULT 'N' COMMENT '조회안함 - N\n조회함    - Y',
  `type` varchar(1) NOT NULL DEFAULT 'M' COMMENT 'F - 알람 -> 팔로우\nL - 알람 -> 좋아요\nC - 알람 -> 신고\nR - 알람 -> 댓글\n',
  `no` int NOT NULL,
  PRIMARY KEY (`alno`),
  KEY `uno` (`uno`),
  CONSTRAINT `alram_ibfk_1` FOREIGN KEY (`uno`) REFERENCES `user` (`uno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alram`
--

LOCK TABLES `alram` WRITE;
/*!40000 ALTER TABLE `alram` DISABLE KEYS */;
INSERT INTO `alram` VALUES (9,2,'2024-11-11 00:48:20','Y','L',34),(10,2,'2024-11-11 00:48:33','Y','C',21),(11,2,'2024-11-11 01:34:09','Y','L',36),(12,2,'2024-11-11 01:34:48','N','L',37),(13,2,'2024-11-11 02:35:13','Y','L',38),(15,1,'2024-11-11 03:34:10','Y','L',40),(16,1,'2024-11-11 03:34:14','N','L',41),(17,2,'2024-11-11 04:33:13','N','R',47),(20,2,'2024-11-11 07:15:31','N','L',43),(21,2,'2024-11-11 07:37:34','N','L',44),(23,2,'2024-11-12 00:02:51','N','L',46);
/*!40000 ALTER TABLE `alram` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attach`
--

DROP TABLE IF EXISTS `attach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attach` (
  `ano` int NOT NULL AUTO_INCREMENT,
  `pname` varchar(50) NOT NULL,
  `fname` varchar(200) DEFAULT NULL,
  `bno` int NOT NULL,
  PRIMARY KEY (`ano`),
  KEY `bno` (`bno`),
  CONSTRAINT `attach_ibfk_1` FOREIGN KEY (`bno`) REFERENCES `board` (`bno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attach`
--

LOCK TABLES `attach` WRITE;
/*!40000 ALTER TABLE `attach` DISABLE KEYS */;
INSERT INTO `attach` VALUES (1,'44cfb725-7162-4184-a272-87d17fe22576','About BuzzFeed.jpg',1),(2,'60463d61-d433-4ab5-bb88-4c56355b3b1c','4 Ingredient Lemon Pound Cake (No Butter or Oil).jpg',2),(3,'8dd7f1e8-f9e6-48b3-8e7d-5e5136d45aaa','귤타르트.jpg',3),(4,'72b17849-510e-4023-88e5-17995e9b7629','Best Classic Scones.jpg',4),(5,'377b6833-a974-4b0d-a632-1d1d82106b65','라구파스타.jpg',5),(6,'ffd9f8c4-5ea3-4f1f-8839-2b430b0b0c2f','크림스튜.jpg',6),(7,'4258b405-d7a7-437c-9253-5b517d854929','Cucumber Yogurt Salad (Creamy + Refreshing) _ Maple + Mango.jpg',7),(11,'883554f1-91d5-4bc7-8cad-8639d50fa834','syteynb    yryeawstguj.jpg',11),(12,'1720af53-8491-4932-ba37-79accc8d34a3','Potato Wedges.jpg',12),(13,'4db76ab0-87a3-4f4a-9044-afee1b749cf6','Creamy Shrimp Alfredo Pasta - (Video) Cooked by Julie.jpg',13),(14,'60358220-6ea5-4a12-9969-195c7f19523f','Pan-Seared Salmon with Lemon Garlic Sauce.jpg',14),(15,'79f31ce3-e004-461c-9663-7f335c4bea2c','Garlic Parmesan Butter Roasted Potatoes.jpg',15),(16,'47d82e02-412f-4def-a169-709441808df0','Spicy Enoki Mushrooms _ Iankewks.jpg',16),(17,'e41125bb-06a2-47b2-8c9f-92f84d4085eb','Japchae (Korean Sweet Potato Noodles).jpg',17),(18,'ed9cf03c-b752-4ae5-a5b1-a1b676d8c476','함박.jpg',18),(19,'865c99be-2a69-468f-bff5-82f0c83d1df8','おきは?_♂️okIha on Twitter.jpg',19),(20,'a9a488ae-fb3b-477c-833c-0b12c62621d2','다운로드 (1).jpg',20),(21,'50cc2c47-3e0f-4d78-b7e3-74b0fbf6f8a2','_☆save & follow☆_.jpg',21),(22,'40d29122-40a5-4593-913b-962e3f5be25c','@???????????????.jpg',22),(23,'a5f048d9-f572-4785-abae-77b9c31c34c0','d914276b-e3d9-4602-8e12-543be4ad2119.jpg',23),(24,'359ee5e4-3816-4509-b4be-d8dcde3f9054','h o l l a.jpg',24),(25,'d8ac3795-3183-4a69-a835-ab7deb4fab45','다운로드 (5).jpg',25),(26,'1e9111aa-2f05-4ad5-9dc7-636211fdf1db','P J ♡.jpg',26),(27,'6421d2c2-01c3-4a44-b55c-b339790cbb7d','????? ??????.jpg',27),(28,'75b032ad-4347-4eaa-89e5-dba93d764858','bcc1ea69-8d73-4cc1-a8e1-6cc1feb0b078.jpg',28),(29,'7f0047d7-7a60-457a-bb2e-cae4572a9bf1','2d484732-fcd6-4f90-8661-7629a0bd1e58.jpg',29),(30,'061b0002-32bd-42cd-9217-b26e65a2f8a0','026fcea2-6d4c-4703-9250-f95683ecebb8.jpg',30),(31,'3c05092d-93cb-471a-9886-3670b21d97eb','d58205a7-1dad-4d03-a4c4-1faaf4210d0a.jpg',31),(32,'5bad0d33-0c5c-4e90-933c-43cad12af234','6f9a8558-252f-474a-b014-5e223d7a95d6.jpg',32),(33,'fbd9eb96-b030-4a85-bcda-980e19c277e3','875bd20f-68a9-4bf8-b041-56553d098e30.jpg',33),(34,'cc2c8262-20c5-43f9-ba10-3e6dd2b8f4b5','ℬ___.jpg',34),(35,'eca28e4a-14e5-4335-b39d-17114670f014','૮꒰ྀི _⸝⸝⸝_ ꒱ྀིა.jpg',35),(36,'059e119c-e586-4c83-aa31-0fdcd47b58f5','★.jpg',36),(37,'77b7906e-d5a6-43b8-b35a-eadf3b0473a9','h o l l a (1).jpg',37),(38,'9ae98ec7-a585-49ea-902b-48618491e2af','??????.jpg',38),(39,'7bc796e6-d8c1-4a4b-acf6-6b1c6c413850','da68eba2-479f-4f9b-ac09-a77619a07fe5.jpg',39),(40,'f005828d-2935-4c57-af83-754254f13e74','@mrskyasiasmith.jpg',40),(41,'86a18ae1-e791-4fe1-8a9d-97a579b3b902','89e1a615-5e5c-45ba-bee6-903ff00fbc3d.jpg',41),(42,'c2fb2260-90a9-4c9b-86e6-19e5ac16df09','e6f9cdfb-6fbf-4605-b2c7-a4c1890bed34.jpg',42),(43,'81b25c44-a009-4e61-af3b-13a45359851c','b8d03064-b96e-4629-b415-da41d600e198.jpg',43),(44,'a38fdad9-4849-46e6-a2bb-903f9ba4e5cf','24069cfe-6d2f-4c3a-9b65-c44c774645f7.jpg',44),(45,'1afd83c1-8356-47a6-8332-db4f879d3547','231476e9-40b2-4b57-8859-22702aeb41a2.jpg',45),(46,'4dff532f-013f-4f0b-8f4a-7f12d4101f43','a203faaf-d05c-46fb-b868-de8f6f93a145.jpg',46),(47,'455a435b-90c0-4b19-8805-fac7a80e7204','124dcd22-9a98-4bc7-b060-a79ba924a726.jpg',47),(48,'bbb54dc6-aeba-4291-b076-35f06d2fe7d6','토끼.jpg',48),(49,'923b12ef-b5cb-474b-8c6d-bd719f30acb4','pinterest_ chaiyunki.jpg',49),(50,'efdde24a-6004-4153-9130-120b35a4e4e1','Korean Fish Cake Soup (Eomuktang).jpg',50),(51,'ce8c6bde-817a-4afa-a82d-3e85cf8d511e','e5fef489-3bc4-4c57-b493-09c989040994.jpg',51),(52,'396edaf9-5606-48a2-9ed6-e6c0afa29f2b','99e81aab-a19c-479c-a3a8-eb08e5b74386.jpg',52),(53,'564ef500-bd6b-448f-8f8c-869d48269b22','f45d0818-8bbe-4e51-8b9a-50709b27df72.jpg',53),(54,'2e9be7f5-7ee6-4517-94c2-5b3da02caa45','b39676c3-6151-45d2-bd7f-00517900827f.jpg',54),(55,'d5ffac7c-ffbe-4caa-9db4-2f877f96b3b0','_ೃ༄ M I L K Y T E A.jpg',55),(56,'3bb0d9e3-9991-4632-8266-93cb1f261a86','!♡︎.jpg',56);
/*!40000 ALTER TABLE `attach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `bno` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `hit` int NOT NULL DEFAULT '0',
  `state` char(1) NOT NULL DEFAULT 'E' COMMENT 'E-활성화\nD-비활성화',
  `rdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `content` text NOT NULL,
  `uno` int NOT NULL,
  PRIMARY KEY (`bno`),
  KEY `uno` (`uno`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`uno`) REFERENCES `user` (`uno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,'푸딩',54,'E','2024-10-23 07:38:54','',1),(2,'레몬파운드케익',25,'E','2024-10-24 03:44:43','',2),(3,'귤타르트',17,'E','2024-10-24 04:54:14','',3),(4,'스콘',42,'E','2024-10-24 05:02:29','',3),(5,'라구파스타',55,'E','2024-10-24 05:10:02','',3),(6,'크림스튜',66,'E','2024-10-24 05:10:41','',3),(7,'오이샐러드',154,'E','2024-10-24 05:16:26','',3),(8,'asdfzxvc',1,'E','2024-10-25 02:42:46','',1),(11,'마르게리타 피자',135,'E','2024-10-28 05:34:44','',4),(12,'감자튀김',4,'E','2024-10-30 06:51:38','',1),(13,'파스타',5,'E','2024-10-30 06:52:22','',1),(14,'연어스테이크',5,'E','2024-10-30 06:54:06','',1),(15,'아코디언감자',4,'E','2024-10-30 06:54:17','',1),(16,'매운팽이',2,'E','2024-10-30 06:54:25','',1),(17,'잡채',1,'E','2024-10-30 06:54:36','',1),(18,'함박',0,'E','2024-10-30 06:54:53','',1),(19,'누오',83,'E','2024-10-30 06:55:07','',1),(20,'호빵맨',10,'E','2024-10-30 06:55:25','',1),(21,'우동',2,'E','2024-10-30 06:56:41','',1),(22,'돈까스 오므라이스',2,'E','2024-10-30 06:57:29','',1),(23,'함박',1,'E','2024-10-30 07:27:51','',1),(24,'고기',1,'E','2024-10-30 07:28:00','',1),(25,'연어소바',0,'E','2024-10-30 07:28:09','',1),(26,'닭다리',1,'E','2024-10-30 07:38:00','',1),(27,'당고',2,'E','2024-10-30 07:38:10','',1),(28,'연어장',0,'E','2024-10-30 07:38:17','',1),(29,'치즈돈까스',1,'E','2024-10-30 07:38:27','',1),(30,'계란바',0,'E','2024-10-30 07:38:34','',1),(31,'라면',2,'E','2024-10-30 07:38:44','',1),(32,'족발',2,'E','2024-10-30 07:38:54','',1),(33,'소세지',1,'E','2024-10-30 08:17:38','',1),(34,'회',3,'E','2024-10-30 08:18:29','',1),(35,'포케',1,'E','2024-10-30 08:19:04','',1),(36,'스테이크덮밥',0,'E','2024-10-30 08:19:47','',1),(37,'파스타',0,'E','2024-10-30 08:19:54','',1),(38,'닭꼬치',5,'E','2024-10-30 08:20:02','',1),(39,'닭발',2,'E','2024-10-30 08:20:30','',1),(40,'김치찌개',2,'E','2024-10-30 08:20:38','',1),(41,'밥',19,'E','2024-10-30 08:20:59','',1),(42,'오뎅',5,'E','2024-10-30 08:21:06','',1),(43,'로제파스타',2,'E','2024-10-30 08:21:47','',1),(44,'고기',5,'E','2024-10-30 08:21:54','',1),(45,'엽떡',8,'E','2024-10-30 08:22:05','',1),(46,'로제떡볶이',11,'E','2024-10-30 08:22:14','',1),(47,'분식',144,'E','2024-10-30 08:22:24','',1),(48,'ㄴㅇㄹ',30,'D','2024-11-01 05:44:26','test',9),(49,'팬케이크',5,'E','2024-11-07 03:35:25','맛있는 팬케이크~',2),(50,'오뎅',2,'E','2024-11-07 03:35:37','',2),(51,'할로윈 미트볼',1,'E','2024-11-07 03:35:48','',2),(52,'스테이크',2,'E','2024-11-07 03:35:59','',2),(53,'고기',0,'E','2024-11-07 03:36:08','',2),(54,'고기집~',5,'E','2024-11-07 03:36:27','',2),(55,'비빔밥',6,'E','2024-11-07 03:36:34','',2),(56,'틈새볶음면',84,'E','2024-11-07 03:36:45','',2);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `cno` int NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `rdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` char(1) NOT NULL DEFAULT 'E' COMMENT 'E-활성화\nD-비활성화',
  `bno` int NOT NULL,
  `uno` int NOT NULL,
  PRIMARY KEY (`cno`),
  KEY `bno` (`bno`),
  KEY `uno` (`uno`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`bno`) REFERENCES `board` (`bno`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`uno`) REFERENCES `user` (`uno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'zxcv','2024-10-30 00:13:57','E',1,3),(2,'zxcv','2024-10-30 00:14:27','E',1,4),(3,'zxcv','2024-10-30 00:15:04','E',1,4),(4,'zxcv','2024-10-30 00:15:17','E',1,4),(5,'zxcv','2024-10-30 00:38:58','E',1,4),(6,'zxcv','2024-10-30 00:39:13','E',1,4),(7,'zxcv','2024-10-30 05:15:44','E',1,4),(8,'asdf','2024-10-30 06:01:23','E',1,4),(9,'asdf','2024-10-30 06:02:06','E',1,4),(10,'asdf','2024-10-30 06:02:20','E',1,4),(11,'zxcv','2024-10-31 00:13:27','E',1,1),(12,'zxcv','2024-10-31 00:14:09','E',1,1),(13,'zxcv','2024-10-31 00:14:11','E',1,1),(14,'zxcv','2024-10-31 00:14:13','E',1,1),(15,'zxcv','2024-10-31 00:15:42','E',1,1),(16,'zxcv','2024-10-31 00:15:43','E',1,1),(17,'zxcv','2024-10-31 00:15:44','E',1,1),(18,'zxcv','2024-10-31 00:15:45','E',1,1),(19,'zxcv','2024-10-31 00:16:50','E',1,1),(20,'zxcv','2024-10-31 00:16:51','E',1,1),(21,'zxvc','2024-10-31 00:16:52','E',1,1),(22,'zxv','2024-10-31 00:16:53','E',1,1),(23,'zxcv','2024-10-31 00:17:11','E',1,1),(24,'zxcv','2024-10-31 00:17:12','E',1,1),(25,'zxcv','2024-10-31 00:17:13','E',1,1),(26,'zxcv','2024-10-31 00:17:13','E',1,1),(27,'zxcv','2024-10-31 00:18:06','E',1,1),(28,'zxcv','2024-10-31 00:18:07','E',1,1),(29,'zxcv','2024-10-31 00:18:08','E',1,1),(30,'zxcv','2024-10-31 00:18:08','E',1,1),(31,'맛있어보여요!','2024-10-31 08:35:39','D',47,1),(32,'ㅋ','2024-10-31 08:36:05','D',19,1),(33,'ㅋㅋ','2024-10-31 08:36:07','D',19,1),(34,'ㅋㅋㅋ','2024-10-31 08:36:08','D',19,1),(35,'ㅋㅋㅋ','2024-10-31 08:36:09','D',19,1),(36,'귀여워','2024-11-01 00:25:26','D',19,1),(38,'1234','2024-11-01 06:35:39','D',47,1),(39,'간편해요!','2024-11-07 01:47:56','E',7,1),(40,'맛있어보여요','2024-11-07 01:51:54','D',46,1),(41,'맛있어보여요!','2024-11-07 03:31:49','E',46,3),(42,'맛있어보여요','2024-11-07 03:37:35','E',56,4),(43,'굿','2024-11-07 03:39:07','E',56,1),(44,'귀엽','2024-11-07 03:39:27','E',19,1),(45,'간편해요','2024-11-07 03:42:41','D',56,3),(46,'매워보여','2024-11-07 03:43:44','D',56,8),(47,'간단해요!','2024-11-11 04:33:13','E',56,3);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaint_board`
--

DROP TABLE IF EXISTS `complaint_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaint_board` (
  `cpno` int NOT NULL AUTO_INCREMENT,
  `bno` int NOT NULL,
  `uno` int NOT NULL,
  PRIMARY KEY (`cpno`),
  KEY `bno` (`bno`),
  KEY `uno` (`uno`),
  CONSTRAINT `complaint_board_ibfk_1` FOREIGN KEY (`bno`) REFERENCES `board` (`bno`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `complaint_board_ibfk_2` FOREIGN KEY (`uno`) REFERENCES `user` (`uno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint_board`
--

LOCK TABLES `complaint_board` WRITE;
/*!40000 ALTER TABLE `complaint_board` DISABLE KEYS */;
INSERT INTO `complaint_board` VALUES (20,48,1),(21,56,3);
/*!40000 ALTER TABLE `complaint_board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `fno` int NOT NULL AUTO_INCREMENT,
  `uno` int NOT NULL,
  `tuno` int NOT NULL,
  PRIMARY KEY (`fno`),
  KEY `uno` (`uno`),
  KEY `tuno` (`tuno`),
  CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`uno`) REFERENCES `user` (`uno`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`tuno`) REFERENCES `user` (`uno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (1,1,4),(2,1,3),(4,8,3);
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `love`
--

DROP TABLE IF EXISTS `love`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `love` (
  `lno` int NOT NULL AUTO_INCREMENT,
  `bno` int NOT NULL,
  `uno` int NOT NULL,
  PRIMARY KEY (`lno`),
  KEY `bno` (`bno`),
  KEY `uno` (`uno`),
  CONSTRAINT `love_ibfk_1` FOREIGN KEY (`bno`) REFERENCES `board` (`bno`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `love_ibfk_2` FOREIGN KEY (`uno`) REFERENCES `user` (`uno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `love`
--

LOCK TABLES `love` WRITE;
/*!40000 ALTER TABLE `love` DISABLE KEYS */;
INSERT INTO `love` VALUES (34,56,2),(36,56,3),(37,56,4),(38,54,4),(40,41,2),(41,34,2),(43,56,17),(44,55,17),(46,56,1);
/*!40000 ALTER TABLE `love` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `mno` int NOT NULL AUTO_INCREMENT,
  `uno` int NOT NULL,
  `tuno` int NOT NULL,
  `content` text NOT NULL,
  `rdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`mno`),
  KEY `uno` (`uno`),
  KEY `tuno` (`tuno`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`uno`) REFERENCES `user` (`uno`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`tuno`) REFERENCES `user` (`uno`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `uno` int NOT NULL AUTO_INCREMENT,
  `uid` varchar(12) NOT NULL,
  `upw` varchar(200) NOT NULL,
  `unick` varchar(20) NOT NULL,
  `uemail` varchar(100) NOT NULL,
  `urdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `uauthor` char(1) NOT NULL DEFAULT 'U' COMMENT 'U-유저\nA-관리자',
  `pname` varchar(50) DEFAULT NULL,
  `fname` varchar(200) DEFAULT NULL,
  `ustate` char(1) NOT NULL DEFAULT 'E' COMMENT '활성화-E\n비활성화-D',
  PRIMARY KEY (`uno`),
  UNIQUE KEY `uid` (`uid`),
  UNIQUE KEY `unick` (`unick`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','81dc9bdb52d04dc20036dbd8313ed055','관리자','gyr0204@naver.com','2024-10-23 07:33:59','A','447b99ce-9040-46b4-bfff-64979e5acb72','다운로드.jpg','E'),(2,'white','81dc9bdb52d04dc20036dbd8313ed055','흰둥이','gyr0204@naver.com','2024-10-24 01:53:07','U','841ecbbc-4985-4cf8-9caa-a271a5d4263b','다운로드 (3).jpg','E'),(3,'blue','81dc9bdb52d04dc20036dbd8313ed055','파란','gyr0204@naver.com','2024-10-24 04:53:30','U','98d36e99-0f64-4cde-96e8-301cae3824f2','귀여운 짤.jpg','E'),(4,'mell','81dc9bdb52d04dc20036dbd8313ed055','mell','gyr0204@naver.com','2024-10-28 05:25:01','U','','','E'),(7,'subadmin','81dc9bdb52d04dc20036dbd8313ed055','부관리자','gyr0204@naver.com','2024-10-31 07:40:38','A','21cffa83-7f04-449e-878a-32191e28ec48','다운로드 (2)1.jpg','E'),(8,'black','81dc9bdb52d04dc20036dbd8313ed055','까망','gyr0204@naver.com','2024-11-01 01:06:58','U','','','E'),(9,'ezen','81dc9bdb52d04dc20036dbd8313ed055','ezen','pak293100@naver.com','2024-11-01 05:43:12','U','ad72bd61-1530-474d-a113-b4908724ac04','토끼1.jpg','E'),(17,'zxcv','fd2cc6c54239c40495a0d3a93b6380eb','zxcv','gyr0204@naver.com','2024-11-01 07:20:25','U','','','E'),(19,'asdf','912ec803b2ce49e4a541068d495ab570','asdf','gyr0204@naver.com','2024-11-06 00:30:55','U','','','E');
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

-- Dump completed on 2024-11-12 10:32:08
