SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

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
  `descripcion` varchar(255) NOT NULL,
  `estado` varchar(15) NOT NULL,
  `ubicacion` varchar(50) NOT NULL,
  `precio` float NOT NULL,
  `divisa` varchar(20) NOT NULL,
  `fech_public` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `anuncio`
--

INSERT INTO `anuncio` (`id`, `id_usuario`, `id_categoria`, `titulo`, `descripcion`, `estado`, `ubicacion`, `precio`, `divisa`, `fech_public`) VALUES
(1, 1, 3, 'Iphone 12 PRO MAX', 'Se vende Iphone 12 PRO MAX, nuevo sin apenas uso.\r\nSe ha cuidado perfectamente, incluye todos los accesorios.', 'Muy bueno', 'Coordenadas ', 950, 'Euro', '2024-04-02');

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
(2, 3, 'Calle Ramon y Cajal 34', '23456', 'Belalcázar', 'Córdoba', 'España');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensaje`
--

CREATE TABLE `mensaje` (
  `id` int(5) NOT NULL,
  `id_anuncio` int(5) NOT NULL,
  `id_emisor` int(5) NOT NULL,
  `id_receptor` int(5) NOT NULL,
  `mensaje` varchar(255) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `mensaje`
--

INSERT INTO `mensaje` (`id`, `id_anuncio`, `id_emisor`, `id_receptor`, `mensaje`, `fecha`) VALUES
(1, 1, 3, 1, 'Hola, ¿sigue disponible?', '2024-04-02 10:21:32'),
(2, 1, 1, 3, 'Hola buenas, sí sigue disponible.', '2024-04-02 10:45:39');

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
(1, 3, 1, '2024-04-02');

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
  `foto` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `nomb_usu`, `contras`, `fecha_nacimiento`, `email`, `telefono`, `foto`) VALUES
(1, 'Francisco', 'Lopez Montes', 'franlm', 'JASDKDHASJDHASKDHSAJKDHAD', '1995-06-08', 'franlopz@gemail.com', '+34 678 54 32 60', NULL),
(2, 'María', 'Cantueso Murillo', 'mariacm', 'JASDKDHASJDHASKDHSAJKDHAD', '2004-04-01', 'mariacanmu@jotmail.com', '+34 789 90 00 76', NULL),
(3, 'Cristina', 'Del Valle', 'cristinadv', 'JASDKDHASJDHASKDHSAJKDHAD', '2000-07-06', 'cristinadv@yajoo.com', '+34 678 54 32 12', NULL);

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
-- Indices de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_mensaje_usuario` (`id_emisor`),
  ADD KEY `FK_mensaje_anuncio` (`id_anuncio`),
  ADD KEY `FK_mensaje_vendedor` (`id_receptor`);

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
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `anuncio`
--
ALTER TABLE `anuncio`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `direccion_envio`
--
ALTER TABLE `direccion_envio`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `mensaje`
--
ALTER TABLE `mensaje`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
-- Filtros para la tabla `mensaje`
--
ALTER TABLE `mensaje`
  ADD CONSTRAINT `FK_mensaje_anuncio` FOREIGN KEY (`id_anuncio`) REFERENCES `anuncio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_mensaje_comprador` FOREIGN KEY (`id_emisor`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_mensaje_vendedor` FOREIGN KEY (`id_receptor`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK_pedido_usuario` FOREIGN KEY (`id_comprador`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;
