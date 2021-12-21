package com.lt.crs.constants;

public class SqlConstants {
	public static final  String userQuery = "select * from user";
	public static final String roleQuery="select role from role where id =";
	public static final String studentInsertQuery = "insert into student values(?,?,?,?,?)";
	public static final String courseInsertQuery ="insert into course values(?,?,?,?,?)";
	public static final String roleInsertQuery="insert into role values(?,?)";
	public static final String userInsertQuery="insert into user values(?,?,?,?,?)";
	public static final String roleIdQuery ="select id from role where role = 'Student'";
}
