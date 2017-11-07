package xin.liaozhixing.class14ba2.domain;

public class EmployInfo {
	private Integer emp_info_id;
	private String emp_info_title;
	private String emp_info_content;
	private String emp_info_date;
	private String emp_info_deadline;
	private Integer emp_info_scan;
	private Student student;
	public Integer getEmp_info_id() {
		return emp_info_id;
	}
	public void setEmp_info_id(Integer emp_info_id) {
		this.emp_info_id = emp_info_id;
	}
	public String getEmp_info_title() {
		return emp_info_title;
	}
	public void setEmp_info_title(String emp_info_title) {
		this.emp_info_title = emp_info_title;
	}
	public String getEmp_info_content() {
		return emp_info_content;
	}
	public void setEmp_info_content(String emp_info_content) {
		this.emp_info_content = emp_info_content;
	}
	public String getEmp_info_deadline() {
		return emp_info_deadline;
	}
	public void setEmp_info_deadline(String emp_info_deadline) {
		this.emp_info_deadline = emp_info_deadline;
	}
	public Integer getEmp_info_scan() {
		return emp_info_scan;
	}
	public void setEmp_info_scan(Integer emp_info_scan) {
		this.emp_info_scan = emp_info_scan;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getEmp_info_date() {
		return emp_info_date;
	}
	public void setEmp_info_date(String emp_info_date) {
		this.emp_info_date = emp_info_date;
	}
}
