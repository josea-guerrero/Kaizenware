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
	$('#formCandidato').validate();
});

function onIncluir() {
	disableInputs(false);
	$('#idString').val("");	
	$('#nombres').val("");
	$('#apellidos').val("");
	$('#telefono').val("");
	$('#correo').val("");
	$('#id_pais').val("-1");
	$('#ciudad').val("");
	$('#sexo').val("");
}

function onModificar(id) {
	disableInputs(false);
	$('#idString').val(id);	
	$('#nombres').val($("#nombres"+id).text());
	$('#apellidos').val($("#apellidos"+id).text());
	$('#telefono').val($("#telefono"+id).text());
	$('#correo').val($("#correo"+id).text());
	$('#id_pais').val($("#id_pais"+id).val());
	$('#ciudad').val($("#ciudad"+id).text());
	$('#sexo').val($("#sexo"+id).text().substr(0,1));
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
	inputs = ["nombres", "apellidos", "telefono", "correo", "id_pais", "ciudad", "sexo"];
	mensajes = ["los Nombres", "los Apellidos", "el Telefono", "el Correo", "el Pais", "la Ciudad", "el Sexo"];
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
		url: "ControladorCandidato",
		type: "POST",
		data: $("#formCandidato").serialize(),
		success: function(response) {
			if (response.indexOf("error:") != 0) {
				var candidato = JSON.parse(response);
				console.log("Intentando registrar")
				agregarFila(candidato);
				mostrarMensajeExito("Candidato incluido satisfactoriamente!");
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
		url: "ControladorCandidato",
		type: "PUT",
		contentType: "application/json; charset=ISO-8859-1",
		data: $("#formCandidato").serialize(),
		success: function(response) {
			if (response.indexOf("error:") != 0) {
				console.log("Intentado convertir JSON");
				var candidato = JSON.parse(response);
				console.log(candidato);
				eliminarFila(candidato.id);
				agregarFila(candidato);
				mostrarMensajeExito("Candidato modificado satisfactoriamente!!!");
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
		url: "ControladorCandidato",
		type: "DELETE",
		data: {"id":id},
		success: function(response) {
			if (response == "ok") {
				eliminarFila(id);
				mostrarMensajeExito('Candidato eliminado satisfactoriamente!!!');
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

										

function agregarFila(candidato) {
	tabla.row.add( [
        '<td><span id="nombres'+candidato.idString+'">'+candidato.nombres+'</span></td>',
        '<td><span id="apellidos'+candidato.idString+'">'+candidato.apellidos+'</span></td>',
        '<td><span id="telefono'+candidato.idString+'">'+candidato.telefono+'</span></td>',
        '<td><span id="correo'+candidato.idString+'">'+candidato.correo+'</span></td>',
    	'<td><span id="pais'+candidato.idString+'">'+candidato.pais.pais+'</span></td><input type="hidden" id="id_pais'+candidato.idString+'" value="'+candidato.id_pais+'" /></td>',
    	'<td><span id="ciudad'+candidato.idString+'">'+candidato.ciudad+'</span></td>',
    	'<td><span id="sexo'+candidato.idString+'">'+candidato.sexoString+'</span></td>',
    	'<td></td>',
        '<td></td>',
        '<td><a class="btn-success btn" data-toggle="modal" data-target="#ventana" onclick="onConsultar(\''+candidato.idString+'\');"><i class="glyphicon glyphicon-eye-open"></i></a></td>',
        '<td><a class="btn-warning btn" data-toggle="modal" data-target="#ventana" onclick="onModificar(\''+candidato.idString+'\');"><i class="glyphicon glyphicon-edit"></i></a></td>',
        '<td><a id="btnEliminar'+candidato.idString+'" class="btn-danger btn" data-toggle="confirmation" data-title="Estas seguro?" data-singleton="true" data-popout="true" data-href="javascript:onEliminar(\''+candidato.idString+'\');" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt" data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"> <i class="glyphicon glyphicon-trash"></i></a></td>'
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
	$('#idCategoria').prop('disabled', value);
	$('#cantidad').prop('disabled', value);
	$('#medida').prop('disabled', value);
	$('#precio').prop('disabled', value);
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