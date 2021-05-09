package demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jwt.AppUtil;
import jwt.RequestCredentials;

@RestController
public class AuthenticationController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	 @PostMapping("/authenticate")
	    public String generateToken(@RequestBody RequestCredentials authRequest) throws Exception {
		 Authentication authenticate =  null; 
		 try {
			 authenticate= authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
	            );
	        } catch (Exception ex) {
	            throw new Exception("inavalid username/password");
	        }
	        return AppUtil.generateToken(authenticate);
	    }
}

