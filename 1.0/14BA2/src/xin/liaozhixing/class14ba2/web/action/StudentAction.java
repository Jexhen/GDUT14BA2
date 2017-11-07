package xin.liaozhixing.class14ba2.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONObject;
import xin.liaozhixing.class14ba2.domain.Carousel;
import xin.liaozhixing.class14ba2.domain.EmployInfo;
import xin.liaozhixing.class14ba2.domain.Notice;
import xin.liaozhixing.class14ba2.domain.Student;
import xin.liaozhixing.class14ba2.service.CarouselService;
import xin.liaozhixing.class14ba2.service.StudentService;
import xin.liaozhixing.class14ba2.utils.FontImageUtils;
import xin.liaozhixing.class14ba2.utils.MD5Utils;
import xin.liaozhixing.class14ba2.utils.StringUtils;

public class StudentAction extends ActionSupport implements ModelDriven<Student>{
	private Student stu = new Student();
	private String md5;
	private File avata;
	private String avataFileName;
	private String avataContentType;
	
	private StudentService studentService;
	private CarouselService carouselService;

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	public String toLogin() throws Exception {
		return "toHome";
	}

	public String login() throws Exception {
		Map<String, Object> parameters = ActionContext.getContext().getParameters();
		for (Entry<String, Object> entry : parameters.entrySet()) {
			if (entry.getKey().equals("rememberPwd")) {
				Cookie cookie = new Cookie("student", stu.toString());
				cookie.setMaxAge(5*24*60*60);
				ServletActionContext.getResponse().addCookie(cookie);
			}
		}
		Student student = studentService.getStudent(stu);
		if (student!=null) {
			Integer isactive = student.getIsactive();
			if (isactive==null||isactive!=1) {
				ActionContext.getContext().put("stu_id", student.getStu_id());
				return "toActive";
			}
			ActionContext.getContext().getSession().put("student", student);
		}
		return "toWelcome";
	}

	public String logout() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session!=null&&session.size()!=0) {
			for (Entry<String, Object> entry : session.entrySet()) {
				if (entry.getKey().equals("student")) {
					session.remove("student");
				}
			}
		}
		return LOGIN;
	}
	
	public String welcome() throws Exception {
		//读取轮播图
		List<Carousel> carousels = carouselService.getCarousels();
		//读取四则最新通知
		List<Notice> notices = studentService.getNotices(1, 4);
		//读取四则最新招聘启事
		List<EmployInfo> employInfos = studentService.getemployInfos(1, 4);
		//读取四则最新侃侃
		/*==============待实现==================*/
		if (notices!=null&&notices.size()!=0)
			ActionContext.getContext().put("notices", notices);
		if (employInfos!=null&&employInfos.size()!=0)
			ActionContext.getContext().put("employInfos", employInfos);
		if (carousels!=null&&carousels.size()!=0)
			ActionContext.getContext().put("carousels", carousels);
		return "toHome";
	}
	
	public String getVerifyCode() throws Exception {
		Student existStu = studentService.getVerifyCode(stu);
		if (existStu!=null) {
			String stu_email = existStu.getStu_email();
			if (stu_email!=null&&!stu_email.isEmpty()) {
				studentService.updateStudent(existStu);
				/************构造邮箱掩码开始*************/
				int indexOf = stu_email.indexOf("@");
				String substring = stu_email.substring(0, indexOf);//@前面的邮箱地址
				int star = substring.length() - 4;//除去前两个字符和后两个字符其他为星号的字符数
				StringBuffer sb = new StringBuffer();
				sb.append(substring.substring(0, 2));
				for (int i=0;i<star;i++)
					sb.append("*");
				sb.append(stu_email.substring(indexOf-2, stu_email.length()));
				/************构造邮箱掩码结束*************/
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("UTF-8");
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("email", sb.toString());
				String json = jsonObject.toString();
				response.getWriter().write(json);
			}
		}
		return null;
	}
	
	public String validateVerifyCode() throws Exception {
		boolean isOk = studentService.validateVerifyCode(stu);
		if (isOk) {
			Student exsitStudent = studentService.getExsitStudent(stu);
			if (exsitStudent!=null) {
				long time = exsitStudent.getVerify_time().getTime();
				String stu_id = exsitStudent.getStu_id();
				String verify_code = exsitStudent.getVerify_code();
				String key = stu_id + "$" + String.valueOf(time) + "$" + verify_code;
				String md5 = MD5Utils.md5(key);
				ActionContext.getContext().put("md5", md5);
				ActionContext.getContext().put("stu_id", stu_id);
			}
		} else {
			throw new RuntimeException("账号无法获取");
		}
		return "toModifyPassword";
	}
	
	public String modifyPassword() throws Exception {
		if (stu!=null) {
			Student exsitStudent = studentService.getExsitStudent(stu);
			if (exsitStudent!=null) {
				long time = exsitStudent.getVerify_time().getTime();
				String stu_id = exsitStudent.getStu_id();
				String verify_code = exsitStudent.getVerify_code();
				String key = stu_id + "$" + String.valueOf(time) + "$" + verify_code;
				String validateKey = MD5Utils.md5(key);
				if (validateKey.equals(md5)) {
					String stu_password = stu.getStu_password();
					exsitStudent.setStu_password(stu_password);
					studentService.updateStudent(exsitStudent);
					ActionContext.getContext().put("successMsg", "修改成功");
					return "toSuccess";
				}
			}
		}
		return null;
	}
	
	public String getVerifyCodeOfLogin() throws Exception {
		Student student = (Student) ActionContext.getContext().getSession().get("student");
		if (student!=null) {
			Student newStu = studentService.getVerifyCode(student);
			String stu_email = newStu.getStu_email();
			if (stu_email!=null&&!stu_email.isEmpty()) {
				studentService.updateStudent(newStu);
				/************构造邮箱掩码开始*************/
				int indexOf = stu_email.indexOf("@");
				String substring = stu_email.substring(0, indexOf);//@前面的邮箱地址
				int star = substring.length() - 4;//除去前两个字符和后两个字符其他为星号的字符数
				StringBuffer sb = new StringBuffer();
				sb.append(substring.substring(0, 2));
				for (int i=0;i<star;i++)
					sb.append("*");
				sb.append(stu_email.substring(indexOf-2, stu_email.length()));
				/************构造邮箱掩码结束*************/
				ActionContext.getContext().put("email", sb.toString());
			}
		}
		return "toResetPasswordOfLogin";
	} 
	
	public String getVerifyCodeOfActive() throws Exception {
		if (stu!=null) {
			Student exsitStudent = studentService.getExsitStudent(stu);
			String stu_email = stu.getStu_email();
			if (stu_email!=null&&!stu_email.isEmpty()) {
				//先检测是否有其他用户绑定相同邮箱
				boolean isAvaliable = studentService.checkEmailAvaliable(stu_email);
				if (isAvaliable) {
					exsitStudent.setStu_email(stu_email);
					studentService.updateStudent(exsitStudent);//将绑定的邮箱更新到数据库
					Student verifyStu = studentService.getVerifyCode(exsitStudent);
					studentService.updateStudent(verifyStu);//将验证码更新到数据库
					if (verifyStu!=null) {
						JSONObject jo = new JSONObject();
						jo.put("isOK", true);
						ServletActionContext.getResponse().getWriter().write(jo.toString());
					}
				} else {
					JSONObject jo = new JSONObject();
					jo.put("isOK", false);
					ServletActionContext.getResponse().getWriter().write(jo.toString());
				}
			}
		}
		return null;
	}
	
	public String activeAccount() throws Exception {
		boolean isOk = studentService.validateVerifyCode(stu);
		if (isOk) {
			Student existStu = studentService.getExsitStudent(stu);
			if (existStu!=null) {
				existStu.setIsactive(1);
				studentService.updateStudent(existStu);
				ActionContext.getContext().put("successMsg", "账户激活成功");
				return "toSuccess";
			}
		} 
		return null;
	}
	
	public String addStudent() throws Exception {
		String realPath = ServletActionContext.getServletContext().getRealPath("img/avata");
		File avata = new File(realPath+File.separator+stu.getStu_id()+".png");
		if(!avata.exists()){
		    //先得到文件的上级目录，并创建上级目录，在创建文件
			avata.getParentFile().mkdir();
		    try {
		        //创建文件
		    	avata.createNewFile();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		String font=StringUtils.getCharString(stu.getStu_name());
        FontImageUtils.createImage(font,avata);
        stu.setStu_avater_src("img/avata/" + stu.getStu_id() + ".png");
		stu.setStu_password("12345");
		studentService.saveStudent(stu);
		ActionContext.getContext().put("successMsg", "成功添加学生");
		return "toSuccessTips";
	}
	
	public String editStudent() throws Exception {
		if (stu!=null) {
			String stu_id = stu.getStu_id();
			if (stu_id!=null&&!stu_id.isEmpty()) {
				Student exsitStudent = studentService.getExsitStudent(stu);
				if (exsitStudent!=null) {
					ActionContext.getContext().put("targetStu", exsitStudent);
				}
			}
		}
		return "toEditStudent";
	}
	
	public String saveStudent() throws Exception {
		Student studentByStu_id = studentService.getStudentByStu_id(stu.getStu_id());
		if (stu.equals(studentByStu_id))
			throw new RuntimeException("您就不要坑我吧！您并没有修改任何资料");
		if (avataFileName!=null&&!avataFileName.isEmpty()) {
			/***********上传头像**************/
			String realPath = ServletActionContext.getServletContext().getRealPath("img/avata");
			String regex = "\\.[a-zA-Z]+";
			Pattern pt = Pattern.compile(regex);
			Matcher mt = pt.matcher(avataFileName);
			String postFix = null;
			if (mt.find()) 
				postFix = mt.group();
			if (postFix.contains("jpg")||postFix.contains("jpeg")||postFix.contains("png")||postFix.contains("bmp")) {
				File file = new File(realPath+ File.separator + avataFileName);
				if (!file.exists()) {
					//先得到文件的上级目录，并创建上级目录，在创建文件
					file.getParentFile().mkdir();
					try {
						//创建文件
						file.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				InputStream is = new FileInputStream(avata);
				OutputStream os = new FileOutputStream(file);
				byte[] buf = new byte[1024];
				int length;
				while ((length = is.read(buf))!=-1) {
					os.write(buf, 0, length);
				}
				os.close();
				is.close();
				studentByStu_id.setStu_avater_src("img/avata/"+avataFileName);
				/***********上传头像结束**************/
			} else {
				throw new RuntimeException("只支持jpg,jpeg,bmp和png格式图片");
			}
		}
		String stu_sex = stu.getStu_sex();
		String stu_dormitory = stu.getStu_dormitory();
		String stu_signature = stu.getStu_signature();
		String stu_birthday = stu.getStu_birthday();
		if (stu_sex!=null&&!stu_sex.isEmpty())
			studentByStu_id.setStu_sex(stu_sex);
		if (stu_dormitory!=null&&!stu_dormitory.isEmpty())
			studentByStu_id.setStu_dormitory(stu_dormitory);
		if (stu_birthday!=null&&!stu_birthday.isEmpty())
			studentByStu_id.setStu_birthday(stu_birthday);
		if (stu_signature!=null&&!stu_signature.isEmpty())
			studentByStu_id.setStu_signature(stu_signature);
		studentService.updateStudent(studentByStu_id);
		ActionContext.getContext().put("successMsg", "修改资料成功");
		return "toSuccessTips";
	}
	
	@Override
	public Student getModel() {
		return stu;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public File getAvata() {
		return avata;
	}

	public void setAvata(File avata) {
		this.avata = avata;
	}

	public String getAvataFileName() {
		return avataFileName;
	}

	public void setAvataFileName(String avataFileName) {
		this.avataFileName = avataFileName;
	}

	public String getAvataContentType() {
		return avataContentType;
	}

	public void setAvataContentType(String avataContentType) {
		this.avataContentType = avataContentType;
	}

	public void setCarouselService(CarouselService carouselService) {
		this.carouselService = carouselService;
	}
	
}
