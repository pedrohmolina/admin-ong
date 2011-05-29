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

	function loadFinanciador(){
	 	var selectedOption = $("#meta").val();
	 	if (selectedOption != "") {
			url = "<c:url value='/actividad/actividad-form.do?method=obtenerFinanciador' />";
	 		$.getJSON(url, {idMeta:selectedOption}, function(json){
				if(json.idFinanciador) {
					$('#financiador').val(json.idFinanciador);
				}
	 		});
	 	}
	}
	
</script>

<div class="form">
<html:form action="/actividad/actividad-form-validate.do?method=save" styleId="abmForm">

	<h1>Datos</h1>
	<div style="float:left;">
		<p>
		<label for="idMeta"><bean:message key="sirius.actividad.meta.label" />(*)&nbsp;:</label>
		<logic:equal name="actividadForm" property="action.descripcion" value="update">
			<html:text property="labelMeta" readonly="true" />
		</logic:equal>
		<logic:equal name="actividadForm" property="action.descripcion" value="create">
			<html:select property="idMeta" styleId="meta" onchange="loadFinanciador();">
				<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
				<html:optionsCollection name="actividadForm" property="metas" label="nombre" value="id"/>
			</html:select>
		</logic:equal>
		</p><br>
		<p>
		<label for="nombre"><bean:message key="sirius.actividad.nombre.label" />(*)&nbsp;:</label>
		<html:text maxlength="255" property="nombre" />
		</p><br>
		<p>
		<label for="ponderacion"><bean:message key="sirius.actividad.ponderacion.label" />(*)&nbsp;:</label>
		<html:text maxlength="3" property="ponderacion" />
		</p><br>
		<p>
		<logic:equal name="actividadForm" property="action.descripcion" value="update">
			<label for="completitud"><bean:message key="sirius.actividad.completitud.label" />(*)&nbsp;:</label>
			<logic:equal name="actividadForm" property="actualizarCompletitud" value="true">
				<html:text maxlength="3" property="completitud" />
			</logic:equal>
			<logic:notEqual name="actividadForm" property="actualizarCompletitud" value="true">
				<html:text property="completitud" readonly="true"/>
			</logic:notEqual>
			<br />
		</logic:equal>
		<label for="fechaInicio"><bean:message key="sirius.actividad.fechaInicio.label" />&nbsp;:</label>
		<html:text property="fechaInicio" />
		</p><br>
		<p>
		<label for="fechaFin"><bean:message key="sirius.actividad.fechaFin.label" />&nbsp;:</label>
		<html:text property="fechaFin" />
		</p><br>
		<p>
		<label for="observaciones"><bean:message key="sirius.actividad.observaciones.label" />(*)&nbsp;:</label>
		<html:textarea property="observaciones" rows="5" />
		</p><br>
		<p>
		<label for="presupuesto"><bean:message key="sirius.actividad.presupuesto.label" />&nbsp;:</label>
		<html:text property="presupuesto" />
		</p><br>
		<p>
		<label for="idFinanciador"><bean:message key="sirius.actividad.financiador.label" />(*)&nbsp;:</label>
		<html:select property="idFinanciador" styleId="financiador">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="actividadForm" property="financiadores" label="nombre" value="id"/>
		</html:select>
		</p>
	</div>
	
	<div style="clear:both; padding:5px 0 0 0;">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="actividadForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/actividad/actividad-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>

</div>

<script type="text/javascript">
</script>
