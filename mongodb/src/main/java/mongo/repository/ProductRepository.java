package mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import mongo.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{
	
@Query("{ 'articleId' : ?0 }")
Product findProductsByArticleId(int articleId);

@Query(value="{'articleId' : ?0}", delete = true)
public Long deleteByArticleId (int articleId);

public List<Product> findByCategory(String category);

}
