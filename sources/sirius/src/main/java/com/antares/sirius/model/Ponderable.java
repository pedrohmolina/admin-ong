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

	protected static BigDecimal calcularCompletitud(Collection<? extends Ponderable> ponderables) {
		BigDecimal completitud = BigDecimal.ZERO;
		if (ponderables != null && ponderables.size() > 0) {
			for (Ponderable ponderable : ponderables) {
				if (ponderable.isActivo()) {
					BigDecimal completitudItem = BigDecimal.valueOf(ponderable.getCompletitud()).multiply(BigDecimal.valueOf(ponderable.getPonderacion()));
					completitudItem = completitudItem.divide(BigDecimal.valueOf(100)); 
					completitud = completitud.add(completitudItem);
				}
			}
		}
		return completitud;
	}
	
	public abstract Double getCompletitud(); 

}
