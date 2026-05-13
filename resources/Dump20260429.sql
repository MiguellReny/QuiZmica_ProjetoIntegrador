CREATE DATABASE IF NOT EXISTS `quizmica` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `quizmica`;
-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quizmica
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
  `alternativaCorreta` tinyint(1) NOT NULL,
  `alternativa` varchar(45) NOT NULL,
  `perguntas_idPerguntas` int NOT NULL,
  PRIMARY KEY (`idAlternativa`,`perguntas_idPerguntas`),
  KEY `fk_Alternativa_Perguntas1_idx` (`perguntas_idPerguntas`),
  CONSTRAINT `fk_Alternativa_Perguntas1` FOREIGN KEY (`perguntas_idPerguntas`) REFERENCES `perguntas` (`idPerguntas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `alternativa` WRITE;
/*!40000 ALTER TABLE `alternativa` DISABLE KEYS */;
/*!40000 ALTER TABLE `alternativa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perguntas`
--

DROP TABLE IF EXISTS `perguntas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perguntas` (
  `idPerguntas` int NOT NULL AUTO_INCREMENT,
  `enunciado` text,
  `perguntaImagem` varchar(255) DEFAULT NULL,
  `dificuldade` enum('FACIL','MEDIO','DIFICIL') NOT NULL,
  `dica` varchar(255) NOT NULL,
  PRIMARY KEY (`idPerguntas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `perguntas` WRITE;
/*!40000 ALTER TABLE `perguntas` DISABLE KEYS */;
/*!40000 ALTER TABLE `perguntas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `login` varchar(100) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `tipo` enum('aluno','professor') NOT NULL,
  `turma` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partida`
--

DROP TABLE IF EXISTS `partida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partida` (
  `idPartida` int NOT NULL AUTO_INCREMENT,
  `pontuacao` int NOT NULL,
  `data` date NOT NULL,
  `nivel` enum('FACIL','MEDIO','DIFICIL') NOT NULL,
  `dicasUsadas` int NOT NULL DEFAULT 0,
  `usuario_idUsuario` int NOT NULL,
  PRIMARY KEY (`idPartida`),
  KEY `fk_Partida_usuario1_idx` (`usuario_idUsuario`),
  CONSTRAINT `fk_Partida_usuario1` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `partida` WRITE;
/*!40000 ALTER TABLE `partida` DISABLE KEYS */;
/*!40000 ALTER TABLE `partida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pergunta_partida`
--

DROP TABLE IF EXISTS `pergunta_partida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pergunta_partida` (
  `idPerguntaPartida` int NOT NULL AUTO_INCREMENT,
  `idPartida` int NOT NULL,
  `idPerguntas` int NOT NULL,
  `idAlternativa` int NOT NULL,
  PRIMARY KEY (`idPerguntaPartida`),
  KEY `fk_pergunta_partida_Partida1_idx` (`idPartida`),
  KEY `fk_pergunta_partida_Perguntas1_idx` (`idPerguntas`),
  KEY `fk_pergunta_partida_Alternativa1_idx` (`idAlternativa`),
  CONSTRAINT `fk_pergunta_partida_Partida1` FOREIGN KEY (`idPartida`) REFERENCES `partida` (`idPartida`),
  CONSTRAINT `fk_pergunta_partida_Perguntas1` FOREIGN KEY (`idPerguntas`) REFERENCES `perguntas` (`idPerguntas`),
  CONSTRAINT `fk_pergunta_partida_Alternativa1` FOREIGN KEY (`idAlternativa`) REFERENCES `alternativa` (`idAlternativa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `pergunta_partida` WRITE;
/*!40000 ALTER TABLE `pergunta_partida` DISABLE KEYS */;
/*!40000 ALTER TABLE `pergunta_partida` ENABLE KEYS */;
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
USE quizmica;
SHOW TABLES;