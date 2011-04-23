<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html-el"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag-el.tld" prefix="display-el"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/authz.tld" prefix="authz"%>

<script>
$(document).ready(function(){
	changeTipoIngreso();
})

function changeTipoIngreso() {
	var idTipoIngreso = $("#tipoIngreso").val();
	
	url = "<c:url value='/ingreso/ingreso-form.do?method=isFinanciacion' />";
	$.getJSON(url, {idTipoIngreso:idTipoIngreso}, function(json){
		if (json.isFinanciacion == "true") {
			$("#comboFinanciador").attr("disabled", false);
		} else {
			$("#comboFinanciador").attr("disabled", true);
		}
	});
}
</script>

<div class="form">
<html:form action="/ingreso/ingreso-form-validate.do?method=save" styleId="abmForm">

	<h1>Datos</h1>
	<div style="float:left;">
		<label for="idTipoIngreso"><bean:message key="sirius.ingreso.tipoIngreso.label" />(*)&nbsp;:</label>
		<html:select property="idTipoIngreso" onchange="changeTipoIngreso();" styleId="tipoIngreso">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="ingresoForm" property="tiposIngreso" label="descripcion" value="id"/>
		</html:select>
		<br>
		<label for="monto"><bean:message key="sirius.ingreso.monto.label" />(*)&nbsp;:</label>
		<html:text property="monto" />
		<br>
		<label for="fecha"><bean:message key="sirius.ingreso.fecha.label" />(*)&nbsp;:</label>
		<html:text property="fecha" />
		<br>
		<label for="descripcion"><bean:message key="sirius.ingreso.descripcion.label" />:</label>
		<html:textarea property="descripcion" rows="5" />
		<br>
		<label for="idFinanciador"><bean:message key="sirius.ingreso.financiador.label" />&nbsp;:</label>
		<html:select property="idFinanciador" styleId="comboFinanciador">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="ingresoForm" property="financiadores" label="nombre" value="id"/>
		</html:select>
		<br>
	</div>
	
	<div style="clear:both; padding:5px 0 0 0;">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="ingresoForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/ingreso/ingreso-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>

</div>

<script type="text/javascript">
</script>
