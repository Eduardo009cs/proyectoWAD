<%-- 
    Eduardo Cuevas Solorza
    ProyectoBase3CM18_V3
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" ></script>
    </head>
    <body>
        <div class="container">
            <div class="card position-absolute top-50 start-50 translate-middle" style="width: 25rem; border-color: #EB5600">
                <div class="card-header" style="background-color: #FF7F35;">
                    <h1 class="text-center text-white">Datos del Usuario</h1>
                </div>
                <div class="card-body">
                    <ol class="list-group list-group-numbered" >
                        <li class="list-group-item d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold">Clave Usuario</div>
                                ${usuario.entidad.idUsuario}
                            </div>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold">Nombre Completo</div>
                                ${usuario.entidad.nombre}
                                ${usuario.entidad.paterno}
                                ${usuario.entidad.materno}
                            </div>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold">Correo</div>
                                ${usuario.entidad.email}
                            </div>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold">Nombre de Usuario</div>
                                ${usuario.entidad.nombreUsuario}
                            </div>
                        </li>
                    </ol>
                    <br/>
                    <a href="UsuarioController?accion=listaDeUsuarios" class="btn btn-outline-secondary form-control">
                        Regresar al listado
                    </a>
                </div>
            </div>
        </div>

    </body>
</html>
