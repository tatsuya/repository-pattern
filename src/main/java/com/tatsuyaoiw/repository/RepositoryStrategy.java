package com.tatsuyaoiw.repository;

import com.tatsuyaoiw.entity.Entity;

import java.util.List;

public abstract class RepositoryStrategy<T extends Entity> {

	abstract T add(T entity);

	abstract List<T> list();

	abstract T get(String id);

	abstract T update(T entity);

	abstract boolean remove(String id);

}
