    <!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>KaizenWare | Home</title>
        <!-- logo barra -->
        <link rel="shortcut icon" type="image/x-icon" href="../assets/images/logo1.png"/>


       <!--estilo de formularios-->
       <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
        <!--estilo de botones, al quitar se ven planos-->
        <link href="../assets/css/bootstrap-theme.min.css" rel="stylesheet">
      <!--cabecera + footer + menu-->
      <link href="../assets/css/style.css" rel="stylesheet">
      <!--separacion entre boton guardar y cancelar-->
       <link href="../assets/css/estilos-jose.css" rel="stylesheet">



        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="color-fondo">
        <div class="main">
			<header>
                <nav class="">
                        <div>
                        <div class="container">
                            <div class="row">
                                <div class="col-xs-6">
                                     <div class="logo small-img">
                                        <a href="../index.html"><img src="../assets/images/LOGO-KG.png"></a>
                                    </div>
                                </div>
                                <div class="col-xs-6 text-right">
                                    <div class="menu m">
                                        <a href="#"><span ></span></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </nav>
			</header>
        <nav class="menu nav-scroll" id="theMenu" role="navigation">
		<div class="menu-wrap">
        <div>
		<h1 class="logo"><a class="center-block" href="../index.html#home">KAIZENWARE</a></h1>
		<i class="fa fa-arrow-rigth menu-close"></i>
                <div class="small-img">
                    <a href="#">
                        <img class="img-circle center-block" src="../assets/images/img2.jpg" alt="Foto de Perfil">
                    </a>

                </div>
                <table>
                    <tr>
                        <td>
                            <a href="miperfil.html"><sub>Perfil</sub></a>
                        </td>
                        <td>
                            <a href="../index.html"><sub>Cerrar Sesi&oacute;n</sub></a>
                        </td>
                    </tr>
                </table>
            </div>
                <hr/>
                <ul>
                    <a href="../index.html"><li>Home</li></a>
                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#gestiones">Gestionar</a>
                        <ul id="gestiones" class="collapse">
                            <li class="second-lvl first">
                                <a href="VCliente.html">Clientes</a>
                            </li>
                            <li class="second-lvl">
                                <a href="VCandidato.html">Candidatos</a>
                            </li>
                            <li class="second-lvl">
                                <a href="VProyecto.html">Proyectos</a>
                            </li>
                            <li class="second-lvl">
                                <a href="VCargos.html">Cargos</a>
                            </li>
                            <li class="second-lvl">
                                <a href="VAsignarEC.html">Asignar Cargos</a>
                            </li>
                            <li class="second-lvl last">
                                <a href="VDiaFeriado.html">D&iacute;as Feriados</a>
                            </li>
                            <li class="second-lvl first">
                                <a href="VUsuario.html">Usuarios</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#administrar">Administrar</a>
                        <ul id="administrar" class="collapse">
                            <li class="second-lvl first">
                                <a href="VEntrevista.html">Entrevistas</a>
                            </li>
                            <li class="second-lvl">
                                <a href="VAsistencia.html">Asistencias</a>
                            </li>
                            <li class="second-lvl">
                                <a href="falta.html">Faltas</a>
                            </li>
                            <li class="second-lvl">
                                <a href="cobro.html">Cobros</a>
                            </li>
                            <li class="second-lvl last">
                                <a href="pago.html">Pagos</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#reportes">Reportes</a>
                        <ul id="reportes" class="collapse">
                            <li class="second-lvl first">
                                <a href="VConsultarCliente.html">Clientes</a>
                            </li>
                            <li class="second-lvl">
                                <a href="VConsultarCandidato.html">Candidatos</a>
                            </li>
                            <li class="second-lvl">
                                <a href="VConsultarEntrevistas.html">Entrevistas</a>
                            </li>
                            <li class="second-lvl">
                                <a href="VConsultarHorasT.html">Horas Trabajadas</a>
                            </li>
                            <li class="second-lvl">
                                <a href="consultar-pagos2.html">Record de Pagos</a>
                            </li>
                            <li class="second-lvl">
                                <a href="consultar-utilidad.html">Utilidad</a>
                            </li>
                            <li class="second-lvl last">
                                <a href="consultar-faltas.html">Faltas</a>
                            </li>
                        </ul>
                    </li>


                </ul>
            </div>
        <!-- Menu button -->
        <div id="menuToggle" class="ion-navicon _ion-android-menu"><i class="fa fa-bars"></i>
        </div>
	</nav>

			<div id="page-wrapper" class="container-fluid">

            <div class="contenedor-principal">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Gesti&oacute;n de Usuarios

                        </h1>

                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>Administrar
                            </li>
                            <li class="active">
                                <i class="fa fa-file"></i>Cobros
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

                <div class="row">
                    <div class="col-lg-12">

                          


                        <div class="table-responsive">
                            <table class="table table-striped text-center">
                                <thead class="bg-primary">
                                    <tr>
                                        <th>Código Cliente</th>
                                        <th>Razón Social</th>
                                        <th>Proyecto</th>
                                        <th>Mes a cancelar</th>
                                        <th>Horas Trabajadas</th>
                                        <th>Monto por hora</th>
                                    </tr>
                                </thead>
                                <tbody id="tb-empleados">
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <button type="submit" class="btn btn-primary center-block">Procesar Cobro</button>

                        <hr/>


                        <form role="form">
                            <div class="col-lg-6">
                                <div class="form-group">
                                    <label>Número de Recibo</label>
                                    <input class="form-control" disabled>
                                </div>

                                <div class="form-group">
                                    <label>Código del Cliente</label>
                                    <input class="form-control" disabled>
                                </div>

                                <div class="form-group">
                                    <label>Razón Social</label>
                                    <input class="form-control" disabled>
                                </div>
                            </div>

                            <div class="col-lg-6">
                                <div class="form-group center-block">
                                    <label>Fecha de Emisión</label>
                                    <input type="date" class="form-control" disabled>
                                </div>

                                <div class="form-group">
                                    <label>Proyecto</label>
                                    <input class="form-control" disabled>
                                </div>

                                <div class="form-group">
                                    <label>Monto a Cobrar</label>
                                    <input class="form-control" disabled>
                                    <p class="help-block">Horas trabajadas * Monto por hora</p>
                                </div>
                            </div>

                            <div class="col-lg-12">
                                <div class="form-group">
                                    <label>Descripción</label>
                                    <textarea class="form-control" rows="3"></textarea>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-lg-6">
                                    <button type="submit" class="btn btn-primary center-block mb-20">Guardar</button>
                                </div>
                                <div class="col-lg-6">
                                    <button type="reset" class="btn btn-primary center-block mb-20">Cancelar</button>
                                </div>
                            </div>

                        </form>

                    </div>
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

        <footer class="footer">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-12">
                            <p>Design <font class="logo-icon"></font> by <a href="https://www.freshdesignweb.com/">group of four</a></p>
                        </div>
                    </div>
                </div>
        </footer>
        </div>

        <!-- Scripts section -->
        <script src="../assets/js/jquery-3.1.1.js"></script>
        <script src="../assets/js/desplegable.js"></script>
        <script src="../assets/js/jquery.js"></script>
        <script src="../assets/js/bootstrap.min.js"></script>
        <script src="../assets/js/owl.carousel.min.js"></script>
        <script src="../assets/js/jquery.magnific-popup.js"></script>
        <script src="../assets/js/script.js"></script>
    </body>
</html>
