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

<div class="form">
<html:form action="/asignacion/asignacion-form.do?method=view">
	<div style="float:left;">
		<p>
		<label for="idActividad"><bean:message key="sirius.asignacion.actividad.label" />(*)&nbsp;:</label>
		<html:text property="labelActividad" readonly="true" />
		</p><br><p>
		<label for="idPersona"><bean:message key="sirius.asignacion.persona.label" />(*)&nbsp;:</label>
		<html:text property="labelPersona" readonly="true" />
		</p><br><p>
		<label for="idTipoAsignacion"><bean:message key="sirius.asignacion.tipoAsignacion.label" />(*)&nbsp;:</label>
		<html:text property="labelTipoAsignacion" readonly="true" />
		</p><br><p>
		<label for="cantidad"><bean:message key="sirius.asignacion.cantidad.label" />&nbsp;:</label>
		<html:text property="cantidad" readonly="true" />
		</p><br>
	</div>

	<div style="clear: both;" class="errores">
		<html:errors />
	</div>

	<div class="boton">
		<a href="<c:url value="/asignacion/asignacion-query.do?method=lastQuery"/>"><bean:message key="antares.base.volver.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</html:form>
</div>

