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

	function cargarComboActividad(select, destinationCombo){
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
<html:form action="/gasto/gasto-actividad-form-validate.do?method=save" styleId="abmForm">

	<h1>Datos</h1>
	<div style="float:left;">
		<label for="idProyecto"><bean:message key="sirius.gasto.proyecto.label" />(*)&nbsp;:</label>
		<html:select property="idProyecto" styleId="proyecto" onchange="cargarComboActividad(this, 'actividad')">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoActividadForm" property="proyectos" label="nombre" value="id"/>
		</html:select>
		<br>
		<label for="idActividad"><bean:message key="sirius.gasto.actividad.label" />(*)&nbsp;:</label>
		<html:select property="idActividad" styleId="actividad">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoActividadForm" property="actividades" label="nombre" value="id"/>
		</html:select>
		<br>
		<label for="fecha"><bean:message key="sirius.gasto.fecha.label" />(*)&nbsp;:</label>
		<html:text property="fecha" />
		<br>
		<label for="idRubro"><bean:message key="sirius.gasto.rubro.label" />(*)&nbsp;:</label>
		<html:select property="idRubro">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoActividadForm" property="rubros" label="descripcion" value="id"/>
		</html:select>
		<br>
		<label for="idOrigen"><bean:message key="sirius.gasto.origen.label" />(*)&nbsp;:</label>
		<html:select property="idOrigen">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoActividadForm" property="origenes" label="descripcion" value="id"/>
		</html:select>
		<br>
		<label for="idProveedor"><bean:message key="sirius.gasto.proveedor.label" />(*)&nbsp;:</label>
		<html:select property="idProveedor">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoActividadForm" property="proveedores" label="nombre" value="id"/>
		</html:select>
		<br>
		<label for="idTipoComprobante"><bean:message key="sirius.gasto.tipoComprobante.label" />(*)&nbsp;:</label>
		<html:select property="idTipoComprobante">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="gastoActividadForm" property="tiposComprobante" label="descripcion" value="id"/>
		</html:select>
		<br>
		<label for="observaciones"><bean:message key="sirius.gasto.observaciones.label" />:</label>
		<html:textarea property="observaciones" rows="5" />
		<br>
		<label for="importe"><bean:message key="sirius.gasto.importe.label" />(*)&nbsp;:</label>
		<html:text property="importe" />
		<br>
		<label for="numeroComprobante"><bean:message key="sirius.gasto.numeroComprobante.label" />(*)&nbsp;:</label>
		<html:text property="numeroComprobante" />
		<br>
		<label for="paquete"><bean:message key="sirius.gasto.paquete.label" />(*)&nbsp;:</label>
		<html:text property="paquete" />
		<br>
	</div>
	
	<div style="clear:both; padding:5px 0 0 0;">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="gastoActividadForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/gasto/gasto-actividad-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
</html:form>

</div>

<script type="text/javascript">
</script>
