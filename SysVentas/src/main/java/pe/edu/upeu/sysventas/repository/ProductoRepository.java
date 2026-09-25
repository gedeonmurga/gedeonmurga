package pe.edu.upeu.sysventas.repository;

import pe.edu.upeu.sysventas.enums.TipoFuncion;
import pe.edu.upeu.sysventas.model.Peliculas;
import pe.edu.upeu.sysventas.model.Sala;
import pe.edu.upeu.sysventas.model.Producto;
import pe.edu.upeu.sysventas.model.ProductoCine;

public class ProductoRepository extends AbstractJpaRepository<Producto, Long>{
    private long sequence=1;
    @Override
    protected Long getId(Producto entity) {
        return entity.getIdProducto();
    }

    @Override
    protected void setId(Producto entity, Long id) {
        entity.setIdProducto(id);
    }

    @Override
    protected Long generateId() {
        return sequence++;
    }

    public void seedData() {
        if (findAll().isEmpty()) {

            Peliculas p=new Peliculas();
            p.setIdPeliculas(2L);

            Sala s=new Sala();
            s.setIdSala(1L);

            ProductoCine u=new ProductoCine();
            u.setIdProductoCine(1L);

            save(new Producto(generateId(),"Gedeon Abad Murga Murga", TipoFuncion.NOCHE, 30.00, 0.00, 10.00,12.0,0.0,
                    p,s,u));
        }
    }
}
