package com.bjpowernode.p2p.admin.interceptor;

import com.bjpowernode.p2p.admin.constants.Constants;
import com.bjpowernode.p2p.admin.model.PermissionInfo;
import com.bjpowernode.p2p.admin.model.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Spring MVC 拦截器，授权拦截器
 * 
 * @author yanglijun
 */
public class PermissionInterceptor implements HandlerInterceptor {
	
	/**log4j2日志记录器*/
	private static final Logger logger = LogManager.getLogger(PermissionInterceptor.class);

	/**
	 * 在执行handler之前执行
	 * 用于用户权限校验
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//得到请求的url
		String url = request.getRequestURI() + "?" + request.getQueryString();

		//获取session
		HttpSession session = request.getSession();
		UserInfo userInfo = (UserInfo) session.getAttribute(Constants.SESSION_USER);
		
		//获取用户菜单权限
		List<PermissionInfo> menuPermissions = userInfo.getMenuPermissionInfo();
		for (PermissionInfo sysPermission: menuPermissions) {
			//权限的url
			String permission_url = sysPermission.getUrl();
			if(StringUtils.isNotEmpty(permission_url) && url.indexOf(permission_url)>=0){
				//如果是权限的url 地址则放行
				return true;
			}
		}
		
		//获取用户按钮权限
		List<PermissionInfo> buttonPermissions = userInfo.getButtonPermissionInfo();
		for (PermissionInfo sysPermission : buttonPermissions) {
			//权限的url
			String permission_url = sysPermission.getUrl();
			if(StringUtils.isNotEmpty(permission_url) && url.indexOf(permission_url)>=0){
				//如果是权限的url 地址则放行
				return true;
			}
		}
		
		//若无权限，执行拦截，跳转到无权访问提示页面
		String path = request.getContextPath();
		response.sendRedirect(path + "/admin/refuse");
		
		//如果返回false表示请求被拦截，用户无权访问，不执行后续的controller，如果返回true表示放行
		logger.info("无权访问url：" + url);
		return false;
	}
	
	/**
	 * 在执行controller返回modelAndView之前来执行
	 * 
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}
	
	/**
	 * 执行controller之后执行此方法
	 * 
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}
}