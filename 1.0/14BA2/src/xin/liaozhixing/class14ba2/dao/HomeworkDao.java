package xin.liaozhixing.class14ba2.dao;

import java.util.List;

import xin.liaozhixing.class14ba2.domain.Homework;
import xin.liaozhixing.class14ba2.domain.Student;
import xin.liaozhixing.class14ba2.domain.Task;

public interface HomeworkDao {
	public boolean addHomework(List<Homework> homeworks);
	public Student getStudent(String sid);
	public Task getTask(Integer task_id);
	public List<Task> getTasks();
	public List<Homework> getPostedHomeworkByTaskAndStudent(Integer task_id, String stu_id);
	public boolean removeHomework(Homework homework);
	public Homework getHomework(String homework_id);
	public List<Task> getTasks(Integer firstIndex, Integer currentCount);
	public Integer getTaskTotalCount();
	public Task getTaskByTask_id(Integer valueOf);
	public Homework getHomeworkByHomework_name(String homework_name);
	public void delTask(Task taskByTask_id);
	public void saveTask(Task task);
	public List<Task> getTasksBySearchCondition(String task_name);
	public Homework getHomeworkByHomework_id(String homework_id);
}
