package com.tatsuyaoiw.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import com.tatsuyaoiw.entity.Entity;
import com.tatsuyaoiw.util.JsonUtils;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MongoStrategy<T extends Entity> extends RepositoryStrategy<T> {

	private final Class<T> type;
	private final String db;
	private final String collection;
	private final MongoClient client;

	public MongoStrategy(Class<T> type, String db, String collection) {
		this.type = type;
		this.db = db;
		this.collection = collection;

		ServerAddress server;
		try {
			server = MongoConfig.getServerAddress();
		} catch (UnknownHostException e) {
			throw new IllegalStateException("Unable to create mongo client", e);
		}
		client = new MongoClient(server);
	}

	private DBCollection getCollection() {
		return client.getDB(db).getCollection(collection);
	}

	@Override
	T add(T entity) {
		BasicDBObject doc = (BasicDBObject) JSON.parse(JsonUtils.serialize(entity));
		getCollection().insert(doc);
		entity.setId(doc.getString("_id"));
		return entity;
	}

	@Override
	List<T> list() {
		List<T> entities = new ArrayList<T>();
		DBCursor cursor = getCollection().find();
		try {
			while (cursor.hasNext()) {
				BasicDBObject doc = (BasicDBObject) cursor.next();
				T entity = JsonUtils.deserialize(doc.toString(), type);
				entity.setId(doc.getString("_id"));
				entities.add(entity);
			}
		} finally {
			cursor.close();
		}
		return entities;
	}

	@Override
	T get(String id) {
		T entity = null;
		BasicDBObject query =  new BasicDBObject("_id", new ObjectId(id));
		DBCursor cursor = getCollection().find(query);
		try {
			BasicDBObject doc = (BasicDBObject) cursor.one();
			if (doc != null) {
				entity = JsonUtils.deserialize(doc.toString(), type);
				entity.setId(doc.getString("_id"));
			}
		} finally {
			cursor.close();
		}
		return entity;
	}

	@Override
	T update(T entity) {
		BasicDBObject query = new BasicDBObject("_id", new ObjectId(entity.getId()));
		BasicDBObject doc = (BasicDBObject) JSON.parse(JsonUtils.serialize(entity));
		getCollection().update(query, doc);
		entity.setId(doc.getString("_id"));
		return entity;
	}

	@Override
	boolean remove(String id) {
		BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
		WriteResult result = getCollection().remove(query);
		return result.getN() != 0;
	}

	private static class MongoConfig {

		private static ServerAddress getServerAddress() throws UnknownHostException {
			String host = System.getenv("MONGO_HOST");
			String port = System.getenv("MONGO_PORT");
			if (host == null) {
				return new ServerAddress();
			}
			if (port == null) {
				return new ServerAddress(host);
			}
			return new ServerAddress(host, Integer.parseInt(port));
		}

	}

}
