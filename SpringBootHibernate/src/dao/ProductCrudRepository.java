package dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import main.Product;

@org.springframework.stereotype.Repository
public interface ProductCrudRepository extends CrudRepository<Product, Integer> {
	
	
}

