--  Area Tematica
INSERT INTO areatematica (id,descripcion) VALUES (1,'Salud y Nutrición');
INSERT INTO areatematica (id,descripcion) VALUES (2,'Derechos Humanos');
INSERT INTO areatematica (id,descripcion) VALUES (3,'Educación Básica');
INSERT INTO areatematica (id,descripcion) VALUES (4,'Desarrollo Democrático');
INSERT INTO areatematica (id,descripcion) VALUES (5,'Capacitación Laboral');
INSERT INTO areatematica (id,descripcion) VALUES (6,'Otros');

--  Tipo Financiador
INSERT INTO tipofinanciador (id,descripcion) VALUES (1,'Persona');
INSERT INTO tipofinanciador (id,descripcion) VALUES (2,'Institución');
INSERT INTO tipofinanciador (id,descripcion) VALUES (3,'Empresa');

--  Estado Financiador
INSERT INTO estadofinanciador (id,descripcion) VALUES (1,'Activo');
INSERT INTO estadofinanciador (id,descripcion) VALUES (2,'Inactivo');
INSERT INTO estadofinanciador (id,descripcion) VALUES (3,'Potencial');

--  Estado Proyecto
INSERT INTO estadoproyecto (id,idEstadoAnterior,descripcion) VALUES (1,null,'Idea');
INSERT INTO estadoproyecto (id,idEstadoAnterior,descripcion) VALUES (2,1,'Diseño');
INSERT INTO estadoproyecto (id,idEstadoAnterior,descripcion) VALUES (3,2,'Evaluación');
INSERT INTO estadoproyecto (id,idEstadoAnterior,descripcion) VALUES (4,3,'Ejecución');
INSERT INTO estadoproyecto (id,idEstadoAnterior,descripcion) VALUES (5,5,'Finalizado');

--  Tipo Ingreso
INSERT INTO tipoingreso (id,descripcion) VALUES (1,'Donación');
INSERT INTO tipoingreso (id,descripcion) VALUES (2,'Venta de Servicio');
INSERT INTO tipoingreso (id,descripcion) VALUES (3,'Financiador');

--  Tipo Documento
INSERT INTO tipodocumento (id,descripcion) VALUES (1,'DNI');
INSERT INTO tipodocumento (id,descripcion) VALUES (2,'Cedula Identidad');
INSERT INTO tipodocumento (id,descripcion) VALUES (3,'Pasaporte');
INSERT INTO tipodocumento (id,descripcion) VALUES (4,'Libreta de Enrolamiento');
INSERT INTO tipodocumento (id,descripcion) VALUES (5,'Libreta Cívica');

--  Tipo Gasto
INSERT INTO tipogasto (id,descripcion) VALUES (1,'Gastos de Actividad');
INSERT INTO tipogasto (id,descripcion) VALUES (2,'Gastos Generales');
INSERT INTO tipogasto (id,descripcion) VALUES (3,'Gastos Institucionales');

--  Tipo Comprobante
INSERT INTO tipocomprobante (id,descripcion) VALUES (1,'Ticket Fiscal');
INSERT INTO tipocomprobante (id,descripcion) VALUES (2,'Factura Tipo A');
INSERT INTO tipocomprobante (id,descripcion) VALUES (3,'Factura Tipo B');
INSERT INTO tipocomprobante (id,descripcion) VALUES (4,'Factura Tipo C');

--  Forma de pago
INSERT INTO formapago (id,descripcion) VALUES (1,'Efectivo');
INSERT INTO formapago (id,descripcion) VALUES (2,'Cheque');
INSERT INTO formapago (id,descripcion) VALUES (3,'Transferencia Bancaria');
INSERT INTO formapago (id,descripcion) VALUES (4,'Tarjeta de Crédito');

--  Tipo Presupuesto
insert into tipoPresupuesto (id, descripcion) VALUES (1, 'Proyecto');
insert into tipoPresupuesto (id, descripcion) VALUES (2, 'Actividad');

--  Tipo Proveedor
INSERT INTO tipoproveedor (id,descripcion) VALUES (1,'Persona');
INSERT INTO tipoproveedor (id,descripcion) VALUES (2,'Institución');
INSERT INTO tipoproveedor (id,descripcion) VALUES (3,'Empresa');

--  Tipo Asignacion
INSERT INTO tipoasignacion (id,descripcion) VALUES (1,'Por jornada');
INSERT INTO tipoasignacion (id,descripcion) VALUES (2,'Por hora');
INSERT INTO tipoasignacion (id,descripcion) VALUES (3,'Por mes');

--  Estado Actividad
INSERT INTO estadoactividad (id,descripcion) VALUES (1,'Latencia');
INSERT INTO estadoactividad (id,descripcion) VALUES (2,'Progreso');
INSERT INTO estadoactividad (id,descripcion) VALUES (3,'Suspensión');
INSERT INTO estadoactividad (id,descripcion) VALUES (4,'Cancelada');
INSERT INTO estadoactividad (id,descripcion) VALUES (5,'Cumplida');

--  Relacion Contractual
INSERT INTO relacioncontractual (id,nombre,descripcion,activo) VALUES (1,'Empleado','Empleado',true);
INSERT INTO relacioncontractual (id,nombre,descripcion,activo) VALUES (2,'Profesional','Profesional Contratado',true);
INSERT INTO relacioncontractual (id,nombre,descripcion,activo) VALUES (3,'Voluntario','Voluntario',true);
INSERT INTO relacioncontractual (id,nombre,descripcion,activo) VALUES (4,'Becario','Becario',true);
INSERT INTO relacioncontractual (id,nombre,descripcion,activo) VALUES (5,'Pasante','Pasante',true);
INSERT INTO relacioncontractual (id,nombre,descripcion,activo) VALUES (6,'Socio','Socio',true);
INSERT INTO relacioncontractual (id,nombre,descripcion,activo) VALUES (7,'Miembro CD','Miembro de la Comisión Directiva',true);

--  Parametros
insert into parametro (id, nombre, valor, descripcion) VALUES (1, 'ID_TIPO_INGRESO_FINANCIACION', '3', 'Id de la tabla tipoIngreso correspondiente al registro que representa Financiacion');
insert into parametro (id, nombre, valor, descripcion) VALUES (2, 'ID_ESTADO_PROYECTO_IDEA', '1', 'Id de la tabla estadoProyecto correspondiente al registro que representa el estado Idea');
insert into parametro (id, nombre, valor, descripcion) VALUES (3, 'ID_ESTADO_ACTIVIDAD_LATENTE', '1', 'Id de la tabla estadoActividad correspondiente al registro que representa el estado Latente');
insert into parametro (id, nombre, valor, descripcion) VALUES (4, 'ID_ESTADO_ACTIVIDAD_PROGRESO', '2', 'Id de la tabla estadoActividad correspondiente al registro que representa el estado En Progreso');
insert into parametro (id, nombre, valor, descripcion) VALUES (5, 'ID_TIPO_GASTO_ACTIVIDAD', '3', 'Id de la tabla tipoGasto correspondiente al registro que representa el tipo de gasto de actividades');
insert into parametro (id, nombre, valor, descripcion) VALUES (6, 'ID_TIPO_GASTO_ORGANIZACION', '1', 'Id de la tabla tipoGasto correspondiente al registro que representa el tipo de gasto de organizacion');
insert into parametro (id, nombre, valor, descripcion) VALUES (7, 'ID_TIPO_GASTO_PROYECTO', '2', 'Id de la tabla tipoGasto correspondiente al registro que representa el tipo de gasto de proyectos');
insert into parametro (id, nombre, valor, descripcion) VALUES (8, 'ID_TIPO_AGRUPAMIENTO_INDIVIDUAL', '1', 'Id de la tabla tipoAgrupamiento correspondiente al registro que representa el tipo de agrupamiento individual');
insert into parametro (id, nombre, valor, descripcion) VALUES (9, 'ID_TIPO_AGRUPAMIENTO_AGRUPADO', '2', 'Id de la tabla tipoAgrupamiento correspondiente al registro que representa el tipo de agrupamiento agrupado');
insert into parametro (id, nombre, valor, descripcion) VALUES (10, 'ID_TIPO_PRESUPUESTO_ACTIVIDAD', '2', 'Id de la tabla tipoPresupuesto correspondiente al registro que representa el tipo de presupuesto para gastos de actividades');
insert into parametro (id, nombre, valor, descripcion) VALUES (11, 'ID_TIPO_PRESUPUESTO_PROYECTO', '1', 'Id de la tabla tipoPresupuesto correspondiente al registro que representa el tipo de presupuesto para gastos de proyecto');

-- Tipo de Agrupamiento
INSERT INTO tipoagrupamiento (id,descripcion) VALUES (1,'Individual');
INSERT INTO tipoagrupamiento (id,descripcion) VALUES (2,'Agrupado');

-- Proximo estado de actividad
insert into proximoestadoactividad (idEstadoActividad, idProximoEstadoActividad) values (1, 2);
insert into proximoestadoactividad (idEstadoActividad, idProximoEstadoActividad) values (2, 3);
insert into proximoestadoactividad (idEstadoActividad, idProximoEstadoActividad) values (2, 4);
insert into proximoestadoactividad (idEstadoActividad, idProximoEstadoActividad) values (2, 5);
insert into proximoestadoactividad (idEstadoActividad, idProximoEstadoActividad) values (3, 2);
