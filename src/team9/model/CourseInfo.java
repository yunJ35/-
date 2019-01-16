package team9.model;

public class CourseInfo {
	private String id;
	private String couName;
	private String couType;
	private String creditHour;
	private String couFaculty;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCouName() {
		return couName;
	}
	public void setCouName(String couName) {
		this.couName = couName;
	}
	public String getCouType() {
		return couType;
	}
	public void setCouType(String couType) {
		this.couType = couType;
	}
	public String getCreditHour() {
		return creditHour;
	}
	public void setCreditHour(String creditHour) {
		this.creditHour = creditHour;
	}
	public String getCouFaculty() {
		return couFaculty;
	}
	public void setCouFaculty(String couFaculty) {
		this.couFaculty = couFaculty;
	}
	@Override
	public String toString() {
		return "CourseInfo [id=" + id + ", couName=" + couName + ", couType=" + couType + ", creditHour=" + creditHour
				+ ", couFaculty=" + couFaculty + "]";
	}
}