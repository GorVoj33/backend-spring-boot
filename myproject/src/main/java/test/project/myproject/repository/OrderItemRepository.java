package test.project.myproject.repository;

import org.springframework.data.repository.CrudRepository;

import test.project.myproject.domain.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long>{

}
