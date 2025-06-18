package hibernate.jpa.service.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import hibernate.jpa.service.beans.jpql.JPQLEntity;

public class JPQLEntityService {

	private EntityManager manager;
	private EntityTransaction transaction;
	
	
	public JPQLEntityService(EntityManager manager, EntityTransaction transaction) {
		this.manager = manager;
		this.transaction = transaction;
	}
	
	//Named JPQL Query
	public JPQLEntity findById(int id) {
		
		Query query = manager.createNamedQuery("find by id");
		query.setParameter("id", id);
		
		JPQLEntity entity = (JPQLEntity)query.getSingleResult();
		return entity;
	}
	
	//Named JPQL Query
	public JPQLEntity updateJPQLEntity(JPQLEntity entity, int id) {
		
		transaction.begin();
		Query query = manager.createNamedQuery("find by id");
		query.setParameter("id", id);
		
		JPQLEntity oldEntity = (JPQLEntity)query.getSingleResult();
		
		oldEntity.setMobile(entity.getMobile());
		oldEntity.setLocation(entity.getLocation());
		transaction.commit();
		
		return oldEntity;
	}
	
	//JPQL Queries
	/** Here findById returns cache data from entityManager, So need to clear entityManager */
	public JPQLEntity updateUsingJPQLQuery(String location, int id) {
		
		transaction.begin();
		Query query = manager.createQuery("update JPQLEntity set location='"+location+"' where id="+id);
		query.executeUpdate();
		transaction.commit();
		manager.clear();
		return findById(id);
	}
	
	//JPQL Queries
	@SuppressWarnings("unchecked")
	public List<JPQLEntity> findByFirstName(String firstName) {
		
		Query query = manager.createQuery("select e from JPQLEntity e where e.firstName='"+firstName+"'");
		List<JPQLEntity> firstNames = query.getResultList();
		
		return firstNames;
	}

	//JPQL Queries
	@SuppressWarnings("unchecked")
	public List<String> findAllFirstName() {
		
		Query query = manager.createQuery("select e.firstName from JPQLEntity e");
		List<String> firstNames = query.getResultList();
		
		return firstNames;
	}
	
	//JPQL Queries
	@SuppressWarnings("unchecked")
	public List<JPQLEntity> findByFirstNameAndLocation(String firstName, String location) {
		
		Query query = manager.createQuery("select e from JPQLEntity e where e.firstName='"+
				firstName+"' and location='"+location+"'");
		List<JPQLEntity> firstNames = query.getResultList();
		
		return firstNames;
	}
	
	//JPQL Queries
	public String deleteEntityJPQL(int id) {
		
		transaction.begin();
		Query query = manager.createQuery("delete from JPQLEntity where id="+id);
		query.executeUpdate();
		transaction.commit();
		
		return "Entity deleted with id "+id;
	}
	
	//JPQL Queries
	public List<JPQLEntity> findByFirstNameLike(String keyword) {
		
		Query query = manager.createQuery("select J from JPQLEntity J where J.firstName like '"+keyword+"%'");
		List<JPQLEntity> firstNames = query.getResultList();
		
		return firstNames;
	}
	
	//JPQL Queries
	public List<JPQLEntity> findByLastNameEndsLike(String keyword) {
		
		Query query = manager.createQuery("select J from JPQLEntity J where J.lastName like '%"+keyword+"'");
		List<JPQLEntity> firstNames = query.getResultList();
		
		return firstNames;
	}
	
	//JPQL Queries
	public List<JPQLEntity> findOrderByFirstNameAsc() {
		
		Query query = manager.createQuery("select J from JPQLEntity J order by J.firstName");
		List<JPQLEntity> entity = query.getResultList();
		
		return entity;
	}
	
	//JPQL Queries
	public List<JPQLEntity> findOrderByFirstNameDesc() {
		
		Query query = manager.createQuery("select J from JPQLEntity J order by J.firstName desc");
		List<JPQLEntity> entity = query.getResultList();
		
		return entity;
	}
	
	//JPQL Queries
	public int count() {
		Query query = manager.createQuery("select count(J) from JPQLEntity J");

		return (Integer)query.getSingleResult();
	}
	
	public void saveEntity(JPQLEntity entity) {
		
		transaction.begin();
		manager.persist(entity);
		transaction.commit();
	}
	
	public void deleteEntity(int id) {
		transaction.begin();
		manager.remove(manager.find(JPQLEntity.class, id));
		transaction.commit();
	}
}
