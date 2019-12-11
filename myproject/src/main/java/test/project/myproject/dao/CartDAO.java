package test.project.myproject.dao;

import java.util.List;

import test.project.myproject.domain.Cart;

public interface CartDAO {
	Cart save(Cart cart);
	Cart getById(Long id);
	List<Cart> getAll();
	//Cart getCartByUsername(String username);
//	Cart getByUserId(Long id);
}
