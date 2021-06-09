package dao.restData;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import main.Product;
/**
 At runtime, Spring Data REST automatically creates an implementation of this interface. Then it uses the @RepositoryRestResource annotation to direct Spring MVC to create RESTful endpoints at /people.

@RepositoryRestResource is not required for a repository to be exported.
It is used only to change the export details, such as using /people instead of the default value of /persons
 */

@RepositoryRestResource(collectionResourceRel = "myproducts", path = "myproduct")
public interface RestProductJPARepository extends JpaRepository<Product, Integer> {
	
	public List<Product> findByCategory(String category);
	 
	
	public boolean existsByTitleAndCategory(String title, String category);
} 

//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods

//meta data:
//http://localhost:8079/profile/myproduct

//http://localhost:8079/myproduct
//http://localhost:8079/myproduct?page=0&size=20
//http://localhost:8079/myproduct?page=1&size=20


//http://localhost:8079/myproduct/search/findByCategory?category=testcat3
	
