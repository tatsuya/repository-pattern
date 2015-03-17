package com.tatsuyaoiw.repository;

import com.tatsuyaoiw.entity.Entity;
import com.tatsuyaoiw.util.JsonUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FileStrategy<T extends Entity> extends RepositoryStrategy<T> {

	private static final File TMP_DIR = new File(System.getProperty("java.io.tmpdir"));
	private final Class<T> type;
	private final File collection;
	private final Map<String, T> storage = new HashMap<String, T>();

	public FileStrategy(Class<T> type) {
		this(type, Long.toString(System.currentTimeMillis()));
	}

	public FileStrategy(Class<T> type, String collection) {
		this.type = type;
		this.collection = new File(TMP_DIR, collection);

		readFileStorage();
	}

	private void readFileStorage() {
		if (!collection.exists()) {
			if (!collection.mkdir()) {
				throw new IllegalStateException("Failed to create directory to " + TMP_DIR);
			}
		}
		if (!collection.isDirectory()) {
			throw new IllegalStateException("Unable to create directory to " + TMP_DIR + " because file already exists");
		}
		File[] files = collection.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile()) {
					String json = readFile(file);
					storage.put(file.getName(), JsonUtils.deserialize(json, type));
				}
			}
		}
	}

	private String readFile(File file) {
		try {
			byte[] encoded = Files.readAllBytes(file.toPath());
			return new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new IllegalStateException("Unable to read file: " + file, e);
		}
	}

	private File createFile(String id) {
		return new File(collection, id);
	}

	private void saveFile(File file, String content) {
		try {
			Files.write(file.toPath(), content.getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			throw new IllegalStateException("Unable to create file: " + file, e);
		}
	}

	private void deleteFile(File file) {
		if (file.exists()) {
			if (!file.delete()) {
				throw new IllegalStateException("Unable to delete file: " + file);
			}
		}
	}

	@Override
	T add(T entity) {
		String id = UUID.randomUUID().toString();
		entity.setId(id);

		String json = JsonUtils.serialize(entity);
		File file = createFile(id);
		saveFile(file, json);

		storage.put(id, entity);
		return entity;
	}

	@Override
	List<T> list() {
		return new ArrayList<T>(storage.values());
	}

	@Override
	T get(String id) {
		return storage.get(id);
	}

	@Override
	T update(T entity) {
		String json = JsonUtils.serialize(entity);
		File file = createFile(entity.getId());
		saveFile(file, json);

		storage.put(entity.getId(), entity);
		return entity;
	}

	@Override
	boolean remove(String id) {
		File file = createFile(id);
		deleteFile(file);
		return storage.remove(id) != null;
	}

}
