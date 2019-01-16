package team9.model;

public class ClassInfo {
	private String id;			// 班级编号
	private String name;		// 班级名称
	private String number;		// 人数
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "ClassInfo [id=" + id + ", name=" + name + ", number=" + number + "]";
	}
}