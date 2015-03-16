package com.tatsuyaoiw.repository.strategy;

import com.tatsuyaoiw.entity.Entity;

import java.util.List;

public abstract class RepositoryStrategy<T extends Entity> {

	public abstract T add(T object);

	public abstract List<T> list();

	public abstract boolean remove(String id);
}
