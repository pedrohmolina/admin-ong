package com.antares.commons.util.jqgrid;

import java.util.ArrayList;
import java.util.List;

/**
 * Modelo para representar una fila de la grilla construida con jQGrid 
 * 
 * @version 1.0.0 Created 06/06/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public class JQGridRow {

	private Integer id;
	private List<String> cell = new ArrayList<String>();

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<String> getCells() {
		return cell;
	}
	public void setCells(List<String> cells) {
		this.cell = cells;
	}

}
