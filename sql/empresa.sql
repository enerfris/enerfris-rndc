-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-08-2022 a las 05:22:29
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
-- Base de datos: `empresa`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `anular_manifiesto`
--

CREATE TABLE `anular_manifiesto` (
  `NUMMANIFIESTOCARGA` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `MOTIVOANULACIONMANIFIESTO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `OBSERVACIONES` varchar(2000) COLLATE latin1_spanish_ci DEFAULT NULL,
  `userLog` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `dateLog` timestamp NOT NULL DEFAULT current_timestamp(),
  `estado` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ingresoId` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `anular_remesa`
--

CREATE TABLE `anular_remesa` (
  `CONSECUTIVOREMESA` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `MOTIVOANULACIONREMESA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `MOTIVOREVERSAREMESA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODMUNICIPIOTRANSBORDO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `MOTIVOTRANSBORDOREMESA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `OBSERVACIONES` varchar(2000) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ingresoId` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estado` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `userLog` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `dateLog` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

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
-- Estructura de tabla para la tabla `categoria_licencia`
--

CREATE TABLE `categoria_licencia` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clasificacion_producto`
--

CREATE TABLE `clasificacion_producto` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `naturaleza` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuracion_vehiculo`
--

CREATE TABLE `configuracion_vehiculo` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `cod` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `descripcion` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `tipo_vehiculo` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `error`
--

CREATE TABLE `error` (
  `ID` int(11) NOT NULL,
  `CLASE` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `METODO` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `LINEA` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `MENSAJE` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `TIEMPO` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
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
-- Estructura de tabla para la tabla `info_empresa`
--

CREATE TABLE `info_empresa` (
  `id` int(11) NOT NULL,
  `serverId` varchar(2) COLLATE latin1_spanish_ci NOT NULL,
  `nit` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `usuario` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `nombre_principal` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `nombreCorto` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `direccion_principal` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `direccion` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `telefono_principal` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `telefono` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `municipio` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `municipio_principal` varchar(30) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `licencia`
--

CREATE TABLE `licencia` (
  `id` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `vencimiento` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `categoria_licencia` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `linea_vehiculo`
--

CREATE TABLE `linea_vehiculo` (
  `cod_marca` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `nombre_marca` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `cod_linea` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `nombre_linea` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `manifiesto`
--

CREATE TABLE `manifiesto` (
  `NUMMANIFIESTOCARGA` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `NUMORDENCARGUE` varchar(30) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CONSECUTIVOINFORMACIONVIAJE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `MANNROMANIFIESTOTRANSBORDO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODOPERACIONTRANSPORTE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `FECHAEXPEDICIONMANIFIESTO` date DEFAULT NULL,
  `CODMUNICIPIOORIGENMANIFIESTO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODMUNICIPIODESTINOMANIFIESTO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODIDTITULARMANIFIESTO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMIDTITULARMANIFIESTO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODSEDETITULARMANIFIESTO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMPLACA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODCONFIGURACIONRESULTANTE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMPLACAREMOLQUE1` varchar(255) COLLATE latin1_spanish_ci DEFAULT '',
  `PESOVEHICULOVACIOREMOLQUE1` varchar(255) COLLATE latin1_spanish_ci DEFAULT '0',
  `NUMPLACAREMOLQUE2` varchar(255) COLLATE latin1_spanish_ci DEFAULT '',
  `PESOVEHICULOVACIOREMOLQUE2` varchar(255) COLLATE latin1_spanish_ci DEFAULT '0',
  `CODIDCONDUCTOR` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMIDCONDUCTOR` varchar(255) COLLATE latin1_spanish_ci DEFAULT '',
  `CODIDCONDUCTOR2` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMIDCONDUCTOR2` varchar(255) COLLATE latin1_spanish_ci DEFAULT '',
  `VALORFLETEPACTADOVIAJE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `VALORFLETEPACTADOVIAJELETRAS` varchar(1000) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ORETENCIONFUENTEMANIFIEST` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `RETENCIONICAMANIFIESTOCARGA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `RETENCIONICAMANIFIESTOCARGAVALOR` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `VALORANTICIPOMANIFIESTO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `SALDOAPAGAR` varchar(25) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NETOAPAGAR` varchar(25) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODMUNICIPIOPAGOSALDO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODRESPONSABLEPAGOCARGUE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODRESPONSABLEPAGODESCARGUE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `FECHAPAGOSALDOMANIFIESTO` date DEFAULT NULL,
  `OBSERVACIONES` varchar(2000) COLLATE latin1_spanish_ci DEFAULT NULL,
  `SEGURIDADQR` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ingresoId` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `userLog` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `dateLog` timestamp NOT NULL DEFAULT current_timestamp(),
  `estado` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `manifiestos`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `manifiestos` (
`CONSEC` varchar(255)
,`ORDENC` varchar(30)
,`OPERAC` varchar(255)
,`EXPED` date
,`DESTINO` varchar(255)
,`IDEN_COND` varchar(255)
,`CONDUCTOR` text
,`PLACA` varchar(255)
,`VALOR_VIAJE` varchar(256)
,`ANTICIPO` varchar(256)
,`CREADOR` varchar(255)
,`FECHA` timestamp
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `manifiestos_exportar`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `manifiestos_exportar` (
`CONSECUTIVO` varchar(255)
,`HORA_FECHA_REGISTRO` timestamp
,`CREADOR` varchar(255)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marca_semiremolque`
--

CREATE TABLE `marca_semiremolque` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `cod` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marca_vehiculo`
--

CREATE TABLE `marca_vehiculo` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `cod` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `motivo_anulacion_manifiesto`
--

CREATE TABLE `motivo_anulacion_manifiesto` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `cod` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `motivo_anulacion_remesa`
--

CREATE TABLE `motivo_anulacion_remesa` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `cod` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `municipio`
--

CREATE TABLE `municipio` (
  `id` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `naturaleza_carga`
--

CREATE TABLE `naturaleza_carga` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `cod` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nombre_producto`
--

CREATE TABLE `nombre_producto` (
  `cod` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `clasificacion` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(1000) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nomenclaturadirecciones`
--

CREATE TABLE `nomenclaturadirecciones` (
  `nombre` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `cod` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parametro_configuracion`
--

CREATE TABLE `parametro_configuracion` (
  `id` int(11) NOT NULL,
  `consecutivo_remesa` int(11) NOT NULL,
  `consecutivo_orden_carga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `remesa`
--

CREATE TABLE `remesa` (
  `CONSECUTIVOREMESA` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `CONSECUTIVOINFORMACIONCARGA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CONSECUTIVOCARGADIVIDIDA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODOPERACIONTRANSPORTE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODNATURALEZACARGA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CANTIDADCARGADA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `UNIDADMEDIDACAPACIDAD` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODTIPOEMPAQUE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `PESOCONTENEDORVACIO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `MERCANCIAREMESA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `DESCRIPCIONCORTAPRODUCTO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODTIPOIDREMITENTE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMIDREMITENTE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODSEDEREMITENTE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODTIPOIDDESTINATARIO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMIDDESTINATARIO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODSEDEDESTINATARIO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `DUENOPOLIZA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMPOLIZATRANSPORTE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `COMPANIASEGURO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `FECHAVENCIMIENTOPOLIZACARGA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `HORASPACTOCARGA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `MINUTOSPACTOCARGA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `HORASPACTODESCARGUE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `MINUTOSPACTODESCARGUE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `FECHALLEGADACARGUE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `HORALLEGADACARGUEREMESA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `FECHAENTRADACARGUE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `HORAENTRADACARGUEREMESA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `FECHASALIDACARGUE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `HORASALIDACARGUEREMESA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODTIPOIDPROPIETARIO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMIDPROPIETARIO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODSEDEPROPIETARIO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `FECHACITAPACTADACARGUE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `HORACITAPACTADACARGUE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `FECHACITAPACTADADESCARGUE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `HORACITAPACTADADESCARGUEREMESA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `PERMISOCARGAEXTRA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMIDGPS` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `OBSERVACIONES` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ingresoId` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `userLog` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `dateLog` timestamp NOT NULL DEFAULT current_timestamp(),
  `estado` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `manifiestoConsecutivo` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `remesas`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `remesas` (
`CONSEC` varchar(255)
,`TIPO_OPER` varchar(255)
,`NAT_CARGA` varchar(255)
,`CANTID` varchar(255)
,`UNIDAD_CAP` varchar(255)
,`TIPO_EMPA` varchar(255)
,`PRODUCTO` varchar(1000)
,`IDENT_REMIT` varchar(255)
,`SEDE_REMIT` varchar(255)
,`IDENT_DESTI` varchar(255)
,`SEDE_DESTI` varchar(255)
,`IDENT_PROPI` varchar(255)
,`SEDE_PROPI` varchar(255)
,`FECHA_CARGUE` varchar(255)
,`HORA_CARGUE` varchar(255)
,`FECHA_DESC` varchar(255)
,`HORA_DESC` varchar(255)
,`CREADOR` varchar(255)
,`FECHA` timestamp
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `remesas_agregar`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `remesas_agregar` (
`Remesa` varchar(255)
,`Codigo` varchar(255)
,`Producto` varchar(255)
,`Cantidad` varchar(255)
,`Unidad` varchar(255)
,`Naturaleza` varchar(255)
,`Empaque` varchar(255)
,`Remitente` varchar(255)
,`Destinatario` varchar(255)
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `remesas_exportar`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `remesas_exportar` (
`CONSECUTIVO` varchar(255)
,`HORA_FECHA_REGISTRO` timestamp
,`CREADOR` varchar(255)
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `remolque`
--

CREATE TABLE `remolque` (
  `NUMPLACA` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `CODCONFIGURACIONUNIDADCARGA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODMARCAVEHICULOCARGA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMEJES` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ANOFABRICACIONVEHICULOCARGA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `PESOVEHICULOVACIO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CAPACIDADUNIDADCARGA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `UNIDADMEDIDACAPACIDAD` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODTIPOCARROCERIA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMCHASIS` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMSEGUROSOAT` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `FECHAVENCIMIENTOSOAT` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMNITASEGURADORASOAT` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODTIPOIDPROPIETARIO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMIDPROPIETARIO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODTIPOIDTENEDOR` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMIDTENEDOR` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ingresoId` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `afiliacion` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `empresa` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `rntc` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `pbc` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `tarjeta_propiedad` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estado` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `userLog` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `dateLog` timestamp NOT NULL DEFAULT current_timestamp(),
  `observaciones` varchar(2000) COLLATE latin1_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `remolques`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `remolques` (
`PLACA` varchar(255)
,`MARCA` varchar(255)
,`IDENT_PROPIE` varchar(255)
,`IDENT_TENED` varchar(255)
,`CONFIG` varchar(255)
,`EJES` varchar(255)
,`MODELO` varchar(255)
,`P_VACIO` varchar(255)
,`CAP` varchar(255)
,`UNIDAD_CAP` varchar(255)
,`CARROCERIA` varchar(255)
,`CREADOR` varchar(255)
,`FECHA` timestamp
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `responsable_pago`
--

CREATE TABLE `responsable_pago` (
  `cod` varchar(2) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sede`
--

CREATE TABLE `sede` (
  `NUMTELEFONOCONTACTO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMCELULARPERSONA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NOMENCLATURADIRECCION` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODMUNICIPIORNDC` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODSEDETERCERO` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `NOMSEDETERCERO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMIDTERCERO` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `userLog` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `dateLog` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tercero`
--

CREATE TABLE `tercero` (
  `CODTIPOIDTERCERO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMIDTERCERO` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `EXPEDICION` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NOMIDTERCERO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `PRIMERAPELLIDOIDTERCERO` varchar(255) COLLATE latin1_spanish_ci DEFAULT '',
  `SEGUNDOAPELLIDOIDTERCERO` varchar(255) COLLATE latin1_spanish_ci DEFAULT '',
  `NUMLICENCIACONDUCCION` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODCATEGORIALICENCIACONDUCCION` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `FECHAVENCIMIENTOLICENCIA` date DEFAULT NULL,
  `EMAIL` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ingresoId` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estado` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `userLog` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `infoAdicional` varchar(1000) COLLATE latin1_spanish_ci DEFAULT NULL,
  `dateLog` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `terceros`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `terceros` (
`TIPO` varchar(255)
,`ID` varchar(255)
,`EXPED` varchar(255)
,`NOMBRE` varchar(255)
,`APELLIDO1` varchar(255)
,`APELLIDO2` varchar(255)
,`TEL` varchar(255)
,`CEL` varchar(255)
,`COD_SEDE` varchar(255)
,`SEDE` varchar(255)
,`DIRECCIÓN` varchar(255)
,`MUNICIPIO` varchar(255)
,`LICENCIA` varchar(255)
,`CAT` varchar(255)
,`VEN_LIC` date
,`EMAIL` varchar(255)
,`CREADOR` varchar(255)
,`FECHA` timestamp
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiempo_logistico`
--

CREATE TABLE `tiempo_logistico` (
  `id` int(11) NOT NULL,
  `fecha_hora_cargue` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `duracion_cargue` int(11) DEFAULT NULL,
  `fecha_hora_descargue` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `duracion_descargue` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_carroceria`
--

CREATE TABLE `tipo_carroceria` (
  `cod` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_combustible`
--

CREATE TABLE `tipo_combustible` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `cod` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_empaque`
--

CREATE TABLE `tipo_empaque` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `cod` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_id`
--

CREATE TABLE `tipo_id` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `cod` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_id_conductor`
--

CREATE TABLE `tipo_id_conductor` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `cod` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_manifiesto`
--

CREATE TABLE `tipo_manifiesto` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `cod` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_operacion`
--

CREATE TABLE `tipo_operacion` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `cod` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_tercero`
--

CREATE TABLE `tipo_tercero` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_vehiculo`
--

CREATE TABLE `tipo_vehiculo` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tomador_poliza`
--

CREATE TABLE `tomador_poliza` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `cod` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ubicacion`
--

CREATE TABLE `ubicacion` (
  `id` int(11) NOT NULL,
  `direccion` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `municipio` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `unidad_medida`
--

CREATE TABLE `unidad_medida` (
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `cod` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `NUMPLACA` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `CODCONFIGURACIONUNIDADCARGA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODMARCAVEHICULOCARGA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODLINEAVEHICULOCARGA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMEJES` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ANOFABRICACIONVEHICULOCARGA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ANOREPOTENCIACION` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODCOLORVEHICULOCARGA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `PESOVEHICULOVACIO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CAPACIDADUNIDADCARGA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `UNIDADMEDIDACAPACIDAD` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODTIPOCARROCERIA` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMCHASIS` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODTIPOCOMBUSTIBLE` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMSEGUROSOAT` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `FECHAVENCIMIENTOSOAT` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMNITASEGURADORASOAT` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODTIPOIDPROPIETARIO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMIDPROPIETARIO` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CODTIPOIDTENEDOR` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NUMIDTENEDOR` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ingresoId` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `afiliacion` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `empresa` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `rntc` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `pvb` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `serie_motor` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `tarjeta_propiedad` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estado` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `userLog` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `dateLog` timestamp NOT NULL DEFAULT current_timestamp(),
  `observaciones` varchar(2000) COLLATE latin1_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `vehiculos`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `vehiculos` (
`PLACA` varchar(255)
,`MARCA` varchar(255)
,`LINEA` varchar(255)
,`IDENT_PROPIE` varchar(255)
,`IDENT_TENED` varchar(255)
,`EJES` varchar(255)
,`MODELO` varchar(255)
,`COLOR` varchar(255)
,`CAP` varchar(255)
,`UNIDAD_CAP` varchar(255)
,`CARROCERIA` varchar(255)
,`SOAT` varchar(255)
,`VEN_SOAT` varchar(255)
,`CREADOR` varchar(255)
,`FECHA` timestamp
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo_color`
--

CREATE TABLE `vehiculo_color` (
  `cod` varchar(255) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(255) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura para la vista `manifiestos`
--
DROP TABLE IF EXISTS `manifiestos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`enerfrisoft`@`%` SQL SECURITY DEFINER VIEW `manifiestos`  AS SELECT `manifiesto`.`NUMMANIFIESTOCARGA` AS `CONSEC`, `manifiesto`.`NUMORDENCARGUE` AS `ORDENC`, `tipo_manifiesto`.`nombre` AS `OPERAC`, `manifiesto`.`FECHAEXPEDICIONMANIFIESTO` AS `EXPED`, `municipio`.`nombre` AS `DESTINO`, `tercero`.`NUMIDTERCERO` AS `IDEN_COND`, concat(`tercero`.`NOMIDTERCERO`,' ',`tercero`.`PRIMERAPELLIDOIDTERCERO`,' ',`tercero`.`SEGUNDOAPELLIDOIDTERCERO`) AS `CONDUCTOR`, `manifiesto`.`NUMPLACA` AS `PLACA`, concat('$',`manifiesto`.`VALORFLETEPACTADOVIAJE`) AS `VALOR_VIAJE`, concat('$',`manifiesto`.`VALORANTICIPOMANIFIESTO`) AS `ANTICIPO`, `manifiesto`.`userLog` AS `CREADOR`, `manifiesto`.`dateLog` AS `FECHA` FROM (((`manifiesto` join `tipo_manifiesto` on(`manifiesto`.`CODOPERACIONTRANSPORTE` = `tipo_manifiesto`.`cod`)) join `municipio` on(`manifiesto`.`CODMUNICIPIODESTINOMANIFIESTO` = `municipio`.`id`)) join `tercero` on(`manifiesto`.`NUMIDCONDUCTOR` = `tercero`.`NUMIDTERCERO`)) WHERE `manifiesto`.`estado` = 'activo''activo'  ;

-- --------------------------------------------------------

--
-- Estructura para la vista `manifiestos_exportar`
--
DROP TABLE IF EXISTS `manifiestos_exportar`;

CREATE ALGORITHM=UNDEFINED DEFINER=`enerfrisoft`@`%` SQL SECURITY DEFINER VIEW `manifiestos_exportar`  AS SELECT `manifiesto`.`NUMMANIFIESTOCARGA` AS `CONSECUTIVO`, `manifiesto`.`dateLog` AS `HORA_FECHA_REGISTRO`, `manifiesto`.`userLog` AS `CREADOR` FROM `manifiesto` WHERE `manifiesto`.`NUMMANIFIESTOCARGA` is not null AND `manifiesto`.`NUMMANIFIESTOCARGA` <> '' AND `manifiesto`.`estado` = 'activo' ORDER BY `manifiesto`.`dateLog` AS `DESCdesc` ASC  ;

-- --------------------------------------------------------

--
-- Estructura para la vista `remesas`
--
DROP TABLE IF EXISTS `remesas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`enerfrisoft`@`%` SQL SECURITY DEFINER VIEW `remesas`  AS SELECT `remesa`.`CONSECUTIVOREMESA` AS `CONSEC`, `tipo_operacion`.`nombre` AS `TIPO_OPER`, `naturaleza_carga`.`nombre` AS `NAT_CARGA`, `remesa`.`CANTIDADCARGADA` AS `CANTID`, `unidad_medida`.`nombre` AS `UNIDAD_CAP`, `tipo_empaque`.`nombre` AS `TIPO_EMPA`, `nombre_producto`.`nombre` AS `PRODUCTO`, `remesa`.`NUMIDREMITENTE` AS `IDENT_REMIT`, `remesa`.`CODSEDEREMITENTE` AS `SEDE_REMIT`, `remesa`.`NUMIDDESTINATARIO` AS `IDENT_DESTI`, `remesa`.`CODSEDEDESTINATARIO` AS `SEDE_DESTI`, `remesa`.`NUMIDPROPIETARIO` AS `IDENT_PROPI`, `remesa`.`CODSEDEPROPIETARIO` AS `SEDE_PROPI`, `remesa`.`FECHACITAPACTADACARGUE` AS `FECHA_CARGUE`, `remesa`.`HORACITAPACTADACARGUE` AS `HORA_CARGUE`, `remesa`.`FECHACITAPACTADADESCARGUE` AS `FECHA_DESC`, `remesa`.`HORACITAPACTADADESCARGUEREMESA` AS `HORA_DESC`, `remesa`.`userLog` AS `CREADOR`, `remesa`.`dateLog` AS `FECHA` FROM (((((`tipo_operacion` join `remesa` on(`remesa`.`CODOPERACIONTRANSPORTE` = `tipo_operacion`.`cod`)) join `naturaleza_carga` on(`remesa`.`CODNATURALEZACARGA` = `naturaleza_carga`.`cod`)) join `unidad_medida` on(`remesa`.`UNIDADMEDIDACAPACIDAD` = `unidad_medida`.`cod`)) join `tipo_empaque` on(`remesa`.`CODTIPOEMPAQUE` = `tipo_empaque`.`cod`)) join `nombre_producto` on(`remesa`.`MERCANCIAREMESA` = `nombre_producto`.`cod`)) WHERE `remesa`.`estado` = 'activo''activo'  ;

-- --------------------------------------------------------

--
-- Estructura para la vista `remesas_agregar`
--
DROP TABLE IF EXISTS `remesas_agregar`;

CREATE ALGORITHM=UNDEFINED DEFINER=`enerfrisoft`@`%` SQL SECURITY DEFINER VIEW `remesas_agregar`  AS SELECT `remesa`.`CONSECUTIVOREMESA` AS `Remesa`, `remesa`.`MERCANCIAREMESA` AS `Codigo`, `remesa`.`DESCRIPCIONCORTAPRODUCTO` AS `Producto`, `remesa`.`CANTIDADCARGADA` AS `Cantidad`, `unidad_medida`.`nombre` AS `Unidad`, `naturaleza_carga`.`nombre` AS `Naturaleza`, `tipo_empaque`.`nombre` AS `Empaque`, `remesa`.`NUMIDREMITENTE` AS `Remitente`, `remesa`.`NUMIDDESTINATARIO` AS `Destinatario` FROM (((`remesa` join `unidad_medida` on(`remesa`.`UNIDADMEDIDACAPACIDAD` = `unidad_medida`.`cod`)) join `naturaleza_carga` on(`remesa`.`CODNATURALEZACARGA` = `naturaleza_carga`.`cod`)) join `tipo_empaque` on(`remesa`.`CODTIPOEMPAQUE` = `tipo_empaque`.`cod`)) WHERE `remesa`.`estado` = 'activo''activo'  ;

-- --------------------------------------------------------

--
-- Estructura para la vista `remesas_exportar`
--
DROP TABLE IF EXISTS `remesas_exportar`;

CREATE ALGORITHM=UNDEFINED DEFINER=`enerfrisoft`@`%` SQL SECURITY DEFINER VIEW `remesas_exportar`  AS SELECT `remesa`.`CONSECUTIVOREMESA` AS `CONSECUTIVO`, `remesa`.`dateLog` AS `HORA_FECHA_REGISTRO`, `remesa`.`userLog` AS `CREADOR` FROM `remesa` WHERE `remesa`.`manifiestoConsecutivo` is not null AND `remesa`.`estado` = 'activo' ORDER BY `remesa`.`dateLog` AS `DESCdesc` ASC  ;

-- --------------------------------------------------------

--
-- Estructura para la vista `remolques`
--
DROP TABLE IF EXISTS `remolques`;

CREATE ALGORITHM=UNDEFINED DEFINER=`enerfrisoft`@`%` SQL SECURITY DEFINER VIEW `remolques`  AS SELECT `remolque`.`NUMPLACA` AS `PLACA`, `marca_semiremolque`.`nombre` AS `MARCA`, `remolque`.`NUMIDPROPIETARIO` AS `IDENT_PROPIE`, `remolque`.`NUMIDTENEDOR` AS `IDENT_TENED`, `configuracion_vehiculo`.`descripcion` AS `CONFIG`, `remolque`.`NUMEJES` AS `EJES`, `remolque`.`ANOFABRICACIONVEHICULOCARGA` AS `MODELO`, `remolque`.`PESOVEHICULOVACIO` AS `P_VACIO`, `remolque`.`CAPACIDADUNIDADCARGA` AS `CAP`, `remolque`.`UNIDADMEDIDACAPACIDAD` AS `UNIDAD_CAP`, `tipo_carroceria`.`nombre` AS `CARROCERIA`, `remolque`.`userLog` AS `CREADOR`, `remolque`.`dateLog` AS `FECHA` FROM (((`remolque` join `marca_semiremolque` on(`remolque`.`CODMARCAVEHICULOCARGA` = `marca_semiremolque`.`cod`)) join `configuracion_vehiculo` on(`remolque`.`CODCONFIGURACIONUNIDADCARGA` = `configuracion_vehiculo`.`cod`)) join `tipo_carroceria` on(`remolque`.`CODTIPOCARROCERIA` = `tipo_carroceria`.`cod`)) WHERE `remolque`.`estado` = 'activo''activo'  ;

-- --------------------------------------------------------

--
-- Estructura para la vista `terceros`
--
DROP TABLE IF EXISTS `terceros`;

CREATE ALGORITHM=UNDEFINED DEFINER=`enerfrisoft`@`%` SQL SECURITY DEFINER VIEW `terceros`  AS SELECT `tipo_id`.`nombre` AS `TIPO`, `tercero`.`NUMIDTERCERO` AS `ID`, `tercero`.`EXPEDICION` AS `EXPED`, `tercero`.`NOMIDTERCERO` AS `NOMBRE`, `tercero`.`PRIMERAPELLIDOIDTERCERO` AS `APELLIDO1`, `tercero`.`SEGUNDOAPELLIDOIDTERCERO` AS `APELLIDO2`, `sede`.`NUMTELEFONOCONTACTO` AS `TEL`, `sede`.`NUMCELULARPERSONA` AS `CEL`, `sede`.`CODSEDETERCERO` AS `COD_SEDE`, `sede`.`NOMSEDETERCERO` AS `SEDE`, `sede`.`NOMENCLATURADIRECCION` AS `DIRECCIÓN`, `municipio`.`nombre` AS `MUNICIPIO`, `tercero`.`NUMLICENCIACONDUCCION` AS `LICENCIA`, `tercero`.`CODCATEGORIALICENCIACONDUCCION` AS `CAT`, `tercero`.`FECHAVENCIMIENTOLICENCIA` AS `VEN_LIC`, `tercero`.`EMAIL` AS `EMAIL`, `tercero`.`userLog` AS `CREADOR`, `sede`.`dateLog` AS `FECHA` FROM (((`tercero` join `tipo_id` on(`tercero`.`CODTIPOIDTERCERO` = `tipo_id`.`cod`)) join `sede` on(`tercero`.`NUMIDTERCERO` = `sede`.`NUMIDTERCERO`)) join `municipio` on(`sede`.`CODMUNICIPIORNDC` = `municipio`.`id`)) WHERE `tercero`.`estado` = 'activo''activo'  ;

-- --------------------------------------------------------

--
-- Estructura para la vista `vehiculos`
--
DROP TABLE IF EXISTS `vehiculos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`enerfrisoft`@`%` SQL SECURITY DEFINER VIEW `vehiculos`  AS SELECT `vehiculo`.`NUMPLACA` AS `PLACA`, `linea_vehiculo`.`nombre_marca` AS `MARCA`, `linea_vehiculo`.`nombre_linea` AS `LINEA`, `vehiculo`.`NUMIDPROPIETARIO` AS `IDENT_PROPIE`, `vehiculo`.`NUMIDTENEDOR` AS `IDENT_TENED`, `vehiculo`.`NUMEJES` AS `EJES`, `vehiculo`.`ANOFABRICACIONVEHICULOCARGA` AS `MODELO`, `vehiculo_color`.`nombre` AS `COLOR`, `vehiculo`.`CAPACIDADUNIDADCARGA` AS `CAP`, `vehiculo`.`UNIDADMEDIDACAPACIDAD` AS `UNIDAD_CAP`, `tipo_carroceria`.`nombre` AS `CARROCERIA`, `vehiculo`.`NUMSEGUROSOAT` AS `SOAT`, `vehiculo`.`FECHAVENCIMIENTOSOAT` AS `VEN_SOAT`, `vehiculo`.`userLog` AS `CREADOR`, `vehiculo`.`dateLog` AS `FECHA` FROM (((`vehiculo` join `linea_vehiculo` on(`vehiculo`.`CODLINEAVEHICULOCARGA` = `linea_vehiculo`.`cod_linea` and `vehiculo`.`CODMARCAVEHICULOCARGA` = `linea_vehiculo`.`cod_marca`)) join `vehiculo_color` on(`vehiculo`.`CODCOLORVEHICULOCARGA` = `vehiculo_color`.`cod`)) join `tipo_carroceria` on(`vehiculo`.`CODTIPOCARROCERIA` = `tipo_carroceria`.`cod`)) WHERE `vehiculo`.`estado` = 'activo''activo'  ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `anular_manifiesto`
--
ALTER TABLE `anular_manifiesto`
  ADD PRIMARY KEY (`NUMMANIFIESTOCARGA`),
  ADD KEY `estado` (`estado`),
  ADD KEY `MOTIVOANULACIONMANIFIESTO` (`MOTIVOANULACIONMANIFIESTO`);

--
-- Indices de la tabla `anular_remesa`
--
ALTER TABLE `anular_remesa`
  ADD PRIMARY KEY (`CONSECUTIVOREMESA`),
  ADD KEY `estado` (`estado`),
  ADD KEY `MOTIVOANULACIONREMESA` (`MOTIVOANULACIONREMESA`);

--
-- Indices de la tabla `aseguradora`
--
ALTER TABLE `aseguradora`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `categoria_licencia`
--
ALTER TABLE `categoria_licencia`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `clasificacion_producto`
--
ALTER TABLE `clasificacion_producto`
  ADD PRIMARY KEY (`nombre`),
  ADD KEY `naturaleza` (`naturaleza`);

--
-- Indices de la tabla `configuracion_vehiculo`
--
ALTER TABLE `configuracion_vehiculo`
  ADD PRIMARY KEY (`nombre`),
  ADD UNIQUE KEY `cod` (`cod`),
  ADD KEY `tipo_vehiculo` (`tipo_vehiculo`);

--
-- Indices de la tabla `error`
--
ALTER TABLE `error`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `info_empresa`
--
ALTER TABLE `info_empresa`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `licencia`
--
ALTER TABLE `licencia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoria_licencia` (`categoria_licencia`);

--
-- Indices de la tabla `linea_vehiculo`
--
ALTER TABLE `linea_vehiculo`
  ADD PRIMARY KEY (`cod_marca`,`cod_linea`);

--
-- Indices de la tabla `manifiesto`
--
ALTER TABLE `manifiesto`
  ADD PRIMARY KEY (`NUMMANIFIESTOCARGA`),
  ADD KEY `CODOPERACIONTRANSPORTE` (`CODOPERACIONTRANSPORTE`),
  ADD KEY `CODMUNICIPIOORIGENMANIFIESTO` (`CODMUNICIPIOORIGENMANIFIESTO`),
  ADD KEY `CODMUNICIPIODESTINOMANIFIESTO` (`CODMUNICIPIODESTINOMANIFIESTO`),
  ADD KEY `CODIDTITULARMANIFIESTO` (`CODIDTITULARMANIFIESTO`),
  ADD KEY `NUMIDTITULARMANIFIESTO` (`NUMIDTITULARMANIFIESTO`),
  ADD KEY `NUMPLACA` (`NUMPLACA`),
  ADD KEY `NUMPLACAREMOLQUE1` (`NUMPLACAREMOLQUE1`),
  ADD KEY `NUMPLACAREMOLQUE2` (`NUMPLACAREMOLQUE2`),
  ADD KEY `CODIDCONDUCTOR` (`CODIDCONDUCTOR`),
  ADD KEY `NUMIDCONDUCTOR` (`NUMIDCONDUCTOR`),
  ADD KEY `CODIDCONDUCTOR2` (`CODIDCONDUCTOR2`),
  ADD KEY `NUMIDCONDUCTOR2` (`NUMIDCONDUCTOR2`),
  ADD KEY `CODMUNICIPIOPAGOSALDO` (`CODMUNICIPIOPAGOSALDO`),
  ADD KEY `CODRESPONSABLEPAGOCARGUE` (`CODRESPONSABLEPAGOCARGUE`),
  ADD KEY `CODRESPONSABLEPAGODESCARGUE` (`CODRESPONSABLEPAGODESCARGUE`),
  ADD KEY `estado` (`estado`);

--
-- Indices de la tabla `marca_semiremolque`
--
ALTER TABLE `marca_semiremolque`
  ADD PRIMARY KEY (`nombre`),
  ADD UNIQUE KEY `cod` (`cod`);

--
-- Indices de la tabla `marca_vehiculo`
--
ALTER TABLE `marca_vehiculo`
  ADD PRIMARY KEY (`nombre`),
  ADD UNIQUE KEY `cod` (`cod`);

--
-- Indices de la tabla `motivo_anulacion_manifiesto`
--
ALTER TABLE `motivo_anulacion_manifiesto`
  ADD PRIMARY KEY (`cod`);

--
-- Indices de la tabla `motivo_anulacion_remesa`
--
ALTER TABLE `motivo_anulacion_remesa`
  ADD PRIMARY KEY (`cod`);

--
-- Indices de la tabla `municipio`
--
ALTER TABLE `municipio`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `naturaleza_carga`
--
ALTER TABLE `naturaleza_carga`
  ADD PRIMARY KEY (`cod`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `nombre_producto`
--
ALTER TABLE `nombre_producto`
  ADD PRIMARY KEY (`cod`),
  ADD KEY `clasificacion` (`clasificacion`);

--
-- Indices de la tabla `nomenclaturadirecciones`
--
ALTER TABLE `nomenclaturadirecciones`
  ADD PRIMARY KEY (`cod`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `parametro_configuracion`
--
ALTER TABLE `parametro_configuracion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `remesa`
--
ALTER TABLE `remesa`
  ADD PRIMARY KEY (`CONSECUTIVOREMESA`),
  ADD KEY `CODOPERACIONTRANSPORTE` (`CODOPERACIONTRANSPORTE`),
  ADD KEY `CODNATURALEZACARGA` (`CODNATURALEZACARGA`),
  ADD KEY `UNIDADMEDIDACAPACIDAD` (`UNIDADMEDIDACAPACIDAD`),
  ADD KEY `CODTIPOEMPAQUE` (`CODTIPOEMPAQUE`),
  ADD KEY `MERCANCIAREMESA` (`MERCANCIAREMESA`),
  ADD KEY `CODTIPOIDREMITENTE` (`CODTIPOIDREMITENTE`),
  ADD KEY `NUMIDREMITENTE` (`NUMIDREMITENTE`,`CODSEDEREMITENTE`),
  ADD KEY `CODTIPOIDDESTINATARIO` (`CODTIPOIDDESTINATARIO`),
  ADD KEY `NUMIDDESTINATARIO` (`NUMIDDESTINATARIO`,`CODSEDEDESTINATARIO`),
  ADD KEY `DUENOPOLIZA` (`DUENOPOLIZA`),
  ADD KEY `COMPANIASEGURO` (`COMPANIASEGURO`),
  ADD KEY `CODTIPOIDPROPIETARIO` (`CODTIPOIDPROPIETARIO`),
  ADD KEY `NUMIDPROPIETARIO` (`NUMIDPROPIETARIO`,`CODSEDEPROPIETARIO`),
  ADD KEY `estado` (`estado`),
  ADD KEY `manifiestoConsecutivo` (`manifiestoConsecutivo`);

--
-- Indices de la tabla `remolque`
--
ALTER TABLE `remolque`
  ADD PRIMARY KEY (`NUMPLACA`),
  ADD KEY `CODCONFIGURACIONUNIDADCARGA` (`CODCONFIGURACIONUNIDADCARGA`),
  ADD KEY `CODMARCAVEHICULOCARGA` (`CODMARCAVEHICULOCARGA`),
  ADD KEY `UNIDADMEDIDACAPACIDAD` (`UNIDADMEDIDACAPACIDAD`),
  ADD KEY `CODTIPOIDPROPIETARIO` (`CODTIPOIDPROPIETARIO`),
  ADD KEY `CODTIPOIDTENEDOR` (`CODTIPOIDTENEDOR`),
  ADD KEY `estado` (`estado`);

--
-- Indices de la tabla `responsable_pago`
--
ALTER TABLE `responsable_pago`
  ADD PRIMARY KEY (`cod`);

--
-- Indices de la tabla `sede`
--
ALTER TABLE `sede`
  ADD PRIMARY KEY (`NUMIDTERCERO`,`CODSEDETERCERO`),
  ADD KEY `CODMUNICIPIORNDC` (`CODMUNICIPIORNDC`);

--
-- Indices de la tabla `tercero`
--
ALTER TABLE `tercero`
  ADD PRIMARY KEY (`NUMIDTERCERO`),
  ADD KEY `EXPEDICION` (`EXPEDICION`),
  ADD KEY `CODTIPOIDTERCERO` (`CODTIPOIDTERCERO`),
  ADD KEY `estado` (`estado`);

--
-- Indices de la tabla `tiempo_logistico`
--
ALTER TABLE `tiempo_logistico`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipo_carroceria`
--
ALTER TABLE `tipo_carroceria`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `tipo_combustible`
--
ALTER TABLE `tipo_combustible`
  ADD PRIMARY KEY (`nombre`),
  ADD UNIQUE KEY `cod` (`cod`);

--
-- Indices de la tabla `tipo_empaque`
--
ALTER TABLE `tipo_empaque`
  ADD PRIMARY KEY (`cod`);

--
-- Indices de la tabla `tipo_id`
--
ALTER TABLE `tipo_id`
  ADD PRIMARY KEY (`cod`);

--
-- Indices de la tabla `tipo_id_conductor`
--
ALTER TABLE `tipo_id_conductor`
  ADD PRIMARY KEY (`cod`);

--
-- Indices de la tabla `tipo_manifiesto`
--
ALTER TABLE `tipo_manifiesto`
  ADD PRIMARY KEY (`nombre`),
  ADD UNIQUE KEY `cod` (`cod`);

--
-- Indices de la tabla `tipo_operacion`
--
ALTER TABLE `tipo_operacion`
  ADD PRIMARY KEY (`cod`);

--
-- Indices de la tabla `tipo_tercero`
--
ALTER TABLE `tipo_tercero`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `tipo_vehiculo`
--
ALTER TABLE `tipo_vehiculo`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `tomador_poliza`
--
ALTER TABLE `tomador_poliza`
  ADD PRIMARY KEY (`cod`);

--
-- Indices de la tabla `ubicacion`
--
ALTER TABLE `ubicacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `municipio` (`municipio`);

--
-- Indices de la tabla `unidad_medida`
--
ALTER TABLE `unidad_medida`
  ADD PRIMARY KEY (`nombre`),
  ADD UNIQUE KEY `cod` (`cod`);

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`NUMPLACA`),
  ADD KEY `CODCONFIGURACIONUNIDADCARGA` (`CODCONFIGURACIONUNIDADCARGA`),
  ADD KEY `CODMARCAVEHICULOCARGA` (`CODMARCAVEHICULOCARGA`,`CODLINEAVEHICULOCARGA`),
  ADD KEY `CODCOLORVEHICULOCARGA` (`CODCOLORVEHICULOCARGA`),
  ADD KEY `UNIDADMEDIDACAPACIDAD` (`UNIDADMEDIDACAPACIDAD`),
  ADD KEY `CODTIPOCOMBUSTIBLE` (`CODTIPOCOMBUSTIBLE`),
  ADD KEY `NUMNITASEGURADORASOAT` (`NUMNITASEGURADORASOAT`),
  ADD KEY `CODTIPOIDPROPIETARIO` (`CODTIPOIDPROPIETARIO`),
  ADD KEY `CODTIPOIDTENEDOR` (`CODTIPOIDTENEDOR`),
  ADD KEY `estado` (`estado`);

--
-- Indices de la tabla `vehiculo_color`
--
ALTER TABLE `vehiculo_color`
  ADD PRIMARY KEY (`cod`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `error`
--
ALTER TABLE `error`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `info_empresa`
--
ALTER TABLE `info_empresa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `nomenclaturadirecciones`
--
ALTER TABLE `nomenclaturadirecciones`
  MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `parametro_configuracion`
--
ALTER TABLE `parametro_configuracion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tiempo_logistico`
--
ALTER TABLE `tiempo_logistico`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ubicacion`
--
ALTER TABLE `ubicacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `anular_manifiesto`
--
ALTER TABLE `anular_manifiesto`
  ADD CONSTRAINT `anular_manifiesto_ibfk_1` FOREIGN KEY (`estado`) REFERENCES `estado` (`nombre`),
  ADD CONSTRAINT `anular_manifiesto_ibfk_2` FOREIGN KEY (`MOTIVOANULACIONMANIFIESTO`) REFERENCES `motivo_anulacion_manifiesto` (`cod`);

--
-- Filtros para la tabla `anular_remesa`
--
ALTER TABLE `anular_remesa`
  ADD CONSTRAINT `anular_remesa_ibfk_1` FOREIGN KEY (`estado`) REFERENCES `estado` (`nombre`),
  ADD CONSTRAINT `anular_remesa_ibfk_2` FOREIGN KEY (`MOTIVOANULACIONREMESA`) REFERENCES `motivo_anulacion_remesa` (`cod`);

--
-- Filtros para la tabla `clasificacion_producto`
--
ALTER TABLE `clasificacion_producto`
  ADD CONSTRAINT `clasificacion_producto_ibfk_1` FOREIGN KEY (`naturaleza`) REFERENCES `naturaleza_carga` (`nombre`);

--
-- Filtros para la tabla `configuracion_vehiculo`
--
ALTER TABLE `configuracion_vehiculo`
  ADD CONSTRAINT `configuracion_vehiculo_ibfk_1` FOREIGN KEY (`tipo_vehiculo`) REFERENCES `tipo_vehiculo` (`nombre`);

--
-- Filtros para la tabla `licencia`
--
ALTER TABLE `licencia`
  ADD CONSTRAINT `licencia_ibfk_1` FOREIGN KEY (`categoria_licencia`) REFERENCES `categoria_licencia` (`nombre`);

--
-- Filtros para la tabla `linea_vehiculo`
--
ALTER TABLE `linea_vehiculo`
  ADD CONSTRAINT `linea_vehiculo_ibfk_1` FOREIGN KEY (`cod_marca`) REFERENCES `marca_vehiculo` (`cod`);

--
-- Filtros para la tabla `manifiesto`
--
ALTER TABLE `manifiesto`
  ADD CONSTRAINT `manifiesto_ibfk_1` FOREIGN KEY (`CODOPERACIONTRANSPORTE`) REFERENCES `tipo_manifiesto` (`cod`),
  ADD CONSTRAINT `manifiesto_ibfk_10` FOREIGN KEY (`NUMIDCONDUCTOR`) REFERENCES `tercero` (`NUMIDTERCERO`),
  ADD CONSTRAINT `manifiesto_ibfk_11` FOREIGN KEY (`CODIDCONDUCTOR2`) REFERENCES `tipo_id` (`cod`),
  ADD CONSTRAINT `manifiesto_ibfk_12` FOREIGN KEY (`NUMIDCONDUCTOR2`) REFERENCES `tercero` (`NUMIDTERCERO`),
  ADD CONSTRAINT `manifiesto_ibfk_13` FOREIGN KEY (`CODMUNICIPIOPAGOSALDO`) REFERENCES `municipio` (`id`),
  ADD CONSTRAINT `manifiesto_ibfk_14` FOREIGN KEY (`CODRESPONSABLEPAGOCARGUE`) REFERENCES `responsable_pago` (`cod`),
  ADD CONSTRAINT `manifiesto_ibfk_15` FOREIGN KEY (`CODRESPONSABLEPAGODESCARGUE`) REFERENCES `responsable_pago` (`cod`),
  ADD CONSTRAINT `manifiesto_ibfk_16` FOREIGN KEY (`estado`) REFERENCES `estado` (`nombre`),
  ADD CONSTRAINT `manifiesto_ibfk_2` FOREIGN KEY (`CODMUNICIPIOORIGENMANIFIESTO`) REFERENCES `municipio` (`id`),
  ADD CONSTRAINT `manifiesto_ibfk_3` FOREIGN KEY (`CODMUNICIPIODESTINOMANIFIESTO`) REFERENCES `municipio` (`id`),
  ADD CONSTRAINT `manifiesto_ibfk_4` FOREIGN KEY (`CODIDTITULARMANIFIESTO`) REFERENCES `tipo_id` (`cod`),
  ADD CONSTRAINT `manifiesto_ibfk_5` FOREIGN KEY (`NUMIDTITULARMANIFIESTO`) REFERENCES `tercero` (`NUMIDTERCERO`),
  ADD CONSTRAINT `manifiesto_ibfk_6` FOREIGN KEY (`NUMPLACA`) REFERENCES `vehiculo` (`NUMPLACA`),
  ADD CONSTRAINT `manifiesto_ibfk_7` FOREIGN KEY (`NUMPLACAREMOLQUE1`) REFERENCES `remolque` (`NUMPLACA`),
  ADD CONSTRAINT `manifiesto_ibfk_8` FOREIGN KEY (`NUMPLACAREMOLQUE2`) REFERENCES `remolque` (`NUMPLACA`),
  ADD CONSTRAINT `manifiesto_ibfk_9` FOREIGN KEY (`CODIDCONDUCTOR`) REFERENCES `tipo_id` (`cod`);

--
-- Filtros para la tabla `nombre_producto`
--
ALTER TABLE `nombre_producto`
  ADD CONSTRAINT `nombre_producto_ibfk_1` FOREIGN KEY (`clasificacion`) REFERENCES `clasificacion_producto` (`nombre`);

--
-- Filtros para la tabla `remesa`
--
ALTER TABLE `remesa`
  ADD CONSTRAINT `remesa_ibfk_1` FOREIGN KEY (`CODOPERACIONTRANSPORTE`) REFERENCES `tipo_operacion` (`cod`),
  ADD CONSTRAINT `remesa_ibfk_10` FOREIGN KEY (`DUENOPOLIZA`) REFERENCES `tomador_poliza` (`cod`),
  ADD CONSTRAINT `remesa_ibfk_11` FOREIGN KEY (`COMPANIASEGURO`) REFERENCES `aseguradora` (`id`),
  ADD CONSTRAINT `remesa_ibfk_12` FOREIGN KEY (`CODTIPOIDPROPIETARIO`) REFERENCES `tipo_id` (`cod`),
  ADD CONSTRAINT `remesa_ibfk_13` FOREIGN KEY (`NUMIDPROPIETARIO`,`CODSEDEPROPIETARIO`) REFERENCES `sede` (`NUMIDTERCERO`, `CODSEDETERCERO`),
  ADD CONSTRAINT `remesa_ibfk_14` FOREIGN KEY (`estado`) REFERENCES `estado` (`nombre`),
  ADD CONSTRAINT `remesa_ibfk_15` FOREIGN KEY (`manifiestoConsecutivo`) REFERENCES `manifiesto` (`NUMMANIFIESTOCARGA`),
  ADD CONSTRAINT `remesa_ibfk_2` FOREIGN KEY (`CODNATURALEZACARGA`) REFERENCES `naturaleza_carga` (`cod`),
  ADD CONSTRAINT `remesa_ibfk_3` FOREIGN KEY (`UNIDADMEDIDACAPACIDAD`) REFERENCES `unidad_medida` (`cod`),
  ADD CONSTRAINT `remesa_ibfk_4` FOREIGN KEY (`CODTIPOEMPAQUE`) REFERENCES `tipo_empaque` (`cod`),
  ADD CONSTRAINT `remesa_ibfk_5` FOREIGN KEY (`MERCANCIAREMESA`) REFERENCES `nombre_producto` (`cod`),
  ADD CONSTRAINT `remesa_ibfk_6` FOREIGN KEY (`CODTIPOIDREMITENTE`) REFERENCES `tipo_id` (`cod`),
  ADD CONSTRAINT `remesa_ibfk_7` FOREIGN KEY (`NUMIDREMITENTE`,`CODSEDEREMITENTE`) REFERENCES `sede` (`NUMIDTERCERO`, `CODSEDETERCERO`),
  ADD CONSTRAINT `remesa_ibfk_8` FOREIGN KEY (`CODTIPOIDDESTINATARIO`) REFERENCES `tipo_id` (`cod`),
  ADD CONSTRAINT `remesa_ibfk_9` FOREIGN KEY (`NUMIDDESTINATARIO`,`CODSEDEDESTINATARIO`) REFERENCES `sede` (`NUMIDTERCERO`, `CODSEDETERCERO`);

--
-- Filtros para la tabla `remolque`
--
ALTER TABLE `remolque`
  ADD CONSTRAINT `remolque_ibfk_1` FOREIGN KEY (`CODCONFIGURACIONUNIDADCARGA`) REFERENCES `configuracion_vehiculo` (`cod`),
  ADD CONSTRAINT `remolque_ibfk_2` FOREIGN KEY (`CODMARCAVEHICULOCARGA`) REFERENCES `marca_semiremolque` (`cod`),
  ADD CONSTRAINT `remolque_ibfk_3` FOREIGN KEY (`UNIDADMEDIDACAPACIDAD`) REFERENCES `unidad_medida` (`nombre`),
  ADD CONSTRAINT `remolque_ibfk_4` FOREIGN KEY (`CODTIPOIDPROPIETARIO`) REFERENCES `tipo_id` (`cod`),
  ADD CONSTRAINT `remolque_ibfk_5` FOREIGN KEY (`CODTIPOIDTENEDOR`) REFERENCES `tipo_id` (`cod`),
  ADD CONSTRAINT `remolque_ibfk_6` FOREIGN KEY (`estado`) REFERENCES `estado` (`nombre`);

--
-- Filtros para la tabla `sede`
--
ALTER TABLE `sede`
  ADD CONSTRAINT `sede_ibfk_1` FOREIGN KEY (`CODMUNICIPIORNDC`) REFERENCES `municipio` (`id`),
  ADD CONSTRAINT `sede_ibfk_2` FOREIGN KEY (`NUMIDTERCERO`) REFERENCES `tercero` (`NUMIDTERCERO`);

--
-- Filtros para la tabla `tercero`
--
ALTER TABLE `tercero`
  ADD CONSTRAINT `tercero_ibfk_1` FOREIGN KEY (`EXPEDICION`) REFERENCES `municipio` (`id`),
  ADD CONSTRAINT `tercero_ibfk_2` FOREIGN KEY (`CODTIPOIDTERCERO`) REFERENCES `tipo_id` (`cod`),
  ADD CONSTRAINT `tercero_ibfk_3` FOREIGN KEY (`estado`) REFERENCES `estado` (`nombre`);

--
-- Filtros para la tabla `ubicacion`
--
ALTER TABLE `ubicacion`
  ADD CONSTRAINT `ubicacion_ibfk_1` FOREIGN KEY (`municipio`) REFERENCES `municipio` (`nombre`);

--
-- Filtros para la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD CONSTRAINT `vehiculo_ibfk_1` FOREIGN KEY (`CODCONFIGURACIONUNIDADCARGA`) REFERENCES `configuracion_vehiculo` (`cod`),
  ADD CONSTRAINT `vehiculo_ibfk_2` FOREIGN KEY (`CODMARCAVEHICULOCARGA`,`CODLINEAVEHICULOCARGA`) REFERENCES `linea_vehiculo` (`cod_marca`, `cod_linea`),
  ADD CONSTRAINT `vehiculo_ibfk_3` FOREIGN KEY (`CODCOLORVEHICULOCARGA`) REFERENCES `vehiculo_color` (`cod`),
  ADD CONSTRAINT `vehiculo_ibfk_4` FOREIGN KEY (`UNIDADMEDIDACAPACIDAD`) REFERENCES `unidad_medida` (`nombre`),
  ADD CONSTRAINT `vehiculo_ibfk_5` FOREIGN KEY (`CODTIPOCOMBUSTIBLE`) REFERENCES `tipo_combustible` (`cod`),
  ADD CONSTRAINT `vehiculo_ibfk_6` FOREIGN KEY (`NUMNITASEGURADORASOAT`) REFERENCES `aseguradora` (`id`),
  ADD CONSTRAINT `vehiculo_ibfk_7` FOREIGN KEY (`CODTIPOIDPROPIETARIO`) REFERENCES `tipo_id` (`cod`),
  ADD CONSTRAINT `vehiculo_ibfk_8` FOREIGN KEY (`CODTIPOIDTENEDOR`) REFERENCES `tipo_id` (`cod`),
  ADD CONSTRAINT `vehiculo_ibfk_9` FOREIGN KEY (`estado`) REFERENCES `estado` (`nombre`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
