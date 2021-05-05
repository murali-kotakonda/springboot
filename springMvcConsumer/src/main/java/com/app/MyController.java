package com.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

	@Autowired
	ProductService service;

	@RequestMapping("/getAllProduct")
	public ModelAndView getAllPdts() {
		List<Product> pdt = service.getProducts();
		return new ModelAndView("showpdts", "pdts", pdt);
	}
	
	@RequestMapping(value = "/CreateProduct")
	public ModelAndView show() {
		Product pdt = new Product();
		return new ModelAndView("show", "command", pdt);
	}
	

	@RequestMapping(value = "addpdt", method = RequestMethod.POST)
	public ModelAndView Add(@ModelAttribute Product product) {
		service.add(product);
		return new ModelAndView("success", "msg", "Product Added Success");
	}

	@RequestMapping(value = "/GetByid")
	public ModelAndView GetById() {
		return new ModelAndView("GetProduct");
	}

	@RequestMapping(value = "/GetProductById1", method = RequestMethod.GET)
	public ModelAndView GetProductId(@RequestParam("id") int id) {
		Product pdt = new Product();
		pdt.setArticleId(id);
		pdt = service.getProduct(pdt);
		if (pdt == null) {
			return new ModelAndView("GetProduct", "msg", "Product Id Not found.");
		}
		return new ModelAndView("getById", "p", pdt);
	}

	@RequestMapping("/Deleteproductbyid")
	public String showDeletepdt() throws Exception {
		return "deleteUser";
	}

	@RequestMapping(value = "/deletePdt", method = RequestMethod.GET)
	public ModelAndView deletePdt(@RequestParam("id") int id) throws Exception {
		Product pdt = new Product();
		pdt.setArticleId(id);
		boolean status = service.deleteProduct(pdt);
		if (!status) {
			return new ModelAndView("deleteUser", "messageInfo", "Product Id Not found.");
		}
		return new ModelAndView("deleteUser", "messageInfo", "Deleted successfully");
	}

	@RequestMapping("/Updateproduct")
	public ModelAndView showUpdateEmp(@RequestParam("id") int id) {
		Product pdt = new Product();
		pdt.setArticleId(id);
		pdt = service.getProduct(pdt);
		return new ModelAndView("updateProfile", "command", pdt);
	}

	@RequestMapping(value = "/Updateproduct1", method = RequestMethod.POST)
	public ModelAndView updateEmpProfile(@ModelAttribute("product") Product product) {
		Product pro = service.updateProduct(product);
		if (pro != null) {
			ModelAndView modelAndView = new ModelAndView("updateUser");
			modelAndView.addObject("msg", "update successful!!");
			return modelAndView;
		} else {
			String msg = "Product not updated";
			ModelAndView modelAndView = new ModelAndView("updateProfile");
			modelAndView.addObject("msg", msg);
			return modelAndView;
		}
	}

}
