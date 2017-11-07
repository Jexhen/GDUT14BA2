package xin.liaozhixing.class14ba2.web.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;
import xin.liaozhixing.class14ba2.domain.EmployInfo;
import xin.liaozhixing.class14ba2.domain.Homework;
import xin.liaozhixing.class14ba2.domain.Notice;
import xin.liaozhixing.class14ba2.domain.Student;
import xin.liaozhixing.class14ba2.service.EmploymentService;
import xin.liaozhixing.class14ba2.utils.PageBean;

public class EmploymentAction extends ActionSupport {
	private EmploymentService employmentService;
	private String currentPage;
	private String currentCount = "5";
	private String emp_info_id;
	
	public String getEmployment() throws Exception {
		ActionContext actionContext = ActionContext.getContext();
		List<EmployInfo> employInfos = null;
		PageBean<EmployInfo> pageBean = new PageBean<EmployInfo>();
		if (currentPage==null||currentPage.isEmpty()) {
			employInfos = employmentService.getEmployInfos(Integer.valueOf(1), Integer.valueOf(currentCount));
			List<EmployInfo> hotEmployInfos = employmentService.getHotEmployInfos();
			pageBean.setCurrentCount(Integer.valueOf(currentCount));
			pageBean.setCurrentPage(Integer.valueOf(1));
			Integer totalCount = employmentService.getEmploymentTotalCount();
			Integer totalPage = (int) Math.ceil(totalCount * 1.0 / Integer.valueOf(currentCount));
			pageBean.setTotalCount(totalCount);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(employInfos);
			if (pageBean!=null&&pageBean.getList()!=null&&pageBean.getList().size()!=0)
				actionContext.put("page", pageBean);
			if (hotEmployInfos!=null&&hotEmployInfos.size()!=0)
				actionContext.put("hotEmployInfos", hotEmployInfos);
			return "toItem";
		}
		else  {
			employInfos = employmentService.getEmployInfos(Integer.valueOf(currentPage), Integer.valueOf(currentCount));
			if (employInfos!=null&&employInfos.size()!=0) {
				pageBean.setCurrentCount(Integer.valueOf(currentCount));
				pageBean.setCurrentPage(Integer.valueOf(currentPage));
				Integer totalCount = employmentService.getEmploymentTotalCount();
				Integer totalPage = (int) Math.ceil(totalCount * 1.0 / Integer.valueOf(currentCount));
				pageBean.setTotalCount(totalCount);
				pageBean.setTotalPage(totalPage);
				pageBean.setList(employInfos);
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT); 
				jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer"});  
				jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
					@Override
					public boolean apply(Object obj, String name, Object value) {
						if (obj instanceof Student && name.equals("notices"))
							return true;
						if (obj instanceof Student && name.equals("homeworks"))
							return true;
						if (obj instanceof Student && name.equals("employInfos"))
							return true;
						if (obj instanceof Homework && name.equals("student"))
							return true;
						if (obj instanceof Notice && name.equals("student"))
							return true;
						if (obj instanceof Student && name.equals("stu_password"))
							return true;
						if (obj instanceof Student && name.equals("stu_email"))
							return true;
						/*if (obj instanceof Notice && name.equals("student"))
							return true;*/
						return false;
					}});
				//jsonConfig.setExcludes(new String[] {"notices", "homeworks", "employInfos"});
				String json = JSONObject.fromObject(pageBean, jsonConfig).toString();
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			}
			return null;
		}
		
	}

	public String getEmployInfo() throws Exception {
		if (emp_info_id!=null&&!emp_info_id.isEmpty()) {
			EmployInfo employInfo = employmentService.getEmployInfoByEmp_info_id(Integer.valueOf(emp_info_id));
			List<EmployInfo> hotEmployInfos = employmentService.getHotEmployInfos();
			if (employInfo!=null) {
				employInfo.setEmp_info_scan(employInfo.getEmp_info_scan()+1);
				employmentService.updateEmployInfo(employInfo);
				ActionContext.getContext().put("employInfo", employInfo);
			}
			if (hotEmployInfos!=null&&hotEmployInfos.size()!=0)
				ActionContext.getContext().put("hotEmp", hotEmployInfos);
		}
		return "toEmployInfo";
	}
	
	public String getRecentEmployInfo() throws Exception {
		List<EmployInfo> recentEmployInfo = employmentService.getRecentEmployInfo();
		if (recentEmployInfo!=null&&!recentEmployInfo.isEmpty()) {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT); 
			jsonConfig.setExcludes(new String[]{"handler","hibernateLazyInitializer"});  
			jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
				@Override
				public boolean apply(Object obj, String name, Object value) {
					if (obj instanceof Student && name.equals("notices"))
						return true;
					if (obj instanceof Student && name.equals("homeworks"))
						return true;
					if (obj instanceof Student && name.equals("employInfos"))
						return true;
					if (obj instanceof Homework && name.equals("student"))
						return true;
					if (obj instanceof Notice && name.equals("student"))
						return true;
					if (obj instanceof Student && name.equals("stu_password"))
						return true;
					if (obj instanceof Student && name.equals("stu_email"))
						return true;
					/*if (obj instanceof Notice && name.equals("student"))
						return true;*/
					return false;
				}});
			String json = JSONArray.fromObject(recentEmployInfo, jsonConfig).toString();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
		return null;
	}
	
	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(String currentCount) {
		this.currentCount = currentCount;
	}

	public void setEmploymentService(EmploymentService employmentService) {
		this.employmentService = employmentService;
	}


	public void setEmp_info_id(String emp_info_id) {
		this.emp_info_id = emp_info_id;
	}

	
	
}
