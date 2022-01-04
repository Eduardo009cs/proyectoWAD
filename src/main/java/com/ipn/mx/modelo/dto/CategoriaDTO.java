package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Categoria;
import java.io.Serializable;

public class CategoriaDTO implements Serializable{
    
    private Categoria entidad;
    
    public CategoriaDTO(){
        entidad = new Categoria();
    }
    
    public Categoria getEntidad() {
        return entidad;
    }

    public void setEntidad(Categoria entidad) {
        this.entidad = entidad;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Clave: ").append(getEntidad().getIdCategoria()).append("\n");
        sb.append("Nombre: ").append(getEntidad().getNombreCategoria()).append("\n"); //Llamamos a la entidad a traves del metodo getEntidad, que nos retorna la instanci a la entidad
        sb.append("Descripcion: ").append(getEntidad().getDescripcionCategoria()).append("\n");
        
        
        
        return sb.toString();
    }
}   

