-- Agregar Persona
INSERT INTO `persona` (`id`, `apellido`, `nombre`, `segundoNombre`, `idTipoDocumento`, `numeroDocumento`, `cuit`, `cbu`, `nacionalidad`, `fechaNacimiento`, `profesion`, `direccion`, `telefono`, `celular`, `email`, `funcion`, `idRelacionContractual`, `idFormaPago`, `idPersonaFactura`, `observaciones`, `activo`) VALUES ('2', 'Lopez', 'Gustavo', '', '1', '29564435.0', '20295644359', '5465735676545676567656', 'Argentina', '1980-11-18', 'Administrador de Empresas', 'Avenida Maipu 2564', '4799-5830', '15-4564-7763', 'guslopez@arnet.com', 'asesor de proyectos', '1', '1', NULL, 'Esta disponible solo los días Lunes y Miércoles', '1');


-- Agregar Financiador
INSERT INTO `financiador` (`id`, `idTipoFinanciador`, `nombre`, `cuit`, `cbu`, `direccion`, `telefono`, `contacto`, `celular`, `email`, `idEstadoFinanciador`, `observaciones`, `activo`) VALUES ('1', '3', '4 de Copas', '56347585567', '47382958492039485967', 'Av. De Mayo 966', '4766-4578', 'Pablo Ota Molina', '15-3533-4546', '4decopas@arnet.com', '1', 'Es una Empresa de Sistemas', '1');


-- Agregar Proveedor
INSERT INTO `proveedor` (`id`, `idTipoProveedor`, `nombre`, `cuit`, `cbu`, `direccion`, `telefono`, `contacto`, `celular`, `email`, `observaciones`, `activo`) VALUES ('1', '3', 'Antares', '54765478893', '356465463464645634565', 'Cordoba 4535', '4232-5673', 'Juan Brahma', '15-3546-5765', 'deptoventas@antares.com', '', '1');


-- Agregar Rubros
INSERT INTO `rubro` (`id`, `idRubroPadre`, `idRubroPrimerNivel`, `nombre`, `descripcion`, `activo`) VALUES ('1', NULL, 1, 'Materiales', 'Se utiliza para identificar cualquier tipo de material utilizado en un proyecto', '1');
INSERT INTO `rubro` (`id`, `idRubroPadre`, `idRubroPrimerNivel`, `nombre`, `descripcion`, `activo`) VALUES ('2', '1', 1, 'Librería', 'Identifica todos los materiales relacionados con libreria', '1');
INSERT INTO `rubro` (`id`, `idRubroPadre`, `idRubroPrimerNivel`, `nombre`, `descripcion`, `activo`) VALUES ('3', '2', 1, 'Fotocopias', 'Fotocopias hechas para un proyecto', '1');
INSERT INTO `rubro` (`id`, `idRubroPadre`, `idRubroPrimerNivel`, `nombre`, `descripcion`, `activo`) VALUES ('4', NULL, 4, 'Honorarios', 'Honorarios', '1');
INSERT INTO `rubro` (`id`, `idRubroPadre`, `idRubroPrimerNivel`, `nombre`, `descripcion`, `activo`) VALUES ('5', NULL, 5, 'Movilidad', 'Movilidad', '1');
INSERT INTO `rubro` (`id`, `idRubroPadre`, `idRubroPrimerNivel`, `nombre`, `descripcion`, `activo`) VALUES ('6', NULL, 6, 'Reintegros', 'Reintegros', '1');


-- Agregar Perfil
INSERT INTO `perfil` (`id`, `nombre`, `descripcion`, `activo`) VALUES ('2', 'Operador', 'El operador realiza carga de gastos', '1');


-- Agregar Proyecto
INSERT INTO `proyecto` (`id`, `idEstadoProyecto`, `idTipoAgrupamiento`, `nombre`, `descripcion`, `fechaInicio`, `fechaFin`, `ubicacion`, `responsable`, `beneficiariosDirectos`, `beneficiariosIndirectos`, `financiador`, `resumen`, `presupuestoTotal`, `idArchivo`, `activo`) VALUES ('1', '3', '2', 'Ole a la Gripe', 'El objetivo del  Proyecto es vacunar contra la gripe a los chicos que viven en la calle, de la zona Oeste de la provincia de Bs As', '2012-04-01', '2012-05-31', 'Zona Oeste de la Provincia de Bs As', '1', 'El grupo Antares', 'SAHDES', '1', NULL, 10000, NULL, '1');


-- Agregar Objetivo General
INSERT INTO `objetivogeneral` (`id`, `nombre`, `descripcion`, `ponderacion`, `idProyecto`, `activo`) VALUES ('1', 'Vacunar a 300 chicos', 'Vacunar a 300 chicos contra la gripe', '100.0', '1', '1');


-- Agregar Objetivo Especifico
INSERT INTO `objetivoespecifico` (`id`, `nombre`, `descripcion`, `ponderacion`, `idObjetivoGeneral`, `activo`) VALUES ('1', 'Vacunar a 200 chicos de la zona de Morón', 'Vacunar a 200 chicos de la zona de Morón contra la gripe', '33.0', '1', '1');


-- Agregar Meta
INSERT INTO `meta` (`id`, `nombre`, `descripcion`, `ponderacion`, `idObjetivoEspecifico`, `activo`) VALUES ('1', 'Vacunar a 70 chicos de la zona Sur de Moron', 'Vacunar a 70 chicos de la zona Sur de Moron contra la gripe', '70.0', '1', '1');


-- Agregar Actividad
INSERT INTO `actividad` (`id`, `idMeta`, `idFinanciador`, `idEstadoActividad`, `nombre`, `observaciones`, `fechaInicio`, `fechaFin`, `ponderacion`, `completitud`, `activo`) VALUES ('2', '1', '1', '3', 'Comprar 70 vacunas contra la gripe', 'Comprar las vacunas en Farmacity', '2012-04-03', '2012-04-03', '40.0', '0.0', '1');


-- Agregar Ingreso
INSERT INTO `ingreso` (`id`, `idTipoIngreso`, `monto`, `fecha`, `descripcion`, `idFinanciador`, `activo`) VALUES ('1', '1', '30000.0', '2011-03-05', 'Dinero destinado para vacunar a los chicos mas necesitados contra la gripe y también para comprar abrigos para los chicos', '1', '1');


-- ******************  Agregar Origen
INSERT INTO `origen` (`id`, `descripcion`) VALUES ('1', 'Caja Chica');
INSERT INTO `origen` (`id`, `descripcion`) VALUES ('2', 'Caja');
INSERT INTO `origen` (`id`, `descripcion`) VALUES ('3', 'Efectivo');


-- Agregar Gasto General de Organizacion
INSERT INTO `gasto` (`id`, `idProyecto`, `idActividad`, `fecha`, `idRubro`, `idOrigen`, `idProveedor`, `idTipoComprobante`, `numeroComprobante`, `paquete`, `importe`, `idPersona`, `idTipoGasto`, `observaciones`, `referencia`, `confirmado`, `activo`) VALUES ('5', NULL, NULL, '2011-03-05', '3', '1', '1', '2', '123456789', NULL, '1000.0', '1', '1', 'Compra de 1 Impresora HP', NULL, '0', '1');


-- Actualizar password

update usuario set password = '32ff9ee7e841b26a966870c144fdcaec' where id = 1 and username = 'admin';