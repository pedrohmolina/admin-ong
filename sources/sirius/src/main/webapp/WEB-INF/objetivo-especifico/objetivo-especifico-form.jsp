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
<html:form action="/objetivo-especifico/objetivo-especifico-form-validate.do?method=save" styleId="abmForm">

	<h1>Datos</h1>
	<div style="float:left;">
		<label for="idObjetivoGeneral"><bean:message key="sirius.objetivoEspecifico.objetivoGeneral.label" />(*)&nbsp;:</label>
		<logic:equal name="objetivoEspecificoForm" property="action.descripcion" value="update">
			<html:text maxlength="255" property="labelObjetivoGeneral" readonly="true" />
		</logic:equal>
		<logic:equal name="objetivoEspecificoForm" property="action.descripcion" value="create">
			<html:select property="idObjetivoGeneral">
				<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
				<html:optionsCollection name="objetivoEspecificoForm" property="objetivosGenerales" label="nombre" value="id"/>
			</html:select>
		</logic:equal>
		<br>
		<label for="nombre"><bean:message key="sirius.objetivoEspecifico.nombre.label" />(*)&nbsp;:</label>
		<html:text maxlength="255" property="nombre" />
		<br />
		<label for="ponderacion"><bean:message key="sirius.objetivoEspecifico.ponderacion.label" />(*)&nbsp;:</label>
		<html:text maxlength="3" property="ponderacion" />
		<br />
		<label for="descripcion"><bean:message key="sirius.objetivoEspecifico.descripcion.label" />(*)&nbsp;:</label>
		<html:textarea property="descripcion" rows="5" />
		<br />
	</div>
	
	<div style="clear:both; padding:5px 0 0 0;">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="objetivoEspecificoForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/objetivo-especifico/objetivo-especifico-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
</html:form>

</div>

<script type="text/javascript">
</script>
