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
	$('#formProyecto').validate();
});

function onIncluir() {
	disableInputs(false);
	$('#idString').val("");	
	$('#nombre').val("");
	$('#descripcion').val("");
	$('#id_cliente').val("-1");
	
}

function onModificar(id) {
	disableInputs(false);
	$('#idString').val(id);	
	$('#nombre').val($("#nombre"+id).text());
	$('#descripcion').val($("#descripcion"+id).text());
	$('#id_cliente').val($("#id_cliente"+id).val());
	
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
	inputs = ["nombre", "descripcion", "id_cliente"];
	mensajes = ["El Nombre", "la descripcion", "el Cliente"];
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
		url: "ControladorProyecto",
		type: "POST",
		data: $("#formProyecto").serialize(),
		success: function(response) {
			if (response.indexOf("error:") != 0) {
				var proyecto = JSON.parse(response);
				console.log("Intentando registrar")
				agregarFila(proyecto);
				mostrarMensajeExito("Proyecto incluido satisfactoriamente!");
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
		url: "ControladorProyecto",
		type: "PUT",
		contentType: "application/json; charset=ISO-8859-1",
		data: $("#formProyecto").serialize(),
		success: function(response) {
			if (response.indexOf("error:") != 0) {
				console.log("Intentado convertir JSON");
				var proyecto = JSON.parse(response);
				console.log(proyecto);
				eliminarFila(proyecto.id);
				agregarFila(proyecto);
				mostrarMensajeExito("Proyecto modificado satisfactoriamente!!!");
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
		url: "ControladorProyecto",
		type: "DELETE",
		data: {"id":id},
		success: function(response) {
			if (response == "ok") {
				eliminarFila(id);
				mostrarMensajeExito('Proyecto eliminado satisfactoriamente!!!');
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

										

function agregarFila(proyecto) {
	tabla.row.add( [
        '<td><span id="nombre'+proyecto.idString+'">'+proyecto.nombre+'</span></td>',
        '<td><span id="descripcion'+proyecto.idString+'">'+proyecto.descripcion+'</span></td>',
        '<td><span id="cliente'+proyecto.idString+'">'+proyecto.cliente.nombre+'</span></td><input type="hidden" id="id_cliente'+proyecto.idString+'" value="'+proyecto.id_cliente+'" /></td>',
        '<td><a class="btn-success btn" data-toggle="modal" data-target="#ventana" onclick="onConsultar(\''+proyecto.idString+'\');"><i class="glyphicon glyphicon-eye-open"></i></a></td>',
        '<td><a class="btn-warning btn" data-toggle="modal" data-target="#ventana" onclick="onModificar(\''+proyecto.idString+'\');"><i class="glyphicon glyphicon-edit"></i></a></td>',
        '<td><a id="btnEliminar'+proyecto.idString+'" class="btn-danger btn" data-toggle="confirmation" data-title="Estas seguro?" data-singleton="true" data-popout="true" data-href="javascript:onEliminar(\''+proyecto.idString+'\');" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt" data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"> <i class="glyphicon glyphicon-trash"></i></a></td>'
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
	$('#descripcion').prop('disabled', value);
	$('#id_cliente').prop('disabled', value);
	
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