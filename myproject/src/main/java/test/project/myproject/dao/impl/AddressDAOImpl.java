package test.project.myproject.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import test.project.myproject.dao.AddressDAO;
import test.project.myproject.domain.Address;
import test.project.myproject.repository.AddressRepository;
@Component
public class AddressDAOImpl implements AddressDAO{
	@Autowired
	AddressRepository addressRep;
	@Override
	public Address save(Address a) {
		return addressRep.save(a);
	}

}
