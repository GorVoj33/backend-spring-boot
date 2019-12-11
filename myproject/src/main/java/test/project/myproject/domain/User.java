package test.project.myproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity(name = "user_profile")
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String password;
	private boolean isAdmin;
	private String email;
	private String name;
	@OneToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(Long id, String password, boolean is_admin, String email, String name, Cart cart) {
		super();
		this.id = id;
		this.password = password;
		this.isAdmin = is_admin;
		this.email = email;
		this.name = name;
		this.cart = cart;
	}
	
}
