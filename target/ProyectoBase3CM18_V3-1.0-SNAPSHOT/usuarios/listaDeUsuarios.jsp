<%-- 
    Eduardo Cuevas Solorza
    ProyectoBase3CM18_V3
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
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
            <div class="card " style="border-color: #EB5600">
                <div class="card-header text-center" style="background-color: #FF7F35;">
                    <h1 class="text-center text-white">Lista de Usuarios</h1>
                </div>
                <div class="card-body">
                    <h4 class="card-title">
                        <a href="UsuarioController?accion=nuevo" class="btn btn-outline-success">Crear Usuario</a>
                        <a href="UsuarioController?accion=verReporte" class="btn btn-outline-info" target="_blank" style="margin-left: 40em;">Mostrar Reporte</a>
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
                                <th>Clave Usuario</th>
                                <th>Nombre de Usuario</th>
                                <th>Eliminar</th>
                                <th>Actualizar</th>
                            </tr>
                        </thead>
                        <c:forEach var="dto" items="${listaDeUsuarios}">
                            <tbody>
                                <tr>
                                    <td>
                                        <a href="UsuarioController?accion=ver&id=<c:out value="${dto.entidad.idUsuario}"/>" class="btn btn-outline-dark">
                                            <c:out value="${dto.entidad.idUsuario}"/>
                                        </a>
                                    </td>
                                    <td>
                                        <c:out value="${dto.entidad.nombreUsuario}"/>
                                    </td>
                                    <td>
                                        <a href="UsuarioController?accion=eliminar&id=<c:out value="${dto.entidad.idUsuario}"/>" class="btn btn-outline-danger">Eliminar</a>
                                    </td>
                                    <td>
                                        <a href="UsuarioController?accion=actualizar&id=<c:out value="${dto.entidad.idUsuario}"/>" class="btn btn-outline-primary">Actualizar</a>
                                    </td>
                                </tr>
                            </tbody>
                        </c:forEach>

                    </table>
                    <br>
                    <a href="index.jsp" class="btn btn-outline-secondary position-absolute bottom-0 start-50 translate-middle-x">Regresar al menú</a>
                </div>
            </div>
        </div>
    </body>
</html>
