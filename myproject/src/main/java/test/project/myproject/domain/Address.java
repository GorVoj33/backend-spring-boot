package test.project.myproject.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Address {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "full_address")
	private String address;
	private String city;
	@Column(name = "contact_phone")
	private String contact;
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setFullAddress(String fullAddress) {
		this.address = fullAddress;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contactPhone) {
		this.contact = contactPhone;
	}

	
	
	
}
