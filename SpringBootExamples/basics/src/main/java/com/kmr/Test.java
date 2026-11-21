package com.kmr;
import com.kmr.consumer.ConsumeWebService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//@ComponentScan(basePackages ={ "controller", "service","dao" })
public class Test {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Test.class, args);
		ConsumeWebService bean = context.getBean(ConsumeWebService.class);
		//bean.deleteProduct(id);
	}
}

/*@SpringBootApplication as our primary application 
 * configuration class; behind the scenes, 
 * that’s equivalent to @Configuration, @EnableAutoConfiguration, and 
 * @ComponentScan together.
 */
