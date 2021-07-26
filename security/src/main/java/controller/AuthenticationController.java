package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jwt.AppUtil;
import jwt.AuthenticationRequest;

@RestController
public class AuthenticationController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/authenticate")
	    public String generateToken(@RequestBody AuthenticationRequest authRequest) throws Exception {
		 Authentication authenticate =  null; 
		 try {
			 authenticate= authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
	            );
	        } catch (Exception ex) {
	            throw new Exception("inavalid username/password");
	        }
	        //return "token generated"; 
	        return AppUtil.generateToken(authenticate);
	    }
}

