/* Personas */
INSERT INTO `persona` (`id`, `apellido`, `nombre`, `segundoNombre`, `idTipoDocumento`, `numeroDocumento`, `cuit`, `cbu`, `nacionalidad`, `fechaNacimiento`, `profesion`, `direccion`, `telefono`, `celular`, `email`, `funcion`, `idRelacionContractual`, `idFormaPago`, `idPersonaFactura`, `observaciones`, `activo`) VALUES
  (3, 'Rodriguez', 'Joaquin', '', 1, 29056273, '', '', '', '1982-12-08', '', '', '', '', '', '', 3, 2, NULL, '', 1),
  (4, 'Marotta', 'Roberto', '', 1, 26983190, '', '', '', NULL, '', '', '', '', '', '', 2, 3, NULL, '', 1),
  (5, 'Sanchez', 'Leopoldo', '', 4, 1321134, '', '', '', NULL, '', '', '', '', '', '', 4, 2, 2, '', 1),
  (6, 'Velazquez', 'Sabrina', 'Romina', 1, 27765220, '', '', '', NULL, '', '', '', '', '', '', 5, 1, NULL, '', 1),
  (7, 'Altamirano', 'Victor', 'Raul', 1, 219384710, '', '', '', NULL, '', '', '', '', '', '', 1, 2, NULL, '', 1),
  (8, 'Zanabria', 'Mario', 'Alberto', 3, 19023998, '', '', '', NULL, '', '', '', '', '', '', 7, 1, NULL, '', 1),
  (9, 'Lopez Torres', 'Ruben', '', 1, 23218912, '', '', '', NULL, '', '', '', '', '', '', 2, 2, NULL, '', 1),
  (10, 'Lusuriaga', 'Emilia', '', 1, 23012978, '', '', '', NULL, '', '', '', '', '', '', 5, 3, NULL, '', 1);

commit;

/* Financiadores */
INSERT INTO `financiador` (`id`, `idTipoFinanciador`, `nombre`, `cuit`, `cbu`, `direccion`, `telefono`, `contacto`, `celular`, `email`, `idEstadoFinanciador`, `observaciones`, `activo`) VALUES
  (2, 3, 'Instituto Vidal', '', '', 'Pasco 349', '4332-7842', '', '', '', 1, '', 1),
  (3, 2, 'CAECE', '', '', 'Av. Mayo 866', '4562-8371', '', '', '', 1, '', 1),
  (4, 2, 'Embajada de Canada', '', '', 'Alem 1013', '4345-5999', '', '', '', 3, '', 1),
  (5, 1, 'Jose Perez', '', '', 'Peron 798', '4321-1123', '', '', '', 2, '', 1);

commit;

/* Proveedores */
INSERT INTO `proveedor` (`id`, `idTipoProveedor`, `nombre`, `cuit`, `cbu`, `direccion`, `telefono`, `contacto`, `celular`, `email`, `observaciones`, `activo`) VALUES
  (2, 3, 'Ledesma', '', '', 'Lamadrid 2122', '4312-3432', '', '', '', '', 1),
  (3, 1, 'Leonardo Leipus', '', '', 'Rodriguez Peña 312', '3122-3431', '', '', '', '', 1),
  (4, 3, 'Vital', '', '', 'Lope de Vega 121', '3431-9548', '', '', '', '', 1),
  (5, 3, 'Farmatron', '', '', 'Zapiola 391', '4952-3912', '', '', '', '', 1),
  (6, 2, 'Medicus', '', '', 'Cordoba 2921', '4821-2312', '', '', '', '', 1);

commit;

/* Proyectos */
INSERT INTO `proyecto` (`id`, `idEstadoProyecto`, `idTipoAgrupamiento`, `nombre`, `descripcion`, `fechaInicio`, `fechaFin`, `ubicacion`, `responsable`, `beneficiariosDirectos`, `beneficiariosIndirectos`, `financiador`, `presupuestoTotal`, `resumen`, `idArchivo`, `activo`) VALUES
  (2, 4, 2, 'Promotores de Salud 2011', NULL, '2011-08-01', '2011-08-31', 'Gral. Lamadrid', 4, NULL, NULL, 2, 7000, NULL, NULL, 1),
  (3, 2, 2, 'Jóvenes Promotores de Salud', 'Capacitar y promover a jovenes promotores de Salud vv', '2011-08-03', '2011-08-17', 'Manzanares. Partido de Pilar. Provincia de Buenos Aires.', 7, 'Mujeres: 34\r\nHombres: 7\r\nNiños/as: 234\r\nTOTAL: 275', 'Mujeres: 486\r\nHombres: 449\r\nNiños/as: 530\r\nTOTAL: 1.465', 4, 94010, NULL, NULL, 1),
  (4, 4, 2, 'Cooperativa AraSur', NULL, '2011-08-10', '2011-09-01', 'Villa Langostura', 1, NULL, NULL, 2, 62000, NULL, NULL, 1);

commit;

/* Coordinadores */
INSERT INTO `coordinador` (`idProyecto`, `idPersona`) VALUES
  (2, 7),
  (3, 6),
  (3, 10),
  (4, 2),
  (4, 7);

commit;

/* Objetivos Generales */
INSERT INTO `objetivogeneral` (`id`, `nombre`, `descripcion`, `ponderacion`, `idProyecto`, `activo`) VALUES
  (2, 'Promocion de promotores de salud', NULL, 100, 2, 1),
  (3, 'Promover Promotores de Salud', NULL, 100, 3, 1),
  (4, 'Autosustentacion cooperativa AraSur', NULL, 40, 4, 1);

  commit;

/* Objetivos Especificos */
INSERT INTO `objetivoespecifico` (`id`, `nombre`, `descripcion`, `ponderacion`, `idObjetivoGeneral`, `activo`) VALUES
  (2, 'Capacitar Promotores de Salud', NULL, 50, 2, 1),
  (3, 'Ejecutar Promocion de Salud', NULL, 50, 2, 1),
  (4, 'Promocion de jovenes promotores de salud', NULL, 100, 3, 1),
  (5, 'Autosustentacion cooperativa AraSur', NULL, 70, 4, 1);

 commit;

/* Metas */
INSERT INTO `meta` (`id`, `nombre`, `descripcion`, `ponderacion`, `idObjetivoEspecifico`, `activo`) VALUES
  (2, 'Capacitar 25 Promotores de Salud', NULL, 100, 2, 1),
  (3, 'Promocion de jovenes promotores de salud', NULL, 100, 4, 1),
  (4, 'Evaluacion capacidad autogestion', NULL, 60, 5, 1),
  (5, 'Capacitar sobre autogestion', NULL, 50, 5, 1),
  (6, 'Supervision de la autogestion', NULL, 50, 5, 1);

 commit;

/* Actividades */
INSERT INTO `actividad` (`id`, `idMeta`, `idFinanciador`, `idEstadoActividad`, `nombre`, `observaciones`, `fechaInicio`, `fechaFin`, `ponderacion`, `completitud`, `activo`, `fechaFinalizacion`) VALUES
  (3, 2, 2, 5, 'Capacitacion Personas', NULL, '2011-08-18', '2011-08-19', 50, 0, 1, '2011-08-15'),
  (4, 2, 2, 5, 'Evaluacion Promotores', NULL, '2011-08-09', '2011-08-16', 30, 13, 1, '2011-08-15'),
  (5, 3, 5, 5, 'Capacitar jovenes promotores', NULL, '2011-08-09', '2011-08-16', 40, 59, 1, '2011-08-15'),
  (6, 3, 5, 4, 'Trabajo de campo de los jovenes promotores de salud', NULL, '2011-08-10', '2011-08-17', 60, 0, 1, '2011-08-15'),
  (7, 2, 2, 3, 'Evaluar Promotores', NULL, '2011-08-09', '2011-08-16', 20, 12, 1, NULL),
  (8, 2, 3, 4, 'Ejercicio practico', NULL, '2011-08-03', '2011-08-11', 0, 0, 0, '2011-08-15'),
  (9, 3, 5, 1, 'Induccion a promotores', NULL, '2011-08-03', '2011-08-03', 0, 0, 0, NULL),
  (12, 4, 2, 4, 'Evaluacion estado gestion actual', NULL, '2011-08-10', '2011-08-17', 75, 0, 1, '2011-08-15'),
  (13, 5, 2, 1, 'Introduccion a la autogestion', NULL, '2011-08-14', '2011-08-19', 60, 0, 1, NULL),
  (14, 5, 3, 1, 'Practica autogestion', NULL, '2011-08-17', '2011-08-24', 40, 0, 1, NULL),
  (15, 6, 2, 1, 'Supervision autogestion', NULL, '2011-08-17', '2011-08-24', 50, 0, 1, NULL);

  commit;