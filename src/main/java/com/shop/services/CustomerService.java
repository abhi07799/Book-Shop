package com.shop.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.shop.commons.DBConnection;
import com.shop.commons.DBProcedures;
import com.shop.models.CustomerModel;
import com.shop.models.ResponseModel;

public class CustomerService
{
	Connection conn = null;
	CallableStatement callStmt = null;
	ResponseModel responseModel = null;

	// add a new customer
	public ResponseModel addCustomer(CustomerModel custModel)
	{
		responseModel = new ResponseModel();

		try {
			conn = DBConnection.getConnection();
			callStmt = conn.prepareCall(DBProcedures.usp_addCustomer);
			callStmt.setString(1, custModel.getCustomerName());
			callStmt.setString(2, custModel.getEmail());
			callStmt.setString(3, custModel.getPasword());
			callStmt.registerOutParameter(4, Types.INTEGER);

			int resultSet = callStmt.executeUpdate();

			List<Integer> list = new ArrayList<>();
			int latestId = callStmt.getInt(4);
			list.add(latestId);

			if (resultSet > 0) {
				responseModel.setStatusCode(102);
				responseModel.setResponseMessage(" New Customer Added Successfully!! ");
				responseModel.setDataList(list);
			} else {
				responseModel.setStatusCode(104);
				responseModel.setResponseMessage(" Failed While Adding New Customer!! ");
			}
		} catch (Exception ex) {
			responseModel.setStatusCode(108);
			responseModel.setResponseMessage(" Error Occured While Adding New Customer!!");
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
			}
		}
		return responseModel;
	}

	// get customer by maild id
	// get all customers
	public ResponseModel getCustomerByMail(CustomerModel custModel)
	{
		responseModel = new ResponseModel();

		try {
			conn = DBConnection.getConnection();
			callStmt = conn.prepareCall(DBProcedures.usp_getCustomerByMail);
			callStmt.setString(1, custModel.getEmail());
			ResultSet resultSet = callStmt.executeQuery();

			List<CustomerModel> list = new ArrayList<>();
			while (resultSet.next()) {
				CustomerModel model = new CustomerModel();
				model.setId(resultSet.getInt(1));
				model.setCustomerName(resultSet.getString(2));
				model.setEmail(resultSet.getString(3));
				list.add(model);
			}
			if (list.size() > 0) {
				responseModel.setStatusCode(102);
				responseModel.setResponseMessage(" Customer Details Fetched Successfully!! ");
				responseModel.setDataList(list);
			} else {
				responseModel.setStatusCode(104);
				responseModel.setResponseMessage(" No Customer Found!! ");
			}
		} catch (Exception ex) {
			responseModel.setStatusCode(108);
			responseModel.setResponseMessage(" Error Occured While Fetching Customer Details!!");
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
			}
		}
		return responseModel;
	}

	// get all customers
	public ResponseModel getAllCustomers()
	{
		responseModel = new ResponseModel();

		try {
			conn = DBConnection.getConnection();
			callStmt = conn.prepareCall(DBProcedures.usp_allCustomers);

			ResultSet resultSet = callStmt.executeQuery();

			List<CustomerModel> list = new ArrayList<>();
			while (resultSet.next()) {
				CustomerModel model = new CustomerModel();
				model.setId(resultSet.getInt(1));
				model.setCustomerName(resultSet.getString(2));
				model.setEmail(resultSet.getString(3));
				list.add(model);
			}
			if (list.size() > 0) {
				responseModel.setStatusCode(102);
				responseModel.setResponseMessage(" All Customers Fetched Successfully!! ");
				responseModel.setDataList(list);
			} else {
				responseModel.setStatusCode(104);
				responseModel.setResponseMessage(" No Customers Found!! ");
			}
		} catch (Exception ex) {
			responseModel.setStatusCode(108);
			responseModel.setResponseMessage(" Error Occured While Fetching All Customers!!");
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
			}
		}
		return responseModel;
	}

}
