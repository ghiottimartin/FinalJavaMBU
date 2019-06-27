-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: db_tp_java
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `ataque`
--

LOCK TABLES `ataque` WRITE;
/*!40000 ALTER TABLE `ataque` DISABLE KEYS */;
/*!40000 ALTER TABLE `ataque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `combate`
--

LOCK TABLES `combate` WRITE;
/*!40000 ALTER TABLE `combate` DISABLE KEYS */;
INSERT INTO `combate` VALUES (1,1,100);
/*!40000 ALTER TABLE `combate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `nivel`
--

LOCK TABLES `nivel` WRITE;
/*!40000 ALTER TABLE `nivel` DISABLE KEYS */;
INSERT INTO `nivel` VALUES (1,1,0,100);
/*!40000 ALTER TABLE `nivel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `personaje`
--

LOCK TABLES `personaje` WRITE;
/*!40000 ALTER TABLE `personaje` DISABLE KEYS */;
INSERT INTO `personaje` VALUES (1,'Villano Generico',25,25,25,25,0,1,NULL),(2,'Heroe Badass',30,30,30,10,0,1,NULL);
/*!40000 ALTER TABLE `personaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `personaje_ataque`
--

LOCK TABLES `personaje_ataque` WRITE;
/*!40000 ALTER TABLE `personaje_ataque` DISABLE KEYS */;
/*!40000 ALTER TABLE `personaje_ataque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipo_personaje`
--

LOCK TABLES `tipo_personaje` WRITE;
/*!40000 ALTER TABLE `tipo_personaje` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_personaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `torneo`
--

LOCK TABLES `torneo` WRITE;
/*!40000 ALTER TABLE `torneo` DISABLE KEYS */;
/*!40000 ALTER TABLE `torneo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `torneo_combate`
--

LOCK TABLES `torneo_combate` WRITE;
/*!40000 ALTER TABLE `torneo_combate` DISABLE KEYS */;
/*!40000 ALTER TABLE `torneo_combate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'user','123','John','Rambo','rambo@mail.com','2019-06-27 19:24:25','usuario');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuario_personaje`
--

LOCK TABLES `usuario_personaje` WRITE;
/*!40000 ALTER TABLE `usuario_personaje` DISABLE KEYS */;
INSERT INTO `usuario_personaje` VALUES (1,1,2);
/*!40000 ALTER TABLE `usuario_personaje` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-27 19:43:10
