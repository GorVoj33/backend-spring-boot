package test.project.myproject.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.project.myproject.dao.UserDAO;
import test.project.myproject.domain.User;
import test.project.myproject.repository.UserRepository;
@Component
public class UserDAOImpl implements UserDAO{

	@Autowired
	UserRepository userRepository;
	@Override
	public User saveUser(User u) {
		User createdUser = userRepository.save(u);
		return createdUser;
	}
	@Override
	public List<User> getAll() {
		return (List<User>) userRepository.findAll();
	}
	@Override
	public User getById(Long id) {
		Optional<User> u = userRepository.findById(id);
		if(u.isPresent()) return u.get();
		return null;
	}
	@Override
	public User getByUsername(String username) {
		
		return (User) userRepository.getByUsername(username);
	}
	
}
