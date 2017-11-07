package xin.liaozhixing.class14ba2.service;

import java.util.List;

import xin.liaozhixing.class14ba2.domain.Module;

public interface ModuleService {
	Module getModuleByModule_id(Integer module_id);
	void updateModule(Module module);
	List<Module> getModules();
}
