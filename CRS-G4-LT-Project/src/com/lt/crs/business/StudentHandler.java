package com.lt.crs.business;

import java.sql.Connection;
import java.util.List;

import com.lt.crs.bean.Course;

public interface StudentHandler {
	public void createDummyStudent();
	public boolean validateStudent(String username, String password);
	public int registerForCourse(String username, String courseEnrolled, Connection conn,List<Course> courseList, int studentOption);
//	public void addCourse(String studentName, String courseName);
//	public void dropCourse(String studentName, String courseName);
	public int viewGrade(String studentName,int studentOption);
	public int payFees(String username, int studentOption);
	public void createStudent();
}
