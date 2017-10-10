package com.jumper.angel.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.jumper.angel.home.login.entity.CrmAdmin;

/**
 * 用于判断用户是否登录
 * @Description TODO
 * @author qinxiaowei
 * @date 2017-01-17
 * @Copyright: Copyright (c) 2016 Shenzhen Angelsound Technology Co., Ltd. Inc. 
 *             All rights reserved.
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	/**
	 * 请求到控制器之前执行该方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//判断session是否为空
		CrmAdmin admin = (CrmAdmin)request.getSession().getAttribute("user");
		if(admin == null) {
			//跳转到登录页面
			response.getWriter().println("<script>window.parent.location.href='"+request.getContextPath()+"/login.jsp'</script>");
			return false;
		}
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
