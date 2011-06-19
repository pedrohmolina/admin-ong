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
<html:form action="/rubro/rubro-query.do?method=query">
	<h1>Datos</h1>
	<div style="float:left;">
		<p>
		<label for="nombre"><bean:message key="sirius.rubro.nombre.label" />&nbsp;:</label>
		<html:text maxlength="255" property="nombre" readonly="true" />
		</p><br><p>
		<label for="descripcion"><bean:message key="sirius.rubro.descripcion.label" />&nbsp;:</label>
		<html:textarea property="descripcion" rows="5" readonly="true" />
		</p><br>
		<logic:equal name="rubroForm" property="nivel" value="2">
			<p>
			<label for="idRubroNivelUno"><bean:message key="sirius.rubro.rubroNivelUno.label" />&nbsp;:</label>
			<html:text property="labelRubroNivelUno" readonly="true" />
			<br />
			</p><br>
		</logic:equal>
		
		<logic:equal name="rubroForm" property="nivel" value="3">
			<p>
			<label for="idRubroNivelUno"><bean:message key="sirius.rubro.rubroNivelUno.label" />&nbsp;:</label>
			<html:text property="labelRubroNivelUno" readonly="true" />
			</p><br><p>
			<label for="idRubroNivelDos"><bean:message key="sirius.rubro.rubroNivelDos.label" />&nbsp;:</label>
			<html:text property="labelRubroNivelDos" readonly="true" />
			</p><br>
		</logic:equal>
	</div>

	<div style="clear: both;" class="errores">
		<html:errors />
	</div> 
	
	<div class="boton">
		<a href="<c:url value="/rubro/rubro-query.do?method=lastQuery"/>"><bean:message key="antares.base.volver.label" /></a>
	</div>
</html:form>
</div>

