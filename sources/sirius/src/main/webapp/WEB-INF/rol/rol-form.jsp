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
<html:form action="/rol/rol-form-validate.do?method=save" styleId="abmForm">

	<div style="float:left;">
		<p>
		<label for="nombre"><bean:message key="sirius.rol.nombre.label" />(*)&nbsp;:</label>
		<html:text maxlength="255" property="nombre"></html:text>
		</p><br><p>
		<label for="descripcion"><bean:message key="sirius.rol.descripcion.label" />(*)&nbsp;:</label>
		<html:textarea property="descripcion" rows="5" />
		</p><br><p>
		<h2><bean:message key="sirius.rol.accesos.label" />&nbsp;:</h2>
		</p>
		<br />
		<br />
		<logic:iterate id="acceso" name="rolForm" property="accesos">
			<p>
			<html:multibox property="idAccesos" style="width:20px">
				<bean:write name="acceso" property="id" /> 
			</html:multibox> 
			<label><bean:write name="acceso" /></label>
			</p> 
			<br />
		</logic:iterate>
		<html:hidden property="idAccesos" value="0" />
		<br>		
	</div>
	
	<div style="clear:both;" class="errores">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="rolForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/rol/rol-query.do?method=lastQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>

</div>

<script type="text/javascript">
</script>
