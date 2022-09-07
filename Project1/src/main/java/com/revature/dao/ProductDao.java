package com.revature.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Product;
import com.revature.utils.ConnectionUtil;

public class ProductDao implements Dao<Product>
{

	@Override
	public Product addInstance(Product instance)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllInstance()
	{
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM Product";
		List<Product> listOfProducts = new ArrayList<>();
		
		try(Connection con = ConnectionUtil.getConnectiion())
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				listOfProducts.add(new Product(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getDouble("price"),
						rs.getString("genre")
						));
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}

		
		return listOfProducts;
	}

	@Override
	public Product updateInstance(Product instance)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product deleteInstance(Product instance)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
