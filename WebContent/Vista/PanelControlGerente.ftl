<!DOCTYPE html>
<html lang="es">
<head>
	     <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Dashboard</title>
        <!-- logo barra -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/images/LOGO-KG.png"/>

        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/css/bootstrap.css" rel="stylesheet">
        <link href="assets/css/bootstrap-theme.min.css" rel="stylesheet">

        <link href="assets/css/owl.carousel.css" rel="stylesheet">
        <link href="assets/css/owl.theme.default.min.css" rel="stylesheet">

        <link href="assets/css/magnific-popup.css" rel="stylesheet">


		    <!-- Morris Charts CSS -->
		    <link href="assets/css/morris.css" rel="stylesheet">

				<link href="assets/css/style.css" rel="stylesheet">
        <link href="assets/font_awesome/css/font-awesome.css" rel="stylesheet">
        
        <script type="text/javascript" src="assets/html5gallery/html5gallery.js"></script>

        
        
        

        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

</head>
 <body class="color-fondo">
    <div class="main"><!--no necesario-->
      <#include "usuarios/header-admin.ftl">
   
     	<#assign x = Session.user.rol.id >
        <#if x == 1>
        <#include "usuarios/navbar-admin.ftl">
        <#elseif x == 2>
        <#include "usuarios/navbar-manager.ftl">
        </#if>
         

<!--titulo principal pagina-->
    <div class="row">
        <div class="col-lg-12">
        <h1 class="page-header">
                DASHBOARD
        </h1>	
        </div>
    </div>


    <!--cuerpo -->
<div class="contenedor-principal">
        <h2>Bienvenido ${Session.user.nombre_usuario}  ( ${Session.user.rol.rol} )</h2>
   <div class="row">
			<div class="col-lg-12">
      <div class="panel panel-default">
		<div class="panel-heading">
		  <h3 class="panel-title"><i class="fa fa-bar-chart-o fa-fw"></i> <span class="glyphicon glyphicon-film"></span>Mira lo m&aacute;s actual en KAIZENWARE</h3>
		</div>
			<div class="panel-body">
             <section id="video" class="youtube">
              <iframe width="560" height="315" src="https://www.youtube.com/embed/videoseries?list=PL3PwiT1a13HNytH4yXxIAxb0p7DkDgGBS" frameborder="0" allowfullscreen></iframe>
        <div class="panel-footer">
           <script src="https://apis.google.com/js/platform.js"></script>

           <div class="g-ytsubscribe" data-channelid="UCBptU9FJNDaQH-l-yeIlPNA" data-layout="full" data-count="default"></div>
        </div>
            </section>
            </div>
	  </div>
	</div>
	</div>
      
      
  

     <section id="grafica" class="grafica">
	       <div class="row">
			<div class="col-lg-12">
					<div class="panel panel-default">
							<div class="panel-heading">
									<h3 class="panel-title"><i class="fa fa-bar-chart-o fa-fw"></i> Area Chart</h3>
							</div>
							<div class="panel-body">
									<div id="morris-area-chart" class="morris-area-chart"></div>
							</div>
					</div>
			</div>
	</div>
   </section>

 
</div><!--cuerpo -->







        <!-- Scripts section -->
       <script src="https://apis.google.com/js/platform.js"></script>
        <script src="assets/js/jquery-3.1.1.js"></script>
        <script src="assets/js/desplegable.js"></script>
        <script src="assets/js/jquery.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/js/jquery.magnific-popup.js"></script>
        <script src="assets/js/script.js"></script>
        <script src="assets/js/auth.js"></script>
				  <!-- Scripts del prof -->
	<script src="resources/js/bootstrap.js"></script>
	<script src="resources/js/jquery.dataTables.min.js"></script>
	<script src="resources/js/dataTables.bootstrap.min.js"></script>
	<script src="resources/js/bootstrap-confirmation.js"></script>	
	<script type="text/javascript" class="init">		
		$('[data-toggle="confirmation"]').confirmation('hide');
	</script>
	<!-- JAVASCRIPT END -->
				<!-- Morris Charts JavaScript -->
		    <script src="assets/js/raphael.min.js"></script>
		    <script src="assets/js/morris.min.js"></script>
		    <script src="assets/js/morris-data.js"></script>

    </body>
</html>
