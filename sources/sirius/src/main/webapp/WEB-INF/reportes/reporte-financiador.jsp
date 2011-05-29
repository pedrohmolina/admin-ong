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
	hacerSubmit('reportes/reporte-financiador.do?method=initForm');
}

function confirmarAccion(mensaje) {
	return confirm(mensaje);
}

</script>

<div class="form">
	<html:form action="/reportes/reporte-financiador.do?method=generarReporteFinanciador">
	<h1>Reporte de Financiadores</h1>
	
	<div style="float:left; width: 100%;">
		<p>
		<label for="nombre"><bean:message key="sirius.financiador.nombre.label" />:</label>
		<html:text property="nombre" />
		</p><br><p>
		<label for="cuit"><bean:message key="sirius.financiador.cuit.label" />:</label>
		<html:text property="cuit" />
		</p><br><p>
		<label for="cbu"><bean:message key="sirius.financiador.cbu.label" />:</label>
		<html:text property="cbu" />
		</p><br><p>

		<label for="formatosReporte"><bean:message key="sirius.reportes.formatoSalida" />:</label>
		<html:select property="formatoReporte">
			<html:optionsCollection name="reporteFinanciadorForm" property="formatosReporte" label="descripcion" value="descripcion"/>
		</html:select>
		</p><br>

	</div>

	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="#" onclick="javascript:limpiarFiltro();"><bean:message key="antares.base.limpiarfiltro.label"/></a>
			<a href="#" onclick="reporteFinanciadorForm.submit();"><bean:message key="sirius.reportes.generar" /></a>
		</div>
	</div>
	
	</html:form>
	
	<div style="clear: both;">
		<html:errors />
	</div>

</div>
