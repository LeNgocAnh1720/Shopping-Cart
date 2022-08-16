package poly.store.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.store.entity.Category;
import poly.store.service.CategoryService;

@CrossOrigin("*")
@RestController
public class CategoryRestController {
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/rest/categories")
	public List<Category> getAll(){
		return categoryService.findAll();
	}
}
