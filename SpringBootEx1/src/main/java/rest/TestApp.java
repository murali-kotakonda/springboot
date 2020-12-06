package rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages ={"rest"})
//@EnableSwagger2

public class TestApp {
	private static final Logger logger = LoggerFactory.getLogger(TestApp.class);
	   public static void main(String[] args) {
	      logger.info("this is a info message");
	      logger.warn("this is a warn message");
	      logger.error("this is a error message");
	      SpringApplication.run(TestApp.class, args);
	   }
}
