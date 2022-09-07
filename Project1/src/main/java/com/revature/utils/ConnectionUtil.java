package com.revature.utils;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtil
{
	private static Connection con;
	
	private ConnectionUtil()
	{
		con = null;
	}
	
	public static Connection getConnectiion()
	{
		try
		{
			if(con != null && !con.isClosed())
			{
				return con;
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		
		String user, url, pass;
		Properties prop = new Properties();
		
		try
		{
            Class.forName("org.postgresql.Driver");

			
			prop.load(new FileReader("C:\\Users\\sabsh\\Documents\\workspace-spring-tool-suite-4-4.14.0.RELEASE\\Project1\\src\\main\\java\\com\\revature\\resources\\database.properties"));
			
			url =prop.getProperty("url");
			user = prop.getProperty("user");
			pass = prop.getProperty("pass");
			
			con = DriverManager.getConnection(url, user, pass);
			
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return con;

	}
}
