package xin.liaozhixing.class14ba2.domain;

import java.util.HashSet;
import java.util.Set;

public class Task {
	private Integer task_id;
	private String task_name;
	private String task_deadline;
	private Set<Homework> homeworks = new HashSet<Homework>();
	public Integer getTask_id() {
		return task_id;
	}
	public void setTask_id(Integer task_id) {
		this.task_id = task_id;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getTask_deadline() {
		return task_deadline;
	}
	public void setTask_deadline(String task_deadline) {
		this.task_deadline = task_deadline;
	}
	@Override
	public String toString() {
		return "Task [task_id=" + task_id + ", task_name=" + task_name + ", task_deadline=" + task_deadline + "]";
	}
	public Set<Homework> getHomeworks() {
		return homeworks;
	}
	public void setHomeworks(Set<Homework> homeworks) {
		this.homeworks = homeworks;
	}
	
}
