package rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {

	//the controller method will return ResponseEntity obj  only if the response is json/xml
	// the controller method will return  String   only if the response is text

	/**
	    URL : /hi
	  Http Method : GET
	  Input Format : NA
	  Input data : NA
	  Output format : text
	  Output data : Welcome to Spring Boot
	   Response Code : 200

	 */
	@RequestMapping("/hi")
	public String getHello() {
		return "Welcome to Spring Boot";
	}

	/**
	  URL : /hello
	  Http Method : GET
	  Input Format : NA
	  Input data : NA
	  Output format : text
	  Output data : Hello Spring Boot
	  Response Code : 200
		 */
	@RequestMapping("/hello")
	public String myData() {
		return "Hello Spring Boot"; //returns text
	}

	
	/**
	 	Req:
		-----
		  URL : /myjson               @RequestMapping(value = "/myjson"
		  Http Method : GET           method =RequestMethod.GET,
		  Input Format : NA
		  Input data : NA
		  Output format : JSON          produces = {"application/json"}
		  Output data : 
		{
		    "msg": "success"
		}
		
		  How  to give diff response code to the customer:
	  -------------------------------------------------------
	   - Response contain data and status
	   - provide the response obj and status to the ResponseEntity.
	   use HttpStatus class for providing the different status
	   ex: 
	   return  new ResponseEntity(emp, HttpStatus.OK); 
	   return  new ResponseEntity(emp, HttpStatus.CREATED); 
	   return  new ResponseEntity(emp, HttpStatus.ACCEPTED); 
	   return  new ResponseEntity(emp, HttpStatus.NO_CONTENT); 
	 */
	@RequestMapping(value = "/myjson", method =RequestMethod.GET,produces = {"application/json"})
	public ResponseEntity getJson() {
		AppResponse res =  new AppResponse("success") ;
		//return new ResponseEntity(emp, HttpStatus.OK);
		return new ResponseEntity(res, HttpStatus.OK); //returns response entity obj that has data + responsecode
	}
	
	/**
	  	  URL : /myxml                     @RequestMapping(value = "/myxml"
		  Http Method : GET                 method =RequestMethod.GET,
		  Input Format : NA        
		  Input data : NA
		  Output format : xml             produces = {"application/xml"}
		  
		  Output data :                    ResponseEntity obj    [ data  + response_code]
		 <EmpResponse>
		    <msg>success</msg>
		</EmpResponse>
		Response Code : 200

	 */
	@RequestMapping(value = "/myxml", method =RequestMethod.GET,produces = {"application/xml"})
	public ResponseEntity getXml() {
		AppResponse res =  new AppResponse("success") ;
		return new ResponseEntity(res, HttpStatus.OK); //returns response entity obj that has data + responsecode
	}
	
	/**
	  URL : /mydata                     @RequestMapping(value = "/mydata"
	  Http Method : GET                 method =RequestMethod.GET,
	  Input Format : NA        
	  Input data : NA
	  Output format : xml/json            produces = {"application/xml","application/json"}
	  
	  Output data :                    ResponseEntity obj    [ data  + response_code]
	 <EmpResponse>
	    <msg>success</msg>
	</EmpResponse>
	  or
  		{
		    "msg": "success"
		}
		
	Response Code : 200
	
	if Accept : "application/xml" , o/p is an xml
	if Accept : "application/json" , o/p is an json

*/
	
	@RequestMapping(value = "/mydata", method =RequestMethod.GET,
			produces = {"application/xml","application/json"})
	public ResponseEntity getData() {
		AppResponse emp =  new AppResponse("success") ;
		return new ResponseEntity(emp, HttpStatus.OK); //returns response entity obj that has data + responsecode
	}

	/**
	 path param example:
	 -------------------------
	 URL : /data/{id} 															@RequestMapping(value = "/data/{id}"
	 Http Method : GET																	 method =RequestMethod.GET,
	 Input Format : NA  
	 Input data : 
	             No request body
	             only path param for id use @PathVariable
	 Output format : text 
	 Output data :                    
		 [getEmp] Hello User {id} 
	 
	 http://localhost:8078/data/2000
	 */
	@RequestMapping("/data/{id}")
	public String getResponseWithPath(@PathVariable("id") int id) {
		return "[getEmp] Hello User "+ id;
	}

	/**
	 Request param example:
	 -------------------------
	 URL : /dataParam 				@RequestMapping(value = "dataParam"
	 Http Method : GET				method =RequestMethod.GET,
	 Input Format : NA  
	 Input data : 
	             No request body
	             only request param for id , use @RequestParam
	 Output format : text 
	 Output data :                    
		 [getEmp] Hello User {id} 
	 
	 http://localhost:8078/dataParam?id=900000
	 */
	@RequestMapping("/dataParam")
	public String getResponseWithUrl(@RequestParam("id") int id) {
		return "[getResponseWithUrl] Your id = "+id;
	}
	
	/**
	 Get employee by id
	 -------------------------
	 URL : /emp/{id} 				@RequestMapping(value = "/emp/{id}"
	 Http Method : GET				method =RequestMethod.GET,
	 Input Format : NA  
	 Input data : 
	             No request body
	             only request param for id , use @RequestParam
	 Output format : json                use produces =  {"application/json","application/xml"}
	 Output data :                    
		 
	 
	 http://localhost:8078/emp/300
	 
	 */
	
	@RequestMapping(value= "/emp/{id}",produces =  {"application/json","application/xml"})
	public ResponseEntity getEmpById(@PathVariable("id") int id) {
		Employee e = new Employee(1000+id, "user"+id, id);
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
	
	/**
	 path param example:
	 -------------------------
	 URL : /emp/{user}/{pass}				@RequestMapping(value = "/emp/{user}/{pass}"
	 Http Method : GET					      method =RequestMethod.GET,
	 Input Format : NA  
	 Input data : 
	             No request body
	             only path params for id use @PathVariable
	 Output format : json/xml         use    produces =  {"application/json","application/xml"}
	 Output data :                    
		 
	 if username and pass is same ->Login success
	 if username and pass is dif ->Login Failure
	  
	 http://localhost:8078/emp/kumar/kumar1
	 */
	
	
	@RequestMapping(value= "/emp/{user}/{pass}",
			produces =  {"application/json","application/xml"})
	public ResponseEntity getEmpById(
			@PathVariable("user") String user,
			@PathVariable("pass") String pass) {
		if(user.equals(pass)) {
			EmpResponse emp =  new EmpResponse("Login success") ;
			return new ResponseEntity(emp, HttpStatus.OK);
		}else {
			EmpResponse emp =  new EmpResponse("Login failure") ;
			return new ResponseEntity(emp, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value= "/emp",method = RequestMethod.POST , 
			consumes = {"application/json","application/xml"})
	public ResponseEntity createEmp(@RequestBody  Employee emp) {
		return new ResponseEntity<>(new EmpResponse("emp created for "+emp), HttpStatus.CREATED);
	}
	
	@RequestMapping(value= "/emp",
			method = RequestMethod.PUT,
			consumes =  {"application/json","application/xml"} 
			)
	public ResponseEntity updateEmp(@RequestBody  Employee emp) {
		return new ResponseEntity<>(new EmpResponse("emp updated for "+emp), HttpStatus.OK);
	}
	
	
	/**
	  - Develop an API for delete emp
	  - Develop an API that takes
		  -path param
		  -request param
		  -request body (json/xml)
  
	 */
	@RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		return new ResponseEntity<>(new EmpResponse("Employee id is deleted successsfully"), HttpStatus.NO_CONTENT);
	}
}