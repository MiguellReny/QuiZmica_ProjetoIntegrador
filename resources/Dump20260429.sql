CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.45

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
-- Table structure for table `alternativa`
--

DROP TABLE IF EXISTS `alternativa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alternativa` (
  `idAlternativa` int NOT NULL,
  `AlternativaCorreta` tinyint(1) NOT NULL,
  `Alternativa` varchar(45) NOT NULL,
  `Perguntas_idPerguntas` int NOT NULL,
  PRIMARY KEY (`idAlternativa`,`Perguntas_idPerguntas`),
  KEY `fk_Alternativa_Perguntas1_idx` (`Perguntas_idPerguntas`),
  CONSTRAINT `fk_Alternativa_Perguntas1` FOREIGN KEY (`Perguntas_idPerguntas`) REFERENCES `perguntas` (`idPerguntas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alternativa`
--

LOCK TABLES `alternativa` WRITE;
/*!40000 ALTER TABLE `alternativa` DISABLE KEYS */;
/*!40000 ALTER TABLE `alternativa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partida`
--

DROP TABLE IF EXISTS `partida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partida` (
  `idPartida` int NOT NULL,
  `Pontuacao` int NOT NULL,
  `Data` date NOT NULL,
  `Tempo` time NOT NULL,
  `Usuário_idUsuário` int NOT NULL,
  PRIMARY KEY (`idPartida`,`Usuário_idUsuário`),
  KEY `fk_Partida_Usuário1_idx` (`Usuário_idUsuário`),
  CONSTRAINT `fk_Partida_Usuário1` FOREIGN KEY (`Usuário_idUsuário`) REFERENCES `usuário` (`idUsuário`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partida`
--

LOCK TABLES `partida` WRITE;
/*!40000 ALTER TABLE `partida` DISABLE KEYS */;
/*!40000 ALTER TABLE `partida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perguntas`
--

DROP TABLE IF EXISTS `perguntas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perguntas` (
  `idPerguntas` int NOT NULL,
  `Enunciado` text NOT NULL,
  `PerguntaImagem` varchar(255) NOT NULL,
  `Dificuldade` enum('Fácil','Média','Difícil') NOT NULL,
  `Dica` varchar(255) NOT NULL,
  PRIMARY KEY (`idPerguntas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perguntas`
--

LOCK TABLES `perguntas` WRITE;
/*!40000 ALTER TABLE `perguntas` DISABLE KEYS */;
/*!40000 ALTER TABLE `perguntas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuário`
--

DROP TABLE IF EXISTS `usuário`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuário` (
  `idUsuário` int NOT NULL,
  `Nome` varchar(40) NOT NULL,
  `Login` varchar(20) NOT NULL,
  `Senha` varchar(255) NOT NULL,
  `Tipo` enum('aluno','professor') NOT NULL,
  `Turma` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idUsuário`),
  UNIQUE KEY `login_UNIQUE` (`Login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuário`
--

LOCK TABLES `usuário` WRITE;
/*!40000 ALTER TABLE `usuário` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuário` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `é jogado...`
--

DROP TABLE IF EXISTS `é jogado...`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `é jogado...` (
  `idPerguntaJogo` int NOT NULL,
  `IdPartidaJogo` int NOT NULL,
  `IdAlternativas_Alternativas` int NOT NULL,
  PRIMARY KEY (`idPerguntaJogo`,`IdPartidaJogo`,`IdAlternativas_Alternativas`),
  KEY `fk_É jogado..._Partida1_idx` (`IdPartidaJogo`),
  KEY `fk_É jogado..._Alternativa1_idx` (`IdAlternativas_Alternativas`),
  CONSTRAINT `fk_É jogado..._Alternativa1` FOREIGN KEY (`IdAlternativas_Alternativas`) REFERENCES `alternativa` (`idAlternativa`),
  CONSTRAINT `fk_É jogado..._Partida1` FOREIGN KEY (`IdPartidaJogo`) REFERENCES `partida` (`idPartida`),
  CONSTRAINT `fk_É jogado..._Perguntas1` FOREIGN KEY (`idPerguntaJogo`) REFERENCES `perguntas` (`idPerguntas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `é jogado...`
--

LOCK TABLES `é jogado...` WRITE;
/*!40000 ALTER TABLE `é jogado...` DISABLE KEYS */;
/*!40000 ALTER TABLE `é jogado...` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-29 20:01:50
