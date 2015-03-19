package com.tatsuyaoiw.strategy;

import com.tatsuyaoiw.entity.Entity;

import java.util.List;

public interface RepositoryStrategy<T extends Entity> {

	T add(T entity);

	List<T> list();

	T get(String id);

	T update(T entity);

	boolean remove(String id);

}
