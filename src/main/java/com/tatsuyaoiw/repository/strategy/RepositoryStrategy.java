package com.tatsuyaoiw.repository.strategy;

import com.tatsuyaoiw.entity.Entity;

import java.util.List;

public abstract class RepositoryStrategy<T extends Entity> {

	public void init() {}

	public abstract T add(T entity);

	public abstract List<T> list();

	public abstract T get(String id);

	public abstract T update(T entity);

	public abstract boolean remove(String id);
}
