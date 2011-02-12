<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/authz.tld" prefix="authz" %>
<%@ taglib uri="/WEB-INF/tlds/menu.tld" prefix="menu" %>

<menu:menu>

	<%--
	<authz:authorize ifAllGranted="MOSTRAR-OPCIONES_ADMINISTRACION">
	--%>
		<menu:submenu top="true" label="&nbsp;Administracion&nbsp;">
			<menu:item style="width: 160px;" label="Financiador"><c:url value="/financiador/financiador-query.do?method=initQuery"/></menu:item>
			<menu:item style="width: 160px;" label="Ingreso"><c:url value="/ingreso/ingreso-query.do?method=initQuery"/></menu:item>
			<menu:item style="width: 160px;" label="Persona"><c:url value="/persona/persona-query.do?method=initQuery"/></menu:item>
			<menu:item style="width: 160px;" label="Proveedor"><c:url value="/proveedor/proveedor-query.do?method=initQuery"/></menu:item>
			<menu:item style="width: 160px;" label="Relacion Contracual"><c:url value="/relacion-contractual/relacion-contractual-query.do?method=initQuery"/></menu:item>
			<menu:item style="width: 160px;" label="Rubro"><c:url value="/rubro/rubro-query.do?method=initQuery"/></menu:item>
		</menu:submenu>
	<%--
	</authz:authorize>
	--%>

	<%--
	<authz:authorize ifAllGranted="MOSTRAR-OPCIONES_PROYECTO">
	--%>
		<menu:submenu top="true" label="&nbsp;Proyecto&nbsp;">
			<menu:item style="width: 160px;" label="Proyectos"><c:url value="/proyecto/proyecto-query.do?method=initQuery"/></menu:item>
			<menu:item style="width: 160px;" label="Objetivos Generales"><c:url value="/objetivo-general/objetivo-general-query.do?method=initQuery"/></menu:item>
			<menu:item style="width: 160px;" label="Objetivos Especificos"><c:url value="/objetivo-especifico/objetivo-especifico-query.do?method=initQuery"/></menu:item>
			<menu:item style="width: 160px;" label="Metas"><c:url value="/meta/meta-query.do?method=initQuery"/></menu:item>
			<menu:item style="width: 160px;" label="Actividades"><c:url value="/actividad/actividad-query.do?method=initQuery"/></menu:item>
			<menu:item style="width: 160px;" label="Asignaciones"><c:url value="/asignacion/asignacion-query.do?method=initQuery"/></menu:item>
		</menu:submenu>
	<%--
	</authz:authorize>
	--%>

	<%--
	<authz:authorize ifAllGranted="MOSTRAR-OPCIONES_SEGURIDAD">
	--%>
		<menu:submenu top="true" label="&nbsp;Seguridad&nbsp;">
			<menu:item style="width: 160px;" label="Rol"><c:url value="/rol/rol-query.do?method=initQuery"/></menu:item>
			<menu:item style="width: 160px;" label="Perfil"><c:url value="/perfil/perfil-query.do?method=initQuery"/></menu:item>
		</menu:submenu>
	<%--
	</authz:authorize>
	--%>

	<authz:authorize ifAllGranted="MOSTRAR-ELEMENTOS_APLICACION">
		<menu:submenu top="true" label="&nbsp;&nbsp;&nbsp;Mis Datos&nbsp;&nbsp;&nbsp;">
			<authz:authorize ifAllGranted="MOSTRAR-ELEMENTOS_APLICACION">
				<menu:item style="width: 113px;" label="Cambio de clave"><c:url value="/base/pass_init.do?method=changepass"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="MOSTRAR-ELEMENTOS_APLICACION">
				<menu:item style="width: 113px;" label="Datos de usuario"><c:url value="/base/usuarios_init.do?method=view"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="MOSTRAR-OPCIONES_ADMINISTRACION">
				<menu:item style="width: 113px;" label="Acerca de ..."><c:url value="/base/acercade.do?method=view"/></menu:item>
			</authz:authorize>
		</menu:submenu>
	</authz:authorize>

</menu:menu>

