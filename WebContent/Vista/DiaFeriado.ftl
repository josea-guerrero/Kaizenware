<!DOCTYPE html>
<html>
<head>
	     <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>KaizenWare | D&iacute;as Feriados</title>
	<!-- Recursos de Kaizenware
		<link href="assets/css/bootstrap.min.css" rel="stylesheet">
		<link href="assets/css/bootstrap.css" rel="stylesheet">
		<link href="assets/css/bootstrap-theme.min.css" rel="stylesheet">
	    <link href="assets/css/owl.carousel.css" rel="stylesheet">
	    <link href="assets/css/owl.theme.default.min.css" rel="stylesheet">
	    <link href="assets/css/magnific-popup.css" rel="stylesheet">
		-->
		
        <!-- logo barra -->
        
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/LOGO-KG.png"/>
		<link href="assets/css/style.css" rel="stylesheet">
        
    	<link href="resources/css/bootstrap.css" rel="stylesheet">
		<link href="resources/css/dataTables.bootstrap.min.css" rel="stylesheet">
		<link href="resources/css/screen.css" rel="stylesheet">
		<script src="resources/js/jquery.js"></script>
		<script src="resources/js/jquery.validate.min.js"></script>
		<script src="resources/js/jquery.numeric.min.js"></script>
		
		<script src="resources/js/pages/diaferiado.js"></script>
	
</head>
 <body class="color-fondo">
        <div class="main">
			<!-- HEADER START -->
			<!-- CABECERA DE MENU -->
			  <#include "usuarios/header-admin.ftl"/>
			  <!-- HEADER END -->
			
			<!-- NAVBAR START -->
		    <#assign x = Session.user.rol.id >
		    <#if x == 1>
		    <#include "usuarios/navbar-admin.ftl">
		    <#elseif x == 2>
		    <#include "usuarios/navbar-manager.ftl">
		    </#if>
			<!-- NAVBAR END -->
			
		<div id="page-wrapper" class="container-fluid">

           <div class="contenedor-principal">

                <!-- HEADER  -->
                <div class="row">
                    <div class="col-lg-12">
                       <h1 class="page-header">
                             D&iacute;as Feriados
                            
                        </h1>

                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="DiaFeriado.ftl">D&iacute;as Feriados</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-file"></i>
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
									<h4>D&iacute;as Feriados</h4>
								</div>
								
								<div class="modal-body">
									<div id="mensajesError"></div>
									<form id="formDiaFeriado" name="diaferiado" >
										<div class="form-group">
										<input type="hidden" id="idString" name="idString" />
										</br>
										<label for="dia"> D&iacute;a Feriado:</label> 
			                            <input class="form-control" type="text" id="dia" name="dia" placeholder="Indique la fecha solo el d&iacute;a" maxlength="2" required/>
			                            </br>
										<label for="mes">Mes</label>																									
										<select id="mes" name="mes" class="form-control" >
											<option value="-1">Seleccione</option> 
												<#list mesList as mes>
													<option value="${mes.idString}">${mes.mes}</option> 
												</#list>
										</select>
										</br>
										<label for="nombre">Nombre</label>
										<input type="text" class="form-control" id="nombre" name="nombre" placeholder="Motivo del D&iacute;a Feriado" maxlength="80" required/>
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
						<th>D&iacute;a</th>
						<th>Mes</th>
						<th>Nombre</th>
						<th>Consultar</th>
						<th>Modificar</th>
						<th>Eliminar</th>
					</tr>
				</thead>
				<tbody>
					<#list diaferiadoList as diaferiado>
					<tr>						
						<td><span id="dia${diaferiado.idString}">${diaferiado.dia}</span></td>
						<td><span id="mes${diaferiado.idString}">${diaferiado.mes.mes}</span>
						<input type="hidden" id="id_mes${diaferiado.idString}"
							value="${diaferiado.mes.idString}" /></td>
						<td><span id="nombre${diaferiado.idString}">${diaferiado.nombre}</span></td>
						
						<td><a class="btn-success btn" data-toggle="modal"
								data-target="#ventana" onclick="onConsultar('${diaferiado.idString}');"><i
								class="glyphicon glyphicon-eye-open"></i></a>
								</td>
						<td><a class="btn-warning btn" data-toggle="modal"
								data-target="#ventana" onclick="onModificar('${diaferiado.idString}');"><i
								class="glyphicon glyphicon-edit"></i></a>
								</td>
						<td><a id="btnEliminar${diaferiado.idString}" class="btn-danger btn" data-toggle="confirmation" data-title="Estas seguro?" data-singleton="true" data-popout="true"
								data-href="javascript:onEliminar('${diaferiado.idString}');"						
								data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt" data-btn-ok-class="btn-success" 
								data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger">
								<i class="glyphicon glyphicon-trash"></i></a></td>
						</tr>
					</#list>
				</tbody>
			</table>
		</div>
		<!-- TABLA END -->
		</div> <!-- col-lg-12 -->
	</div>
	<!-- row -->
</div>
<!-- CONTENT END -->
</div>
</div>

      	<#include "usuarios/footerusuario.ftl" />
 </div>

        <!-- Scripts section -->
            <script src="assets/js/script.js"></script>
 
    <!-- Scripts del prof -->
	<script src="resources/js/bootstrap.js"></script>
	<script src="resources/js/jquery.dataTables.min.js"></script>
	<script src="resources/js/dataTables.bootstrap.min.js"></script>
	<script src="resources/js/bootstrap-confirmation.js"></script>	
	<script type="text/javascript" class="init">		
		$('[data-toggle="confirmation"]').confirmation('hide');
	</script>

    </body>
</html>
