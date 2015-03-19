package com.tatsuyaoiw.strategy;

import com.tatsuyaoiw.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStrategy<T extends Entity> implements RepositoryStrategy<T> {

	private final Map<String, T> storage = new ConcurrentHashMap<String, T>();

	@Override
	public T add(T entity) {
		String id = UUID.randomUUID().toString();
		entity.setId(id);
		storage.put(id, entity);
		return entity;
	}

	@Override
	public List<T> list() {
		return new ArrayList<T>(storage.values());
	}

	@Override
	public T get(String id) {
		return storage.get(id);
	}

	@Override
	public T update(T entity) {
		storage.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public boolean remove(String id) {
		return storage.remove(id) != null;
	}

}
