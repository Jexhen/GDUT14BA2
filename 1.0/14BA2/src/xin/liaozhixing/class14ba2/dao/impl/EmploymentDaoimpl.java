package xin.liaozhixing.class14ba2.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import xin.liaozhixing.class14ba2.dao.EmploymentDao;
import xin.liaozhixing.class14ba2.domain.EmployInfo;
import xin.liaozhixing.class14ba2.domain.Notice;

public class EmploymentDaoimpl extends HibernateDaoSupport implements EmploymentDao {

	@Override
	public List<EmployInfo> getEmployInfos(Integer currentPage, Integer currentCount) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<EmployInfo>>() {

			@Override
			public List<EmployInfo> doInHibernate(Session session) throws HibernateException {
				String hql = "from EmployInfo employInfo order by emp_info_date desc";
				Query query = session.createQuery(hql);
				query.setFirstResult(currentPage);
				query.setMaxResults(currentCount);
				List<EmployInfo> employInfos = query.list();
				return employInfos;
			}
			
		});
	}

	@Override
	public Integer getEmploymentTotalCount() {
		return this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				String hql = "select count(*) from EmployInfo";
				Query query = session.createQuery(hql);
				Long totalCount = (Long) query.uniqueResult();
				return totalCount.intValue();
			}});
	}

	@Override
	public List<EmployInfo> getHotEmployInfos() {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<EmployInfo>>() {

			@Override
			public List<EmployInfo> doInHibernate(Session session) throws HibernateException {
				String hql = "from EmployInfo order by emp_info_scan desc";
				Query query = session.createQuery(hql);
				query.setFirstResult(0);
				query.setMaxResults(4);
				List<EmployInfo> hotEmployInfos = query.list();
				return hotEmployInfos;
			}});
	}

	@Override
	public EmployInfo getEmployInfoByEmp_info_id(Integer emp_info_id) {
		return this.getHibernateTemplate().execute(new HibernateCallback<EmployInfo>() {

			@Override
			public EmployInfo doInHibernate(Session session) throws HibernateException {
				EmployInfo employInfo = session.get(EmployInfo.class, emp_info_id);
				return employInfo;
			}});
	}

	@Override
	public List<EmployInfo> getRecentEmployInfo() {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<EmployInfo>>() {

			@Override
			public List<EmployInfo> doInHibernate(Session session) throws HibernateException {
				String hql = "from EmployInfo info order by info.emp_info_date desc";
				Query query = session.createQuery(hql);
				query.setFirstResult(0);
				query.setMaxResults(4);
				List<EmployInfo> recentInfo = query.list();
				return recentInfo;
			}
			
		});
	}

	@Override
	public void updateEmployInfo(EmployInfo employInfo) {
		this.getHibernateTemplate().execute(new HibernateCallback<EmployInfo>() {

			@Override
			public EmployInfo doInHibernate(Session session) throws HibernateException {
				session.update(employInfo);
				return null;
			}
			
		});
	}

	@Override
	public void delEmployInfo(Integer emp_info_id) {
		this.getHibernateTemplate().execute(new HibernateCallback<EmployInfo>() {

			@Override
			public EmployInfo doInHibernate(Session session) throws HibernateException {
				EmployInfo employInfo = session.get(EmployInfo.class, emp_info_id);
				session.delete(employInfo);
				return null;
			}});
	}

	@Override
	public void saveEmployInfo(EmployInfo employInfo) {
		this.getHibernateTemplate().execute(new HibernateCallback<EmployInfo>() {

			@Override
			public EmployInfo doInHibernate(Session session) throws HibernateException {
				session.save(employInfo);
				return null;
			}
			
		});
	}

	@Override
	public List<EmployInfo> getEmployInfosBySearchCondition(String emp_info_title, String emp_info_date) {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<EmployInfo>>() {

			@Override
			public List<EmployInfo> doInHibernate(Session session) throws HibernateException {
				String sql = "select * from EmployInfo where 1=1";
				if (emp_info_title!=null&&!emp_info_title.isEmpty())
					sql += " and emp_info_title like '%" + emp_info_title + "%'";
				if (emp_info_date!=null&&!emp_info_date.isEmpty())
					sql += " and emp_info_date like '%" + emp_info_date + "%'";
				SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(EmployInfo.class);
				List<EmployInfo> list = sqlQuery.list();
				return list;
			}});
	}

	@Override
	public List<EmployInfo> getAllEmployInfos() {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<EmployInfo>>() {

			@Override
			public List<EmployInfo> doInHibernate(Session session) throws HibernateException {
				String hql = "from EmployInfo";
				Query query = session.createQuery(hql);
				List<EmployInfo> list = query.list();
				return list;
			}});
	}

}
