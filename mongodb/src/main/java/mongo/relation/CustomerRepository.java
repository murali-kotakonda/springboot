package mongo.relation;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import mongo.model.Product;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>{
	
}
