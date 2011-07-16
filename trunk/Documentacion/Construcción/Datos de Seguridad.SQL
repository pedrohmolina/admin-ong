-- Datos de Seguridad
insert into accion (id, descripcion) values (1, 'LISTADO');
insert into accion (id, descripcion) values (2, 'ALTA');
insert into accion (id, descripcion) values (3, 'BAJA');
insert into accion (id, descripcion) values (4, 'MODIFICACION');
insert into accion (id, descripcion) values (5, 'DETALLE');
insert into accion (id, descripcion) values (6, 'CAMBIAR_ESTADO');
insert into accion (id, descripcion) values (7, 'CONFIRMAR');
insert into accion (id, descripcion) values (8, 'BLOQUEAR');
insert into accion (id, descripcion) values (9, 'DESBLOQUEAR');
insert into accion (id, descripcion) values (10, 'EJECUTAR');
insert into accion (id, descripcion) values (11, 'HISTORICO');
insert into accion (id, descripcion) values (12, 'ADMINISTRAR_PRESUPUESTO');

insert into recurso (id, descripcion) values (5, 'ENTIDAD_FINANCIADOR');
insert into recurso (id, descripcion) values (6, 'ENTIDAD_INGRESO');
insert into recurso (id, descripcion) values (7, 'ENTIDAD_PERSONA');
insert into recurso (id, descripcion) values (8, 'ENTIDAD_PROVEEDOR');
insert into recurso (id, descripcion) values (9, 'ENTIDAD_RELACION_CONTRACTUAL');
insert into recurso (id, descripcion) values (10, 'ENTIDAD_RUBRO');
insert into recurso (id, descripcion) values (11, 'ENTIDAD_PROYECTO');
insert into recurso (id, descripcion) values (12, 'ENTIDAD_OBJETIVO_GENERAL');
insert into recurso (id, descripcion) values (13, 'ENTIDAD_OBJETIVO_ESPECIFICO');
insert into recurso (id, descripcion) values (14, 'ENTIDAD_META');
insert into recurso (id, descripcion) values (15, 'ENTIDAD_ACTIVIDAD');
insert into recurso (id, descripcion) values (16, 'ENTIDAD_ASIGNACION');
insert into recurso (id, descripcion) values (17, 'ENTIDAD_GASTO_ORGANIZACION');
insert into recurso (id, descripcion) values (18, 'ENTIDAD_GASTO_PROYECTO');
insert into recurso (id, descripcion) values (19, 'ENTIDAD_GASTO_ACTIVIDAD');
insert into recurso (id, descripcion) values (20, 'ENTIDAD_ROL');
insert into recurso (id, descripcion) values (21, 'ENTIDAD_PERFIL');
insert into recurso (id, descripcion) values (22, 'ENTIDAD_USUARIO');
insert into recurso (id, descripcion) values (23, 'ENTIDAD_REGLA');
insert into recurso (id, descripcion) values (25, 'ENTIDAD_REPORTE_PERSONA');
insert into recurso (id, descripcion) values (26, 'ENTIDAD_REPORTE_FINANCIADOR');
insert into recurso (id, descripcion) values (27, 'ENTIDAD_REPORTE_PROVEEDOR');
insert into recurso (id, descripcion) values (28, 'ENTIDAD_REPORTE_FINANZAS');


insert into acceso (id, idRecurso, idAccion) values (5, 5, 1);
insert into acceso (id, idRecurso, idAccion) values (6, 5, 2);
insert into acceso (id, idRecurso, idAccion) values (7, 5, 3);
insert into acceso (id, idRecurso, idAccion) values (8, 5, 4);
insert into acceso (id, idRecurso, idAccion) values (9, 5, 5);
insert into acceso (id, idRecurso, idAccion) values (10, 6, 1);
insert into acceso (id, idRecurso, idAccion) values (11, 6, 2);
insert into acceso (id, idRecurso, idAccion) values (12, 6, 3);
insert into acceso (id, idRecurso, idAccion) values (13, 6, 4);
insert into acceso (id, idRecurso, idAccion) values (14, 6, 5);
insert into acceso (id, idRecurso, idAccion) values (15, 7, 1);
insert into acceso (id, idRecurso, idAccion) values (16, 7, 2);
insert into acceso (id, idRecurso, idAccion) values (17, 7, 3);
insert into acceso (id, idRecurso, idAccion) values (18, 7, 4);
insert into acceso (id, idRecurso, idAccion) values (19, 7, 5);
insert into acceso (id, idRecurso, idAccion) values (20, 8, 1);
insert into acceso (id, idRecurso, idAccion) values (21, 8, 2);
insert into acceso (id, idRecurso, idAccion) values (22, 8, 3);
insert into acceso (id, idRecurso, idAccion) values (23, 8, 4);
insert into acceso (id, idRecurso, idAccion) values (24, 8, 5);
insert into acceso (id, idRecurso, idAccion) values (25, 9, 1);
insert into acceso (id, idRecurso, idAccion) values (26, 9, 2);
insert into acceso (id, idRecurso, idAccion) values (27, 9, 3);
insert into acceso (id, idRecurso, idAccion) values (28, 9, 4);
insert into acceso (id, idRecurso, idAccion) values (29, 9, 5);
insert into acceso (id, idRecurso, idAccion) values (30, 10, 1);
insert into acceso (id, idRecurso, idAccion) values (31, 10, 2);
insert into acceso (id, idRecurso, idAccion) values (32, 10, 3);
insert into acceso (id, idRecurso, idAccion) values (33, 10, 4);
insert into acceso (id, idRecurso, idAccion) values (34, 10, 5);
insert into acceso (id, idRecurso, idAccion) values (35, 11, 1);
insert into acceso (id, idRecurso, idAccion) values (36, 11, 2);
insert into acceso (id, idRecurso, idAccion) values (37, 11, 3);
insert into acceso (id, idRecurso, idAccion) values (38, 11, 4);
insert into acceso (id, idRecurso, idAccion) values (39, 11, 5);
insert into acceso (id, idRecurso, idAccion) values (40, 12, 1);
insert into acceso (id, idRecurso, idAccion) values (41, 12, 2);
insert into acceso (id, idRecurso, idAccion) values (42, 12, 3);
insert into acceso (id, idRecurso, idAccion) values (43, 12, 4);
insert into acceso (id, idRecurso, idAccion) values (44, 12, 5);
insert into acceso (id, idRecurso, idAccion) values (45, 13, 1);
insert into acceso (id, idRecurso, idAccion) values (46, 13, 2);
insert into acceso (id, idRecurso, idAccion) values (47, 13, 3);
insert into acceso (id, idRecurso, idAccion) values (48, 13, 4);
insert into acceso (id, idRecurso, idAccion) values (49, 13, 5);
insert into acceso (id, idRecurso, idAccion) values (50, 14, 1);
insert into acceso (id, idRecurso, idAccion) values (51, 14, 2);
insert into acceso (id, idRecurso, idAccion) values (52, 14, 3);
insert into acceso (id, idRecurso, idAccion) values (53, 14, 4);
insert into acceso (id, idRecurso, idAccion) values (54, 14, 5);
insert into acceso (id, idRecurso, idAccion) values (55, 15, 1);
insert into acceso (id, idRecurso, idAccion) values (56, 15, 2);
insert into acceso (id, idRecurso, idAccion) values (57, 15, 3);
insert into acceso (id, idRecurso, idAccion) values (58, 15, 4);
insert into acceso (id, idRecurso, idAccion) values (59, 15, 5);
insert into acceso (id, idRecurso, idAccion) values (60, 16, 1);
insert into acceso (id, idRecurso, idAccion) values (61, 16, 2);
insert into acceso (id, idRecurso, idAccion) values (62, 16, 3);
insert into acceso (id, idRecurso, idAccion) values (63, 16, 4);
insert into acceso (id, idRecurso, idAccion) values (64, 16, 5);
insert into acceso (id, idRecurso, idAccion) values (65, 17, 1);
insert into acceso (id, idRecurso, idAccion) values (66, 17, 2);
insert into acceso (id, idRecurso, idAccion) values (67, 17, 3);
insert into acceso (id, idRecurso, idAccion) values (68, 17, 4);
insert into acceso (id, idRecurso, idAccion) values (69, 17, 5);
insert into acceso (id, idRecurso, idAccion) values (70, 18, 1);
insert into acceso (id, idRecurso, idAccion) values (71, 18, 2);
insert into acceso (id, idRecurso, idAccion) values (72, 18, 3);
insert into acceso (id, idRecurso, idAccion) values (73, 18, 4);
insert into acceso (id, idRecurso, idAccion) values (74, 18, 5);
insert into acceso (id, idRecurso, idAccion) values (75, 19, 1);
insert into acceso (id, idRecurso, idAccion) values (76, 19, 2);
insert into acceso (id, idRecurso, idAccion) values (77, 19, 3);
insert into acceso (id, idRecurso, idAccion) values (78, 19, 4);
insert into acceso (id, idRecurso, idAccion) values (79, 19, 5);
insert into acceso (id, idRecurso, idAccion) values (80, 20, 1);
insert into acceso (id, idRecurso, idAccion) values (81, 20, 2);
insert into acceso (id, idRecurso, idAccion) values (82, 20, 3);
insert into acceso (id, idRecurso, idAccion) values (83, 20, 4);
insert into acceso (id, idRecurso, idAccion) values (84, 20, 5);
insert into acceso (id, idRecurso, idAccion) values (85, 21, 1);
insert into acceso (id, idRecurso, idAccion) values (86, 21, 2);
insert into acceso (id, idRecurso, idAccion) values (87, 21, 3);
insert into acceso (id, idRecurso, idAccion) values (88, 21, 4);
insert into acceso (id, idRecurso, idAccion) values (89, 21, 5);
insert into acceso (id, idRecurso, idAccion) values (90, 22, 1);
insert into acceso (id, idRecurso, idAccion) values (91, 22, 2);
insert into acceso (id, idRecurso, idAccion) values (92, 22, 3);
insert into acceso (id, idRecurso, idAccion) values (93, 22, 4);
insert into acceso (id, idRecurso, idAccion) values (94, 22, 5);
insert into acceso (id, idRecurso, idAccion) values (95, 11, 6);
insert into acceso (id, idRecurso, idAccion) values (96, 15, 6);
insert into acceso (id, idRecurso, idAccion) values (97, 19, 7);
insert into acceso (id, idRecurso, idAccion) values (98, 22, 8);
insert into acceso (id, idRecurso, idAccion) values (99, 22, 9);
insert into acceso (id, idRecurso, idAccion) values (100, 23, 1);
insert into acceso (id, idRecurso, idAccion) values (101, 23, 2);
insert into acceso (id, idRecurso, idAccion) values (102, 23, 3);
insert into acceso (id, idRecurso, idAccion) values (103, 23, 4);
insert into acceso (id, idRecurso, idAccion) values (104, 23, 5);

insert into acceso (id, idRecurso, idAccion) values (106, 25, 10);
insert into acceso (id, idRecurso, idAccion) values (107, 26, 10);
insert into acceso (id, idRecurso, idAccion) values (108, 27, 10);
insert into acceso (id, idRecurso, idAccion) values (109, 28, 10);

insert into acceso (id, idRecurso, idAccion) values (110, 19, 11);
insert into acceso (id, idRecurso, idAccion) values (111, 11, 12);

insert into rol (id, nombre, descripcion, activo) values (1, 'Administrador de Financiadores', 'Acceso completo al ABM de Financiadores', '1');
insert into rol (id, nombre, descripcion, activo) values (2, 'Administrador de Ingresos', 'Acceso completo al ABM de Ingresos', '1');
insert into rol (id, nombre, descripcion, activo) values (3, 'Administrador de Personas', 'Acceso completo al ABM de Personas', '1');
insert into rol (id, nombre, descripcion, activo) values (4, 'Administrador de Proveedores', 'Acceso completo al ABM de Proveedores', '1');
insert into rol (id, nombre, descripcion, activo) values (5, 'Administrador de Relaciones Contractuales', 'Acceso completo al ABM de Relaciones Contractuales', '1');
insert into rol (id, nombre, descripcion, activo) values (6, 'Administrador de Rubros', 'Acceso completo al ABM de Rubros', '1');
insert into rol (id, nombre, descripcion, activo) values (7, 'Administrador de Proyectos', 'Acceso completo al ABM de Proyectos', '1');
insert into rol (id, nombre, descripcion, activo) values (8, 'Administrador de Objetivos Generales', 'Acceso completo al ABM de Objetivos Generales', '1');
insert into rol (id, nombre, descripcion, activo) values (9, 'Administrador de Objetivos Especificos', 'Acceso completo al ABM de Objetivos Especificos', '1');
insert into rol (id, nombre, descripcion, activo) values (10, 'Administrador de Metas', 'Acceso completo al ABM de Metas', '1');
insert into rol (id, nombre, descripcion, activo) values (11, 'Administrador de Actividades', 'Acceso completo al ABM de Actividades', '1');
insert into rol (id, nombre, descripcion, activo) values (12, 'Administrador de Asignaciones', 'Acceso completo al ABM de Asignaciones', '1');
insert into rol (id, nombre, descripcion, activo) values (13, 'Administrador de Gastos de Organizacion', 'Acceso completo al ABM de Gastos de Organizacion', '1');
insert into rol (id, nombre, descripcion, activo) values (14, 'Administrador de Gastos de Proyecto', 'Acceso completo al ABM de Gastos de Proyecto', '1');
insert into rol (id, nombre, descripcion, activo) values (15, 'Administrador de Gastos de Actividad', 'Acceso al ABM de Gastos de Actividad', '1');
insert into rol (id, nombre, descripcion, activo) values (16, 'Carga de Gastos de Actividad', 'Acceso a la carga de Gastos de Actividad', '1');
insert into rol (id, nombre, descripcion, activo) values (17, 'Administrador de Roles', 'Acceso completo al ABM de Roles', '1');
insert into rol (id, nombre, descripcion, activo) values (18, 'Administrador de Perfiles', 'Acceso completo al ABM de Perfiles', '1');
insert into rol (id, nombre, descripcion, activo) values (19, 'Administrador de Usuarios', 'Acceso completo al ABM de Usuarios', '1');
insert into rol (id, nombre, descripcion, activo) values (20, 'Administrador de Reglas de Seguridad', 'Acceso completo al ABM de Reglas de Seguridad', '1');
insert into rol (id, nombre, descripcion, activo) values (21, 'Administrador de Reportes', 'Acceso completo a los modulos de generacion de reportes', '1');

insert into rolacceso (idRol, idAcceso) values (1, 5);
insert into rolacceso (idRol, idAcceso) values (1, 6);
insert into rolacceso (idRol, idAcceso) values (1, 7);
insert into rolacceso (idRol, idAcceso) values (1, 8);
insert into rolacceso (idRol, idAcceso) values (1, 9);
insert into rolacceso (idRol, idAcceso) values (2, 10);
insert into rolacceso (idRol, idAcceso) values (2, 11);
insert into rolacceso (idRol, idAcceso) values (2, 12);
insert into rolacceso (idRol, idAcceso) values (2, 13);
insert into rolacceso (idRol, idAcceso) values (2, 14);
insert into rolacceso (idRol, idAcceso) values (3, 15);
insert into rolacceso (idRol, idAcceso) values (3, 16);
insert into rolacceso (idRol, idAcceso) values (3, 17);
insert into rolacceso (idRol, idAcceso) values (3, 18);
insert into rolacceso (idRol, idAcceso) values (3, 19);
insert into rolacceso (idRol, idAcceso) values (4, 20);
insert into rolacceso (idRol, idAcceso) values (4, 21);
insert into rolacceso (idRol, idAcceso) values (4, 22);
insert into rolacceso (idRol, idAcceso) values (4, 23);
insert into rolacceso (idRol, idAcceso) values (4, 24);
insert into rolacceso (idRol, idAcceso) values (5, 25);
insert into rolacceso (idRol, idAcceso) values (5, 26);
insert into rolacceso (idRol, idAcceso) values (5, 27);
insert into rolacceso (idRol, idAcceso) values (5, 28);
insert into rolacceso (idRol, idAcceso) values (5, 29);
insert into rolacceso (idRol, idAcceso) values (6, 30);
insert into rolacceso (idRol, idAcceso) values (6, 31);
insert into rolacceso (idRol, idAcceso) values (6, 32);
insert into rolacceso (idRol, idAcceso) values (6, 33);
insert into rolacceso (idRol, idAcceso) values (6, 34);
insert into rolacceso (idRol, idAcceso) values (7, 35);
insert into rolacceso (idRol, idAcceso) values (7, 36);
insert into rolacceso (idRol, idAcceso) values (7, 37);
insert into rolacceso (idRol, idAcceso) values (7, 38);
insert into rolacceso (idRol, idAcceso) values (7, 39);
insert into rolacceso (idRol, idAcceso) values (7, 111);
insert into rolacceso (idRol, idAcceso) values (8, 40);
insert into rolacceso (idRol, idAcceso) values (8, 41);
insert into rolacceso (idRol, idAcceso) values (8, 42);
insert into rolacceso (idRol, idAcceso) values (8, 43);
insert into rolacceso (idRol, idAcceso) values (8, 44);
insert into rolacceso (idRol, idAcceso) values (9, 45);
insert into rolacceso (idRol, idAcceso) values (9, 46);
insert into rolacceso (idRol, idAcceso) values (9, 47);
insert into rolacceso (idRol, idAcceso) values (9, 48);
insert into rolacceso (idRol, idAcceso) values (9, 49);
insert into rolacceso (idRol, idAcceso) values (10, 50);
insert into rolacceso (idRol, idAcceso) values (10, 51);
insert into rolacceso (idRol, idAcceso) values (10, 52);
insert into rolacceso (idRol, idAcceso) values (10, 53);
insert into rolacceso (idRol, idAcceso) values (10, 54);
insert into rolacceso (idRol, idAcceso) values (11, 55);
insert into rolacceso (idRol, idAcceso) values (11, 56);
insert into rolacceso (idRol, idAcceso) values (11, 57);
insert into rolacceso (idRol, idAcceso) values (11, 58);
insert into rolacceso (idRol, idAcceso) values (11, 59);
insert into rolacceso (idRol, idAcceso) values (12, 60);
insert into rolacceso (idRol, idAcceso) values (12, 61);
insert into rolacceso (idRol, idAcceso) values (12, 62);
insert into rolacceso (idRol, idAcceso) values (12, 63);
insert into rolacceso (idRol, idAcceso) values (12, 64);
insert into rolacceso (idRol, idAcceso) values (13, 65);
insert into rolacceso (idRol, idAcceso) values (13, 66);
insert into rolacceso (idRol, idAcceso) values (13, 67);
insert into rolacceso (idRol, idAcceso) values (13, 68);
insert into rolacceso (idRol, idAcceso) values (13, 69);
insert into rolacceso (idRol, idAcceso) values (14, 70);
insert into rolacceso (idRol, idAcceso) values (14, 71);
insert into rolacceso (idRol, idAcceso) values (14, 72);
insert into rolacceso (idRol, idAcceso) values (14, 73);
insert into rolacceso (idRol, idAcceso) values (14, 74);
insert into rolacceso (idRol, idAcceso) values (15, 75);
insert into rolacceso (idRol, idAcceso) values (15, 76);
insert into rolacceso (idRol, idAcceso) values (15, 77);
insert into rolacceso (idRol, idAcceso) values (15, 78);
insert into rolacceso (idRol, idAcceso) values (15, 79);
insert into rolacceso (idRol, idAcceso) values (16, 76);
insert into rolacceso (idRol, idAcceso) values (16, 110);
insert into rolacceso (idRol, idAcceso) values (17, 80);
insert into rolacceso (idRol, idAcceso) values (17, 81);
insert into rolacceso (idRol, idAcceso) values (17, 82);
insert into rolacceso (idRol, idAcceso) values (17, 83);
insert into rolacceso (idRol, idAcceso) values (17, 84);
insert into rolacceso (idRol, idAcceso) values (18, 85);
insert into rolacceso (idRol, idAcceso) values (18, 86);
insert into rolacceso (idRol, idAcceso) values (18, 87);
insert into rolacceso (idRol, idAcceso) values (18, 88);
insert into rolacceso (idRol, idAcceso) values (18, 89);
insert into rolacceso (idRol, idAcceso) values (19, 90);
insert into rolacceso (idRol, idAcceso) values (19, 91);
insert into rolacceso (idRol, idAcceso) values (19, 92);
insert into rolacceso (idRol, idAcceso) values (19, 93);
insert into rolacceso (idRol, idAcceso) values (19, 94);
insert into rolacceso (idRol, idAcceso) values (7, 95);
insert into rolacceso (idRol, idAcceso) values (11, 96);
insert into rolacceso (idRol, idAcceso) values (15, 97);
insert into rolacceso (idRol, idAcceso) values (19, 98);
insert into rolacceso (idRol, idAcceso) values (19, 99);
insert into rolacceso (idRol, idAcceso) values (20, 100);
insert into rolacceso (idRol, idAcceso) values (20, 101);
insert into rolacceso (idRol, idAcceso) values (20, 102);
insert into rolacceso (idRol, idAcceso) values (20, 103);
insert into rolacceso (idRol, idAcceso) values (20, 104);

insert into rolacceso (idRol, idAcceso) values (21, 106);
insert into rolacceso (idRol, idAcceso) values (21, 107);
insert into rolacceso (idRol, idAcceso) values (21, 108);
insert into rolacceso (idRol, idAcceso) values (21, 109);


insert into perfil (id, nombre, descripcion, activo) values (1, 'Administrador', 'Administrador General', '1');

insert into perfilrol (idPerfil, idRol) values (1, 1);
insert into perfilrol (idPerfil, idRol) values (1, 2);
insert into perfilrol (idPerfil, idRol) values (1, 3);
insert into perfilrol (idPerfil, idRol) values (1, 4);
insert into perfilrol (idPerfil, idRol) values (1, 5);
insert into perfilrol (idPerfil, idRol) values (1, 6);
insert into perfilrol (idPerfil, idRol) values (1, 7);
insert into perfilrol (idPerfil, idRol) values (1, 8);
insert into perfilrol (idPerfil, idRol) values (1, 9);
insert into perfilrol (idPerfil, idRol) values (1, 10);
insert into perfilrol (idPerfil, idRol) values (1, 11);
insert into perfilrol (idPerfil, idRol) values (1, 12);
insert into perfilrol (idPerfil, idRol) values (1, 13);
insert into perfilrol (idPerfil, idRol) values (1, 14);
insert into perfilrol (idPerfil, idRol) values (1, 15);
insert into perfilrol (idPerfil, idRol) values (1, 16);
insert into perfilrol (idPerfil, idRol) values (1, 17);
insert into perfilrol (idPerfil, idRol) values (1, 18);
insert into perfilrol (idPerfil, idRol) values (1, 19);
insert into perfilrol (idPerfil, idRol) values (1, 20);

insert into perfilrol (idPerfil, idRol) values (1, 21);

-- Datos para crear un usuario default para administrardor admin/admin
insert into persona (id, apellido, nombre, segundoNombre, idTipoDocumento, numeroDocumento, cuit, cbu, nacionalidad, fechaNacimiento, profesion, direccion, telefono, celular, email, funcion, idRelacionContractual, idFormaPago, idPersonaFactura, observaciones, activo) values (1, 'Otakon', 'Otakon', '', 1, 29319345, '', '', '', null, '', '', '', '', '', '', 1, 1, null, '', '1');
insert into usuario (id, idPersona, username, password, idPerfil, activo, bloqueado) values (1, 1, 'admin', '32ff9ee7e841b26a966870c144fdcaec', 1, '1', '0');






-- SEGURIDAD POR VALOR

insert into tipoatributo (id, descripcion) values (1, 'Numero');
insert into tipoatributo (id, descripcion) values (2, 'Texto');
insert into tipoatributo (id, descripcion) values (3, 'Fecha');
insert into tipoatributo (id, descripcion) values (4, 'Booleano');
insert into tipoatributo (id, descripcion) values (5, 'Opcion');


-- Operadores de tipo Numerico
insert into operador (id, idTipoAtributo, descripcion) values (1, 1, 'Igual');
insert into operador (id, idTipoAtributo, descripcion) values (2, 1, 'Distinto');
insert into operador (id, idTipoAtributo, descripcion) values (3, 1, 'Menor');
insert into operador (id, idTipoAtributo, descripcion) values (4, 1, 'Menor o igual');
insert into operador (id, idTipoAtributo, descripcion) values (5, 1, 'Mayor');
insert into operador (id, idTipoAtributo, descripcion) values (6, 1, 'Mayor o igual');

-- Operadores de tipo Texto
insert into operador (id, idTipoAtributo, descripcion) values (7, 2, 'Igual');
insert into operador (id, idTipoAtributo, descripcion) values (8, 2, 'Distinto');
insert into operador (id, idTipoAtributo, descripcion) values (9, 2, 'Contiene');
insert into operador (id, idTipoAtributo, descripcion) values (10, 2, 'No contiene');
insert into operador (id, idTipoAtributo, descripcion) values (11, 2, 'Empieza con');
insert into operador (id, idTipoAtributo, descripcion) values (12, 2, 'No empieza con');
insert into operador (id, idTipoAtributo, descripcion) values (13, 2, 'Termina con');
insert into operador (id, idTipoAtributo, descripcion) values (14, 2, 'No termina con');

-- Operadores de tipo Fecha
insert into operador (id, idTipoAtributo, descripcion) values (15, 3, 'Igual');
insert into operador (id, idTipoAtributo, descripcion) values (16, 3, 'Distinto');
insert into operador (id, idTipoAtributo, descripcion) values (17, 3, 'Antes de');
insert into operador (id, idTipoAtributo, descripcion) values (18, 3, 'Despues de');

-- Operadores de tipo Booleano
insert into operador (id, idTipoAtributo, descripcion) values (19, 4, 'Si');
insert into operador (id, idTipoAtributo, descripcion) values (20, 4, 'No');

-- Operadores de tipo Opcion
insert into operador (id, idTipoAtributo, descripcion) values (21, 5, 'Igual');
insert into operador (id, idTipoAtributo, descripcion) values (22, 5, 'Distinto');


insert into entidad (id, descripcion, nombreEntidad) values (1, 'sirius.entidad.actividad.label', 'Actividad');
insert into entidad (id, descripcion, nombreEntidad) values (2, 'sirius.entidad.asignacion.label', 'Asignacion');
insert into entidad (id, descripcion, nombreEntidad) values (3, 'sirius.entidad.financiador.label', 'Financiador');
insert into entidad (id, descripcion, nombreEntidad) values (4, 'sirius.entidad.gasto.label', 'Gasto');
insert into entidad (id, descripcion, nombreEntidad) values (5, 'sirius.entidad.ingreso.label', 'Ingreso');
insert into entidad (id, descripcion, nombreEntidad) values (6, 'sirius.entidad.meta.label', 'Meta');
insert into entidad (id, descripcion, nombreEntidad) values (7, 'sirius.entidad.objetivoEspecifico.label', 'ObjetivoEspecifico');
insert into entidad (id, descripcion, nombreEntidad) values (8, 'sirius.entidad.objetivoGenetal.label', 'ObjetivoGeneral');
insert into entidad (id, descripcion, nombreEntidad) values (9, 'sirius.entidad.persona.label', 'Persona');
insert into entidad (id, descripcion, nombreEntidad) values (10, 'sirius.entidad.proveedor.label', 'Proveedor');
insert into entidad (id, descripcion, nombreEntidad) values (11, 'sirius.entidad.proyecto.label', 'Proyecto');
insert into entidad (id, descripcion, nombreEntidad) values (12, 'sirius.entidad.relacionContractual.label', 'RelacionContractual');
insert into entidad (id, descripcion, nombreEntidad) values (13, 'sirius.entidad.rubro.label', 'Rubro');


-- Atributos simples de Actividad
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (1, 1, 2, 'nombre', 'sirius.actividad.nombre.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (2, 1, 3, 'fechaInicio', 'sirius.actividad.fechaInicio.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (3, 1, 3, 'fechaFin', 'sirius.actividad.fechaFin.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (4, 1, 1, 'presupuesto', 'sirius.actividad.presupuesto.label');

-- Atributos simples de Financiador
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (5, 3, 2, 'nombre', 'sirius.financiador.nombre.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (6, 3, 2, 'cuit', 'sirius.financiador.cuit.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (7, 3, 2, 'cbu', 'sirius.financiador.cbu.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (8, 3, 2, 'email', 'sirius.financiador.email.label');

-- Atributos simples de Gasto
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (9, 4, 3, 'fecha', 'sirius.gasto.fecha.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (10, 4, 2, 'numeroComprobante', 'sirius.gasto.numeroComprobante.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (11, 4, 2, 'paquete', 'sirius.gasto.paquete.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (12, 4, 1, 'importe', 'sirius.gasto.importe.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (13, 4, 4, 'confirmado', 'sirius.gasto.confirmado.label');

-- Atributos simples de Ingreso
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (14, 5, 1, 'monto', 'sirius.ingreso.monto.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (15, 5, 3, 'fecha', 'sirius.ingreso.fecha.label');

-- Atributos simples de Meta
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (16, 6, 2, 'nombre', 'sirius.meta.nombre.label');

-- Atributos simples de Objetivo Especifico
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (17, 7, 2, 'nombre', 'sirius.objetivoEspecifico.nombre.label');

-- Atributos simples de Objetivo General
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (18, 8, 2, 'nombre', 'sirius.objetivoGeneral.nombre.label');

-- Atributos simples de Persona
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (20, 9, 2, 'apellido', 'sirius.persona.apellido.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (21, 9, 2, 'nombre', 'sirius.persona.nombre.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (22, 9, 2, 'segundoNombre', 'sirius.persona.segundoNombre.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (23, 9, 1, 'numeroDocumento', 'sirius.persona.numeroDocumento.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (24, 9, 2, 'cuit', 'sirius.persona.cuit.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (25, 9, 2, 'cbu', 'sirius.persona.cbu.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (26, 9, 2, 'nacionalidad', 'sirius.persona.nacionalidad.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (27, 9, 3, 'fechaNacimiento', 'sirius.persona.fechaNacimiento.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (28, 9, 2, 'email', 'sirius.persona.email.label');

-- Atributos simples de Proveedor
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (29, 10, 2, 'nombre', 'sirius.proveedor.nombre.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (30, 10, 2, 'cuit', 'sirius.proveedor.cuit.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (31, 10, 2, 'cbu', 'sirius.proveedor.cbu.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (32, 10, 2, 'email', 'sirius.proveedor.email.label');

-- Atributos simples de Proyecto
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (33, 11, 2, 'nombre', 'sirius.proyecto.nombre.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (34, 11, 3, 'fechaInicio', 'sirius.proyecto.fechaInicio.label');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (35, 11, 3, 'fechaFin', 'sirius.proyecto.fechaFin.label');

-- Atributos simples de Relacion Contractual
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (36, 12, 2, 'nombre', 'sirius.relacionContractual.nombre.label');

-- Atributos simples de Rubro
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion) values (38, 13, 2, 'nombre', 'sirius.rubro.nombre.label');


-- Atributos de Opciones
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion, optionBean) values (39, 1, 5, 'estadoActividad.id', 'sirius.actividad.estadoActividad.label', 'estadoActividadDAO');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion, optionBean) values (40, 2, 5, 'tipoAsignacion.id', 'sirius.asignacion.tipoAsignacion.label', 'tipoAsignacionDAO');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion, optionBean) values (41, 3, 5, 'tipoFinanciador.id', 'sirius.financiador.tipoFinanciador.label', 'tipoFinanciadorDAO');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion, optionBean) values (42, 3, 5, 'estadoFinanciador.id', 'sirius.financiador.estadoFinanciador.label', 'estadoFinanciadorDAO');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion, optionBean) values (43, 4, 5, 'origen.id', 'sirius.gasto.origen.label', 'origenDAO');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion, optionBean) values (44, 4, 5, 'tipoComprobante.id', 'sirius.gasto.tipoComprobante.label', 'tipoComprobanteDAO');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion, optionBean) values (45, 4, 5, 'tipoGasto.id', 'sirius.gasto.tipoGasto.label', 'tipoGastoDAO');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion, optionBean) values (46, 5, 5, 'tipoIngreso.id', 'sirius.ingreso.tipoIngreso.label', 'tipoIngresoDAO');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion, optionBean) values (47, 9, 5, 'tipoDocumento.id', 'sirius.persona.tipoDocumento.label', 'tipoDocumentoDAO');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion, optionBean) values (48, 9, 5, 'formaPago.id', 'sirius.persona.formaPago.label', 'formaPagoDAO');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion, optionBean) values (49, 10, 5, 'tipoProveedor.id', 'sirius.proveedor.tipoProveedor.label', 'tipoProveedorDAO');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion, optionBean) values (50, 11, 5, 'estadoProyecto.id', 'sirius.proyecto.estadoProyecto.label', 'estadoProyectoDAO');
insert into atributo (id, idEntidad, idTipoAtributo, nombreAtributo, descripcion, optionBean) values (51, 11, 5, 'tipoAgrupamiento.id', 'sirius.proyecto.tipoAgrupamiento.label', 'tipoAgrupamientoDAO');






-- Referencias de las distintas entidades
-- Referencias de Actividad
insert into entidadReferenciada (id, idEntidad, idEntidadReferenciada, nombreEntidad) values (1, 1, 3, 'financiador');
insert into entidadReferenciada (id, idEntidad, idEntidadReferenciada, nombreEntidad) values (2, 1, 6, 'meta');

-- Referencias de Asignacion
insert into entidadReferenciada (id, idEntidad, idEntidadReferenciada, nombreEntidad) values (3, 2, 1, 'actividad');
insert into entidadReferenciada (id, idEntidad, idEntidadReferenciada, nombreEntidad) values (4, 2, 9, 'persona');

-- Referencias de Gasto
insert into entidadReferenciada (id, idEntidad, idEntidadReferenciada, nombreEntidad) values (5, 4, 1, 'actividad');
insert into entidadReferenciada (id, idEntidad, idEntidadReferenciada, nombreEntidad) values (6, 4, 9, 'persona');
insert into entidadReferenciada (id, idEntidad, idEntidadReferenciada, nombreEntidad) values (7, 4, 10, 'proveedor');
insert into entidadReferenciada (id, idEntidad, idEntidadReferenciada, nombreEntidad) values (8, 4, 11, 'proyecto');
insert into entidadReferenciada (id, idEntidad, idEntidadReferenciada, nombreEntidad) values (9, 4, 13, 'rubro');

-- Referencias de Ingreso
insert into entidadReferenciada (id, idEntidad, idEntidadReferenciada, nombreEntidad) values (10, 5, 3, 'financiador');

-- Referencias de Meta
insert into entidadReferenciada (id, idEntidad, idEntidadReferenciada, nombreEntidad) values (11, 6, 7, 'objetivoEspecifico');

-- Referencias de Objetivo Especifico
insert into entidadReferenciada (id, idEntidad, idEntidadReferenciada, nombreEntidad) values (12, 7, 8, 'objetivoGeneral');

-- Referencias de Objetivo General
insert into entidadReferenciada (id, idEntidad, idEntidadReferenciada, nombreEntidad) values (13, 8, 11, 'proyecto');

-- Referencias de Persona
insert into entidadReferenciada (id, idEntidad, idEntidadReferenciada, nombreEntidad) values (14, 9, 12, 'relacionContractual');

-- Referencias de Rubro
insert into entidadReferenciada (id, idEntidad, idEntidadReferenciada, nombreEntidad) values (15, 13, 13, 'rubroPadre');

-- Referencias de Proyecto
insert into entidadReferenciada (id, idEntidad, idEntidadReferenciada, nombreEntidad) values (16, 11, 3, 'financiador');
insert into entidadReferenciada (id, idEntidad, idEntidadReferenciada, nombreEntidad) values (17, 11, 9, 'responsable');

