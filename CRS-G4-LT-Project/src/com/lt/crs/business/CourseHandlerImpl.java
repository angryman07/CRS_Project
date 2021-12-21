package com.lt.crs.business;

import java.util.ArrayList;
import java.util.List;

import com.lt.crs.bean.Course;
import com.lt.crs.dao.CourseDAO;
import com.lt.crs.dao.CourseDAOImpl;

public class CourseHandlerImpl implements CourseHandler {
	public static List<Course> courseList = new ArrayList<>();
	CourseDAO courseDao= new CourseDAOImpl();
	
	public void createCourse() {
		Course course1 = new Course();
		course1.setCourseAvailable(true);
		course1.setCourseId(1001);
		course1.setCourseName("Course1");
		course1.setOfflieFees(1000);
		course1.setOnlineFees(500);
	
		Course course2 = new Course();
		course2.setCourseAvailable(true);
		course2.setCourseId(1002);
		course2.setCourseName("Course2");
		course2.setOfflieFees(2000);
		course2.setOnlineFees(1500);
		
		courseDao.createCourse(course1);
		courseDao.createCourse(course2);
		
	}
}
