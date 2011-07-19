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

	function cargarComboSubrubros(select, destinationCombo){
	 	$("#" + destinationCombo).removeOption(/^[^-]/i);
	 	$("#" + destinationCombo).val("");

	 	var selectedOption = $(select).val();
	 	if (selectedOption != "") {
			url = "<c:url value='/rubro/rubro-form.do?method=cargarComboSubrubros' />";
	 		$("#" + destinationCombo).ajaxAddOption(url, {idRubro:selectedOption}, false);
	 	}
	}
	
</script>

<div class="form">
<html:form action="/rubro/rubro-form-validate.do?method=save" styleId="abmForm">

	<div style="float:left;">
		<p>
		<label for="nombre"><bean:message key="sirius.rubro.nombre.label" />(*)&nbsp;:</label>
		<html:text maxlength="16" property="nombre" />
		</p><br><p>
		<label for="descripcion"><bean:message key="sirius.rubro.descripcion.label" />(*)&nbsp;:</label>
		<html:textarea property="descripcion" rows="5" />
		</p><br>
		
		<logic:equal name="rubroForm" property="nivel" value="2">
			<logic:equal name="rubroForm" property="action.descripcion" value="update">
				<p>
				<label for="idRubroNivelUno"><bean:message key="sirius.rubro.rubroNivelUno.label" />&nbsp;:</label>
				<html:text property="labelRubroNivelUno" readonly="true" />
				</p><br>
			</logic:equal>
			<logic:equal name="rubroForm" property="action.descripcion" value="create">
				<p>
				<label for="idRubroNivelUno"><bean:message key="sirius.rubro.rubroNivelUno.label" />(*)&nbsp;:</label>
				<html:select property="idRubroNivelUno">
					<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
					<html:optionsCollection name="rubroForm" property="rubrosNivelUno" label="nombre" value="id"/>
				</html:select>
				</p><br>
			</logic:equal>
		</logic:equal>

		<logic:equal name="rubroForm" property="nivel" value="3">
			<logic:equal name="rubroForm" property="action.descripcion" value="update">
				<p>
				<label for="idRubroNivelUno"><bean:message key="sirius.rubro.rubroNivelUno.label" />&nbsp;:</label>
				<html:text property="labelRubroNivelUno" readonly="true" />
				</p><br /><p>
				<label for="idRubroNivelDos"><bean:message key="sirius.rubro.rubroNivelDos.label" />&nbsp;:</label>
				<html:text property="labelRubroNivelDos" readonly="true" />
				</p><br />
			</logic:equal>
			<logic:notEqual name="rubroForm" property="action.descripcion" value="update">
				<p>
				<label for="idRubroNivelUno"><bean:message key="sirius.rubro.rubroNivelUno.label" />(*)&nbsp;:</label>
				<html:select property="idRubroNivelUno" styleId="rubroNivelUno" onchange="cargarComboSubrubros(this, 'rubroNivelDos')">
					<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
					<html:optionsCollection name="rubroForm" property="rubrosNivelUno" label="nombre" value="id"/>
				</html:select>
				</p><br /><p>
				<label for="idRubroNivelDos"><bean:message key="sirius.rubro.rubroNivelDos.label" />(*)&nbsp;:</label>
				<html:select property="idRubroNivelDos" styleId="rubroNivelDos">
					<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
					<html:optionsCollection name="rubroForm" property="rubrosNivelDos" label="nombre" value="id"/>
				</html:select>
				</p><br />
			</logic:notEqual>
		</logic:equal>
	</div>
	
	<div style="clear:both;" class="errores">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="rubroForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/rubro/rubro-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>

</div>

<script type="text/javascript">
</script>
