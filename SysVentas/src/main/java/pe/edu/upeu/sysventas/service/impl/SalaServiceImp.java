package pe.edu.upeu.sysventas.service.impl;

import pe.edu.upeu.sysventas.dto.ComboBoxOption;
import pe.edu.upeu.sysventas.model.Sala;
import pe.edu.upeu.sysventas.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysventas.repository.SalaRepository;
import pe.edu.upeu.sysventas.service.ISalaService;

import java.util.ArrayList;
import java.util.List;

public class SalaServiceImp extends CrudGenericoServiceImp<Sala, Long> implements ISalaService {
    private final SalaRepository salaRepository;

    public SalaServiceImp(SalaRepository marcaRepository) {
        this.salaRepository = marcaRepository;
    }


    @Override
    protected ICrudGenericoRepository<Sala, Long> getRepo() {
        return salaRepository;
    }

    @Override
    public List<ComboBoxOption> listarCombobox() {
        if(salaRepository.findAll().isEmpty()) {
            salaRepository.seedData();
        }
        List<ComboBoxOption> listar = new ArrayList<>();
        for (Sala m : salaRepository.findAll()) {
            ComboBoxOption cb = new ComboBoxOption();
            cb.setKey(String.valueOf(m.getIdSala()));
            cb.setValue(m.getNombre());
            listar.add(cb);
        }
        return listar;
    }
}
