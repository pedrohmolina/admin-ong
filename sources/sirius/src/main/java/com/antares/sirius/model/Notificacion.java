package com.antares.sirius.model;

import static java.lang.Boolean.FALSE;
import static javax.persistence.FetchType.LAZY;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;


@Entity
@SuppressWarnings("serial")
public class Notificacion extends BusinessObject {

	private Date fecha;

	@ManyToOne
	@JoinColumns(@JoinColumn(name = "idUsuario"))
	private Usuario usuario;

	@ManyToOne(fetch = LAZY)
	@JoinColumns(@JoinColumn(name = "idProyecto"))
	private Proyecto proyecto;

	private String mensaje;
	private Boolean leida = FALSE;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public Proyecto getProyecto() {
		return proyecto;
	}
	
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public Boolean getLeida() {
		return leida;
	}
	
	public void setLeida(Boolean leida) {
		this.leida = leida;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
