package xin.liaozhixing.class14ba2.domain;

public class NoticeAttach {
	private Integer notice_attach_id;
	private String noitce_attach_name;
	private String notice_attach_src;
	private String notice_attach_content_type; 
	private Notice notice;
	public Integer getNotice_attach_id() {
		return notice_attach_id;
	}
	public void setNotice_attach_id(Integer notice_attach_id) {
		this.notice_attach_id = notice_attach_id;
	}
	public String getNoitce_attach_name() {
		return noitce_attach_name;
	}
	public void setNoitce_attach_name(String noitce_attach_name) {
		this.noitce_attach_name = noitce_attach_name;
	}
	public String getNotice_attach_src() {
		return notice_attach_src;
	}
	public void setNotice_attach_src(String notice_attach_src) {
		this.notice_attach_src = notice_attach_src;
	}
	public Notice getNotice() {
		return notice;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
	}
	public String getNotice_attach_content_type() {
		return notice_attach_content_type;
	}
	public void setNotice_attach_content_type(String notice_attach_content_type) {
		this.notice_attach_content_type = notice_attach_content_type;
	}
	
}
