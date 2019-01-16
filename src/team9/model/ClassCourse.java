package team9.model;

public class ClassCourse {
	private String semester;          
	private String classId;          
	private String couId;
	private String teaId;          
	private String examType;          
	private String examDate;          
	private String examTime;          
	private String examAddr;
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getCouId() {
		return couId;
	}
	public void setCouId(String couId) {
		this.couId = couId;
	}
	public String getTeaId() {
		return teaId;
	}
	public void setTeaId(String teaId) {
		this.teaId = teaId;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	public String getExamAddr() {
		return examAddr;
	}
	public void setExamAddr(String examAddr) {
		this.examAddr = examAddr;
	}
	@Override
	public String toString() {
		return "ClassCourse [semester=" + semester + ", classId=" + classId + ", couId=" + couId + ", teaId=" + teaId
				+ ", examType=" + examType + ", examDate=" + examDate + ", examTime=" + examTime + ", examAddr="
				+ examAddr + "]";
	}
	
}