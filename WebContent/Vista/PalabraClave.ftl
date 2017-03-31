<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>KaizenWare | Candidatos</title>
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
	<script src="resources/js/pages/palabraclave.js"></script>
</head>

 <body class="color-fondo">
 
    <div class="main">
	<!-- HEADER START -->
		<#include "usuarios/header-admin.ftl">
	<!-- HEADER END -->
	
	<!-- NAVBAR START -->
	    <#include "usuarios/navbar-admin.ftl">
	    <!-- NAVBAR END -->
	
	<!-- CONTENT START -->
	<div id="page-wrapper" class="container-fluid">

           <div class="contenedor-principal">
           
            <!-- HEADER  -->
                <div class="row">
                    <div class="col-lg-12">
                       <h1 class="page-header">
                             Palabras Claves
                        </h1>

                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="PalabraClave">Palabras Claves</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-file"></i>
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- HEADER END -->
 <!-- CONTENT Row -->
<div class="row">
       <div class="col-lg-12">
			<div id="content">
			
		<!-- VENTANA START -->
		<div class="modal fade" id="ventana" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4>Palabra Clave</h4>
					</div>
					<div class="modal-body">
						<div id="mensajesError"></div>
						<form id="formPalabraClave" name="palabraclave">
							<div class="form-group">
								<input type="hidden" id="idString" name="idString" />								
								</br>
								<label for="palabra">Palabra Clave</label>
								<input type="text" class="form-control" id="palabra" name="palabra" placeholder="Palabra Clave" maxlength="50" required/>
								</br>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" id="btnGuardar" class="btn btn-primary"
							onclick="onGuardar();"><i class="glyphicon glyphicon-ok"></i>&nbsp;Guardar</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal"><i class="glyphicon glyphicon-remove"></i>&nbsp;Cerrar</button>
						</form>
					</div>
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
							data-target="#ventana" onclick="onIncluir();"><i
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
				<thead class="bg-primary">
					<tr>
						<th>Palabras Claves</th>
						<th>Modificar</th>
						<th>Eliminar</th>
					</tr>
				</thead>
				<tbody>
					<#list palabraclaveList as palabraclave>
					<tr>						
						<td><span id="pala${palabraclave.idString}"> ${palabraclave.palabra} </span></td>
						<td><a class="btn-warning btn" data-toggle="modal"
							   data-target="#ventana" onclick="onModificar('${palabraclave.idString}');">
							   <i class="glyphicon glyphicon-edit"></i></a></td>
						<td><a id="btnEliminar${palabraclave.idString}" class="btn-danger btn" data-toggle="confirmation" data-title="Estas seguro?" data-singleton="true" data-popout="true"
							data-href="javascript:onEliminar('${palabraclave.idString}');"						
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
		</div> <!--content end-->
		</div> <!--contenedor col-lg-12 -->
		</div> <!--contenedor row end-->
	</div> <!--contenedor principal-->
</div> <!--container fluid-->
	
	<#include "usuarios/footerusuario.ftl" />
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
