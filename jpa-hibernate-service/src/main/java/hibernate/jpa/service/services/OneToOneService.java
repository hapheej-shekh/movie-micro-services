package hibernate.jpa.service.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import hibernate.jpa.service.beans.joins.Husband;

public class OneToOneService {

	private EntityManager manager;
	private EntityTransaction transaction;
	
	public OneToOneService(EntityManager manager, EntityTransaction transaction) {
		this.manager = manager;
		this.transaction = transaction;
	}
	
	public void addCouple(Husband h) {
		
		saveWife(h);
		
		transaction.begin();
		manager.persist(h);
		transaction.commit();
		
		System.out.println("Couple Saved--> "+h);
	}
	
	private void saveWife(Husband h) {
		transaction.begin();
		manager.persist(h.getWife());
		transaction.commit();
	}
	
	public void breakOneToOne(Husband h) {
		
		transaction.begin();
		manager.persist(h.getWife());
		manager.persist(h);
		transaction.commit();
		
		System.out.println("Couple Saved--> "+h);
	}
}
