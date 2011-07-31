package com.antares.sirius.view.action;

import static ar.com.fdvs.dj.domain.AutoText.ALIGNMENT_RIGHT;
import static ar.com.fdvs.dj.domain.AutoText.AUTOTEXT_PAGE_X_SLASH_Y;
import static ar.com.fdvs.dj.domain.AutoText.POSITION_FOOTER;
import static ar.com.fdvs.dj.domain.ImageBanner.ALIGN_RIGHT;
import static ar.com.fdvs.dj.domain.ImageBanner.ALIGN_LEFT;
import static ar.com.fdvs.dj.domain.constants.Border.NO_BORDER;
import static ar.com.fdvs.dj.domain.constants.Border.PEN_2_POINT;
import static ar.com.fdvs.dj.domain.constants.Border.THIN;
import static ar.com.fdvs.dj.domain.constants.Font.VERDANA_MEDIUM;
import static ar.com.fdvs.dj.domain.constants.Font.VERDANA_MEDIUM_BOLD;
import static ar.com.fdvs.dj.domain.constants.Font._FONT_TIMES_NEW_ROMAN;
import static ar.com.fdvs.dj.domain.constants.Font._FONT_VERDANA;
import static ar.com.fdvs.dj.domain.constants.HorizontalAlign.CENTER;
import static ar.com.fdvs.dj.domain.constants.Transparency.OPAQUE;
import static ar.com.fdvs.dj.domain.constants.VerticalAlign.MIDDLE;
import static com.antares.sirius.base.Constants.ANTARES_LOGO;
import static com.antares.sirius.base.Constants.SIRIUS_LOGO;
import static java.awt.Color.BLACK;
import static java.awt.Color.DARK_GRAY;
import static java.awt.Color.GRAY;
import static java.awt.Color.LIGHT_GRAY;
import static java.awt.Color.WHITE;
import static net.sf.jasperreports.engine.JRExporterParameter.JASPER_PRINT;
import static net.sf.jasperreports.engine.JRExporterParameter.OUTPUT_STREAM;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.json.JSONObject;

import org.apache.struts.actions.DispatchAction;

import ar.com.fdvs.dj.domain.DJCrosstabColumn;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.CrosstabColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

import com.antares.commons.enums.FormatoReporteEnum;

/**
 * Controlador abstracto del que heredan los controladores de reportes 
 * 
 * @version 1.0.0 Created by Pablo Delfino
 * @author <a href:mailto:pnicdelfino@gmail.com>Pablo Delfino</a>

 * @version 2.0.0 Created 12/07/2011 by Julian Martinez
 * @author <a href:mailto:otakon@gmail.com>Julian Martinez</a>
 *
 */
public abstract class ReporteAction extends DispatchAction {
	
	
	/**
	 * Genera el reporte a los parametros ingresados
	 * 
	 * @param request
	 * @param response
	 * @param reportType
	 * @param jasperPrint
	 * @throws Exception
	 */
	protected void generateReport(HttpServletRequest request, HttpServletResponse response, Integer reportType, JasperPrint jasperPrint) throws Exception {
		
		OutputStream ouputStream = response.getOutputStream();
		JRExporter exporter = null;
		
		FormatoReporteEnum formatoReporte = FormatoReporteEnum.findById(reportType);
		switch (formatoReporte) {
			case PDF:
				setContentHeader(response, getFileName(), formatoReporte.getExtension());
			    exporter = new JRPdfExporter();
			    exporter.setParameter(JASPER_PRINT, jasperPrint);
			    exporter.setParameter(OUTPUT_STREAM, ouputStream);
				break;
			case RTF:
				setContentHeader(response, getFileName(), formatoReporte.getExtension());
			    exporter = new JRRtfExporter();
			    exporter.setParameter(JASPER_PRINT, jasperPrint);
			    exporter.setParameter(OUTPUT_STREAM, ouputStream);
				break;
			case XLS:
				setContentHeader(response, getFileName(), formatoReporte.getExtension());
			    exporter = new JRXlsExporter();
			    exporter.setParameter(JASPER_PRINT, jasperPrint);
			    exporter.setParameter(OUTPUT_STREAM, ouputStream);
				break;
			case CSV:
				setContentHeader(response, getFileName(), formatoReporte.getExtension());
			    exporter = new JRCsvExporter();
			    exporter.setParameter(JASPER_PRINT, jasperPrint);
			    exporter.setParameter(OUTPUT_STREAM, ouputStream);
				break;
			default: break;
		}

		try {
		    exporter.exportReport();
		} finally {
		    if (ouputStream != null) {
		        try {
		            ouputStream.close();
		        } catch (IOException ex) {
		        }
		    }
		}
	}

	protected void setContentHeader(HttpServletResponse response, String fileName, String format) {
	    response.setContentType("application/" + format);
	    response.setHeader("Content-Disposition", "attachment; filename=" + fileName + "." + format);
	}

	protected DynamicReportBuilder getDynamicReport(String title, String subtitle, String reportName) {
		
		DynamicReportBuilder drb = new DynamicReportBuilder();
		drb.setTitle(title);	
		drb.setSubtitle(subtitle);
		drb.setReportName(reportName);		
	
   		Style oddRowStyle = new Style();
  		oddRowStyle.setBorder(NO_BORDER); oddRowStyle.setBackgroundColor(LIGHT_GRAY);oddRowStyle.setTransparency(OPAQUE);
		Integer margin = new Integer(15);
		Style atStyle2 = new StyleBuilder(true).setFont(new Font(9, _FONT_TIMES_NEW_ROMAN, false, true, false)).setTextColor(BLACK).build();
		drb.addAutoText(AUTOTEXT_PAGE_X_SLASH_Y, POSITION_FOOTER, ALIGNMENT_RIGHT,30,30,atStyle2);
		drb.setPageSizeAndOrientation(Page.Page_A4_Landscape());
		drb.setUseFullPageWidth(true); 

		drb.setTitleStyle(getTitleStyle());
		drb.setDefaultStyles(getTitleStyle(), getSubtitleStyle(), getHeaderStyle(), getDetailStyle());
		drb.setDetailHeight(new Integer(15));
		drb.setLeftMargin(margin);
		drb.setRightMargin(margin);
		drb.setTopMargin(margin);
		drb.setBottomMargin(margin);
		drb.setPrintBackgroundOnOddRows(true);
		drb.setOddRowBackgroundStyle(oddRowStyle);
		drb.addFirstPageImageBanner(ANTARES_LOGO, new Integer(90), new Integer(20), ALIGN_LEFT);
		drb.addFirstPageImageBanner(SIRIUS_LOGO, new Integer(120), new Integer(50), ALIGN_RIGHT);  
		return drb;
	}
	
	protected Style getHeaderStyle() {
		Style headerStyle = new Style();
  		headerStyle.setFont(VERDANA_MEDIUM_BOLD);
  		headerStyle.setBorderBottom(PEN_2_POINT);
  		headerStyle.setHorizontalAlign(CENTER);
  		headerStyle.setVerticalAlign(MIDDLE);
  		headerStyle.setBackgroundColor(DARK_GRAY);
  		headerStyle.setTextColor(WHITE);
  		headerStyle.setTransparency(OPAQUE);
		return headerStyle;
	}

	protected Style getTitleStyle() {
		Style titleStyle = new Style();
		titleStyle.setFont(new Font(18,_FONT_VERDANA,true));
		titleStyle.setHorizontalAlign(CENTER);
		return titleStyle;
	}

	protected Style getSubtitleStyle() {
		Style titleStyle = new Style();
		titleStyle.setFont(new Font(16,_FONT_VERDANA,true));
		return titleStyle;
	}

	protected Style getDetailStyle() {
		Style detailStyle = new Style();
 		detailStyle.setFont(new Font(9,_FONT_VERDANA,false));
  		detailStyle.setHorizontalAlign(CENTER);
  		detailStyle.setBorderBottom(THIN);
		return detailStyle;
	}

	protected Style getColHeaderStyle() {
		StyleBuilder sb = new StyleBuilder(false);
		sb.setFont(VERDANA_MEDIUM_BOLD);
		sb.setBorderBottom(PEN_2_POINT);
		sb.setHorizontalAlign(CENTER);
		sb.setVerticalAlign(MIDDLE);
		sb.setBackgroundColor(DARK_GRAY);
		sb.setTextColor(WHITE);
		sb.setTransparency(OPAQUE);
		return sb.build();
	}

	protected Style getTotalStyle() {
		StyleBuilder sb = new StyleBuilder(false);
		sb.setPattern("#,###.##");
		sb.setHorizontalAlign(CENTER);
		sb.setFont(VERDANA_MEDIUM_BOLD);
		sb.setBorderBottom(PEN_2_POINT);
		sb.setVerticalAlign(MIDDLE);
		sb.setBackgroundColor(GRAY);
		sb.setTextColor(BLACK);
		sb.setTransparency(OPAQUE);
		return sb.build();
	}
	
	protected static Style getMeasureStyle() {
		StyleBuilder sb = new StyleBuilder(false);
		sb.setPattern("#,###.##");
		sb.setHorizontalAlign(CENTER);
		sb.setFont(VERDANA_MEDIUM);
		sb.setBackgroundColor(WHITE);
		sb.setTextColor(BLACK);
		return sb.build();
	}
	
	@SuppressWarnings("unchecked") 
	protected AbstractColumn getColumn(String property, Class type, String title, Integer width, Style headerStyle, Style detailStyle) throws ColumnBuilderException {
		ColumnBuilder cb = ColumnBuilder.getNew();
		cb.setColumnProperty(property, type.getName());
		cb.setTitle(title);
		cb.setWidth(width);
		cb.setStyle(detailStyle);
		cb.setHeaderStyle(headerStyle);
		return cb.build();
	}

	@SuppressWarnings("unchecked") 
	protected DJCrosstabColumn getCrosstabColumn(String property, Class type, String title, Integer width, Style headerStyle, Style detailStyle) throws ColumnBuilderException {
		CrosstabColumnBuilder cb = new CrosstabColumnBuilder();
		cb.setProperty(property, type.getName());
		cb.setTitle(title);
		cb.setWidth(width);
//		cb.setStyle(detailStyle);
		cb.setHeaderStyle(headerStyle);
		return cb.build();
	}

	/**
	 * Convierte el mapa clave-valor pasar por parametro en un objeto JSON para enviar a la pagina.
	 * Usado para cargar datos en los combos.
	 * 
	 * @param response response http
	 * @param map mapa clave-valor con los elementos a enviar
	 * @throws IOException
	 */
	protected void sendJSON(HttpServletResponse response, Map<String, String> map) throws IOException {
		JSONObject jsonMap = JSONObject.fromObject(map);
		response.setContentType("text/html; charset=iso-8859-1");
		response.getOutputStream().print(jsonMap.toString());
	}

	/**
	 * Devuelve la lista de formatos posibles de salida para la generacion de los reportes

	 * @return
	 */
	protected List<FormatoReporteEnum> getReportFormatList(){
		List<FormatoReporteEnum> formatos = new ArrayList<FormatoReporteEnum>();
		for (FormatoReporteEnum formatoReporteEnum : FormatoReporteEnum.values()) {
			formatos.add(formatoReporteEnum);
		}
		return formatos;
	}

	protected abstract String getFileName();

}
