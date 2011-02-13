package com.antares.sirius.model;



@SuppressWarnings("serial")
public class GastoProyecto extends Gasto {

	private Proyecto proyecto;

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

}
