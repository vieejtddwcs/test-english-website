package com.testenglish.dao;

import java.util.Collection;

public interface AbstractDAO<T> {

	public T select(int id);
	public Collection<T> selectAll();
	public int count();
	public void insert(T t);
	public void update(T t);
	public void delete(int id);
	
}