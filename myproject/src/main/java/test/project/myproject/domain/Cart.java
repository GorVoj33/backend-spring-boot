package test.project.myproject.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Cart {
	@Id
	@GeneratedValue
	private Long id;
	private double total;
	private int itemsNumber;
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CartItem> items = new ArrayList<>();
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getItemsNumber() {
		return itemsNumber;
	}

	public void setItemsNumber(int itemsNumber) {
		this.itemsNumber = itemsNumber;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Cart(Long id, double total, int itemsNumber, List<CartItem> items, User user) {
		super();
		this.id = id;
		this.total = total;
		this.itemsNumber = itemsNumber;
		this.items = items;
		this.user = user;
	}
	
	public void calculateTotal () {
		double sum = 0;
		for(CartItem i : items) {
			sum+=i.getTotal();
		}
		total = sum;
		itemsNumber = items.size();
	}
	
	
}
