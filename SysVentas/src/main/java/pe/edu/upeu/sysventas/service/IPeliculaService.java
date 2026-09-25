package pe.edu.upeu.sysventas.service;

import pe.edu.upeu.sysventas.dto.ComboBoxOption;
import pe.edu.upeu.sysventas.model.Peliculas;

import java.util.List;

public interface IPeliculaService extends ICrudGenericoService<Peliculas, Long>{
    List<ComboBoxOption> lisCategoria();
}
