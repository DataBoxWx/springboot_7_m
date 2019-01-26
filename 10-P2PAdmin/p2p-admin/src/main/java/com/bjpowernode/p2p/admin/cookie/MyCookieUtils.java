package com.bjpowernode.p2p.admin.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie操作工具类
 *
 * @author yanglijun
 *
 */
public class MyCookieUtils {

	/**
	 * 设置cookie
	 * 
	 * @param response
	 * @param name cookie名字
	 * @param value cookie值
	 * @param maxAge cookie生命周期 以秒为单位
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}
	
	/**
	 * 根据cookie名称获取cookie的值
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookieValueByName (HttpServletRequest request, String name) {
		String cookieValue = "";
		//获取当前站点的所有Cookie
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			//对cookies中的数据进行遍历，找到用户名、密码的数据
			for (int i=0; i<cookies.length; i++) {
				if (name.equals(cookies[i].getName())) {
					cookieValue = cookies[i].getValue();
					break;
				}
			}
		}
		return cookieValue;
	}
	
	/**
	 * 根据cookie名称获取cookie的值
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static void removeCookieValueByName (HttpServletRequest request, HttpServletResponse response, String name) {
		//获取当前站点的所有Cookie
		Cookie[] cookies = request.getCookies();
		//对cookies中的数据进行遍历，找到用户名、密码的数据
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName())) {
				cookie.setValue(null);
				cookie.setPath("/"); 
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				break;
			}
		}
	}
}