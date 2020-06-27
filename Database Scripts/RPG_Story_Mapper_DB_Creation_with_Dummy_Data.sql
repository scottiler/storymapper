CREATE DATABASE  IF NOT EXISTS `rpg_story_mapper_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `rpg_story_mapper_db`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: rpg_story_mapper_db
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admins` (
  `admin_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` int(11) NOT NULL,
  `is_active` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`admin_id`,`player_id`),
  UNIQUE KEY `admin_id_UNIQUE` (`admin_id`),
  UNIQUE KEY `player_id_UNIQUE` (`player_id`),
  KEY `fk_admin_player_id_idx` (`player_id`),
  CONSTRAINT `fk_players_playerId` FOREIGN KEY (`player_id`) REFERENCES `players` (`player_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` VALUES (1,2,1);
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `characterplayerqyestplayerlog`
--

DROP TABLE IF EXISTS `characterplayerqyestplayerlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `characterplayerqyestplayerlog` (
  `quest_id` int(11) NOT NULL,
  `character_id` int(11) NOT NULL,
  `quest_status` int(11) NOT NULL,
  PRIMARY KEY (`quest_id`),
  KEY `fk_characterPlayerQyestPlayerLog_quests1_idx` (`quest_id`),
  KEY `fk_characterPlayerQyestPlayerLog_charactersPlayer1_idx` (`character_id`),
  CONSTRAINT `fk_characterPlayerQyestPlayerLog_charactersPlayer1` FOREIGN KEY (`character_id`) REFERENCES `charactersplayer` (`character_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_characterPlayerQyestPlayerLog_quests1` FOREIGN KEY (`quest_id`) REFERENCES `quests` (`quest_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characterplayerqyestplayerlog`
--

LOCK TABLES `characterplayerqyestplayerlog` WRITE;
/*!40000 ALTER TABLE `characterplayerqyestplayerlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `characterplayerqyestplayerlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `characters_npc`
--

DROP TABLE IF EXISTS `characters_npc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `characters_npc` (
  `character_npc_id` int(11) NOT NULL AUTO_INCREMENT,
  `character_npc_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `character_npc_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `character_npc_type` int(11) NOT NULL,
  `character_npc_faction` int(11) NOT NULL,
  `character_npc_pos_x` decimal(10,2) NOT NULL,
  `character_npc_pos_y` decimal(10,2) NOT NULL,
  `character_npc_pos_z` decimal(10,2) NOT NULL,
  PRIMARY KEY (`character_npc_id`),
  UNIQUE KEY `demo.characters_npc_id_UNIQUE` (`character_npc_id`),
  UNIQUE KEY `demo.characters_npc_name_UNIQUE` (`character_npc_name`),
  UNIQUE KEY `demo.characters_npc_description_UNIQUE` (`character_npc_description`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characters_npc`
--

LOCK TABLES `characters_npc` WRITE;
/*!40000 ALTER TABLE `characters_npc` DISABLE KEYS */;
/*!40000 ALTER TABLE `characters_npc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `charactersplayer`
--

DROP TABLE IF EXISTS `charactersplayer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `charactersplayer` (
  `character_id` int(11) NOT NULL AUTO_INCREMENT,
  `character_player_id` int(11) NOT NULL,
  `character_name` varchar(45) NOT NULL,
  `character_type` int(11) NOT NULL,
  `character_faction` int(11) NOT NULL,
  `character_posX` decimal(10,2) NOT NULL,
  `character_posY` decimal(10,2) NOT NULL,
  `character_posZ` decimal(10,2) NOT NULL,
  PRIMARY KEY (`character_id`),
  UNIQUE KEY `character_id_UNIQUE` (`character_id`),
  UNIQUE KEY `character_player_id_UNIQUE` (`character_player_id`),
  UNIQUE KEY `character_name_UNIQUE` (`character_name`),
  KEY `fk_charactersPlayer_players1_idx` (`character_player_id`),
  CONSTRAINT `fk_charactersPlayer_players1` FOREIGN KEY (`character_player_id`) REFERENCES `players` (`player_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charactersplayer`
--

LOCK TABLES `charactersplayer` WRITE;
/*!40000 ALTER TABLE `charactersplayer` DISABLE KEYS */;
/*!40000 ALTER TABLE `charactersplayer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conflicts`
--

DROP TABLE IF EXISTS `conflicts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conflicts` (
  `confkict_id` int(11) NOT NULL AUTO_INCREMENT,
  `conflict_name` varchar(45) NOT NULL,
  `conflict_description` varchar(45) NOT NULL,
  `conflict_template` int(11) NOT NULL,
  PRIMARY KEY (`confkict_id`),
  CONSTRAINT `fk_conflicts_quests1` FOREIGN KEY (`confkict_id`) REFERENCES `quests` (`quest_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conflicts`
--

LOCK TABLES `conflicts` WRITE;
/*!40000 ALTER TABLE `conflicts` DISABLE KEYS */;
/*!40000 ALTER TABLE `conflicts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gamestory`
--

DROP TABLE IF EXISTS `gamestory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gamestory` (
  `game_story_name` varchar(45) NOT NULL,
  `game_story_summary` varchar(255) NOT NULL,
  PRIMARY KEY (`game_story_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gamestory`
--

LOCK TABLES `gamestory` WRITE;
/*!40000 ALTER TABLE `gamestory` DISABLE KEYS */;
/*!40000 ALTER TABLE `gamestory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `inventory_id` int(11) NOT NULL,
  `character_id` int(11) NOT NULL,
  PRIMARY KEY (`inventory_id`,`character_id`),
  KEY `fk_inventory_characters_npc1_idx` (`character_id`),
  CONSTRAINT `fk_inventory_charactersPlayer1` FOREIGN KEY (`character_id`) REFERENCES `charactersplayer` (`character_player_id`),
  CONSTRAINT `fk_inventory_characters_npc1` FOREIGN KEY (`character_id`) REFERENCES `characters_npc` (`character_npc_id`),
  CONSTRAINT `fk_items_itenid` FOREIGN KEY (`inventory_id`) REFERENCES `items` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Item Name',
  `item_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Item Description',
  `item_type` int(11) NOT NULL DEFAULT '0',
  `is_quest_item` tinyint(4) NOT NULL DEFAULT '0',
  `is_implicit_item` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `item_id_UNIQUE` (`item_id`),
  UNIQUE KEY `demo.item_name_UNIQUE` (`item_name`),
  UNIQUE KEY `demo.item_description_UNIQUE` (`item_description`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `players` (
  `player_id` int(11) NOT NULL AUTO_INCREMENT,
  `player_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `player_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `player_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `player_country_code` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'USA',
  PRIMARY KEY (`player_id`),
  UNIQUE KEY `demo.players_id_UNIQUE` (`player_id`),
  UNIQUE KEY `demo.players_name_UNIQUE` (`player_name`),
  UNIQUE KEY `demo.player_email_UNIQUE` (`player_email`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` VALUES (1,'player','test1234','player@llol.com','USA'),(2,'adim','test1234','admin@lol.com','USA');
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questitems`
--

DROP TABLE IF EXISTS `questitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questitems` (
  `quest_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `item_quantity` int(11) NOT NULL,
  PRIMARY KEY (`quest_id`,`item_id`),
  KEY `fk_questItems_items1_idx` (`item_id`),
  CONSTRAINT `fk_questItems_items1` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_questItems_quests1` FOREIGN KEY (`quest_id`) REFERENCES `quests` (`quest_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questitems`
--

LOCK TABLES `questitems` WRITE;
/*!40000 ALTER TABLE `questitems` DISABLE KEYS */;
/*!40000 ALTER TABLE `questitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quests`
--

DROP TABLE IF EXISTS `quests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quests` (
  `quest_id` int(11) NOT NULL,
  `pre_req_quest-id` int(11) NOT NULL,
  `confict_id` int(11) NOT NULL,
  `min_chartacter_level` int(11) NOT NULL,
  `quest_name` varchar(45) NOT NULL,
  `quest_description` varchar(45) NOT NULL,
  PRIMARY KEY (`quest_id`,`pre_req_quest-id`),
  KEY `fk_quests_questid_idx` (`pre_req_quest-id`),
  CONSTRAINT `fk_quests_questid` FOREIGN KEY (`pre_req_quest-id`) REFERENCES `quests` (`quest_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quests`
--

LOCK TABLES `quests` WRITE;
/*!40000 ALTER TABLE `quests` DISABLE KEYS */;
/*!40000 ALTER TABLE `quests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'rpg_story_mapper_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-27  0:12:42
