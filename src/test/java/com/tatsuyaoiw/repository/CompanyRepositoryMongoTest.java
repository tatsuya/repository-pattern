package com.tatsuyaoiw.repository;

import com.tatsuyaoiw.entity.Company;
import com.tatsuyaoiw.strategy.MongoStrategy;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Ignore
public class CompanyRepositoryMongoTest {

	private static final String NAME_1 = "Google";
	private static final String WEBSITE_1 = "google.com";
	private static final String NAME_2 = "Github";
	private static final String WEBSITE_2 = "github.com";

	@Test
	public void testAdd() throws Exception {
		CompanyRepository repo = CompanyRepository.getInstance();
		repo.init(new MongoStrategy<Company>(Company.class, "mydb", "company"));

		Company google = new Company();
		google.setName(NAME_1);
		google.setWebsite(WEBSITE_1);

		Company added = repo.add(google);
		assertNotNull(added.getId());
		assertEquals(NAME_1, added.getName());
		assertEquals(WEBSITE_1, added.getWebsite());

		List<Company> companies = repo.list();
		assertEquals(1, companies.size());

		Company company = companies.get(0);
		assertEquals(added.getId(), company.getId());
		assertEquals(NAME_1, company.getName());
		assertEquals(WEBSITE_1, company.getWebsite());
	}

	@Test
	public void testGet() throws Exception {
		CompanyRepository repo = CompanyRepository.getInstance();
		repo.init(new MongoStrategy<Company>(Company.class, "mydb", "company"));

		Company google = new Company();
		google.setName(NAME_1);
		google.setWebsite(WEBSITE_1);

		Company added = repo.add(google);

		Company company = repo.get(added.getId());
		assertEquals(added.getId(), company.getId());
		assertEquals(NAME_1, company.getName());
		assertEquals(WEBSITE_1, company.getWebsite());
	}

	@Test
	public void testUpdate() throws Exception {
		CompanyRepository repo = CompanyRepository.getInstance();
		repo.init(new MongoStrategy<Company>(Company.class, "mydb", "company"));

		Company google = new Company();
		google.setName(NAME_1);
		google.setWebsite(WEBSITE_1);

		Company added = repo.add(google);
		assertNotNull(added.getId());
		assertEquals(NAME_1, added.getName());
		assertEquals(WEBSITE_1, added.getWebsite());

		List<Company> companies = repo.list();
		assertEquals(1, companies.size());

		google.setName(NAME_2);
		google.setWebsite(WEBSITE_2);

		Company updated = repo.update(google);
		assertEquals(added.getId(), updated.getId());
	}

	@Test
	public void testRemove() throws Exception {
		CompanyRepository repo = CompanyRepository.getInstance();
		repo.init(new MongoStrategy<Company>(Company.class, "mydb", "company"));

		Company google = new Company();
		google.setName(NAME_1);
		google.setWebsite(WEBSITE_1);

		Company added = repo.add(google);

		List<Company> companies = repo.list();
		assertEquals(1, companies.size());

		boolean isRemoved = repo.remove(added.getId());
		assertTrue(isRemoved);

		isRemoved = repo.remove(added.getId());
		assertFalse("should already be removed", isRemoved);

		companies = repo.list();
		assertEquals(0, companies.size());
	}

}
