package jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import main.ProductController;

@Component
//@ApplicationPath("/spring-app")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(ProductController.class);
	}
}
