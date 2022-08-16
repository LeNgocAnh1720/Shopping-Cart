package poly.store.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.store.entity.Product;
import poly.store.service.CategoryService;
import poly.store.service.ProductService;



@Controller
public class HomeController {
	@Autowired
	CategoryService cate;
	
	@Autowired
	ProductService productService;
	@RequestMapping("/home/index")
	public String index(Model model) {
		model.addAttribute("cates", cate.findAll());
		List<Product> list = productService.findAll();
		model.addAttribute("items", list);
		return "layout/index";
	}
	
	@RequestMapping({"/admin","/admin/home/index"})
	public String admin() {
		return "redirect:/assets/admin/index1.html";
	}
	
	@RequestMapping("/admin/product")
	public String adminProduct() {
		return "redirect:/assets/admin/product/index.html";
	}
	
	@RequestMapping("/admin/account")
	public String adminAccount() {
		return "redirect:/assets/admin/authority/index.html";
	}
}
