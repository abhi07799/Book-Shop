package com.shop.models;

public class CustomerModel
{
	private int id;
	private String customerName;
	private String email;
	private String pasword;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getCustomerName()
	{
		return customerName;
	}
	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPasword()
	{
		return pasword;
	}
	public void setPasword(String pasword)
	{
		this.pasword = pasword;
	}
}
