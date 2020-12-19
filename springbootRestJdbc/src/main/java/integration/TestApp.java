package integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={"integration"})
public class TestApp {
	private static final Logger logger = LoggerFactory.getLogger(TestApp.class);
	   public static void main(String[] args) {
	      ConfigurableApplicationContext context = SpringApplication.run(TestApp.class, args);
	      IntegrationService service = context.getBean(IntegrationService.class);
	      System.out.println(service);
	   }
}
