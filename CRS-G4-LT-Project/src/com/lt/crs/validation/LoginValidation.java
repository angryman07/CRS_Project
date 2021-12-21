package com.lt.crs.validation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lt.crs.app.MainCRSMenu;
import com.lt.crs.constants.SqlConstants;
import com.lt.crs.utils.DbUtils;

public class LoginValidation {
	DbUtils dbConn = new DbUtils();
	
	public String validateCredentials(String userName, String password) {
		int roleId = 0 ;
		String role = null;
		boolean isApproved = false;
		Connection conn=null;
		PreparedStatement stmt=null ;
		ResultSet rs = null;
		PreparedStatement stmt1=null ;
		ResultSet rs1 = null;
		conn=(Connection) dbConn.createConnection();
		String sql=SqlConstants.userQuery;
		
		try {
			stmt= conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				if(userName.equalsIgnoreCase(rs.getString(2)) && password.equalsIgnoreCase(rs.getString(3))) {
					roleId = rs.getInt(4);
					String sql1="select role from role where id ="+roleId;
					stmt1= conn.prepareStatement(sql1);
					rs1 = stmt1.executeQuery();
					while(rs1.next()) {
					 role=rs1.getString(1);
					 isApproved = rs.getBoolean(5);
					}
						
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally{
			dbConn.closeConnection(conn);
		}
		if(role.equalsIgnoreCase("Student") && isApproved == true ||role.equalsIgnoreCase("Admin") || role.equalsIgnoreCase("Professor"))
		    return role;
		else
			return null;
		
	}
	
// This Method is responsible for updating the password for Student/Proff/Admin in user table.	
	public void updateCredentials(String username, String oldPassword, String newPassword){
		Connection conn= null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2=null;
		ResultSet rs= null;
		
		conn=(Connection) dbConn.createConnection();
		String sql=SqlConstants.userQuery;
		try{
			stmt1= conn.prepareStatement(sql);
			 rs= stmt1.executeQuery();
			
			while(rs.next()){
				 	if(username.equalsIgnoreCase(rs.getString(2))&& oldPassword.equalsIgnoreCase(rs.getString(3))){
					String passw = "UPDATE user SET userPassword='"+newPassword+"' WHERE userPassword='"+oldPassword+"' ";
					stmt2= conn.prepareStatement(passw);
					System.out.println("Password Updated Successfully for: "+username);
					stmt2.executeUpdate();
					
				}else if(!username.equalsIgnoreCase(rs.getString(2))|| !oldPassword.equalsIgnoreCase(rs.getString(3))){
					System.out.println("Invalid Username or Password");
				}
				
			}
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
		dbConn.closeConnection(conn);
		MainCRSMenu crs= new MainCRSMenu();
		crs.mainMenu();
		}
	}
}


