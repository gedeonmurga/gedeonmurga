package pe.edu.upeu.sysventas.model;

//import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Emisor {
    long idEmisor ;
    String ruc;
    String nombreComercial;
    String ubigeo;
    String domicilioFiscal;
    String urbanizacion;
    String departamento;
    String provincia;
    String distrito;
}
