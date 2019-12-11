package test.project.myproject.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import test.project.myproject.dao.ProductDAO;
import test.project.myproject.domain.Category;
import test.project.myproject.domain.Product;
import test.project.myproject.domain.User;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductRestController {
	@Autowired
	ProductDAO productDAO;
	@GetMapping("/rest/products")
	public List<Product> getAll(){
		return productDAO.getAll();
	}
	@GetMapping("/rest/products/active")
	public List<Product> getAllActiveProducts(){
		List<Product> all = productDAO.getAll();
		List<Product> onlyActiveProducts = new ArrayList<>();
		for(Product p: all) {
			if(p.isActive()) {
				onlyActiveProducts.add(p);
			}
		}
		return onlyActiveProducts;
	}
	@GetMapping("/rest/products/{id}")
	public Product getById(@PathVariable("id") Long id) {
		return productDAO.getById(id);
	}

	@GetMapping("/rest/products/category/{cat_id}")
	public List<Product> getProductsByCategoryId(@PathVariable("cat_id") Long id) {
		List<Product> all = getAll();
		List<Product> list = new ArrayList<>();
		for (Product product : all) {
			if(product.getCategory().getId()==id)
				list.add(product);
		}
		return list;
	}
	@GetMapping("/rest/products/filter/{filterValue}")
	public List<Product> getProductsByFilter(@PathVariable("filterValue") String filter) {
		List<Product> all = getAll();
		List<Product> list = new ArrayList<>();
		if(filter.equals("")) return all;
		for (Product product : all) {
			if(product.getName().toLowerCase().contains(filter.toLowerCase()))
				list.add(product);
		}
		
		System.out.println("List size: "+list.size());
		return list;
	}
	@PostMapping(value = "/rest/products")
	public ResponseEntity<Void> saveProduct(@RequestBody Product product){
		
		Product createdPr = productDAO.save(product);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdPr.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/rest/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
		System.out.println("Rezzultat: "+product.getId()+ ", "+product.getDescription() + " ,"+product.getPrice()+ " ,"+product.getName());
		Product updated = productDAO.save(product);
		return new ResponseEntity<Product>(updated, HttpStatus.OK);
		
	}
	
}
