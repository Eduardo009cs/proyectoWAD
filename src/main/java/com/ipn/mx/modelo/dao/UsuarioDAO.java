package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.UsuarioDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {

    private static final String SQL_INSERT = "call spInsertarUsuario(?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "call spActualizarUsuario(?,?,?,?,?,?,?)";
    private static final String SQL_DELETE = "call spDeleteUsuario(?)";
    private static final String SQL_SELECT = "select * from seleccionaUsuario(?)";
    private static final String SQL_SELECT_ALL = "select * from seleccionaTodoUsuario()";
    
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
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void create(UsuarioDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        try{
            cs = conexion.prepareCall(SQL_INSERT);
            cs.setString(1, dto.getEntidad().getNombre());
            cs.setString(2, dto.getEntidad().getPaterno());
            cs.setString(3, dto.getEntidad().getMaterno());
            cs.setString(4, dto.getEntidad().getEmail());
            cs.setString(5, dto.getEntidad().getNombreUsuario());
            cs.setString(6, dto.getEntidad().getClaveUsuario());
            cs.executeUpdate();
        }finally{
            if(cs != null){
                cs.close();
            }
            if(conexion != null){
                conexion.close();
            }
        }
    }
    public void update(UsuarioDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        
        try{
            cs = conexion.prepareCall(SQL_UPDATE);
            cs.setString(1, dto.getEntidad().getNombre());
            cs.setString(2, dto.getEntidad().getPaterno());
            cs.setString(3, dto.getEntidad().getMaterno());
            cs.setString(4, dto.getEntidad().getEmail());
            cs.setString(5, dto.getEntidad().getNombreUsuario());
            cs.setString(6, dto.getEntidad().getClaveUsuario());
            cs.setInt(7, dto.getEntidad().getIdUsuario());
            cs.executeUpdate();
        }finally{
            if(cs != null){
                cs.close();
            }
            if(conexion != null){
                conexion.close();
            }
        }
    }
    public void delete(UsuarioDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        
        try{
            cs = conexion.prepareCall(SQL_DELETE);
            cs.setInt(1, dto.getEntidad().getIdUsuario());
            cs.executeUpdate();
        }finally{
            if(cs != null){
                cs.close();
            }
            if(conexion != null){
                conexion.close();
            }
        }
    }
    public UsuarioDTO read (UsuarioDTO dto) throws SQLException{
        obtenerConexion();
        CallableStatement cs = null;
        ResultSet rs = null;
        
        try{
            cs = conexion.prepareCall(SQL_SELECT);
            cs.setInt(1, dto.getEntidad().getIdUsuario());
            rs = cs.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size()>0){
                return (UsuarioDTO)resultados.get(0);
            }else{
                return null;
            }
        }finally{
            if(rs != null) rs.close();
            if(cs != null) cs.close();
            if(conexion != null) conexion.close();
        }
    }
    public List readAll() throws SQLException{
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
    
    private List obtenerResultados (ResultSet rs) throws SQLException{
        List resultados = new ArrayList();
        while(rs.next()){
            UsuarioDTO dto = new UsuarioDTO();
            dto.getEntidad().setIdUsuario(rs.getInt("idUsuario"));
            dto.getEntidad().setNombre(rs.getString("nombre"));
            dto.getEntidad().setPaterno(rs.getString("paterno"));
            dto.getEntidad().setMaterno(rs.getString("materno"));
            dto.getEntidad().setEmail(rs.getString("email"));
            dto.getEntidad().setNombreUsuario(rs.getString("nombreUsuario"));
            dto.getEntidad().setClaveUsuario(rs.getString("claveUsuario"));
            
            resultados.add(dto);
        }
        return resultados;
    }
    
    public static void main(String[] args) {
        
    }
}
