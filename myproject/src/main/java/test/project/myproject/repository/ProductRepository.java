package test.project.myproject.repository;

import org.springframework.data.repository.CrudRepository;

import test.project.myproject.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
