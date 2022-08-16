package poly.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Product_DetailController {
	@RequestMapping("/detail")
	public String index() {
		return "product_detail/index";
	}
}
