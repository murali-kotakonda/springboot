package com.kmr.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author I335484
 *
 */
@Service
public class EmpService { 
	
	/**
	@Value- > to assign the values to the instance variables by reading from  "application.properties:"
	 */
	@Value("${com.kmr.config}")
	private String config;
	
	@Value("${pageSize}")
	private String pageSize;
	
	public void process() {
		System.out.println("com.kmr.config = "+config);
		System.out.println("pageSize = "+pageSize);
	}
	
	
}