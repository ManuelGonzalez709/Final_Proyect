-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.28-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para karmasell
CREATE DATABASE IF NOT EXISTS `karmasell` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `karmasell`;

-- Volcando estructura para tabla karmasell.anuncio
CREATE TABLE IF NOT EXISTS `anuncio` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `id_categoria` int(11) DEFAULT NULL,
  `titulo` varchar(50) DEFAULT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  `ubicacion` varchar(50) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  `divisa` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_anuncio_categoria` (`id_categoria`),
  KEY `FK_anuncio_usuario` (`id_usuario`),
  CONSTRAINT `FK_anuncio_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_anuncio_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla karmasell.categoria
CREATE TABLE IF NOT EXISTS `categoria` (
  `id` int(11) NOT NULL,
  `Categoria` varchar(50) DEFAULT NULL,
  `Descripcion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla karmasell.direccion_envio
CREATE TABLE IF NOT EXISTS `direccion_envio` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `cp` int(11) DEFAULT NULL,
  `poblacion` varchar(50) DEFAULT NULL,
  `provincia` varchar(50) DEFAULT NULL,
  `pais` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_direccion_envio_usuario` (`id_usuario`),
  CONSTRAINT `FK_direccion_envio_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla karmasell.mensaje
CREATE TABLE IF NOT EXISTS `mensaje` (
  `id` int(11) NOT NULL,
  `id_anuncio` int(11) DEFAULT NULL,
  `id_escritor` int(11) DEFAULT NULL,
  `mensaje` varchar(50) NOT NULL DEFAULT '',
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mensaje_usuario` (`id_escritor`),
  KEY `FK_mensaje_anuncio` (`id_anuncio`),
  CONSTRAINT `FK_mensaje_anuncio` FOREIGN KEY (`id_anuncio`) REFERENCES `anuncio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_mensaje_usuario` FOREIGN KEY (`id_escritor`) REFERENCES `usuario` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla karmasell.pedido
CREATE TABLE IF NOT EXISTS `pedido` (
  `id` int(11) NOT NULL,
  `Id_comprador` int(11) DEFAULT NULL,
  `id_anuncio` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pedido_usuario` (`Id_comprador`),
  KEY `FK_pedido_anuncio` (`id_anuncio`),
  CONSTRAINT `FK_pedido_anuncio` FOREIGN KEY (`id_anuncio`) REFERENCES `anuncio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_pedido_usuario` FOREIGN KEY (`Id_comprador`) REFERENCES `usuario` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla karmasell.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `Id` int(11) NOT NULL DEFAULT 0,
  `Nombre` varchar(50) DEFAULT NULL,
  `Apellidos` varchar(50) DEFAULT NULL,
  `Fecha_nacimiento` datetime DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telefono` int(11) DEFAULT NULL,
  `foto` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- La exportación de datos fue deseleccionada.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
