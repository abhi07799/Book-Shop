package com.shop.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shop.models.CustomerModel;
import com.shop.models.ResponseModel;
import com.shop.services.CustomerService;

@Path("customer")
public class CustomerResource
{
	@POST
	@Path("addCustomer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseModel addCustomer(CustomerModel custModel)
	{
		ResponseModel response = new ResponseModel();
		
		try
		{
			if(custModel.getCustomerName()==null || custModel.getCustomerName().isEmpty())
			{
				response.setStatusCode(104);
				response.setResponseMessage(" Customer Name is Required ");
			}
			else if(custModel.getEmail()==null || custModel.getEmail().isEmpty())
			{
				response.setStatusCode(104);
				response.setResponseMessage(" Mail Id is Required ");
			}
			else if(custModel.getPasword()==null || custModel.getPasword().isEmpty())
			{
				response.setStatusCode(104);
				response.setResponseMessage(" Password is Required ");
			}
			else
				response = new CustomerService().addCustomer(custModel);
		}
		catch(Exception ex) {ex.printStackTrace();}
		
		return response;
	}
	
	@GET
	@Path("getCustomer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseModel getCustomerByMail(CustomerModel custModel)
	{
		ResponseModel response = new ResponseModel();		
		try
		{
			if(custModel.getEmail()==null || custModel.getEmail().isEmpty())
			{
				response.setStatusCode(104);
				response.setResponseMessage(" Mail Id is Required ");
			}
			else
			{
				response = new CustomerService().getCustomerByMail(custModel);
			}
		}
		catch(Exception ex) {ex.printStackTrace();}
		
		return response;
	}
	
	@GET
	@Path("allCustomers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseModel getAllCustomer()
	{
		ResponseModel response = new ResponseModel();		
		try
		{
			response = new CustomerService().getAllCustomers();
		}
		catch(Exception ex) {ex.printStackTrace();}
		
		return response;
	}
}
