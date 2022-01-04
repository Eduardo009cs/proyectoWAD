<%-- 
    Eduardo Cuevas Solorza
    ProyectoBase3CM18_V3
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <h1 class="text-center text-white">Agregar Productos</h1>
                </div>
                <div class="card-body">
                    <form method="post" action="ProductoController?accion=${accion}">
                        <div class="form-floating mb-3">
                            <input type="text" name="txtNombreProducto"
                                   id="txtNombreProducto"
                                   maxlength="50"
                                   placeholder="Nombre"
                                   class="form-control"
                                   required="required"
                                   value="<c:out value="${producto.entidad.nombreProducto}"/>"
                                   />
                            <label for="txtNombreProducto">Nombre del producto</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" name="txtDescripcionProducto"
                                   id="txtDescripcionProducto"
                                   maxlength="50"
                                   placeholder="Descripcion"
                                   class="form-control"
                                   required="required"
                                   value="<c:out value="${producto.entidad.descripcionProducto}"/>"
                                   />
                            <label for="txtDescripcionProducto">Descripción del Producto</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="number" name="txtPrecioProducto"
                                   id="txtPrecioProducto"
                                   placeholder="$ 00.00"
                                   class="form-control"
                                   required="required"
                                   value="<c:out value="${producto.entidad.precioProducto}"/>"
                                   />
                            <label for="txtPrecioProducto">Precio del producto</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="number" name="txtExistenciasProducto"
                                   id="txtExistenciasProducto"
                                   placeholder="0"
                                   class="form-control"
                                   required="required"
                                   value="<c:out value="${producto.entidad.existenciasProducto}"/>"
                                   />
                            <label for="txtExistenciasProducto">Existencias del Producto</label>
                        </div>
                        <div class="form-floating mb-3">

                            <input type="number" name="txtClaveCategoria"
                                   id="txtClaveCategoria"
                                   placeholder="Clave de la categoria"
                                   class="form-control"
                                   required="required"
                                   value="<c:out value="${producto.entidad.idCategoria}"/>"
                                   />
                            <label for="txtClaveCategoria">Clave de la categoría</label>
                        </div>
                        <button type="submit" class="btn btn-outline-success form-control">${titulo}</button> 
                    </form>
                    <a href="ProductoController?accion=listaDeProductos" class="btn btn-outline-secondary form-control">Regresar al listado</a>
                </div>
            </div>
        </div>
    </body>
</html>
