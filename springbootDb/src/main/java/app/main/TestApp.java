package app.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "app" })
@EntityScan(basePackages="app")
@EnableJpaRepositories(basePackages = { "app"})
public class TestApp {
	private static final Logger logger = LoggerFactory.getLogger(TestApp.class);
	public static void main(String[] args) {
		SpringApplication.run(TestApp.class, args);
	}
}