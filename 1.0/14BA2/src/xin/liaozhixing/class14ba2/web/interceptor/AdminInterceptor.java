package xin.liaozhixing.class14ba2.web.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import xin.liaozhixing.class14ba2.domain.Student;

public class AdminInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Student student = (Student) session.get("student");
		if (student==null) {
			ActionSupport action = (ActionSupport)invocation.getAction();
			action.addActionError("您没有登录,请登录后再尝试访问!");
			return action.LOGIN;
		} else if (student.getIsadmin()==null||student.getIsadmin()!=1){
			ActionSupport action = (ActionSupport)invocation.getAction();
			action.addActionError("您没有管理员权限！");
			return action.ERROR;
		} else {
			return invocation.invoke();
		}
	}

}
