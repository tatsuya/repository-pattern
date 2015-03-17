package com.tatsuyaoiw;

import com.tatsuyaoiw.entity.Company;
import com.tatsuyaoiw.entity.Contact;
import com.tatsuyaoiw.repository.CompanyRepository;
import com.tatsuyaoiw.repository.ContactRepository;
import com.tatsuyaoiw.repository.FileStrategy;
import com.tatsuyaoiw.repository.RepositoryStrategy;

import java.util.List;

public class App {

	public static void main(String[] args) {
		CompanyRepository companies = initCompanyRepository(new FileStrategy<Company>(Company.class, "companies"));
		addCompanies(companies);
		updateCompanies(companies);

		printCompanies(companies);

		ContactRepository contacts = initContactRepository(new FileStrategy<Contact>(Contact.class, "contacts"));
		addContacts(contacts);
		updateContacts(contacts);

		printContacts(contacts);
	}

	private static CompanyRepository initCompanyRepository(RepositoryStrategy<Company> strategy) {
		CompanyRepository repo = CompanyRepository.getInstance();
		repo.init(strategy);
		return repo;
	}

	private static void addCompanies(CompanyRepository repo) {
		Company google = new Company();
		google.setName("Google");
		google.setWebsite("google.com");
		repo.add(google);
	}

	private static void updateCompanies(CompanyRepository repo) {
		List<Company> companies = repo.list();
		Company company = companies.get(0);
		company.setWebsite("google.co.jp");
		repo.update(company);
	}

	private static void printCompanies(CompanyRepository repo) {
		for (Company company : repo.list()) {
			System.out.println("ID: " + company.getId());
			System.out.println("Name: " + company.getName());
			System.out.println("Website: " + company.getWebsite());
		}
	}

	private static ContactRepository initContactRepository(RepositoryStrategy<Contact> strategy) {
		ContactRepository repo = ContactRepository.getInstance();
		repo.init(strategy);
		return repo;
	}

	private static void addContacts(ContactRepository repo) {
		Contact larry = new Contact();
		larry.setFirstName("Larry");
		larry.setLastName("Page");
		repo.add(larry);

		Contact sergey = new Contact();
		sergey.setFirstName("Sergey");
		sergey.setLastName("Brin");
		repo.add(sergey);
	}

	private static void updateContacts(ContactRepository repo) {
		List<Contact> contacts = repo.list();
		Contact contact = contacts.get(0);
		contact.setFirstName("Eric");
		contact.setLastName("Schmidt");
		repo.update(contact);
	}

	private static void printContacts(ContactRepository repo) {
		for (Contact contact : repo.list()) {
			System.out.println("ID: " + contact.getId());
			System.out.println("First name: " + contact.getFirstName());
			System.out.println("Last name: " + contact.getLastName());
		}
	}

}
