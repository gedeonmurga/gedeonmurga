package pe.edu.upeu.sysventas.repository;

import pe.edu.upeu.sysventas.model.Sala;

public class SalaRepository extends AbstractJpaRepository<Sala, Long>{
private long sequence=1;
    @Override
    protected Long getId(Sala entity) {
        return entity.getIdSala();
    }

    @Override
    protected void setId(Sala entity, Long id) {
        entity.setIdSala(id);

    }

    @Override
    protected Long generateId() {
        return sequence++;
    }

    public void seedData() {
        if (findAll().isEmpty()) {
            save(new Sala(generateId(), "Sala 1"+ " 2D"));
            save(new Sala(generateId(), "Sala 2"+  " 2D"));
            save(new Sala(generateId(), "Sala 3"+  " 3D"));
            save(new Sala(generateId(), "Sala 4"+ " 2D"));
            save(new Sala(generateId(), "Sala VIP"+  " VIP"));
        }
    }
}
