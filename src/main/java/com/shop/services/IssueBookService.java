package com.shop.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shop.commons.DBConnection;
import com.shop.commons.DBProcedures;
import com.shop.models.IssueBookModel;
import com.shop.models.ResponseModel;

public class IssueBookService
{
	Connection conn = null;
	CallableStatement callStmt = null;
	ResponseModel responseModel = null;

	public ResponseModel issueBook(IssueBookModel model)
	{
		responseModel = new ResponseModel();

		try {
			conn = DBConnection.getConnection();
			callStmt = conn.prepareCall(DBProcedures.usp_issueBook);
			callStmt.setInt(1, model.getBookId());
			callStmt.setInt(2, model.getCustomerId());
			callStmt.setString(3, model.getIssueDate());
			callStmt.setString(4, model.getDueDate());
			callStmt.setString(5, "no");

			int resultSet = callStmt.executeUpdate();

			if (resultSet > 0) {
				responseModel.setStatusCode(102);
				responseModel.setResponseMessage(" Book Issued Successfully!! ");
			} else {
				responseModel.setStatusCode(104);
				responseModel.setResponseMessage(" Failed While Issuing The Book!! ");
			}
		} catch (Exception ex) {
			responseModel.setStatusCode(108);
			responseModel.setResponseMessage(" Error Occured While Issuing The Book!!");
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
	
	public ResponseModel returnBook(IssueBookModel model)
	{
		responseModel = new ResponseModel();

		try {
			conn = DBConnection.getConnection();
			callStmt = conn.prepareCall(DBProcedures.usp_returnBook);
			callStmt.setInt(1, model.getBookId());
			callStmt.setString(2, model.getReturnedOn());

			int resultSet = callStmt.executeUpdate();

			if (resultSet > 0) {
				responseModel.setStatusCode(102);
				responseModel.setResponseMessage(" Book Returned Successfully!! ");
			} else {
				responseModel.setStatusCode(104);
				responseModel.setResponseMessage(" Failed While Returning The Book!! ");
			}
		} catch (Exception ex) {
			responseModel.setStatusCode(108);
			responseModel.setResponseMessage(" Error Occured While Returning The Book!!");
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
	
	public ResponseModel getAllIssuedBooks()
	{
		responseModel = new ResponseModel();

		try {
			conn = DBConnection.getConnection();
			callStmt = conn.prepareCall(DBProcedures.usp_allIssuedBooks);

			ResultSet resultSet = callStmt.executeQuery();

			List<IssueBookModel> list = new ArrayList<>();
			while (resultSet.next()) {
				IssueBookModel model = new IssueBookModel();
				model.setBookId(resultSet.getInt(1));
				model.setBookName(resultSet.getString(2));
				model.setCustomerId(resultSet.getInt(3));
				model.setCustomerName(resultSet.getString(4));
				model.setIssueDate(resultSet.getString(5));
				model.setDueDate(resultSet.getString(6));
				list.add(model);
			}
			if (list.size() > 0) {
				responseModel.setStatusCode(102);
				responseModel.setResponseMessage(" All Issued and Not Returned Books Fetched Successfully!! ");
				responseModel.setDataList(list);
			} else {
				responseModel.setStatusCode(104);
				responseModel.setResponseMessage(" No Books Issued!! ");
			}
		} catch (Exception ex) {
			responseModel.setStatusCode(108);
			responseModel.setResponseMessage(" Error Occured While Fetching All Issued and Not Returned Books!!");
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
	
	public ResponseModel getAllReturnedBooks()
	{
		responseModel = new ResponseModel();

		try {
			conn = DBConnection.getConnection();
			callStmt = conn.prepareCall(DBProcedures.usp_allReturnedBooks);

			ResultSet resultSet = callStmt.executeQuery();

			List<IssueBookModel> list = new ArrayList<>();
			while (resultSet.next()) {
				IssueBookModel model = new IssueBookModel();
				model.setBookId(resultSet.getInt(1));
				model.setBookName(resultSet.getString(2));
				model.setCustomerId(resultSet.getInt(3));
				model.setCustomerName(resultSet.getString(4));
				model.setIssueDate(resultSet.getString(5));
				model.setReturnedOn(resultSet.getString(6));
				list.add(model);
			}
			if (list.size() > 0) {
				responseModel.setStatusCode(102);
				responseModel.setResponseMessage(" All Returned Books Fetched Successfully!! ");
				responseModel.setDataList(list);
			} else {
				responseModel.setStatusCode(104);
				responseModel.setResponseMessage(" No Issued Books Returned!! ");
			}
		} catch (Exception ex) {
			responseModel.setStatusCode(108);
			responseModel.setResponseMessage(" Error Occured While Fetching All Returned Books!!");
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
