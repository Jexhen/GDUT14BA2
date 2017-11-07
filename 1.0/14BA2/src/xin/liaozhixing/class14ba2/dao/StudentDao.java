package xin.liaozhixing.class14ba2.dao;

import java.util.List;

import xin.liaozhixing.class14ba2.domain.EmployInfo;
import xin.liaozhixing.class14ba2.domain.Notice;
import xin.liaozhixing.class14ba2.domain.Student;
import xin.liaozhixing.class14ba2.domain.Task;

public interface StudentDao {
	Student getStudentBySid(String sid);

	List<Notice> getNotices(int currentPage, int currentCount);

	List<EmployInfo> getemployInfos(int currentPage, int currentCount);

	void updateStudent(Student existStu);
	
	List<Student> getAdmin();

	List<Student> getAllStudent();

	List<Student> getStudents(Integer firstIndex, Integer currentCount);

	Integer getCountOfStudent();

	List<Student> getStudentBySearchCondition(String stu_id, String stu_name);

	void saveStudent(Student stu);

	Student getStudentByEmail(String stu_email);

	void delStudent(String stu_id);
}
