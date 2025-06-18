package hibernate.jpa.service.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import hibernate.jpa.service.beans.joins.Husband;
import hibernate.jpa.service.beans.joins.Relative;

public class OneToManyService {

	private EntityManager manager;
	private EntityTransaction transaction;
	
	public OneToManyService(EntityManager manager, EntityTransaction transaction) {
		this.manager = manager;
		this.transaction = transaction;
	}
	
	public void markRelation(Husband h) {
		
		transaction.begin();
		
		manager.persist(h.getWife());
		manager.flush();
		for(Relative r:h.getRelatives())
			manager.persist(r);
		
		manager.persist(h);
		manager.flush();
		transaction.commit();
		
		System.err.println("Relation Saved -> "+h);
	}
}
