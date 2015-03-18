package com.tatsuyaoiw.repository;

import com.tatsuyaoiw.entity.Contact;

public class ContactRepository extends Repository<Contact> {

	private static final ContactRepository INSTANCE = new ContactRepository();

	public static ContactRepository getInstance() {
		return INSTANCE;
	}

	private ContactRepository() {}

}
