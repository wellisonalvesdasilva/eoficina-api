package com.eoficina.api.dao;

import java.util.List;

import com.eoficina.api.dto.DtoSearchPaginated;

public interface GenericDAO<T> {
	  
	T loadById(Integer id);

	void persist(T entity);
	
	void merge(T entity);

	void delete(T entity);
	
	List<T> loadAll();
	
	List<T> loadAll(String order);

	void createCriteria();

	List<T> getResultList();	

}
