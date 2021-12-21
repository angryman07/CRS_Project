package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lt.crs.bean.Student;
import com.lt.crs.bean.role;
import com.lt.crs.utils.DbUtils;


public class AdminDAOImpl implements AdminDAO {
	
	DbUtils dbConn= new DbUtils();
	
	public  AdminDAOImpl() {

		role r1 = new role();
		r1.setRoleId(101);
		r1.setRoleName("Admin");

		role r2 = new role();
		r2.setRoleId(102);
		r2.setRoleName("Professor");

		role r3 = new role();
		r3.setRoleId(103);
		r3.setRoleName("Student");

		Connection conn=null;
		PreparedStatement stmt=null ;
		PreparedStatement stmt1=null ;
		PreparedStatement stmt2=null ;
		conn=(Connection) dbConn.createConnection();
		String sql="insert into role values(?,?)";

		try {
			stmt= conn.prepareStatement(sql);
			stmt1= conn.prepareStatement(sql);
			stmt2= conn.prepareStatement(sql);
			stmt.setInt(1,r1.getRoleId()); 
			stmt.setString(2,r1.getRoleName());
			stmt1.setInt(1,r2.getRoleId()); 
			stmt1.setString(2,r2.getRoleName());
			stmt2.setInt(1,r3.getRoleId()); 
			stmt2.setString(2,r3.getRoleName());
			stmt.executeUpdate();
			stmt1.executeUpdate();
			stmt2.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		dbConn.closeConnection(conn);
		return;

	}

	public void userApproval(Student student) {

		//@Override
		Connection conn=null;
		PreparedStatement stmt=null ;
		PreparedStatement stmt2=null ;
		conn=(Connection) dbConn.createConnection();
		String sql="insert into user values(?,?,?,?,?)";
		String sql2 = "select id from role where role = 'Student'";


		try {
			stmt= conn.prepareStatement(sql);
			stmt2= conn.prepareStatement(sql2);
			ResultSet rs = stmt2.executeQuery();
			stmt.setInt(1,student.getStudentId());  
			stmt.setString(2,student.getStudentUsername());
			stmt.setString(3, student.getStudentPassword());
			while(rs.next()) {
				stmt.setInt(4,rs.getInt(1));
			}

			stmt.setBoolean(5, true);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbConn.closeConnection(conn);
		}
	}	

}
