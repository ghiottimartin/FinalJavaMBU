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
-- Table structure for table `ataque`
--

DROP TABLE IF EXISTS `ataque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ataque` (
  `id_ataque` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_ataque` varchar(45) NOT NULL,
  `energia_requerida` int(11) NOT NULL,
  PRIMARY KEY (`id_ataque`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `combate`
--

DROP TABLE IF EXISTS `combate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `combate` (
  `id_combate` int(11) NOT NULL AUTO_INCREMENT,
  `id_enemigo` int(11) NOT NULL,
  PRIMARY KEY (`id_combate`),
  KEY `personaje_combate_idx` (`id_enemigo`),
  CONSTRAINT `personaje_combate` FOREIGN KEY (`id_enemigo`) REFERENCES `personaje` (`id_personaje`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nivel`
--

DROP TABLE IF EXISTS `nivel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nivel` (
  `id_nivel` int(11) NOT NULL AUTO_INCREMENT,
  `nro_nivel` int(11) NOT NULL,
  `experiencia_minima` int(11) NOT NULL,
  `puntos_totales` int(11) NOT NULL,
  PRIMARY KEY (`id_nivel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `personaje`
--

DROP TABLE IF EXISTS `personaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personaje` (
  `id_personaje` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `vida` int(11) DEFAULT NULL,
  `energia` int(11) DEFAULT NULL,
  `defensa` int(11) DEFAULT NULL,
  `evasion` int(11) DEFAULT NULL,
  `experiencia` int(11) DEFAULT NULL,
  `id_nivel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_personaje`),
  KEY `personaje_nivel_idx` (`id_nivel`),
  CONSTRAINT `personaje_nivel` FOREIGN KEY (`id_nivel`) REFERENCES `nivel` (`id_nivel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `personaje_ataque`
--

DROP TABLE IF EXISTS `personaje_ataque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personaje_ataque` (
  `id_personaje_ataque` int(11) NOT NULL AUTO_INCREMENT,
  `id_personaje` int(11) NOT NULL,
  `id_ataque` int(11) NOT NULL,
  PRIMARY KEY (`id_personaje_ataque`),
  KEY `personaje_personajeAtaque_idx` (`id_personaje`),
  KEY `ataque_personajeAtaque_idx` (`id_ataque`),
  CONSTRAINT `ataque_personajeAtaque` FOREIGN KEY (`id_ataque`) REFERENCES `ataque` (`id_ataque`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `personaje_personajeAtaque` FOREIGN KEY (`id_personaje`) REFERENCES `personaje` (`id_personaje`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `torneo`
--

DROP TABLE IF EXISTS `torneo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `torneo` (
  `id_torneo` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario_personaje` int(11) NOT NULL,
  PRIMARY KEY (`id_torneo`),
  KEY `torneo_usuarioPersonaje_idx` (`id_usuario_personaje`),
  CONSTRAINT `torneo_usuarioPersonaje` FOREIGN KEY (`id_usuario_personaje`) REFERENCES `usuario_personaje` (`id_usuario_personaje`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `torneo_combate`
--

DROP TABLE IF EXISTS `torneo_combate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `torneo_combate` (
  `id_torneo_combate` int(11) NOT NULL AUTO_INCREMENT,
  `id_torneo` int(11) NOT NULL,
  `id_combate` int(11) NOT NULL,
  `combate_activo` tinyint(4) DEFAULT NULL,
  `id_siguiente_combate` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_torneo_combate`),
  KEY `fk_torneo_torneoCombate_idx` (`id_torneo`),
  KEY `fk_combate_torneoCombate_idx` (`id_combate`),
  KEY `fk_torneoCombate_torneoCombate_idx` (`id_siguiente_combate`),
  CONSTRAINT `fk_combate_torneoCombate` FOREIGN KEY (`id_combate`) REFERENCES `combate` (`id_combate`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_torneoCombate_torneoCombate` FOREIGN KEY (`id_siguiente_combate`) REFERENCES `torneo_combate` (`id_torneo_combate`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_torneo_torneoCombate` FOREIGN KEY (`id_torneo`) REFERENCES `torneo` (`id_torneo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `fechaCreacion` datetime DEFAULT NULL,
  `rol` varchar(45) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario_personaje`
--

DROP TABLE IF EXISTS `usuario_personaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_personaje` (
  `id_usuario_personaje` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `id_personaje` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario_personaje`),
  KEY `usuario_usuarioPersonaje_idx` (`id_usuario`),
  KEY `personaje_usuarioPersonaje_idx` (`id_personaje`),
  CONSTRAINT `personaje_usuarioPersonaje` FOREIGN KEY (`id_personaje`) REFERENCES `personaje` (`id_personaje`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `usuario_usuarioPersonaje` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-30 20:58:45
