package pe.edu.upeu.sysventas.repository;

import pe.edu.upeu.sysventas.model.Peliculas;

public class PeliculaRepository extends AbstractJpaRepository<Peliculas, Long>{
    private  long sequence=1;
    @Override
    protected Long getId(Peliculas entity) {
        return entity.getIdPeliculas();
    }

    @Override
    protected void setId(Peliculas entity, Long id) {
        entity.setIdPeliculas(id);
    }

    @Override
    protected Long generateId() {
        return sequence++;
    }public void seedData() {
        if (findAll().isEmpty()) {
            save(new Peliculas(generateId(), "Avengers"));
            save(new Peliculas(generateId(), "Spider-Man"));
            save(new Peliculas(generateId(), "Toy Story 5"));
            save(new Peliculas(generateId(), "Jurassic World"));
            save(new Peliculas(generateId(), "Mario Bros 2"));
            save(new Peliculas(generateId(), "Transformers"));
            save(new Peliculas(generateId(), "Kung Fu Panda 3"));
            save(new Peliculas(generateId(), "Intensamente 2"));
            save(new Peliculas(generateId(), "Deadpool"));
            save(new Peliculas(generateId(), "El Conjuro"));
        }
    }


}
