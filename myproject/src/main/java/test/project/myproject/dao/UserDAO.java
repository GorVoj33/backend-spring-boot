package test.project.myproject.dao;

import java.util.List;

import test.project.myproject.domain.User;

public interface UserDAO {
	User saveUser(User u);
	List<User> getAll();
	User getById(Long id);
	User getByUsername(String username);
}
