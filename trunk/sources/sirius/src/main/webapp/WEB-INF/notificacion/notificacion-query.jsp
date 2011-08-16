<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean-el.tld" prefix="bean-el"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/tlds/struts-nested.tld" prefix="nested"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag.tld" prefix="display"%>
<%@ taglib uri="/WEB-INF/tlds/displaytag-el.tld" prefix="display-el"%>
<%@ taglib uri="/WEB-INF/tlds/authz.tld" prefix="authz"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>

<div class="form">
	<html:form action="/notificacion/notificacion-query.do?method=query">
	<div style="float: left; width: 100%;">
		<div class="boton">
			<a href="<c:url value="/notificacion/notificacion-query.do?method=queryLeidas"/>"><bean:message key="sirius.notificacion.leidas.label" /></a>
			<a href="<c:url value="/notificacion/notificacion-query.do?method=queryNoLeidas"/>"><bean:message key="sirius.notificacion.noLeidas.label" /></a>
			<a href="<c:url value="/notificacion/notificacion-query.do?method=queryTodas"/>"><bean:message key="sirius.notificacion.todas.label" /></a>
		</div>
	</div>
	
	</html:form>
	
	<div style="clear: both;" class="errores">
		<html:errors />
	</div>

	<h1><bean:message key="antares.base.result.label" /></h1>
	<display-el:table export="true" pagesize="${requestScope['displayTagPageSize']}" class="tabla" name="sessionScope.notificacionQuery.result" id="item"
		requestURI="/notificacion/notificacion-query.do" sort="list" >

		<display:setProperty name="basic.msg.empty_list"><table width\="100%"  border\="0" cellspacing\="0" cellpadding\="0" class\="tablaTitulo"><tr  height="40px"><td align\="center"><label class="emptylist"><bean:message key="sirius.notificacion.empty.label" /></label></td></tr></table></display:setProperty>
	
		<display:column sortable="true" property="fecha" 						titleKey="sirius.notificacion.fecha.label" format="{0,date,dd/MM/yyyy HH:mm}"  style="text-align: center"/>
		<display:column sortable="true" property="proyecto.nombre"				titleKey="sirius.notificacion.proyecto.label" maxLength="50" />
		<display:column sortable="true" property="mensaje" 						titleKey="sirius.notificacion.mensaje.label" />

		<display:column title="Acciones" media="html" style="text-align: center">
			<logic:notEqual name="item" property="leida" value="true">
				<a href="<c:url value="/notificacion/notificacion-query.do?method=marcarLeido&id="/><bean:write name="item" property="id"/>"><img border="0" alt="Confirmar" title="Confirmar"
					src="<c:url value="/img/tick.png"/>" /></a>
			</logic:notEqual>
		</display:column>
	</display-el:table>

	<div class="boton">
		<a href="<c:url value="/notificacion/notificacion-query.do?method=save"/>"><bean:message key="antares.base.aceptar.label" /></a>
		<a href="<c:url value="/notificacion/notificacion-query.do?method=initQuery"/>"><bean:message key="antares.base.cancelar.label" /></a>
	</div>
	<div style="clear:both; padding:5px 0 0 0;"></div>
</div>
