package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Producto;
import java.io.Serializable;
import lombok.Data;

@Data
public class ProductoDTO implements Serializable{
    private Producto entidad;
    
    public ProductoDTO(){
        entidad = new Producto();
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Clave: ").append(getEntidad().getIdProducto()).append("\n");
        sb.append("Nombre: ").append(getEntidad().getNombreProducto()).append("\n");
        sb.append("Descripcion: ").append(getEntidad().getDescripcionProducto()).append("\n");
        sb.append("Precio: ").append(getEntidad().getPrecioProducto()).append("\n");
        sb.append("Existencia: ").append(getEntidad().getExistenciasProducto()).append("\n");
        sb.append("Categoria: ").append(getEntidad().getIdCategoria()).append("\n");
        return sb.toString();
    }
}
