package com.revature.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.CustomerDao;
import com.revature.models.Customer;
import com.revature.services.CustomerService;

public class CustomerController extends HttpServlet
{
	private static CustomerService customerserv = new CustomerService(new CustomerDao());
	private static ObjectMapper objmap = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		final String URI = req.getRequestURI().replace("/Project1/", "");
		resp.setContentType("application/json");

		String jsonString;

		switch (URI)
		{
		case "customer":
			// resp.getWriter().println("testing for 1 customer");
			Integer id = 0;

			try
			{
				id = Integer.parseInt(req.getParameter("id"));

			} catch (Exception e)
			{
				// TODO: handle exception
			}

			Customer foundCustomer = customerserv.getCustomerById(id);
			jsonString = objmap.writeValueAsString(foundCustomer);
			

			resp.getWriter().println(jsonString);

			break;

		case "allcustomers":
			// resp.getWriter().println("test for all customers");
			List<Customer> listOfCustomers = customerserv.getAllCustomers();

			jsonString = objmap.writeValueAsString(listOfCustomers);

			resp.getWriter().println(jsonString);

			break;

		default:
			resp.setStatus(405);
			break;
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		final String URI = req.getRequestURI().replace("/Project1/", "");
		resp.setContentType("application/json");

		switch (URI)
		{
		case "addcustomer":
			String jsonString = req.getReader().lines().collect(Collectors.joining());

			Customer customer = objmap.readValue(jsonString, Customer.class);

			customer = customerserv.addCustomer(customer);
			jsonString = objmap.writeValueAsString(customer);

			resp.getWriter().println(jsonString);

			break;

		default:
			break;
		}
	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException
	{
		final String URI = req.getRequestURI().replace("/Project1/", "");
		resp.setContentType("application/json");
		
		switch (URI)
		{
		case "deletecustomer":
			
			Customer customer = new Customer();
			
			customer = customerserv.deleteCustomer(customer);
			
			resp.getWriter().println("Customer has been deleted");
			
			
			break;

		default:
			break;
		}
	}

}
