package com.tatsuyaoiw.repository;

import com.tatsuyaoiw.entity.Company;

public class CompanyRepository extends Repository<Company> {

	private static final CompanyRepository INSTANCE = new CompanyRepository();

	public static CompanyRepository getInstance() {
		return INSTANCE;
	}

	private CompanyRepository() {}

}
