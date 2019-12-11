package test.project.myproject.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import test.project.myproject.domain.User;
import test.project.myproject.repository.UserRepository;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  //static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();
 
//  static {
//    inMemoryUserList.add(new JwtUserDetails(2L, "goran",
//            "$2y$12$knXYF3zPtX9pPdsnSyR71OII7ut7Im3AheASDd5vEz2Au59So9Rn6", "ROLE_USER_2", 3L, "iME2"));
//    inMemoryUserList.add(new JwtUserDetails(3L, "goranvojnovic96@gmail.com",
//            "$2y$12$eWSElSHLsAE2ueIgjPGCje5lEDMHGqbhpnEjRzQxhyJE1ofYbgOkK", "ROLE_USER_2", 4L, "iME3"));
//  }
	@Autowired
	UserRepository userRepository;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  System.out.println("Pretraga po username: "+username);
//	   Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
//	           .filter(user -> user.getUsername().equals(username)).findFirst();
//
//	   if (!findFirst.isPresent()) {
//	            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
//	   }
//
//	   return findFirst.get();
	
	List<User> allUsers = getAllUsers();
	User user = null;
	for (User u : allUsers) {
		if(u.getEmail().equals(username))
			user = u;
	}
	System.out.println("Ovo je user: "+user.getEmail()+" pass: "+user.getPassword() + " ime:"+user.getName());
	if (user == null) throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
	

	String role = user.isAdmin() ? "admin" : "user";
	Long cartId = user.getCart()!=null ? user.getCart().getId() : 0;
	UserDetails ud = new JwtUserDetails(user.getId(), user.getEmail(), user.getPassword(), role, cartId , user.getName());
    return ud;
  }
	private List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

}


