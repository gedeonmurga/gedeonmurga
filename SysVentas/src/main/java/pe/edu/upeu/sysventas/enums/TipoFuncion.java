package pe.edu.upeu.sysventas.enums;

import lombok.Getter;

@Getter
public enum TipoFuncion {
    TEMPRANO("Función de día"),
    TARDE("Función de tarde"),
    NOCHE("Función de noche");
    String descripcion;
    TipoFuncion(String descripcion){
        this.descripcion=descripcion;
    }
}
