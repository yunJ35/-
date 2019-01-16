package team9.model;

public class StudentInfo {
	private String id;   
	private String stuName;   
	private String enName;   
	private String sex;   
	private String grade;   
	private String eduSys;   
	private String faculty;   
	private String major;   
	private String stuMode; 
	private String education; 
	private String campus; 
	private String classId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getEduSys() {
		return eduSys;
	}
	public void setEduSys(String eduSys) {
		this.eduSys = eduSys;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getStuMode() {
		return stuMode;
	}
	public void setStuMode(String stuMode) {
		this.stuMode = stuMode;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	@Override
	public String toString() {
		return "StudentInfo [id=" + id + ", stuName=" + stuName + ", enName=" + enName + ", sex=" + sex + ", grade="
				+ grade + ", eduSys=" + eduSys + ", faculty=" + faculty + ", major=" + major + ", stuMode=" + stuMode
				+ ", education=" + education + ", campus=" + campus + ", classId=" + classId + "]";
	} 
}