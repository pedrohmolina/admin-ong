package com.antares.commons.enums;

public enum TipoAgregacionEnum {
	ACTIVIDAD("ACTIVIDAD"),
	META("META"),
	OBJETIVO_ESPECIFICO("OBJETIVO_ESPECIFICO"),
	OBJETIVO_GENERAL("OBJETIVO_GENERAL"),
	PROYECTO("PROYECTO");
	
	private final String nombre;

	private TipoAgregacionEnum(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public static TipoAgregacionEnum findByNombre(String nombre) {
		TipoAgregacionEnum tipoAgregacionEnum = null;
		for (TipoAgregacionEnum tipo : TipoAgregacionEnum.values()) {
			if (tipo.getNombre().equals(nombre)) {
				tipoAgregacionEnum = tipo; 
			}
		}
		return tipoAgregacionEnum; 
	}
}
