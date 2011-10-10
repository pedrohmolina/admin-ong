package com.antares.commons.enums;

public enum ActionEnum {
	CREATE("create"),
	UPDATE("update");
	
	private final String descripcion;

	private ActionEnum(String desc) {
		this.descripcion = desc;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static ActionEnum findByDescripcion(String descripcion) {
		ActionEnum actionEnum = null;
		for (ActionEnum action : ActionEnum.values()) {
			if (action.getDescripcion().equals(descripcion)) {
				actionEnum = action; 
			}
		}
		return actionEnum; 
	}

}
