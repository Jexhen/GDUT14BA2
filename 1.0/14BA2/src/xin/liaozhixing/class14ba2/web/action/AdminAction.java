package xin.liaozhixing.class14ba2.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import xin.liaozhixing.class14ba2.domain.Carousel;
import xin.liaozhixing.class14ba2.domain.EmployInfo;
import xin.liaozhixing.class14ba2.domain.Homework;
import xin.liaozhixing.class14ba2.domain.Module;
import xin.liaozhixing.class14ba2.domain.Notice;
import xin.liaozhixing.class14ba2.domain.NoticeAttach;
import xin.liaozhixing.class14ba2.domain.Student;
import xin.liaozhixing.class14ba2.domain.Task;
import xin.liaozhixing.class14ba2.service.CarouselService;
import xin.liaozhixing.class14ba2.service.EmploymentService;
import xin.liaozhixing.class14ba2.service.HomeworkService;
import xin.liaozhixing.class14ba2.service.ModuleService;
import xin.liaozhixing.class14ba2.service.NoticeService;
import xin.liaozhixing.class14ba2.service.StudentService;
import xin.liaozhixing.class14ba2.utils.PageBean;
import xin.liaozhixing.class14ba2.utils.ZipUtils;

public class AdminAction extends ActionSupport {
	private StudentService studentService;
	private ModuleService moduleService;
	private NoticeService noticeService;
	private HomeworkService homeworkService;
	private EmploymentService employmentService;
	private CarouselService carouselService;
	private String currentPage;
	private String currentCount;
	private String stu_id;
	private String stu_name;
	private String notice_id;
	private String notice_title;
	private String notice_content;
	private String notice_abstract;
	private List<File> attach;
	private List<String> attachFileName;
	private List<String> attachContentType;
	private String notice_attach_id;
	private String notice_date;
	private String task_id;
	private String task_name;
	private String task_deadline;
	private String homework_id;
	private String emp_info_id;
	private String emp_info_title;
	private String emp_info_deadline;
	private String emp_info_content;
	private String emp_info_date;
	private String crs_prior;
	private String crs_id;
	private String crs_desc;
	
	public String welcomeAdmin() throws Exception {
		List<Module> modules = moduleService.getModules();
		if (modules!=null&&!modules.isEmpty())
			ActionContext.getContext().put("modules", modules);
		return "toAdminWelcome";
	}
	
	public String getAdministrators() throws Exception {
		List<Student> admin = studentService.getAdmin();
		List<Student> students = studentService.getAllStudent();
		if (admin!=null) {
			ActionContext.getContext().put("admins", admin);
		}
		if (students!=null&&!students.isEmpty()) {
			ActionContext.getContext().put("students", students);
		}
		return "toAdministrator";
	}
	
	public String addAdmin() throws Exception {
		if (stu_id!=null&&!stu_id.isEmpty()) {
			Student stu = new Student();
			stu.setStu_id(stu_id);
			Student exsitStudent = studentService.getExsitStudent(stu);
			if (exsitStudent!=null) {
				if (exsitStudent.getIsadmin()==null||exsitStudent.getIsadmin()!=1) {
					exsitStudent.setIsadmin(1);
					studentService.updateStudent(exsitStudent);
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write("ok");
				}
			}
		}
		return null;
	}
	
	public String delAdmin() throws Exception {
		if (stu_id!=null&&!stu_id.isEmpty()) {
			Student stu = new Student();
			stu.setStu_id(stu_id);
			Student exsitStudent = studentService.getExsitStudent(stu);
			if (exsitStudent!=null) {
				if (exsitStudent.getIsadmin()==1) {
					exsitStudent.setIsadmin(0);
					studentService.updateStudent(exsitStudent);
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write("ok");
				}
			}
		}
		return null;
	}
	
	public String getStudents() throws Exception {
		if (currentPage!=null&&!currentPage.isEmpty()&&currentCount!=null&&!currentCount.isEmpty()) {
			List<Student> students = studentService.getStudents(Integer.valueOf(currentPage), Integer.valueOf(currentCount));
			if (students!=null&&!students.isEmpty()) {
				PageBean<Student> page = new PageBean<Student>();
				page.setCurrentCount(Integer.valueOf(currentCount));
				page.setCurrentPage(Integer.valueOf(currentPage));
				Integer totalCount = studentService.getCountOfStudent();
				page.setTotalCount(totalCount );
				Integer totalPage = (int) Math.ceil(totalCount*1.0/Integer.valueOf(currentCount));
				page.setTotalPage(totalPage );
				page.setList(students);
				ActionContext.getContext().put("page", page);
			} else {
				PageBean<Student> page = new PageBean<Student>();
				page.setCurrentCount(0);
				page.setCurrentPage(1);
				page.setTotalCount(0);
				page.setTotalPage(1);
				page.setList(students);
				ActionContext.getContext().put("page", page);
			}
		}
		return "toStudent";
	}
	
	public String getStudentsBySearchCondition() throws Exception {
		List<Student> students = studentService.getStudentsBySearchCondition(stu_id, stu_name);
		if (students!=null&&!students.isEmpty()) {
			PageBean<Student> page = new PageBean<Student>();
			page.setCurrentCount(students.size());
			page.setCurrentPage(1);
			page.setTotalCount(students.size());
			page.setTotalPage(1);
			page.setList(students);
			ActionContext.getContext().put("page", page);
			if (stu_id!=null&&!stu_id.isEmpty())
				ActionContext.getContext().put("stu_id", stu_id);
			if (stu_name!=null&&!stu_name.isEmpty())
				ActionContext.getContext().put("stu_name", stu_name);
		} else {
			PageBean<Student> page = new PageBean<Student>();
			page.setCurrentCount(0);
			page.setCurrentPage(1);
			page.setTotalCount(0);
			page.setTotalPage(1);
			page.setList(students);
			ActionContext.getContext().put("page", page);
			if (stu_id!=null&&!stu_id.isEmpty())
				ActionContext.getContext().put("stu_id", stu_id);
			if (stu_name!=null&&!stu_name.isEmpty())
				ActionContext.getContext().put("stu_name", stu_name);
		}
		return "toStudent";
	}
	
	public String delStudent() throws Exception {
		boolean isOk = studentService.delStudent(stu_id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		if (isOk)
			response.getWriter().write("删除成功！");
		else 
			response.getWriter().write("删除失败！");
		return null;
	}
	
	public String getNotices() throws Exception {
		if (currentPage!=null&&!currentPage.isEmpty()&&currentCount!=null&&!currentCount.isEmpty()) {
			List<Notice> notices = noticeService.getNotices(Integer.valueOf(currentPage), Integer.valueOf(currentCount));
			if (notices!=null&&!notices.isEmpty()) {
				PageBean<Notice> page = new PageBean<Notice>();
				page.setCurrentCount(Integer.valueOf(currentCount));
				page.setCurrentPage(Integer.valueOf(currentPage));
				Integer totalCount = noticeService.getNoticeTotalCount();
				page.setTotalCount(totalCount);
				Integer totalPage = (int) Math.ceil(totalCount*1.0/Integer.valueOf(currentCount));
				page.setTotalPage(totalPage);
				page.setList(notices);
				ActionContext.getContext().put("page", page);
			} else {
				PageBean<Notice> page = new PageBean<Notice>();
				page.setCurrentCount(0);
				page.setCurrentPage(1);
				page.setTotalCount(0);
				page.setTotalPage(1);
				page.setList(notices);
				ActionContext.getContext().put("page", page);
			}
		}
		return "toNotice";
	}
	
	public String delNotice() throws Exception {
		boolean isOk = noticeService.delNotice(notice_id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		if (isOk)
			response.getWriter().write("删除成功！");
		else 
			response.getWriter().write("删除失败！");
		return null;
	}
	
	public String editNotice() throws Exception {
		Notice notice = noticeService.getNoticeByNotice_id(Integer.valueOf(notice_id));
		if (notice!=null) 
			ActionContext.getContext().put("notice", notice);
		return "toEditNotice"; 
	}
	
	public String saveNotice() throws Exception {
		if (notice_title!=null&&!notice_title.isEmpty()&&notice_content!=null&&!notice_content.isEmpty()&&notice_abstract!=null&&!notice_abstract.isEmpty()) {
			Notice notice = new Notice();
			notice.setNotice_title(notice_title);
			notice.setNotice_content(notice_content);
			notice.setNotice_abstract(notice_abstract);
			Student student = studentService.getStudentByStu_id(stu_id);
			notice.setStudent(student);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = new Date();
			String notice_date = sdf.format(date);
			notice.setNotice_date(notice_date);
			notice.setNotice_scan(0);
			if (attach!=null) {
				for (int i=0; i<attach.size(); i++) {
					String realPath = ServletActionContext.getServletContext().getRealPath("noticeAttach");
					File file = new File(realPath + File.separator + attachFileName.get(i));
					if (!file.exists()) {
						file.getParentFile().mkdir();
						file.createNewFile();
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
					NoticeAttach noticeAttach = new NoticeAttach();
					noticeAttach.setNoitce_attach_name(attachFileName.get(i));
					noticeAttach.setNotice_attach_src("noticeAttach/"+attachFileName.get(i));
					noticeAttach.setNotice_attach_content_type(attachContentType.get(i));
					notice.getAttachs().add(noticeAttach);
				}
			}
			noticeService.saveNotice(notice);
			ActionContext.getContext().put("successMsg", "发布通知成功");
		} else 
			ActionContext.getContext().put("successMsg", "发布通知失败(请检查标题和内容是否为空)");
		return "toSuccessTips";
	}
	
	public String delNoticeAttach() throws Exception {
		boolean isOk = noticeService.delNoticeAttach(notice_attach_id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		if (isOk)
			response.getWriter().write("删除成功！");
		else 
			response.getWriter().write("删除失败！");
		return null;
	}
	
	public String updateNotice() throws Exception {
		if (notice_title!=null&&!notice_title.isEmpty()&&notice_content!=null&&!notice_content.isEmpty()&&notice_abstract!=null&&!notice_abstract.isEmpty()) {
			Notice notice = noticeService.getNoticeByNotice_id(Integer.valueOf(notice_id));
			if (notice!=null) {
				notice.setNotice_title(notice_title);
				notice.setNotice_content(notice_content);
				notice.setNotice_abstract(notice_abstract);
				Student student = studentService.getStudentByStu_id(stu_id);
				notice.setStudent(student);
				if (attach!=null) {
					for (int i=0; i<attach.size(); i++) {
						String realPath = ServletActionContext.getServletContext().getRealPath("noticeAttach");
						File file = new File(realPath + File.separator + attachFileName.get(i));
						if (!file.exists()) {
							file.getParentFile().mkdir();
							file.createNewFile();
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
						NoticeAttach noticeAttach = new NoticeAttach();
						noticeAttach.setNoitce_attach_name(attachFileName.get(i));
						noticeAttach.setNotice_attach_src("noticeAttach/"+attachFileName.get(i));
						notice.getAttachs().add(noticeAttach);
					}
				}
				noticeService.updateNotice(notice);
				ActionContext.getContext().put("successMsg", "更新通知成功");
			}
		} else 
			ActionContext.getContext().put("successMsg", "更新通知失败(请检查标题和内容是否为空)");
		return "toSuccessTips";
	}
	
	public String getNoticesBySearchCondition() throws Exception {
		List<Notice> notices = noticeService.getNoticesBySearchCondition(notice_title, stu_name, notice_date);
		if (notices!=null&&!notices.isEmpty()) {
			PageBean<Notice> page = new PageBean<Notice>();
			page.setCurrentCount(notices.size());
			page.setCurrentPage(1);
			page.setTotalCount(notices.size());
			page.setTotalPage(1);
			page.setList(notices);
			ActionContext.getContext().put("page", page);
			if (notice_title!=null&&!notice_title.isEmpty())
				ActionContext.getContext().put("notice_title", notice_title);
			if (stu_name!=null&&!stu_name.isEmpty())
				ActionContext.getContext().put("stu_name", stu_name);
			if (notice_date!=null&&!notice_date.isEmpty())
				ActionContext.getContext().put("notice_date", notice_date);
		} else {
			PageBean<Notice> page = new PageBean<Notice>();
			page.setCurrentCount(0);
			page.setCurrentPage(1);
			page.setTotalCount(0);
			page.setTotalPage(1);
			page.setList(notices);
			ActionContext.getContext().put("page", page);
			if (notice_title!=null&&!notice_title.isEmpty())
				ActionContext.getContext().put("notice_title", notice_title);
			if (stu_name!=null&&!stu_name.isEmpty())
				ActionContext.getContext().put("stu_name", stu_name);
			if (notice_date!=null&&!notice_date.isEmpty())
				ActionContext.getContext().put("notice_date", notice_date);
		}
		return "toNotice";
	}
	
	public String getTasks() throws Exception {
		if (currentPage!=null&&!currentPage.isEmpty()&&currentCount!=null&&!currentCount.isEmpty()) {
			List<Task> tasks = homeworkService.getTasks(Integer.valueOf(currentPage), Integer.valueOf(currentCount));
			if (tasks!=null&&!tasks.isEmpty()) {
				PageBean<Task> page = new PageBean<Task>();
				page.setCurrentCount(Integer.valueOf(currentCount));
				page.setCurrentPage(Integer.valueOf(currentPage));
				Integer totalCount = homeworkService.getTaskTotalCount();
				page.setTotalCount(totalCount);
				Integer totalPage = (int) Math.ceil(totalCount*1.0/Integer.valueOf(currentCount));
				page.setTotalPage(totalPage);
				page.setList(tasks);
				ActionContext.getContext().put("page", page);
			} else {
				PageBean<Task> page = new PageBean<Task>();
				page.setCurrentCount(0);
				page.setCurrentPage(1);
				page.setTotalCount(0);
				page.setTotalPage(1);
				page.setList(tasks);
				ActionContext.getContext().put("page", page);
			}
		}
		return "toTask";
	}
	
	public String downloadAllHomework() throws Exception {
	    Task task = homeworkService.getTaskByTask_id(task_id);
	    if (task!=null) {
	    	HttpServletResponse response = ServletActionContext.getResponse();
	    	response.setCharacterEncoding("UTF-8");
	    	String fileName = task.getTask_name() + ".zip";
	    	String zipName = new String(fileName.getBytes(), "ISO-8859-1");
	    	response.setContentType("APPLICATION/OCTET-STREAM");  
	    	response.setHeader("Content-Disposition","attachment; filename="+zipName);
	    	ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
	    	try {
	    		Set<Homework> homeworks = task.getHomeworks();
	    		for (Homework homework : homeworks) {
	    			String realPath = ServletActionContext.getServletContext().getRealPath(homework.getHomework_src());
	    			File file = new File(realPath);
	    			ZipUtils.doCompress(file, out);
	    			response.flushBuffer();
	    		}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}finally{
	    		out.close();
	    	}
	    }
		return null;
	}
	
	public String delTask() throws Exception {
		boolean isOk = homeworkService.delTask(task_id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		if (isOk)
			response.getWriter().write("删除成功！");
		else 
			response.getWriter().write("删除失败！");
		return null;
	}
	
	public String saveTask() throws Exception {
		if (task_name!=null&&!task_name.isEmpty()&&task_deadline!=null&&!task_deadline.isEmpty()) {
			Task task = new Task();
			task.setTask_name(task_name);
			task.setTask_deadline(task_deadline);
			homeworkService.saveTask(task);
			ActionContext.getContext().put("successMsg", "发布作业成功");
		} else 
			ActionContext.getContext().put("successMsg", "发布作业失败(请检查标题和截止时间是否为空)");
		return "toSuccessTips";
	}
	
	public String getTaskBySearchCondition() throws Exception {
		List<Task> tasks = homeworkService.getTasksBySearchCondition(task_name);
		if (tasks!=null&&!tasks.isEmpty()) {
			PageBean<Task> page = new PageBean<Task>();
			page.setCurrentCount(tasks.size());
			page.setCurrentPage(1);
			page.setTotalCount(tasks.size());
			page.setTotalPage(1);
			page.setList(tasks);
			ActionContext.getContext().put("page", page);
			if (task_name!=null&&!task_name.isEmpty()) {
				ActionContext.getContext().put("task_name", task_name);
			}
		} else {
			PageBean<Task> page = new PageBean<Task>();
			page.setCurrentCount(0);
			page.setCurrentPage(1);
			page.setTotalCount(0);
			page.setTotalPage(1);
			page.setList(tasks);
			ActionContext.getContext().put("page", page);
			if (task_name!=null&&!task_name.isEmpty()) {
				ActionContext.getContext().put("task_name", task_name);
			}
		}
		return "toTask";
	}
	
	public String getHomeworkOfTask() throws Exception {
		Task taskByTask_id = homeworkService.getTaskByTask_id(task_id);
		if (taskByTask_id!=null) {
			Set<Homework> homeworks = taskByTask_id.getHomeworks();
			ActionContext.getContext().put("homeworks", homeworks);
		}
		return "toHomework";
	}
	
	public String downloadHomework() throws Exception {
		Homework homework = homeworkService.getHomeworkByHomework_id(homework_id);
		if (homework!=null) {
			String homework_src = homework.getHomework_src();
			String realPath = ServletActionContext.getServletContext().getRealPath(homework_src);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			String regex = "\\.[a-zA-Z]+";
		    Pattern pt = Pattern.compile(regex);
		    Matcher mt = pt.matcher(homework.getHomework_src());
		    String postFix = null;
		    if (mt.find()) 
		      postFix = mt.group();
	    	String fileName = homework.getStudent().getStu_id() + homework.getStudent().getStu_name() + postFix;
	    	fileName = new String(fileName.getBytes(), "ISO-8859-1");
	    	response.setContentType(homework.getHomework_content_type());  
	    	response.setHeader("Content-Disposition","attachment; filename="+fileName);
			File file = new File(realPath);
			InputStream is = new FileInputStream(file);
			OutputStream os = response.getOutputStream();
	        byte[] buf = new byte[1024];
	        int len = 0;
	        while ((len = is.read(buf)) > 0) {
	            os.write(buf, 0, len);
	        }
	        is.close();
	        os.close();
		}
		return null;
	}
	
	public String delHomework() throws Exception {
		boolean isOk = homeworkService.removeHomework(homework_id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		if (isOk)
			response.getWriter().write("删除成功！");
		else 
			response.getWriter().write("删除失败！");
		return null;
	}
	
	public String getEmployInfos() throws Exception {
		if (currentPage!=null&&!currentPage.isEmpty()&&currentCount!=null&&!currentCount.isEmpty()) {
			List<EmployInfo> employInfos = employmentService.getEmployInfos(Integer.valueOf(currentPage), Integer.valueOf(currentCount));
			if (employInfos!=null&&!employInfos.isEmpty()) {
				PageBean<EmployInfo> page = new PageBean<EmployInfo>();
				page.setCurrentCount(Integer.valueOf(currentCount));
				page.setCurrentPage(Integer.valueOf(currentPage));
				Integer totalCount = noticeService.getNoticeTotalCount();
				page.setTotalCount(totalCount);
				Integer totalPage = (int) Math.ceil(totalCount*1.0/Integer.valueOf(currentCount));
				page.setTotalPage(totalPage);
				page.setList(employInfos);
				ActionContext.getContext().put("page", page);
			} else {
				PageBean<EmployInfo> page = new PageBean<EmployInfo>();
				page.setCurrentCount(0);
				page.setCurrentPage(1);
				page.setTotalCount(0);
				page.setTotalPage(1);
				page.setList(employInfos);
				ActionContext.getContext().put("page", page);
			}
		}
		return "toEmployInfo";
	}
	
	public String editEmployInfo() throws Exception {
		EmployInfo employInfo = employmentService.getEmployInfoByEmp_info_id(Integer.valueOf(emp_info_id));
		if (employInfo!=null) 
			ActionContext.getContext().put("employInfo", employInfo);
		return "toEditEmployInfo"; 
	}
	
	public String updateEmployInfo() throws Exception {
		if (emp_info_title!=null&&!emp_info_title.isEmpty()&&emp_info_content!=null&&!emp_info_content.isEmpty()&&emp_info_deadline!=null&&!emp_info_deadline.isEmpty()) {
			EmployInfo employInfo = employmentService.getEmployInfoByEmp_info_id(Integer.valueOf(emp_info_id));
			if (employInfo!=null) {
				employInfo.setEmp_info_title(emp_info_title);
				employInfo.setEmp_info_deadline(emp_info_deadline);
				employInfo.setEmp_info_content(emp_info_content);
				Student student = studentService.getStudentByStu_id(stu_id);
				employInfo.setStudent(student);
				employmentService.updateEmployInfo(employInfo);
				ActionContext.getContext().put("successMsg", "更新招聘启事成功");
			}
		} else 
			ActionContext.getContext().put("successMsg", "更新招聘启事失败(请检查标题和内容是否为空)");
		return "toSuccessTips";
	}
	
	public String delEmployInfo() throws Exception {
		boolean isOk = employmentService.delEmployInfo(emp_info_id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		if (isOk)
			response.getWriter().write("删除成功！");
		else 
			response.getWriter().write("删除失败！");
		return null;
	}
	
	public String saveEmployInfo() throws Exception {
		if (emp_info_title!=null&&!emp_info_title.isEmpty()&&emp_info_content!=null&&!emp_info_content.isEmpty()) {
			EmployInfo employInfo = new EmployInfo();
			employInfo.setEmp_info_title(emp_info_title);
			if (emp_info_deadline!=null&&!emp_info_deadline.isEmpty())
				employInfo.setEmp_info_deadline(emp_info_deadline);
			employInfo.setEmp_info_content(emp_info_content);
			Student student = studentService.getStudentByStu_id(stu_id);
			employInfo.setStudent(student);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String emp_info_date = sdf.format(date);
			employInfo.setEmp_info_date(emp_info_date);
			employInfo.setEmp_info_scan(0);
			employmentService.saveEmployInfo(employInfo);
			ActionContext.getContext().put("successMsg", "发布招聘启事成功");
		} else 
			ActionContext.getContext().put("successMsg", "发布招聘启事失败(请检查标题和内容是否为空)");
		return "toSuccessTips";
	}
	
	public String getEmployInfoBySearchCondition() throws Exception {
		List<EmployInfo> employInfos = employmentService.getEmployInfosBySearchCondition(emp_info_title, stu_name, emp_info_date);
		if (employInfos!=null&&!employInfos.isEmpty()) {
			PageBean<EmployInfo> page = new PageBean<EmployInfo>();
			page.setCurrentCount(employInfos.size());
			page.setCurrentPage(1);
			page.setTotalCount(employInfos.size());
			page.setTotalPage(1);
			page.setList(employInfos);
			ActionContext.getContext().put("page", page);
			if (emp_info_title!=null&&!emp_info_title.isEmpty())
				ActionContext.getContext().put("emp_info_title", emp_info_title);
			if (stu_name!=null&&!stu_name.isEmpty())
				ActionContext.getContext().put("stu_name", stu_name);
			if (emp_info_date!=null&&!emp_info_date.isEmpty())
				ActionContext.getContext().put("emp_info_date", emp_info_date);
		} else {
			PageBean<EmployInfo> page = new PageBean<EmployInfo>();
			page.setCurrentCount(0);
			page.setCurrentPage(1);
			page.setTotalCount(0);
			page.setTotalPage(1);
			page.setList(employInfos);
			ActionContext.getContext().put("page", page);
			if (emp_info_title!=null&&!emp_info_title.isEmpty())
				ActionContext.getContext().put("emp_info_title", emp_info_title);
			if (stu_name!=null&&!stu_name.isEmpty())
				ActionContext.getContext().put("stu_name", stu_name);
			if (emp_info_date!=null&&!emp_info_date.isEmpty())
				ActionContext.getContext().put("emp_info_date", emp_info_date);
		}
		return "toEmployInfo";
	}
	
	public String getCarousels() throws Exception {
		List<Carousel> carousels = carouselService.getCarousels();
		ActionContext.getContext().put("carousels", carousels);
		return "toCarousel";
	}
	
	public String upCarousel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		if (crs_prior!=null) {
			Integer prior = Integer.valueOf(crs_prior);
			if (prior>1) {
				Carousel crs = carouselService.getCarouselByPrior(prior);
				Carousel crsUpper = carouselService.getCarouselByPrior(prior-1);
				if (crs!=null&&crsUpper!=null) {
					crs.setCrs_prior(prior-1);
					crsUpper.setCrs_prior(prior);
				}
				carouselService.updateCarousel(crs);
				carouselService.updateCarousel(crsUpper);
				response.getWriter().write("success");
				return null;
			} 
		}
		response.getWriter().write("error");
		return null;
	}
	
	public String downCarousel() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		if (crs_prior!=null) {
			Integer prior = Integer.valueOf(crs_prior);
			Integer maxPrior = carouselService.getMaxPrior();
			if (prior<maxPrior) {
				Carousel crs = carouselService.getCarouselByPrior(prior);
				Carousel crsLower = carouselService.getCarouselByPrior(prior+1);
				if (crs!=null&&crsLower!=null) {
					crs.setCrs_prior(prior+1);
					crsLower.setCrs_prior(prior);
				}
				carouselService.updateCarousel(crs);
				carouselService.updateCarousel(crsLower);
				response.getWriter().write("success");
				return null;
			} 
		}
		response.getWriter().write("error");
		return null;
	}
	
	public String delCarousel() throws Exception {
		carouselService.delCarousel(crs_id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("success");
		return null;
	}
	
	public String saveCarousel() throws Exception {
		if (attach!=null) {
			for (int i=0; i<attach.size(); i++) {
				String filename = attachFileName.get(i);
				Pattern pattern = Pattern.compile("\\.[a-zA-Z]+");
				Matcher matcher = pattern.matcher(filename);
				String postfix = null;
				if (matcher.find()) 
					postfix = matcher.group();
				postfix = postfix.toLowerCase();
				if (postfix.contains("jpg")||postfix.contains("jpeg")||postfix.contains("png")||postfix.contains("bmp")) {
					String realPath = ServletActionContext.getServletContext().getRealPath("img/carousel");
					File file = new File(realPath + File.separator + filename);
					if (!file.exists()) {
						file.getParentFile().mkdir();
						file.createNewFile();
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
					Carousel carousel = new Carousel();
					carousel.setCrs_src("img/carousel/" + filename);
					if (crs_desc!=null)
						carousel.setCrs_desc(crs_desc);
					Integer maxPrior = carouselService.getMaxPrior();
					if (maxPrior!=null)
						carousel.setCrs_prior(maxPrior+1);
					else
						carousel.setCrs_prior(1);//是空的话说明是第一张
					carouselService.saveCarousel(carousel);
				} else
					throw new RuntimeException("上传的为非图片，只支持jpg,jpeg,bmp,png");
			}
		}
		return "toGetCarousels";
	}
	
	public String getCarousel() throws Exception {
		Carousel carousel = carouselService.getCarouelByCrs_id(crs_id);
		ActionContext.getContext().put("carousel", carousel);
		return "toEditCarousel";
	}
	
	public String updateCarousel() throws Exception {
		Carousel carousel = carouselService.getCarouelByCrs_id(crs_id);
		if (carousel!=null) {
			if (attach!=null) {
				for (int i=0; i<attach.size(); i++) {
					//检测文件是否为图片
					String filename = attachFileName.get(i);
					Pattern pattern = Pattern.compile("\\.[a-zA-Z]+");
					Matcher matcher = pattern.matcher(filename);
					String postfix = null;
					if (matcher.find()) 
						postfix = matcher.group();
					postfix = postfix.toLowerCase();
					if (postfix.contains("jpg")||postfix.contains("jpeg")||postfix.contains("png")||postfix.contains("bmp")) {
						//如果有新上传的文件直接删除原来的文件
						String realPath = ServletActionContext.getServletContext().getRealPath(carousel.getCrs_src());
						File oldFile = new File(realPath);
						if (oldFile.exists())
							oldFile.delete();
						realPath = ServletActionContext.getServletContext().getRealPath("img/carousel");
						File file = new File(realPath + File.separator + attachFileName.get(i));
						if (!file.exists()) {
							file.getParentFile().mkdir();
							file.createNewFile();
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
						carousel.setCrs_src("img/carousel/" + attachFileName.get(i));
					} else 
						throw new RuntimeException("上传的为非图片，只支持jpg,jpeg,bmp,png");
				}
			}
			if (crs_desc!=null&&!crs_desc.isEmpty())
				carousel.setCrs_desc(crs_desc);
			carouselService.updateCarousel(carousel);
		}
		return "toGetCarousels";
	}
	
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public void setCurrentCount(String currentCount) {
		this.currentCount = currentCount;
	}

	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
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

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public void setNotice_abstract(String notice_abstract) {
		this.notice_abstract = notice_abstract;
	}

	public void setNotice_attach_id(String notice_attach_id) {
		this.notice_attach_id = notice_attach_id;
	}

	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}

	public void setHomeworkService(HomeworkService homeworkService) {
		this.homeworkService = homeworkService;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public void setTask_deadline(String task_deadline) {
		this.task_deadline = task_deadline;
	}

	public void setHomework_id(String homework_id) {
		this.homework_id = homework_id;
	}

	public EmploymentService getEmploymentService() {
		return employmentService;
	}

	public void setEmploymentService(EmploymentService employmentService) {
		this.employmentService = employmentService;
	}

	public void setEmp_info_id(String emp_info_id) {
		this.emp_info_id = emp_info_id;
	}

	public void setEmp_info_title(String emp_info_title) {
		this.emp_info_title = emp_info_title;
	}

	public void setEmp_info_deadline(String emp_info_deadline) {
		this.emp_info_deadline = emp_info_deadline;
	}

	public void setEmp_info_content(String emp_info_content) {
		this.emp_info_content = emp_info_content;
	}

	public void setEmp_info_date(String emp_info_date) {
		this.emp_info_date = emp_info_date;
	}

	public void setCarouselService(CarouselService carouselService) {
		this.carouselService = carouselService;
	}

	public String getCrs_prior() {
		return crs_prior;
	}

	public void setCrs_prior(String crs_prior) {
		this.crs_prior = crs_prior;
	}

	public void setCrs_id(String crs_id) {
		this.crs_id = crs_id;
	}

	public void setCrs_desc(String crs_desc) {
		this.crs_desc = crs_desc;
	}

}
