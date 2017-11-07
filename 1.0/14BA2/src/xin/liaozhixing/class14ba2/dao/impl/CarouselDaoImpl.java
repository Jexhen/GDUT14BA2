package xin.liaozhixing.class14ba2.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import xin.liaozhixing.class14ba2.dao.CarouselDao;
import xin.liaozhixing.class14ba2.domain.Carousel;

public class CarouselDaoImpl extends HibernateDaoSupport implements CarouselDao {

	@Override
	public List<Carousel> getCarousels() {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Carousel>>() {

			@Override
			public List<Carousel> doInHibernate(Session session) throws HibernateException {
				String hql = "from Carousel order by crs_prior asc";
				Query query = session.createQuery(hql);
				List<Carousel> carousels = query.list();
				return carousels;
			}});
	}

	@Override
	public Carousel getCarouselByPrior(Integer prior) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Carousel>() {

			@Override
			public Carousel doInHibernate(Session session) throws HibernateException {
				String hql = "from Carousel where crs_prior=?";
				Query query = session.createQuery(hql);
				query.setParameter(0, prior);
				Carousel crs = (Carousel) query.uniqueResult();
				return crs;
			}
			
		});
	}

	@Override
	public void updateCarousel(Carousel crs) {
		this.getHibernateTemplate().execute(new HibernateCallback<Carousel>() {

			@Override
			public Carousel doInHibernate(Session session) throws HibernateException {
				session.update(crs);
				return null;
			}
			
		});
	}

	@Override
	public Integer getMaxPrior() {
		return this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				String hql = "select MAX(crs_prior) from Carousel";
				Query query = session.createQuery(hql);
				Integer ur = (Integer) query.uniqueResult();
				return ur;
			}
			
		});
	}

	@Override
	public void delCarousel(Carousel crs) {
		this.getHibernateTemplate().execute(new HibernateCallback<Carousel>() {

			@Override
			public Carousel doInHibernate(Session session) throws HibernateException {
				String sql = "update carousel set crs_prior=crs_prior-1 where crs_prior>"+crs.getCrs_prior();
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				sqlQuery.executeUpdate();
				session.delete(crs);
				return null;
			}
			
		});
	}

	@Override
	public Carousel getCarouselByCrs_id(Integer crs_id) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Carousel>() {

			@Override
			public Carousel doInHibernate(Session session) throws HibernateException {
				return session.get(Carousel.class, crs_id);
			}
			
		});
	}

	@Override
	public void saveCarousel(Carousel carousel) {
		this.getHibernateTemplate().execute(new HibernateCallback<Carousel>() {

			@Override
			public Carousel doInHibernate(Session session) throws HibernateException {
				session.save(carousel);
				return null;
			}
			
		});
	}

	

}
