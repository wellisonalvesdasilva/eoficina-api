package com.eoficina.api.daoImpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.eoficina.api.dao.GenericDAO;

public abstract class AbstractJpaDAOImpl<T extends Serializable> implements GenericDAO<T> {

	private Class<T> persistentClass;

	@PersistenceContext
	protected EntityManager entityManager;

	protected CriteriaBuilder cb;

	protected CriteriaQuery<T> query;

	protected Root<T> root;

	@SuppressWarnings("unchecked")
	public AbstractJpaDAOImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public T loadById(Integer id) {
		return entityManager.find(persistentClass, id);
	}

	public void persist(T entity) {
		entityManager.persist(entity);
	}

	public void merge(T entity) {
		entityManager.merge(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entity);
	}

	@SuppressWarnings("unchecked")
	public List<T> loadAll() {
		return entityManager.createQuery("Select t from " + persistentClass.getSimpleName() + " t").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> loadAll(String order) {
		order = order != null && !order.equals("") ? " order by t." + order : "";
		return entityManager.createQuery("Select t from " + persistentClass.getSimpleName() + " t" + order)
				.getResultList();
	}

	public void createCriteria() {
		this.cb = this.entityManager.getCriteriaBuilder();
		this.query = cb.createQuery(persistentClass);
		this.root = query.from(persistentClass);
	}

	public List<T> getResultList() {
		TypedQuery<T> result = this.entityManager.createQuery(query);
		return result.getResultList();
	}

	public List<T> getResultListMax(Integer max) {
		TypedQuery<T> result = this.entityManager.createQuery(query);
		result.setFirstResult(0).setMaxResults(max);
		return result.getResultList();
	}

	public List<T> getResultListPagging(int offset, int limit) {
		TypedQuery<T> result = this.entityManager.createQuery(query);
		result.setFirstResult(offset).setMaxResults(limit);
		return result.getResultList();
	}

	public int getCount() {
		TypedQuery<T> result = this.entityManager.createQuery(query);
		return (Integer) result.getSingleResult();
	}

}