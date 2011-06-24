package com.antares.commons.util.jqgrid;

import java.util.List;

/**
 * Modelo para representar la grilla construida con jQGrid 
 * 
 * @version 1.0.0 Created 06/06/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public class JQGridContainer {

	private List<JQGridRow> rows;

	public List<JQGridRow> getRows() {
		return rows;
	}
	public void setRows(List<JQGridRow> rows) {
		this.rows = rows;
	}

}
