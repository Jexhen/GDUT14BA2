package xin.liaozhixing.class14ba2.service;

import java.util.List;

import xin.liaozhixing.class14ba2.domain.Homework;
import xin.liaozhixing.class14ba2.domain.Task;

public interface HomeworkService {
	public boolean saveHomework(List<Homework> homeworks);

	public List<Task> getTasks();

	public List<Homework> getPostedHomeworkByTaskAndStudent(Integer task_id, String stu_id);

	public boolean removeHomework(String homework_id);

	public List<Task> getTasks(Integer currentPage, Integer currentCount);

	public Integer getTaskTotalCount();

	public Task getTaskByTask_id(String task_id);

	public Homework getHomeworkByHomework_name(String homework_name);

	public boolean delTask(String task_id);

	public void saveTask(Task task);

	public List<Task> getTasksBySearchCondition(String task_name);

	public Homework getHomeworkByHomework_id(String homework_id);
}
