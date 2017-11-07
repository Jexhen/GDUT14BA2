package xin.liaozhixing.class14ba2.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;

import xin.liaozhixing.class14ba2.dao.HomeworkDao;
import xin.liaozhixing.class14ba2.dao.StudentDao;
import xin.liaozhixing.class14ba2.dao.impl.StudentDaoImpl;
import xin.liaozhixing.class14ba2.domain.Homework;
import xin.liaozhixing.class14ba2.domain.Student;
import xin.liaozhixing.class14ba2.domain.Task;
import xin.liaozhixing.class14ba2.service.HomeworkService;

public class HomeworkServiceImpl implements HomeworkService {

	private HomeworkDao homeworkDao;
	
	@Override
	public boolean saveHomework(List<Homework> homeworks) {
		for (int i = 0; i < homeworks.size(); i++) {
			Homework homework = homeworks.get(i);
			String stu_id = homework.getStudent().getStu_id();
			Student student = homeworkDao.getStudent(stu_id);
			homework.setStudent(student);
			Integer task_id = homework.getTask().getTask_id();
			Task task = homeworkDao.getTask(task_id);
			homework.setTask(task);
		}
		return homeworkDao.addHomework(homeworks);
	}

	public void setHomeworkDao(HomeworkDao homeworkDao) {
		this.homeworkDao = homeworkDao;
	}

	@Override
	public List<Task> getTasks() {
		List<Task> tasks = homeworkDao.getTasks();
		return tasks;
	}

	@Override
	public List<Homework> getPostedHomeworkByTaskAndStudent(Integer task_id, String stu_id) {
		List<Homework> homeworks = null;
		if (task_id!=null && stu_id !=null && !stu_id.isEmpty()) 
			homeworks = homeworkDao.getPostedHomeworkByTaskAndStudent(task_id, stu_id);
		return homeworks;
	}

	@Override
	public boolean removeHomework(String homework_id) {
		boolean isOk = false;
		if (homework_id!=null&&!homework_id.isEmpty()) {
			Homework homework = homeworkDao.getHomework(homework_id);
			isOk = homeworkDao.removeHomework(homework);
			if (isOk) {
				String realPath = ServletActionContext.getServletContext().getRealPath(homework.getHomework_src());
				File file = new File(realPath);
				if (file.exists()) {
					file.delete();
				}
			}
		}
		return isOk;
	}

	@Override
	public List<Task> getTasks(Integer currentPage, Integer currentCount) {
		if (currentPage!=null&&currentCount!=null) {
			Integer firstIndex = (currentPage - 1) * currentCount;
			return homeworkDao.getTasks(firstIndex, currentCount);
		}
		return null;
	}

	@Override
	public Integer getTaskTotalCount() {
		return homeworkDao.getTaskTotalCount();
	}

	@Override
	public Task getTaskByTask_id(String task_id) {
		if (task_id!=null&&!task_id.isEmpty()) {
			return homeworkDao.getTaskByTask_id(Integer.valueOf(task_id));
		}
		return null;
	}

	@Override
	public Homework getHomeworkByHomework_name(String homework_name) {
		if (homework_name!=null) {
			return homeworkDao.getHomeworkByHomework_name(homework_name);
		}
		return null;
	}

	@Override
	public boolean delTask(String task_id) {
		if (task_id!=null&&!task_id.isEmpty()) {
			String realPath = ServletActionContext.getServletContext().getRealPath("homework" + File.separator + task_id);
			File file = new File(realPath);
			if (file.isDirectory()) {
				String[] list = file.list();
				for (int i=0; i<list.length; i++) {
	                File children = new File(file, list[i]);
	                if (children.exists()) 
	                	children.delete();
	            }
				if (file.exists())
					file.delete();
			}
			Task taskByTask_id = homeworkDao.getTaskByTask_id(Integer.valueOf(task_id));
			homeworkDao.delTask(taskByTask_id);
			return true;
		}
		return false;
	}

	@Override
	public void saveTask(Task task) {
		homeworkDao.saveTask(task);
	}

	@Override
	public List<Task> getTasksBySearchCondition(String task_name) {
		return homeworkDao.getTasksBySearchCondition(task_name);
	}

	@Override
	public Homework getHomeworkByHomework_id(String homework_id) {
		if (homework_id!=null&&!homework_id.isEmpty())
			return homeworkDao.getHomeworkByHomework_id(homework_id);
		return null;
	}

	

}
