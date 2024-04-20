-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-04-2024 a las 14:06:04
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `anunciaya`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `anuncio`
--

CREATE TABLE `anuncio` (
  `id` int(5) NOT NULL,
  `id_usuario` int(5) NOT NULL,
  `id_categoria` int(5) NOT NULL,
  `titulo` varchar(20) NOT NULL,
  `descripcion` text NOT NULL,
  `estado` varchar(15) NOT NULL,
  `ubicacion` varchar(50) NOT NULL,
  `precio` float NOT NULL,
  `divisa` varchar(20) NOT NULL,
  `fotos` text NOT NULL,
  `fech_public` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `anuncio`
--

INSERT INTO `anuncio` (`id`, `id_usuario`, `id_categoria`, `titulo`, `descripcion`, `estado`, `ubicacion`, `precio`, `divisa`, `fotos`, `fech_public`) VALUES
(1, 1, 3, 'Iphone 12 PRO MAX', 'Se vende Iphone 12 PRO MAX, nuevo sin apenas uso.\r\nSe ha cuidado perfectamente, incluye todos los accesorios.', 'Muy bueno', 'Coordenadas ', 950, 'Euro', '/1/img1.png', '2024-04-02'),
(2, 1, 1, 'Seat León 2009', 'Se vende SEAT LEON 2009. Tiene 120.000km, ITV al dia y todos los mantenimientos echos en la SEAT.', 'Usado', 'Coordenadas', 8000, 'Euro', 'ruta_imagenes', '2024-04-10'),
(3, 1, 2, 'Yamaha TMAX', 'Se vende Yamaha TMAX 550 del 2020.\r\nSe compro como capricho, tiene todos los extras y mantenimientos hechos al día.\r\nMejor ver y probar.', 'Nuevo', 'Coordenadas', 10000, 'Euro', 'ruta_imagenes', '2024-04-10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` int(5) NOT NULL,
  `descripcion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `descripcion`) VALUES
(1, 'Coche'),
(2, 'Moto'),
(3, 'Electrónica'),
(4, 'Ocio');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion_envio`
--

CREATE TABLE `direccion_envio` (
  `id` int(5) NOT NULL,
  `id_usuario` int(5) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `cp` varchar(5) NOT NULL,
  `poblacion` varchar(50) NOT NULL,
  `provincia` varchar(50) NOT NULL,
  `pais` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `direccion_envio`
--

INSERT INTO `direccion_envio` (`id`, `id_usuario`, `direccion`, `cp`, `poblacion`, `provincia`, `pais`) VALUES
(1, 1, 'Avenida del Parque 12', '12345', 'Pozoblanco', 'Córdoba', 'España'),
(2, 3, 'Calle Ramon y Cajal 34', '23456', 'Belalcázar', 'Córdoba', 'España'),
(3, 1, 'Calle de ejemplo 12', '12092', 'La Malagueta', 'Málaga', 'España'),
(4, 1, 'Calle de ejemplo 12', '12092', 'La Malagueta', 'Málaga', 'España'),
(5, 1, 'Calle de ejemplo 12', '12092', 'La Malagueta', 'Málaga', 'España');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id` int(5) NOT NULL,
  `id_comprador` int(5) NOT NULL,
  `id_anuncio` int(5) NOT NULL,
  `fech_pedido` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id`, `id_comprador`, `id_anuncio`, `fech_pedido`) VALUES
(1, 3, 1, '2024-04-02'),
(2, 3, 2, '2024-04-10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(5) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `nomb_usu` varchar(20) NOT NULL,
  `contras` varchar(255) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefono` varchar(25) NOT NULL,
  `tipo` varchar(3) NOT NULL DEFAULT 'STD',
  `foto` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `nomb_usu`, `contras`, `fecha_nacimiento`, `email`, `telefono`, `tipo`, `foto`) VALUES
(1, 'Modificado', 'Apellidos modificados', 'usuario', '$2y$10$QHHyvaoIguuO7Ve.UUlN6.NejY67wPYUg2yTTqLKML9KNuBcgU.4a', '2024-04-17', 'nuevo@correo.com3', '+34 789 90 00 763', 'STD', NULL),
(2, 'Modificado', 'Apellidos modificados', 'usuario777', '$2a$12$zwdimD4AMfP2nzT9uDRoOOpxfB93Ynirl6dHd1ifOhlBqo70kmsv2\n', '2024-04-17', 'nuevo@correo.com777', '+34 789 90 00 76', 'STD', NULL),
(3, 'Modificado', 'Apellidos modificados', 'usuario1111', '$2a$12$FiftGO0JHzzeCH5djMlW8uXWdvhLTU2ZBrX11fCpEKNLt7EldQbMa', '2024-04-17', 'nuevo@correo.com', '20848209483', 'STD', NULL),
(4, 'Administrador', 'Administrador', 'admin', '$2a$12$XgYSJ6Kq8ADtfIPfoo.62.Oh7JYHJB9vqqDRNYZ72tPZH60Iw3qCO', '2024-04-17', 'admin@example.com', '000000000', 'ADM', NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `anuncio`
--
ALTER TABLE `anuncio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_anuncio_categoria` (`id_categoria`),
  ADD KEY `FK_anuncio_usuario` (`id_usuario`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `direccion_envio`
--
ALTER TABLE `direccion_envio`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_direccion_envio_usuario` (`id_usuario`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_pedido_usuario` (`id_comprador`),
  ADD KEY `FK_pedido_anuncio` (`id_anuncio`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UNIQUE_tlf` (`telefono`),
  ADD UNIQUE KEY `UNIQUE_nomb_usu` (`nomb_usu`) USING BTREE,
  ADD UNIQUE KEY `UNIQUE_email` (`email`) USING BTREE;

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `anuncio`
--
ALTER TABLE `anuncio`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `direccion_envio`
--
ALTER TABLE `direccion_envio`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `anuncio`
--
ALTER TABLE `anuncio`
  ADD CONSTRAINT `FK_anuncio_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_anuncio_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `direccion_envio`
--
ALTER TABLE `direccion_envio`
  ADD CONSTRAINT `FK_direccion_envio_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK_pedido_anuncio` FOREIGN KEY (`id_anuncio`) REFERENCES `anuncio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_pedido_usuario` FOREIGN KEY (`id_comprador`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
