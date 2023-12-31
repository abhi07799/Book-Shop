package com.shop.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shop.models.IssueBookModel;
import com.shop.models.ResponseModel;
import com.shop.services.IssueBookService;

@Path("issue")
public class IssueBookResource
{
	@POST
	@Path("issueBook")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel issueBook(IssueBookModel model)
	{
		ResponseModel response = new ResponseModel();

		try {
			/*
			 * if(model.getCustomerId()>0 ) { response.setStatusCode(104);
			 * response.setResponseMessage(" Customer Id is Required "); } else
			 * if(model.getBookId()>0) { response.setStatusCode(104);
			 * response.setResponseMessage(" Book Id is Required "); } else
			 */
			if (model.getIssueDate() == null) {
				response.setStatusCode(104);
				response.setResponseMessage(" Issue Date is Required ");
			} else if (model.getDueDate() == null) {
				response.setStatusCode(104);
				response.setResponseMessage(" Due Date is Required ");
			} else
				response = new IssueBookService().issueBook(model);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return response;
	}

	@POST
	@Path("returnBook")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseModel returnBook(IssueBookModel model)
	{
		ResponseModel response = new ResponseModel();

		try {
			if (model.getReturnedOn() == null || model.getReturnedOn().isEmpty()) {
				response.setStatusCode(104);
				response.setResponseMessage(" Returned On Date is Required ");
			} else
				response = new IssueBookService().returnBook(model);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return response;
	}

	@GET
	@Path("allIssuedBooks")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseModel getAllCustomer()
	{
		ResponseModel response = new ResponseModel();		
		try
		{
			response = new IssueBookService().getAllIssuedBooks();
		}
		catch(Exception ex) {ex.printStackTrace();}
		
		return response;
	}
	
	@GET
	@Path("allReturnedBooks")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseModel getAllReturnedBooks()
	{
		ResponseModel response = new ResponseModel();		
		try
		{
			response = new IssueBookService().getAllReturnedBooks();
		}
		catch(Exception ex) {ex.printStackTrace();}
		
		return response;
	}
}
