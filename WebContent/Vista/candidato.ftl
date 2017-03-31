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

	
	<!-- CONTENT START -->
	<div id="content" class="col-md-10 col-md-offset-1">
	
		<h1 class="page-header">
           Gesti&oacute;n de Candidatos
        </h1>

		<!-- VENTANA START -->
		<div class="modal fade" id="ventana" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4>Candidato</h4>
					</div>
					<div class="modal-body">
						<div id="mensajesError"></div>
						</div>
						<form id="formCandidato" name="candidato">
							<div class="form-group">
								<input type="hidden" id="idString" name="idString" />								
								<br/>
								<label for="nombres">Nombres</label>
								<input type="text" class="form-control" id="nombres" name="nombres" placeholder="Nombres" maxlength="50" required/>
								<br/>
								<label for="apellidos" placeholder="Apellidos" >Apellidos</label>
								<input type="text" class="form-control" id="apellidos" name="apellidos" maxlength="50" required />
								<br/>
								<label for="telefono">Telefono</label>																									
								<input type="text" class="form-control" id="telefono" name="telefono" maxlength="13" required />
								<br/>
								<label for="correo">Correo</label>																									
								<input type="text" class="form-control" id="correo" name="correo" maxlength="100" required/>
								<br/>
								<label for="id_pais">Pais</label>
								<select id="id_pais" name="id_pais" class="form-control" >
									<option value="-1">Seleccione</option> 
									<#list paisesList as pais>
										<option value="${pais.idString}">${pais.pais}</option> 
									</#list>
								</select>
								<br/>
								<label for="ciudad">Ciudad</label>
								<input type="text" class="form-control" id="ciudad" name="ciudad" placeholder="Ciudad" maxlength="100" />
								<br/>										
								<label for="sexo">Sexo</label>
								<select id="sexo" name="sexo" class="form-control" >
									<option value="-1">Seleccione</option> 
										<option value="F">Femenino</option>
										<option value="M">Masculino</option>
								</select>
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
						<td>
							<a class="navbar-btn btn-info btn" data-toggle="modal"
							data-target="#ventana" onclick="onIncluir();">
							<i
							class="glyphicon glyphicon-plus"></i>&nbsp;Incluir</a></td>
						<td>
							<div id="mensajes" class="col-md-offset-1"></div>
						</td>
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
						<th>Nombres</th>
						<th>Apellidos</th>
						<th>Tel&eacute;fono</th>
						<th>Correo</th>
						<th>País</th>
						<th>Ciudad</th>
						<th>Sexo</th>
						<th>Palabras Clave</th>
						<th>Habilidades</th>
						<th>Consultar</th>
						<th>Modificar</th>
						<th>Eliminar</th>
					</tr>
				</thead>
				<tbody>
					<#list candidatosList as candidato>
					<tr>						
						<td><span id="nombres${candidato.idString}"> ${candidato.nombres} </span></td>
						<td><span id="apellidos${candidato.idString}">${candidato.apellidos}</span></td>
						<td><span id="telefono${candidato.idString}">${candidato.telefono}</span></td>
						<td><span id="correo${candidato.idString}">${candidato.correo}</span></td>
						<td><span id="pais${candidato.idString}">${candidato.pais.pais}</span>
							<input type="hidden"
								id="id_pais${candidato.idString}"
								value="${candidato.pais.id}" />
						</td>
						<td><span id="ciudad${candidato.idString}">${candidato.ciudad}</span></td>
						<td><span id="sexo${candidato.idString}">${candidato.sexoString}</span></td>
						<td><select id="palabrasClave${candidato.idString}" name="palabrasClave" class="form-control" >
								<option value="-1">Desplegar</option> 
								<#list candidato.palabrasClaves as palabra>
									<option value="${palabra.idString}">${palabra.palabra}</option> 
								</#list>
							</select>
						
						</td>
						<td><select id="habilidades${candidato.idString}" name="habilidades" class="form-control" >
								<option value="-1">Desplegar</option> 
								<#list candidato.habilidades as habilidad>
									<option value="${habilidad.idString}">${habilidad.habilidad}</option> 
								</#list>
							</select>
						
						</td>
						<td><a class="btn-success btn" data-toggle="modal"
							data-target="#ventana" onclick="onConsultar('${candidato.idString}');">
							<i class="glyphicon glyphicon-eye-open"></i></a>
						</td>
						<td><a class="btn-warning btn" data-toggle="modal"
							   data-target="#ventana" onclick="onModificar('${candidato.idString}');">
							   <i class="glyphicon glyphicon-edit"></i></a>
						</td>
						<td><a id="btnEliminar${candidato.idString}" class="btn-danger btn" data-toggle="confirmation" data-title="Estas seguro?" data-singleton="true" data-popout="true"
							data-href="javascript:onEliminar('${candidato.idString}');"						
							data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt" data-btn-ok-class="btn-success" 
							data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger">
							<i class="glyphicon glyphicon-trash"></i></a>
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
