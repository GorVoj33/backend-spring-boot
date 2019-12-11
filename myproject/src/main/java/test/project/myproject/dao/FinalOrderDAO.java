package test.project.myproject.dao;

import java.util.List;

import test.project.myproject.domain.FinalOrder;
import test.project.myproject.domain.OrderItem;

public interface FinalOrderDAO {
	FinalOrder save(FinalOrder fo);
	List<FinalOrder>getAll();
	FinalOrder getById(Long id);
}
