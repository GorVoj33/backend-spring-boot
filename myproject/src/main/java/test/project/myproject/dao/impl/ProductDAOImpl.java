package test.project.myproject.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.project.myproject.dao.ProductDAO;
import test.project.myproject.domain.Product;
import test.project.myproject.repository.ProductRepository;
@Component
public class ProductDAOImpl implements ProductDAO{
	@Autowired
	ProductRepository productRepository;
	@Override
	public List<Product> getAll() {
		return (List<Product>) productRepository.findAll();
	}
	@Override
	public Product getById(Long id) {
		Optional<Product> p = productRepository.findById(id);
		if(p.isPresent()) return p.get();
		return null;
	}
	@Override
	public Product save(Product product) {
		Product p = productRepository.save(product);
		return p;
	}

}
