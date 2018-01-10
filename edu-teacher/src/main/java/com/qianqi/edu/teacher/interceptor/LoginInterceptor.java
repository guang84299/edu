package com.qianqi.edu.teacher.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qianqi.edu.common.CookieUtils;
import com.qianqi.edu.pojo.common.EduResult;
import com.qianqi.edu.service.SsoService;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private SsoService ssoService;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		String token = CookieUtils.getCookieValue(request, "token");
		if(StringUtils.isEmpty(token))
		{
			response.sendRedirect("/toLogin");
			return false;
		}
		System.out.println("token="+token);
		EduResult res = ssoService.getUserByToken(token);
		if(res.getState() != 200)
		{
			response.sendRedirect("/toLogin");
			return false;
		}
		request.setAttribute("user", res.getData());
		return true;
	}

}
