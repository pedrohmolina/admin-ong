<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/authz.tld" prefix="authz" %>
<%@ taglib uri="/WEB-INF/tlds/menu.tld" prefix="menu" %>

<menu:menu>

	<authz:authorize ifAllGranted="MENU_ADMINISTRACION-LISTADO">
		<menu:submenu top="true" label="&nbsp;Administracion&nbsp;" style="border-right: solid 1px;border-color: red;">
			<authz:authorize ifAllGranted="ENTIDAD_FINANCIADOR-LISTADO">
				<menu:item   label="Financiador"><c:url value="/financiador/financiador-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_INGRESO-LISTADO">
				<menu:item   label="Ingreso"><c:url value="/ingreso/ingreso-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_PERSONA-LISTADO">
				<menu:item   label="Persona"><c:url value="/persona/persona-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_PROVEEDOR-LISTADO">
				<menu:item   label="Proveedor"><c:url value="/proveedor/proveedor-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_RELACION_CONTRACTUAL-LISTADO">
				<menu:item   label="Relacion Contracual"><c:url value="/relacion-contractual/relacion-contractual-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_RUBRO-LISTADO">
				<menu:item   label="Rubro"><c:url value="/rubro/rubro-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
		</menu:submenu>
	</authz:authorize>

	<authz:authorize ifAllGranted="MENU_PROYECTO-LISTADO">
		<menu:submenu top="true" label="&nbsp;Proyecto&nbsp;" style="border-right: solid 1px;border-color: red;">
			<authz:authorize ifAllGranted="ENTIDAD_PROYECTO-LISTADO">
				<menu:item   label="Proyectos"><c:url value="/proyecto/proyecto-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_OBJETIVO_GENERAL-LISTADO">
				<menu:item   label="Objetivos Generales"><c:url value="/objetivo-general/objetivo-general-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_OBJETIVO_ESPECIFICO-LISTADO">
				<menu:item   label="Objetivos Especificos"><c:url value="/objetivo-especifico/objetivo-especifico-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_META-LISTADO">
				<menu:item   label="Metas"><c:url value="/meta/meta-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_ACTIVIDAD-LISTADO">
				<menu:item   label="Actividades"><c:url value="/actividad/actividad-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_ASIGNACION-LISTADO">
				<menu:item   label="Asignaciones"><c:url value="/asignacion/asignacion-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
		</menu:submenu>
	</authz:authorize>

	<authz:authorize ifAllGranted="MENU_GASTOS-LISTADO">
		<menu:submenu top="true" label="&nbsp;Gastos&nbsp;" style="border-right: solid 1px;border-color: red;">
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_ORGANIZACION-LISTADO">
				<menu:item   label="Gasto General de Organizacion"><c:url value="/gasto/gasto-organizacion-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_PROYECTO-LISTADO">
				<menu:item   label="Gasto General de Proyecto"><c:url value="/gasto/gasto-proyecto-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_ACTIVIDAD-LISTADO">
				<menu:item   label="Gasto Registrados por Actividad"><c:url value="/gasto/gasto-actividad-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_ACTIVIDAD-ALTA">
				<menu:item   label="Registrar Gasto de Actividad"><c:url value="/gasto/gasto-actividad-form.do?method=initCreate"/></menu:item>
			</authz:authorize>
		</menu:submenu>
	</authz:authorize>

	<authz:authorize ifAllGranted="MENU_SEGURIDAD-LISTADO">
		<menu:submenu top="true" label="&nbsp;Seguridad&nbsp;" style="border-right: solid 1px;border-color: red;">
			<authz:authorize ifAllGranted="ENTIDAD_ROL-LISTADO">
				<menu:item   label="Rol"><c:url value="/rol/rol-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_PERFIL-LISTADO">
				<menu:item   label="Perfil"><c:url value="/perfil/perfil-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_USUARIO-LISTADO">
				<menu:item   label="Usuario"><c:url value="/usuario/usuario-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_REGLA-LISTADO">
				<menu:item   label="Regla"><c:url value="/regla/regla-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<menu:item   label="Logout"><c:url value="/public/logout.do"/></menu:item>		
		</menu:submenu>
	</authz:authorize>

	<authz:authorize ifAllGranted="MENU_REPORTE-EJECUTAR">
		<menu:submenu top="true" label="&nbsp;Reportes&nbsp;" >
			<authz:authorize ifAllGranted="ENTIDAD_REPORTE_PERSONA-EJECUTAR">
				<menu:item style="width: 160px;" label="Personas"><c:url value="/reportes/reporte-persona.do?method=initForm"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_REPORTE_FINANCIADOR-EJECUTAR">
				<menu:item style="width: 160px;" label="Financiadores"><c:url value="/reportes/reporte-financiador.do?method=initForm"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_REPORTE_PROVEEDOR-EJECUTAR">
				<menu:item style="width: 160px;" label="Proveedores"><c:url value="/reportes/reporte-proveedor.do?method=initForm"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_REPORTE_FINANZAS-EJECUTAR">
				<menu:item style="width: 160px;" label="Finanzas"><c:url value="/reportes/reporte-financiero.do?method=initForm"/></menu:item>
			</authz:authorize>
		</menu:submenu>
	</authz:authorize>
	
</menu:menu>

