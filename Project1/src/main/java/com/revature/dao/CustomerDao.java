package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.Logger;
import com.revature.utils.Logger.LogLevel;

public class CustomerDao implements Dao<Customer>
{
	
	private static Logger logger = Logger.getLogger();


	@Override
	public Customer addInstance(Customer instance)
	{
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Customer(name, address, email) VALUES(?,?,?) RETURNING id";
		
		try(Connection con = ConnectionUtil.getConnectiion())
		{
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, instance.getName());
			stmt.setString(2, instance.getAddress());
			stmt.setString(3, instance.getEmail());
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			instance.setId(rs.getInt("id"));
			
			logger.log(LogLevel.info, "Adding a new customer"+ instance.getId());

			

		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return instance;
	}

	@Override
	public List<Customer> getAllInstance()
	{
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM Customer";
		List<Customer> listOfCustomers = new ArrayList<>();

		try (Connection con = ConnectionUtil.getConnectiion())
		{
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())
			{
				listOfCustomers.add(new Customer(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getString("email")
						));
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		
		logger.log(LogLevel.info, "Getting all customers");


		return listOfCustomers;
	}

	@Override
	public Customer updateInstance(Customer instance)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer deleteInstance(Customer instance)
	{
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Customer WHERE name = ?";
		
		try(Connection con = ConnectionUtil.getConnectiion())
		{
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, instance.getName());
			stmt.execute();
			
			
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return instance;
	}

}
