package team9.model;

public class UserInfo {
	private String userName;
	private String password;
	private String roleInfo;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoleInfo() {
		return roleInfo;
	}
	public void setRoleInfo(String roleInfo) {
		this.roleInfo = roleInfo;
	}
	@Override
	public String toString() {
		return "UserInfo [userName=" + userName + ", password=" + password + ", roleInfo=" + roleInfo + "]";
	}
}