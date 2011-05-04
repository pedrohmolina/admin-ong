<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean-el.tld" prefix="bean-el"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag-el.tld" prefix="display-el"%>
<%@ taglib uri="/WEB-INF/tlds/authz.tld" prefix="authz"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>

<script>
function limpiarFiltro(){
	hacerSubmit('reportes/reporte-persona.do?method=initForm');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}

</script>

<div class="form">
	<html:form action="/reportes/reporte-persona.do?method=generarReportePersona">
	<h1>Reporte de Personas</h1>
	
	<div style="float:left; width: 100%;">
		<label for="apellido"><bean:message key="sirius.persona.apellido.label" />:</label>
		<html:text property="apellido" />
		<br>
		<label for="nombre"><bean:message key="sirius.persona.nombre.label" />:</label>
		<html:text property="nombre" />
		<br>
		<label for="cuit"><bean:message key="sirius.persona.cuit.label" />:</label>
		<html:text property="cuit" />
		<br>
		<label for="numeroDocumento"><bean:message key="sirius.persona.numeroDocumento.label" />:</label>
		<html:text property="numeroDocumento" />
		<br>
		<label for="formatosReporte"><bean:message key="sirius.reportes.formatoSalida" />:</label>
		<html:select property="formatoReporte">
			<html:optionsCollection name="reportePersonaForm" property="formatosReporte" label="descripcion" value="descripcion"/>
		</html:select>
		
		<br>
	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label"/></a>
			<a href="#" onclick="reportePersonaForm.submit();"><bean:message key="sirius.reportes.generar" /></a>
		</div>
	</div>
	
	</html:form>
	
	<div style="clear: both;">
		<html:errors />
	</div>

</div>
