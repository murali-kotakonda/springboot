package dao;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import main.Product;

@Configuration
public class StartupData {

	//creates the startup data
	@Bean
	CommandLineRunner loadData(ProductCrudRepository repo) {
		return args -> {
			Product p1 = new Product(100,"test1", "testcat1");
			Product p2 = new Product(101,"test2", "testcat2");
			Product p3 = new Product(102,"test3", "testcat3");
			
			repo.saveAll(Arrays.asList(p1,p2,p3));
		};
	}
}
