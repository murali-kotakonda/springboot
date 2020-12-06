package com.mvc;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

	@GetMapping("/hello1")
	public String t1() {
			return "index";
	}
	
	@GetMapping("/hello2")
	public String t2() {
			return "results";
	}
	
	@RequestMapping(value="/hello3" , method= RequestMethod.GET )
	public String handleHello1(){
		return "welcome1";
	}
	
	@RequestMapping(value = "/hello4",method = RequestMethod.GET)
	public ModelAndView getMultiData() {
		ModelAndView responseObj = new ModelAndView("response1");
		responseObj.addObject("age", 34);
		responseObj.addObject("name", "ramesh");
		responseObj.addObject("id", 3000);
		responseObj.addObject("sal", 1133.1331f);
		return responseObj;
	}
}
