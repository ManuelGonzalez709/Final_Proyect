# Modelo para la Base de Datos
## Diagrama Entidad Relación
Diseño del diagrama para el modelo entidad relación.

![](diagrama_e-r.svg)

## Diagrama Modelo Relacional
Diseño del diagrama para la traducción del modelo entidad relación a modelo relacional.

![](diagrama_m-r.svg)

## Esquema de Tablas
### Tabla Usuario
Tabla donde se almacenarán los usuarios de la aplicación.
- ```tipo``` &#8594; Permisos del usuario, por defecto STD (estándar) que serán los que usen la aplicación móvil para realizar las ventas o compras de los productos de segunda manos y ADM (administrador) que será un perfil administrador el cual gestionará la aplicación.

  
```sql
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
  `foto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
```

### Tabla Categoria
Tabla donde se almacenarán las categorías que pueden pertenecer un anuncio.
```sql
CREATE TABLE `categoria` (
  `id` int(5) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
```

### Tabla Dirección Envio
Tabla donde se recogerán las direcciones de envio de los usuarios.
```sql
CREATE TABLE `direccion_envio` (
  `id` int(5) NOT NULL,
  `id_usuario` int(5) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `cp` varchar(5) NOT NULL,
  `poblacion` varchar(50) NOT NULL,
  `provincia` varchar(50) NOT NULL,
  `pais` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_direccion_envio_usuario` (`id_usuario`),
  CONSTRAINT `FK_direccion_envio_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
```

### Tabla Anuncio
Tabla donde se almacenarán los anuncios que pertenecen a los usuario los cuales otros usuarios serán los compradores.
```sql
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
  `fech_public` date NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `FK_anuncio_categoria` (`id_categoria`),
  KEY `FK_anuncio_usuario` (`id_usuario`),
  CONSTRAINT `FK_anuncio_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_anuncio_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
```

### Tabla Usuario
Tabla donde se almacenarán los pedidos de los anuncios realizados por los usuarios.
```sql
CREATE TABLE `pedido` (
  `id` int(5) NOT NULL,
  `id_comprador` int(5) NOT NULL,
  `id_anuncio` int(5) NOT NULL,
  `fech_pedido` date NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `FK_pedido_usuario` (`id_comprador`),
  KEY `FK_pedido_anuncio` (`id_anuncio`),
  CONSTRAINT `FK_pedido_anuncio` FOREIGN KEY (`id_anuncio`) REFERENCES `anuncio` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_pedido_usuario` FOREIGN KEY (`id_comprador`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
```



































