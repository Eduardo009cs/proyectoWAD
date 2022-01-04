<%-- 
    Eduardo Cuevas Solorza
    ProyectoBase3CM18_V3
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Principal</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" >
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" ></script>
    </head>
    <body class="bg-dark bg-gradient bg-opacity-75">
        <div class="container " >
            <c:choose>
                <c:when test="${sesion == true}">
                    <div class="position-absolute top-0 end-0">
                        <a href="UsuarioController?accion=cerrar" class="btn btn-light">Cerrar sesión</a>
                    </div>

                    <div class='card text-center position-absolute top-50 end-0 translate-middle-y border-dark mb-3' style='width: 18rem; height: 20rem; right: 15%!important;'>
                        <img src="images/usuarios.png" class="card-img-top" alt="usuarios" style="height:14rem;">
                        <div class="card-body bg-black p-2 text-white bg-opacity-75 ">
                            <h5 class="card-title text-center ">Usuarios</h5>

                            <a href="UsuarioController?accion=listaDeUsuarios" class="btn btn-outline-light">Entrar</a>
                        </div>
                    </div>

                    <div class='card text-center position-absolute top-50 start-0 translate-middle-y border-dark mb-3 ' style='width: 18rem; height: 20rem; left: 15%!important;'>
                        <img src="images/categorias.png" class="card-img-top" alt="categorias" style="height:14rem;">
                        <div class="card-body bg-black p-2 text-white bg-opacity-75 " >
                            <h5 class="card-title text-center ">Categorias</h5>

                            <a href="CategoriaController?accion=listaDeCategorias" class="btn btn-outline-light">Entrar</a>
                        </div>
                    </div>
                    <div class='card text-center position-absolute top-50 start-50 translate-middle border-dark mb-3' style='width: 18rem; height: 20rem;' >
                        <img src="images/productos.png" class="card-img-top" alt="productos" style="height:14rem;">
                        <div class="card-body bg-black p-2 text-white bg-opacity-75 ">
                            <h5 class="card-title text-center ">Productos</h5>

                            <a href="ProductoController?accion=listaDeProductos" class="btn btn-outline-light">Entrar</a>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="card text-center position-absolute top-50 start-50 translate-middle border-dark mb-3" style="width: 18rem; height: 20rem;">
                        <img src="images/iniciarSesion.png" class="card-img-top" alt="iniciar sesion" style="height:14rem;">
                        <div class="card-body bg-black p-2 text-white bg-opacity-75 ">
                            <h5 class="card-title text-center ">Iniciar Sesión</h5>

                            <a href="UsuarioController?accion=login" class="btn btn-outline-light ">Entrar</a>
                        </div>
                    </div>
                </c:otherwise>

            </c:choose>
    </body>
</html>
