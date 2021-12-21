package com.lt.crs.app;

import java.util.Scanner;

import com.lt.crs.constants.CRSConstants;

public class CRSApplication {
		
	static Scanner sc = new Scanner(System.in);
	CRSConstants crsConstants = new CRSConstants();
	
	public static void main(String[] args) {
		

		MainCRSMenu mainMenu = new MainCRSMenu();
		mainMenu.mainMenu();
		
	}	
}
