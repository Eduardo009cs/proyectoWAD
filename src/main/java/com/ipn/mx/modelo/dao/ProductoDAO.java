package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.ProductoDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductoDAO {

   private static final String SQL_INSERT = "call spInsertarProducto(?,?,?,?,?)";
    private static final String SQL_UPDATE = "call spActualizarProducto(?,?,?,?,?,?)";
    private static final String SQL_DELETE = "call spDeleteProducto(?)";
    private static final String SQL_SELECT = "select * from seleccionaProducto(?)";
    private static final String SQL_SELECT_ALL = "select * from seleccionaTodoProducto()";
    private Connection conexion;

    private void obtenerConexion() {
        String usuario = "fvgjjynxhdckjz";
        String clave = "430dbfb772472d50b7e0696a4219c1d7833522b039ea9fab8993e7f8527b5989";
        String url = "jdbc:postgresql://ec2-34-205-14-168.compute-1.amazonaws.com:5432/dd2g6sjdt74amt";
        String driverPostgreSql = "org.postgresql.Driver";

        try {
            Class.forName(driverPostgreSql);
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(GraficaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void create(ProductoDTO dto) throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;
        try {
            cs = conexion.prepareCall(SQL_INSERT);
            cs.setString(1, dto.getEntidad().getNombreProducto());
            cs.setString(2, dto.getEntidad().getDescripcionProducto());
            cs.setDouble(3, dto.getEntidad().getPrecioProducto());
            cs.setInt(4, dto.getEntidad().getExistenciasProducto());
            cs.setInt(5, dto.getEntidad().getIdCategoria());
            cs.executeUpdate();
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    public void update(ProductoDTO dto) throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;

        try {
            cs = conexion.prepareCall(SQL_UPDATE);
            cs.setString(1, dto.getEntidad().getNombreProducto());
            cs.setString(2, dto.getEntidad().getDescripcionProducto());
            cs.setDouble(3, dto.getEntidad().getPrecioProducto());
            cs.setInt(4, dto.getEntidad().getExistenciasProducto());
            cs.setInt(5, dto.getEntidad().getIdCategoria());
            cs.setInt(6, dto.getEntidad().getIdProducto());
            cs.executeUpdate();
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    public void delete(ProductoDTO dto) throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;

        try {
            cs = conexion.prepareCall(SQL_DELETE);
            cs.setInt(1, dto.getEntidad().getIdProducto());
            cs.executeUpdate();
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    public ProductoDTO read(ProductoDTO dto) throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            cs = conexion.prepareCall(SQL_SELECT);
            cs.setInt(1, dto.getEntidad().getIdProducto());
            rs = cs.executeQuery();
            List resultados = obtenerResultados(rs);
            if (resultados.size() > 0) {
                return (ProductoDTO) resultados.get(0);
            } else {
                return null;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    public List readAll() throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            cs = conexion.prepareCall(SQL_SELECT_ALL);
            rs = cs.executeQuery();
            List resultados = obtenerResultados(rs);
            if (resultados.size() > 0) {
                return resultados;
            } else {
                return null;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException {
        List resultados = new ArrayList();
        while(rs.next()){
            ProductoDTO dto = new ProductoDTO();
            dto.getEntidad().setIdProducto(rs.getInt("idProducto"));
            dto.getEntidad().setNombreProducto(rs.getString("nombreProducto"));
            dto.getEntidad().setDescripcionProducto(rs.getString("descripcionProducto"));
            dto.getEntidad().setPrecioProducto(rs.getDouble("precioProducto"));
            dto.getEntidad().setExistenciasProducto(rs.getInt("existenciasProducto"));
            dto.getEntidad().setIdCategoria(rs.getInt("idCategoria"));
            resultados.add(dto);
        }
        return resultados;
    }
}
