package com.bjpowernode.p2p.admin.controller;

import com.bjpowernode.p2p.admin.cookie.MyCookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页相关处理Controller
 * 
 * @author yanglijun
 *
 */
@Controller
public class IndexController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@RequestMapping("/")
	public String index(HttpServletRequest request, Model model) {

		String userName = MyCookieUtils.getCookieValueByName(request, "userName");
		String password = MyCookieUtils.getCookieValueByName(request, "password");
		
		model.addAttribute("userName", userName);
		model.addAttribute("password", password);
		model.addAttribute("isRemember", 1);
		
		if (StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(password)) {
			model.addAttribute("isAutoLogin", 1);
		}
		return "index";
	}
}