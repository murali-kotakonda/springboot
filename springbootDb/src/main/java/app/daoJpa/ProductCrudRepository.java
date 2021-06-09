package app.daoJpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import app.util.Product;

@Repository
public interface ProductCrudRepository extends CrudRepository<Product, Integer> {
	
	
}

