package com.shop.models;

import java.util.List;

public class ResponseModel
{
	private int statusCode;
	private String responseMessage;
	private List<?> dataList;
	
	public int getStatusCode()
	{
		return statusCode;
	}
	public void setStatusCode(int statusCode)
	{
		this.statusCode = statusCode;
	}
	public String getResponseMessage()
	{
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage)
	{
		this.responseMessage = responseMessage;
	}
	public List<?> getDataList()
	{
		return dataList;
	}
	public void setDataList(List<?> dataList)
	{
		this.dataList = dataList;
	}
}
