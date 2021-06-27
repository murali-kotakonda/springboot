package dao.restData;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.util.Product;
/**


1.Write Repository
2.Expose the repository

steps:
----------
1.add bootstarter for data-rest
2.Add @RepositoryRestResource anotation for the Repository class

 At runtime, Spring Data REST automatically creates an implementation of this interface. Then it uses the @RepositoryRestResource annotation to direct Spring MVC to create RESTful endpoints at /people.

@RepositoryRestResource is not required for a repository to be exported.
It is used only to change the export details, such as using /people instead of the default value of /persons

<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-rest</artifactId>
		</dependency>
 */

//@RepositoryRestResource(collectionResourceRel = "myproducts", path = "myproduct")
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
//http://localhost:8079/myproduct/search/findByTitle?title=dgdg
