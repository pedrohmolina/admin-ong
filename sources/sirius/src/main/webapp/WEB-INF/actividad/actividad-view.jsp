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
</div>x
<div class="form">
<html:form action="/actividad/actividad-query.do?method=query">
	<h1>Datos</h1>
	<div style="float:left;">
		<label for="labelMeta"><bean:message key="sirius.actividad.meta.label" />&nbsp;:</label>
		<html:text property="labelMeta" readonly="true" />
		<br>
		<label for="nombre"><bean:message key="sirius.actividad.nombre.label" />&nbsp;:</label>
		<html:text property="nombre" readonly="true" />
		<br />
		<label for="ponderacion"><bean:message key="sirius.actividad.ponderacion.label" />&nbsp;:</label>
		<html:text property="ponderacion" readonly="true" />
		<br />
		<label for="completitud"><bean:message key="sirius.actividad.completitud.label" />&nbsp;:</label>
		<html:text maxlength="3" property="completitud" readonly="true" />
		<br />
		<label for="fechaInicio"><bean:message key="sirius.actividad.fechaInicio.label" />&nbsp;:</label>
		<html:text property="fechaInicio" readonly="true" />
		<br />
		<label for="fechaFin"><bean:message key="sirius.actividad.fechaFin.label" />&nbsp;:</label>
		<html:text property="fechaFin" readonly="true" />
		<br />
		<label for="observaciones"><bean:message key="sirius.actividad.observaciones.label" />&nbsp;:</label>
		<html:textarea property="observaciones" rows="5" readonly="true" />
		<br />
		<label for="presupuesto"><bean:message key="sirius.actividad.presupuesto.label" />&nbsp;:</label>
		<html:text property="presupuesto" readonly="true" />
		<br />
		<label for="labelFinanciador"><bean:message key="sirius.actividad.financiador.label" />&nbsp;:</label>
		<html:text property="labelFinanciador" readonly="true" />
		<br>
	</div>

	<div style="clear: both;">
		<html:errors />
	</div> 
	
	<div class="boton">
		<a href="<c:url value="/actividad/actividad-query.do?method=lastQuery"/>"><bean:message key="antares.base.volver.label" /></a>
	</div>
</html:form>
</div>

