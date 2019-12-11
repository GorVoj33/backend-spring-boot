package test.project.myproject.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import test.project.myproject.dao.CategoryDAO;
import test.project.myproject.dao.ProductDAO;
import test.project.myproject.domain.Category;
import test.project.myproject.domain.Product;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CategoryRestController {
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	ProductDAO productDAO;
	@GetMapping("/rest/categories")
	public List<Category> getAllCategories(){
		return categoryDAO.getAll();
	}
	@GetMapping("/rest/categories/{catId}")
	public List<Product> getPorductsByCategoryId(@PathVariable("catId") Long id){
		List<Product> products = new ArrayList<>();
		List<Product> allProducts = productDAO.getAll();
		for(Product p : allProducts) {
			if(p.getCategory().getId() == id) {
				products.add(p);
			}
		}
		return products;
	}
	@PostMapping("/rest/categories")
	public boolean saveCategory(@RequestBody Category cat) {
//		cat.setId(0l);
		return categoryDAO.save(cat);
	}
}
