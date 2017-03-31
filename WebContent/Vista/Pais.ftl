<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Mantenimiento</title>
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
	<script src="resources/js/pages/pais.js"></script>
		
</head>

<body class="color-fondo">
	<div class="main">
		<!-- HEADER logo START -->
		<header>
		<#include "usuarios/header-admin.ftl">
		   </header>
		<!-- HEADER logo END -->
		<!-- MENU START -->
		<#include "usuarios/navbar-admin.ftl">
		<!-- Menu button -->
		
<!-- CONTENT FLUID START--->
   <div id="page-wrapper" class="container-fluid">
	<!-- CONTENT PRINCIPAL START--->   
      <div class="contenedor-principal">

               <!-- HEADER START -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Gesti&oacute;n de Mantenimiento
                            <!-- <small>Subheading</small>-->
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>Administrar
                            </li>
                            <li class="active">
                                <i class="fa fa-file"></i> Pa&iacute;s
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- HEADER END -->

	<!-- CONTENT START -->
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
									<h4>Pa&iacute;s</h4>
								</div>
								
								<div class="modal-body">
									<div id="mensajesError"></div>
									<form id="formPais" name="pais">
										<div class="form-group">
											<label for="idString">ID</label>
											<input type="hidden" id="idString" name="idString" />								
											</br>
											<label for="pais">Nombre</label>
											<input type="text" class="form-control" id="pais" name="pais" placeholder="Nombre del Pa&iacute;s" maxlength="80" required/>
											<input type="hidden" id="id_pais" name="id_pais" />
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
									<th>Paises</th>
									<th>Modificar</th>
									<th>Eliminar</th>
								</tr>
							</thead>
							<tbody>
								<#list paisList as pais>
								<tr>	
														
									<td><span id="pais${pais.idString}">${pais.pais}</span>
										<input type="hidden" id="id_pais${pais.idString}" value="${pais.idString}" />
									</td>
									<td><a class="btn-warning btn" data-toggle="modal"
										data-target="#ventana" onclick="onModificar('${pais.idString}');"><i
											class="glyphicon glyphicon-edit"></i></a></td>
									<td><a id="btnEliminar${pais.idString}" class="btn-danger btn" data-toggle="confirmation" data-title="Estas seguro?" data-singleton="true" data-popout="true"
									data-href="javascript:onEliminar('${pais.idString}');"						
									data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt" data-btn-ok-class="btn-success" 
									data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger">
									<i class="glyphicon glyphicon-trash"></i></a></td>						
								</tr>
								</#list>
							</tbody>
						</table>
						</div>
						<!-- TABLA END -->
					</div>
                  </div>
                </div>
                <!-- CONTENT END -->
            </div>
            <!-- CONTENEDOR PRINCIPAL  END -->
        </div>
     	<!-- CONTAINER FLUID END -->
      	<#include "usuarios/footerusuario.ftl" />
	</div>
	
	<!-- JAVASCRIPT START -->
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
