package com.antares.sirius.service.impl;

import java.util.Collection;

import com.antares.commons.service.impl.BusinessEntityServiceImpl;
import com.antares.sirius.dao.RubroDAO;
import com.antares.sirius.model.Rubro;
import com.antares.sirius.service.RubroService;

/**
 * Implementacion de la interfaz RubroService.
 *
 * @version 1.0.0 Created 23/11/2010 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com> Julian Martinez </a>
 *
 */
public class RubroServiceImpl extends BusinessEntityServiceImpl<Rubro, RubroDAO> implements RubroService {

	public Collection<Rubro> findByIds(Integer[] ids) {
		Collection<Rubro> rubros;
		if (ids == null) {
			rubros = dao.findAll();
		} else {
			rubros = dao.findByIds(ids);
		}
		return rubros;
	}

	public boolean isNombreRepetido(String nombre, Integer id) {
		boolean isNombreRepetido = false;
		Rubro entity = dao.findByNombre(nombre);
		if (entity != null) {
			isNombreRepetido = !entity.getId().equals(id);
		}
		return isNombreRepetido;
	}

	public Collection<Rubro> findPrimerNivel() {
		return dao.findPrimerNivel();
	}

	public Collection<Rubro> findHijos(Integer id) {
		return dao.findHijos(id);
	}

}
