package com.shop.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shop.models.BookModel;
import com.shop.models.ResponseModel;
import com.shop.services.BookService;

@Path("book")
public class BookResource
{
	@POST
	@Path("addBook")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseModel addBook(BookModel bookModel)
	{
		ResponseModel response = new ResponseModel();
		
		try
		{
			if(bookModel.getBookName()==null || bookModel.getBookName().isEmpty())
			{
				response.setStatusCode(104);
				response.setResponseMessage(" Book Name is Required ");
			}
			else if(bookModel.getBookAuthor()==null || bookModel.getBookAuthor().isEmpty())
			{
				response.setStatusCode(104);
				response.setResponseMessage(" Author Name is Required ");
			}
			else if(bookModel.getYear()==null || bookModel.getYear().isEmpty())
			{
				response.setStatusCode(104);
				response.setResponseMessage(" Publish Year is Required ");
			}
			else if(bookModel.getPrice()==null || bookModel.getPrice().isEmpty())
			{
				response.setStatusCode(104);
				response.setResponseMessage(" Price is Required ");
			}
			else if(bookModel.getBookAvailable()==null || bookModel.getBookAvailable().isEmpty())
			{
				response.setStatusCode(104);
				response.setResponseMessage(" Book Availability is Required ");
			}
			else
				response = new BookService().addBook(bookModel);
		}
		catch(Exception ex) {ex.printStackTrace();}
		
		return response;
	}
	
	@GET
	@Path("getBookByName")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseModel getBookByName(BookModel bookModel)
	{
		ResponseModel response = new ResponseModel();		
		try
		{
			if(bookModel.getBookName()==null || bookModel.getBookName().isEmpty())
			{
				response.setStatusCode(104);
				response.setResponseMessage(" Book Name is Required ");
			}
			else
			{
				response = new BookService().getBookByName(bookModel);
			}
		}
		catch(Exception ex) {ex.printStackTrace();}
		
		return response;
	}
	
	@GET
	@Path("allBooks")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseModel getAllBooks()
	{
		ResponseModel response = new ResponseModel();		
		try
		{
			response = new BookService().getAllBooks();
		}
		catch(Exception ex) {ex.printStackTrace();}
		
		return response;
	}
	
	//update existing book
//	@POST
//	@Path("updateBook")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public ResponseModel updateBook(BookModel bookModel)
//	{
//		ResponseModel response = new ResponseModel();
//		
//		try
//		{
//			if(bookModel.getBookName()==null || bookModel.getBookName().isEmpty())
//			{
//				response.setStatusCode(104);
//				response.setResponseMessage(" Book Name is Required ");
//			}
//			else if(bookModel.getBookAuthor()==null || bookModel.getBookAuthor().isEmpty())
//			{
//				response.setStatusCode(104);
//				response.setResponseMessage(" Author Name is Required ");
//			}
//			else if(bookModel.getYear()==null || bookModel.getYear().isEmpty())
//			{
//				response.setStatusCode(104);
//				response.setResponseMessage(" Publish Year is Required ");
//			}
//			else if(bookModel.getPrice()==null || bookModel.getPrice().isEmpty())
//			{
//				response.setStatusCode(104);
//				response.setResponseMessage(" Price is Required ");
//			}
//			else if(bookModel.getBookAvailable()==null || bookModel.getBookAvailable().isEmpty())
//			{
//				response.setStatusCode(104);
//				response.setResponseMessage(" Book Availability is Required ");
//			}
//			else
//				response = new BookService().addBook(bookModel);
//		}
//		catch(Exception ex) {ex.printStackTrace();}
//		
//		return response;
//	}
}
