package test.project.myproject.rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import test.project.myproject.dao.CartDAO;
import test.project.myproject.dao.UserDAO;
import test.project.myproject.domain.Cart;
import test.project.myproject.domain.Product;
import test.project.myproject.domain.User;
import test.project.myproject.repository.CartRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserRestController {
	@Autowired
	UserDAO userDAO;
	@Autowired
	CartDAO cartDAO;
	@Autowired
	PasswordEncoder passwordEncoderBean;
	@GetMapping("/rest/users")
	public List<User> getUsers(){
		return userDAO.getAll();
		
	}
	@GetMapping("/rest/users/{id}")
	public User getUserById(@PathVariable("id") Long id){
		return userDAO.getById(id);
	}
	@GetMapping("/rest/users/filter/username?username={username}")
	public User getUserById(@PathVariable("username") String username){
		return userDAO.getByUsername(username);
	}
	@GetMapping("/rest/users/username/{username}")
	public User getUserByUsername(@PathVariable("username") String username){
		System.out.println("USERNAME: "+username);
		ArrayList<User> users = (ArrayList<User>) userDAO.getAll();
		for (User user : users) {
			if(user.getEmail().equals(username)) {
				return user;
			}
		}
		return null;
			
			
		}
	@GetMapping("/rest/cart/username/{username}")
	public Cart getCartByUsername(@PathVariable("username") String username){
		System.out.println("USERNAME: "+username);
		ArrayList<User> users = (ArrayList<User>) userDAO.getAll();
		for (User user : users) {
			if(user.getEmail().equals(username)) {
				if(user.getCart()!=null)
					return user.getCart();
			}
		}
		return null;
			
			
		}
	@PostMapping(value = "/rest/users/register")
	public ResponseEntity<Void> register(@RequestBody User user){
		System.out.println("Preparing to save new user...");
		Cart c = new Cart();
		
		cartDAO.save(c);
		
		user.setPassword(passwordEncoderBean.encode(user.getPassword()));
		user.setAdmin(false);
		user.setCart(c);
		c.setUser(user);
		User createdUser = userDAO.saveUser(user);
		
//		c.setUser(createdUser); ovo ovde kod usera upadetuje id ... nije dobro jer preskace po 1 id...
		//cartDAO.save(c);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@GetMapping(value = "/rest/users/{email}/{pass}")
	public boolean getPassword(@PathVariable("email") String email, @PathVariable("pass") String pass){
		User u = getUserByUsername(email);
		if (passwordEncoderBean.matches(pass, u.getPassword())) {
			return true;
		}
		return false;
	}
	
	
	@PutMapping("/rest/users/{id}")
	public ResponseEntity<User> updateUserCredentials(@PathVariable Long id, @RequestBody User user){
		System.out.println("Rezzultat: "+user.getId()+ ", "+user.getEmail() + " ,"+user.getPassword()+ " ,");
		user.setPassword(passwordEncoderBean.encode(user.getPassword()));
		user.setAdmin(false);;
		User updated = userDAO.saveUser(user);
		return new ResponseEntity<User>(updated, HttpStatus.OK);
		
	}
	
	
	
//	
//	{
//	    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJnb3JhbiIsImV4cCI6MTU3NTg4ODQwMCwiaWF0IjoxNTc1MjgzNjAwfQ.xh02z2mJ-6SrrFS6km4i4_z-RgbdVLGVXsZhs6x3cpS7BLxtkAyQMCdQ1IesUcy0Og0kbbT0HHrO5mpMju-ibQ"
//	}
	
}