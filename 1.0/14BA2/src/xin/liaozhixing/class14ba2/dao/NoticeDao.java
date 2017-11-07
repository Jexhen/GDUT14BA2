package xin.liaozhixing.class14ba2.dao;

import java.util.List;


import xin.liaozhixing.class14ba2.domain.Notice;
import xin.liaozhixing.class14ba2.domain.NoticeAttach;

public interface NoticeDao{

	List<Notice> getNotices(Integer currentPage, Integer currentCount);

	Integer getNoticeTotalCount();

	List<Notice> getHotNotice();

	Notice getNoticeByNotice_id(Integer notice_id);

	List<Notice> getRecentNotices();

	void updateNotice(Notice notice);

	void delNotice(String notice_id);

	void saveNotice(Notice notice);

	void delNoticeAttach(String notice_attach_id);

	List<Notice> getNoticesBySearchCondition(String notice_title, String notice_date);

	List<Notice> getAllNotices();

	NoticeAttach getNoticeAttachByNotice_attach_id(Integer notice_attach_id);

}
