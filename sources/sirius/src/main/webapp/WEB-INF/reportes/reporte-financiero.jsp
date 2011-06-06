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
	hacerSubmit('reportes/reporte-financiero.do?method=initForm');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}

function cargarComboObjetivoGeneral(select, destinationCombo){
 	$("#" + destinationCombo).removeOption(/^[^-]/i);
 	$("#" + destinationCombo).val("");

 	var selectedOption = $(select).val();
	url = "<c:url value='/reportes/reporte-financiero.do?method=cargarComboObjetivoGeneral' />";
	$("#" + destinationCombo).ajaxAddOption(url, {idProyecto:selectedOption}, false);
}

function cargarComboObjetivoEspecifico(select, destinationCombo){
 	$("#" + destinationCombo).removeOption(/^[^-]/i);
 	$("#" + destinationCombo).val("");

 	var selectedOption = $(select).val();
	url = "<c:url value='/reportes/reporte-financiero.do?method=cargarComboObjetivoEspecifico' />";
	$("#" + destinationCombo).ajaxAddOption(url, {idObjetivoGeneral:selectedOption}, false);
}

function cargarComboMeta(select, destinationCombo){
 	$("#" + destinationCombo).removeOption(/^[^-]/i);
 	$("#" + destinationCombo).val("");

 	var selectedOption = $(select).val();
	url = "<c:url value='/reportes/reporte-financiero.do?method=cargarComboMeta' />";
	$("#" + destinationCombo).ajaxAddOption(url, {idObjetivoEspecifico:selectedOption}, false);
}

function cargarComboActividad(select, destinationCombo){
 	$("#" + destinationCombo).removeOption(/^[^-]/i);
 	$("#" + destinationCombo).val("");

 	var selectedOption = $(select).val();
	url = "<c:url value='/asignacion/asignacion-query.do?method=cargarComboActividad' />";
	$("#" + destinationCombo).ajaxAddOption(url, {idMeta:selectedOption}, false);
}

</script>

<div class="form">
	<html:form action="/reportes/reporte-financiero.do?method=generarReporteFinanciero">
	<h1>Reporte Financiero</h1>
	
	<div style="float:left; width: 100%;">
	<p>
		<h2>Nivel</h2>
		
			<label for="filtroIdProyecto"><bean:message key="sirius.asignacion.proyecto.label" />&nbsp;:</label>
			<html:select property="filtroIdProyecto" styleId="proyecto" onchange="cargarComboObjetivoGeneral(this, 'objetivoGeneral');">
				<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
				<html:optionsCollection name="reporteFinancieroForm" property="proyectos" label="nombre" value="id"/>
			</html:select>
			</p><br><p>
			
			<label for="idObjetivoGeneral"><bean:message key="sirius.objetivoEspecifico.objetivoGeneral.label" />&nbsp;:</label>
			<html:select property="idObjetivoGeneral" styleId="objetivoGeneral" onchange="cargarComboObjetivoEspecifico(this, 'objetivoEspecifico');">
				<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
				<html:optionsCollection name="reporteFinancieroForm" property="objetivosGenerales" label="nombre" value="id"/>
			</html:select>
			</p><br><p>

			<label for="idObjetivoEspecifico"><bean:message key="sirius.meta.objetivoEspecifico.label" />&nbsp;:</label>
			<html:select property="idObjetivoEspecifico" styleId="objetivoEspecifico" onchange="cargarComboMeta(this, 'meta');">
				<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
				<html:optionsCollection name="reporteFinancieroForm" property="objetivosEspecificos" label="nombre" value="id"/>
			</html:select>
			</p><br><p>
			
			<label for="idMeta"><bean:message key="sirius.actividad.meta.label" />(*)&nbsp;:</label>
			<html:select property="idMeta" styleId="meta" onchange="cargarComboActividad(this, 'actividad');">
				<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
				<html:optionsCollection name="reporteFinancieroForm" property="metas" label="nombre" value="id"/>
			</html:select>
			</p><br><p>
			
			<label for="filtroIdActividad"><bean:message key="sirius.asignacion.actividad.label" />&nbsp;:</label>
			<html:select property="filtroIdActividad" styleId="actividad">
				<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
				<html:optionsCollection name="reporteFinancieroForm" property="actividades" label="nombre" value="id"/>
			</html:select>
			</p><br><p>

	</p><br><p>
	</div>
	
	<div style="float:left; width: 100%;">
	<p>
		<h2>Rubro</h2>

	</p><br><p>
	</div>
		
	<div style="float:left; width: 100%;">
	<p>
		<h2>Informacion</h2>

	</p><br><p>
	</div>
	
	
	</html:form>

	<div style="clear: both;">
		<html:errors />
	</div>

</div>
