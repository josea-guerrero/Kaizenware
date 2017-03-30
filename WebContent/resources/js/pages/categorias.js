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
	$('#formCategoria').validate();
});

function onIncluir() {
	disableInputs(false);
	$('#idString').val("");
	$('#idCategoriaPadre').val("-1");
	$('#nombre').val("");
	$('#descripcion').val("");
}

function onModificar(id) {
	disableInputs(false);
	$('#idString').val(id);
	$('#idCategoriaPadre').val($("#idCategoriaPadre"+id).val());
	$('#nombre').val($("#nombre"+id).text());
	$('#descripcion').val($("#descripcion"+id).text());
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
	if (!$('#nombre').val()) {
		mensaje += "el Nombre";
	}
	if (mensaje.length > 0) {
		$('#mensajesError').
		append('<div class="alert alert-danger fade in"><a href="#" class="close" data-dismiss="alert">&times;</a><strong>Debe indicar:&nbsp;</strong>'+mensaje+'</div>');
		return false;
	}
	return true;
}

function onAgregar() {
	var datos = $("#formCategoria").serialize();
	console.log("datos: "+JSON.stringify(datos));
	$.ajax({
		url: "ControladorCategorias",
		type: "POST",
		data: datos,
		success: function(response) {
			if (response.indexOf("error:") != 0) {
				var categoria = JSON.parse(response);
				agregarFila(categoria);
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
	var datos = $("#formCategoria").serialize();
	console.log("datos: "+JSON.stringify(datos));
	$.ajax({
		url: "ControladorCategorias",
		type: "PUT",
		data: datos,
		success: function(response) {
			console.log("response: "+JSON.stringify(response));
			if (response.indexOf("error:") != 0) {
				var categoria = JSON.parse(response);
				eliminarFila(categoria.id);
				agregarFila(categoria);
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
		url: "ControladorCategorias",
		type: "DELETE",
		data: {"id":id},
		success: function(response) {
			console.log("'"+response+"'");
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

function agregarFila(categoria) {
	//console.log(JSON.stringify(categoria));
	tabla.row.add( [
        '<td>'+categoria.codigo+'<input type="hidden" id="idCategoriaPadre'+categoria.idString+'" value="'+categoria.idCategoriaPadre+'" /></td>',
        '<td><span id="nombre'+categoria.idString+'">'+categoria.nombre+'</span></td>',
        '<td><span id="descripcion'+categoria.idString+'">'+categoria.descripcion+'</span></td>',
        '<td><a class="btn-success btn" data-toggle="modal" data-target="#ventana" onclick="onConsultar(\''+categoria.idString+'\');"><i class="glyphicon glyphicon-eye-open"></i></a></td>',
        '<td><a class="btn-warning btn" data-toggle="modal" data-target="#ventana" onclick="onModificar(\''+categoria.idString+'\');"><i class="glyphicon glyphicon-edit"></i></a></td>',
        '<td><a id="btnEliminar'+categoria.idString+'" class="btn-danger btn" data-toggle="confirmation" data-title="Estas seguro?" data-singleton="true" data-popout="true" data-href="javascript:onEliminar(\''+categoria.idString+'\');" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt" data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"> <i class="glyphicon glyphicon-trash"></i></a></td>'
    ] ).draw();
	$('[data-toggle="confirmation"]').confirmation('hide');
	$('#idCategoriaPadre')
    .append($('<option>', { value : categoria.idString })
    .text(categoria.codigo+" - "+categoria.nombre));
}

function eliminarFila(id) {
	fila = $('#btnEliminar'+id).closest("tr")[0];
	$(fila).addClass('selected');
	tabla.row('.selected').remove().draw( false );
	$('#idCategoriaPadre option[value="'+id+'"]').remove();
}

function disableInputs(value) {
	$('#idCategoriaPadre').prop('disabled', value);
	$('#nombre').prop('disabled', value);
	$('#descripcion').prop('disabled', value);
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