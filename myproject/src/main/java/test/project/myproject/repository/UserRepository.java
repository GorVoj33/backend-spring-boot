package test.project.myproject.repository;
import test.project.myproject.domain.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long>{
	@Query("from user_profile u WHERE u.email=:username")
	public Iterable<User> getByUsername(@Param("username") String username);
	

}
