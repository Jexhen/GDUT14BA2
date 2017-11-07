package xin.liaozhixing.class14ba2.domain;

public class Homework {
	private Integer homework_id;
	private String homework_name;
	private String homework_src;
	private String homework_content_type;
	private Student student;
	private Task task;
	public Integer getHomework_id() {
		return homework_id;
	}
	public void setHomework_id(Integer homework_id) {
		this.homework_id = homework_id;
	}
	public String getHomework_src() {
		return homework_src;
	}
	public void setHomework_src(String homework_src) {
		this.homework_src = homework_src;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public String getHomework_name() {
		return homework_name;
	}
	public void setHomework_name(String homework_name) {
		this.homework_name = homework_name;
	}
	public String getHomework_content_type() {
		return homework_content_type;
	}
	public void setHomework_content_type(String homework_content_type) {
		this.homework_content_type = homework_content_type;
	}
	
}
