package team9.model;

import team9.control.utils.UserVerify;

public class User {
	private static String userName;
	private static String name;
	private static String password;
	private static String roleInfo;
	
	public static boolean passwordVerify() {
		if (UserVerify.userVerify(userName, password)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String getUserName() {
		return userName;
	}
	public static void setUserName(String userName) {
		User.userName = userName;
	}
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		User.name = name;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		User.password = password;
	}
	public static String getRoleInfo() {
		return roleInfo;
	}
	public static void setRoleInfo(String roleInfo) {
		switch (roleInfo) {
		case "manager":
			User.roleInfo = "管理员";
			break;
		case "student":
			User.roleInfo = "学生";
			break;
		case "teacher":
			User.roleInfo = "教师";
			break;
		}
	}
}