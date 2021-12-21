package com.lt.crs.dao;

public interface PaymentsDao {
	
	public void  makePayment(String studentUsername, int payment);
	public void checkPayment(String studentUsername);
	
}
