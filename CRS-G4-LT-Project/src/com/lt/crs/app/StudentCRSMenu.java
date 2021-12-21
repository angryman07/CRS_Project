package com.lt.crs.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.lt.crs.bean.Course;
import com.lt.crs.business.StudentHandler;
import com.lt.crs.business.StudentHandlerImpl;
import com.lt.crs.exception.CannotDropCourseException;
import com.lt.crs.exception.DuplicateCourseNameException;
import com.lt.crs.exception.InvalidCourseNameException;
import com.lt.crs.utils.DbUtils;

public class StudentCRSMenu {
	
	static Scanner sc = new Scanner(System.in);
	
	DbUtils dbConn= new DbUtils();
	Connection conn= null;
	PreparedStatement stmt = null;
	
	public void courseRegistration(String username) throws SQLException, InvalidCourseNameException {
		studentMenu();
		conn=(Connection) dbConn.createConnection();
		StringBuilder coursesEnrolled = new StringBuilder();
//		CourseHandler courseHandler = new CourseHandlerImpl();
//		courseHandler.createCourse();
		List<Course> courseList	= new ArrayList<>();
		int studentOption = sc.nextInt();
		sc.nextLine();
		do{
			if(studentOption==1) {
				StudentHandler sh= new StudentHandlerImpl();
				studentOption = sh.registerForCourse(username,coursesEnrolled.toString(), conn, courseList,studentOption);				
			}else if(studentOption==2){
				studentOption=	addCourseHandling(coursesEnrolled, courseList,studentOption);
			}else if(studentOption==3){
				studentOption= dropCourseHandling(coursesEnrolled,studentOption);
			}else if(studentOption==4){
				StudentHandler sh= new StudentHandlerImpl();
				studentOption= sh.viewGrade(username, studentOption);
			}else if(studentOption==5){
				StudentHandler sh = new StudentHandlerImpl();
				studentOption = sh.payFees(username, studentOption);
			}else if(studentOption==6){
				MainCRSMenu mainMenu = new MainCRSMenu();
				mainMenu.mainMenu();
			}else{
				System.out.println("Invalid Input");
			}
		}while(studentOption>0 && studentOption<=6);
		sc.close();
		dbConn.closeConnection(conn);
	}

	private int dropCourseHandling(StringBuilder coursesEnrolled,int studentOption) {
				System.out.println("Course Added: " +coursesEnrolled.toString());
				boolean furtherRequired = false;
				do {	
					System.out.println("Select the courses you want to remove: ");
					String tobeRemoved=sc.nextLine();
					try {
						if(coursesEnrolled.indexOf(tobeRemoved) != -1) {
							if(coursesEnrolled.indexOf(tobeRemoved) == 0 && coursesEnrolled.length()==tobeRemoved.length()){
								coursesEnrolled.replace(coursesEnrolled.indexOf(tobeRemoved), tobeRemoved.length() + coursesEnrolled.indexOf(tobeRemoved), "");
							}
							else if(coursesEnrolled.indexOf(tobeRemoved) == 0)
								coursesEnrolled.replace(coursesEnrolled.indexOf(tobeRemoved), tobeRemoved.length() + 1 + coursesEnrolled.indexOf(tobeRemoved), "");
							else
								coursesEnrolled.replace(coursesEnrolled.indexOf(tobeRemoved)-1, tobeRemoved.length()+ coursesEnrolled.indexOf(tobeRemoved), "");
						}
						else
							throw new CannotDropCourseException("Course does not exist in list, please provide appropriate course");
					} catch (CannotDropCourseException cdce) {
						System.out.println(cdce.getMessage());
						return dropCourseHandling(coursesEnrolled,studentOption);
					}
					System.out.println();
					System.out.println("Want to remove more course : (y/n)");
					String input =  sc.nextLine();
					if("Y".equalsIgnoreCase(input)) {
						furtherRequired = true;
					}
				} while(furtherRequired);
					
					System.out.println("Finally selected Courses: "+coursesEnrolled.toString());			
					studentMenu();
					studentOption = sc.nextInt();
					sc.nextLine();
					return studentOption;
	}

	private int addCourseHandling(StringBuilder coursesEnrolled, List<Course> courseList,int studentOption) throws SQLException, InvalidCourseNameException {
		
		System.out.println();
					System.out.println("Available Courses");
					System.out.println("-----------------");	
					String sql = "SELECT * FROM course";
					stmt= conn.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();
					while(rs.next()) {
						Course course = new Course();
						course.setCourseId(rs.getInt("courseId"));
						course.setCourseName(rs.getString("courseName"));
						course.setCourseAvailable(rs.getBoolean("courseAvailable"));
						course.setOfflieFees(rs.getInt("offlineAmount"));
						course.setOnlineFees(rs.getInt("onlineAmount"));
						if(!courseList.contains(course))
							courseList.add(course);
					}
					System.out.println();
					System.out.println("___________________________________________________________________________________________");
					System.out.format("| %15s | %15s | %15s | %15s | %15s |","CourseId","CourseName","CourseAvaliable","OfflineAmount","OnlineAmount");
					for(Course c : courseList) {
						System.out.println();
						System.out.format("| %15s | %15s | %15s | %15s | %15s |",c.getCourseId(),c.getCourseName(),c.isCourseAvailable(),c.getOfflieFees(),c.getOnlineFees());
					}
					System.out.println();
					System.out.println("___________________________________________________________________________________________");
					boolean furtherRequired = false;
					do {
						System.out.println();
						System.out.println();
						System.out.println("Enter courseName :");
						String courseName = sc.nextLine();
						try {
							int flag = 0;
							for(Course course : courseList) {
								if(course.getCourseName().equalsIgnoreCase(courseName)) {
									
								try{
									if(coursesEnrolled.indexOf(courseName)== -1){
										coursesEnrolled.append(courseName);
									}else {
										throw new DuplicateCourseNameException ("Course Name Already Exists!!");
									}
								}catch (DuplicateCourseNameException dcne){
									System.out.println(dcne.getMessage());
									return addCourseHandling(coursesEnrolled, courseList,studentOption);
								}
									flag = 1;
								}
									
							}
							if(flag == 0)
								throw new InvalidCourseNameException("Invalid Course Name");
						} catch (InvalidCourseNameException e) {
							System.out.println();
							System.out.println(e.getMessage());
							return addCourseHandling(coursesEnrolled, courseList,studentOption);
						}
						System.out.println();
						System.out.println("Want to add more course : (y/n)");
						String input =  sc.nextLine();
						if("Y".equalsIgnoreCase(input)) {
							furtherRequired = true;
							coursesEnrolled.append(",");
						}
						else {
							furtherRequired = false;
						}
						
					} while (furtherRequired);
					System.out.println();
					System.out.println("Courses selected : " + coursesEnrolled.toString());
					System.out.println();
					System.out.println("Select further operation");
					studentMenu();
					studentOption = sc.nextInt();
					sc.nextLine();
					return studentOption;
				}

	public void studentMenu() {
		System.out.println();
		System.out.println("Please select the appropriate option");
		System.out.println("------------------------------------");
		System.out.println("1. Register for course");
		System.out.println("2. Add Course");
		System.out.println("3. Drop Course");
		System.out.println("4. View Grade");
		System.out.println("5. Pay Fee");
		System.out.println("6. Logout");
	}
}
