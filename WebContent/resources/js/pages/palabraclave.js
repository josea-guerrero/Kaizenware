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
	$('#formPalabra').validate();
});

function onIncluir() {
	disableInputs(false);
	$('#idString').val("");	
	$('#palabra').val("");
}

function onModificar(id) {
	disableInputs(false);
	$('#idString').val(id);	
	$('#palabra').val($("#pala"+id).text());
	}

function onConsultar(id) {
	onModificar(id);
	disableInputs(true);
}

function onGuardar() {
	if (validar()) {
		if ($('#idString').val().length > 0 ) {
			onEditar();
		}
		else {
			onAgregar();
		}
	}
}

function validar() {
	mensaje = "";
	if (!$('#palabra').val()) {
		mensaje += "la descripcion";
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
		url: "ControladorPalabraClave",
		type: "POST",
		data: $("#formPalabra").serialize(),
		success: function(response) {
			if (response.indexOf("error:") != 0) {
				var palabra = JSON.parse(response);
				agregarFila(palabra);
				mostrarMensajeExito("Item incluido satisfactoriamente!!!");
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
		url: "ControladorPalabraClave",
		type: "PUT",
		data: $("#formPalabra").serialize(),
		success: function(response) {
			if (response.indexOf("error:") != 0) {
				var palabra = JSON.parse(response);
				eliminarFila(palabra.id);
				agregarFila(palabra);
				mostrarMensajeExito("Item modificado satisfactoriamente!!!");
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
		url: "ControladorPalabraClave",
		type: "DELETE",
		data: {"id":id},
		success: function(response) {
			if (response == "ok") {
				eliminarFila(id);
				mostrarMensajeExito('Item eliminado satisfactoriamente!!!');
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

function agregarFila(palabra) {
	tabla.row.add( [
        '<td><span id="id'+palabra.idString+'">'+palabra.id+'</span></td>',
        '<td><span id="pala'+palabra.idString+'">'+palabra.palabra+'</span></td>',
        '<td><a class="btn-success btn" data-toggle="modal" data-target="#ventana" onclick="onConsultar(\''+palabra.idString+'\');"><i class="glyphicon glyphicon-eye-open"></i></a></td>',
        '<td><a class="btn-warning btn" data-toggle="modal" data-target="#ventana" onclick="onModificar(\''+palabra.idString+'\');"><i class="glyphicon glyphicon-edit"></i></a></td>',
        '<td><a id="btnEliminar'+palabra.idString+'" class="btn-danger btn" data-toggle="confirmation" data-title="Estas seguro?" data-singleton="true" data-popout="true" data-href="javascript:onEliminar(\''+producto.idString+'\');" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt" data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"> <i class="glyphicon glyphicon-trash"></i></a></td>'
    ] ).draw();
	$('[data-toggle="confirmation"]').confirmation('hide');
}


function eliminarFila(id) {
	fila = $('#btnEliminar'+id).closest("tr")[0];
	$(fila).addClass('selected');
	tabla.row('.selected').remove().draw( false );
}

function disableInputs(value) {	
	$('#id').prop('disabled', value);
	$('#palabra').prop('disabled', value);
	$('#btnGuardar').prop('disabled', value);
}

function mostrarMensajeExito(mensaje) {
	$('#mensajes').
	append('<div class="navbar-btn alert alert-success fade in"><a href="#" class="close" data-dismiss="alert">&times;</a><strong>Exito:&nbsp;</strong>'+mensaje+'</div>');
}

function mostrarMensajeError(mensaje) {
	$('#mensajes').
	append('<div class="navbar-btn alert alert-danger fade in"><a href="#" class="close" data-dismiss="alert">&times;</a><strong>Error:&nbsp;</strong>'+mensaje+'</div>');
}