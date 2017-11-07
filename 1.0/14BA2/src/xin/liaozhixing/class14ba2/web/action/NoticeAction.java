package xin.liaozhixing.class14ba2.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
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
import xin.liaozhixing.class14ba2.domain.NoticeAttach;
import xin.liaozhixing.class14ba2.domain.Student;
import xin.liaozhixing.class14ba2.service.NoticeService;
import xin.liaozhixing.class14ba2.utils.PageBean;

public class NoticeAction extends ActionSupport {

	private NoticeService noticeService;
	private String currentPage;
	private String currentCount = "5";
	private String notice_id;
	private String notice_attach_id;
	
	public String getNotices() throws Exception {
		List<Notice> notices = null;
		PageBean<Notice> pageBean = new PageBean<Notice>();
		if (currentPage==null||currentPage.isEmpty()) {
			notices = noticeService.getNotices(Integer.valueOf(1), Integer.valueOf(currentCount));
			List<Notice> hotNotices = noticeService.getHotNotice();
			pageBean.setCurrentCount(Integer.valueOf(currentCount));
			pageBean.setCurrentPage(Integer.valueOf(1));
			Integer totalCount = noticeService.getNoticeTotalCount();
			Integer totalPage = (int) Math.ceil(totalCount * 1.0 / Integer.valueOf(currentCount));
			pageBean.setTotalCount(totalCount);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(notices);
			if (pageBean!=null&&pageBean.getList()!=null&&pageBean.getList().size()!=0)
				ActionContext.getContext().put("page", pageBean);
			if (hotNotices!=null&&hotNotices.size()!=0)
				ActionContext.getContext().put("hotNotices", hotNotices);
			return "toItem";
		}
		else  {
			notices = noticeService.getNotices(Integer.valueOf(currentPage), Integer.valueOf(currentCount));
			if (notices!=null&&notices.size()!=0) {
				pageBean.setCurrentCount(Integer.valueOf(currentCount));
				pageBean.setCurrentPage(Integer.valueOf(currentPage));
				Integer totalCount = noticeService.getNoticeTotalCount();
				Integer totalPage = (int) Math.ceil(totalCount * 1.0 / Integer.valueOf(currentCount));
				pageBean.setTotalCount(totalCount);
				pageBean.setTotalPage(totalPage);
				pageBean.setList(notices);
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
						if (obj instanceof EmployInfo && name.equals("student"))
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
	
	
	public String getNotice() throws Exception {
		if (notice_id!=null&&!notice_id.isEmpty()) {
			Notice notice = noticeService.getNoticeByNotice_id(Integer.valueOf(notice_id));
			List<Notice> hotNotice = noticeService.getHotNotice();
			if (notice!=null) {
				notice.setNotice_scan(notice.getNotice_scan()+1);
				noticeService.updateNotice(notice);				
				ActionContext.getContext().put("notice", notice);
			}
			if (hotNotice!=null&&hotNotice.size()!=0)
				ActionContext.getContext().put("hot", hotNotice);
		}
		return "toNotice";
	}
	
	public String getRecentNotice() throws Exception {
		List<Notice> recentNotices = noticeService.getRecentNotices();
		if (recentNotices!=null&&!recentNotices.isEmpty()) {
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
					if (obj instanceof EmployInfo && name.equals("student"))
						return true;
					if (obj instanceof Student && name.equals("stu_password"))
						return true;
					if (obj instanceof Student && name.equals("stu_email"))
						return true;
					/*if (obj instanceof Notice && name.equals("student"))
						return true;*/
					return false;
				}});
			String json = JSONArray.fromObject(recentNotices, jsonConfig).toString();
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
		return null;
	}
	
	public String downloadNoticeAttach() throws Exception {
		NoticeAttach noticeAttach = noticeService.getNoticeAttachByNotice_attach_id(notice_attach_id);
		if (noticeAttach!=null) {
			String notice_attach_src = noticeAttach.getNotice_attach_src();
			String realPath = ServletActionContext.getServletContext().getRealPath(notice_attach_src);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
	    	String fileName = noticeAttach.getNoitce_attach_name();
	    	fileName = new String(fileName.getBytes(), "ISO-8859-1");
	    	response.setContentType(noticeAttach.getNotice_attach_content_type());  
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

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
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

	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}


	public void setNotice_attach_id(String notice_attach_id) {
		this.notice_attach_id = notice_attach_id;
	}
	
}
