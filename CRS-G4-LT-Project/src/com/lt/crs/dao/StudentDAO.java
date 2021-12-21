package com.lt.crs.dao;

import com.lt.crs.bean.Student;

public interface StudentDAO {
 public void  createStudent(Student student);
 public void addCourse(String studentName, String courseName);
}
