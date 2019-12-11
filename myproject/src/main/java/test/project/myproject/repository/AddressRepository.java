package test.project.myproject.repository;

import org.springframework.data.repository.CrudRepository;

import test.project.myproject.domain.Address;

public interface AddressRepository extends CrudRepository<Address, Long>{

}
