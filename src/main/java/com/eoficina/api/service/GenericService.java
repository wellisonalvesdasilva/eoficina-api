package com.eoficina.api.service;

import java.util.List;


public interface GenericService<T> {

	public List<T> getAll();

	public T getById(Integer id);

	public void created(T object);

	public boolean deleteById(Integer id);

	public boolean updatedById(Integer id, T object);

}
