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
	<logic:equal name="gastoActividadForm" property="updated" value="true" >
		window.parent.opener.document.gastoActividadQuery.submit();
		window.close();
	</logic:equal>
})

</script>

<div class="form">
<html:form action="/gasto/gasto-actividad-form.do?method=saveReferencia" styleId="abmForm">

	<div style="float:left;">
		<p>
		<label for="referencia"><bean:message key="sirius.gasto.referencia.label" />:</label>
		<html:textarea property="referencia" rows="5" />
		</p><br>
	</div>
	
	<div style="clear:both" class="errores">
		<html:errors />
	</div>
	<div class="boton">
		<a href="#" onclick="gastoActividadForm.submit();" tabindex="17"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="javascript: window.close()"><bean:message key="antares.base.cerrar.label" /></a>
	</div>
</html:form>

</div>

<script type="text/javascript">
</script>
