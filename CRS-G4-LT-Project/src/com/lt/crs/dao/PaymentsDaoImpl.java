package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.lt.crs.bean.Payment;
import com.lt.crs.utils.DbUtils;

public class PaymentsDaoImpl implements PaymentsDao {
	
	int result = 0;
	DbUtils dbConn = new DbUtils();
	Scanner sc = new Scanner(System.in);
	Payment payment = new Payment();

	@Override
	public void makePayment(String studentUsername, int amount) {
		
		System.out.println();
		System.out.println("Please Enter the Amount to be paid: ");
		
		String amountToPaid = sc.next();

		Connection conn = null;
		PreparedStatement stmt = null;

		conn = (Connection) dbConn.createConnection();
		String sql1 = "insert into payment (paymentMode, courseAmount, studentUsername) values (?,?,?)";
		try {
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, "Online");
			stmt.setString(2, amountToPaid);
			stmt.setString(3, studentUsername);
			
			result = stmt.executeUpdate();
			
			if(result==1) {
				System.out.println("Payment Successful");
			}else {
				System.out.println("Payment Declined");
			}

			dbConn.closeConnection(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void checkPayment(String studentUsername) {
		
		Payment payment = new Payment();

		Connection conn = null;
		PreparedStatement stmt = null;

		conn = (Connection) dbConn.createConnection();
		String sql = "Select onlineAmount from course where courseId = (select courseId from enrolledcourses where studentUsername=?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, payment.getStudentId());
			
			ResultSet rs2 = stmt.executeQuery();
			while(rs2.next()) {
				int amount = rs2.getInt(1);
				System.out.println();
				System.out.println("You have to pay "+amount+" for your course");
				System.out.println();
				
				makePayment(studentUsername, amount);
				
			}
			
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
