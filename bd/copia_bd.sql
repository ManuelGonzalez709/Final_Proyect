CREATE DATABASE IF NOT EXISTS `karmasell` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci */;
USE `karmasell`;

-- Volcando estructura para tabla karmasell.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` INT(5) AUTO_INCREMENT PRIMARY KEY,
  `nombre` VARCHAR(50) NOT NULL,
  `apellidos` VARCHAR(50) NOT NULL,
  `nomb_usu` VARCHAR(50) NOT NULL,
  `contras` VARCHAR(255) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `telefono` VARCHAR(25) NOT NULL,
  `foto` VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando estructura para tabla karmasell.categoria
CREATE TABLE IF NOT EXISTS `categoria` (
  `id` INT(11) AUTO_INCREMENT PRIMARY KEY,
  `descripcion` VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando estructura para tabla karmasell.direccion_envio
CREATE TABLE IF NOT EXISTS `direccion_envio` (
  `id` INT(5) AUTO_INCREMENT PRIMARY KEY,
  `id_usuario` INT(5) NOT NULL,
  `direccion` VARCHAR(50) NOT NULL,
  `cp` INT(11) NOT NULL,
  `poblacion` VARCHAR(50) NOT NULL,
  `provincia` VARCHAR(50) NOT NULL,
  `pais` VARCHAR(50) NOT NULL,
  KEY `FK_direccion_envio_usuario` (`id_usuario`),
  CONSTRAINT `FK_direccion_envio_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando estructura para tabla karmasell.anuncio
CREATE TABLE IF NOT EXISTS `anuncio` (
  `id` INT(5) AUTO_INCREMENT PRIMARY KEY,
  `id_usuario` INT(5) NOT NULL,
  `id_categoria` INT(5) NOT NULL,
  `titulo` VARCHAR(50) NOT NULL,
  `descripcion` VARCHAR(50) NOT NULL,
  `estado` VARCHAR(50) NOT NULL,
  `ubicacion` VARCHAR(50) NOT NULL,
  `precio` FLOAT NOT NULL,
  `divisa` VARCHAR(50) NOT NULL,
  `fech_public` DATE DEFAULT CURRENT_TIMESTAMP NOT NULL,
  KEY `FK_anuncio_categoria` (`id_categoria`),
  KEY `FK_anuncio_usuario` (`id_usuario`),
  CONSTRAINT `FK_anuncio_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_anuncio_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando estructura para tabla karmasell.mensaje
CREATE TABLE IF NOT EXISTS `mensaje` (
  `id` INT(5) AUTO_INCREMENT PRIMARY KEY,
  `id_anuncio` INT(5) NOT NULL,
  `id_comprador` INT(5) NOT NULL,
  `id_vendedor` INT(5) NOT NULL,
  `mensaje` VARCHAR(50) NOT NULL,
  `fecha` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  KEY `FK_mensaje_usuario` (`id_comprador`),
  KEY `FK_mensaje_anuncio` (`id_anuncio`),
  CONSTRAINT `FK_mensaje_anuncio` FOREIGN KEY (`id_anuncio`) REFERENCES `anuncio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_mensaje_comprador` FOREIGN KEY (`id_comprador`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_mensaje_vendedor` FOREIGN KEY (`id_vendedor`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando estructura para tabla karmasell.pedido
CREATE TABLE IF NOT EXISTS `pedido` (
  `id` INT(5) AUTO_INCREMENT PRIMARY KEY,
  `id_comprador` INT(5) NOT NULL,
  `id_anuncio` INT(5) NOT NULL,
  `fech_pedido` DATE DEFAULT CURRENT_TIMESTAMP NOT NULL,
  KEY `FK_pedido_usuario` (`id_comprador`),
  KEY `FK_pedido_anuncio` (`id_anuncio`),
  CONSTRAINT `FK_pedido_anuncio` FOREIGN KEY (`id_anuncio`) REFERENCES `anuncio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_pedido_usuario` FOREIGN KEY (`id_comprador`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;



