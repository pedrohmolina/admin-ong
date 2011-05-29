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

<div class="form">
<html:form action="/asignacion/asignacion-form-validate.do?method=save" styleId="abmForm">

	<h1>Datos</h1>
	<div style="float:left;">
	<p>
		<label for="idActividad"><bean:message key="sirius.asignacion.actividad.label" />(*)&nbsp;:</label>
		<logic:equal name="asignacionForm" property="action.descripcion" value="update">
			<html:text property="labelActividad" readonly="true" />
		</logic:equal>
		<logic:equal name="asignacionForm" property="action.descripcion" value="create">
			<html:select property="idActividad">
				<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
				<html:optionsCollection name="asignacionForm" property="actividades" label="nombre" value="id"/>
			</html:select>
		</logic:equal>
		</p><br>
		<p>
		<label for="idPersona"><bean:message key="sirius.asignacion.persona.label" />(*)&nbsp;:</label>
		<logic:equal name="asignacionForm" property="action.descripcion" value="update">
			<html:text property="labelPersona" readonly="true" />
		</logic:equal>
		<logic:equal name="asignacionForm" property="action.descripcion" value="create">
			<html:select property="idPersona">
				<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
				<html:optionsCollection name="asignacionForm" property="personas" label="nombreYApellido" value="id"/>
			</html:select>
		</logic:equal>
		</p><br>
		<p>
		<label for="idTipoAsignacion"><bean:message key="sirius.asignacion.tipoAsignacion.label" />(*)&nbsp;:</label>
		<html:select property="idTipoAsignacion">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="asignacionForm" property="tiposAsignacion" label="descripcion" value="id"/>
		</html:select>
		</p><br>
		<p>
		<label for="nombre"><bean:message key="sirius.asignacion.cantidad.label" />(*)&nbsp;:</label>
		<html:text maxlength="3" property="cantidad" />
		</p><br>

	</div>
	
	<div style="clear:both; padding:5px 0 0 0;">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="asignacionForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/asignacion/asignacion-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>

</div>

<script type="text/javascript">
</script>
