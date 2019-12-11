package test.project.myproject.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.project.myproject.dao.CartItemDAO;
import test.project.myproject.domain.CartItem;
import test.project.myproject.repository.CartItemRepository;

@Component
public class CartItemDAOImpl implements CartItemDAO{
	
	@Autowired
	CartItemRepository cartItemRepository;
	@Override
	public CartItem save(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}
	@Override
	public void delete(Long cartItemId) {
		cartItemRepository.deleteById(cartItemId);
	}
	@Override
	public CartItem getById(Long id) {
		Optional<CartItem> opt = cartItemRepository.findById(id);
		if(opt.isPresent()) return opt.get();
		else return null;
	}
	
}
