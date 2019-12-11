package test.project.myproject.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity()
public class FinalOrder {
	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;
	private double total;
	@Temporal(TemporalType.DATE)
	private Date date;
	private boolean isDone;
	private String paymentMethod;
	@JsonIgnore
	@OneToMany(mappedBy = "finalOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<OrderItem> items = new ArrayList<>();
	public FinalOrder() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public FinalOrder(Long id, User user, Address address, double total, Date date, boolean isDone, String paymentMethod) {
		super();
		this.id = id;
		this.user = user;
		this.address = address;
		this.total = total;
		this.date = date;
		this.isDone = isDone;
		this.paymentMethod = paymentMethod;
	}
	
	
}
