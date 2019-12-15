package com.eoficina.api.serviceImpl;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.eoficina.api.dao.GenericDAO;
import com.eoficina.api.dto.DtoReturnPaginated;
import com.eoficina.api.service.GenericService;

public abstract class AbstractServiceImpl<T extends Serializable> implements GenericService<T> {

	@Autowired
	GenericDAO<T> _DAO;

	@Transactional
	public T getById(Integer id) {
		return _DAO.loadById(id);
	}

	@Transactional
	public boolean deleteById(Integer id) {
		T objectSearch = _DAO.loadById(id);
		if (objectSearch != null) {
			_DAO.delete(objectSearch);
			return true;
		}
		return false;
	}

	@Transactional
	public List<T> getAll() {
		List<T> lista = _DAO.loadAll();
		return lista;
	}

	@Transactional
	public void created(T object) {
		_DAO.persist(object);
	}

	@Transactional
	public boolean updatedById(Integer id, T object) {
		T objectDatabase = _DAO.loadById(id);
		if (objectDatabase != null) {
			_DAO.merge(object);
			return true;
		}
		return false;
	}
}