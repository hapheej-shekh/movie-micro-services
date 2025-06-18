package hibernate.jpa.service.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hibernate.jpa.service.beans.jpql.Address;

public class AddressServiceTest {

	private static AddressService addressService;
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	
	@BeforeAll
	public static void beforeSetup() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa");
		EntityManager manager = factory.createEntityManager();
		transaction = manager.getTransaction();
		addressService = new AddressService(manager, transaction);
	}

	@BeforeEach
	public void setUp() {
		
	}
	
	@AfterAll
	public static void afterSetup() {
		if(null!=manager)
			manager.close();
		if(null!=factory)
			factory.close();
	}
	
	@Test
	public void saveAddress() {
		isActive();
		Address address = new Address("Tempo1", "Shyam Talkeej", "Narsinghpur", 222);
		addressService.saveAddress(address);
		assertNotNull(address.getId());
		assertTrue(address.getId()>0);
	}
	
	@Test
	public void saveAllAddress() {
		isActive();
		Address address1 = new Address("Tempo1", "Shyam Talkeej", "Narsinghpur", 222);
		Address address2 = new Address("Tempo1", "Shyam Talkeej", "Narsinghpur", 222);
		List<Address> adrs = new ArrayList<>();
		adrs.add(address1);
		adrs.add(address2);
		addressService.saveAllAddress(adrs);
		
		assertNotNull(adrs);
		assertTrue(adrs.size()>=2);
	}
	
	@Test
	public void findAddress() {
		isActive();
		Address address = addressService.findAddress(1);
		assertNotNull(address);
	}

	@Test
	public void findAllAddress() {
		isActive();
		List<Address> adrs = addressService.findAllAddress();
		assertNotNull(adrs);
		assertTrue(!adrs.isEmpty());
	}

	@Test
	public void updateAddress() {
		isActive();
		Address address = new Address(1,"Tempo1", "Shyam Talkeej", "Narsinghpur", 222);
		address.setState("UP");
		address.setZipcode(666);
		address.setLandmark("Agrawal Hospital");
		addressService.updateAddress(address);
		
		address = addressService.findAddress(address.getId());
		assertTrue(address.getLandmark()=="Agrawal Hospital");
	}
	
	@Test
	public void deleteAddress() {
		isActive();
		int numberOfAddress = addressService.findAllAddress().size();
		
		if(numberOfAddress==0) {
			Address address1 = new Address("Tempo1", "Shyam Talkeej", "Narsinghpur", 222);
			Address address2 = new Address("Tempo1", "Shyam Talkeej", "Narsinghpur", 222);
			addressService.saveAddress(address1);
			addressService.saveAddress(address2);
		}
		addressService.deleteAddress(2);
		
		int numberOfAddressAfterDelete = addressService.findAllAddress().size();
		assertTrue(numberOfAddress!=numberOfAddressAfterDelete);
	}
	
	private void isActive() {
		
		if(transaction.isActive())
			transaction.commit();
	}
}
