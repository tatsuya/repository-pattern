package com.tatsuyaoiw.repository;

import com.tatsuyaoiw.entity.Contact;
import com.tatsuyaoiw.repository.strategy.RepositoryStrategy;

import java.util.List;

public class ContactRepository {

	private RepositoryStrategy<Contact> strategy;

	private static final ContactRepository INSTANCE = new ContactRepository();

	public static ContactRepository getInstance() {
		return INSTANCE;
	}

	private ContactRepository() {}

	public void init(RepositoryStrategy<Contact> strategy) {
		this.strategy = strategy;
	}

	public Contact add(Contact contact) {
		return strategy.add(contact);
	}

	public List<Contact> list() {
		return strategy.list();
	}

	public boolean delete(String id) {
		return strategy.remove(id);
	}

}
