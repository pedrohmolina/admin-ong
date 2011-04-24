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

	function changeEntidad() {
	 	$("#atributo").removeOption(/^[^-]/i);
	 	$("#atributo").val("");
	 	$("#operador").removeOption(/^[^-]/i);
	 	$("#operador").val("");

	 	var selectedOption = $("#entidad").val();
	 	if (selectedOption != "") {
			url = "<c:url value='/regla/regla-form.do?method=cargarComboAtributos' />";
	 		$("#atributo").ajaxAddOption(url, {idEntidad:selectedOption}, false);
	 	}
	}
	
	function changeAtributo() {
	 	$("#operador").removeOption(/^[^-]/i);
	 	$("#operador").val("");

	 	var selectedOption = $("#atributo").val();
	 	if (selectedOption != "") {
			url = "<c:url value='/regla/regla-form.do?method=cargarComboOperadores' />";
	 		$("#operador").ajaxAddOption(url, {idAtributo:selectedOption}, false);
	 	}
	}
	
</script>

<div class="form">
<html:form action="/regla/regla-form-validate.do?method=save" styleId="abmForm">

	<h1>Datos</h1>
	<div style="float:left;">
		<label for="idUsuario"><bean:message key="sirius.regla.usuario.label" />(*)&nbsp;:</label>
		<html:select property="idUsuario">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="reglaForm" property="usuarios" label="username" value="id"/>
		</html:select>
		<br>
		<label for="idEntidad"><bean:message key="sirius.regla.entidad.label" />(*)&nbsp;:</label>
		<html:select property="idEntidad" styleId="entidad" onchange="changeEntidad();" >
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="reglaForm" property="entidades" label="descripcion" value="id"/>
		</html:select>
		<br>
		<label for="idAtributo"><bean:message key="sirius.regla.atributo.label" />(*)&nbsp;:</label>
		<html:select property="idAtributo" styleId="atributo" onchange="changeAtributo();" >
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="reglaForm" property="atributos" label="displayName" value="id"/>
		</html:select>
		<br>
		<label for="idOperador"><bean:message key="sirius.regla.operador.label" />(*)&nbsp;:</label>
		<html:select property="idOperador" styleId="operador">
			<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
			<html:optionsCollection name="reglaForm" property="operadores" label="descripcion" value="id"/>
		</html:select>
		<br>
		<label for="valor"><bean:message key="sirius.regla.valor.label" />(*)&nbsp;:</label>
		<html:text property="valor" />
		<br>
	</div>
	
	<div style="clear:both; padding:5px 0 0 0;">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="reglaForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/regla/regla-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>

</div>

<script type="text/javascript">
</script>
