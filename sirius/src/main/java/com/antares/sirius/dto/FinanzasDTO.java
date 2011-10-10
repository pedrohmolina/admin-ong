package com.antares.sirius.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * Representacion de las finanzas del proyecto, usando los montos (presupuestados e incurridos) por cada rubro 
 * 
 * @version 1.0.0 Created 12/07/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
@SuppressWarnings("serial")
public class FinanzasDTO implements Serializable {

	// Mapas indexados por id de agregacion y rubro
	private Map<Integer, Map<Integer, MontoDTO>> finanzasPorNivel = new HashMap<Integer, Map<Integer, MontoDTO>>();

	/**
	 * Agrega un monto a las finanzas.

	 * @param monto a agregar
	 * @return
	 */
	public MontoDTO putMonto(MontoDTO monto) {
		Map<Integer, MontoDTO> mapaPorRubro = finanzasPorNivel.get(monto.getId());
		if (mapaPorRubro == null) {
			mapaPorRubro = new HashMap<Integer, MontoDTO>(); 
			finanzasPorNivel.put(monto.getId(), mapaPorRubro);
		}
		mapaPorRubro.put(monto.getIdRubro(), monto);
		return finanzasPorNivel.get(monto.getId()).get(monto.getIdRubro());
	}

	/**
	 * Obtiene el monto correspondiente al id del nivel de agregacion y al id del rubro correspondientes.
	 * 
	 * @param id id del nivel de agregacion
	 * @param idRubro idd del rubro
	 * @return
	 */
	public MontoDTO get(Integer id, Integer idRubro) {
		MontoDTO monto = null;
		Map<Integer, MontoDTO> mapaPorRubro = finanzasPorNivel.get(id);
		if (mapaPorRubro != null) {
			monto = mapaPorRubro.get(idRubro); 
		}
		return monto;
	}

}
