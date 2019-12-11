package test.project.myproject.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import test.project.myproject.domain.Cart;


public interface CartRepository extends CrudRepository<Cart, Long>{
//	@Query("SELECT c FROM cart c WHERE c.user_id=:id")
//	Cart getCartByUserId(@Param("id") Long id);
	
	
//	@Query("SELECT * FROM cart c JOIN user_profile u ON (c.user_id=u.id)")
//	Cart
}
