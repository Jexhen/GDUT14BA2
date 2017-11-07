package xin.liaozhixing.class14ba2.service;

import java.util.List;

import xin.liaozhixing.class14ba2.domain.EmployInfo;
import xin.liaozhixing.class14ba2.domain.Notice;
import xin.liaozhixing.class14ba2.domain.Student;
import xin.liaozhixing.class14ba2.domain.Task;

public interface StudentService {
	
	public Student getStudent(Student stu);

	public List<Notice> getNotices(int currentPage, int currentCount);

	public List<EmployInfo> getemployInfos(int currentPage, int currentCount);
	
	public Student getVerifyCode(Student stu);
	
	public void updateStudent(Student student);

	public Student getExsitStudent(Student stu);

	public boolean validateVerifyCode(Student stu);

	public List<Student> getAdmin();

	public List<Student> getAllStudent();

	public List<Student> getStudents(Integer currentPage, Integer currentCount);

	public Integer getCountOfStudent();

	public List<Student> getStudentsBySearchCondition(String stu_id, String stu_name);

	public void saveStudent(Student stu);

	public boolean checkEmailAvaliable(String stu_email);

	public boolean delStudent(String stu_id);

	public Student getStudentByStu_id(String stu_id);
	
}
