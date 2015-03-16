package com.tatsuyaoiw.repository.strategy;

import com.tatsuyaoiw.entity.Entity;

import java.util.List;

public class FileStrategy extends RepositoryStrategy {
	@Override
	public Entity add(Entity entity) {
		return null;
	}

	@Override
	public List list() {
		return null;
	}

	@Override
	public boolean remove(String id) {
		return false;
	}
}
