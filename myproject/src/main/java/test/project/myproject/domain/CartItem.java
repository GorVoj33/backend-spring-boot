package test.project.myproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CartItem {
	@Id
	//@GeneratedValue
	private Long itemId;
	private int countProducts;
	private double total;
	@ManyToOne
    @JoinColumn(name = "product_id")
	private Product product;
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "cart_id")
	private Cart cart;
	public CartItem() {
		// TODO Auto-generated constructor stub
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public int getCountProducts() {
		return countProducts;
	}

	public void setCountProducts(int countProducts) {
		this.countProducts = countProducts;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof CartItem) {
			return this.itemId==((CartItem) obj).getItemId();
		}else {
			return false;
		}
	}
	
	
}
