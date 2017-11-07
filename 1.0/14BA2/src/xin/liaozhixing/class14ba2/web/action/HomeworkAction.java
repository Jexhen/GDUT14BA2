package xin.liaozhixing.class14ba2.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;
import xin.liaozhixing.class14ba2.domain.EmployInfo;
import xin.liaozhixing.class14ba2.domain.Homework;
import xin.liaozhixing.class14ba2.domain.Notice;
import xin.liaozhixing.class14ba2.domain.Student;
import xin.liaozhixing.class14ba2.domain.Task;
import xin.liaozhixing.class14ba2.service.HomeworkService;

public class HomeworkAction extends ActionSupport {
	
	private String task_id;
	private String stu_id;
	private String stu_name;
	private String homework_id;
	private List<File> attach;
	private List<String> attachFileName;
	private List<String> attachContentType;
	private HomeworkService homeworkService;
	
	public String getTasks() throws Exception {
		List<Task> tasks = homeworkService.getTasks();
		List<Homework> homeworks = null;
		if (tasks!=null&&tasks.size()!=0) {
			homeworks = homeworkService.getPostedHomeworkByTaskAndStudent(tasks.get(0).getTask_id(), stu_id);
			ActionContext.getContext().put("tasks", tasks);
		}
		if (homeworks!=null&&homeworks.size()!=0)
			ActionContext.getContext().put("homeworks", homeworks);
		return "toHomework";
	}
	
	public String getPostedHomeworkByTaskAndStudent() {
		List<Homework> homeworks = homeworkService.getPostedHomeworkByTaskAndStudent(Integer.valueOf(task_id), stu_id);
		if (homeworks!=null&&homeworks.size()!=0)
			ActionContext.getContext().put("homeworks", homeworks);
		return "toHomework";
	}
	
	public String saveHomework() throws Exception {
		String realPath = ServletActionContext.getRequest().getServletContext().getRealPath("/homework/" + task_id);
		List<Homework> homeworks = new ArrayList<Homework>();
		for (int i = 0; i < attach.size(); i++) {
			//检测是否重复提交作业
			String string = attachFileName.get(i);
			String homework_name = task_id + "$" + stu_id + "$" + string; 
			Homework existHomework = homeworkService.getHomeworkByHomework_name(homework_name);
			//重复提交，检查下一个附件
			if (existHomework!=null) {
				continue;
			} else {
			//无重复提交存入
				Homework homework = new Homework();
				homework.setHomework_name(homework_name);
				if (stu_id!=null&&!stu_id.isEmpty()&&task_id!=null&&!task_id.isEmpty()) {
					Student stu = new Student();
					stu.setStu_id(stu_id);
					homework.setStudent(stu);
					Task task = new Task();
					task.setTask_id(Integer.valueOf(task_id));
					homework.setTask(task);
					homeworks.add(homework);
					String regex = "\\.[a-zA-Z]+";
				    Pattern pt = Pattern.compile(regex);
				    Matcher mt = pt.matcher(string);
				    String postFix = null;
				    if (mt.find()) 
				      postFix = mt.group();
					String fileName =  stu_id + stu_name + "(" + i +")"+ postFix;
					File file = new File(realPath + File.separator + fileName);
					if(!file.exists()){
					    //先得到文件的上级目录，并创建上级目录，在创建文件
					    file.getParentFile().mkdir();
					    try {
					        //创建文件
					        file.createNewFile();
					    } catch (IOException e) {
					        e.printStackTrace();
					    }
					}
					InputStream is = new FileInputStream(attach.get(i));
					OutputStream os = new FileOutputStream(file);
					byte[] buf = new byte[1024];
					int length;
					while ((length = is.read(buf))!=-1) {
						os.write(buf, 0, length);
					}
					os.close();
					is.close();
					homework.setHomework_src("homework" + File.separator  + task_id + File.separator + fileName);
					homework.setHomework_content_type(attachContentType.get(i));
				}
			}
					
		}
		boolean isOk = homeworkService.saveHomework(homeworks);
		ActionContext.getContext().put("isOk", isOk);
		List<Task> tasks = homeworkService.getTasks();
		if (tasks!=null&&tasks.size()!=0)
			ActionContext.getContext().put("tasks", tasks);
		return "toHomework";
	}
	
	
	public String removeHomework() throws Exception {
		boolean isOk = homeworkService.removeHomework(homework_id);
		ServletActionContext.getResponse().getWriter().write(Boolean.toString(isOk));
		return null;
	}
	
	public String getHomeworkByTask_id() throws Exception {
		List<Homework> homeworks = homeworkService.getPostedHomeworkByTaskAndStudent(Integer.valueOf(task_id), stu_id);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT); 
		jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer"});  
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object obj, String name, Object value) {
				if (obj instanceof Notice && name.equals("student"))
					return true;
				if (obj instanceof Student && name.equals("notices"))
					return true;
				if (obj instanceof Student && name.equals("homeworks"))
					return true;
				if (obj instanceof Student && name.equals("employInfos"))
					return true;
				if (obj instanceof Homework && name.equals("student"))
					return true;
				return false;
			}});
		String json = JSONArray.fromObject(homeworks, jsonConfig).toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
		return null;
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public List<File> getAttach() {
		return attach;
	}

	public void setAttach(List<File> attach) {
		this.attach = attach;
	}

	public List<String> getAttachFileName() {
		return attachFileName;
	}

	public void setAttachFileName(List<String> attachFileName) {
		this.attachFileName = attachFileName;
	}

	public List<String> getAttachContentType() {
		return attachContentType;
	}

	public void setAttachContentType(List<String> attachContentType) {
		this.attachContentType = attachContentType;
	}

	public String getStu_id() {
		return stu_id;
	}

	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}

	public void setHomeworkService(HomeworkService homeworkService) {
		this.homeworkService = homeworkService;
	}

	public String getHomework_id() {
		return homework_id;
	}

	public void setHomework_id(String homework_id) {
		this.homework_id = homework_id;
	}

	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}

	
}
