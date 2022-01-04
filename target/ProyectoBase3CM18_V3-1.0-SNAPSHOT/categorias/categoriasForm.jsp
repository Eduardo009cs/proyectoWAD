<%-- 
    Eduardo Cuevas Solorza
    ProyectoBase3CM18_V3
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <form method="post" action="CategoriaController?accion=${accion}">
                        <div class="form-floating mb-3">

                            <input type="text" name="txtNombreCategoria"
                                   id="txtNombreCategoria"
                                   maxlength="50"
                                   placeholder="Nombre de la categoria"
                                   class="form-control"
                                   required="required"
                                   value="<c:out value="${categoria.entidad.nombreCategoria}"/>"
                                   />
                            <label for="txtNombreCategoria">Nombre de la categoría</label>
                        </div>
                        <div class="form-floating mb-3">

                            <input type="text" name="txtDescripcionCategoria"
                                   id="txtDescripcionCategoria"
                                   maxlength="50"
                                   placeholder="Descripcion de la categoria"
                                   class="form-control"
                                   required="required"
                                   value="<c:out value="${categoria.entidad.descripcionCategoria}"/>"
                                   />
                            <label for="txtDescripcionCategoria">Descripción de la categoría</label>

                        </div>
                        <button type="submit" class="btn btn-outline-success form-control">Guardar Categoria</button> 
                    </form>
                    <a href="CategoriaController?accion=listaDeCategorias" class="btn btn-outline-secondary form-control">Regresar al listado</a>
                </div>
            </div>
        </div>
    </body>
</html>
