package com.szf.cms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(null!=session &&null!=session.getAttribute("admin")) {
				return true;//不拦截
		}
		//如果没有登录则把请求转发到登录页面
		request.setAttribute("msg", "请登录后再试...");
		request.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(request, response);
		
		return false;
		
	}

}
