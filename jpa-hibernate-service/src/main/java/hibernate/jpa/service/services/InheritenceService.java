package hibernate.jpa.service.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import hibernate.jpa.service.beans.joins.GrandParent;
import hibernate.jpa.service.beans.joins.GrandParent1;
import hibernate.jpa.service.beans.joins.GrandParent2;
import hibernate.jpa.service.beans.joins.Parent;
import hibernate.jpa.service.beans.joins.Parent1;
import hibernate.jpa.service.beans.joins.Parent2;

public class InheritenceService {

	private EntityManager manager;
	private EntityTransaction transaction;
	
	public InheritenceService(EntityManager manager, EntityTransaction transaction) {
		this.manager = manager;
		this.transaction = transaction;
	}
	
	public void makeSingleTableInheritence() {
		
		transaction.begin();
		
		for(GrandParent p : getGrand())
			manager.persist(p);
		
		transaction.commit();
	}
	
	public void makeMultiTableInheritence() {
		
		transaction.begin();
		
		for(GrandParent1 p : getGrand1())
			manager.persist(p);
		
		transaction.commit();
	}
	
	public void makePerClassTableInheritence() {
		
		transaction.begin();
		
		for(GrandParent2 p : getGrand2())
			manager.persist(p);
		
		transaction.commit();
	}
	
	private List<GrandParent> getGrand() {
		
		List<GrandParent> lst = new ArrayList<>();
		GrandParent grand1 = new GrandParent("Late Mr Raheem", "Vehleen");
		
		GrandParent grand2 = new Parent("Hapheej");
		grand2.setSirname("Vahleen");
		
		lst.add(grand1);
		lst.add(grand2);
		
		return lst;
	}
	
	private List<GrandParent1> getGrand1() {
		
		List<GrandParent1> lst = new ArrayList<>();
		GrandParent1 grand1 = new GrandParent1("Late Mr Raheem", "Vehleen");
		
		Parent1 grand2 = new Parent1("Sheikh", "Mansoori");
		grand2.setSirname("Vahleen");
		grand2.setName("Hapheej");
		
		lst.add(grand1);
		lst.add(grand2);
		
		return lst;
	}
	
	private List<GrandParent2> getGrand2() {
		
		List<GrandParent2> lst = new ArrayList<>();
		GrandParent2 grand1 = new GrandParent2("Late Mr Raheem", "Vehleen");
		
		Parent2 grand2 = new Parent2("Sheikh", "Mansoori");
		grand2.setSirname("Vahleen");
		grand2.setName("Hapheej");
		
		lst.add(grand1);
		lst.add(grand2);
		
		return lst;
	}
}
