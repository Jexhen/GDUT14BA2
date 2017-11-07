package xin.liaozhixing.class14ba2.service.impl;

import java.util.List;

import xin.liaozhixing.class14ba2.dao.ModuleDao;
import xin.liaozhixing.class14ba2.domain.Module;
import xin.liaozhixing.class14ba2.service.ModuleService;

public class ModuleServiceImpl implements ModuleService {
	private ModuleDao moduleDao;
	
	@Override
	public Module getModuleByModule_id(Integer module_id) {
		if (module_id!=null)
			return moduleDao.getModuleByModule_id(module_id);
		return null;
	}

	@Override
	public void updateModule(Module module) {
		if (module!=null) {
			moduleDao.updateModule(module);
		}
	}

	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	@Override
	public List<Module> getModules() {
		return moduleDao.getModules();
	}

}
