package test.project.myproject.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.project.myproject.dao.OrderItemDAO;
import test.project.myproject.domain.OrderItem;
import test.project.myproject.repository.OrderItemRepository;

@Component
public class OrderItemDAOImpl implements OrderItemDAO{
	@Autowired
	OrderItemRepository orderItemRepository;

	@Override
	public OrderItem save(OrderItem oi) {
		return orderItemRepository.save(oi);
	}

	@Override
	public List<OrderItem> getAll() {
		return (List<OrderItem>) orderItemRepository.findAll();
	}

	@Override
	public OrderItem getById(Long id) {
		Optional<OrderItem> opt = orderItemRepository.findById(id);
		if(opt.isPresent()) return opt.get();
		return null;
	}
}
