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
$(document).ready(function() {
	changeProyecto();
})

function changeProyecto() {
	$("#divAgregacion input").attr("checked", false);

	$("#idAgregacion").attr("disabled", true);
 	$("#idAgregacion").removeOption(/^[^-]/i);
 	$("#idAgregacion").val("");

	if ($("#proyecto").val() == "") {
		$("#divAgregacion input").attr("disabled", true);
	} else {
		$("#divAgregacion input").attr("disabled", false);
		$("#radioProyecto").attr("checked", true);
	}
}

function cargarCombo(method){
	$("#idAgregacion").attr("disabled", false);
 	$("#idAgregacion").removeOption(/^[^-]/i);
 	$("#idAgregacion").val("");
 	if (method != '') {
	 	var selectedOption = $("#proyecto").val();
		url = "<c:url value='/reportes/reporte-financiero.do?method=' />" + method;
		$("#idAgregacion").ajaxAddOption(url, {idProyecto:selectedOption}, false);
	} else {
		$("#idAgregacion").attr("disabled", true);
	}
}

</script>

<div class="form">
	<html:form action="/reportes/reporte-financiero-validate.do?method=generarReporteFinanciero">
	
	<div style="float:left; width: 100%;">
		<p>		
		<label for="filtroIdProyecto"><bean:message key="sirius.reportes.finanzas.proyecto.label" />&nbsp;:</label>
		<html:select property="idProyecto" styleId="proyecto" onchange="changeProyecto();">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="reporteFinancieroForm" property="proyectos" label="nombre" value="id"/>
		</html:select>
		</p><br>
	</div>

	<div style="float:left; width: 100%;" id="divAgregacion">
		<h2><bean:message key="sirius.reportes.finanzas.nivelAgregacion.label"/></h2>
		<p>
		<label for="tipoAgregacion"><bean:message key="sirius.reportes.finanzas.proyecto.label"/> :</label>
		<html:radio property="tipoAgregacion" value="PROYECTO" styleId="radioProyecto" style="width:20px" onclick="cargarCombo('');" />
		</p><br><p>
		<label for="tipoAgregacion"><bean:message key="sirius.reportes.finanzas.objetivoGeneral.label"/> :</label>
		<html:radio property="tipoAgregacion" value="OBJETIVO_GENERAL" style="width:20px" onclick="cargarCombo('cargarComboObjetivoGeneral');" />
		</p><br><p>
		<label for="tipoAgregacion"><bean:message key="sirius.reportes.finanzas.objetivoEspecifico.label" /> :</label>
		<html:radio property="tipoAgregacion" value="OBJETIVO_ESPECIFICO" style="width:20px" onclick="cargarCombo('cargarComboObjetivoEspecifico');" />
		</p><br><p>
		<label for="tipoAgregacion"><bean:message key="sirius.reportes.finanzas.meta.label" /> :</label>
		<html:radio property="tipoAgregacion" value="META" style="width:20px" onclick="cargarCombo('cargarComboMeta');" />
		</p><br><p>
		<label for="tipoAgregacion"><bean:message key="sirius.reportes.finanzas.actividad.label" /> :</label>
		<html:radio property="tipoAgregacion" value="ACTIVIDAD" style="width:20px" onclick="cargarCombo('cargarComboActividad');" />
		</p><br><p>
		<label for="idAgregacion"><bean:message key="sirius.reportes.finanzas.seleccione.label" />&nbsp;:</label>
		<html:select property="idAgregacion" styleId="idAgregacion">
		</html:select>
		</p><br>
	</div>

	<div style="float:left; width: 100%;">
		<h2><bean:message key="sirius.reportes.finanzas.rubros.label" /></h2>
		<p>		
		<label for="rubrosSeleccionados"><bean:message key="sirius.reportes.finanzas.rubros.label" />&nbsp;:</label>
		<html:select property="rubrosSeleccionados" multiple="true" style="height: 100px">
			<html:optionsCollection name="reporteFinancieroForm" property="rubros" label="nombre" value="id"/>
		</html:select>
		<html:hidden property="rubrosSeleccionados" value="0" />
		</p><br>
	</div>
		
	<div style="float: left; width: 100%;">
		<p>
		<label for="formatosReporte"><bean:message key="sirius.reportes.formatoSalida" />:</label>
		<html:select property="formatoReporte">
			<html:optionsCollection name="reporteFinancieroForm" property="formatosReporte" label="descripcion" value="id"/>
		</html:select>
		</p><br><p>
		</p><br>
	</div>
	
	<div style="float: left; width: 100%;">
		<p>
		<div class="boton">
			<a href="#" onclick="reporteFinancieroForm.submit();"><bean:message key="sirius.reportes.generar" /></a>
			<a href="#" onclick="hideShow('divColumnas');"><bean:message key="antares.base.personalizarColumnas.label"/></a>
		</div>
		</p><br>	
	</div>

	<div id="divColumnas" style="float: left; width: 100%; display: none">
		<h2><bean:message key="sirius.reportes.columnas" /></h2>
		<div>
			<p>
			<label for="verPresupuestado"><bean:message key="sirius.reportes.finanzas.presupuestado.label" />:</label>
			<html:checkbox property="verPresupuestado" value="true" style="width:20px" />
			<html:hidden property="verPresupuestado" value="false" />
			</p><br><p>
			<label for="verGastado"><bean:message key="sirius.reportes.finanzas.gastado.label" />:</label>
			<html:checkbox property="verGastado" value="true" style="width:20px" />
			<html:hidden property="verGastado" value="false" />
			</p><br><p>
			<label for="verDiferencia"><bean:message key="sirius.reportes.finanzas.diferencia.label" />:</label>
			<html:checkbox property="verDiferencia" value="true" style="width:20px" />
			<html:hidden property="verDiferencia" value="false" />
			</p><br>
		</div>
	</div>

	</html:form>

	<div style="clear: both;" class="errores">
		<html:errors />
	</div>

</div>
