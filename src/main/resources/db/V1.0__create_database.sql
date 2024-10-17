-- Este es un volcado (dump) de una base de datos MySQL que incluye la estructura de tablas y datos.
-- Base de datos: flights_reservation
-- Versión del servidor: 8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
-- Guarda la configuración actual del conjunto de caracteres del cliente para restaurarlo después.

/*!50503 SET NAMES utf8 */;
-- Establece el conjunto de caracteres a UTF-8 para esta sesión, lo que garantiza la correcta interpretación de caracteres.

### Estructura de la tabla `itinerary`

DROP TABLE IF EXISTS `itinerary`;
-- Elimina la tabla `itinerary` si ya existe para evitar errores al recrearla.

CREATE TABLE `itinerary` (
    `id` bigint AUTO_INCREMENT PRIMARY KEY,  -- Clave primaria autoincremental para identificar cada itinerario.
    `version` bigint,                        -- Columna para versiones (puede utilizarse para control de concurrencia).
    `itinerary_id` bigint DEFAULT NULL,      -- Columna para relacionar este itinerario con otro (clave foránea).
    UNIQUE KEY `UK_nuabwliqt5wnaafbfe1mjqdtu` (`itinerary_id`), -- Índice único en `itinerary_id`.
    CONSTRAINT `FKfuq1y9btsw9if6vuyrnngvw0j` FOREIGN KEY (`itinerary_id`) REFERENCES `price` (`id`)
    -- Clave foránea que vincula `itinerary_id` con la columna `id` de la tabla `price`.
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- Define la tabla `itinerary` con el motor InnoDB y UTF-8 como juego de caracteres.

### Bloqueo de la tabla para operaciones

LOCK TABLES `itinerary` WRITE;
-- Bloquea la tabla `itinerary` para que solo permita escritura, previniendo acceso simultáneo.

UNLOCK TABLES;
-- Desbloquea la tabla permitiendo su uso normal nuevamente.

### Estructura de la tabla `passenger`

DROP TABLE IF EXISTS `passenger`;
-- Elimina la tabla `passenger` si ya existe.

CREATE TABLE `passenger` (
    `birthday` date NOT NULL,               -- Fecha de nacimiento del pasajero.
    `id` bigint AUTO_INCREMENT PRIMARY KEY, -- Clave primaria autoincremental.
    `version` bigint,                       -- Columna para control de versiones.
    `reservation_id` bigint DEFAULT NULL,   -- Clave foránea que relaciona el pasajero con una reserva.
    `first_name` varchar(30) NOT NULL,      -- Primer nombre del pasajero.
    `last_name` varchar(30) NOT NULL,       -- Apellido del pasajero.
    `document_number` varchar(255) NOT NULL,-- Número de documento del pasajero.
    `document_type` varchar(255) NOT NULL,  -- Tipo de documento (por ejemplo, pasaporte, DNI).
    CONSTRAINT `FKp4qdewtk73iwqerswqtqs0g1q` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`)
    -- Clave foránea que enlaza `reservation_id` con la columna `id` de la tabla `reservation`.
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- Define la tabla `passenger` con el motor InnoDB y UTF-8 como juego de caracteres.

### Estructura de la tabla `price`

DROP TABLE IF EXISTS `price`;
-- Elimina la tabla `price` si ya existe.

CREATE TABLE `price` (
    `base_price` decimal(38,2) NOT NULL,    -- Precio base del itinerario o vuelo.
    `total_price` decimal(38,2) NOT NULL,   -- Precio total después de impuestos.
    `total_tax` decimal(38,2) NOT NULL,     -- Impuestos totales sobre el precio base.
    `version` bigint,                       -- Columna de versión para control de concurrencia.
    `id` bigint AUTO_INCREMENT PRIMARY KEY  -- Clave primaria autoincremental.
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- Define la tabla `price` para almacenar precios con el motor InnoDB y UTF-8.

### Estructura de la tabla `reservation`

DROP TABLE IF EXISTS `reservation`;
-- Elimina la tabla `reservation` si ya existe.

CREATE TABLE `reservation` (
    `creation_date` date NOT NULL,          -- Fecha de creación de la reserva.
    `id` bigint AUTO_INCREMENT PRIMARY KEY, -- Clave primaria autoincremental.
    `version` bigint,                       -- Columna de versión para control de concurrencia.
    `itinerary_id` bigint DEFAULT NULL,     -- Clave foránea que relaciona la reserva con un itinerario.
    CONSTRAINT `FKmm7xy4cybogy2irexcsttlvi2` FOREIGN KEY (`itinerary_id`) REFERENCES `itinerary` (`id`)
    -- Clave foránea que enlaza `itinerary_id` con la columna `id` de la tabla `itinerary`.
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- Define la tabla `reservation` para almacenar las reservas, utilizando el motor InnoDB y UTF-8.

### Estructura de la tabla `segment`

DROP TABLE IF EXISTS `segment`;
-- Elimina la tabla `segment` si ya existe.

CREATE TABLE `segment` (
    `carrier` varchar(3) NOT NULL,          -- Código de la aerolínea (por ejemplo, AA para American Airlines).
    `destination` varchar(3) NOT NULL,      -- Código del aeropuerto de destino (IATA).
    `origin` varchar(3) NOT NULL,           -- Código del aeropuerto de origen (IATA).
    `id` bigint AUTO_INCREMENT PRIMARY KEY, -- Clave primaria autoincremental.
    `version` bigint,                       -- Columna de versión.
    `itinerary_id` bigint DEFAULT NULL,     -- Clave foránea que vincula el segmento al itinerario.
    `arrival` varchar(255) NOT NULL,        -- Hora de llegada (podría ser texto o formato).
    `departure` varchar(255) NOT NULL,      -- Hora de salida.
    CONSTRAINT `FK8l81wkolgwmpxhicuc9o6u141` FOREIGN KEY (`itinerary_id`) REFERENCES `itinerary` (`id`)
    -- Clave foránea que enlaza `itinerary_id` con la columna `id` de la tabla `itinerary`.
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- Define la tabla `segment` que almacena los tramos o segmentos de un vuelo dentro de un itinerario.

### Fin del volcado
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
-- Restaura la zona horaria anterior.

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
-- Restaura el modo SQL anterior.

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
-- Restaura las verificaciones de claves foráneas.

/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
-- Restaura las verificaciones de unicidad.

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
-- Restaura el juego de caracteres del cliente.

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
-- Restaura las notas SQL.
