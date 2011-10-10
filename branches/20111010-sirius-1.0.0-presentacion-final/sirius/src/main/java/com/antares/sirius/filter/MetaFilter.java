package com.antares.sirius.filter;

import com.antares.commons.filter.Filter;
import com.antares.sirius.model.Meta;
import com.antares.sirius.model.ObjetivoEspecifico;

/**
 * Filter para la endidad Meta.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
public class MetaFilter extends Filter<Meta> {

	private ObjetivoEspecifico objetivoEspecifico;
	private String nombre;

	public ObjetivoEspecifico getObjetivoEspecifico() {
		return objetivoEspecifico;
	}
	public void setObjetivoEspecifico(ObjetivoEspecifico objetivoEspecifico) {
		this.objetivoEspecifico = objetivoEspecifico;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
