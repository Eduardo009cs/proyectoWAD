package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.GraficaDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GraficaDAO {
    public Connection conexion;
    
    private static final String SQL_GRAFICAR="SELECT  nombreCategoria, count(*) as cantidad FROM Categoria inner JOIN Producto ON Categoria.idcategoria = Producto.idcategoria group by nombrecategoria;"; 
    
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
    
    public List obtenerDatosGrafica() throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List lista = new ArrayList();
        
        try{
            ps = conexion.prepareStatement(SQL_GRAFICAR);
            rs = ps.executeQuery();
            while(rs.next()){
                GraficaDTO dto = new GraficaDTO();
                dto.setNombreCategoria(rs.getString("nombreCategoria"));
                dto.setCantidad(rs.getInt("cantidad"));
                lista.add(dto);
            }
        }finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conexion != null) conexion.close();
        }
        return lista;
    }
    public static void main(String[] args) {
        GraficaDAO dao = new GraficaDAO();
        try {
            System.out.println(dao.obtenerDatosGrafica());
        } catch (SQLException ex) {
            Logger.getLogger(GraficaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
