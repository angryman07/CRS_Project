package com.lt.crs.dao;

import com.lt.crs.bean.Student;

public interface AdminDAO {
	
	//public boolean validateUserDao(String username, String password);
	public void userApproval(Student student);
}
