package com.antares.sirius.view.action;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import net.sf.json.JSONObject;

import org.apache.struts.actions.DispatchAction;

import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.ImageBanner;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;

import com.antares.sirius.base.Constants;

public class ReporteAction extends DispatchAction {
	
	
	/**
	 * Genera el reporte a los parametros ingresados
	 * 
	 * @param request
	 * @param response
	 * @param reportType
	 * @param jasperPrint
	 * @throws Exception
	 */
	public void generateReport(HttpServletRequest request, HttpServletResponse response, String reportType, JasperPrint jasperPrint, String fileName) throws Exception {
		
		OutputStream ouputStream = response.getOutputStream();
		JRExporter exporter = null;
		
		if( Constants.FORMATO_REPORTE_PDF.equalsIgnoreCase(reportType) )
		{
		    response.setContentType("application/pdf");
		    response.setHeader("Content-Disposition", "attachment; filename="+fileName
		    		+"."+Constants.FORMATO_REPORTE_PDF);
		    
		    exporter = new JRPdfExporter();
		    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
		}
		else if( Constants.FORMATO_REPORTE_RTF.equalsIgnoreCase(reportType) )
		{
		    response.setContentType("application/rtf");
		    response.setHeader("Content-Disposition", "attachment; filename="+fileName
		    		+"."+Constants.FORMATO_REPORTE_RTF);

		    exporter = new JRRtfExporter();
		    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
		}
		else if( Constants.FORMATO_REPORTE_HTM.equalsIgnoreCase(reportType) )
		{
		    exporter = new JRHtmlExporter();
		    request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, jasperPrint);
		    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
		    // gonna set url pattern given for Image servlet with a reponse parameter <url-pattern>/image</url-pattern>
		    exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,"image?image=");
		   // exporter.exportReport();
		}
		else if( Constants.FORMATO_REPORTE_XLS.equalsIgnoreCase(reportType) )
		{
		    response.setContentType("application/xls");
		    response.setHeader("Content-Disposition", "attachment; filename="+fileName
		    		+"."+Constants.FORMATO_REPORTE_XLS);

		    exporter = new JRXlsExporter();
		    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
		}
		else if( Constants.FORMATO_REPORTE_CSV.equalsIgnoreCase(reportType) )
		{
		    response.setContentType("application/csv");
		    response.setHeader("Content-Disposition", "attachment; filename="+fileName
		    		+"."+Constants.FORMATO_REPORTE_CSV);

		    exporter = new JRCsvExporter();
		    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		    exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
		}

		try
		{
		    exporter.exportReport();
		}
		catch (JRException e)
		{
		    throw new ServletException(e);
		}
		finally
		{
		    if (ouputStream != null)
		    {
		        try
		        {
		            ouputStream.close();
		        }
		        catch (IOException ex)
		        {
		        }
		    }
		}
	}

	public static DynamicReportBuilder getDynamicReport(String title, String subtitle, String reportName){
		
		DynamicReportBuilder drb = new DynamicReportBuilder();
		drb.setTitle(title)	
		        .setSubtitle(subtitle)
		        .setReportName(reportName);		
	
   		Style oddRowStyle = new Style();
  		oddRowStyle.setBorder(Border.NO_BORDER); oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);oddRowStyle.setTransparency(Transparency.OPAQUE);
		Integer margin = new Integer(15);
		Style atStyle2 = new StyleBuilder(true).setFont(new Font(9, Font._FONT_TIMES_NEW_ROMAN, false, true, false)).setTextColor(Color.BLACK).build();
		drb.addAutoText(AutoText.AUTOTEXT_PAGE_X_SLASH_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT,30,30,atStyle2);
		drb.setPageSizeAndOrientation(Page.Page_A4_Landscape());
		drb.setUseFullPageWidth(true); 

		drb.setTitleStyle(getTitleStyle())
			.setDefaultStyles(getTitleStyle(), getSubtitleStyle(), getHeaderStyle(), getDetailStyle())
			.setDetailHeight(new Integer(15))
			.setLeftMargin(margin)
			.setRightMargin(margin)
			.setTopMargin(margin)
			.setBottomMargin(margin)
			.setPrintBackgroundOnOddRows(true)
			.setOddRowBackgroundStyle(oddRowStyle)
			.addFirstPageImageBanner(Constants.ANTARES_LOGO, new Integer(90), new Integer(20), ImageBanner.ALIGN_RIGHT);
		  ;
		  
		return drb;
	}
	
	public static Style getHeaderStyle(){
		Style headerStyle = new Style();
  		headerStyle.setFont(Font.VERDANA_MEDIUM_BOLD);
  		headerStyle.setBorderBottom(Border.PEN_2_POINT);
  		headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
  		headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
  		headerStyle.setBackgroundColor(Color.DARK_GRAY);
  		headerStyle.setTextColor(Color.WHITE);
  		headerStyle.setTransparency(Transparency.OPAQUE);
		return headerStyle;
	}

	public static Style getTitleStyle(){
		Style titleStyle = new Style();
		titleStyle.setFont(new Font(18,Font._FONT_VERDANA,true));
		titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
		return titleStyle;
	}

	public static Style getSubtitleStyle(){
		Style titleStyle = new Style();
		titleStyle.setFont(new Font(16,Font._FONT_VERDANA,true));
		return titleStyle;
	}

	public static Style getDetailStyle(){
		Style detailStyle = new Style();
 		detailStyle.setFont(new Font(9,Font._FONT_VERDANA,false));
  		detailStyle.setHorizontalAlign(HorizontalAlign.CENTER);
  		detailStyle.setBorderBottom(Border.THIN);
		return detailStyle;
	}

	public static AbstractColumn getColumn(String property, @SuppressWarnings("rawtypes") Class type,
			String title, int width, Style headerStyle, Style detailStyle)
			throws ColumnBuilderException {

		AbstractColumn columnState = ColumnBuilder.getNew()
			.setColumnProperty(property, type.getName()).setTitle(title).setWidth(Integer.valueOf(width))
			.setStyle(detailStyle).setHeaderStyle(headerStyle).build();
		
		return columnState;
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


}
