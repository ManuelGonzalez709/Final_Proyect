-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-05-2024 a las 12:56:24
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
  `titulo` varchar(50) NOT NULL,
  `descripcion` text NOT NULL,
  `estado` varchar(15) NOT NULL,
  `ubicacion` varchar(50) NOT NULL,
  `precio` float NOT NULL,
  `fotos` text NOT NULL,
  `fech_public` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `anuncio`
--

INSERT INTO `anuncio` (`id`, `id_usuario`, `id_categoria`, `titulo`, `descripcion`, `estado`, `ubicacion`, `precio`, `fotos`, `fech_public`) VALUES
(39, 23, 3, 'Teclado mecanico', 'Teclado gamer de conexión USB,de tamaño perfecto para tenerle en cualquier sitio,resistente al agua.', 'Muy bueno', 'Pozoblanco', 25, 'http://192.168.200.170/sv-php/img/39_1_IMG_20240528_165115072.jpg;http://192.168.200.170/sv-php/img/39_1_IMG_20240528_165121423.jpg;http://192.168.200.170/sv-php/img/39_1_IMG_20240528_165127974.jpg;', '2024-05-28'),
(40, 22, 3, 'Micro NUEVO', 'Buenas ! Este micrófono literalmente tiene como 2 horas de uso , lo compré lo usé me vine abajo y lo guardé . No me dio tiempo ni a tirar la caja cuando ya quise guardarlo de nuevo porque ya no quise hacer streams , funciona super bien y se escucha fenomenal ! Trae su anti-pop y se puede apagar el micrófono sin necesidad de meterse en ajustes ni nada , pulsando encima del micrófono !!', 'Muy bueno', 'Hinojosa del Duque', 55, 'http://192.168.200.170/sv-php/img/40_1_IMG_20240528_165912054.jpg;http://192.168.200.170/sv-php/img/40_1_IMG_20240528_165941508.jpg;', '2024-05-28'),
(41, 22, 3, 'Cable cargador', 'Se vende Cable Cargador de iPhone. Totalmente nuevo y precintado. Se puede ver y probar.', 'Nuevo', 'Pozoblanco', 10, 'http://192.168.200.170/sv-php/img/41_1_IMG_20240528_165824613.jpg;', '2024-05-28'),
(42, 23, 3, 'Repetidor wifi', 'TP Link modelo TL-WPA8630P kit repetidor de wifi con 3 puertos y enfuche. Caja original, no lleva cables Ethernet. Hay una mancha en la extensión pero no interfiere con el perfecto funcionamiento.', 'Usado', 'Córdoba', 40, 'http://192.168.200.170/sv-php/img/42_23_IMG_20240528_174412585.jpg;http://192.168.200.170/sv-php/img/42_23_IMG_20240528_174421150.jpg;http://192.168.200.170/sv-php/img/42_23_IMG_20240528_174427964.jpg;', '2024-05-28'),
(57, 41, 6, 'Padre rico padre pobre', 'En perfecto estado.', 'Muy bueno', 'Montemayor', 12, 'http://192.168.200.170/sv-php/img/57_1_IMG_20240529_111627559.jpg;http://192.168.200.170/sv-php/img/57_1_IMG_20240529_111636441.jpg', '2024-05-29'),
(58, 41, 6, 'Libro ENRIQUE MORIS', 'Vendo libro de Enrique Moris. A 22 Pasos del éxito. Nuevo a estrenar.', 'Nuevo', 'Lucena', 22, 'http://192.168.200.170/sv-php/img/58_1_IMG_20240529_112424452.jpg;http://192.168.200.170/sv-php/img/58_1_IMG_20240529_112431789.jpg;', '2024-05-29'),
(59, 22, 6, 'Comic Dragon Ball Z', 'Puedo mandar mas fotos, esta en perfecto estado\n', 'Usado', 'Montilla', 14.95, 'http://192.168.200.170/sv-php/img/59_1_IMG_20240529_112807359.jpg;', '2024-05-29'),
(60, 39, 6, 'Libro y comic', '2 libros personajes\n1 libro Fanboy & Chumchum\nNos olemos luego\nIsbn 9788415239253\n1 comic Inazuma Eleven go\nIsbn 9788415480389\nLos dos 5 euros 1 a 3 euros\nBuena idea regalo Tio Navidad, Reyes, amigo invisibl', 'Usado', 'Belalcázar', 5, 'http://192.168.200.170/sv-php/img/60_1_IMG_20240529_113132040.jpg;http://192.168.200.170/sv-php/img/60_1_IMG_20240529_113138870.jpg;', '2024-05-29'),
(62, 22, 6, 'Cómic pokemon', 'Cómic de pokemon oro, plata y cristal tomo 7 en perfecto estado.', 'Muy bueno', 'Posadas', 18, 'http://192.168.200.170/sv-php/img/62_1_IMG_20240529_155314238.jpg', '2024-05-29'),
(63, 22, 4, 'Jeans hombre', 'Vaqueros Energie para hombre, ajuste excelente, color azul vaquero con ribetes. ¡A estrenar!', 'Usado', 'Palma del Río', 30, 'http://192.168.200.170/sv-php/img/63_1_IMG_20240529_160217428.jpg;http://192.168.200.170/sv-php/img/63_1_IMG_20240529_160225921.jpg;http://192.168.200.170/sv-php/img/63_1_IMG_20240529_160234433.jpg;', '2024-05-29'),
(64, 40, 4, 'Camiseta Nike', 'Camiseta con logo estampado. Recomendado ⭐⭐⭐⭐⭐', 'Usado', 'Hinojosa del Duque', 15, 'http://192.168.200.170/sv-php/img/64_1_IMG_20240529_160603084.jpg;', '2024-05-29'),
(65, 22, 4, 'Polo LACOSTE', 'Polo azul de algodón (piqué), manga corta, talla M, marca Lacoste, excelente estado.', 'Usado', 'Pozoblanco', 28, 'http://192.168.200.170/sv-php/img/65_1_IMG_20240529_160737307.jpg;http://192.168.200.170/sv-php/img/65_1_IMG_20240529_160744534.jpg;http://192.168.200.170/sv-php/img/65_1_IMG_20240529_160753356.jpg;http://192.168.200.170/sv-php/img/65_1_IMG_20240529_160801473.jpg;', '2024-05-29'),
(66, 39, 4, 'Nike Airforce 1', 'Se vende porque me las regalaron y me van pequeñas', 'Nuevo', 'Lucena', 75, 'http://192.168.200.170/sv-php/img/66_1_IMG_20240529_160918700.jpg;http://192.168.200.170/sv-php/img/66_1_IMG_20240529_160926952.jpg;', '2024-05-29'),
(70, 24, 4, 'Air Jordan 4', 'Talla 42\nSin usar\nTengo la factura de compra', 'Nuevo', 'Villafranca de Córdoba', 250, 'http://192.168.200.170/sv-php/img/70_1_IMG_20240529_161429453.jpg;http://192.168.200.170/sv-php/img/70_1_IMG_20240529_161546083.jpg;http://192.168.200.170/sv-php/img/70_1_IMG_20240529_161643004.jpg;http://192.168.200.170/sv-php/img/70_1_IMG_20240529_161649835.jpg', '2024-05-29'),
(71, 40, 5, 'Mesa auxiliar', 'Mesa auxiliar de madera de pino de Ikea modelo NORNAS de gran resistencia, puede servir para niños o para otros usos. Se vende montada pero si es envío se puede desmontar. Tablero en verde agua y resto color pino', 'Muy bueno', 'Córdoba', 20, 'http://192.168.200.170/sv-php/img/71_1_IMG_20240529_161845983.jpg;http://192.168.200.170/sv-php/img/71_1_IMG_20240529_161916517.jpg;http://192.168.200.170/sv-php/img/71_1_IMG_20240529_161924651.jpg', '2024-05-29'),
(72, 24, 5, 'Silla escritorio', 'Silla de escritorio IKEA', 'Muy bueno', 'Peñarroya-Pueblonuevo', 15, 'http://192.168.200.170/sv-php/img/72_1_IMG_20240529_162055188.jpg;http://192.168.200.170/sv-php/img/72_1_IMG_20240529_162108415.jpg;http://192.168.200.170/sv-php/img/72_1_IMG_20240529_162116659.jpg;', '2024-05-29'),
(73, 40, 5, 'Jarrón niquel', 'JARRÓN NIQUEL METAL DECORACIÓN', 'Nuevo', 'Lucena', 22, 'http://192.168.200.170/sv-php/img/73_1_IMG_20240529_162304852.jpg;', '2024-05-29'),
(74, 22, 5, 'Làmpara techo', 'Lampara de techo y 2 apliques', 'Muy bueno', 'Lucena', 35, 'http://192.168.200.170/sv-php/img/74_1_IMG_20240529_162442792.jpg;', '2024-05-29'),
(75, 41, 2, 'Yamaha yzf R1', 'vendo Yamaha r1 2008 azul 65000 km hechos en ruta. completamente de serie y muy cuidada todos sus cambios al día líquidos de frenos pastillas tec..ITV en vigor ,kit de transmisión nuevo, cambidada la corona y embrague. muy nueva y cuidada', 'Usado', 'Cabra', 6900, 'http://192.168.200.170/sv-php/img/75_1_IMG_20240529_163700236.jpg;http://192.168.200.170/sv-php/img/75_1_IMG_20240529_163709202.jpg;http://192.168.200.170/sv-php/img/75_1_IMG_20240529_163721971.jpg;', '2024-05-29'),
(76, 38, 2, 'Yamaha Jog r', 'Vendo yamaha jog r con top negro 70cc, variador polini hi-speed.', 'Usado', 'Fuente la Lancha', 1400, 'http://192.168.200.170/sv-php/img/76_1_IMG_20240529_164040038.jpg;http://192.168.200.170/sv-php/img/76_1_IMG_20240529_164224274.jpg;http://192.168.200.170/sv-php/img/76_1_IMG_20240529_164230930.jpg', '2024-05-29'),
(77, 24, 2, 'Gasgas 250cc', 'Se vende gas gas del 99, moto bien cuidado totalmente nuevo de todo, suspensión recién echas de hace 3 meses, plásticos, kit de pegatinas, foco delantero de led y trasero nuevo, se acepta prueba de mecánico, ruedas 80% , kit de arrastre nuevo, bufanda mexxico y colín fmf powercore 2. sin itv.', 'Usado', 'Puente Genil', 2400, 'http://192.168.200.170/sv-php/img/77_1_IMG_20240529_164416859.jpg;http://192.168.200.170/sv-php/img/77_1_IMG_20240529_164436434.jpg;http://192.168.200.170/sv-php/img/77_1_IMG_20240529_164449360.jpg', '2024-05-29'),
(78, 41, 1, 'Golf 2013', 'Volkswagen Golf Mk 7 R line 2.0 TDI 150 cv con muchísimos extras y en perfecto estado en general, tiene todo el mantenimiento recién hecho. Extras: -Paquete completo interior y exterior Rline (paragolpes, taloneras, volante multi función, asientos de piel calefactables…) -Techo solar panorámico -Navegador, climatizador bizona, front assist, start stop, cámara de marcha atrás… -Faros led delanteros y traseros -Llantas 19” de GTI 35th originales y con los 4 neumáticos kuhmo. Se ha cambiado em', 'Muy bueno', 'Lucena', 16500, 'http://192.168.200.170/sv-php/img/78_41_IMG_20240529_171643397.jpg;http://192.168.200.170/sv-php/img/78_41_IMG_20240529_171659022.jpg;http://192.168.200.170/sv-php/img/78_41_IMG_20240529_171706068.jpg;http://192.168.200.170/sv-php/img/78_41_IMG_20240529_171712089.jpg', '2024-05-29'),
(79, 41, 1, 'BMW F30', 'En perfecto estado, coche nacional, cambio steptronic 8 velocidades, muy potente y consumo de risa, faros bixenon, asientos piel calefactables, climatizador, llanta 17', 'Muy bueno', 'Lucena', 16490, 'http://192.168.200.170/sv-php/img/79_41_IMG_20240529_173619016.jpg;http://192.168.200.170/sv-php/img/79_41_IMG_20240529_173637902.jpg;http://192.168.200.170/sv-php/img/79_41_IMG_20240529_173645863.jpg;http://192.168.200.170/sv-php/img/79_41_IMG_20240529_173651667.jpg;http://192.168.200.170/sv-php/img/79_41_IMG_20240529_173657046.jpg', '2024-05-29'),
(80, 41, 1, 'Range Rover Velar R', 'IBERICA RETAIL SUR - LUCENA. Oferta Flash solo 24 H !! FINANC. MÍN 25.000€ RANGE ROVER VELAR R DYNAMIC 180CV FULL EQUIP. (Automático) EXTRAS: - Color: Negro Perla - Techo solar Panorámico - Asientos Piel Sport Beige', 'Muy bueno', 'Lucena', 42990, 'http://192.168.200.170/sv-php/img/80_41_IMG_20240529_173800002.jpg;http://192.168.200.170/sv-php/img/80_41_IMG_20240529_173819655.jpg;http://192.168.200.170/sv-php/img/80_41_IMG_20240529_173830488.jpg;http://192.168.200.170/sv-php/img/80_41_IMG_20240529_173839272.jpg;http://192.168.200.170/sv-php/img/80_41_IMG_20240529_173905398.jpg', '2024-05-29');

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
(1, 'Coches'),
(2, 'Motos'),
(3, 'Electrónica'),
(4, 'Moda'),
(5, 'Hogar'),
(6, 'Libros');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id` int(5) NOT NULL,
  `id_comprador` int(5) NOT NULL,
  `id_anuncio` int(5) NOT NULL,
  `fech_pedido` date NOT NULL DEFAULT current_timestamp(),
  `ciudad` varchar(50) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `cp` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id`, `id_comprador`, `id_anuncio`, `fech_pedido`, `ciudad`, `direccion`, `cp`) VALUES
(6, 41, 63, '2024-05-30', 'Córdoba', 'Calle Islas Canarias 12', '14014'),
(7, 41, 39, '2024-05-30', 'Córdoba', 'Calle Islas Canarias 12', '14014'),
(8, 23, 57, '2024-05-30', 'Hinojosa del Duque', 'Avenida Parque 105', '14270'),
(9, 23, 75, '2024-05-30', 'Alcaracejos', 'Calle Plaza Colón 23', '14480');

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
  `tipo` varchar(3) NOT NULL DEFAULT 'STD'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `nomb_usu`, `contras`, `fecha_nacimiento`, `email`, `telefono`, `tipo`) VALUES
(22, 'María', 'Martínez Rodríguez', 'mmartinez', '$2y$10$QHHyvaoIguuO7Ve.UUlN6.NejY67wPYUg2yTTqLKML9KNuBcgU.4a', '2002-12-09', 'mariamartrodr@gmail.com', '+34 789 90 00 763', 'STD'),
(23, 'Lucas', 'Murillo Sánchez ', 'lmurillo', '$2y$10$QHHyvaoIguuO7Ve.UUlN6.NejY67wPYUg2yTTqLKML9KNuBcgU.4a', '2004-06-28', 'lucasmurllsnch@gmail.com', '+34 789 90 00 060', 'STD'),
(24, 'Mario', 'Ruiz Moyano', 'mruiz', '$2y$10$QHHyvaoIguuO7Ve.UUlN6.NejY67wPYUg2yTTqLKML9KNuBcgU.4a', '1998-02-26', 'marioruizmy@gmail.com', '+34 689 90 00 760', 'STD'),
(25, 'Administrador', 'Administrador', 'admin', '$2y$10$QHHyvaoIguuO7Ve.UUlN6.NejY67wPYUg2yTTqLKML9KNuBcgU.4a', '2004-08-15', 'admin@anunciaya.es', '+34 789 98 00 710', 'ADM'),
(38, 'Claudia', 'Martínez Rodríguez', 'cmartinez', '$2y$10$QHHyvaoIguuO7Ve.UUlN6.NejY67wPYUg2yTTqLKML9KNuBcgU.4a', '2002-12-09', 'mariamartrodddr@gmail.com', '+34 789 90 00 760', 'STD'),
(39, 'Juan', 'López García', 'jlopez', '$2y$10$QHHyvaoIguuO7Ve.UUlN6.NejY67wPYUg2yTTqLKML9KNuBcgU.4a', '1995-05-15', 'juanlopez@gmail.com', '+34 123 45 67 890', 'STD'),
(40, 'Ana', 'González Fernández', 'agonzalez', '$2y$10$QHHyvaoIguuO7Ve.UUlN6.NejY67wPYUg2yTTqLKML9KNuBcgU.4a', '1988-03-22', 'anagonzalez@gmail.com', '+34 456 78 90 123', 'STD'),
(41, 'Carlos', 'Pérez Sánchez', 'cperez', '$2y$10$QHHyvaoIguuO7Ve.UUlN6.NejY67wPYUg2yTTqLKML9KNuBcgU.4a', '1990-07-30', 'carlosperez@gmail.com', '+34 789 12 34 567', 'STD');

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
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `anuncio`
--
ALTER TABLE `anuncio`
  ADD CONSTRAINT `FK_anuncio_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_anuncio_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK_pedido_anuncio` FOREIGN KEY (`id_anuncio`) REFERENCES `anuncio` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_pedido_usuario` FOREIGN KEY (`id_comprador`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
