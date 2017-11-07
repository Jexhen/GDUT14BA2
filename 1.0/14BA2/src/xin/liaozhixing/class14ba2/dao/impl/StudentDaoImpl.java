package xin.liaozhixing.class14ba2.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import xin.liaozhixing.class14ba2.dao.StudentDao;
import xin.liaozhixing.class14ba2.domain.EmployInfo;
import xin.liaozhixing.class14ba2.domain.Notice;
import xin.liaozhixing.class14ba2.domain.Student;
import xin.liaozhixing.class14ba2.domain.Task;

public class StudentDaoImpl extends HibernateDaoSupport implements StudentDao {

	@Override
	public Student getStudentBySid(String sid) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Student>() {
			@Override
			public Student doInHibernate(Session session) throws HibernateException {
				String hql = "from Student where stu_id = ?";
				org.hibernate.Query query = session.createQuery(hql);
				query.setParameter(0, sid);
				Student stu = (Student) query.uniqueResult();
				return stu;
			}});
	}

	@Override
	public List<Notice> getNotices(int currentPage, int currentCount) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Notice>>() {

			@Override
			public List<Notice> doInHibernate(Session session) throws HibernateException {
				String hql = "from Notice notice order by notice.notice_date desc";
				Query query = session.createQuery(hql);
				query.setFirstResult(currentPage - 1);
				query.setMaxResults(currentCount);
				List<Notice> notices = query.list();
				return notices;
			}});
	}

	@Override
	public List<EmployInfo> getemployInfos(int currentPage, int currentCount) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<EmployInfo>>() {

			@Override
			public List<EmployInfo> doInHibernate(Session session) throws HibernateException {
				String hql = "from EmployInfo employInfo order by employInfo.emp_info_date desc";
				Query query = session.createQuery(hql);
				query.setFirstResult(currentPage - 1);
				query.setMaxResults(currentCount);
				List<EmployInfo> employInfos = query.list();
				return employInfos;
			}});
	}

	@Override
	public void updateStudent(Student existStu) {
		this.getHibernateTemplate().execute(new HibernateCallback<Student>() {

			@Override
			public Student doInHibernate(Session session) throws HibernateException {
				session.update(existStu);
				return null;
			}
			
		});
	}

	@Override
	public List<Student> getAdmin() {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Student>>() {

			@Override
			public List<Student> doInHibernate(Session session) throws HibernateException {
				String hql = "from Student stu where stu.isadmin=?";
				Query query = session.createQuery(hql);
				query.setParameter(0, 1);
				List<Student> list = query.list();
				return list;
			}});
	}

	@Override
	public List<Student> getAllStudent() {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Student>>() {

			@Override
			public List<Student> doInHibernate(Session session) throws HibernateException {
				String hql = "from Student order by stu_id asc";
				Query query = session.createQuery(hql);
				List<Student> students = query.list();
				return students;
			}});
	}

	@Override
	public List<Student> getStudents(Integer firstIndex, Integer currentCount) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Student>>() {

			@Override
			public List<Student> doInHibernate(Session session) throws HibernateException {
				String hql = "from Student stu order by stu.stu_id asc";
				Query query = session.createQuery(hql);
				query.setFirstResult(firstIndex);
				query.setMaxResults(currentCount);
				List<Student> students = query.list();
				return students;
			}
			
		});
	}

	@Override
	public Integer getCountOfStudent() {
		return this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				String hql = "select count(*) from Student";
				Long count = (Long) session.createQuery(hql).uniqueResult();
				return count.intValue();
			}
			
		});
	}

	@Override
	public List<Student> getStudentBySearchCondition(String stu_id, String stu_name) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Student>>() {

			@Override
			public List<Student> doInHibernate(Session session) throws HibernateException {
				String sql = "select * from Student where 1=1";
				if (stu_id!=null&&!stu_id.isEmpty()) 
					sql += " and stu_id like '%" + stu_id + "%'";
				if (stu_name!=null&&!stu_name.isEmpty())
					sql += " and stu_name like '%" + stu_name + "%'";
				SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Student.class);
				List<Student> students = sqlQuery.list();
				return students;
			}
			
		});
	}

	@Override
	public void saveStudent(Student stu) {
		this.getHibernateTemplate().execute(new HibernateCallback<Student>() {

			@Override
			public Student doInHibernate(Session session) throws HibernateException {
				session.save(stu);
				return null;
			}});
	}

	@Override
	public Student getStudentByEmail(String stu_email) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Student>() {

			@Override
			public Student doInHibernate(Session session) throws HibernateException {
				String hql = "from Student stu where stu.stu_email=?";
				Query query = session.createQuery(hql);
				query.setParameter(0, stu_email);
				Student student = (Student) query.uniqueResult();
				return student;
			}});
	}

	@Override
	public void delStudent(String stu_id) {
		this.getHibernateTemplate().execute(new HibernateCallback<Student>() {

			@Override
			public Student doInHibernate(Session session) throws HibernateException {
				String hql = "from Student where stu_id=?";
				Query query = session.createQuery(hql);
				query.setParameter(0, stu_id);
				Student student = (Student) query.uniqueResult();
				session.delete(student);
				return null;
			}});
	}

	

}
