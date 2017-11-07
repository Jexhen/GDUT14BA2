package xin.liaozhixing.class14ba2.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import xin.liaozhixing.class14ba2.dao.ModuleDao;
import xin.liaozhixing.class14ba2.domain.Module;

public class ModuleDaoImpl extends HibernateDaoSupport implements ModuleDao {

	@Override
	public Module getModuleByModule_id(Integer module_id) {
		return this.getHibernateTemplate().execute(new HibernateCallback<Module>() {

			@Override
			public Module doInHibernate(Session session) throws HibernateException {
				Module module = session.get(Module.class, module_id);
				return module;
			}});
	}

	@Override
	public void updateModule(Module module) {
		this.getHibernateTemplate().execute(new HibernateCallback<Module>() {

			@Override
			public Module doInHibernate(Session session) throws HibernateException {
				session.update(module);
				return null;
			}});

	}

	@Override
	public List<Module> getModules() {
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Module>>() {

			@Override
			public List<Module> doInHibernate(Session session) throws HibernateException {
				String hql = "from Module";
				Query query = session.createQuery(hql);
				List<Module> modules = query.list();
				return modules;
			}} );

			
	}

}
