package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ProductDao;
import com.revature.models.Product;
import com.revature.services.ProductService;

public class ProductController extends HttpServlet
{
	private static ProductService productserv = new ProductService(new ProductDao());
	private static ObjectMapper objmap = new ObjectMapper();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		final String URI = req.getRequestURI().replace("/Project1/", "");
		resp.setContentType("application/json");

		String jsonString;
		
		switch (URI)
		{
		
		case "productbyid":
			Integer id = 0;
			
			try
			{
				id = Integer.parseInt(req.getParameter("id"));
			}
			catch (Exception e)
			{
				// TODO: handle exception
				
			}
			
			Product foundProduct = productserv.getProductById(id);
			jsonString = objmap.writeValueAsString(foundProduct);
			
			resp.getWriter().println(jsonString);
			break;
		
		case "allproducts":
			//resp.getWriter().println("you are getting all products test");
			List<Product> listOfProducts = productserv.getAllProducts();
			
			jsonString = objmap.writeValueAsString(listOfProducts);
			
			resp.getWriter().println(jsonString);
			
			break;

		default:
			resp.setStatus(405);
			break;
		}
	}
	
}
