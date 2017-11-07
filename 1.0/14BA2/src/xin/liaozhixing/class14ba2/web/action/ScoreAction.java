package xin.liaozhixing.class14ba2.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import xin.liaozhixing.class14ba2.domain.Score;
import xin.liaozhixing.class14ba2.utils.JWGL;

public class ScoreAction extends ActionSupport {
	private String stu_id;
	private String password;
	private String verifyCode;
	private String schoolYear;
	private String semester;

	public String getLogin() throws Exception {
		String uuid = JWGL.getVerifyCode();
		if (uuid!=null&&!uuid.isEmpty())
			ActionContext.getContext().getSession().put("uuid", uuid);
		return "toLogin";
	}
	
	public String refreshVerifyCode() throws Exception {
		//先清除原来的验证码图片
		String oldUuid = (String)ActionContext.getContext().getSession().get("uuid");
		String realPath = ServletActionContext.getServletContext().getRealPath("/img/verifyCode");
		String filename = "verifycode_"+oldUuid+".jpeg";
		File file = new File(realPath+File.separator+filename);
		if(!file.exists()){
	        file.delete();
		}
		//再重新获得验证码图片
		String uuid = JWGL.getVerifyCode();
		if (uuid!=null&&!uuid.isEmpty()) {
			ActionContext.getContext().getSession().put("uuid", uuid);//将新的uuid放入session中
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(uuid);
		}
		return null;
	}
	
	public String login() throws Exception {
		if (password==null||password.isEmpty())
			throw new RuntimeException("密码不能为空");
		if (verifyCode==null||verifyCode.isEmpty())
			throw new RuntimeException("验证码不能为空");
		if (password!=null&&!password.isEmpty()&&verifyCode!=null&&stu_id!=null&&!stu_id.isEmpty()) {
			JWGL jwgl = new JWGL(stu_id, password, verifyCode);
			int isSuccess = jwgl.login();
			if (isSuccess==1)
				ActionContext.getContext().getSession().put("jwgl", jwgl);
			else 
				throw new RuntimeException("登录失败，请检查密码或者验证码!!!注意:密码为教务系统密码!!!");
		}
		return "toScore";
	}
	
	public String getScoresBySemester() throws Exception {
		if (schoolYear!=null&&!schoolYear.isEmpty()&&semester!=null&&!semester.isEmpty()) {
			JWGL jwgl = (JWGL)ActionContext.getContext().getSession().get("jwgl");
			if (jwgl!=null) {
				String yearSemesterCode = schoolYear+"0"+semester;
				List<Score> scores = jwgl.getScores(yearSemesterCode);
				if (scores!=null&&!scores.isEmpty()) {
					String json = JSONArray.fromObject(scores).toString();
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(json);
				}
			}
		}
		return null;
	}
	
	public String getScoresBySchoolYear() throws Exception {
		if (schoolYear!=null&&!schoolYear.isEmpty()) {
			JWGL jwgl = (JWGL)ActionContext.getContext().getSession().get("jwgl");
			if (jwgl!=null) {
				List<Score> scores = jwgl.getScores(Integer.valueOf(schoolYear));
				if (scores!=null&&!scores.isEmpty()) {
					String json = JSONArray.fromObject(scores).toString();
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(json);
				}
			}
		}
		return null;
	}
	
	public String getAllScores() throws Exception {
		JWGL jwgl = (JWGL)ActionContext.getContext().getSession().get("jwgl");
		if (jwgl!=null) {
			List<Score> scores = jwgl.getAllScores();
			if (scores!=null&&!scores.isEmpty()) {
				String json = JSONArray.fromObject(scores).toString();
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			}
		}
		return null;
	}
	
	public String logout() throws Exception {
		JWGL jwgl = (JWGL)ActionContext.getContext().getSession().get("jwgl");
		if (jwgl!=null) {
			jwgl.logOut();
			ActionContext.getContext().getSession().remove("jwgl");
			String oldUuid = (String)ActionContext.getContext().getSession().get("uuid");
			String realPath = ServletActionContext.getServletContext().getRealPath("/img/verifyCode");
			String filename = "verifycode_"+oldUuid+".jpeg";
			File file = new File(realPath+File.separator+filename);
			if(!file.exists()){
		        file.delete();
			}
			ActionContext.getContext().getSession().remove("uuid");
		}
		return null;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
