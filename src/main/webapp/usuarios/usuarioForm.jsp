<%-- 
    Eduardo Cuevas Solorza
    ProyectoBase3CM18_V3
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" ></script>
    </head>
    <body>
        <div class="container">
            <c:if test="${mensaje != null}">
                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    <strong>${mensaje}</strong>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
            <div class="card position-absolute top-0 start-50 translate-middle-x" style="width: 25rem; border-color: #EB5600">
                <div class="card-header" style="background-color: #FF7F35;">
                    <h1 class="text-center text-white">${titulo}</h1>
                </div>
                <div class="card-body">
                    <form method="post" action="UsuarioController?accion=${accion}">
                        <div class="form-floating mb-3">

                            <input type="text" name="txtNombre"
                                   id="txtNombre"
                                   maxlength="50"
                                   placeholder="Nombre"
                                   class="form-control"
                                   required="required"
                                   value="<c:out value="${usuario.entidad.nombre}"/>"
                                   />
                            <label for="txtNombre">Nombre</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" name="txtPaterno"
                                   id="txtPaterno"
                                   maxlength="50"
                                   placeholder="Apellido Paterno"
                                   class="form-control"
                                   required="required"
                                   value="<c:out value="${usuario.entidad.paterno}"/>"
                                   />
                            <label for="txtPaterno">Apellido Paterno</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" name="txtMaterno"
                                   id="txtMaterno"
                                   maxlength="50"
                                   placeholder="Materno"
                                   class="form-control"
                                   required="required"
                                   value="<c:out value="${usuario.entidad.materno}"/>"
                                   />
                            <label for="txtMaterno">Apellido Materno</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="email" name="txtEmail"
                                   id="txtEmail"
                                   placeholder="Correo"
                                   class="form-control"
                                   required="required"
                                   value="<c:out value="${usuario.entidad.email}"/>"
                                   />
                            <label for="txtCorreo">Correo</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" name="txtNombreUsuario"
                                   id="txtNombreUsuario"
                                   maxlength="50"
                                   placeholder="Usuario"
                                   class="form-control"
                                   required="required"
                                   value="<c:out value="${usuario.entidad.nombreUsuario}"/>"
                                   />
                            <label for="txtNombreUsuario">Nombre de Usuario</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="password" name="txtClaveUsuario"
                                   id="txtClaveUsuario"
                                   maxlength="50"
                                   placeholder="Contraseña"
                                   class="form-control"
                                   required="required"
                                   value="<c:out value="${usuario.entidad.claveUsuario}"/>"
                                   />
                            <label for="txtClaveUsuario">Contraseña</label>
                        </div>
                        <div class="form-floating mb-3">

                            <input type="password" name="txtConfirmarClave"
                                   id="txtConfirmarClave"
                                   maxlength="50"
                                   placeholder="Confirmar contraseña"
                                   class="form-control"
                                   required="required"
                                   />
                            <label for="txtConfirmarClave">Confirmar contraseña</label>

                        </div>
                        <button type="submit" class="btn btn-outline-success form-control">${titulo}</button> 
                    </form>
                    <c:if test="${sesion == true}">
                        <a href="UsuarioController?accion=listaDeUsuarios" class="btn btn-outline-secondary form-control">
                            Regresar al listado
                        </a>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
