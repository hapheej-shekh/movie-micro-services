package hibernate.jpa.service.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import hibernate.jpa.service.beans.joins.Child;
import hibernate.jpa.service.beans.joins.Relative;

public class ManyToManyService {

	private EntityManager manager;
	private EntityTransaction transaction;
	
	public ManyToManyService(EntityManager manager, EntityTransaction transaction) {
		this.manager = manager;
		this.transaction = transaction;
	}
	
	public void gennerateRelativeCroud(List<Relative> relatives) {
		
		transaction.begin();
		
		for(Relative r : relatives) {
			
			for(Child c : r.getChildren()) {
				manager.persist(c.getFather());
				manager.persist(c);
			}
			
			manager.persist(r);
		}
		
		transaction.commit();
	}
	
	public void gennerateChildCroud(List<Child> childs) {
		
		transaction.begin();
		
		for(Child c : childs) {
			
			manager.persist(c.getFather());
			
			for(Relative r : c.getRelatives())
				manager.persist(r);
			
			manager.persist(c);
		}
		transaction.commit();
	}
}
