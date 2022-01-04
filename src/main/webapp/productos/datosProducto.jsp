<%-- 
    Eduardo Cuevas Solorza
    ProyectoBase3CM18_V3
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" ></script>
    </head>
    <body>
        <div class="container">
            <div class="card position-absolute top-50 start-50 translate-middle" style="width: 30rem;border-color:#013F99; ">
                <div class="card-header text-center" style="background-color:#3C7EDC; ">
                    <h1 class="text-center text-white">Datos del Producto</h1>
                </div>
                <div class="card-body">
                    <ol class="list-group list-group-numbered">
                        <li class="list-group-item d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold">Clave Producto</div>
                                <c:out value="${producto.entidad.idProducto}"/>
                            </div>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold">Nombre Producto</div>
                                <c:out value="${producto.entidad.nombreProducto}"/>
                            </div>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold">Descripcion Producto</div>
                                <c:out value="${producto.entidad.descripcionProducto}"/>
                            </div>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold">Precio del Producto</div>
                                <c:out value="${producto.entidad.precioProducto}"/>
                            </div>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold">Existencias del Producto</div>
                                <c:out value="${producto.entidad.existenciasProducto}"/>
                            </div>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="fw-bold">Categoría del Producto</div>
                                <c:out value="${producto.entidad.idCategoria}"/>
                            </div>
                        </li>
                    </ol>
                    <br>
                    <a href="ProductoController?accion=listaDeProductos" class="btn btn-outline-secondary form-control">Regresar al listado</a>

                </div>
            </div>
        </div>

    </body>
</html>
