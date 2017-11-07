package xin.liaozhixing.class14ba2.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import net.sf.json.JSONString;

public class Student {
	private String stu_id;
	private String stu_password;
	private String stu_name;
	private String stu_avater_src;
	private String stu_sex;
	private String stu_birthday;
	private String stu_dormitory;
	private String stu_signature;
	private String stu_email;
	private Integer isadmin;
	private Integer isactive;
	private String verify_code;
	private Timestamp verify_time;
	private Set<Homework> homeworks = new HashSet<Homework>();
	private Set<Notice> notices = new HashSet<Notice>();
	private Set<EmployInfo> employInfos = new HashSet<EmployInfo>();
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public String getStu_password() {
		return stu_password;
	}
	public void setStu_password(String stu_password) {
		this.stu_password = stu_password;
	}
	@Override
	public String toString() {
		return "" + stu_id + "%=%=" + stu_password;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getStu_avater_src() {
		return stu_avater_src;
	}
	public void setStu_avater_src(String stu_avater_src) {
		this.stu_avater_src = stu_avater_src;
	}
	public String getStu_sex() {
		return stu_sex;
	}
	public void setStu_sex(String stu_sex) {
		this.stu_sex = stu_sex;
	}
	public String getStu_birthday() {
		return stu_birthday;
	}
	public void setStu_birthday(String stu_birthday) {
		this.stu_birthday = stu_birthday;
	}
	public String getStu_dormitory() {
		return stu_dormitory;
	}
	public void setStu_dormitory(String stu_dormitory) {
		this.stu_dormitory = stu_dormitory;
	}
	public String getStu_signature() {
		return stu_signature;
	}
	public void setStu_signature(String stu_signature) {
		this.stu_signature = stu_signature;
	}
	public String getStu_email() {
		return stu_email;
	}
	public void setStu_email(String stu_email) {
		this.stu_email = stu_email;
	}
	public Integer getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(Integer isadmin) {
		this.isadmin = isadmin;
	}
	public Integer getIsactive() {
		return isactive;
	}
	public void setIsactive(Integer isactive) {
		this.isactive = isactive;
	}
	public Set<Homework> getHomeworks() {
		return homeworks;
	}
	public void setHomeworks(Set<Homework> homeworks) {
		this.homeworks = homeworks;
	}
	public Set<Notice> getNotices() {
		return notices;
	}
	public void setNotices(Set<Notice> notices) {
		this.notices = notices;
	}
	public Set<EmployInfo> getEmployInfos() {
		return employInfos;
	}
	public void setEmployInfos(Set<EmployInfo> employInfos) {
		this.employInfos = employInfos;
	}
	public String getVerify_code() {
		return verify_code;
	}
	public void setVerify_code(String verify_code) {
		this.verify_code = verify_code;
	}
	public Timestamp getVerify_time() {
		return verify_time;
	}
	public void setVerify_time(Timestamp verify_time) {
		this.verify_time = verify_time;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stu_avater_src == null) ? 0 : stu_avater_src.hashCode());
		result = prime * result + ((stu_birthday == null) ? 0 : stu_birthday.hashCode());
		result = prime * result + ((stu_dormitory == null) ? 0 : stu_dormitory.hashCode());
		result = prime * result + ((stu_email == null) ? 0 : stu_email.hashCode());
		result = prime * result + ((stu_id == null) ? 0 : stu_id.hashCode());
		result = prime * result + ((stu_name == null) ? 0 : stu_name.hashCode());
		result = prime * result + ((stu_sex == null) ? 0 : stu_sex.hashCode());
		result = prime * result + ((stu_signature == null) ? 0 : stu_signature.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (stu_avater_src == null) {
			if (other.stu_avater_src != null)
				return false;
		} else if (!stu_avater_src.equals(other.stu_avater_src))
			return false;
		if (stu_birthday == null) {
			if (other.stu_birthday != null)
				return false;
		} else if (!stu_birthday.equals(other.stu_birthday))
			return false;
		if (stu_dormitory == null) {
			if (other.stu_dormitory != null)
				return false;
		} else if (!stu_dormitory.equals(other.stu_dormitory))
			return false;
		if (stu_email == null) {
			if (other.stu_email != null)
				return false;
		} else if (!stu_email.equals(other.stu_email))
			return false;
		if (stu_id == null) {
			if (other.stu_id != null)
				return false;
		} else if (!stu_id.equals(other.stu_id))
			return false;
		if (stu_name == null) {
			if (other.stu_name != null)
				return false;
		} else if (!stu_name.equals(other.stu_name))
			return false;
		if (stu_sex == null) {
			if (other.stu_sex != null)
				return false;
		} else if (!stu_sex.equals(other.stu_sex))
			return false;
		if (stu_signature == null) {
			if (other.stu_signature != null)
				return false;
		} else if (!stu_signature.equals(other.stu_signature))
			return false;
		return true;
	}
	
}
