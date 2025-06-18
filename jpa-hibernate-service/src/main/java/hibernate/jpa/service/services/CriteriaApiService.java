package hibernate.jpa.service.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import hibernate.jpa.service.beans.jpql.Address;

public class CriteriaApiService {

	private EntityManager manager;
	private EntityTransaction transaction;
	private CriteriaBuilder criteria;
	private CriteriaQuery<Address> criteriaQuery;
	private Root<Address> rootAddress;
	
	public CriteriaApiService(EntityManager manager, EntityTransaction transaction) {
		this.manager = manager;
		this.transaction = transaction;
		
		criteria = manager.getCriteriaBuilder();
		criteriaQuery = criteria.createQuery(Address.class);
		rootAddress = criteriaQuery.from(Address.class);
	}
	
	public List<Address> getAddressList(){
		
		criteriaQuery.select(rootAddress.get("type"));
		
		criteriaQuery.distinct(true);
		criteriaQuery.orderBy(criteria.desc(rootAddress.get("address")));
		
		
		CriteriaQuery<Address> select = criteriaQuery.select(rootAddress);
		
		TypedQuery<Address> query = manager.createQuery(select);
		
		return query.getResultList();
	}
}
