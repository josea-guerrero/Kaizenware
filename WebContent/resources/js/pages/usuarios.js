var tabla;
$(document).ready(function() {
	tabla = 
	$('#tabla').DataTable({
		"language": {
            "lengthMenu": "Mostrar _MENU_ registros por pagina",
            "zeroRecords": "No se encontraron registros",
            "info": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "infoEmpty": "No hay registros disponibles",
            "infoFiltered": "(filtrado de _MAX_ registros totales)",
            "search": "Buscar:",
            "paginate": {
        		"first":    "Primero",
        		"last":     "Ultimo",
        		"next":     "Siguiente",
        		"previous": "Anterior"
        	}
        }
	});
	$('#formUsuario').validate();
});

function onIncluir() {
	disableInputs(false);
	$('#idString').val("");	
	$('#nombre').val("");
	$('#contrasena').val("");
	$('#candidato').val("");
	$('#rol').val("");
}

function onModificar(id) {
	disableInputs(false);
	$('#idString').val(id);	
	$('#nombre').val($("#nombre"+id).text());
	$('#contrasena').val($("#contrasena"+id).text());
	
	$('#candidato').val($("#candidato"+id).text());
	$('#rol').val($("#idRol"+id).val());
}

function onConsultar(id) {
	onModificar(id);
	disableInputs(true);
}

function onGuardar() {
	if (validar()) {
		if ($('#idString').val().length > 0 ) {
			console.log("Intentando editar");
			onEditar();
		}
		else {
			onAgregar();
		}
	}
}

function validar() {
	mensaje = "";
	inputs = ["nombre", "contrasena", "rol"];
	mensajes = ["nombre de usuario", "la contraseña", "rol"];
	for (i=0; i<inputs.length; i++) {
		if (!$('#'+inputs[i]).val() || parseInt($('#'+inputs[i]).val()) <= 0) {
			mensaje += (mensaje.length>0?", ":"")+mensajes[i];
		}
	}
	if (mensaje.length > 0) {
		$('#mensajesError').
		append('<div class="alert alert-danger fade in"><a href="#" class="close" data-dismiss="alert">&times;</a><strong>Debe indicar:&nbsp;</strong>'+mensaje+'</div>');
		return false;
	}
	return true;
}

function onAgregar() {
	$.ajax({
		url: "ControladorUsuario",
		type: "POST",
		data: $("#formUsuario").serialize(),
		success: function(response) {
			if (response.indexOf("error:") != 0) {
				var usuario = JSON.parse(response);
				console.log("Intentando registrar")
				agregarFila(usuario);
				mostrarMensajeExito("Usuario incluido satisfactoriamente!");
			}
			else {
				mostrarMensajeError(response.substring(6));
 			}
		},
		error: function(jqXHR, estado, error) {
			mostrarMensajeError(error);
		},
		complete: function(jqXHR, estado) {
			$('#ventana').modal('hide');
		},
		timeout: 10000
	});
}

function onEditar() {
	$.ajax({
		url: "ControladorUsuario",
		type: "PUT",
		contentType: "application/json; charset=ISO-8859-1",
		data: $("#formusuario").serialize(),
		success: function(response) {
			if (response.indexOf("error:") != 0) {
				console.log("Intentado convertir JSON");
				var candidato = JSON.parse(response);
				console.log(candidato);
				eliminarFila(candidato.id);
				agregarFila(candidato);
				mostrarMensajeExito("Usuario modificado satisfactoriamente!!!");
			}
			else {
				mostrarMensajeError(response.substring(6));
 			}
		},
		error: function(jqXHR, estado, error) {
			mostrarMensajeError(error);
		},
		complete: function(jqXHR, estado) {
			$('#ventana').modal('hide');
		},
		timeout: 10000
	});
}

function onEliminar(id) {	
	$.ajax({
		url: "ControladorUsuario",
		type: "DELETE",
		data: {"id":id},
		success: function(response) {
			if (response == "ok") {
				eliminarFila(id);
				mostrarMensajeExito('Usuario eliminado satisfactoriamente!!!');
			}
			else {
				mostrarMensajeError(response);
			}
		},
		error: function(jqXHR, estado, error) {
			mostrarMensajeError(error);
		},
		timeout: 10000
	});
	
}

										

function agregarFila(usuario) {
	var msj;
	if (usuario.candidato==null)
		{
		msj = "no tiene";
		}
	else
		{
		msj = usuario.candidato.nombres;
		}
	tabla.row.add( [
        '<td><span id="nombre'+usuario.idString+'">'+usuario.nombre_usuario+'</span></td>',
        '<td><span id="contrasena'+usuario.idString+'">'+usuario.contrasena+'</span></td>',
        '<td><span id="rol'+usuario.idString+'">'+usuario.rol.rol+'</span></td>',
        '<td><span id="candidato'+usuario.idString+'">'+msj+'</span></td>',
        
        '<td><a class="btn-success btn" data-toggle="modal" data-target="#ventana" onclick="onConsultar(\''+usuario.idString+'\');"><i class="glyphicon glyphicon-eye-open"></i></a></td>',
        '<td><a class="btn-warning btn" data-toggle="modal" data-target="#ventana" onclick="onModificar(\''+usuario.idString+'\');"><i class="glyphicon glyphicon-edit"></i></a></td>',
        '<td><a id="btnEliminar'+usuario.idString+'" class="btn-danger btn" data-toggle="confirmation" data-title="Estas seguro?" data-singleton="true" data-popout="true" data-href="javascript:onEliminar(\''+usuario.idString+'\');" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt" data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"> <i class="glyphicon glyphicon-trash"></i></a></td>'
    ] ).draw();
	$('[data-toggle="confirmation"]').confirmation('hide');
}

function colocarPuntosMiles(num) {
	num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1.');
	num = num.split('').reverse().join('').replace(/^[\.]/,'');
	return num;
}

function colocarPuntosMilesYComaDecimal(numero) {
	console.log("numero: "+numero);	
	if (numero.toString().indexOf('.') >= 0) {
		num = numero.toString().replace('.', ',');		
	}
	else {
		num = numero.toString()+ ",00";
	}
	console.log("num: "+num);
	index = num.indexOf(',');
	entera = colocarPuntosMiles(num.substr(0, index));
	decimal = num.substring(index);
	decimal += (decimal.length==3)?"":"0";
	console.log("entera: "+entera+" - decimal: "+decimal);
	return entera+decimal;
}

function eliminarFila(id) {
	fila = $('#btnEliminar'+id).closest("tr")[0];
	$(fila).addClass('selected');
	tabla.row('.selected').remove().draw( false );
}

function disableInputs(value) {	
	$('#nombre').prop('disabled', value);
	$('#contrasena').prop('disabled', value);
	$('#candidato').prop('disabled', value);
	$('#rol').prop('disabled', value);
	
}

function mostrarMensajeExito(mensaje) {
	$('#mensajes').
	append('<div class="navbar-btn alert alert-success fade in"><a href="#" class="close" data-dismiss="alert">&times;</a><strong>Exito:&nbsp;</strong>'+mensaje+'</div>');
}

function mostrarMensajeError(mensaje) {
	$('#mensajes').
	append('<div class="navbar-btn alert alert-danger fade in"><a href="#" class="close" data-dismiss="alert">&times;</a><strong>Error:&nbsp;</strong>'+mensaje+'</div>');
}