package com.tatsuyaoiw.repository.strategy;

import com.tatsuyaoiw.entity.Entity;

import java.util.List;
import java.util.UUID;

public class FileStrategy<T extends Entity> extends RepositoryStrategy<T> {

	@Override
	public void init() {
		// TODO
	}

	@Override
	public T add(T entity) {
		String id = UUID.randomUUID().toString();
		entity.setId(id);
		return entity;
	}

	@Override
	public List<T> list() {
		return null;
	}

	@Override
	public T get(String id) {
		return null;
	}

	@Override
	public T update(T entity) {
		return null;
	}

	@Override
	public boolean remove(String id) {
		return false;
	}

}
