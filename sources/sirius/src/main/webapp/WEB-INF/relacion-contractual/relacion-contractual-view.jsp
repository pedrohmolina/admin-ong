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
<html:form action="/relacion-contractual/relacion-contractual-query.do?method=query">
	<h1>Datos</h1>
	<div style="float:left;">
		<p>
		<label for="nombre"><bean:message key="sirius.relacionContractual.nombre.label" />&nbsp;:</label>
		<html:text property="nombre" readonly="true" />
		</p><br><p>
		<label for="descripcion"><bean:message key="sirius.relacionContractual.descripcion.label" />&nbsp;:</label>
		<html:textarea property="descripcion" rows="5" readonly="true" />
		</p><br> 
	</div>

	<div style="clear: both;">
		<html:errors />
	</div> 
	
	<div class="boton">
		<a href="<c:url value="/relacion-contractual/relacion-contractual-query.do?method=lastQuery"/>"><bean:message key="antares.base.volver.label" /></a>
	</div>
</html:form>
</div>

