package com.tatsuyaoiw.repository;

import com.tatsuyaoiw.entity.Company;
import com.tatsuyaoiw.repository.strategy.FileStrategy;
import com.tatsuyaoiw.repository.strategy.InMemoryStrategy;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CompanyRepositoryTest {

	private static final String NAME = "Google";
	private static final String WEBSITE = "google.com";

	@Test
	public void testInMemory() throws Exception {
		CompanyRepository repo = CompanyRepository.getInstance();
		repo.init(new InMemoryStrategy<Company>());

		Company google = new Company();
		google.setName(NAME);
		google.setWebsite(WEBSITE);

		Company added = repo.add(google);
		assertNotNull(added.getId());
		assertEquals(NAME, added.getName());
		assertEquals(WEBSITE, added.getWebsite());

		List<Company> companies = repo.list();
		assertEquals(1, companies.size());

		Company company = companies.get(0);
		assertEquals(added.getId(), company.getId());
		assertEquals(NAME, company.getName());
		assertEquals(WEBSITE, company.getWebsite());
	}

	@Test
	public void testFile() throws Exception {
		CompanyRepository repo = CompanyRepository.getInstance();
		repo.init(new FileStrategy<Company>());

		Company google = new Company();
		google.setName(NAME);
		google.setWebsite(WEBSITE);

		Company added = repo.add(google);
		assertNotNull(added.getId());
	}
}