CREATE DATABASE  IF NOT EXISTS `mabaya` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mabaya`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mabaya
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `campaign_categories`
--

DROP TABLE IF EXISTS `campaign_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campaign_categories` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `campaign_id` int unsigned NOT NULL,
  `categories` json DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `campaign_id_UNIQUE` (`campaign_id`),
  CONSTRAINT `fk_categories` FOREIGN KEY (`campaign_id`) REFERENCES `campaigns` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campaign_categories`
--

LOCK TABLES `campaign_categories` WRITE;
/*!40000 ALTER TABLE `campaign_categories` DISABLE KEYS */;
INSERT INTO `campaign_categories` VALUES (1,1,'[\"electronics\", \"sports\", \"clothing\"]'),(2,2,'[\"electronics\", \"food\", \"cosmetics\"]');
/*!40000 ALTER TABLE `campaign_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campaign_product`
--

DROP TABLE IF EXISTS `campaign_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campaign_product` (
  `campaign_id` int unsigned NOT NULL,
  `product_id` int unsigned NOT NULL,
  PRIMARY KEY (`campaign_id`,`product_id`),
  KEY `fk_products_idx` (`product_id`),
  CONSTRAINT `fk_campaigns` FOREIGN KEY (`campaign_id`) REFERENCES `campaigns` (`id`),
  CONSTRAINT `fk_products` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campaign_product`
--

LOCK TABLES `campaign_product` WRITE;
/*!40000 ALTER TABLE `campaign_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `campaign_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `campaigns`
--

DROP TABLE IF EXISTS `campaigns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `campaigns` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `bid` double DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `start_date` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_start_date` (`start_date`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `campaigns`
--

LOCK TABLES `campaigns` WRITE;
/*!40000 ALTER TABLE `campaigns` DISABLE KEYS */;
INSERT INTO `campaigns` VALUES (1,100,'campaign 1',1677004452),(2,100,'campaign 1',1677004452);
/*!40000 ALTER TABLE `campaigns` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(10) DEFAULT NULL,
  `category` enum('clothing','electronics','sports','food','cosmetics') DEFAULT NULL,
  `price` double DEFAULT NULL,
  `serial_number` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'rifuabygwy','clothing',99.9,'a4f55228-be26-437e-ae5f-6739bd799170'),(2,'jxaigkhbsi','sports',99.9,'3a84d561-0196-4a81-be02-cdc5e2389437'),(3,'jxlbyxkzhz','electronics',99.9,'3d72eba2-b559-4327-8adc-ef048d4c1c9f'),(4,'bjeyodohnh','electronics',99.9,'9bfb5991-c45f-42b6-9781-e8e9f464d172'),(5,'nqnrbzjqoe','cosmetics',99.9,'580ddbc7-ee1e-49f0-8f5f-74d965f212c4'),(6,'mxjtvspyrs','food',99.9,'a740eefb-7865-4cbc-b2e4-b2965b271c84'),(7,'gvntssqfvm','clothing',99.9,'19966ae2-4c7e-4f9f-9532-0c41d277c4ac'),(8,'ttfbpcbvvq','electronics',99.9,'1ae8a059-2aa6-43b9-93f4-9f7ee7b945e9'),(9,'hujeirjnwc','clothing',99.9,'8912103a-c0b9-40da-869b-b564dea01f77'),(10,'bfxihmoaoj','food',99.9,'05d7aca1-f4a6-4106-a54d-5020ba488ea4'),(11,'mtzdknlwfp','food',99.9,'b6d8d589-52c6-45f5-a7e7-ae809bd3bd93'),(12,'obwzekmjxb','food',99.9,'8acd0316-6c00-4029-b311-cd019578bb27'),(13,'yrargmxzjm','cosmetics',99.9,'78347168-5c21-4aae-bd7e-2cf1e7deed02'),(14,'suspaexzta','electronics',99.9,'25c94071-f6b5-40b6-8c30-d2d1546d6daf'),(15,'pwtwjvzeta','food',99.9,'387dfd71-d44b-43a3-9991-d93b0339af03'),(16,'rzfrtherql','food',99.9,'b5dad18a-ad0f-4d78-a21b-88a48c9925ad'),(17,'beswwzxzbg','sports',99.9,'62eac673-0b36-484a-985c-ecc90a9aeb90'),(18,'mkwxfrnzsh','sports',99.9,'80d0fc8e-9253-4047-b670-c506a699849c'),(19,'duqdqtbven','sports',99.9,'22d2a19a-f956-4020-816c-98fe4552e2c2'),(20,'fvofoptmto','sports',99.9,'36846670-39db-43cf-bbfe-cd731ed91db7'),(21,'uhrqvqkxtt','clothing',99.9,'a025dd04-c0e3-4bb4-89e8-296281f0be43'),(22,'iahounjdpn','cosmetics',99.9,'c5cadca1-2260-443e-9c8c-9df230862194'),(23,'nqjhgbvunb','electronics',99.9,'6020a368-c926-4f6d-8618-bd1084b86aea'),(24,'aenudjjjeh','sports',99.9,'d5b126ef-576d-4672-ade5-d7d9cf53adb2'),(25,'cjqmfzvboy','sports',99.9,'67bae20e-46ab-4a8c-b848-572a3d3bb88e'),(26,'uomqmzxmxh','electronics',99.9,'3a621794-4223-4e77-8ee6-6cda08aacdd8'),(27,'azwadcnqhi','sports',99.9,'d7a726ac-113d-40e7-b9d2-48b454a080d8'),(28,'vyvjignvdi','food',99.9,'c4d3d86b-c084-421b-9b39-c30a3595ece6'),(29,'vnecsghyqv','electronics',99.9,'f2cd348f-a3e6-4593-9546-61c58b55ac12'),(30,'uojghuqnfe','sports',99.9,'88a1790c-fc46-4c99-a296-72d4dfe9afee'),(31,'sljnpjdqwu','cosmetics',99.9,'70f7c3fe-cb8f-4e9f-afaf-3fa7d8fc86f8'),(32,'btjmclgsek','food',99.9,'2908eeb1-c3b4-40e6-b923-d859ea267a78'),(33,'algqupjiol','electronics',99.9,'7f9b5f15-61d4-49b4-94c3-bed17e56a0dc'),(34,'tzamorqauu','food',99.9,'1f7cf90f-176e-4feb-8053-60710bc15444'),(35,'distantukf','cosmetics',99.9,'ba80efda-1a2f-4ca8-866c-21c7ad484378'),(36,'igedijbeym','cosmetics',99.9,'3854b7fb-fc69-4b6e-a952-5e82fd7020ef'),(37,'pwxlsebtfv','electronics',99.9,'020aa2a5-82e9-410c-ba7a-4759dcfb5fac'),(38,'miwtcqnjuv','clothing',99.9,'eb421715-a353-4cfd-80d6-5f950a69dee4'),(39,'ursukeymrz','cosmetics',99.9,'8e3f514c-5494-48e1-80b1-0503a14ae054'),(40,'jefkxgskmd','clothing',99.9,'2619f7d8-e207-483d-9650-2d90e86e83a9'),(41,'ueisqutpgs','clothing',99.9,'e14091c5-9364-49f0-aa96-30552ce63425'),(42,'hbqnhhbccn','electronics',99.9,'29aadff8-4c80-4d55-b4a1-6ed5273c55ec'),(43,'tlwgpmlytc','food',99.9,'fb8b2d21-118a-4610-ac56-69666b992b0e'),(44,'spdvzlbkvj','clothing',99.9,'444528ad-4d75-4171-88d3-909e3960c69e'),(45,'jiyqzayqek','clothing',99.9,'25d525ad-a2fb-45c9-bd72-11699a13a2fa'),(46,'tkjgrlosju','sports',99.9,'b5d61187-35cf-4fd6-a4bb-a956a3088ac3'),(47,'nrsiumqfqp','cosmetics',99.9,'871eceb7-4a42-415e-9dbc-733dd88846af'),(48,'rzpdrnpyls','sports',99.9,'3e0883bf-bc4a-41bd-897c-470e40c3f4c9'),(49,'fltitcfcid','sports',99.9,'bc38c55b-1483-49ee-970f-1fb7a0070afb'),(50,'enkybfnfjr','sports',99.9,'ca6a4c00-7bfd-476f-a1ea-6818272f38a9'),(51,'zgfhsbkccz','electronics',99.9,'d8eb35a5-9d62-469a-a832-dcf425fd23df'),(52,'ylkndpnudl','food',99.9,'f74fc619-9ee9-4452-93a7-491229d84d12'),(53,'raabmpernu','food',99.9,'b6ffb397-f2af-47e6-8f1e-41c114bb8ae7'),(54,'vkbdwfcxol','food',99.9,'13727412-a369-4857-aaa6-489d10f2eef9'),(55,'zahmoxevdw','sports',99.9,'b761a1bc-6023-4891-8189-641093c70019'),(56,'mxdnftmpkr','food',99.9,'0b50952c-7d00-4578-8754-e182cf6a2b57'),(57,'ofwjhxfleq','food',99.9,'a83b8fec-8688-4e2e-87fd-86e283cb5530'),(58,'wlocbbfksn','food',99.9,'8c425873-c36d-405b-952c-4e394f63605b'),(59,'tjomvkzaes','food',99.9,'f9e2bf6b-ff37-40c1-906f-8e99ee01bb68'),(60,'tyzrpqljpj','clothing',99.9,'ebcb7c92-b318-4976-9b68-0e5c772e98c7'),(61,'banopdyuwe','food',99.9,'c5bc50bc-5495-4cc4-9f19-36584fba20e2'),(62,'kmipnvptbq','clothing',99.9,'503f8173-ec11-4481-ba2b-b91b2c1ce00d'),(63,'uschoumhod','sports',99.9,'3ae24918-5e75-4a15-a4a6-98ec5fc8f149'),(64,'jngtxqxvhb','sports',99.9,'f354789d-c49d-473f-9057-e36ae114d8e5'),(65,'tkxwreirma','sports',99.9,'ed01c288-0289-4b90-8d03-d2526271736b'),(66,'gmxuahzujp','electronics',99.9,'a2af7cb9-2672-4afa-a3bd-cbac21a8c660'),(67,'qgeomfucdy','food',99.9,'dc9a746c-a05a-4729-9fea-262cf340b0ca'),(68,'hvtvkpbeum','cosmetics',99.9,'0e645dba-9a69-486b-8a73-14a08fbe87f1'),(69,'rqdpfzbind','clothing',99.9,'08ba7780-77d2-47a7-a9a9-780406eece43'),(70,'cgqshnryeb','sports',99.9,'e59ceab3-edcc-470b-8ac0-60176c550274'),(71,'calmlwbozb','food',99.9,'09c2a69c-a4a0-4da4-b4ee-515d29aba3dc'),(72,'oardtscjla','electronics',99.9,'9007583e-3c79-4452-84f4-bb190b13f5fa'),(73,'cznrssdgpw','cosmetics',99.9,'74f1d1d7-e335-4a13-93ed-4172ef066734'),(74,'dqkhaiebpa','electronics',99.9,'71e9afaf-652a-47c2-8ea9-553f764e64f5'),(75,'fidikpafum','food',99.9,'7fc93cea-810f-43b0-bc09-7744d3efb387'),(76,'bgjxqpaehg','cosmetics',99.9,'a64d7849-3f30-4779-9f6a-86608d0554d1'),(77,'tjdkqkmgcx','electronics',99.9,'2ed4c7d1-153d-4131-9d9e-0d9a55d2a229'),(78,'inolbvryhe','food',99.9,'47da3ab7-4d70-4afe-8188-f1c776632736'),(79,'vmsxwvbulj','food',99.9,'ca15045a-6075-4e84-bc88-503b82419ef3'),(80,'aynfhjtbff','sports',99.9,'81714378-b836-4f74-9aff-ce3b9c0a7748'),(81,'mrhamkoutl','electronics',99.9,'20801951-243c-4bf7-8026-4c10d88212a4'),(82,'ujkaqfjkgq','electronics',99.9,'c01b4202-e443-42e3-910f-7cf7df2e527f'),(83,'xahqvwozsz','clothing',99.9,'bf4ae9bf-525e-4465-8f42-56d973f7de3e'),(84,'nzbiknfeam','electronics',99.9,'9a18df38-0e50-4f63-a4a4-c8b4441a2bd4'),(85,'swexpsgknk','electronics',99.9,'855b1fb9-c9f7-4e4a-8ffd-cb08e3ff8a7f'),(86,'wdhgjwynip','clothing',99.9,'ce4699e8-5cfe-42f4-adc6-b61fed9f6783'),(87,'xrprvqwjlh','sports',99.9,'b7121256-b5e9-442d-b5a3-92594dbfe974'),(88,'jvxprkpfcz','electronics',99.9,'c38875aa-1e69-4668-82d3-1c55ebb8662e'),(89,'nwmeznfwaa','food',99.9,'0f690dc0-de70-48f5-b8ec-6e11c3ba3fbf'),(90,'ztvoreprar','sports',99.9,'c1353079-556d-418d-b3ec-a3036715f5af'),(91,'rxltgxtpdz','sports',99.9,'bedc47a1-1f41-4449-84eb-c63474a37fce'),(92,'timwrijhxx','cosmetics',99.9,'d729cf8d-cd07-4741-aff7-0d444af73931'),(93,'oogqjjhboi','clothing',99.9,'cebd2f7b-fa48-4e9a-a3ef-7a05416d7b4a'),(94,'tbmexlaljs','food',99.9,'05b787a3-cd17-47cd-a2f2-0475ec0040ea'),(95,'tinlrxmkte','clothing',99.9,'eb7f1b48-f81a-42ce-908c-23afd7107df4'),(96,'pnnuzonrcp','cosmetics',99.9,'46bea2b4-20ae-4e77-beef-8f7d09761f48'),(97,'vnxuoedmgw','cosmetics',99.9,'c86e56d7-5987-4554-8c28-4065e520496a'),(98,'vppjmkhjxe','food',99.9,'b9a07a39-5e87-4843-acf2-7a597a07e222'),(99,'dqmeqjfrff','cosmetics',99.9,'caf45491-9077-4216-85a8-33383f3a5b6c'),(100,'hlvumefily','food',99.9,'065083ce-9871-4f72-9914-d14b77abb57f');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-21 22:04:16
