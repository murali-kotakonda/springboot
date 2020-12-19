package jersey;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.Product;
import main.ProductService;

public class ProductControllerJersey {
	private static final Logger logger = LoggerFactory.getLogger(ProductControllerJersey.class);	
	@Autowired
	private ProductService service;
	@GET
	@Path("/details")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticleDetails() {
		List<Product> list = service.getAllArticles(); 
		return Response.ok(list).build();
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticleById(@PathParam("id") Integer id) {
		Product article = service.getArticleById(id);
		return Response.ok(article).build();
	}
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addArticle(Product article) {
        boolean isAdded = service.addArticle(article);
        if (!isAdded) {
        	logger.info("Article already exits.");
	        return Response.status(Status.CONFLICT).build();
        }
        return Response.created(URI.create("/spring-app/article/"+ article.getArticleId())).build();
	}	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public Response updateArticle(Product article) {
		service.updateArticle(article);
		return Response.ok(article).build();
	}
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)		
	public Response deleteArticle(@PathParam("id") Integer id) {
		service.deleteArticle(id);
		return Response.noContent().build();
	}	
}