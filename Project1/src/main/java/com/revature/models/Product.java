package com.revature.models;

public class Product
{
	private int id;
	private String name;
	private double price;
	private String genre;
	public Product()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int id, String name, double price, String genre)
	{
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.genre = genre;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	public String getGenre()
	{
		return genre;
	}
	public void setGenre(String genre)
	{
		this.genre = genre;
	}
	@Override
	public String toString()
	{
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", genre=" + genre + "]";
	}
	
	
}
