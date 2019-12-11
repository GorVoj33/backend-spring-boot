package test.project.myproject.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.project.myproject.dao.CartDAO;
import test.project.myproject.domain.Cart;
import test.project.myproject.repository.CartRepository;
@Component
public class CartDAOImpl implements CartDAO{
	@Autowired
	CartRepository cartRep;
//	@Autowired
//	CartRepository cartRep;
	@Override
	public Cart save(Cart cart) {
		return cartRep.save(cart);
		
	}

	public Cart getById(Long id) {
		Optional<Cart> c = cartRep.findById(id);
		if(c.isPresent()) return c.get();
		else return null;
	}
//	@Override
//	public Cart getByUserId(Long id) {
//		Cart c = cartRep.getCartByUserId(id);
//		return c;
//	}

	@Override
	public List<Cart> getAll() {
		return (List<Cart>) cartRep.findAll();
	}

}
