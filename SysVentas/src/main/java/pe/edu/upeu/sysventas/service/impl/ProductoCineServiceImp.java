package pe.edu.upeu.sysventas.service.impl;

import lombok.RequiredArgsConstructor;
import pe.edu.upeu.sysventas.dto.ComboBoxOption;
import pe.edu.upeu.sysventas.model.ProductoCine;
import pe.edu.upeu.sysventas.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysventas.repository.ProductoCineRepository;
import pe.edu.upeu.sysventas.service.IProductoCineService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProductoCineServiceImp extends CrudGenericoServiceImp<ProductoCine, Long> implements IProductoCineService {
    private final ProductoCineRepository productoCineRepository;
    @Override
    protected ICrudGenericoRepository<ProductoCine, Long> getRepo() {
        return productoCineRepository;
    }

    @Override
    public List<ComboBoxOption> listarCombobox() {

            List<ComboBoxOption> listar=new ArrayList<>();
            if(productoCineRepository.findAll().isEmpty()) {
                productoCineRepository.seedData();
            }
            for (ProductoCine m: productoCineRepository.findAll()) {
                ComboBoxOption cb = new ComboBoxOption();
                cb.setKey(String.valueOf(m.getIdProductoCine()));
                cb.setValue(m.getNombreMedida());
                listar.add(cb);
            }
            return listar;

    }
}
