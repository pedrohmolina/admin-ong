<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/authz.tld" prefix="authz" %>
<%@ taglib uri="/WEB-INF/tlds/menu.tld" prefix="menu" %>

<menu:menu>

	<authz:authorize ifAnyGranted="ENTIDAD_FINANCIADOR-LISTADO, ENTIDAD_INGRESO-LISTADO, ENTIDAD_PERSONA-LISTADO, ENTIDAD_PROVEEDOR-LISTADO, ENTIDAD_RELACION_CONTRACTUAL-LISTADO, ENTIDAD_RUBRO-LISTADO">
		<menu:submenu top="true" labelKey="sirius.menu.administracion.label" style="border-right: solid 1px;border-color: #B11116;">
			<authz:authorize ifAllGranted="ENTIDAD_FINANCIADOR-LISTADO">
				<menu:item labelKey="sirius.financiador.label"><c:url value="/financiador/financiador-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_INGRESO-LISTADO">
				<menu:item labelKey="sirius.ingreso.label"><c:url value="/ingreso/ingreso-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_PERSONA-LISTADO">
				<menu:item labelKey="sirius.persona.label"><c:url value="/persona/persona-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_PROVEEDOR-LISTADO">
				<menu:item labelKey="sirius.proveedor.label"><c:url value="/proveedor/proveedor-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_RELACION_CONTRACTUAL-LISTADO">
				<menu:item labelKey="sirius.relacionContractual.label"><c:url value="/relacion-contractual/relacion-contractual-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_RUBRO-LISTADO">
				<menu:item labelKey="sirius.rubro.label"><c:url value="/rubro/rubro-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
		</menu:submenu>
	</authz:authorize>

	<authz:authorize ifAnyGranted="ENTIDAD_PROYECTO-LISTADO, ENTIDAD_OBJETIVO_GENERAL-LISTADO, ENTIDAD_OBJETIVO_ESPECIFICO-LISTADO, ENTIDAD_META-LISTADO, ENTIDAD_ACTIVIDAD-LISTADO, ENTIDAD_ASIGNACION-LISTADO">
		<menu:submenu top="true" labelKey="sirius.menu.proyecto.label" style="border-right: solid 1px;border-color: #B11116;">
			<authz:authorize ifAllGranted="ENTIDAD_PROYECTO-LISTADO">
				<menu:item labelKey="sirius.proyecto.label"><c:url value="/proyecto/proyecto-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_OBJETIVO_GENERAL-LISTADO">
				<menu:item labelKey="sirius.objetivoGeneral.label"><c:url value="/objetivo-general/objetivo-general-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_OBJETIVO_ESPECIFICO-LISTADO">
				<menu:item labelKey="sirius.objetivoEspecifico.label"><c:url value="/objetivo-especifico/objetivo-especifico-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_META-LISTADO">
				<menu:item labelKey="sirius.meta.label"><c:url value="/meta/meta-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_ACTIVIDAD-LISTADO">
				<menu:item labelKey="sirius.actividad.label"><c:url value="/actividad/actividad-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_ASIGNACION-LISTADO">
				<menu:item labelKey="sirius.asignacion.label"><c:url value="/asignacion/asignacion-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
		</menu:submenu>
	</authz:authorize>

	<authz:authorize ifAnyGranted="ENTIDAD_GASTO_ORGANIZACION-LISTADO, ENTIDAD_GASTO_PROYECTO-LISTADO, ENTIDAD_GASTO_ACTIVIDAD-LISTADO, ENTIDAD_GASTO_ACTIVIDAD-ALTA, ENTIDAD_GASTO_ACTIVIDAD-HISTORICO">
		<menu:submenu top="true" labelKey="sirius.menu.gastos.label" style="border-right: solid 1px;border-color: #B11116;">
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_ORGANIZACION-LISTADO">
				<menu:item labelKey="sirius.gasto.gastoOrganizacion.label"><c:url value="/gasto/gasto-organizacion-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_PROYECTO-LISTADO">
				<menu:item labelKey="sirius.gasto.gastoProyecto.label"><c:url value="/gasto/gasto-proyecto-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_ACTIVIDAD-LISTADO">
				<menu:item labelKey="sirius.gasto.gastoActividad.listado.label"><c:url value="/gasto/gasto-actividad-query.do?method=initQuery"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_ACTIVIDAD-ALTA">
				<menu:item labelKey="sirius.gasto.gastoActividad.registracion.label"><c:url value="/gasto/gasto-actividad-form.do?method=initCreate"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_GASTO_ACTIVIDAD-HISTORICO">
				<menu:item labelKey="sirius.gasto.gastoActividad.historial.label"><c:url value="/gasto/gasto-actividad-list.do?method=initList"/></menu:item>
			</authz:authorize>
		</menu:submenu>
	</authz:authorize>

 	<authz:authorize ifAnyGranted="ENTIDAD_REPORTE_PERSONA-EJECUTAR, ENTIDAD_REPORTE_FINANCIADOR-EJECUTAR, ENTIDAD_REPORTE_PROVEEDOR-EJECUTAR, ENTIDAD_REPORTE_FINANZAS-EJECUTAR">
		<menu:submenu top="true" labelKey="sirius.menu.reportes.label" style="border-right: solid 1px;border-color: #B11116;">
			<authz:authorize ifAllGranted="ENTIDAD_REPORTE_PERSONA-EJECUTAR">
				<menu:item style="width: 160px;" labelKey="sirius.reporte.personas.label"><c:url value="/reportes/reporte-persona.do?method=initForm"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_REPORTE_FINANCIADOR-EJECUTAR">
				<menu:item style="width: 160px;" labelKey="sirius.reporte.financiadores.label"><c:url value="/reportes/reporte-financiador.do?method=initForm"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_REPORTE_PROVEEDOR-EJECUTAR">
				<menu:item style="width: 160px;" labelKey="sirius.reporte.proveedores.label"><c:url value="/reportes/reporte-proveedor.do?method=initForm"/></menu:item>
			</authz:authorize>
			<authz:authorize ifAllGranted="ENTIDAD_REPORTE_FINANZAS-EJECUTAR">
				<menu:item style="width: 160px;" labelKey="sirius.reporte.finanzas.label"><c:url value="/reportes/reporte-financiero.do?method=initForm"/></menu:item>
			</authz:authorize>
		</menu:submenu>
	</authz:authorize>
	
	<menu:submenu top="true" labelKey="sirius.menu.seguridad.label">
		<authz:authorize ifAllGranted="ENTIDAD_ROL-LISTADO">
			<menu:item labelKey="sirius.rol.label"><c:url value="/rol/rol-query.do?method=initQuery"/></menu:item>
		</authz:authorize>
		<authz:authorize ifAllGranted="ENTIDAD_PERFIL-LISTADO">
			<menu:item labelKey="sirius.perfil.label"><c:url value="/perfil/perfil-query.do?method=initQuery"/></menu:item>
		</authz:authorize>
		<authz:authorize ifAllGranted="ENTIDAD_USUARIO-LISTADO">
			<menu:item labelKey="sirius.usuario.label"><c:url value="/usuario/usuario-query.do?method=initQuery"/></menu:item>
		</authz:authorize>
		<authz:authorize ifAllGranted="ENTIDAD_REGLA-LISTADO">
			<menu:item labelKey="sirius.regla.label"><c:url value="/regla/regla-query.do?method=initQuery"/></menu:item>
		</authz:authorize>
		<menu:item labelKey="sirius.usuario.cambiarPassword.label"><c:url value="/usuario/usuario-password-form.do?method=initCambiarPassword"/></menu:item>
		<menu:item label="Logout"><c:url value="/public/logout.do"/></menu:item>		
	</menu:submenu>

</menu:menu>

