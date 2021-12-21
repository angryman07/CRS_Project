package com.lt.crs.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.lt.crs.business.StudentHandler;
import com.lt.crs.business.StudentHandlerImpl;
import com.lt.crs.constants.CRSConstants;
import com.lt.crs.exception.InvalidCourseNameException;
import com.lt.crs.validation.LoginValidation;

public class MainCRSMenu {
	
	Scanner sc = new Scanner(System.in);
	
	public void mainMenu() {
		System.out.println();
		System.out.println("\t\t\t\t\t\t\t-----WELCOME TO CRS APPLICATION-----");
		System.out.println();
		System.out.println("Main Menu");
		System.out.println("-----------------------------");
		System.out.println("1. Login");
		System.out.println("2. New User");
		System.out.println("3. Update Password");
		System.out.println("4. Exit");
		System.out.println();
		System.out.println("Enter required option : ");
		int mainOption = sc.nextInt();
		sc.nextLine();
		switch(mainOption) {
		case 1 :	checkRespectiveRole();
					break;
		case 2 :	newUserCreation();
					break;
		case 3 :	updatePassword();
					break;
		case 4 :	System.out.println();
					System.out.println("Exiting Application");
					System.exit(0);
		}
	}
	
	private void updatePassword() {
		
		System.out.println();
		System.out.println("Enter creadentials");
		System.out.println("----------------------");
		System.out.println("Username");
		String userName = sc.nextLine();
		System.out.println("Old Password");
		String oldPassword = sc.nextLine();
		System.out.println("New Password");
		String newPassword = sc.nextLine();
		
		LoginValidation lv = new LoginValidation();
		lv.updateCredentials(userName, oldPassword, newPassword);
	}

	private void newUserCreation() {
		System.out.println();
		System.out.println("Please provide type of user: (Student/Professor/Admin)");
		String userType = sc.nextLine();
		if(CRSConstants.STUDENT.equalsIgnoreCase(userType)) {
			StudentHandler sh = new StudentHandlerImpl();
			sh.createStudent();
		} else if (CRSConstants.PROFESSOR.equalsIgnoreCase(userType)) {
			System.out.println("Under dev");
		} else if (CRSConstants.ADMIN.equalsIgnoreCase(userType)) {
			System.out.println("Under dev");
		} else {
			System.out.println("Invalid input");
		}
		
	}

	private void checkRespectiveRole() {
		System.out.println();
		System.out.println("Enter creadentials");
		System.out.println("----------------------");
		System.out.println("Username");
		String userName = sc.nextLine();
		System.out.println("Password");
		String password = sc.nextLine();
		LoginValidation lv = new LoginValidation();
		String role = lv.validateCredentials(userName, password);
		System.out.println();
		System.out.println("Logged in user \"" + userName + "\" as " + role);
		if(CRSConstants.STUDENT.equalsIgnoreCase(role)) {
			StudentCRSMenu sm = new StudentCRSMenu();
			try {
				sm.courseRegistration(userName);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InvalidCourseNameException e) {
				e.printStackTrace();
			} 
		} else if(CRSConstants.ADMIN.equalsIgnoreCase(role)) {
			AdminCRSMenu am = new AdminCRSMenu();
			am.adminLogin();
		} else if(CRSConstants.PROFESSOR.equalsIgnoreCase(role)) {
			ProfessorCRSMenu pm = new ProfessorCRSMenu();
			pm.professorLogin();
		} else {
			System.out.println("Invalid Role");
		}
	}
}
