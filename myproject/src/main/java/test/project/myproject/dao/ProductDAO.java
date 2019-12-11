package test.project.myproject.dao;
import java.util.List;

import org.springframework.stereotype.Component;

import test.project.myproject.domain.Category;
import test.project.myproject.domain.Product;

public interface ProductDAO {
	List<Product> getAll();

	Product getById(Long id);

	Product save(Product product);

}
