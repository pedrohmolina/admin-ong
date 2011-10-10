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
<html:form action="/meta/meta-form-validate.do?method=save" styleId="abmForm">

	<div style="float:left;">
		<p>
		<label for="idObjetivoEspecifico"><bean:message key="sirius.meta.objetivoEspecifico.label" />(*)&nbsp;:</label>
		<logic:equal name="metaForm" property="action.descripcion" value="update">
			<html:text maxlength="255" property="labelObjetivoEspecifico" readonly="true" />
		</logic:equal>
		<logic:equal name="metaForm" property="action.descripcion" value="create">
			<html:select property="idObjetivoEspecifico">
				<html:option value=""><bean:message key="antares.base.seleccione.label"/></html:option>
				<html:optionsCollection name="metaForm" property="objetivosEspecificos" label="nombre" value="id"/>
			</html:select>
		</logic:equal>
		</p><br><p>
		<label for="nombre"><bean:message key="sirius.meta.nombre.label" />(*)&nbsp;:</label>
		<html:text maxlength="255" property="nombre" />
		</p><br><p>
		<label for="ponderacion"><bean:message key="sirius.meta.ponderacion.label" />(*)&nbsp;:</label>
		<html:text maxlength="3" property="ponderacion"  styleClass="tres" />
		</p><br><p>
		<label for="descripcion"><bean:message key="sirius.meta.descripcion.label" />(*)&nbsp;:</label>
		<html:textarea property="descripcion" rows="5" />
		</p><br>
	</div>
	
	<div style="clear:both;" class="errores">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="metaForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/meta/meta-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>

</div>

<script type="text/javascript">
</script>
