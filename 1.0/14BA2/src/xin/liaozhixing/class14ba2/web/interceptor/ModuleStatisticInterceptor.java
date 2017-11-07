package xin.liaozhixing.class14ba2.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import xin.liaozhixing.class14ba2.domain.Module;
import xin.liaozhixing.class14ba2.service.ModuleService;
import xin.liaozhixing.class14ba2.service.impl.ModuleServiceImpl;

public class ModuleStatisticInterceptor extends MethodFilterInterceptor {
	private final Integer ALL_ACCESS_ID = 1;
	private final Integer NOTICE_MODULE_ID = 2;
	private final Integer EMPLOYMENT_MODULE_ID = 3;
	private final Integer HOMEWORK_MODULE_ID = 4;
	private final Integer SCORE_MODULE_ID = 5;
	private final Integer TALK_MODULE_ID = 6;
	private ModuleService moduleService;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		String actionName = invocation.getAction().toString();
		this.addAccessNum(ALL_ACCESS_ID);//增加总访问量
		
		if (actionName.contains("Notice")||actionName.contains("notice")) {
			//增加通知模块的访问量
			this.addAccessNum(NOTICE_MODULE_ID);
		} else if(actionName.contains("Employment")||actionName.contains("employment")) {
			//增加招聘模块访问量
			this.addAccessNum(EMPLOYMENT_MODULE_ID);
		} else if(actionName.contains("Score")||actionName.contains("score")) {
			//增加成绩模块访问量
			this.addAccessNum(SCORE_MODULE_ID);
		} else if(actionName.contains("Homework")||actionName.contains("homework")) {
			//增加作业模块访问量
			this.addAccessNum(HOMEWORK_MODULE_ID);
		} else if(actionName.contains("Talk")||actionName.contains("talk")) {
			//增加看看模块访问量
			this.addAccessNum(TALK_MODULE_ID);
		}
		return invocation.invoke();
	}
	
	private void addAccessNum(Integer module_id) {
		Module module = moduleService.getModuleByModule_id(module_id);
		long module_access_num = module.getModule_access_num();
		long newAccessNum = module_access_num + 1;
		module.setModule_access_num(newAccessNum);
		moduleService.updateModule(module);
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

}
