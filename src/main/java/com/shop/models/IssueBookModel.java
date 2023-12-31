package com.shop.models;

public class IssueBookModel
{
	private int id;
	private int customerId;
	private int bookId;
	private String issueDate;
	private String dueDate;
	private String returned;
	private String returnedOn;
	private String bookName;
	private String customerName;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getCustomerId()
	{
		return customerId;
	}
	public void setCustomerId(int customerId)
	{
		this.customerId = customerId;
	}
	public int getBookId()
	{
		return bookId;
	}
	public void setBookId(int bookId)
	{
		this.bookId = bookId;
	}
	public String getIssueDate()
	{
		return issueDate;
	}
	public void setIssueDate(String issueDate)
	{
		this.issueDate = issueDate;
	}
	public String getDueDate()
	{
		return dueDate;
	}
	public void setDueDate(String dueDate)
	{
		this.dueDate = dueDate;
	}
	public String getReturned()
	{
		return returned;
	}
	public void setReturned(String returned)
	{
		this.returned = returned;
	}
	public String getReturnedOn()
	{
		return returnedOn;
	}
	public void setReturnedOn(String returnedOn)
	{
		this.returnedOn = returnedOn;
	}
	public String getBookName()
	{
		return bookName;
	}
	public void setBookName(String bookName)
	{
		this.bookName = bookName;
	}
	public String getCustomerName()
	{
		return customerName;
	}
	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}

}
