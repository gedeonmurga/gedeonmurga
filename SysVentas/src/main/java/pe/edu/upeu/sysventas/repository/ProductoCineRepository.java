package pe.edu.upeu.sysventas.repository;

import pe.edu.upeu.sysventas.model.ProductoCine;

public class ProductoCineRepository extends  AbstractJpaRepository<ProductoCine, Long>{
    private long sequence=1;
    @Override
    protected Long getId(ProductoCine entity) {
        return entity.getIdProductoCine();
    }

    @Override
    protected void setId(ProductoCine entity, Long id) {
        entity.setIdProductoCine(id);
    }

    @Override
    protected Long generateId() {
        return sequence++;
    }

    public void seedData() {
        if (findAll().isEmpty()) {
            save(new ProductoCine(generateId(), "Canchita Grande"+ " Snacks"));
            save(new ProductoCine(generateId(), "Canchita Mediana"+ " Snacks"));
            save(new ProductoCine(generateId(), "Gaseosa Grande"+ " Bebidas"));
            save(new ProductoCine(generateId(), "Gaseosa Mediana"+ " Bebidas"));
            save(new ProductoCine(generateId(), "Combo Pareja"+ " Combos"));
            save(new ProductoCine(generateId(), "Chocolate"+ " Dulces"));
            save(new ProductoCine(generateId(), "Agua"+ " Bebidas"));
        }
    }
}
