package poly.store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.store.entity.Product;
import poly.store.service.CategoryService;
import poly.store.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService cate;
	@RequestMapping("/product/list")
	public String list(Model model,@RequestParam("cid") Optional<String> cid)  {
		model.addAttribute("cates", cate.findAll());
		
		if(cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			model.addAttribute("items", list);
		}else {
			List<Product> list = productService.findAll();
			model.addAttribute("items", list);
		}
		return "product/index";
	}
	 
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("cates", cate.findAll());
		 
		Product item = productService.findById(id);
		model.addAttribute("item", item);
		return "product_detail/index";
	}
	
	
}
