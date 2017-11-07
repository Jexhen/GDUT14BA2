package xin.liaozhixing.class14ba2.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import xin.liaozhixing.class14ba2.dao.NoticeDao;
import xin.liaozhixing.class14ba2.domain.Notice;
import xin.liaozhixing.class14ba2.domain.NoticeAttach;
import xin.liaozhixing.class14ba2.service.NoticeService;

public class NoticeServiceImpl implements NoticeService {
	private NoticeDao noticeDao;

	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	@Override
	public List<Notice> getNotices(Integer currentPage, Integer currentCount) {
		if (currentPage!=null&&currentCount!=null) {
			Integer index = (currentPage-1) * currentCount;
			return noticeDao.getNotices(index, currentCount);
		}
		return null;
	}

	@Override
	public Integer getNoticeTotalCount() {
		return noticeDao.getNoticeTotalCount();
	}

	@Override
	public List<Notice> getHotNotice() {
		return noticeDao.getHotNotice();
	}

	@Override
	public Notice getNoticeByNotice_id(Integer notice_id) {
		if (notice_id!=null)
			return noticeDao.getNoticeByNotice_id(notice_id);
		return null;
	}

	@Override
	public List<Notice> getRecentNotices() {
		return noticeDao.getRecentNotices();
	}

	@Override
	public void updateNotice(Notice notice) {
		if (notice!=null)
			noticeDao.updateNotice(notice);
	}

	@Override
	public boolean delNotice(String notice_id) {
		//先删除对应的附件
		if (notice_id!=null&&!notice_id.isEmpty()) {
			Notice noticeByNotice_id = this.getNoticeByNotice_id(Integer.valueOf(notice_id));
			Set<NoticeAttach> attachs = noticeByNotice_id.getAttachs();
			for (NoticeAttach na : attachs) {
				String realPath = ServletActionContext.getServletContext().getRealPath(na.getNotice_attach_src());
				File file = new File(realPath);
				if (file.exists())
					file.delete();
			}
			noticeDao.delNotice(notice_id);
			return true;
		}
		return false;
	}

	@Override
	public void saveNotice(Notice notice) {
		if (notice!=null)
			noticeDao.saveNotice(notice);
		
	}

	@Override
	public boolean delNoticeAttach(String notice_attach_id) {
		if (notice_attach_id!=null&&!notice_attach_id.isEmpty()) {
			//先把磁盘保存的附件删除
			NoticeAttach noticeAttachByNotice_attach_id = noticeDao.getNoticeAttachByNotice_attach_id(Integer.valueOf(notice_attach_id));
			if (noticeAttachByNotice_attach_id!=null) {
				String realPath = ServletActionContext.getServletContext().getRealPath(noticeAttachByNotice_attach_id.getNotice_attach_src());
				File file = new File(realPath);
				if (file.exists())
					file.delete();
			}
			noticeDao.delNoticeAttach(notice_attach_id);
			return true;
		}
		return false;
	}

	@Override
	public List<Notice> getNoticesBySearchCondition(String notice_title, String stu_name, String notice_date) {
		List<Notice> noticesByCondition = noticeDao.getNoticesBySearchCondition(notice_title, notice_date);
		//stu_name为空，只需要检索通知题目和通知发布日，调用getNoticesBySearchCondition方法即可
		if (stu_name==null||stu_name.isEmpty()) {
			return noticesByCondition;
		} else if ((notice_title==null||notice_title.isEmpty())&&(notice_date==null||notice_date.isEmpty())){
		//stu_name不为空且其他两者皆为空，要检索全部该学生发布的通知，所以获取全部通知，然后从通知中筛选出是发布者包含该学生的通知
			List<Notice> allNotices = noticeDao.getAllNotices();
			if (allNotices!=null&&!allNotices.isEmpty()) {
				List<Notice> notices = new ArrayList<Notice>();
				for (Notice notice : allNotices) {
					if (notice.getStudent().getStu_name().contains(stu_name)) {
						notices.add(notice);
					}
				}
				return notices;
			}
		} else {
		//stu_name不为空且其他两者有一者不空,其他两者不为空的结果已经用getNoticesBySearchCondition方法获得了，再从中求出与该学生的交集即可
			if (noticesByCondition!=null&&!noticesByCondition.isEmpty()) {
				List<Notice> notices = new ArrayList<Notice>();
				for (Notice notice : noticesByCondition) {
					if (notice.getStudent().getStu_name().contains(stu_name)) {
						notices.add(notice);
					}
				}
				return notices;
			}
		}
		return null;
	}

	@Override
	public NoticeAttach getNoticeAttachByNotice_attach_id(String notice_attach_id) {
		if (notice_attach_id!=null) {
			return noticeDao.getNoticeAttachByNotice_attach_id(Integer.valueOf(notice_attach_id));
		}
		return null;
	}
	
}
