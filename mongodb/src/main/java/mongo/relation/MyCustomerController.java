package mongo.relation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mongo.model.Product;
import mongo.model.ProductResponse;

@RestController
public class MyCustomerController {

	@Autowired
	private CustomerRepository repository;

	/**
	 {
    "custId":1234,
    "customerName": "raj",
	"age":34,
	"accounts":[
	{
	   "accId": 12345,
	    "accountName": "raj1",
	    "description": "raj2" 
	}
	]
	}
	 */
	@PostMapping("/customer")
	public String saveCustomer(@RequestBody Customer customer) {
		repository.save(customer);
		return "Added customer with id : " + customer.getCustId();
	}

	@PutMapping("/customer")
	public String updateCustomer(@RequestBody Customer customer) {
		repository.save(customer);
		return "updated customer with id : " + customer.getCustId();
	}

	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return repository.findAll();
	}

	@GetMapping("/customer/{id}")
	public ProductResponse getCustomer(@PathVariable String id) {
		Optional<Customer> customer = repository.findById(id);
		ProductResponse<Customer> response = new ProductResponse<>();
		if(customer.isPresent()) {
			response.setMessage("success");
			response.getProducts().add(customer.get());
		}else{
			response.setMessage("customer not found");
		}
		return response;
	}
	
	@DeleteMapping("/customer/{id}")
	public String deleteProduct(@PathVariable String id) {
		repository.deleteById(id);
		return "Customer deleted with id : " + id;
	}
}
