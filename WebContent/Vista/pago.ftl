<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>Candidatos</title>
	<!-- Recursos de Kaizenware 

	<link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <link href="assets/css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="assets/css/owl.carousel.css" rel="stylesheet">
    <link href="assets/css/owl.theme.default.min.css" rel="stylesheet">
    <link href="assets/css/magnific-popup.css" rel="stylesheet">

	-->
	<link href="assets/css/style.css" rel="stylesheet">
	<link rel="shortcut icon" type="image/x-icon" href="assets/images/LOGO-KG.png"/>
	
	<!-- Recursos del Prof -->
	<link href="resources/css/bootstrap.css" rel="stylesheet">
	<link href="resources/css/dataTables.bootstrap.min.css" rel="stylesheet">
	<link href="resources/css/screen.css" rel="stylesheet">
	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/jquery.validate.min.js"></script>
	<script src="resources/js/jquery.numeric.min.js"></script>
	<script src="resources/js/pages/pagos.js"></script>
</head>

 <body>
 
    <div class="main">
	<!-- HEADER START -->
		<#include "usuarios/header-admin.ftl">
	<!-- HEADER END -->
	
	<!-- NAVBAR START -->
	    <#assign x = Session.user.rol.id >
	    <#if x == 1>
	    <#include "usuarios/navbar-admin.ftl">
	    <#elseif x == 2>
	    <#include "usuarios/navbar-manager.ftl">
	    </#if>
	<!-- NAVBAR END -->

	
	<!-- CONTENT START -->
	<div id="content" class="col-md-10 col-md-offset-1">
	<h1 class="page-header">
        Gesti&oacute;n de Pagos
    </h1>

		<!-- VENTANA START -->
		<div class="modal fade" id="ventana" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4>Pago</h4>
					</div>
					<div class="modal-body">
						<div id="mensajesError"></div>
						</div>

						<form id="formPago" name="pago">
							<div class="form-group">
								<input type="hidden" id="fila" name="fila" />
								<input type="hidden" id="idString" name="idString" />								
								<br/>
								<label for="fechaa">Fecha Emisión</label>
								<input type="text" class="form-control" id="fecha" name="fecha" placeholder="Fecha" maxlenght="15"/>
								<br/>
								<label for="empleado">Empleado</label>
								<input type="text" class="form-control" id="empleado" name="empleado" placeholder="Empleado" />
								<input type="hidden" class="form-control" id="id_empleado" name="id_empleado"/>
								<br/>
								<label for="mes" placeholder="Mes" >Mes</label>
								<input type="text" class="form-control" id="mes" name="mes" maxlength="50"  />
								<input type="hidden" class="form-control" id="id_mes" name="id_mes"/>
								<br/>
								<label for="anio">Año</label>																									
								<input type="text" class="form-control" id="anio" name="anio" maxlength="13"  />
								<br/>
								<label for="montoo">Monto a Pagar</label>																									
								<input type="text" class="form-control" id="monto" name="monto" maxlength="10" />
								<br/>
								<label for="descripcion" >Descripcion</label>
								<textarea class="form-control" rows="2" id="descripcion" name="descripcion" maxlength="200" ></textarea>
								
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" id="btnGuardar" class="btn btn-primary"
							onclick="onGuardar();"><i class="glyphicon glyphicon-ok"></i>&nbsp;Guardar</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal"><i class="glyphicon glyphicon-remove"></i>&nbsp;Cerrar</button>
						
					</div>
				</div>
			</div>
		<!-- VENTANA END -->		
		
		<!-- BARRA DE HERRAMIENTAS START -->
		<div class="navbar navbar-default">
			<div class="container">
				<table class="col-md-10">
					<tr>
							<div id="mensajes" class="col-md-offset-1"></div>
					</tr>
				</table>
			</div>			
		</div>
		<!-- BARRA DE HERRAMIENTAS END -->
		
		<!-- TABLA START -->
		<div>
			<table id="tabla" class="table table-striped table-bordered table-hover table-condensed">
				<thead>
					<tr>
						<th>Empleado</th>
						<th>Mes</th>
						<th>Año</th>
						<th>Remuneracion Total</th>
						<th>Horas Laboradas</th>
						<th>Horas Justificadas</th>
						<th>Monto a Pagar</th>
						<th>Registrar</th>
					</tr>
				</thead>
				<tbody>
					<#list asistenciasList as asistencia>
					<tr>
						<input type="hidden" id="fila${asistencia.idString}" value="${asistencia.id}" />					
						<td><span id="empleado${asistencia.idString}">${asistencia.candidato.nombres} ${asistencia.candidato.apellidos}</span>
							<input type="hidden" id="id_empleado${asistencia.idString}" value="${asistencia.candidato.id}" />
						</td>	
						<td><span id="mes${asistencia.idString}"> ${asistencia.mes.mes} </span>
							<input type="hidden" id="id_mes${asistencia.idString}" value="${asistencia.mes.id}" />
						</td>
						<td><span id="anio${asistencia.idString}">${asistencia.anio}</span></td>
						<#assign remuneracion = asistencia.candidato.getRemuneracionTotal(asistencia.mes)>
						<td><span id="remuneracion${asistencia.idString}">${remuneracion}</span></td>
						<td><span id="horas_lab${asistencia.idString}">${asistencia.horas_trabajadas}</span></td>
						<#assign justificadas = asistencia.candidato.getHorasJustificadas(asistencia.mes.id, asistencia.anio)>
						<td><span id="horas_jus${asistencia.idString}">${justificadas}</span></td>
						<td><span id="monto${asistencia.idString}">${remuneracion*(asistencia.horas_trabajadas + justificadas)}</span></td>
						<td><a class="btn-info btn" data-toggle="modal"
							data-target="#ventana" onclick="onIncluir('${asistencia.idString}');">
							<i class="glyphicon glyphicon-plus"></i>Incluir</a>
						</td>
												
					</tr>
				 	</#list>
				</tbody>
			</table>
		</div>
		<!-- TABLA END -->
	</div>
	<!-- CONTENT END -->
	</div>
	
	<!-- JAVASCRIPT START -->
    <!-- Scripts de Kaizenware 
    <script src="https://apis.google.com/js/platform.js"></script>
    <script src="assets/js/jquery-3.1.1.js"></script>
    <script src="assets/js/desplegable.js"></script>
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/owl.carousel.min.js"></script>
    <script src="assets/js/auth.js"></script>
    <script src="assets/js/jquery.magnific-popup.js"></script>
    -->
   
    <script src="assets/js/script.js"></script>
 
    <!-- Scripts del prof -->
	<script src="resources/js/bootstrap.js"></script>
	<script src="resources/js/jquery.dataTables.min.js"></script>
	<script src="resources/js/dataTables.bootstrap.min.js"></script>
	<script src="resources/js/bootstrap-confirmation.js"></script>	
	<script type="text/javascript" class="init">		
		$('[data-toggle="confirmation"]').confirmation('hide');
	</script>
	<!-- JAVASCRIPT END -->

</body>
</html>
