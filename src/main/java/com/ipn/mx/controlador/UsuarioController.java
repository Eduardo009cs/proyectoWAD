package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.dto.UsuarioDTO;
import com.ipn.mx.utilerias.EnviarMail;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

@WebServlet(name = "UsuarioController", urlPatterns = {"/UsuarioController"})
public class UsuarioController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion.equals("listaDeUsuarios")) {
            listadoDeUsuarios(request, response);
        } else {
            if (accion.equals("nuevo")) {
                agregarUsuario(request, response);
            } else {
                if (accion.equals("eliminar")) {
                    eliminarUsuario(request, response);
                } else {
                    if (accion.equals("actualizar")) {
                        actualizarUsuario(request, response);
                    } else {
                        if (accion.equals("guardar")) {
                            almacenarUsuario(request, response);
                        } else {
                            if (accion.equals("ver")) {
                                mostrarUsuario(request, response);
                            } else {
                                if (accion.equals("actualizado")) {
                                    usuarioActualizado(request, response);
                                } else {
                                    if (accion.equals("login")) {
                                        loginUsuario(request, response);
                                    } else {
                                        if (accion.equals("logueado")) {
                                            usuarioLogueado(request, response);
                                        } else {
                                            if (accion.equals("cerrar")) {
                                                cerrarSesion(request, response);
                                            }else{
                                                if(accion.equals("verReporte")){
                                                    mostrarReporte(request,response);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listadoDeUsuarios(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        try {
            request.setAttribute("listaDeUsuarios", dao.readAll());
            RequestDispatcher rd = request.getRequestDispatcher("usuarios/listaDeUsuarios.jsp");
            rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("accion", "guardar");
        RequestDispatcher rd = request.getRequestDispatcher("usuarios/usuarioForm.jsp");
        request.setAttribute("titulo", "Agregar Usuario");

        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDTO dto = new UsuarioDTO();
        UsuarioDAO dao = new UsuarioDAO();

        EnviarMail email = new EnviarMail();
        HttpSession sesion = request.getSession(false);

        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));
        try {
            dao.delete(dto);
            email.enviarCorreo((String) sesion.getAttribute("correo"), "Registro eliminado", "Ha eliminado un registro de usuario");
            listadoDeUsuarios(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDTO dto = new UsuarioDTO();
        UsuarioDAO dao = new UsuarioDAO();

        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher vista = request.getRequestDispatcher("usuarios/usuarioForm.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("usuario", dto);

            request.setAttribute("titulo", "Actualizar Usuario");
            request.setAttribute("accion", "actualizado&id=" + dto.getEntidad().getIdUsuario());
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDTO dto = new UsuarioDTO();
        UsuarioDAO dao = new UsuarioDAO();
        EnviarMail email = new EnviarMail();
        request.setAttribute("accion", "guardar");
        request.setAttribute("titulo", "Agregar Usuario");
        RequestDispatcher vista = request.getRequestDispatcher("usuarios/loginUsuario.jsp");
        RequestDispatcher vista2 = request.getRequestDispatcher("usuarios/usuarioForm.jsp");
        if (!request.getParameter("txtClaveUsuario").equals(request.getParameter("txtConfirmarClave"))) {
            request.setAttribute("mensaje", "Verifique las contraseñas");

            try {
                vista2.forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        dto.getEntidad().setNombre(request.getParameter("txtNombre"));
        dto.getEntidad().setPaterno(request.getParameter("txtPaterno"));
        dto.getEntidad().setMaterno(request.getParameter("txtMaterno"));
        dto.getEntidad().setEmail(request.getParameter("txtEmail"));
        dto.getEntidad().setNombreUsuario(request.getParameter("txtNombreUsuario"));
        dto.getEntidad().setClaveUsuario(request.getParameter("txtClaveUsuario"));

        try {
            List lista = dao.readAll();
            for (int i = 0; i < lista.size(); i++) {
                UsuarioDTO dto2 = (UsuarioDTO) lista.get(i);
                if (dto.getEntidad().getEmail().equals(dto2.getEntidad().getEmail())) {
                    request.setAttribute("mensaje", "Este correo ya ha sido registrado.");
                    vista2.forward(request, response);
                    
                }
                if (dto.getEntidad().getNombreUsuario().equals(dto2.getEntidad().getNombreUsuario())) {
                    request.setAttribute("mensaje", "Este nombre de usuario ya ha sido registrado.");
                    vista2.forward(request, response);
                    
                }
            }
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            dao.create(dto);
            HttpSession sesion = request.getSession(false);
            email.enviarCorreo(dto.getEntidad().getEmail(), "Registro Creado", "Se ha registrado un usuario con este correo.");

            //request.setAttribute("mensaje", "Usuario Agregado con exito");
            if (sesion.getAttribute("sesion") == null) {
                vista.forward(request, response);
            } else {
                email.enviarCorreo((String) sesion.getAttribute("correo"), "Registro Creado", "Ha creado un registro de usuario");
                email.enviarCorreo("eduardo009cs@gmail.com", "Registro Creado", "El usuario " + sesion.getAttribute("nombreUsuario") + " ha creado un nuevo usuario");
                listadoDeUsuarios(request, response);
            }

        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDTO dto = new UsuarioDTO();
        UsuarioDAO dao = new UsuarioDAO();

        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));
        RequestDispatcher vista = request.getRequestDispatcher("usuarios/datosUsuario.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("usuario", dto);
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void usuarioActualizado(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDTO dto = new UsuarioDTO();
        UsuarioDAO dao = new UsuarioDAO();
        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));
        dto.getEntidad().setNombre(request.getParameter("txtNombre"));
        dto.getEntidad().setPaterno(request.getParameter("txtPaterno"));
        dto.getEntidad().setMaterno(request.getParameter("txtMaterno"));
        dto.getEntidad().setEmail(request.getParameter("txtEmail"));
        dto.getEntidad().setNombreUsuario(request.getParameter("txtNombreUsuario"));
        dto.getEntidad().setClaveUsuario(request.getParameter("txtClaveUsuario"));

        EnviarMail email = new EnviarMail();
        HttpSession sesion = request.getSession(false);

        try {
            dao.update(dto);
            request.setAttribute("mensaje", "Usuario Actualizado con exito");
            email.enviarCorreo((String) sesion.getAttribute("correo"), "Registro Actualizado", "Ha actualizado un registro de usuario");
            listadoDeUsuarios(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loginUsuario(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher vista = request.getRequestDispatcher("usuarios/loginUsuario.jsp");
        try {
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void usuarioLogueado(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();

        RequestDispatcher vista1 = request.getRequestDispatcher("usuarios/loginUsuario.jsp");
        RequestDispatcher vista2 = request.getRequestDispatcher("index.jsp");
        try {

            List lista = dao.readAll();
            for (int i = 0; i < lista.size(); i++) {
                UsuarioDTO dto = (UsuarioDTO) lista.get(i);
                if (dto.getEntidad().getNombreUsuario().equals(request.getParameter("txtNombreUsuario"))) {
                    if (dto.getEntidad().getClaveUsuario().equals(request.getParameter("txtClaveUsuario"))) {
                        request.setAttribute("usuario", dto);
                        HttpSession sesion = request.getSession(true);
                        sesion.setAttribute("nombreUsuario", dto.getEntidad().getNombre());
                        sesion.setAttribute("correo", dto.getEntidad().getEmail());
                        sesion.setAttribute("sesion", true);
                        vista2.forward(request, response);
                    } else {
                        request.setAttribute("mensaje", "Error al iniciar sesión.");
                        vista1.forward(request, response);
                    }
                }
            }

        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
        HttpSession sesion = request.getSession(false);
        sesion.invalidate();
        RequestDispatcher vista = request.getRequestDispatcher("index.jsp");
        try {
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarReporte(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        try {
            ServletOutputStream sos = response.getOutputStream();
            File reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/ReporteUsuarios.jasper"));
            byte[] b = JasperRunManager.runReportToPdf(reporte.getPath(), null, dao.obtenerConexion());
            response.setContentType("application/pdf");
            response.setContentLength(b.length);

            sos.write(b, 0, b.length);
            sos.flush();
            sos.close();

        } catch (IOException | JRException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
