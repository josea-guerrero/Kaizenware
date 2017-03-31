<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Mi Perfil | KAIZENWARE</title>
        <!-- logo barra -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/LOGO-KG.png"/>


       <!--imagenes+efecto despliegue menu+tabla de datos-->
       
        <!--<link href="assets/css/bootstrap-theme.min.css" rel="stylesheet">-->

        <!--imagen de fondo-->
       <link href="assets/css/style-perfil.css" rel="stylesheet">

        
        <link href="assets/css/style.css" rel="stylesheet">
      




	<!-- Recursos del Prof -->
	<link href="resources/css/bootstrap.css" rel="stylesheet">
	<link href="resources/css/dataTables.bootstrap.min.css" rel="stylesheet">
	<link href="resources/css/screen.css" rel="stylesheet">
	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/jquery.validate.min.js"></script>
	<script src="resources/js/jquery.numeric.min.js"></script>
	

        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>




<body >
  
<div class="main">
			
               <!-- HEADER START -->
		<#include "usuarios/header-admin.ftl" />
	<!-- HEADER END -->
			
			
         <!-- NAVBAR START -->
        <#include "usuarios/navbar-employee.ftl" />
        
       <!-- NAVBAR END -->


<!--MODAL CONTENT START-->

<div id="content">      
      
        	 
        	   	   	 <!-- VENTANA MODAL START -->
		<div class="modal fade" id="cargos" role="dialog">
			<div class="modal-dialog">
			
				<div class="modal-content">
				
					<div class="modal-header">
					
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Cargos del Usuario</h4> 
                    </div>
            <!-- MODAL HEADER END -->
                    
            <div class="modal-body"> 
            
            <!-- TABLA START -->
			<table id="tabla" class="table table-striped table-bordered table-hover table-condensed">
				<thead>
					<tr>
						<th>Cargo</th>
						<th>Remuneraci&oacute;n</th>
						<th>Divisa</th>
						<th>Tipo de Remuneraci&oacute;n</th>
						<th>Proyecto</th>	
						
					</tr>
				</thead>
				<tbody>
					<#list Session.user.candidato.cargos as cargo>
					<tr>						
						<td><span id="nombre${cargo.idString}"> ${cargo.nombre}</span></td>
						<td><span id="remuneracion${cargo.idString}">${cargo.remuneracion}</span></td>
						<td><span id="divisa${cargo.idString}">${cargo.divisa.divisa}</span>
						<td><span id="tipoRemuneracion${cargo.idString}">${cargo.tipoRemuneracionString}</span>
						<td><span id="proyecto${cargo.idString}">${cargo.proyecto.nombre}</span>
						<input type="hidden"
							id="idCargo${cargo.idString}"
							value="${cargo.candidato.id}" />
						</td>
				
						
					</#list>
				</tbody>
			</table>
		
		
		<!-- TABLA END -->
        </div>
        <!-- MODAL BODY END -->
                        
		  <div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>						
            </div>
            <!-- MODAL FOOTER END -->
				</div>
			</div>
		</div>
		<!-- VENTANA MODAL END -->
      
      
      
      
      
      <!--//////////////////////////////////////////////-->
		
		
		 <!-- VENTANA MODAL START -->
		<div class="modal fade" id="ventana2" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4>Record de Asistencias</h4> 
                    </div>
            <!-- MODAL HEADER END -->
                    
            <div class="modal-body"> 
            <!-- TABLA START -->
	        
                    	
		
			<table id="tabla" class="table table-striped table-bordered table-hover table-condensed">
				<thead>
					<tr>
						<th>Horas Trabajadas</th>
						<th>Mes</th>
						<th>A&ntilde;o</th>
						
						
						
					</tr>
				</thead>
				<tbody>
					<#list Session.user.candidato.asistenciasMensuales as asist>
					<tr>						
						<td><span id="horasTrabajadas${asist.idString}"> ${asist.horas_trabajadas}</span></td>
						<td><span id="mes${asist.idString}">${asist.mes.mes}</span></td>
						<td><span id="anio${asist.idString}">${asist.anio}</span>
						
						<input type="hidden"
							id="idAsist${asist.idString}"
							value="${asist.candidato.id}" />
						</td>
					   
											
					</tr>
					</#list>
				</tbody>
			</table>
		<!-- TABLA END -->
            </div>
        <!-- MODAL BODY END -->
                        
		  <div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal"><i class="glyphicon glyphicon-remove"></i>&nbsp;Cerrar</button>						
            </div>
            <!-- MODAL FOOTER END -->
				</div>
			</div>
		</div>
		<!-- VENTANA MODAL END -->
		
		
		<!--//////////////////////////////////////////////////////////////-->
		
      
         <!-- VENTANA MODAL START -->
		<div class="modal fade" id="ventana3" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4>Registro de Faltas</h4> 
                    </div>
            <!-- MODAL HEADER END -->
                    
        <div class="modal-body"> 
            <!-- TABLA START -->
		
			<table id="tabla" class="table table-striped table-bordered table-hover table-condensed">
				<thead>
					<tr>
						<th>Horas Faltadas</th>
						<th>Fecha</th>
						<th>Tipo de Falta</th>
						<th>Descripcion</th>
						
						
					</tr>
				</thead>
				<tbody>
					<#list Session.user.candidato.faltas as falta>
					<tr>						
						<td><span id="horasFaltadas${falta.idString}"> ${falta.horas_faltadas}</span></td>
						<td><span id="fecha${falta.idString}">${falta.fechaString}</span></td>
						<td><span id="tipoFalta${falta.idString}">${falta.tipoFaltaString}</span>
						<td><span id="descripcion${falta.idString}">${falta.descripcion}</span></td>
						
						<input type="hidden"
							id="idFalta${falta.idString}"
							value="${falta.candidato.id}" />
						
  
						
                     					
					</tr>
					</#list>
				</tbody>
			</table>
		<!-- TABLA END --> 
        </div>
        <!-- MODAL BODY END -->
                        
		  <div class="modal-footer">
						<button type="button" class="btn btn-danger" data-dismiss="modal"><i class="glyphicon glyphicon-remove"></i>&nbsp;Cerrar</button>						
            </div>
            <!-- MODAL FOOTER END -->
				</div>
			</div>
		</div>
		<!-- VENTANA MODAL END -->
        


  <h1 class="page-header">
                           Mi Perfil de Usuario

                        </h1>



<div id="content" class="col-md-12 bg-img header">
      <div class="row">
      <div class="col-md-5  toppad  pull-right col-md-offset-3 ">
       <br>
      </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >
          <div class="panel panel-info">
            <div class="panel-heading">
              <h3 class="panel-title"> ${Session.user.rol.rol} / ${Session.user.nombre_usuario}</h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-3 col-lg-3 " align="center"> <img alt="User Pic" src="assets/images/usuario-simbolo.jpg" class="img-circle img-responsive"> </div>
                
                
                

<!--tabla de contenido inicia-->

                <div class=" col-md-9 col-lg-9 ">
    
                  
                  <table class="table table-user-information">
                    <tbody>
                    <tr>
                        <td>Nombre Completo:</td>   
                        <td>${Session.user.candidato.nombres} ${Session.user.candidato.apellidos}</td>    
                        </tr>
                 
                      <tr>
                        <td>Tel&eacute;fono</td>
                        <td>${Session.user.candidato.telefono}</td>
                      </tr> 
                        <tr>
                        <td>Correo</td>
                        <td>${Session.user.candidato.correo}</td>
                      </tr>
                        <tr>
                        <td>Direcci&oacute;n</td>
                        <td>${Session.user.candidato.ciudad}  ${Session.user.candidato.pais.pais}</td>
                      </tr>
                      <tr>
                        <td>Sexo</td>
                        <td>${Session.user.candidato.sexoString}</td>
                      </tr>
              
                    </tbody>
                  </table>


                </div>
                <!--tabla de contenido finaliza-->
                
                <!--    //////////////////////////////////////////////////-->      
      
      
      	

              </div>
            </div>
                     

      
      
      </div>
            
              <!-- BARRA DE HERRAMIENTAS START -->
		<div class="navbar navbar-default">
			<div class="container">
				<table class="col-md-6">
					<tr>
						<td class="col-md-2">
							<button type="button"  class="navbar-btn btn-info btn" data-toggle="modal"
							data-target="#cargos" >
							<i
							class="glyphicon glyphicon-eye-open"></i>&nbsp;Cargos</button>
						</td>
						
						<td class="col-md-2">
							<a class="navbar-btn btn-info btn" data-toggle="modal"
							data-target="#ventana2" ><i
							class="glyphicon glyphicon-eye-open"></i>&nbsp;Record de Asistencia</a>
						</td>
						
						<td class="col-md-2">
							<a class="navbar-btn btn-info btn" data-toggle="modal"
							data-target="#ventana3" ><i
							class="glyphicon glyphicon-eye-open"></i>&nbsp;Faltas Registradas</a>
						</td>
						
					</tr>
				</table>
			</div>			
		</div>
		<!-- BARRA DE HERRAMIENTAS END -->
</div>     
      <!--//////////////////////////////////////////////////////-->
             
   
      
      
      
      
    </div>
 
    

</div>
	
 
    
</div>
    
    
   

    
    
    
</div>		
		
      <!-- Scripts section -->
        
        <script src="assets/js/desplegable.js"></script>
        
       

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