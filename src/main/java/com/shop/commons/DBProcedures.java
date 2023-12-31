package com.shop.commons;

public class DBProcedures
{
	public static String usp_addCustomer = "call usp_addcustomer(?,?,?,?)";
	
	public static String usp_allCustomers = "call usp_allcustomers()";
	
	public static String usp_getCustomerByMail = "call usp_getcustomerbymail(?)";
	
	public static String usp_addBook = "call usp_addbook(?,?,?,?,?,?)";
	
	public static String usp_allBooks = "call usp_allbooks()";
	
	public static String usp_getBookByName = "call usp_getbookbyname(?)";
	
	public static String usp_issueBook = "call usp_issuebook(?,?,?,?,?)";
	
	public static String usp_returnBook = "call usp_returnbook(?,?)";
	
	public static String usp_logIn = "call usp_loginowner(?,?)";
	
	public static String usp_registerOwner = "call usp_registerowner(?,?)";
	
	public static String usp_allIssuedBooks = "call usp_allissuedbooks()";
	
	public static String usp_allReturnedBooks = "call usp_allreturnedbooks()";
}
