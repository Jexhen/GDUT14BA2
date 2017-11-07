package xin.liaozhixing.class14ba2.service;

import java.util.List;

import xin.liaozhixing.class14ba2.domain.Notice;
import xin.liaozhixing.class14ba2.domain.NoticeAttach;

public interface NoticeService {

	List<Notice> getNotices(Integer currentPage, Integer currnetCount);

	Integer getNoticeTotalCount();

	List<Notice> getHotNotice();

	Notice getNoticeByNotice_id(Integer notice_id);

	List<Notice> getRecentNotices();
	
	void updateNotice(Notice notice);

	boolean delNotice(String notice_id);

	void saveNotice(Notice notice);

	boolean delNoticeAttach(String notice_attach_id);

	List<Notice> getNoticesBySearchCondition(String notice_title, String stu_name, String notice_date);

	NoticeAttach getNoticeAttachByNotice_attach_id(String notice_attach_id);
}
