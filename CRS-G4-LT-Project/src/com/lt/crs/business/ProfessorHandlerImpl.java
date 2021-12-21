package com.lt.crs.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import com.lt.crs.constants.EnumGrade;
import com.lt.crs.utils.DbUtils;

public class ProfessorHandlerImpl implements ProfessorHandler {
	DbUtils dbConn= new DbUtils();
	Connection conn=null;
	PreparedStatement ps=null;
	
 
	@Override
	public String viewGrades(int studentid) {
		try {
			String sql= "Select studentGrade from grades where studentId=?";
			conn=(Connection) dbConn.createConnection();
			ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1,studentid );
			ResultSet rs= ps.executeQuery();
			while (rs.next()){
				return rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbConn.closeConnection(conn);
		}
		return null;
	}

	@Override
	public String insertGrade(String studentUsername) {
		
		String sql= "insert into grades values (?,?)";
		String studIdQuery= "Select studentId from student where studentUsername=?";
		int studId=0;
		try {
			conn=(Connection) dbConn.createConnection();
			ps= (PreparedStatement) conn.prepareStatement(studIdQuery);
			ps.setString(1, studentUsername);
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				studId = rs.getInt(1);
			}
			ps= (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, studId);
			ps.setString(2, EnumGrade.values()[new Random().nextInt(EnumGrade.values().length)].toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			dbConn.closeConnection(conn);
		}
		
		return null;
	}

	
}
