package xin.liaozhixing.class14ba2.domain;

import java.util.HashSet;
import java.util.Set;

public class Notice {
	private Integer notice_id;
	private String notice_title;
	private String notice_abstract;
	private String notice_content;
	private String notice_date;
	private Integer notice_scan;
	private Student student;
	private Set<NoticeAttach> attachs = new HashSet<NoticeAttach>();
	public Integer getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(Integer notice_id) {
		this.notice_id = notice_id;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public String getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}
	public Integer getNotice_scan() {
		return notice_scan;
	}
	public void setNotice_scan(Integer notice_scan) {
		this.notice_scan = notice_scan;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getNotice_abstract() {
		return notice_abstract;
	}
	public void setNotice_abstract(String notice_abstract) {
		this.notice_abstract = notice_abstract;
	}
	public Set<NoticeAttach> getAttachs() {
		return attachs;
	}
	public void setAttachs(Set<NoticeAttach> attachs) {
		this.attachs = attachs;
	}
	
}
