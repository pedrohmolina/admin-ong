<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean-el.tld" prefix="bean-el"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html-el.tld" prefix="html-el"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag-el.tld" prefix="display-el"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<div class="boxTitulo">
	<div class="bi">
		<div class="bt"><div></div></div>
		<h1>Datos</h1>
		<div class="bb"><div></div></div>
	</div>
</div>
<div class="form">
<html:form action="/regla/regla-query.do?method=query">
	<h1>Datos</h1>
	<div style="float:left;">
		<label for="labelUsuario"><bean:message key="sirius.regla.usuario.label" />&nbsp;:</label>
		<html:text property="labelUsuario" readonly="true" />
		<br>
		<label for="labelEntidad"><bean:message key="sirius.regla.entidad.label" />&nbsp;:</label>
		<html:text property="labelEntidad" readonly="true" />
		<br>
		<label for="labelAtributo"><bean:message key="sirius.regla.atributo.label" />&nbsp;:</label>
		<html:text property="labelAtributo" readonly="true" />
		<br>
		<label for="labelOperador"><bean:message key="sirius.regla.operador.label" />&nbsp;:</label>
		<html:text property="labelOperador" readonly="true" />
		<br>
		<label for="labelValor"><bean:message key="sirius.regla.valor.label" />&nbsp;:</label>
		<html:text property="labelValor" readonly="true" />
		<br>
	</div>

	<div style="clear: both;">
		<html:errors />
	</div> 
	
	<div class="boton">
		<a href="<c:url value="/regla/regla-query.do?method=lastQuery"/>"><bean:message key="antares.base.volver.label" /></a>
	</div>
</html:form>
</div>

