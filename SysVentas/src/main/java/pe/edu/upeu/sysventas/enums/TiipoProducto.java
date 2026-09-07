package pe.edu.upeu.sysventas.enums;

import lombok.Getter;

@Getter
public enum TiipoProducto {
    PRODUCTO("Producto"),
    PREPARADO("Preparado"),
    SERVICIO("Sevicio");
    String descripcion;
    TiipoProducto(String descripcion){
        this.descripcion=descripcion;
    }
}
