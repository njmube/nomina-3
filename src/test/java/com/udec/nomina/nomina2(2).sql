-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 15-02-2014 a las 23:08:29
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `nomina2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `banco`
--

CREATE TABLE IF NOT EXISTS `banco` (
  `idbanco` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(45) DEFAULT NULL,
  `banco` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idbanco`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `banco`
--

INSERT INTO `banco` (`idbanco`, `codigo`, `banco`) VALUES
(1, 'B101', 'BANCO DE LA REPUBLICA'),
(2, 'B102', 'BANCO AVVILLAS'),
(3, 'B103', 'BANCOLOMBIA S.A'),
(4, 'B104', 'BANCO SUDAMERIS'),
(5, 'B105', 'BANCO DE LAS MARGARITAS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cargo`
--

CREATE TABLE IF NOT EXISTS `cargo` (
  `cargoid` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(45) DEFAULT NULL,
  `cargo` varchar(100) DEFAULT NULL,
  `funciones` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`cargoid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Volcado de datos para la tabla `cargo`
--

INSERT INTO `cargo` (`cargoid`, `codigo`, `cargo`, `funciones`) VALUES
(1, 'C101', 'JEFE DE PRODUCCION', 'PRODUCIR'),
(2, 'C102', 'LINGUISTA', 'ESCRIBIR Y REVISAR'),
(3, 'C103', 'PILOTO DE CARROS', 'MANEJAR CARROS DE FORMULA 1'),
(4, 'C103', 'INGENIERO DE SISTEMAS', 'HACER PROGRAMAS'),
(5, 'C0049', 'AUX. SERVICIOS GENERALES', 'HACER DE TODO UN POCO'),
(6, 'C0083', 'SECRETARIA EJECUTIVA', 'ESCRIBIR'),
(7, 'C0021', 'AUXILIAR DE MENSAJERIA', 'LLEVAR CARTAS'),
(8, 'C0062', 'JEFE DIVISION ADMINISTRATIVA', 'MANDAR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `concepto`
--

CREATE TABLE IF NOT EXISTS `concepto` (
  `idconcepto` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(45) DEFAULT NULL,
  `concepto` varchar(45) DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `formato` varchar(45) DEFAULT NULL,
  `general` varchar(45) DEFAULT NULL,
  `grupoconcepto_idgrupoconcepto` int(11) DEFAULT NULL,
  PRIMARY KEY (`idconcepto`),
  KEY `fk_concepto_grupoconcepto1` (`grupoconcepto_idgrupoconcepto`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17 ;

--
-- Volcado de datos para la tabla `concepto`
--

INSERT INTO `concepto` (`idconcepto`, `codigo`, `concepto`, `tipo`, `formato`, `general`, `grupoconcepto_idgrupoconcepto`) VALUES
(1, 'DEV001', 'SUELDO DEVENGADO', 'DEVENGADO', 'ESTANDAR', 'SI', NULL),
(2, 'DEV002', 'AUXILIO DE TRANSPORTE', 'DEVENGADO', 'ESTANDAR', 'SI', NULL),
(3, 'DEV003', 'SUBSIDIO DE ALIMENTACIÓN', 'DEVENGADO', 'ESTANDAR', 'SI', NULL),
(4, 'DEV004', 'LIC/INCA', 'DEVENGADO', 'ESTANDAR', 'SI', NULL),
(5, 'DED001', 'SEGURIDAD SOCIAL', 'DEDUCIDO', 'ESTANDAR', 'SI', NULL),
(6, 'DED002', 'RETEFUENTE', 'DEDUCIDO', 'ESTANDAR', 'SI', NULL),
(7, 'DED008', 'RECORDAR', 'DEDUCIDO', 'ESTANDAR', 'NO', NULL),
(8, 'DED007', 'AMI', 'DEDUCIDO', 'ESTANDAR', 'NO', NULL),
(9, 'DED006', 'OTROS PAGARES', 'DEDUCIDO', 'ESTANDAR', 'NO', NULL),
(10, 'DED101', 'SEGUROS DEL ESTADO', 'DEDUCIDO', 'ESTANDAR', 'NO', 2),
(11, 'DED102', 'SEGUROS BOLIVAR', 'DEDUCIDO', 'ESTANDAR', 'NO', 2),
(12, 'DED004', 'FONDO EMPLEADOS', 'DEDUCIDO', 'ESTANDAR', 'NO', NULL),
(13, 'DED003', 'LIBRANZAS', 'DEDUCIDO', 'LIBRANZA', 'NO', 1),
(14, 'DED201', 'SALUD', 'DEDUCIDO', 'ESTANDAR', 'SI', 3),
(15, 'DED202', 'PENSION', 'DEDUCIDO', 'ESTANDAR', 'SI', 3),
(16, 'DED203', 'FSP', 'DEDUCIDO', 'ESTANDAR', 'SI', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE IF NOT EXISTS `empleado` (
  `codigo` int(11) NOT NULL,
  `cedula` varchar(45) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `salario` float DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `banco_idbanco` int(11) DEFAULT NULL,
  `numero_cuenta` varchar(45) DEFAULT NULL,
  `cargo_cargoid` int(11) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `fk_empleado_banco1_idx` (`banco_idbanco`),
  KEY `fk_empleado_cargo1_idx` (`cargo_cargoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`codigo`, `cedula`, `nombre`, `salario`, `tipo`, `estado`, `banco_idbanco`, `numero_cuenta`, `cargo_cargoid`) VALUES
(45654, '987123456', 'DEWIS ROMERO', 1600000, 'PLANTA', 'ACTIVO', 3, '887957-5656', 4),
(87565, '73214802', 'OSCAR BALLESTEROS', 3000000, 'CONTRATO', 'ACTIVO', 3, '78659877', 4),
(123456, '73214585', 'DIANA PADILLA', 1300000, 'PLANTA', 'ACTIVO', 1, '54585654125', 2),
(654123, '2299965', 'JUAN PABLO MONTOYA', 50000000, 'PLANTA', 'ACTIVO', 2, '789456321', 3),
(30761780, '30761780', 'ACEVEDO DE ACEVEDO MARIA E', 832599, 'PLANTA', 'ACTIVO', 2, '8888888', 5),
(45495621, '45495621', 'ACOSTA MANOSALVA GLORIA', 1622520, 'CONTRATO', 'ACTIVO', 2, '9999999', 6),
(73129072, '73129072', 'BERROCAL CASTILLA EDGAR IG', 4694880, 'PLANTA', 'ACTIVO', 2, '98867584', 8),
(1047416719, '1047416719', 'ARZUZA GONZALEZ JESSY ENRIQUE', 915861, 'PLANTA', 'ACTIVO', 2, '8795746', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupoconcepto`
--

CREATE TABLE IF NOT EXISTS `grupoconcepto` (
  `idgrupoconcepto` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(45) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idgrupoconcepto`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `grupoconcepto`
--

INSERT INTO `grupoconcepto` (`idgrupoconcepto`, `codigo`, `nombre`) VALUES
(1, 'G101', 'BANCOS'),
(2, 'G102', 'SEGUROS'),
(3, 'G103', 'SEGURIDAD SOCIAL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nomina`
--

CREATE TABLE IF NOT EXISTS `nomina` (
  `idnomina` int(11) NOT NULL,
  `valor` float DEFAULT NULL,
  `periodo_idperiodo` int(11) NOT NULL,
  `concepto_idconcepto` int(11) NOT NULL,
  `empleado_codigo` int(11) NOT NULL,
  PRIMARY KEY (`idnomina`),
  KEY `fk_nomina_periodo1_idx` (`periodo_idperiodo`),
  KEY `fk_nomina_concepto1_idx` (`concepto_idconcepto`),
  KEY `fk_nomina_empleado1_idx` (`empleado_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `novedadmedic`
--

CREATE TABLE IF NOT EXISTS `novedadmedic` (
  `idnovedadmedic` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_final` date DEFAULT NULL,
  `tipo` varchar(100) DEFAULT NULL,
  `empleado_codigo` int(11) NOT NULL,
  PRIMARY KEY (`idnovedadmedic`),
  KEY `fk_novedadmedic_empleado1_idx` (`empleado_codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `novedadmedic`
--

INSERT INTO `novedadmedic` (`idnovedadmedic`, `fecha_inicio`, `fecha_final`, `tipo`, `empleado_codigo`) VALUES
(1, '2014-01-16', '2014-01-18', 'incapacidad general', 123456),
(3, '2014-01-19', '2014-01-20', 'incapacidad general', 654123),
(4, '2014-02-05', '2014-02-07', 'incapacidad general', 654123);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `novedadxconcepto`
--

CREATE TABLE IF NOT EXISTS `novedadxconcepto` (
  `idnovedad` int(11) NOT NULL AUTO_INCREMENT,
  `valor` float DEFAULT NULL,
  `saldo` float DEFAULT NULL,
  `tipo_saldo` varchar(45) DEFAULT NULL,
  `aplicar_quincenal` varchar(45) DEFAULT NULL,
  `total_libranza` float DEFAULT NULL,
  `numero_cuotas` int(11) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `concepto_idconcepto` int(11) NOT NULL,
  `banco_idbanco` int(11) DEFAULT NULL,
  `empleado_codigo` int(11) NOT NULL,
  PRIMARY KEY (`idnovedad`),
  KEY `fk_novedadxconcepto_concepto1_idx` (`concepto_idconcepto`),
  KEY `fk_novedadxconcepto_banco1_idx` (`banco_idbanco`),
  KEY `fk_novedadxconcepto_empleado1_idx` (`empleado_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parametros_generales`
--

CREATE TABLE IF NOT EXISTS `parametros_generales` (
  `salario_minimo` float DEFAULT NULL,
  `subsidio_transporte` float DEFAULT NULL,
  `subsidio_alimentacion` float DEFAULT NULL,
  `porcentaje_salud` float DEFAULT NULL,
  `porcentaje_pension` float DEFAULT NULL,
  `porcentaje_fps` float DEFAULT NULL,
  `id_parametrosgenerales` int(11) NOT NULL,
  PRIMARY KEY (`id_parametrosgenerales`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `parametros_generales`
--

INSERT INTO `parametros_generales` (`salario_minimo`, `subsidio_transporte`, `subsidio_alimentacion`, `porcentaje_salud`, `porcentaje_pension`, `porcentaje_fps`, `id_parametrosgenerales`) VALUES
(566700, 67800, 44655, 4, 4, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `periodo`
--

CREATE TABLE IF NOT EXISTS `periodo` (
  `idperiodo` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `mes` int(11) DEFAULT NULL,
  `anio` int(11) DEFAULT NULL,
  `quincena` int(11) DEFAULT NULL,
  `desde` date DEFAULT NULL,
  `hasta` date DEFAULT NULL,
  `actual` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idperiodo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `concepto`
--
ALTER TABLE `concepto`
  ADD CONSTRAINT `fk_concepto_grupoconcepto1` FOREIGN KEY (`grupoconcepto_idgrupoconcepto`) REFERENCES `grupoconcepto` (`idgrupoconcepto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `fk_empleado_banco1` FOREIGN KEY (`banco_idbanco`) REFERENCES `banco` (`idbanco`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_empleado_cargo1` FOREIGN KEY (`cargo_cargoid`) REFERENCES `cargo` (`cargoid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `nomina`
--
ALTER TABLE `nomina`
  ADD CONSTRAINT `fk_nomina_periodo1` FOREIGN KEY (`periodo_idperiodo`) REFERENCES `periodo` (`idperiodo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_nomina_concepto1` FOREIGN KEY (`concepto_idconcepto`) REFERENCES `concepto` (`idconcepto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_nomina_empleado1` FOREIGN KEY (`empleado_codigo`) REFERENCES `empleado` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `novedadmedic`
--
ALTER TABLE `novedadmedic`
  ADD CONSTRAINT `fk_novedadmedic_empleado1` FOREIGN KEY (`empleado_codigo`) REFERENCES `empleado` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `novedadxconcepto`
--
ALTER TABLE `novedadxconcepto`
  ADD CONSTRAINT `fk_novedadxconcepto_concepto1` FOREIGN KEY (`concepto_idconcepto`) REFERENCES `concepto` (`idconcepto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_novedadxconcepto_banco1` FOREIGN KEY (`banco_idbanco`) REFERENCES `banco` (`idbanco`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_novedadxconcepto_empleado1` FOREIGN KEY (`empleado_codigo`) REFERENCES `empleado` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
