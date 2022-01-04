package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dto.ProductoDTO;
import com.ipn.mx.utilerias.EnviarMail;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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


/**
 *
 * @author eduar
 */
@WebServlet(name = "ProductoController", urlPatterns = {"/ProductoController"})
public class ProductoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion.equals("listaDeProductos")) {
            listadoDeProductos(request, response);
        } else {
            if (accion.equals("nuevo")) {
                agregarProducto(request, response);
            } else {
                if (accion.equals("eliminar")) {
                    eliminarProducto(request, response);
                } else {
                    if (accion.equals("actualizar")) {
                        actualizarProducto(request, response);
                    } else {
                        if (accion.equals("guardar")) {
                            almacenarProducto(request, response);
                        } else {
                            if (accion.equals("ver")) {
                                mostrarProducto(request, response);
                            } else {
                                if (accion.equals("verReporte")) {
                                    mostrarReporte(request, response);
                                } else {

                                    if (accion.equals("actualizado")) {
                                        productoActualizado(request, response);
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

    private void listadoDeProductos(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        try {
            //System.out.println(dao.readAll());
            if (dao.readAll() == null) {
                request.setAttribute("mensaje", "No hay registros");
            }
            request.setAttribute("listaDeProductos", dao.readAll());
            RequestDispatcher rd = request.getRequestDispatcher("productos/listaDeProductos.jsp");
            rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarProducto(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("accion", "guardar");
        request.setAttribute("titulo", "Agregar Producto");
        RequestDispatcher rd = request.getRequestDispatcher("productos/productosForm.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
        
        EnviarMail email = new EnviarMail();
        HttpSession sesion = request.getSession(false);
        try {
            dao.delete(dto);
            request.setAttribute("mensaje", "Producto eliminado");
            email.enviarCorreo((String) sesion.getAttribute("correo"), "Registro eliminado", "Ha eliminado un registro de producto");
            listadoDeProductos(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        request.setAttribute("titulo", "Actualizar Producto");
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
        RequestDispatcher vista = request.getRequestDispatcher("productos/productosForm.jsp");
        try {
            dto = dao.read(dto);
            request.setAttribute("producto", dto);
            request.setAttribute("accion", "actualizado&id=" + dto.getEntidad().getIdProducto());
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();

        dto.getEntidad().setNombreProducto(request.getParameter("txtNombreProducto"));
        dto.getEntidad().setDescripcionProducto(request.getParameter("txtDescripcionProducto"));
        dto.getEntidad().setExistenciasProducto(Integer.parseInt(request.getParameter("txtExistenciasProducto")));
        dto.getEntidad().setPrecioProducto(Double.parseDouble(request.getParameter("txtPrecioProducto")));
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("txtClaveCategoria")));
        
        EnviarMail email = new EnviarMail();
        HttpSession sesion = request.getSession(false);

        try {
            dao.create(dto);
            request.setAttribute("mensaje", "Producto agregado con exito");
            email.enviarCorreo((String) sesion.getAttribute("correo"), "Registro Almacenado", "Ha almacenado un registro de producto");
            listadoDeProductos(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void productoActualizado(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
        dto.getEntidad().setNombreProducto(request.getParameter("txtNombreProducto"));
        dto.getEntidad().setDescripcionProducto(request.getParameter("txtDescripcionProducto"));
        dto.getEntidad().setExistenciasProducto(Integer.parseInt(request.getParameter("txtExistenciasProducto")));
        dto.getEntidad().setPrecioProducto(Double.parseDouble(request.getParameter("txtPrecioProducto")));
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("txtClaveCategoria")));

        EnviarMail email = new EnviarMail();
        HttpSession sesion = request.getSession(false);
        
        try {
            dao.update(dto);
            request.setAttribute("mensaje", "Producto actualizado con exito");
            email.enviarCorreo((String) sesion.getAttribute("correo"), "Registro actualizado", "Ha actualizado un registro de producto");
            listadoDeProductos(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
        RequestDispatcher vista = request.getRequestDispatcher("productos/datosProducto.jsp");
        try {
            dto = dao.read(dto);
            request.setAttribute("producto", dto);
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarReporte(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        try {
            ServletOutputStream sos = response.getOutputStream();
            File reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/ReporteProductosGeneral.jasper"));
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
