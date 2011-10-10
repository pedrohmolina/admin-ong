package com.antares.commons.predicate;


/**
 * Enum que representa los tipos de datos de las reglas de seguridad por valor
 *
 * @version 1.0.0 Created 01/05/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 */
public enum PropertyType {

	NUMERIC(1),
	TEXT(2),
	DATE(3),
	BOOLEAN(4),
	OPTION(5)
	;
	
	private PropertyType(Integer id) {
		this.id = id;
	}

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static PropertyType findById(Integer id) {
		PropertyType propertyType = null;
		for (PropertyType value : PropertyType.values()) {
			if (value.getId().equals(id)) {
				propertyType = value; 
			}
		}
		return propertyType; 
	}
}
