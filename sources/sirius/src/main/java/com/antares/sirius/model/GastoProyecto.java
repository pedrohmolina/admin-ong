package com.antares.sirius.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Entity
@DiscriminatorValue("2")
@SuppressWarnings("serial")
public class GastoProyecto extends Gasto {

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idProyecto"))
    private Proyecto proyecto;

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

}
