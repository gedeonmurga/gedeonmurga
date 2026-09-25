package pe.edu.upeu.sysventas.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.edu.upeu.sysventas.enums.TipoFuncion;

@Data
@Builder
@NoArgsConstructor//te crea constructores //sin argumrntos
@AllArgsConstructor//todos los parametros de entrada
public class Producto {

    private Long idProducto;
    @NotBlank(message = "El nombre del Usuario es obligatorio")
    private String nombre;
    @NotNull(message = "EL tipo de funcion es obligatorio")
    private TipoFuncion tiipoProducto;

    @NotNull(message = "El precio de la pelicula es obligatorio")
    @Positive(message = "El precio de la pelicula debe ser positivo")
    private Double pu;
    @NotNull(message = "El precio anterior del producto es obligatorio")
    @PositiveOrZero(message = "El precio anterior del producto debe ser positivo o cero")
    private Double puold;
    @NotNull(message = "La utilidad es obligatoria")
    @PositiveOrZero(message = "La utilidad debe ser positiva o cero")
    private Double utilidad;
    @NotNull(message = "El stock del producto es obligatorio")
    @PositiveOrZero(message = "El stock del producto debe ser positivo")
    private Double stock;
    @NotNull(message = "El stock anterior del producto es obligatorio")
    @PositiveOrZero(message = "El stock anterior del producto debe ser positivo")
    private Double stockold;
    @NotNull(message = "La pelicula de la funcion es obligatoria")
    private Peliculas idPelicula;
    @NotNull(message = "La sala de la funcion es obligatoria")
    private Sala idSala;
    @NotNull(message = "El producto de cine de la funcion es obligatoria")
    private ProductoCine idProductoCine;
}