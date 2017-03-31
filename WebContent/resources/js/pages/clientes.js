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
	$('#formCliente').validate();
});

function onIncluir() {
	disableInputs(false);
	$('#idString').val("");	
	$('#nombre').val("");
	$('#nom_contacto').val("");
	$('#tlf_contacto').val("");
	$('#correo').val("");
	$('#correo_contacto').val("");
	$('#id_pais').val("-1");
	
}

function onModificar(id) {
	disableInputs(false);
	$('#idString').val(id);	
	$('#nombre').val($("#nombre"+id).text());
	$('#nom_contacto').val($("#nom_contacto"+id).text());
	$('#tlf_contacto').val($("#tlf_contacto"+id).text());
	$('#correo').val($("#correo"+id).text());
	$('#correo_contacto').val($("#correo_contacto"+id).text());
	$('#id_pais').val($("#id_pais"+id).val());
	
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
	inputs = ["nombre", "nom_contacto", "tlf_contacto", "correo", "correo_contacto" ,"id_pais"];
	mensajes = ["el Nombre", "el contacto", "el Telefono", "el Correo","el correo del contacto", "el Pais"];
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
		url: "ControladorCliente",
		type: "POST",
		data: $("#formCliente").serialize(),
		success: function(response) {
			if (response.indexOf("error:") != 0) {
				var cliente = JSON.parse(response);
				console.log("Intentando registrar")
				agregarFila(cliente);
				mostrarMensajeExito("CLiente incluido satisfactoriamente!");
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
		url: "ControladorCliente",
		type: "PUT",
		contentType: "application/json; charset=ISO-8859-1",
		data: $("#formCliente").serialize(),
		success: function(response) {
			if (response.indexOf("error:") != 0) {
				console.log("Intentado convertir JSON");
				var cliente = JSON.parse(response);
				console.log(cliente);
				eliminarFila(cliente.id);
				agregarFila(cliente);
				mostrarMensajeExito("Cliente modificado satisfactoriamente!!!");
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
		url: "ControladorCliente",
		type: "DELETE",
		data: {"id":id},
		success: function(response) {
			if (response == "ok") {
				eliminarFila(id);
				mostrarMensajeExito('Cliente eliminado satisfactoriamente!!!');
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

										

function agregarFila(cliente) {
	tabla.row.add( [
        '<td><span id="nombre'+cliente.idString+'">'+cliente.nombre+'</span></td>',
        '<td><span id="nom_contacto'+cliente.idString+'">'+cliente.nom_contacto+'</span></td>',
        '<td><span id="tlf_contacto'+cliente.idString+'">'+cliente.tlf_contacto+'</span></td>',
        '<td><span id="correo'+cliente.idString+'">'+cliente.correo+'</span></td>',
        '<td><span id="correo_contacto'+cliente.idString+'">'+cliente.correo_contacto+'</span></td>',
    	'<td><span id="pais'+cliente.idString+'">'+cliente.pais.pais+'</span></td><input type="hidden" id="id_pais'+cliente.idString+'" value="'+cliente.id_pais+'" /></td>',

        '<td><a class="btn-success btn" data-toggle="modal" data-target="#ventana" onclick="onConsultar(\''+cliente.idString+'\');"><i class="glyphicon glyphicon-eye-open"></i></a></td>',
        '<td><a class="btn-warning btn" data-toggle="modal" data-target="#ventana" onclick="onModificar(\''+cliente.idString+'\');"><i class="glyphicon glyphicon-edit"></i></a></td>',
        '<td><a id="btnEliminar'+cliente.idString+'" class="btn-danger btn" data-toggle="confirmation" data-title="Estas seguro?" data-singleton="true" data-popout="true" data-href="javascript:onEliminar(\''+cliente.idString+'\');" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt" data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"> <i class="glyphicon glyphicon-trash"></i></a></td>'
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
	$('#nom_contacto').prop('disabled', value);
	$('#tlf_contacto').prop('disabled', value);
	$('#correo').prop('disabled', value);
	$('#correo_contacto').prop('disabled', value);
	$('#id_pais').prop('disabled', value);
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