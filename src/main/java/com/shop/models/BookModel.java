package com.shop.models;

public class BookModel
{
	private int id;
	private String bookName;
	private String bookAuthor;
	private String year;
	private String price;
	private String bookAvailable;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getBookName()
	{
		return bookName;
	}
	public void setBookName(String bookName)
	{
		this.bookName = bookName;
	}
	public String getBookAuthor()
	{
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor)
	{
		this.bookAuthor = bookAuthor;
	}
	public String getYear()
	{
		return year;
	}
	public void setYear(String year)
	{
		this.year = year;
	}
	public String getPrice()
	{
		return price;
	}
	public void setPrice(String price)
	{
		this.price = price;
	}
	public String getBookAvailable()
	{
		return bookAvailable;
	}
	public void setBookAvailable(String bookAvailable)
	{
		this.bookAvailable = bookAvailable;
	}
	
}
