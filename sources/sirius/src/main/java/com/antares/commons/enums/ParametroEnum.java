package com.antares.commons.enums;

public enum ParametroEnum {
	TIPO_INGRESO_FINANCIACION(1, "TIPO_INGRESO_FINANCIACION"),
	ESTADO_PROYECTO_IDEA(2, "ESTADO_PROYECTO_IDEA"),
	ESTADO_ACTIVIDAD_LATENTE(3, "ESTADO_ACTIVIDAD_LATENTE"),
	ESTADO_ACTIVIDAD_PROGRESO(4, "ESTADO_ACTIVIDAD_PROGRESO"),
	TIPO_GASTO_ACTIVIDAD(5, "TIPO_GASTO_ACTIVIDAD"),
	TIPO_GASTO_ORGANIZACION(6, "TIPO_GASTO_ORGANIZACION"),
	TIPO_GASTO_PROYECTO(7, "TIPO_GASTO_PROYECTO"),
	TIPO_AGRUPAMIENTO_INDIVIDUAL(8, "TIPO_AGRUPAMIENTO_INDIVIDUAL"),
	TIPO_AGRUPAMIENTO_AGRUPADO(9, "TIPO_AGRUPAMIENTO_AGRUPADO");
	
	private final Integer id;
	private final String nombre;

	private ParametroEnum(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

}
