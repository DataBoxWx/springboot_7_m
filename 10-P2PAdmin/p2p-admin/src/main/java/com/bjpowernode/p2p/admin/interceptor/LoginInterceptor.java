package com.bjpowernode.p2p.admin.interceptor;

import com.bjpowernode.p2p.admin.constants.Constants;
import com.bjpowernode.p2p.admin.model.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Spring MVC登录拦截器，验证登录状态
 * 
 * @author yanglijun
 */
public class LoginInterceptor implements HandlerInterceptor {

	private static final Logger logger = LogManager.getLogger(LoginInterceptor.class);
	
	/**
	 * 全局登录拦截器
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Map<String,String[]> pramMap = request.getParameterMap();
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String[]> entry: pramMap.entrySet()) {
			sb.append(entry.getKey()).append("=");
		    sb.append(Arrays.toString(entry.getValue()));
		    sb.append("--");
		}
		logger.info("请求：" + request.getServletPath() + "-->" + sb);
		
		UserInfo userInfo = (UserInfo)request.getSession().getAttribute(Constants.SESSION_USER);
		if (null == userInfo) {//用户未登录，重定向到登录页面
			String path = request.getContextPath();
			response.sendRedirect(path + "/");
		    return false;  
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}
}