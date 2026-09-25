package pe.edu.upeu.sysventas.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder//patron de diseño
@NoArgsConstructor
@AllArgsConstructor
public class Peliculas {
    private Long idPeliculas;
    private String nombre;
}