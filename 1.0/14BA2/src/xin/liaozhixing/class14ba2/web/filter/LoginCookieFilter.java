package xin.liaozhixing.class14ba2.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class LoginCookieFilter implements Filter {
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		Cookie[] cookies = request.getCookies();
		if (cookies!=null && cookies.length!=0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("student")) {
					String value = cookie.getValue();
					String[] split = value.split("%=%=");
					request.setAttribute("stu_id", split[0]);
					request.setAttribute("stu_password", split[1]);
				}
			}
		}
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		
	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
