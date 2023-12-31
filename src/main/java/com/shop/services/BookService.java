package com.shop.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.shop.commons.DBConnection;
import com.shop.commons.DBProcedures;
import com.shop.models.BookModel;
import com.shop.models.ResponseModel;

public class BookService
{
	Connection conn = null;
	CallableStatement callStmt = null;
	ResponseModel responseModel = null;
	
	// add a new book
	public ResponseModel addBook(BookModel bookModel)
	{
		responseModel = new ResponseModel();
		
		try
		{
			conn = DBConnection.getConnection();
			callStmt = conn.prepareCall(DBProcedures.usp_addBook);			
			callStmt.setString(1, bookModel.getBookName());
			callStmt.setString(2, bookModel.getBookAuthor());
			callStmt.setString(3, bookModel.getYear());
			callStmt.setString(4, bookModel.getPrice());
			callStmt.setString(5, bookModel.getBookAvailable());
			callStmt.registerOutParameter(6, Types.INTEGER);
			
			int resultSet = callStmt.executeUpdate();
			
			List<Integer> list = new ArrayList<>();
			int latestId = callStmt.getInt(6);
			list.add(latestId);
			
			if(resultSet>0)
			{
				responseModel.setStatusCode(102);
				responseModel.setResponseMessage(" New Book Added Successfully!! ");
				responseModel.setDataList(list);
			}
			else
			{
				responseModel.setStatusCode(104);
				responseModel.setResponseMessage(" Failed While Adding New Book!! ");
			}
		}
		catch(Exception ex)
		{
			responseModel.setStatusCode(108);
			responseModel.setResponseMessage(" Error Occured While Adding New Book!!");
			ex.printStackTrace();
		}
		finally
		{
			try 
			{
				if(conn != null)
					conn.close();
			}
			catch(Exception ex) {}
		}
		return responseModel;
	}
	
	// get book by name
		public ResponseModel getBookByName(BookModel bookModel)
		{
			responseModel = new ResponseModel();
			
			try
			{
				conn = DBConnection.getConnection();
				callStmt = conn.prepareCall(DBProcedures.usp_getBookByName);			
				callStmt.setString(1, bookModel.getBookName());
				ResultSet resultSet = callStmt.executeQuery();
				
				List<BookModel> list = new ArrayList<>();
				while(resultSet.next())
				{
					BookModel model = new BookModel();
					model.setId(resultSet.getInt(1));
					model.setBookName(resultSet.getString(2));
					model.setBookAuthor(resultSet.getString(3));
					model.setYear(resultSet.getString(4));
					model.setPrice(resultSet.getString(5));
					model.setBookAvailable(resultSet.getString(6));
					list.add(model);
				}
				if(list.size()>0)
				{				
					responseModel.setStatusCode(102);
					responseModel.setResponseMessage(" Book Details Fetched Successfully!! ");
					responseModel.setDataList(list);
				}
				else
				{
					responseModel.setStatusCode(104);
					responseModel.setResponseMessage(" No Book Found!! ");
				}
			}
			catch(Exception ex)
			{
				responseModel.setStatusCode(108);
				responseModel.setResponseMessage(" Error Occured While Fetching Book Details!!");
				ex.printStackTrace();
			}
			finally
			{
				try 
				{
					if(conn != null)
						conn.close();
				}
				catch(Exception ex) {}
			}
			return responseModel;
		}
	
	// get all books
	public ResponseModel getAllBooks()
	{
		responseModel = new ResponseModel();
		
		try
		{
			conn = DBConnection.getConnection();
			callStmt = conn.prepareCall(DBProcedures.usp_allBooks);			
			
			ResultSet resultSet = callStmt.executeQuery();
			
			List<BookModel> list = new ArrayList<>();
			while(resultSet.next())
			{
				BookModel model = new BookModel();
				model.setId(resultSet.getInt(1));
				model.setBookName(resultSet.getString(2));
				model.setBookAuthor(resultSet.getString(3));
				model.setYear(resultSet.getString(4));
				model.setPrice(resultSet.getString(5));
				model.setBookAvailable(resultSet.getString(6));
				list.add(model);
			}
			if(list.size()>0)
			{				
				responseModel.setStatusCode(102);
				responseModel.setResponseMessage(" All Books Fetched Successfully!! ");
				responseModel.setDataList(list);
			}
			else
			{
				responseModel.setStatusCode(104);
				responseModel.setResponseMessage(" No Books Found!! ");
			}
		}
		catch(Exception ex)
		{
			responseModel.setStatusCode(108);
			responseModel.setResponseMessage(" Error Occured While Fetching All Books!!");
			ex.printStackTrace();
		}
		finally
		{
			try 
			{
				if(conn != null)
					conn.close();
			}
			catch(Exception ex) {}
		}
		return responseModel;
	}
	
	// update existing book
//		public ResponseModel updateBook(BookModel bookModel)
//		{
//			responseModel = new ResponseModel();
//			
//			try
//			{
//				conn = DBConnection.getConnection();
//				callStmt = conn.prepareCall(DBProcedures.usp_addBook);			
//				callStmt.setString(1, bookModel.getBookName());
//				callStmt.setString(2, bookModel.getBookAuthor());
//				callStmt.setString(3, bookModel.getYear());
//				callStmt.setString(4, bookModel.getPrice());
//				callStmt.setString(5, bookModel.getBookAvailable());
//				callStmt.registerOutParameter(6, Types.INTEGER);
//				
//				int resultSet = callStmt.executeUpdate();
//				
//				List<Integer> list = new ArrayList<>();
//				int latestId = callStmt.getInt(6);
//				list.add(latestId);
//				
//				if(resultSet>0)
//				{
//					responseModel.setStatusCode(102);
//					responseModel.setResponseMessage(" New Book Added Successfully!! ");
//					responseModel.setDataList(list);
//				}
//				else
//				{
//					responseModel.setStatusCode(104);
//					responseModel.setResponseMessage(" Failed While Adding New Book!! ");
//				}
//			}
//			catch(Exception ex)
//			{
//				responseModel.setStatusCode(108);
//				responseModel.setResponseMessage(" Error Occured While Adding New Book!!");
//				ex.printStackTrace();
//			}
//			finally
//			{
//				try 
//				{
//					if(conn != null)
//						conn.close();
//				}
//				catch(Exception ex) {}
//			}
//			return responseModel;
//		}
	
}
