package team9.model;

public class ResultInfo {
	private String semester;
	private String id;
	private String couId;
	private String result;
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCouId() {
		return couId;
	}
	public void setCouId(String couId) {
		this.couId = couId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "ResultInfo [semester=" + semester + ", id=" + id + ", couId=" + couId + ", result=" + result + "]";
	}
}