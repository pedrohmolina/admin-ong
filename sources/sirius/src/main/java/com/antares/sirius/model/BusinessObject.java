package com.antares.sirius.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BusinessObject extends PersistentObject {

    @Column(nullable = false)
	protected Boolean activo = Boolean.TRUE;

	public Boolean isActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}
