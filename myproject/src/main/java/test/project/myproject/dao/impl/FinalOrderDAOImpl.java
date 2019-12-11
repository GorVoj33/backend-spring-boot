package test.project.myproject.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.project.myproject.dao.FinalOrderDAO;
import test.project.myproject.dao.OrderItemDAO;
import test.project.myproject.domain.FinalOrder;
import test.project.myproject.domain.OrderItem;
import test.project.myproject.repository.FinalOrderRepository;
import test.project.myproject.repository.OrderItemRepository;

@Component
public class FinalOrderDAOImpl implements FinalOrderDAO{
	@Autowired
	FinalOrderRepository finalOrderRepository;

	public FinalOrder save(FinalOrder fo) {
		return finalOrderRepository.save(fo);	
	}
	@Override
	public List<FinalOrder> getAll() {
		return (List<FinalOrder>) finalOrderRepository.findAll();
	}
	@Override
	public FinalOrder getById(Long id) {
		Optional<FinalOrder> opt = finalOrderRepository.findById(id);
		if(opt.isPresent()) return opt.get();
		return null;
	}
	
}
