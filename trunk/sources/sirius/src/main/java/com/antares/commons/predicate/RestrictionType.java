package com.antares.commons.predicate;

/**
 * Enum que representa los tipos de restricciones de las reglas de seguridad por valor
 *
 * @version 1.0.0 Created 01/05/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 */
public enum RestrictionType {

	NUMERIC_EQUAL(1),
	NUMERIC_NOT_EQUAL(2),
	NUMERIC_LESS_THAN(3),
	NUMERIC_LESS_OR_EQUAL(4),
	NUMERIC_GREATER_THAN(5),
	NUMERIC_GREATER_OR_EQUAL(6),
	TEXT_EQUAL(7),
	TEXT_NOT_EQUAL(8),
	TEXT_CONTAIN(9),
	TEXT_NOT_CONTAIN(10),
	TEXT_START_WITH(11),
	TEXT_DONT_START_WITH(12),
	TEXT_END_WITH(13),
	TEXT_DONT_END_WITH(14),
	DATE_EQUAL(15),
	DATE_NOT_EQUAL(16),
	DATE_BEFORE(17),
	DATE_AFTER(18),
	BOOLEAN_TRUE(19),
	BOOLEAN_FALSE(20),
	OPTION_EQUAL(21),
	OPTION_NOT_EQUAL(22)
	;
	
	private RestrictionType(Integer id) {
		this.id = id;
	}

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static RestrictionType findById(Integer id) {
		RestrictionType restrictionType = null;
		for (RestrictionType value : RestrictionType.values()) {
			if (value.getId().equals(id)) {
				restrictionType = value; 
			}
		}
		return restrictionType; 
	}
}
