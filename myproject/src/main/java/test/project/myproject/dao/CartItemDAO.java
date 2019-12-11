package test.project.myproject.dao;

import test.project.myproject.domain.CartItem;

public interface CartItemDAO {
	CartItem save(CartItem cartItem);
	void delete(Long cartItemId);
	CartItem getById(Long id);
}
