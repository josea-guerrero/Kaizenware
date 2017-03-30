<html>
<head>
	<title>Store</title>
	<link href="resources/css/bootstrap.css" rel="stylesheet">
	<link href="resources/css/dataTables.bootstrap.min.css" rel="stylesheet">
	<link href="resources/css/screen.css" rel="stylesheet">
	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/jquery.validate.min.js"></script>
	<script src="resources/js/jquery.numeric.min.js"></script>
	<script src="resources/js/pages/productos.js"></script>
</head>

<body class="col-md-10 col-md-offset-1">
	<!-- NAVBAR START -->
      <#include "usuarios/navbar-admin.ftl">
	<!-- NAVBAR END -->
	
	
	<!-- HEADER START -->
	<#include "usuarios/header-admin.ftl">
	<!-- HEADER END -->
	
	<!-- CONTENT START -->
	<div id="content">

		<!-- VENTANA START -->
		<div class="modal fade" id="ventana" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4>Gesti&oacute;n de Usuario</h4>
					</div>
					<div class="modal-body">
						<div id="mensajesError"></div>
						<form id="formUsuario" name="usuario">
							<div class="form-group">
								<input type="hidden" id="idString" name="idString" />								
								</br>
								<label for="nombre">Nombre de Usuario</label>
								<input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre" maxlength="80" required/>
								</br>
								<label for="contrasena" placeholder="Contraseña" >Contraseña</label>
								<input type="text" class="form-control" id="contrasena" name="contrasena" placeholder="contraseña" maxlength="80" required/>
								</br>
								<label for="idCandidato">Candidato</label>																									
								<select id="idCandidato" name="idCandidato" class="form-control" >
									<option value="-1">Seleccione</option> 
									<#list empleadoList as empleado>
										<option value="${empleado.id}">${empleado.nombres} ${empleado.apellidos}</option> 
									</#list>
								</select>
								</br>
								<label for="rol">Rol</label>
								<input type="text" class="form-control" id="rol" name="rol" placeholder="rol" maxlength="6" required/>
								</br>
								
								</br>
								<label for="fotoPerfil">Foto de Perfil</label>
								<input type="text" class="form-control" id="foto" name="foto" placeholder="foto de perfil" maxlength="6" required/>
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
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Contraseña</th>
						<th>Candidato</th>
						<th>Rol</th>
						<th>Foto de perfil</th>
						
						<th>Consultar</th>
						<th>Modificar</th>
						<th>Eliminar</th>
					</tr>
				</thead>
				<tbody>
					<#list usuariosList as usuario>
					<tr>						
						<td><span id="nombre${usuario.idString}">${usuario.nombre_usuario}</span></td>
						<td><span id="contraseña${usuario.idString}">${usuario.contrasena}</span></td>
						<td><span id="candidato${usuario.idString}">${usuario.candidato.nombre}</span>
						<input type="hidden"
							id="idCandidato${usuario.idString}"
							value="${usuario.idCandidato}" />
						</td>
						<td><span id="cantidadString${producto.idString}">${producto.cantidadString}</span>
						<input type="hidden"
							id="cantidad${producto.idString}"
							value="${producto.cantidad}" />
						</td>
						<td><span id="medidaString${producto.idString}">${producto.medidaString}</span>
						<input type="hidden"
							id="medida${producto.idString}"
							value="${producto.medida}" />
						</td>
						<td><span id="precioString${producto.idString}">${producto.precioString}</span>
						<input type="hidden"
							id="precio${producto.idString}"
							value="${producto.precio}" />						
						</td>
						<td><a class="btn-success btn" data-toggle="modal"
							data-target="#ventana" onclick="onConsultar('${producto.idString}');"><i
								class="glyphicon glyphicon-eye-open"></i></a></td>
						<td><a class="btn-warning btn" data-toggle="modal"
							data-target="#ventana" onclick="onModificar('${producto.idString}');"><i
								class="glyphicon glyphicon-edit"></i></a></td>
						<td><a id="btnEliminar${producto.idString}" class="btn-danger btn" data-toggle="confirmation" data-title="Estas seguro?" data-singleton="true" data-popout="true"
						data-href="javascript:onEliminar('${producto.idString}');"						
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
	<!-- CONTENT END -->
	
	
	<!-- JAVASCRIPT START -->
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