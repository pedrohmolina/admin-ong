package com.antares.commons.restrictions;


public enum PropertyType {

	NUMERIC(1),
	TEXT(2),
	DATE(3),
	BOOLEAN(4),
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
