<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Mi Perfil | KAIZENWARE</title>
        <!-- logo barra -->
        <link rel="shortcut icon" type="image/x-icon" href="../assets/images/LOGO-KG.png"/>


       <!--imagenes+efecto despliegue menu+tabla de datos-->
       <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
        <!--<link href="../assets/css/bootstrap-theme.min.css" rel="stylesheet">-->

        <!--imagen de fondo-->
       <link href="../assets/css/style-perfil.css" rel="stylesheet">

        <!--cabecera+menu+letras y más-->
        <link href="../assets/css/style.css" rel="stylesheet">
      <!--  <link href="../assets/css/estilos-jose.css" rel="stylesheet">-->



        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

<body>

<!--cabecera-->
       <body class="bg-img body-img ">
        <div class="main">
			<header>
               <#include "/usuarios/header-admin.ftl">
			</header>
			
          x=${Session.user.idRolString};
        <#if x == 1>
        <#include "usuarios/navbar-admin.ftl">
        <#elseif x == 2>
        <#include "usuarios/navbar-manager.ftl">
        <#elseif x == 3>
        <#include "usuarios/navbar-employee.ftl">
        </#if>
	<!--fin cabecera -->




<div class="container">
      <div class="row">
      <div class="col-md-5  toppad  pull-right col-md-offset-3 ">



       <br>

      </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >


          <div class="panel panel-info">
            <div class="panel-heading">
              <h3 class="panel-title">${Session.user.idRolString} ${Session.user.rol.rol} ${Session.user.nombre_usuario}</h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-3 col-lg-3 " align="center"> <img alt="User Pic" src="../assets/images/img2.jpg" class="img-circle img-responsive"> </div>


                <div class=" col-md-9 col-lg-9 ">
                  
                  
                  <table class="table table-user-information">
                    <tbody>
                      <tr>
                        <td>Cargo Actual:</td>       
                        <tr>
                              <td>Proyecto 2</td>
                              <td>Cargo 2</td>
                        </tr>

                      <tr>
                        <td>fecha de nacimiento</td>
                        <td>01/24/1988</td>
                      </tr>

                         <tr>
                             <tr>
                        <td>Sexo</td>
                        <td>Femenino</td>
                      </tr>
                        <tr>
                        <td>Direccion</td>
                        <td>Prados del Sol, Cabudare, edo Lara, Venezuela</td>
                      </tr>
                      <tr>
                        <td>Correo</td>
                        <td>mariagonzalez@gmail.com</td>
                      </tr>
                        <td>Numero de telefono</td>
                        <td>0414-5098957(Movil)<br><br>02510997653(Fijo)
                        </td>



                    </tbody>
                  </table>


                </div>
              </div>
            </div>


      <!-- Scripts section -->
        <script src="../assets/js/jquery-3.1.1.js"></script>
        <script src="../assets/js/desplegable.js"></script>
        <script src="../assets/js/jquery.js"></script>
        <script src="../assets/js/bootstrap.min.js"></script>

        <script src="../assets/js/script.js"></script>
    </body>
</html>