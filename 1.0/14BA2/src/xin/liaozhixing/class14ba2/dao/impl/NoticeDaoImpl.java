package xin.liaozhixing.class14ba2.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import xin.liaozhixing.class14ba2.dao.NoticeDao;
import xin.liaozhixing.class14ba2.domain.Notice;
import xin.liaozhixing.class14ba2.domain.NoticeAttach;

public class NoticeDaoImpl extends HibernateDaoSupport implements NoticeDao {

	@Override
	public List<Notice> getNotices(Integer currentPage, Integer currentCount) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Notice>>() {

			@Override
			public List<Notice> doInHibernate(Session session) throws HibernateException {
				String hql = "from Notice notice order by notice.notice_date desc";
				Query query = session.createQuery(hql);
				query.setFirstResult(currentPage);
				query.setMaxResults(currentCount);
				List<Notice> notices = query.list();
				return notices;
			}});
	}

	@Override
	public Integer getNoticeTotalCount() {
		return this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				String hql = "select count(*) from Notice";
				Query query = session.createQuery(hql);
				Long totalCount = (Long) query.uniqueResult();
				return totalCount.intValue();
			}});
	}

	@Override
	public List<Notice> getHotNotice() {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Notice>>() {

			@Override
			public List<Notice> doInHibernate(Session session) throws HibernateException {
				String hql = "from Notice notice order by notice.notice_scan desc";
				Query query = session.createQuery(hql);
				query.setFirstResult(0);
				query.setMaxResults(4);
				List<Notice> hotNotices = query.list();
				return hotNotices;
			}});
		
	}

	@Override
	public Notice getNoticeByNotice_id(Integer notice_id) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Notice>() {

			@Override
			public Notice doInHibernate(Session session) throws HibernateException {
				Notice notice = session.get(Notice.class, notice_id);
				return notice;
			}
			
		});
	}

	@Override
	public List<Notice> getRecentNotices() {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Notice>>() {

			@Override
			public List<Notice> doInHibernate(Session session) throws HibernateException {
				String hql = "from Notice notice order by notice.notice_date desc";
				Query query = session.createQuery(hql);
				query.setFirstResult(0);
				query.setMaxResults(4);
				List<Notice> recentNotices = query.list();
				return recentNotices;
			}
			
		});
	}

	@Override
	public void updateNotice(Notice notice) {
		this.getHibernateTemplate().execute(new HibernateCallback<Notice>() {

			@Override
			public Notice doInHibernate(Session session) throws HibernateException {
				session.update(notice);
				return null;
			}
			
		});
	}

	@Override
	public void delNotice(String notice_id) {
		this.getHibernateTemplate().execute(new HibernateCallback<Notice>() {

			@Override
			public Notice doInHibernate(Session session) throws HibernateException {
				Notice notice = session.get(Notice.class, Integer.valueOf(notice_id));
				session.delete(notice);
				return null;
			}});
		
	}

	@Override
	public void saveNotice(Notice notice) {
		this.getHibernateTemplate().execute(new HibernateCallback<Notice>() {

			@Override
			public Notice doInHibernate(Session session) throws HibernateException {
				session.save(notice);
				return null;
			}
			
		});
	}

	@Override
	public void delNoticeAttach(String notice_attach_id) {
		this.getHibernateTemplate().execute(new HibernateCallback<NoticeAttach>() {

			@Override
			public NoticeAttach doInHibernate(Session session) throws HibernateException {
				NoticeAttach noticeAttach = session.get(NoticeAttach.class, Integer.valueOf(notice_attach_id));
				session.delete(noticeAttach);
				return null;
			}});
	}

	@Override
	public List<Notice> getNoticesBySearchCondition(String notice_title, String notice_date) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Notice>>() {

			@Override
			public List<Notice> doInHibernate(Session session) throws HibernateException {
				String sql = "select * from notice where 1=1";
				if (notice_title!=null&&!notice_title.isEmpty())
					sql += " and notice_title like '%" + notice_title + "%'";
				if (notice_date!=null&&!notice_date.isEmpty())
					sql += " and notice_date like '%" + notice_date + "%'";
				SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Notice.class);
				List<Notice> list = sqlQuery.list();
				return list;
			}});
	}

	@Override
	public List<Notice> getAllNotices() {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Notice>>() {

			@Override
			public List<Notice> doInHibernate(Session session) throws HibernateException {
				String hql = "from Notice";
				Query query = session.createQuery(hql);
				List<Notice> list = query.list();
				return list;
			}
			
		});
	}

	@Override
	public NoticeAttach getNoticeAttachByNotice_attach_id(Integer notice_attach_id) {
		return this.getHibernateTemplate().execute(new HibernateCallback<NoticeAttach>() {

			@Override
			public NoticeAttach doInHibernate(Session session) throws HibernateException {
				return session.get(NoticeAttach.class, notice_attach_id);
			}});
	}
	
}
