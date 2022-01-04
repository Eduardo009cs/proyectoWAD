package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Usuario;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDTO implements Serializable{
    private Usuario entidad;
    
    public UsuarioDTO(){
        this.entidad = new Usuario();
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Clave: ").append(getEntidad().getIdUsuario()).append("\n");
        sb.append("Nombre: ").append(getEntidad().getNombre()).append("\n");
        sb.append("Paterno: ").append(getEntidad().getPaterno()).append("\n");
        sb.append("Materno: ").append(getEntidad().getMaterno()).append("\n");
        sb.append("Email: ").append(getEntidad().getEmail()).append("\n");
        sb.append("Usuario: ").append(getEntidad().getNombreUsuario()).append("\n");
        sb.append("Clave: ").append(getEntidad().getClaveUsuario()).append("\n");
        return sb.toString();
    }
}
