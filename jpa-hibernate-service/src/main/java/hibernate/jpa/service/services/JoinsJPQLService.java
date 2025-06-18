package hibernate.jpa.service.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import hibernate.jpa.service.beans.joins.Child;
import hibernate.jpa.service.beans.joins.Husband;
import hibernate.jpa.service.beans.joins.Relative;
import hibernate.jpa.service.beans.joins.Wife;

public class JoinsJPQLService {

	private OneToOneService oneToOne;
	private OneToManyService oneToMany;
	private ManyToOneService manyToOne;
	private ManyToManyService manyToMany;
	
	public JoinsJPQLService() {	}
	
	public JoinsJPQLService(EntityManager manager, EntityTransaction transaction) {
		
		oneToOne = new OneToOneService(manager, transaction);
		oneToMany = new OneToManyService(manager, transaction);
		manyToOne = new ManyToOneService(manager, transaction);
		manyToMany = new ManyToManyService(manager, transaction);
	}
	
	public void oneToOne() {
		
		Husband h = getCouple();
		
		oneToOne.addCouple(h);
		
		addWife(h);
		
		/** It will replace new wife [One To One will not Break] */
		//oneToOne.breakOneToOne(h);
	}
	
	public void oneToMany() {
		
		Husband h = addRelatives(getCouple());
		
		oneToMany.markRelation(h);
	}

	public void manyToOne() {
		
		List<Child> h = addChilds(addRelatives(getCouple()));
		
		manyToOne.completeFamily(h);
	}

	public void manyToMany() {
		
		manyToMany.gennerateChildCroud(getChildsFamilyDetails());
		
		manyToMany.gennerateRelativeCroud(getRelativesFamily());
	}
	
	
	private Husband getCouple() {
		
		Husband h = new Husband();
		h.setName("Hapheej");
		h.getMarry();
		
		return h;
	}
	
	private Husband addWife(Husband h) {
	
		Wife w = new Wife("Aastana");
		h.setWife(w);
		
		return h;
	}
	
	private Husband addRelatives(Husband h) {
		
		List<Relative> relatives = new ArrayList<>();
		
		relatives.add(new Relative("Rafiq", "Brother"));
		relatives.add(new Relative("Sahil", "Brother"));
		
		h.setRelatives(relatives);
		
		return h;
	}
	
	private List<Child> addChilds(Husband h) {
		
		List<Child> chlds = new ArrayList<>();
		Child c1 = new Child("Arham", h);
		c1.setFather(h);
		Child c2 = new Child("Aenam", h);
		c2.setFather(h);
		chlds.add(c2);chlds.add(c1);
		
		return chlds;
	}
	
	private Husband getFather() {
		
		Husband h = new Husband();
		h.setName("Hapheej");
		
		return h;
	}
	
	private List<Relative> getRelativesFamily() {
		
		List<Relative> relatives = new ArrayList<>();
		List<Child> childs = new ArrayList<>();
		
		childs.add(new Child("Arham", getFather()));
		childs.add(new Child("Aenam", getFather()));
		childs.add(new Child("Dummy1", getFather()));
		childs.add(new Child("Dummy2", getFather()));
		
		Relative r1 = new Relative("Rafiq", "Uncle");
		Relative r2 = new Relative("Sahil", "Uncle");
		Relative r3 = new Relative("Haleema", "Fufi");	
		
		r1.setChildren(childs);
		r2.setChildren(childs);
		r3.setChildren(childs);
		
		relatives.add(r1);
		relatives.add(r2);
		relatives.add(r3);
		
		return relatives;
	}
	
	private List<Child> getChildsFamilyDetails() {
		
		List<Relative> relatives = new ArrayList<>();
		List<Child> childs = new ArrayList<>();
		
		Child c1 = new Child("Arham", getFather());
		Child c2 = new Child("Aenam", getFather());
		Child c3 = new Child("Dummy1", getFather());
		Child c4 = new Child("Dummy2", getFather());
		
		relatives.add(new Relative("Rafiq", "Uncle"));
		relatives.add(new Relative("Sahil", "Uncle"));
		relatives.add(new Relative("Haleema", "Fufi"));	

		c1.setRelatives(relatives);
		c2.setRelatives(relatives);
		//c3.setRelatives(relatives);
		c4.setRelatives(relatives);
		
		childs.add(c1);
		childs.add(c2);
		childs.add(c3);
		childs.add(c4);
		
		return childs;
	}
}
