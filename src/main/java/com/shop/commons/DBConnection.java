package com.shop.commons;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection
{
	public static Connection getConnection()
	{
		Connection con = null;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/book_shop", "root","root");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return con;
	}
}
