package hibernate.jpa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import hibernate.jpa.service.beans.jpql.Address;
import hibernate.jpa.service.beans.jpql.JPQLEntity;
import hibernate.jpa.service.services.AddressService;
import hibernate.jpa.service.services.CriteriaApiService;
import hibernate.jpa.service.services.InheritenceService;
import hibernate.jpa.service.services.JPQLEntityService;
import hibernate.jpa.service.services.JoinsJPQLService;

public class HibernateJpaApplication 
{
	private EntityManagerFactory factory;
	private EntityManager manager;
	private EntityTransaction transaction;
	
	private AddressService addressService;
	private JPQLEntityService jpqlService;
	private JoinsJPQLService joinsService;
	private InheritenceService inheritenceService;
	private CriteriaApiService criteriaService;
	
    public static void main( String[] args )
    {
    	HibernateJpaApplication app = new HibernateJpaApplication();
    	
    	app.initJPA();
    	
    	
    	app.processAddress();
    	
    	app.processJPQLEntity();
    	
    	app.processJoins();
    	
    	app.processInheritence();
    	
    	app.processCriteriaQuery();
    	
    	
    	
    	app.close();
    }

    public void processCriteriaQuery() {
    	
    	criteriaService = new CriteriaApiService(manager, transaction);
    	
    	//Order By Desc Address
    	List<Address> adrs = criteriaService.getAddressList();
    	System.err.println(adrs);
    }
    
    public void processJoins() {
    	
    	joinsService = new JoinsJPQLService(manager, transaction);
    	
    	joinsService.oneToOne();

    	joinsService.oneToMany();
    	
    	joinsService.manyToOne();
    	
    	joinsService.manyToMany();
    }
    
    
    public void processInheritence() {
    	
    	inheritenceService = new InheritenceService(manager, transaction);
    	
    	inheritenceService.makeSingleTableInheritence();
    	
    	inheritenceService.makeMultiTableInheritence();
    	
    	inheritenceService.makePerClassTableInheritence();
    }
    
    
    public void processJPQLEntity() {
    	
    	jpqlService = new JPQLEntityService(manager, transaction);
    	
    	JPQLEntity entity0 = new JPQLEntity("Arham", 975381);
    	jpqlService.saveEntity(entity0);
    	
    	JPQLEntity entity1 = new JPQLEntity("Arham", 975381);
    	entity1.setLocation("Narsinghpur");
    	JPQLEntity entity2 = new JPQLEntity("Arham", 975381);
    	entity2.setLocation("Harrai");
    	JPQLEntity entity3 = new JPQLEntity("Arham", 975381);
    	entity3.setLocation("Harrai");
    	
    	jpqlService.saveEntity(entity1);
    	jpqlService.saveEntity(entity2);
    	jpqlService.saveEntity(entity3);
    	

    	JPQLEntity entity = jpqlService.findById(1);
    	System.out.println(entity);
    	
    	List<String> firstNames = jpqlService.findAllFirstName();
    	System.out.println(firstNames);
    	
    	List<JPQLEntity> entities = jpqlService.findByFirstName("Arham");
    	System.out.println(entities);
    	
    	entities = jpqlService.findByFirstNameAndLocation("Arham", "Harrai");
    	System.out.println(entities);
    	
    	JPQLEntity entity4 = new JPQLEntity("Arham", 975381);
    	entity4.setMobile(6523);
    	entity4.setLocation("Pune");
    	
    	jpqlService.updateJPQLEntity(entity4, 1);
    	JPQLEntity entity5 = jpqlService.updateUsingJPQLQuery("Harrai", 2);
    	System.out.println(entity5);
    	
    	//String status = jpqlService.deleteEntityJPQL(43);
    	//System.out.println(status);
    	
    	entities = jpqlService.findByFirstNameLike("Arham");
    	entities = jpqlService.findByFirstNameLike("");
    	System.out.println(entities);
    	
    	entities = jpqlService.findOrderByFirstNameDesc();
    	entities = jpqlService.findOrderByFirstNameAsc();
    	System.out.println(entities);
    }
    
    public void processAddress() {
    	
    	addressService = new AddressService(manager, transaction);
    	
    	Address address = new Address("Tempo1", "Shyam Talkeej", "Narsinghpur", 222);
    	
    	addressService.saveAddress(address);
    	address = addressService.findAddress(1);
    	System.out.println(address);
    	
    	address.setState("MP");
    	addressService.updateAddress(address);
    	
    	List<Address> adrs = addressService.findAllAddress();
    	System.out.println(adrs);
    	adrs.clear();
    	Address address1 = new Address("Tempo1", "Shyam Talkeej", "Narsinghpur", 222);
    	adrs.add(address1);
    	adrs.add(address);
    	addressService.saveAllAddress(adrs);
    }
    
	private void initJPA() {
		
		factory = Persistence.createEntityManagerFactory("jpa");
    	manager = factory.createEntityManager();
    	transaction = manager.getTransaction();
	}
	
    public void close() {
    	
    	if(null!=manager || manager.isOpen())
    		manager.close();
    	if(null!=factory || factory.isOpen())
    		factory.close();
    }
}
