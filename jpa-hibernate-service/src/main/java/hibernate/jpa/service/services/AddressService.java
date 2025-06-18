package hibernate.jpa.service.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import hibernate.jpa.service.beans.jpql.Address;

public class AddressService {

	private EntityManager manager;
	private EntityTransaction transaction;
	
	public AddressService(EntityManager manager, EntityTransaction transaction) {
		this.manager = manager;
		this.transaction = transaction;
	}
	
	public void saveAddress(Address address) {
		
		transaction.begin();
		manager.persist(address);
		transaction.commit();
	}
	
	public void saveAllAddress(List<Address> address) {
		
		transaction.begin();
		for(Address adr:address)
			manager.persist(adr);
		transaction.commit();
	}
	
	public Address findAddress(int id) {
		
		return manager.find(Address.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Address> findAllAddress() {
		
		return manager.createQuery("from Address").getResultList();
	}

	public void updateAddress(Address address) {
		
		transaction.begin();
		
		Address addressOld = manager.find(Address.class, address.getId());
		
		addressOld.setAddress(address.getAddress());
		addressOld.setLandmark(address.getLandmark());
		addressOld.setState(address.getState());
		addressOld.setType(address.getType());
		addressOld.setZipcode(address.getZipcode());
		
		transaction.commit();
	}
	
	public void deleteAddress(int id) {
		transaction.begin();
		manager.remove(manager.find(Address.class, id));
		transaction.commit();
	}
}
