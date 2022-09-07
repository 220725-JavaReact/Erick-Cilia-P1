package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.dao.Dao;
import com.revature.models.Customer;

public class CustomerService
{
	private Dao<Customer> customerdao;
	
	public CustomerService(Dao<Customer> customerdao)
	{
		this.customerdao = customerdao;
	}
	
	public List<Customer> getAllCustomers()
	{
		return customerdao.getAllInstance();
	}
	
	public Customer getCustomerById(int id)
	{
		List<Customer> listOfCustomers = getAllCustomers();
		
		Optional<Customer> foundCustomer = listOfCustomers.stream()
				.filter(customer -> customer.getId() == id)
				.findFirst();
		
		if (foundCustomer.isPresent())
		{
			return foundCustomer.get();
		}
		else
		{
			Customer noCustomer = new Customer();
			noCustomer.setName("No such customer was found");
			
			return noCustomer;

		}
		
	}
	
	public Customer addCustomer(Customer customer)
	{
		return customerdao.addInstance(customer);
	}
	
	public Customer deleteCustomer(Customer customer)
	{
		return customerdao.deleteInstance(customer);
	}
}
