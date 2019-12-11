package test.project.myproject.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import test.project.myproject.dao.CartDAO;
import test.project.myproject.dao.CartItemDAO;
import test.project.myproject.domain.Cart;
import test.project.myproject.domain.CartItem;
import test.project.myproject.domain.Product;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CartRestController {
	@Autowired
	CartDAO cartDAO;
	@Autowired
	CartItemDAO cartItemDAO;
	@PutMapping("/rest/cart/{cartId}")
	//public Cart addProductToCart(@PathVariable("cartId") Long cartId) 
	public ResponseEntity<Cart> updateCart(@PathVariable("cartId") Long id, @RequestBody Cart cart){
		System.out.println("Pristigla cart: "+cart.getId()+ ", "+cart.getTotal() + " ,"+cart.getItemsNumber());
		for(CartItem ci : cart.getItems()) {
			if(cart.getId()>0)
				ci.setCart(cart);
			System.out.println("Item: "+ci.getItemId());
			System.out.println("Item: "+ci.getProduct().getName());
		}
		
		Cart updated = cartDAO.save(cart);
		return new ResponseEntity<Cart>(updated, HttpStatus.OK);
		
	}
	
	@GetMapping("/rest/cart/{cart_id}")
	public Cart getCartById(@PathVariable("cart_id") Long id) {
		return cartDAO.getById(id);
	}
	@GetMapping("/rest/carts")
	public List<Cart> getAllCarts() {
		return cartDAO.getAll();
	}
//	@DeleteMapping("rest/cart/{cartId}/delete/item/{itemId}")
//	public ResponseEntity<Void> deleteItem(@PathVariable("cartId") Long cartId,
//			@PathVariable("itemId") Long itemId) {
//			Cart c = cartDAO.getById(cartId);
//			CartItem item = cartItemDAO.getById(itemId);
//			c.getItems().remove(item);
//			c.calculateTotal();
//			cartItemDAO.delete(itemId);
//			cartDAO.save(c);
//			return ResponseEntity.noContent().build();
//	}
	@DeleteMapping("rest/cart/{cartId}/delete/item/{itemId}")
	public ResponseEntity<Cart> deleteItem(@PathVariable("cartId") Long cartId,
			@PathVariable("itemId") Long itemId) {
			Cart c = cartDAO.getById(cartId);
			CartItem item = cartItemDAO.getById(itemId);
			c.getItems().remove(item);
			c.calculateTotal();
			cartItemDAO.delete(itemId);
			cartDAO.save(c);
			return new ResponseEntity<Cart>(c, HttpStatus.OK);
	}
}
