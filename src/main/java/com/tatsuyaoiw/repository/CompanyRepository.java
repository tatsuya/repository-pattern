package com.tatsuyaoiw.repository;

import com.tatsuyaoiw.entity.Company;

import java.util.List;

public class CompanyRepository {

 	private RepositoryStrategy<Company> strategy;

	private static final CompanyRepository INSTANCE = new CompanyRepository();

	public static CompanyRepository getInstance() {
		return INSTANCE;
	}

	private CompanyRepository() {}

	public void init(RepositoryStrategy<Company> strategy) {
		this.strategy = strategy;
	}

	public Company add(Company company) {
		return strategy.add(company);
	}

	public List<Company> list() {
		return strategy.list();
	}

	public Company update(Company company) {
		return strategy.update(company);
	}

	public boolean delete(String id) {
		return strategy.remove(id);
	}

}
