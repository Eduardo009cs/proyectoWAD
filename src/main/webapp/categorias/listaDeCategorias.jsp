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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <br>
            <br>
            <div class="position-absolute top-0 end-0">
                <a href="UsuarioController?accion=cerrar" class="btn btn-light">Cerrar sesión</a>
            </div>
            <div class="card " style="border-color: #9F0000;">
                <div class="card-header text-center" style="background-color: #FF5151;">
                    <h1 class="text-center text-white">Lista de Categorías</h1>
                </div>
                <div class="card-body">
                    <h4 class="card-title">
                        <a href="CategoriaController?accion=nuevo" class="btn btn-outline-success">Crear Categoría</a>
                        <a href="CategoriaController?accion=graficar" class="btn btn-outline-warning" target="_blank" style="margin-left: 20em;">Mostrar Grafica</a>
                        <a href="CategoriaController?accion=verReporte" class="btn btn-outline-info" target="_blank" style="margin-left: 20em;">Mostrar Reporte</a>
                    </h4>

                    <c:if test="${mensaje != null}">
                        <div class="alert alert-warning alert-dismissible fade show" role="alert">
                            <strong>${mensaje}</strong>
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>

                    <table class="table table-stripped">
                        <thead>
                            <tr>
                                <th>Clave Categoría</th>
                                <th>Nombre Categoría</th>

                                <th>Eliminar</th>
                                <th>Actualizar</th>
                                <th>Reporte</th>

                            </tr>
                        </thead>
                        <c:forEach var="dto" items="${listaDeCategorias}">
                            <tbody>
                                <tr>
                                    <td>
                                        <a href="CategoriaController?accion=ver&id=<c:out value="${dto.entidad.idCategoria}"/>" class="btn btn-outline-dark">
                                            <c:out value="${dto.entidad.idCategoria}"/>
                                        </a>
                                    </td>
                                    <td>
                                        <c:out value="${dto.entidad.nombreCategoria}"/>
                                    </td>

                                    <td>
                                        <a href="CategoriaController?accion=eliminar&id=<c:out value="${dto.entidad.idCategoria}"/>" class="btn btn-outline-danger">Eliminar</a>
                                    </td>
                                    <td>
                                        <a href="CategoriaController?accion=actualizar&id=<c:out value="${dto.entidad.idCategoria}"/>" class="btn btn-outline-primary">Actualizar</a>
                                    </td>
                                    <td>
                                        <a href="CategoriaController?accion=verReporteIndividual&id=<c:out value="${dto.entidad.idCategoria}"/>" class="btn btn-outline-info" target="_blank">Reporte</a>
                                    </td>

                                </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                    <br>
                    <br>
                    <a href="index.jsp" class="btn btn-outline-secondary position-absolute bottom-0 start-50 translate-middle-x">Regresar al menú</a>
                </div>
            </div>
        </div>
        <br>
        <br>
    </body>
</html>