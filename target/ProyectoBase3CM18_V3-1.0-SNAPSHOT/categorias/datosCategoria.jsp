<%-- 
    Eduardo Cuevas Solorza
    ProyectoBase3CM18_V3
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categorías</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" ></script>
    </head>
    <body>
        <div class="container">
            <div class="card position-absolute top-50 start-50 translate-middle" style="width: 30rem; border-color: #9F0000">
                <div class="card-header " style="background-color: #FF5151;">
                    <h1 class="text-white text-center">Datos de la Categoría</h1>
                </div>
                <div class="card-body">
                    <ol class="list-group list-group-numbered">
                        <li class="list-group-item d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold">Clave Categoría</div>
                                <c:out value="${categoria.entidad.idCategoria}"/> 
                            </div>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold">Nombre de la Categoría</div>
                                <c:out value="${categoria.entidad.nombreCategoria}"/> 
                            </div>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold">Descripción de la Categoría</div>
                                <c:out value="${categoria.entidad.descripcionCategoria}"/> 
                            </div>
                        </li>
                    </ol>
                    <br>
                    <a href="CategoriaController?accion=listaDeCategorias" class="btn btn-outline-secondary form-control">
                        Regresar al listado
                    </a>
                </div>
            </div>
        </div>

    </body>
</html>
