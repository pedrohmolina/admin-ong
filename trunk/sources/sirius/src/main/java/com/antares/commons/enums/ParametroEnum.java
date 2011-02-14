package com.antares.commons.enums;

public enum ParametroEnum {
	TIPO_INGRESO_FINANCIACION(1, "TIPO_INGRESO_FINANCIACION"),
	ESTADO_PROYECTO_IDEA(2, "ESTADO_PROYECTO_IDEA"),
	ESTADO_ACTIVIDAD_LATENTE(3, "ESTADO_ACTIVIDAD_LATENTE"),
	ESTADO_ACTIVIDAD_PROGRESO(4, "ESTADO_ACTIVIDAD_PROGRESO");
	
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
