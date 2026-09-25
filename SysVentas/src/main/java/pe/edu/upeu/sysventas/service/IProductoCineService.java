package pe.edu.upeu.sysventas.service;

import pe.edu.upeu.sysventas.dto.ComboBoxOption;
import pe.edu.upeu.sysventas.model.ProductoCine;

import java.util.List;

public interface IProductoCineService extends ICrudGenericoService<ProductoCine, Long>{
    List<ComboBoxOption> listarCombobox();
}
