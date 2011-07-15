package com.antares.commons.enums;

public enum FormatoReporteEnum {
	PDF(1, "PDF", "pdf"),
	XLS(2, "XLS", "xls"),
	CSV(3, "CSV", "csv"),
	HTM(4, "HTM", "htm"),
	RTF(5, "RTF", "rtf");
	
	protected Integer id;
	private String descripcion;
	private String extension;

	private FormatoReporteEnum(Integer id, String descripcion, String extension) {
		this.id = id;
		this.descripcion = descripcion;
		this.extension = extension;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public static FormatoReporteEnum findById(Integer id) {
		FormatoReporteEnum formatoReporteEnum = null;
		for (FormatoReporteEnum tipo : FormatoReporteEnum.values()) {
			if (tipo.getId().equals(id)) {
				formatoReporteEnum = tipo; 
			}
		}
		return formatoReporteEnum; 
	}

}
