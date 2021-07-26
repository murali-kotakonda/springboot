package app;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class CatalogController {

	@GetMapping("/")
	public String message(Principal principal) {
		return "Hello "+principal.getName();
	}
	
	  /*@GetMapping("/")
		@ResponseBody
		public Principal message(Principal principal) {
			return principal;
		}*/
		
		
	
    private static  List<Catalog> CATALOGS = new ArrayList<>();
    static {
    	CATALOGS.add(new Catalog(1, "Mobiles"));
    	CATALOGS.add(new Catalog(2, "Accessories"));
    	CATALOGS.add(new Catalog(3, "Laptops"));
    	CATALOGS.add(new Catalog(3, "Groceries"));
    }
	
  
    
    @GetMapping("/catalog")
    //   @PreAuthorize("hasAuthority('catalog:read')")
 	public ResponseEntity<ResponseInfo<Catalog>> getAllCatalogs() {
 		List<Catalog> list = CATALOGS;
 		return new ResponseEntity(new ResponseInfo<Catalog>("success", list), HttpStatus.OK);
 	}

 	@GetMapping(value = "/catalog/{id}")
 	//@PreAuthorize("hasAuthority('catalog:read')")
 	public ResponseEntity<Catalog> getCatalogById(@PathVariable("id") Integer CatalogId) {
 		Catalog article = CATALOGS.stream()
                .filter(Catalog -> CatalogId.equals(Catalog.getCatalogId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "CatalogId " + CatalogId + " does not exists"
                ));
 		return new ResponseEntity<Catalog>(article, HttpStatus.OK);
 	}
 	
 	@DeleteMapping(value = "/catalog/{id}")
 	//@PreAuthorize("hasAuthority('catalog:write')")
 	public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer CatalogId ) {
 		CATALOGS.removeIf(Catalog -> CatalogId.equals(Catalog.getCatalogId()));
 		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
 	}
 		
 	@PostMapping(value = "/catalog",consumes = "application/json" ,produces = "application/json")
 	//@PreAuthorize("hasAuthority('catalog:write')")
 	public ResponseEntity<Void> addCatalog(@RequestBody Catalog Catalog, UriComponentsBuilder builder) {
 		Integer productId = Catalog.getCatalogId();        
 		boolean exists = CATALOGS.stream()
                         .filter(product -> productId.equals(product.getCatalogId()))
                         .findFirst()
                         .orElse(null)!=null;
                 if (exists) {
                 	return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
                 }
                 CATALOGS.add(Catalog);
                 HttpHeaders headers = new HttpHeaders();
                 headers.setLocation(builder.path("/article/{id}").buildAndExpand(Catalog.getCatalogId()).toUri());
                 return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
 	}
 	
 	@PutMapping( value = "/catalog",produces = "application/json",consumes = "application/json")
 	//@PreAuthorize("hasAuthority('catalog:write')")
 	public ResponseEntity<Catalog> updateCatalog(@RequestBody Catalog Catalog) {
 		Integer productId = Catalog.getCatalogId();    
 		Catalog pro = CATALOGS.stream()
                .filter(product -> productId.equals(product.getCatalogId()))
                .findFirst()
                .orElse(null);
        if (pro==null) {
        	return new ResponseEntity<Catalog>(HttpStatus.BAD_REQUEST);
        }
        pro.setCatalogName(Catalog.getCatalogName());
 		return new ResponseEntity<Catalog>(Catalog, HttpStatus.OK);
 	}
}
