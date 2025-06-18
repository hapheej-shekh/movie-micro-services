package hibernate.jpa.service.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import hibernate.jpa.service.beans.joins.Child;
import hibernate.jpa.service.beans.joins.Relative;

public class ManyToOneService {

	private EntityManager manager;
	private EntityTransaction transaction;
	
	public ManyToOneService(EntityManager manager, EntityTransaction transaction) {
		this.manager = manager;
		this.transaction = transaction;
	}
	
	public void completeFamily(List<Child> childs) {
		
		transaction.begin();
		
		manager.persist(childs.get(0).getFather().getWife());
		
		for(Relative r: childs.get(0).getFather().getRelatives())
			manager.persist(r);
		
		manager.persist(childs.get(0).getFather());
		
		for(Child c : childs)
			manager.persist(c);
		
		transaction.commit();
		
		System.out.println("Family Saved--> "+childs);
	}
}
