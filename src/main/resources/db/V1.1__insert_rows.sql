-- Volcado de la base de datos `flights_reservation`
-- Esta configuración inicial guarda el estado actual de varias variables para restaurarlas después del volcado.
SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT;
SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS;
SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION;
SET NAMES utf8;
SET @OLD_TIME_ZONE=@@TIME_ZONE;
SET TIME_ZONE='+00:00';  -- Se establece la zona horaria a UTC
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;  -- Deshabilita la comprobación de valores únicos
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;  -- Deshabilita la comprobación de claves foráneas
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';
SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0;  -- Deshabilita las notas SQL

-- ------------------------------------------------------
-- Sección: Inserción de datos en las tablas

-- Tabla: `itinerary`
LOCK TABLES `itinerary` WRITE;  -- Bloquea la tabla `itinerary` para escritura
ALTER TABLE `itinerary` DISABLE KEYS;  -- Deshabilita las claves para mejorar la inserción de datos
INSERT INTO `itinerary` VALUES (1,3,6), (2,1,3), (3,0,7), (4,0,8), (5,0,9);  -- Inserta 5 itinerarios
ALTER TABLE `itinerary` ENABLE KEYS;  -- Habilita nuevamente las claves
UNLOCK TABLES;  -- Desbloquea la tabla

-- Tabla: `passenger`
LOCK TABLES `passenger` WRITE;  -- Bloquea la tabla `passenger` para escritura
ALTER TABLE `passenger` DISABLE KEYS;  -- Deshabilita las claves para la inserción
INSERT INTO `passenger` VALUES
('1985-01-01',1,0,1,'Andres','Sacco','AB554713','PASSPORT'),
('1985-01-01',2,0,2,'Andres','Sacco','AB554714','PASSPORT'),
('1985-01-01',3,0,2,'Horacio','Sacco','AB554715','PASSPORT'),
('1985-01-01',4,0,3,'Ignacio','Canale','AB554716','PASSPORT'),
('1985-01-01',5,0,4,'Julian','Dominguez','AB554717','PASSPORT'),
('1985-01-01',6,0,1,'Andres','Sacco','AB554718','PASSPORT'),
('1985-01-01',7,0,3,'Josefa','Sacco','AB554719','PASSPORT'),
('1985-01-01',8,0,4,'Maria','Rodriguez','AB554720','PASSPORT'),
('1985-01-01',9,0,5,'Rodolfo','Rodriguez','AB554721','PASSPORT'),
('1985-01-01',10,0,5,'Maria','Ferrari','AB554722','PASSPORT');  -- Inserta datos de 10 pasajeros
ALTER TABLE `passenger` ENABLE KEYS;  -- Habilita nuevamente las claves
UNLOCK TABLES;  -- Desbloquea la tabla

-- Tabla: `price`
LOCK TABLES `price` WRITE;  -- Bloquea la tabla `price` para escritura
ALTER TABLE `price` DISABLE KEYS;  -- Deshabilita las claves para la inserción
INSERT INTO `price` VALUES
(10.00,30.00,20.00,0,1),
(10.00,30.00,20.00,0,2),
(10.00,30.00,20.00,0,3),
(10.00,30.00,20.00,0,4),
(10.00,30.00,20.00,0,5),
(10.00,30.00,20.00,0,6),
(10.00,30.00,20.00,0,7),
(10.00,30.00,20.00,0,8),
(10.00,30.00,20.00,0,9);  -- Inserta 9 registros de precios
ALTER TABLE `price` ENABLE KEYS;  -- Habilita nuevamente las claves
UNLOCK TABLES;  -- Desbloquea la tabla

-- Tabla: `reservation`
LOCK TABLES `reservation` WRITE;  -- Bloquea la tabla `reservation` para escritura
ALTER TABLE `reservation` DISABLE KEYS;  -- Deshabilita las claves para la inserción
INSERT INTO `reservation` VALUES
('2023-11-11',1,3,1),
('2023-11-12',2,1,2),
('2023-11-10',3,0,3),
('2023-11-13',4,0,4),
('2023-11-13',5,0,5);  -- Inserta 5 reservas con fechas y sus correspondientes IDs
ALTER TABLE `reservation` ENABLE KEYS;  -- Habilita nuevamente las claves
UNLOCK TABLES;  -- Desbloquea la tabla

-- Tabla: `segment`
LOCK TABLES `segment` WRITE;  -- Bloquea la tabla `segment` para escritura
ALTER TABLE `segment` DISABLE KEYS;  -- Deshabilita las claves para la inserción
INSERT INTO `segment` VALUES
('AA','MIA','BUE',1,0,NULL,'2024-01-01','2023-12-31'),
('AA','MIA','BUE',2,0,NULL,'2024-01-01','2023-12-31'),
('AA','MIA','BUE',3,0,2,'2024-01-01','2023-12-31'),
('AA','MIA','BUE',4,0,NULL,'2024-01-01','2023-12-31'),
('AA','MIA','BUE',5,0,NULL,'2024-01-01','2023-12-31'),
('AA','MIA','BUE',6,0,1,'2024-01-01','2023-12-31'),
('AA','MIA','BUE',7,0,3,'2024-01-01','2023-12-31'),
('AA','MIA','BUE',8,0,4,'2024-01-01','2023-12-31'),
('AA','MIA','BUE',9,0,5,'2024-01-01','2023-12-31');  -- Inserta 9 segmentos de vuelo
ALTER TABLE `segment` ENABLE KEYS;  -- Habilita nuevamente las claves
UNLOCK TABLES;  -- Desbloquea la tabla

-- Restauración de las configuraciones originales.
SET TIME_ZONE=@OLD_TIME_ZONE;
SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT;
SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS;
SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION;
SET SQL_NOTES=@OLD_SQL_NOTES;

-- Fin del volcado de la base de datos.
