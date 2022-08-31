-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-08-2022 a las 05:21:01
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `default_server`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aseguradora`
--

CREATE TABLE `aseguradora` (
  `id` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE `estado` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `server`
--

CREATE TABLE `server` (
  `id` varchar(3) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `username` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `passwd` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `aseguradora` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `poliza` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `vencimiento` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `conPalOC` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `conPalRem` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `conPalMan` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `conNumOC` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `conNumRem` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `conNumMan` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `avisoconNumOC` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `avisoconNumRem` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `avisoconNumMan` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `maxconNumOC` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `maxconNumRem` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `maxconNumMan` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `activarDireccionesLibres` varchar(255) COLLATE latin1_spanish_ci DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `identidad` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `username` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `pass` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `estado` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `tipo_usuario` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `server` varchar(1) COLLATE latin1_spanish_ci NOT NULL,
  `dateLog` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `usuarios`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `usuarios` (
`Usuario` varchar(255)
,`Nombre` varchar(255)
,`Empresa` varchar(255)
,`Tipo de usuario` varchar(255)
,`Identificación` varchar(255)
,`Creac/Modfic` timestamp
);

-- --------------------------------------------------------

--
-- Estructura para la vista `usuarios`
--
DROP TABLE IF EXISTS `usuarios`;

CREATE ALGORITHM=UNDEFINED DEFINER=`enerfrisoft`@`%` SQL SECURITY DEFINER VIEW `usuarios`  AS SELECT `usuario`.`username` AS `Usuario`, `usuario`.`nombre` AS `Nombre`, `server`.`nombre` AS `Empresa`, `usuario`.`tipo_usuario` AS `Tipo de usuario`, `usuario`.`identidad` AS `Identificación`, `usuario`.`dateLog` AS `Creac/Modfic` FROM (`usuario` join `server` on(`usuario`.`server` = `server`.`id`)) WHERE `usuario`.`estado` = 'activo''activo'  ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `aseguradora`
--
ALTER TABLE `aseguradora`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `server`
--
ALTER TABLE `server`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`identidad`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `estado` (`estado`),
  ADD KEY `tipo_usuario` (`tipo_usuario`),
  ADD KEY `server` (`server`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`estado`) REFERENCES `estado` (`nombre`),
  ADD CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`tipo_usuario`) REFERENCES `tipo_usuario` (`nombre`),
  ADD CONSTRAINT `usuario_ibfk_3` FOREIGN KEY (`server`) REFERENCES `server` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
