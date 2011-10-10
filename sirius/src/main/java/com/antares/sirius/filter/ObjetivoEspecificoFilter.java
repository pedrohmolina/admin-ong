package com.antares.sirius.filter;

import com.antares.commons.filter.Filter;
import com.antares.sirius.model.ObjetivoEspecifico;
import com.antares.sirius.model.ObjetivoGeneral;

/**
 * Filter para la endidad ObjetivoEspecifico.
 *
 * @version 1.0.0 Created 23/01/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 * @param <T> clase de la entidad de modelo
 */
public class ObjetivoEspecificoFilter extends Filter<ObjetivoEspecifico> {

	private ObjetivoGeneral objetivoGeneral;
	private String nombre;

	public ObjetivoGeneral getObjetivoGeneral() {
		return objetivoGeneral;
	}
	public void setObjetivoGeneral(ObjetivoGeneral objetivoGeneral) {
		this.objetivoGeneral = objetivoGeneral;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
