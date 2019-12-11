package test.project.myproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="order_item")
public class OrderItem {
	@Id
	@GeneratedValue
	Long id;
	double total;
	int quantity;
	@ManyToOne
    @JoinColumn(name = "product_id")
	Product product;
	@ManyToOne
    @JoinColumn(name = "order_id")
	FinalOrder finalOrder;
	
	public OrderItem() {
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public FinalOrder getFinalOrder() {
		return finalOrder;
	}

	public void setFinalOrder(FinalOrder order) {
		this.finalOrder = order;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
