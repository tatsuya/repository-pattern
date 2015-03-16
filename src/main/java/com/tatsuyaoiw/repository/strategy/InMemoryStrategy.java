package com.tatsuyaoiw.repository.strategy;

import com.tatsuyaoiw.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStrategy<T extends Entity> extends RepositoryStrategy<T> {

	private final Map<String, T> storage = new ConcurrentHashMap<String, T>();

	@Override
	public T add(T object) {
		String id = UUID.randomUUID().toString();
		object.setId(id);
		storage.put(id, object);
		return object;
	}

	@Override
	public List<T> list() {
		return new ArrayList<T>(storage.values());
	}

	@Override
	public boolean remove(String id) {
		return storage.remove(id) != null;
	}
}
