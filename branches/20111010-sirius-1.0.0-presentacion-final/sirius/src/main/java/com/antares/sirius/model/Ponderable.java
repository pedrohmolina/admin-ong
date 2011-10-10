package com.antares.sirius.model;

import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class Ponderable extends BusinessObject {
	
	protected Integer ponderacion;

	public Integer getPonderacion() {
		return ponderacion;
	}

	public void setPonderacion(Integer ponderacion) {
		this.ponderacion = ponderacion;
	}

	/**
	 * Suma la ponderacion de todos los elementos ponderables que contiene, exceptuando la de un elemento particular
	 * (para el caso en que se esté editando ese elemento)  
	 * 
	 * @param idPonderacionEditada id del elemento que se esta editando y que no se quiere que sea tenido en cuenta 
	 * 		al calcular la ponderacion total
	 * @return sumatoria de las ponderaciones de todos los elementos ponderables que contiene
	 */
	public Integer ponderacionTotal(Integer idPonderacionEditada) {
		Integer ponderacion = 0;
		for (Ponderable ponderable : getPonderables()) {
			if (ponderable.isActivo() && (!ponderable.getId().equals(idPonderacionEditada))) {
				ponderacion += ponderable.getPonderacion();
			}
		}
		return ponderacion;
	}

	/**
	 * Calcula la completitud en base a la completitud de los elementos hijos y sus ponderaciones respectivas
	 * 
	 * @return
	 */
	public Double getCompletitud() {
		Double completitud = 0D;
		for (Ponderable ponderable : getPonderables()) {
			if (ponderable.isActivo()) {
				BigDecimal completitudItem = BigDecimal.valueOf(ponderable.getCompletitud()).multiply(BigDecimal.valueOf(ponderable.getPonderacion()));
				completitudItem = completitudItem.divide(BigDecimal.valueOf(100)); 
				completitud = BigDecimal.valueOf(completitud).add(completitudItem).doubleValue();
			}
		}
		return completitud;
	}
	
	/**
	 * Devuelve los elementos ponderables que contiene
	 * 
	 * @return coleccion de elementos ponderables
	 */
	public abstract Collection<? extends Ponderable> getPonderables(); 

}
