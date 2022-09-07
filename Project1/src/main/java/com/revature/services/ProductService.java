package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.dao.Dao;
import com.revature.models.Product;

public class ProductService
{
	private Dao<Product> productdao;
	
	public ProductService(Dao<Product> productdao)
	{
		this.productdao = productdao;
	}
	
	public List<Product> getAllProducts()
	{
		return productdao.getAllInstance();
	}
	
	public Product getProductById(int id)
	{
		List<Product> listOfProducts = getAllProducts();
		
		Optional<Product> foundProduct = listOfProducts.stream()
				.filter(product -> product.getId() == id)
				.findFirst();
		
		if (foundProduct.isPresent())
		{
			return foundProduct.get();
		}
		else
		{
			Product noProduct = new Product();
			noProduct.setName("There is no game by that ID");
			
			return noProduct;
		}
	}
}
