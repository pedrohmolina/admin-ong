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
$(document).ready(function() {
	changeProyecto();
})

function changeProyecto() {
	var idProyecto = $("#proyecto").val();
	isIndividual(idProyecto);
	isAgrupado(idProyecto);
}

function isIndividual(idProyecto) {
	url = "<c:url value='/gasto/gasto-actividad-form.do?method=isIndividual' />";
	$.getJSON(url, {idProyecto:idProyecto}, function(json){
		if (json.isIndividual == "true") {
			$("#comboProveedor").attr("disabled", false);
			$("#comboTipoComprobante").attr("disabled", false);
			$("#numeroComprobante").attr("disabled", false);
		} else {
			$("#comboProveedor").attr("disabled", true);
			$("#comboTipoComprobante").attr("disabled", true);
			$("#numeroComprobante").attr("disabled", true);
		}
	});
}

function isAgrupado(idProyecto) {
	url = "<c:url value='/gasto/gasto-actividad-form.do?method=isAgrupado' />";
	$.getJSON(url, {idProyecto:idProyecto}, function(json){
		if (json.isAgrupado == "true") {
			$("#paquete").attr("disabled", false);
		} else {
			$("#paquete").attr("disabled", true);
		}
	});
}

function cargarComboActividad(select, destinationCombo) {
	$("#" + destinationCombo).removeOption(/^[^-]/i);
	$("#" + destinationCombo).val("");

	var selectedOption = $(select).val();
	if (selectedOption != "") {
		url = "<c:url value='/gasto/gasto-actividad-form.do?method=cargarComboActividad' />";
 		$("#" + destinationCombo).ajaxAddOption(url, {idProyecto:selectedOption}, false);
	}
}
</script>

<div class="form">
<html:form action="/gasto/gasto-actividad-form-validate.do?method=save"
	styleId="abmForm">

	<div style="float:left;">
		<p>
		<label for="idProyecto"><bean:message key="sirius.gasto.proyecto.label" />(*)&nbsp;:</label>
		<logic:equal name="gastoActividadForm" property="action.descripcion" value="create">
			<html:select property="idProyecto" styleId="proyecto" onchange="cargarComboActividad(this, 'actividad');changeProyecto();">
				<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
				<html:optionsCollection name="gastoActividadForm" property="proyectos" label="nombre" value="id"/>
			</html:select>
		</logic:equal>
		<logic:equal name="gastoActividadForm" property="action.descripcion" value="update">
			<html:text property="labelProyecto" readonly="true" />
		</logic:equal>
		</p><br><p>
		<label for="idActividad"><bean:message key="sirius.gasto.actividad.label" />(*)&nbsp;:</label>
		<logic:equal name="gastoActividadForm" property="action.descripcion" value="create">
			<html:select property="idActividad" styleId="actividad">
				<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
				<html:optionsCollection name="gastoActividadForm" property="actividades" label="nombre" value="id"/>
			</html:select>
		</logic:equal>
		<logic:equal name="gastoActividadForm" property="action.descripcion" value="update">
			<html:text property="labelActividad" readonly="true" />
		</logic:equal>
		</p><br><p>
		<label for="fecha"><bean:message key="sirius.gasto.fecha.label" />(*)&nbsp;:</label>
		<html:text property="fecha" styleClass="datepicker"/>
		</p><br><p>
		<label for="idRubro"><bean:message key="sirius.gasto.rubro.label" />(*)&nbsp;:</label>
		<html:select property="idRubro">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoActividadForm" property="rubros" label="nombre" value="id"/>
		</html:select>
		</p><br><p>
		<label for="idOrigen"><bean:message key="sirius.gasto.origen.label" />(*)&nbsp;:</label>
		<html:select property="idOrigen">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoActividadForm" property="origenes" label="descripcion" value="id"/>
		</html:select>
		</p><br>
		<logic:equal name="gastoActividadForm" property="individual" value="true">
			<p>
			<label for="idProveedor"><bean:message key="sirius.gasto.proveedor.label" />(*)&nbsp;:</label>
			<html:select property="idProveedor" styleId="comboProveedor">
				<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
				<html:optionsCollection name="gastoActividadForm" property="proveedores" label="nombre" value="id"/>
			</html:select>
			</p><br><p>
			<label for="idTipoComprobante"><bean:message key="sirius.gasto.tipoComprobante.label" />(*)&nbsp;:</label>
			<html:select property="idTipoComprobante" styleId="comboTipoComprobante">
				<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
				<html:optionsCollection name="gastoActividadForm" property="tiposComprobante" label="descripcion" value="id"/>
			</html:select>
			</p>
		</logic:equal>
		<br><p>
		<label for="observaciones"><bean:message key="sirius.gasto.observaciones.label" />:</label>
		<html:textarea property="observaciones" rows="5" />
		</p><br><p>
		<label for="importe"><bean:message key="sirius.gasto.importe.label" />(*)&nbsp;:</label>
		<html:text property="importe" />
		</p><br>
		<logic:equal name="gastoActividadForm" property="individual" value="true">
			<p>
			<label for="numeroComprobante"><bean:message key="sirius.gasto.numeroComprobante.label" />(*)&nbsp;:</label>
			<html:text property="numeroComprobante" styleId="numeroComprobante" />
			</p><br>
		</logic:equal>
		<logic:equal name="gastoActividadForm" property="agrupado" value="true">
			<p>
			<label for="paquete"><bean:message key="sirius.gasto.paquete.label" />(*)&nbsp;:</label>
			<html:text property="paquete" styleId="paquete" />
			</p><br>
		</logic:equal>

		<authz:authorize ifAllGranted="ENTIDAD_GASTO_ACTIVIDAD-LISTADO">
			<label for="idPersona"><bean:message key="sirius.gasto.persona.label" />(*)&nbsp;:</label>
			<html:select property="idPersona">
				<html:optionsCollection name="gastoActividadForm" property="personas" label="nombreYApellido" value="id"/>
			</html:select>
			<br>
		</authz:authorize>
	</div>

	<p>
	<div style="clear:both;" class="errores">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="gastoActividadForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<logic:equal name="gastoActividadForm" property="action.descripcion" value="create">
			<a href="<c:url value="/base/home_init.do"/>"><bean:message key="antares.base.cancelar.label" /></a>
		</logic:equal>
		<logic:equal name="gastoActividadForm" property="action.descripcion" value="update">
			<a href="<c:url value="/gasto/gasto-actividad-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
		</logic:equal>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>

</div>

<script type="text/javascript">	
</script>
