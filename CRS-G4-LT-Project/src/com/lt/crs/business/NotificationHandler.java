package com.lt.crs.business;

import com.lt.crs.dao.PaymentsDaoImpl;

public interface NotificationHandler {
	public void checkPayment(PaymentsDaoImpl paymentsDao);

}
