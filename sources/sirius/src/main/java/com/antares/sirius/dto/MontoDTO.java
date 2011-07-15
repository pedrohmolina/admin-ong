package com.antares.sirius.dto;

import java.io.Serializable;


/**
 * Representacion de los montos por rubro para las finanzas del proyecto. 
 * 
 * @version 1.0.0 Created 12/07/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
@SuppressWarnings("serial")
public class MontoDTO implements Serializable {
	
	private Integer id;
	private Integer idRubro;
	private String descripcion;
	private Double montoPresupuestado = 0D;
	private Double montoGastado = 0D;
	private Double montoDif = 0D;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdRubro() {
		return idRubro;
	}
	public void setIdRubro(Integer idRubro) {
		this.idRubro = idRubro;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getMontoPresupuestado() {
		return montoPresupuestado;
	}
	public void setMontoPresupuestado(Double montoPresupuestado) {
		this.montoPresupuestado = montoPresupuestado;
	}
	public Double getMontoGastado() {
		return montoGastado;
	}
	public void setMontoGastado(Double montoGastado) {
		this.montoGastado = montoGastado;
	}
	public Double getMontoDif() {
		return montoDif;
	}
	public void setMontoDif(Double montoDif) {
		this.montoDif = montoDif;
	}

}
