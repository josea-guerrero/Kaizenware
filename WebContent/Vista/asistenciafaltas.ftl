<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>Reporte de Assitencias y Faltas</title>
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
	<script src="resources/js/pages/candidatos.js"></script>
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
	
	<h1 class="page-header">
                            Reporte de Asistencias y Faltas

                        </h1>

	
	<!-- CONTENT START -->
	<div id="content" class="col-md-10 col-md-offset-1">
		
		
		
		<!-- TABLA START -->
		<div>
			<table id="tabla" class="table table-striped table-bordered table-hover table-condensed">
				<thead>
					<tr>
						<th>Nombres</th>
						<th>Apellidos</th>
						<th>Horas trabajadas</th>
						<th>Mes</th>
						<th>A&ntilde;o</th>
						<th>Horas Faltadas</th>
						<th>Fecha</th>
						
					</tr>
				</thead>
				<tbody>
					<#list asistenciasList as asist>
					<tr>
					     <td><span id="nombres${asist.idString}"> <#if asist.id.usuario.candidato?? >
						 												${asist.id.usuario.candidato.nombres}
						 											<#else>
						 											${"No tiene"}
						                                              </#if></span>	</td>					
					     <td><span id="apellidos${asist.idString}"> <#if asist.id.usuario.candidato?? >
						 												${asist.id.usuario.candidato.apellidos}
						 											<#else>
						 											${"No tiene"}
						                                              </#if></span>	</td>						
						<td><span id="horastrabajadas${asist.idString}">${asist.id.usuario.candidato.asistenciasMensuales.horas_trabajadas}</span></td>
						<td><span id="mes${asist.idString}">${asist.id.usuario.candidato.asistenciasMensuales.mes.mes}</span></td>
						<td><span id="anio${asist.idString}">${asist.id.usuario.candidato.asistenciasMensuales.anio}</span></td>
					    <#list faltasList as f>
					    <td><span id="horasfaltadas${f.idString}">${f.id.usuario.candidato.faltas.tipoFaltaString}</span></td>
					    <td><span id="fecha${f.idString}">${f.id.usuario.candidato.faltas.fechaString}</span></td>
					</tr>
					
							</select>
						</span>
						
				 	</#list>
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
