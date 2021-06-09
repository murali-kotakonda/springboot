package app.daoJpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.util.Product;
@Repository
public interface ProductJPARepository extends JpaRepository<Product, Integer> {
	
	public List<Product> findByCategory(String category);
	
	public boolean existsByTitleAndCategory(String title, String category);
	
}

//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods