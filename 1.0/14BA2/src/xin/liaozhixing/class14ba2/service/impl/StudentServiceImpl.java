package xin.liaozhixing.class14ba2.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;

import xin.liaozhixing.class14ba2.dao.StudentDao;
import xin.liaozhixing.class14ba2.domain.EmployInfo;
import xin.liaozhixing.class14ba2.domain.Notice;
import xin.liaozhixing.class14ba2.domain.Student;
import xin.liaozhixing.class14ba2.domain.Task;
import xin.liaozhixing.class14ba2.exception.LoginException;
import xin.liaozhixing.class14ba2.exception.PasswordException;
import xin.liaozhixing.class14ba2.service.StudentService;
import xin.liaozhixing.class14ba2.utils.MailUtils;

public class StudentServiceImpl implements StudentService {
	
	private StudentDao studentDao;
	
	@Override
	public Student getStudent(Student stu) {
		String stu_id = stu.getStu_id();
		Student existStu = studentDao.getStudentBySid(stu_id);
		if (existStu == null) {
			throw new LoginException("ѧ�Ŵ���");
		} else {
			String stu_password = stu.getStu_password();
			if (!stu_password.equals(existStu.getStu_password()))
				throw new LoginException("�������");
		}
		return existStu;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	@Override
	public List<Notice> getNotices(int currentPage, int currentCount) {
		List<Notice> notices = studentDao.getNotices(currentPage, currentCount);
		return notices;
	}

	@Override
	public List<EmployInfo> getemployInfos(int currentPage, int currentCount) {
		List<EmployInfo> employInfos = studentDao.getemployInfos(currentPage, currentCount);
		return employInfos;
	}

	@Override
	public Student getVerifyCode(Student stu) {
		if (stu!=null) {
			String stu_id = stu.getStu_id();
			if (stu_id!=null&&!stu_id.isEmpty()) {
				Student existStu = studentDao.getStudentBySid(stu_id);
				if (existStu==null) 
					throw new PasswordException("���û�������");
				String stu_email = existStu.getStu_email();
				if (stu_email==null)
					throw new PasswordException("���û�û�м���,��ʹ�ó�ʼ������е�¼����!!!");
				Random rand = new Random(System.currentTimeMillis());
				StringBuffer sb = new StringBuffer();
				for (int i=0;i<6;i++) {
					sb.append(rand.nextInt(10));
				}
				if (sb!=null) {
					String verifyCode = sb.toString();
					existStu.setVerify_code(verifyCode);
					Timestamp verify_time = new Timestamp(System.currentTimeMillis()+30*60*1000);//����ǰʱ�����30����
					existStu.setVerify_time(verify_time);
//					long verifyDateTime = verify_date.getTime() / 1000 * 1000;
					String email = existStu.getStu_email();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
					Date date = new Date();
					String format = sdf.format(date);
					String emailMsg = "<strong>�װ���"+existStu.getStu_name()+
							"ͬѧ��</strong><br/>���ã���л��ʹ���й���2����������ڽ���������֤�������������֤��Ϊ��<br/><strong style='color: #FE9800;font-size:large;'>" + 
							verifyCode + "</strong><span style='color:#979797'>(Ϊ�˱������ʺŵİ�ȫ�ԣ�����30�����������֤��)</span><br/>" + 
							"�й���2��<br/>" + format;
					try {
						MailUtils.sendMail(email, emailMsg);
					} catch (MessagingException e) {
						throw new PasswordException("�޷������ʼ���");
					}
					return existStu;
				}
			}
		}
		return null;
	}

	@Override
	public void updateStudent(Student student) {
		if (student!=null)
			studentDao.updateStudent(student);
	}

	@Override
	public Student getExsitStudent(Student stu) {
		if (stu!=null) {
			String stu_id = stu.getStu_id();
			if (stu_id!=null&&!stu_id.isEmpty()) 
				return studentDao.getStudentBySid(stu_id);
		}
		return null;
	}

	@Override
	public boolean validateVerifyCode(Student stu) {
		if (stu!=null) {
			String stu_id = stu.getStu_id();
			if (stu_id!=null&&!stu_id.isEmpty()) {
				Student student = studentDao.getStudentBySid(stu_id);
				if (student==null)
					throw new PasswordException("ѧ�Ų����ڣ�");
				if (!stu.getVerify_code().equals(student.getVerify_code()))
					throw new PasswordException("��֤���������ϸ�˶��������֤�룡");
				else if (student.getVerify_time().getTime() <= System.currentTimeMillis())
					throw new PasswordException("��֤���Ѿ����ڣ������»�ȡ��֤�룡");
				else {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List<Student> getAdmin() {
		return studentDao.getAdmin();
	}

	@Override
	public List<Student> getAllStudent() {
		return studentDao.getAllStudent();
	}

	@Override
	public List<Student> getStudents(Integer currentPage, Integer currentCount) {
		if (currentPage!=null&&currentCount!=null) {
			Integer firstIndex = (currentPage - 1) * currentCount;
			List<Student> students = studentDao.getStudents(firstIndex, currentCount);
			return students;
		}
		return null;
	}

	@Override
	public Integer getCountOfStudent() {
		return studentDao.getCountOfStudent();
	}

	@Override
	public List<Student> getStudentsBySearchCondition(String stu_id, String stu_name) {
		return studentDao.getStudentBySearchCondition(stu_id, stu_name);
	}

	@Override
	public void saveStudent(Student stu) {
		if (stu!=null&&stu.getStu_id()!=null&&stu.getStu_name()!=null)
			studentDao.saveStudent(stu);
		else 
			throw new RuntimeException("ѧ�ź�ѧ����������Ϊ��");
	}

	@Override
	public boolean checkEmailAvaliable(String stu_email) {
		if (stu_email!=null&&!stu_email.isEmpty()) {
			Student stu = studentDao.getStudentByEmail(stu_email);
			if (stu==null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delStudent(String stu_id) {
		if (stu_id!=null&&!stu_id.isEmpty()) {
			studentDao.delStudent(stu_id);
			return true;
		}
		return false;
	}

	@Override
	public Student getStudentByStu_id(String stu_id) {
		if (stu_id!=null&&!stu_id.isEmpty())
			return studentDao.getStudentBySid(stu_id);
		return null;
	}

	
}
