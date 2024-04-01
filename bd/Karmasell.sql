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
CREATE DATABASE IF NOT EXISTS `karmasell2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci */;
USE `karmasell2`;

-- Volcando estructura para tabla karmasell.anuncio
CREATE TABLE IF NOT EXISTS `anuncio` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(5) NOT NULL,
  `id_categoria` int(5) NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `estado` varchar(50) NOT NULL,
  `ubicacion` varchar(50) NOT NULL,
  `precio` float NOT NULL,
  `divisa` varchar(50) NOT NULL,
  `fech_public` date DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `FK_anuncio_categoria` (`id_categoria`),
  KEY `FK_anuncio_usuario` (`id_usuario`),
  CONSTRAINT `FK_anuncio_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_anuncio_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla karmasell.categoria
CREATE TABLE IF NOT EXISTS `categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla karmasell.direccion_envio
CREATE TABLE IF NOT EXISTS `direccion_envio` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(5) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `cp` int(11) NOT NULL,
  `poblacion` varchar(50) NOT NULL,
  `provincia` varchar(50) NOT NULL,
  `pais` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_direccion_envio_usuario` (`id_usuario`),
  CONSTRAINT `FK_direccion_envio_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla karmasell.mensaje
CREATE TABLE IF NOT EXISTS `mensaje` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `id_anuncio` int(5) NOT NULL,
  `id_vendedor` int(11) NOT NULL,
  `id_comprador` int(5) NOT NULL,
  `mensaje` varchar(50) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `FK_mensaje_usuario` (`id_comprador`),
  KEY `FK_mensaje_anuncio` (`id_anuncio`),
  KEY `FK_mensaje_usuario_2` (`id_vendedor`),
  CONSTRAINT `FK_mensaje_anuncio` FOREIGN KEY (`id_anuncio`) REFERENCES `anuncio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_mensaje_usuario` FOREIGN KEY (`id_comprador`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_mensaje_usuario_2` FOREIGN KEY (`id_vendedor`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla karmasell.pedido
CREATE TABLE IF NOT EXISTS `pedido` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `id_comprador` int(5) NOT NULL,
  `id_anuncio` int(5) NOT NULL,
  `fech_pedido` date DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `FK_pedido_usuario` (`id_comprador`),
  KEY `FK_pedido_anuncio` (`id_anuncio`),
  CONSTRAINT `FK_pedido_anuncio` FOREIGN KEY (`id_anuncio`) REFERENCES `anuncio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_pedido_usuario` FOREIGN KEY (`id_comprador`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla karmasell.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `nomb_usu` varchar(50) NOT NULL,
  `contras` varchar(255) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefono` varchar(50) NOT NULL,
  `foto` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- La exportación de datos fue deseleccionada.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
