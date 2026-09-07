package pe.edu.upeu.sysventas.model;

//import lombok.Getter;

import lombok.Data;

import java.util.List;
//@Getter
//@Getter
@Data
public class Perfil {
    long idPerfil;
    String nombre;
    String codigo;
    List<Acceso> accesos;
}
