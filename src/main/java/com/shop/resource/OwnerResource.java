package com.shop.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shop.models.OwnerModel;
import com.shop.models.ResponseModel;
import com.shop.services.OwnerService;

@Path("admin")
public class OwnerResource
{
	@POST
	@Path("register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseModel register(OwnerModel ownerModel)
	{
		ResponseModel response = new ResponseModel();
		
		try
		{
			if(ownerModel.getMailId()==null || ownerModel.getMailId().isEmpty())
			{
				response.setStatusCode(104);
				response.setResponseMessage(" Mail Id is Required ");
			}
			else if(ownerModel.getPasword()==null || ownerModel.getPasword().isEmpty())
			{
				response.setStatusCode(104);
				response.setResponseMessage(" Password is Required ");
			}
			else
				response = new OwnerService().register(ownerModel);
		}
		catch(Exception ex) {ex.printStackTrace();}
		
		return response;
	}
	
	@POST
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseModel logIn(OwnerModel ownerModel)
	{
		ResponseModel response = new ResponseModel();
		
		try
		{
			if(ownerModel.getMailId()==null || ownerModel.getMailId().isEmpty())
			{
				response.setStatusCode(104);
				response.setResponseMessage(" Mail Id is Required ");
			}
			else if(ownerModel.getPasword()==null || ownerModel.getPasword().isEmpty())
			{
				response.setStatusCode(104);
				response.setResponseMessage(" Password is Required ");
			}
			else
				response = new OwnerService().logIn(ownerModel);
		}
		catch(Exception ex) {ex.printStackTrace();}
		
		return response;
	}
}
