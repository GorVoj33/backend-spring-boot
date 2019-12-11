package test.project.myproject.repository;

import org.springframework.data.repository.CrudRepository;

import test.project.myproject.domain.FinalOrder;

public interface FinalOrderRepository extends CrudRepository<FinalOrder, Long>{

}
