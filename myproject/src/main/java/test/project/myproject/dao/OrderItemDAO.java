package test.project.myproject.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import test.project.myproject.domain.OrderItem;
import test.project.myproject.repository.OrderItemRepository;

public interface OrderItemDAO {
	OrderItem save(OrderItem oi);
	List<OrderItem> getAll();
	OrderItem getById(Long id);
	
	
}
