package com.antares.sirius.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Entity
@SuppressWarnings("serial")
public class Presupuesto extends PersistentObject {

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idTipoPresupuesto"))
	private TipoPresupuesto tipoPresupuesto;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idProyecto"))
	private Proyecto proyecto;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idActividad"))
	private Actividad actividad;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idRubro"))
	private Rubro rubro;

	private Double monto;

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public TipoPresupuesto getTipoPresupuesto() {
		return tipoPresupuesto;
	}

	public void setTipoPresupuesto(TipoPresupuesto tipoPresupuesto) {
		this.tipoPresupuesto = tipoPresupuesto;
	}

}
