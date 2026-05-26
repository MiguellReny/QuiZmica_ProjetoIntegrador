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
  `alternativa` varchar(60) NOT NULL,
  `alternativaImagem` varchar(255) DEFAULT NULL,
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
  `tipo` enum('textual','imagem') NOT NULL DEFAULT 'textual',
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

INSERT INTO perguntas (enunciado, perguntaImagem, dificuldade, dica, tipo) VALUES
('O que é uma bureta?', NULL, 'FACIL', 'É usada para medir volumes variáveis de líquidos.', 'textual'),
('O que é um béquer?', NULL, 'FACIL', 'É um recipiente cilíndrico usado para conter líquidos.', 'textual'),
('Para que serve um funil de separação?', NULL, 'FACIL', 'É usado para separar líquidos imiscíveis.', 'textual'),
('O que é um erlenmeyer?', NULL, 'FACIL', 'É um frasco cônico usado em titulações.', 'textual'),
('Para que serve uma pipeta?', NULL, 'FACIL', 'É usada para transferir volumes precisos de líquidos.', 'textual'),
('O que é um bastão de vidro?', NULL, 'FACIL', 'É usado para agitar soluções.', 'textual'),
('Para que serve um vidro de relógio?', NULL, 'FACIL', 'É usado para cobrir béqueres ou pesar substâncias.', 'textual'),
('O que é uma proveta?', NULL, 'FACIL', 'É usada para medir volumes aproximados de líquidos.', 'textual'),
('Para que serve um cadinho?', NULL, 'FACIL', 'É usado para aquecer substâncias em altas temperaturas.','textual'),
('O que é um funil de vidro simples?', NULL, 'FACIL', 'É usado para transferir líquidos ou filtrar.', 'textual'),
('Para que serve o tripé de laboratório?', NULL, 'FACIL', 'É usado para apoiar vidrarias durante o aquecimento.','textual'),
('O que é uma tela de amianto?', NULL, 'FACIL', 'É usada para distribuir o calor uniformemente.', 'textual'),
('Para que serve o suporte universal?', NULL, 'FACIL', 'É usado para fixar e suportar equipamentos de laboratório.','textual'),
('O que é um balão volumétrico?', NULL, 'FACIL', 'É usado para preparar soluções de concentração exata.', 'textual'),
('O que é uma centrífuga?', NULL, 'MEDIO', 'É usada para separar substâncias por diferença de densidade.', 'textual'),
('Para que serve um condensador?', NULL, 'MEDIO', 'É usado para resfriar vapores e convertê-los em líquido.', 'textual'),
('O que é um dessecador?', NULL, 'MEDIO', 'É usado para armazenar substâncias sem umidade.','textual'),
('O que é uma mufla?', NULL, 'MEDIO', 'É um forno usado para calcinar substâncias.','textual'),
('O que é um espectrofotômetro?', NULL, 'DIFICIL', 'É usado para medir a absorção de luz por uma solução.', 'textual'),
('O que é uma coluna cromatográfica?', NULL, 'DIFICIL', 'É usada para separar componentes de uma mistura.', 'textual');

INSERT INTO alternativa (idAlternativa, alternativaCorreta, alternativa, perguntas_idPerguntas) VALUES
(1, 1, 'Instrumento para medir volumes variáveis', 1),
(2, 0, 'Instrumento para pesar substâncias', 1),
(3, 0, 'Instrumento para aquecer líquidos', 1),
(4, 0, 'Instrumento para filtrar soluções', 1),
(5, 1, 'Recipiente cilíndrico para conter líquidos', 2),
(6, 0, 'Instrumento para medir temperatura', 2),
(7, 0, 'Instrumento para filtrar gases', 2),
(8, 0, 'Recipiente para pesar sólidos', 2),
(9, 1, 'Separar líquidos imiscíveis', 3),
(10, 0, 'Medir volumes de gases', 3),
(11, 0, 'Aquecer substâncias sólidas', 3),
(12, 0, 'Transferir sólidos entre recipientes', 3),
(13, 1, 'Frasco cônico usado em titulações', 4),
(14, 0, 'Frasco para armazenar gases', 4),
(15, 0, 'Recipiente para calcinar substâncias', 4),
(16, 0, 'Instrumento para medir pressão', 4),
(17, 1, 'Transferir volumes precisos de líquidos', 5),
(18, 0, 'Medir a temperatura de líquidos', 5),
(19, 0, 'Agitar soluções químicas', 5),
(20, 0, 'Filtrar substâncias sólidas', 5),
(21, 1, 'Agitar soluções', 6),
(22, 0, 'Medir volumes de líquidos', 6),
(23, 0, 'Separar misturas heterogêneas', 6),
(24, 0, 'Aquecer substâncias', 6),
(25, 1, 'Cobrir béqueres ou pesar substâncias', 7),
(26, 0, 'Medir volumes de gases', 7),
(27, 0, 'Filtrar líquidos', 7),
(28, 0, 'Armazenar substâncias voláteis', 7),
(29, 1, 'Medir volumes aproximados de líquidos', 8),
(30, 0, 'Preparar soluções exatas', 8),
(31, 0, 'Separar substâncias por densidade', 8),
(32, 0, 'Aquecer substâncias em altas temperaturas', 8),
(33, 1, 'Aquecer substâncias em altas temperaturas', 9),
(34, 0, 'Medir volumes de líquidos', 9),
(35, 0, 'Filtrar soluções', 9),
(36, 0, 'Armazenar substâncias sem umidade', 9),
(37, 1, 'Transferir líquidos ou filtrar', 10),
(38, 0, 'Medir temperatura de líquidos', 10),
(39, 0, 'Agitar soluções', 10),
(40, 0, 'Pesar substâncias sólidas', 10),
(41, 1, 'Apoiar vidrarias durante o aquecimento', 11),
(42, 0, 'Medir volumes de líquidos', 11),
(43, 0, 'Filtrar substâncias', 11),
(44, 0, 'Armazenar substâncias químicas', 11),
(45, 1, 'Distribuir o calor uniformemente', 12),
(46, 0, 'Medir a temperatura de substâncias', 12),
(47, 0, 'Filtrar gases', 12),
(48, 0, 'Separar líquidos imiscíveis', 12),
(49, 1, 'Fixar e suportar equipamentos de laboratório', 13),
(50, 0, 'Medir volumes de líquidos', 13),
(51, 0, 'Aquecer substâncias', 13),
(52, 0, 'Filtrar soluções', 13),
(53, 1, 'Preparar soluções de concentração exata', 14),
(54, 0, 'Medir volumes aproximados', 14),
(55, 0, 'Separar substâncias por densidade', 14),
(56, 0, 'Aquecer substâncias em altas temperaturas', 14),
(57, 1, 'Separar substâncias por diferença de densidade', 15),
(58, 0, 'Medir volumes de líquidos', 15),
(59, 0, 'Filtrar substâncias sólidas', 15),
(60, 0, 'Aquecer substâncias', 15),
(61, 1, 'Resfriar vapores e convertê-los em líquido', 16),
(62, 0, 'Medir a temperatura de gases', 16),
(63, 0, 'Separar líquidos imiscíveis', 16),
(64, 0, 'Filtrar substâncias sólidas', 16),
(65, 1, 'Armazenar substâncias sem umidade', 17),
(66, 0, 'Medir volumes de líquidos', 17),
(67, 0, 'Aquecer substâncias', 17),
(68, 0, 'Separar misturas', 17),
(69, 1, 'Forno usado para calcinar substâncias', 18),
(70, 0, 'Instrumento para medir pressão', 18),
(71, 0, 'Recipiente para armazenar gases', 18),
(72, 0, 'Instrumento para filtrar líquidos', 18),
(73, 1, 'Medir a absorção de luz por uma solução', 19),
(74, 0, 'Medir volumes de líquidos', 19),
(75, 0, 'Separar substâncias por densidade', 19),
(76, 0, 'Aquecer substâncias em altas temperaturas', 19),
(77, 1, 'Separar componentes de uma mistura', 20),
(78, 0, 'Medir a temperatura de soluções', 20),
(79, 0, 'Filtrar substâncias sólidas', 20),
(80, 0, 'Armazenar substâncias sem umidade', 20);

INSERT INTO usuario (nome, login, senha, tipo) VALUES (
  'Maria do Socorro',
  'mariadosocorro@cps.sp.gov.br',
  'b68939004065e85da9ce2976cf4839c2a75dd7a8d706351a8becc301b743fd35',
  'professor'
);

CREATE USER IF NOT EXISTS 'QuiZmica'@'%' IDENTIFIED BY 'etecquimica10';
GRANT ALL PRIVILEGES ON quizmica.* TO 'QuiZmica'@'%';
FLUSH PRIVILEGES;

SHOW TABLES;