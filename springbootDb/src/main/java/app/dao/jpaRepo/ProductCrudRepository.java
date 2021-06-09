package app.dao.jpaRepo;

import org.springframework.data.repository.CrudRepository;

import app.util.Product;

@org.springframework.stereotype.Repository
public interface ProductCrudRepository extends CrudRepository<Product, Integer> {
	
	
}

