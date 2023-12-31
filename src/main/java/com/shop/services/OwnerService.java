package com.shop.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.commons.DBConnection;
import com.shop.commons.DBProcedures;
import com.shop.models.OwnerModel;
import com.shop.models.ResponseModel;

public class OwnerService
{
	Connection conn = null;
	CallableStatement callStmt = null;
	ResponseModel responseModel = null;
	
	public ResponseModel register(OwnerModel ownerModel)
	{
		responseModel = new ResponseModel();
		
		try
		{
			conn = DBConnection.getConnection();
			callStmt = conn.prepareCall(DBProcedures.usp_registerOwner);	
			callStmt.setString(1, ownerModel.getMailId());
			callStmt.setString(2, ownerModel.getPasword());
			
			int resultSet = callStmt.executeUpdate();
			
			
			if(resultSet>0)
			{
				responseModel.setStatusCode(102);
				responseModel.setResponseMessage(" New Owner Added Successfully!! ");
			}
			else
			{
				responseModel.setStatusCode(104);
				responseModel.setResponseMessage(" Failed While Adding New Owner!! ");
			}
		}
		catch(Exception ex)
		{
			responseModel.setStatusCode(108);
			responseModel.setResponseMessage(" Error Occured While Adding New Owner!!");
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
	
	public ResponseModel logIn(OwnerModel ownerModel)
	{
		responseModel = new ResponseModel();
		
		try
		{
			conn = DBConnection.getConnection();
			callStmt = conn.prepareCall(DBProcedures.usp_logIn);			
			callStmt.setString(1, ownerModel.getMailId());
			callStmt.setString(2, ownerModel.getPasword());
			ResultSet resultSet = callStmt.executeQuery();
			
			List<OwnerModel> list = new ArrayList<>();
			while(resultSet.next())
			{
				OwnerModel model = new OwnerModel();
				model.setMailId(resultSet.getString(1));
				model.setPasword(resultSet.getString(2));
				list.add(model);
			}
			if(list.size()>0)
			{				
				responseModel.setStatusCode(102);
				responseModel.setResponseMessage(" Log In Succesfull!! ");
				responseModel.setDataList(list);
			}
			else
			{
				responseModel.setStatusCode(104);
				responseModel.setResponseMessage(" No Owner Found!! Wrong Credentials!!");
			}
		}
		catch(Exception ex)
		{
			responseModel.setStatusCode(108);
			responseModel.setResponseMessage(" Error Occured While Logging In Owner!!");
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
}
