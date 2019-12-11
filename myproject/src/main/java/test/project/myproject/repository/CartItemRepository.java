package test.project.myproject.repository;

import org.springframework.data.repository.CrudRepository;

import test.project.myproject.domain.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, Long>{

}
