package xin.liaozhixing.class14ba2.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import xin.liaozhixing.class14ba2.dao.HomeworkDao;
import xin.liaozhixing.class14ba2.domain.Homework;
import xin.liaozhixing.class14ba2.domain.Student;
import xin.liaozhixing.class14ba2.domain.Task;

public class HomeworkDaoImpl extends HibernateDaoSupport implements HomeworkDao {
	
	@Override
	public boolean addHomework(List<Homework> homeworks) {
		this.getHibernateTemplate().execute(new HibernateCallback<List>() {

			@Override
			public List doInHibernate(Session session) throws HibernateException {
				for (int i = 0; i < homeworks.size(); i++) {
					session.save(homeworks.get(i));
				}
				return null;
			}});
		return true;
	}

	public Student getStudent(String sid) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Student>() {

			@Override
			public Student doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("from Student where stu_id = ?");
				query.setParameter(0, sid);
				Student student = (Student) query.uniqueResult();
				return student;
			}});
	}

	@Override
	public Task getTask(Integer task_id) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Task>() {

			@Override
			public Task doInHibernate(Session session) throws HibernateException {
				Task task = session.get(Task.class, task_id);
				return task;
			}
			
		});
	}

	@Override
	public List<Task> getTasks() {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Task>>()  {

			@Override
			public List<Task> doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery("from Task");
				List<Task> tasks = query.list();
				return tasks;
			}
			
		});
	}

	@Override
	public List<Homework> getPostedHomeworkByTaskAndStudent(Integer task_id, String stu_id) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Homework>>() {

			@Override
			public List<Homework> doInHibernate(Session session) throws HibernateException {
				String hql = "from Homework where homework_task_id=? and homework_stu_id=?";
				Query query = session.createQuery(hql);
				query.setParameter(0, task_id);
				query.setParameter(1, stu_id);
				List<Homework> homeworks = query.list();
				return homeworks;
			}});
	}

	@Override
	public boolean removeHomework(Homework homework) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Boolean>() {

			@Override
			public Boolean doInHibernate(Session session) throws HibernateException {
				session.delete(homework);
				return true;
				
			}});
	}

	@Override
	public Homework getHomework(String homework_id) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Homework>() {

			@Override
			public Homework doInHibernate(Session session) throws HibernateException {
				Homework homework = session.get(Homework.class, Integer.valueOf(homework_id));
				return homework;
			}
			
		});
	}

	@Override
	public List<Task> getTasks(Integer firstIndex, Integer currentCount) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Task>>() {

			@Override
			public List<Task> doInHibernate(Session session) throws HibernateException {
				String hql = "from Task order by task_deadline desc";
				Query query = session.createQuery(hql);
				query.setFirstResult(firstIndex);
				query.setMaxResults(currentCount);
				List<Task> tasks = query.list();
				return tasks;
			}});
	}

	@Override
	public Integer getTaskTotalCount() {
		return this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				String hql = "select count(*) from Task";
				Query query = session.createQuery(hql);
				Long count = (Long) query.uniqueResult();
				return count.intValue();
			}});
	}

	@Override
	public Task getTaskByTask_id(Integer task_id) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Task>() {

			@Override
			public Task doInHibernate(Session session) throws HibernateException {
				return session.get(Task.class, task_id);
			}});
	}

	@Override
	public Homework getHomeworkByHomework_name(String homework_name) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Homework>() {

			@Override
			public Homework doInHibernate(Session session) throws HibernateException {
				String hql = "from Homework where homework_name=?";
				Query query = session.createQuery(hql);
				query.setParameter(0, homework_name);
				Homework homework = (Homework) query.uniqueResult();
				return homework;
			}});
	}

	@Override
	public void delTask(Task taskByTask_id) {
		this.getHibernateTemplate().execute(new HibernateCallback<Task>() {

			@Override
			public Task doInHibernate(Session session) throws HibernateException {
				session.delete(taskByTask_id);
				return null;
			}});
	}

	@Override
	public void saveTask(Task task) {
		this.getHibernateTemplate().execute(new HibernateCallback<Task>() {

			@Override
			public Task doInHibernate(Session session) throws HibernateException {
				session.save(task);
				return null;
			}});
	}

	@Override
	public List<Task> getTasksBySearchCondition(String task_name) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Task>>() {
			@Override
			public List<Task> doInHibernate(Session session) throws HibernateException {
				String sql = "select * from task where task_name like '%" + task_name + "%' order by task_deadline desc";
				SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Task.class);
				List<Task> list = sqlQuery.list();
				return list;
			}});
	}

	@Override
	public Homework getHomeworkByHomework_id(String homework_id) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Homework>() {

			@Override
			public Homework doInHibernate(Session session) throws HibernateException {
				Homework homework = session.get(Homework.class, Integer.valueOf(homework_id));
				return homework;
			}});
	}
}
