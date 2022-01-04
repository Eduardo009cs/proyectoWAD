<%-- 
    Eduardo Cuevas Solorza
    ProyectoBase3CM18_V3
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" ></script>
    </head>
    <body>
        <div class="card  position-absolute top-50 start-50 translate-middle " style="width: 25rem; height: 28rem; border-color: #EB5600">
            <div class="card-header " style="background-color: #FF7F35;">
                <h1 class="text-center text-white">Iniciar Sesión</h1>
            </div>
            <c:if test="${mensaje != null}">
                <div class="alert alert-warning alert-dismissible fade show" role="alert">
                    <strong>${mensaje}</strong>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>
            <div class="card-body" >
                <form method="post" action="./UsuarioController?accion=logueado">
                    <label class="col-form-label">Nombre de usuario</label>
                    <input type="text" name="txtNombreUsuario"
                           id="txtNombreUsuario"
                           maxlength="50"
                           placeholder="Usuario"
                           class="form-control"
                           required="required"
                           />
                    <br/>
                    <br/>
                    <label class="col-form-label">Contraseña</label>
                    <input type="password" name="txtClaveUsuario"
                           id="txtClaveUsuario"
                           maxlength="50"
                           placeholder="Contraseña"
                           class="form-control"
                           required="required"
                           />
                    <br/>
                    <br/>
                    <button type="submit" class="btn btn-outline-success form-control">Entrar</button> 
                </form>
                <a href="UsuarioController?accion=nuevo" class="btn btn-outline-secondary form-control">Crear Cuenta</a>
            </div>
        </div>
    </body>
</html>
