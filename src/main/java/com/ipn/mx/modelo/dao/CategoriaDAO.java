package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.CategoriaDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriaDAO {

    private static final String SQL_INSERT = "call spInsertarCategoria(?,?)";
    private static final String SQL_UPDATE = "call spActualizarCategoria(?,?,?)";
    private static final String SQL_DELETE = "call spDeleteCategoria(?)";
    private static final String SQL_SELECT = "select * from seleccionaCategoria(?)";
    private static final String SQL_SELECT_ALL = "select * from seleccionaTodoCategoria()";

    private Connection conexion;

    public Connection obtenerConexion() {
        String usuario = "fvgjjynxhdckjz";
        String clave = "430dbfb772472d50b7e0696a4219c1d7833522b039ea9fab8993e7f8527b5989";
        String url = "jdbc:postgresql://ec2-34-205-14-168.compute-1.amazonaws.com:5432/dd2g6sjdt74amt";
        String driverPostgreSql = "org.postgresql.Driver";

        try {
            Class.forName(driverPostgreSql);
            conexion = DriverManager.getConnection(url, usuario, clave);
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return conexion;
    }

    public void create(CategoriaDTO dto) throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;

        try {
            cs =  conexion.prepareCall(SQL_INSERT);
            cs.setString(1, dto.getEntidad().getNombreCategoria());
            cs.setString(2, dto.getEntidad().getDescripcionCategoria());
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

    public void update(CategoriaDTO dto) throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;

        try {
            cs = conexion.prepareCall(SQL_UPDATE);
            cs.setString(1, dto.getEntidad().getNombreCategoria());
            cs.setString(2, dto.getEntidad().getDescripcionCategoria());
            cs.setInt(3, dto.getEntidad().getIdCategoria());
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

    public void delete(CategoriaDTO dto) throws SQLException {
        obtenerConexion();
        CallableStatement cs = null;

        try {
            cs = conexion.prepareCall(SQL_DELETE);
            cs.setInt(1, dto.getEntidad().getIdCategoria());
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
    
    public CategoriaDTO read (CategoriaDTO dto)throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = conexion.prepareCall(SQL_SELECT);
            cs.setInt(1, dto.getEntidad().getIdCategoria());
            rs = cs.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size()>0){
                return (CategoriaDTO)resultados.get(0);
            }else{
                return null;
            }
        }finally{
            if(rs != null) rs.close();
            if(cs != null) cs.close();
            if(conexion != null) conexion.close();
        }
    } 
    public List readAll ()throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = conexion.prepareCall(SQL_SELECT_ALL);
            rs = cs.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size()>0){
                return resultados;
            }else{
                return null;
            }
        }finally{
            if(rs != null) rs.close();
            if(cs != null) cs.close();
            if(conexion != null) conexion.close();
        }
    } 
    private List obtenerResultados(ResultSet rs)throws SQLException{
        List resultados = new ArrayList();
        while(rs.next()){
            CategoriaDTO dto = new CategoriaDTO();
            dto.getEntidad().setIdCategoria(rs.getInt("idCategoria"));
            dto.getEntidad().setNombreCategoria(rs.getString("nombreCategoria"));
            dto.getEntidad().setDescripcionCategoria(rs.getString("descripcionCategoria"));
            resultados.add(dto);
        }
        return resultados;
    }
    public static void main(String[] args) {
        CategoriaDTO dto = new CategoriaDTO();
        /*dto.getEntidad().setNombreCategoria("Deportes");
        dto.getEntidad().setNombreCategoria("todo de deportes");
*/
        CategoriaDAO dao = new CategoriaDAO();
        
        try {
            System.out.println(dao.readAll());
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*private List obtenerResultados(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
