package com.bjpowernode.p2p.admin.controller;

import com.bjpowernode.p2p.admin.constants.Constants;
import com.bjpowernode.p2p.admin.cookie.MyCookieUtils;
import com.bjpowernode.p2p.admin.model.StaffInfo;
import com.bjpowernode.p2p.admin.model.UserInfo;
import com.bjpowernode.p2p.admin.rto.ResponseObject;
import com.bjpowernode.p2p.admin.service.StaffInfoService;
import com.bjpowernode.p2p.admin.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户相关处理的Controller
 * 
 * @author yanglijun
 *
 */
@Controller
public class UserController {
	
	/**log4j2 日志记录器*/
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	/**注入UserInfoService*/
	@Autowired
	private UserInfoService userInfoService;
	
	/**注入StaffInfoService*/
	@Autowired
	private StaffInfoService staffInfoService;

	/**
	 * 用户登录
	 * 
	 * @param session
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping("/admin/login")
	public @ResponseBody ResponseObject login (HttpSession session, HttpServletResponse response,
			@RequestParam(name="userName", required=false) String userName,
			@RequestParam(name="password", required=false) String password,
			@RequestParam(name="rememberMe", required=false) String rememberMe) {
		
		logger.info("用户登录-->userName={}", userName);
		
		ResponseObject responseObject = new ResponseObject();
		
		//服务端对参数再次进行验证，验证用户名不能为空
		if (StringUtils.isEmpty(userName)) {
			responseObject.setErrorCode(Constants.ONE);
			responseObject.setErrorMessage("请输入登录账户");
			return responseObject;
		} else if (StringUtils.isEmpty(password)) {//服务端对参数再次进行验证，验证密码不能为空
			responseObject.setErrorCode(Constants.ONE);
			responseObject.setErrorMessage("请输入登录密码");
			return responseObject;
		} else {
			
			//调用底层登录方法，根据用户和密码查询用户是否存在
			UserInfo userInfo = userInfoService.login(userName, password);
			
			if (null == userInfo) {//未查询到用户，则登录失败
				responseObject.setErrorCode(Constants.ONE);
				responseObject.setErrorMessage("账户名或密码错误");
			} else {
				
				//登录成功，将用户账户和密码设置到Cookie中
				if (StringUtils.equals(rememberMe, "rememberMe")) {
					int loginMaxAge = 7 * 24 * 60 * 60;//定义账户密码的生命周期，这里是7天，单位为秒  
					MyCookieUtils.addCookie(response , "userName" , userName , loginMaxAge);//将姓名加入到cookie中
					MyCookieUtils.addCookie(response , "password" , password , loginMaxAge);//将密码加入到cookie中  
				}
	            
				//将登录查询出来的用户信息放入session中
				session.setAttribute(Constants.SESSION_USER, userInfo);
				
				responseObject.setErrorCode(Constants.ZERO);
				responseObject.setErrorMessage("登录成功");
			}
	        return responseObject;
		}
	}
	
	/**
	 * 进入后台系统主页面-用户信息页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/profile")
	public String main (HttpSession session, Model model) {
		
		UserInfo userInfo = (UserInfo)session.getAttribute(Constants.SESSION_USER);
		
		//根据员工ID获取用户所属员工及组织机构信息
		StaffInfo staffInfo = staffInfoService.getStaffInfoById(userInfo.getStaffId());
		
		//员工信息放入model中供页面展示
		model.addAttribute("staffInfo", staffInfo);
		
		//跳转到profile.jsp页面
		return "profile";
	}
	
	/**
	 * 用户退出登录
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/admin/logout")
	public String logout (HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		//将session中的用户信息删除，退出登录
		session.removeAttribute(Constants.SESSION_USER);
		
		//废弃 销毁 session
		session.invalidate();
		
		//删除用户登录的cookie信息
		MyCookieUtils.removeCookieValueByName(request, response, "userName");
		MyCookieUtils.removeCookieValueByName(request, response, "password");
		
		//跳转到index.jsp页面
		return "redirect:/";
	}
	
	/**
	 * 查询用户信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/users")
	public String users (Model model, 
			@RequestParam(value="currentPage", required=false) Integer currentPage,
			@RequestParam(value="pageSize", required=false) Integer pageSize) {
		
		if (null == currentPage) {
			currentPage = 1;//当前页从1开始
		}
		if (null == pageSize) {
			pageSize = Constants.DEFAULT_PAGESIZE;//默认每页显示10条数据
		}
		
		int startRow = (currentPage-1) * pageSize;
		Map<String, Object> paramMap = new ConcurrentHashMap<String, Object>();
		paramMap.put("startRow", startRow);
		paramMap.put("pageSize", pageSize);
		
		//分页查询用户列表数据
		List<UserInfo> userInfoList = userInfoService.getUserInfoByPage(paramMap);
		
		//符合查询条件的用户数据总条数
		int totalRows = userInfoService.getUserInfoByTotal(paramMap);
		
		//计算有多少页
		int totalPage = totalRows / pageSize;
		int mod = totalRows % pageSize;
		if (mod > 0) {
			totalPage = totalPage + 1;
		}
		
		model.addAttribute("userInfoList", userInfoList);//产品列表
		model.addAttribute("totalPage", totalPage);//总页数
		model.addAttribute("currentPage", currentPage);//当前页
		model.addAttribute("startRow", startRow);//开始行
		model.addAttribute("totalRows", totalRows);//总数据条数
		model.addAttribute("pageSize", pageSize);//每页显示多少条数据
		
		//跳转到产品列表页
		return "users";
	}
	
	/**
	 * 去添加用户
	 * 
	 * @return
	 */
	@RequestMapping(value="/admin/toAddUser")
	public String toAddUser () {
		
		return "addUser";
	}
	
	/**
	 * 去修改用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value="/admin/toEditUser")
	public String toEditUser (Model model,
			@RequestParam(name="id", required=true) Integer userId) {
		
		UserInfo userInfo = userInfoService.getUserInfoById(userId);
		
		model.addAttribute("updateUserInfo", userInfo);
		
		return "addUser";
	}
	
	/**
	 * 根据前端输入的手机号自动动态匹配查询员工手机号
	 * 
	 * jQuery UI Autocomplete，默认传递的参数名称为term
	 * @param startPhone
	 * @return
	 */
	@RequestMapping(value="/admin/getStaffPhone")
	public @ResponseBody Object getStaffPhone (
			@RequestParam(name="term", required=true) String startPhone) {
		
		ResponseObject responseObject = new ResponseObject();
		logger.info("根据输入的手机号开始几位查询匹配的手机号:" + startPhone);
		
		//根据输入的手机号开始几位查询匹配的手机号
		List<Map<String, Object>> phoneMapList = staffInfoService.getStaffPhone(startPhone);
		
		String[] array = new String[phoneMapList.size()];
		for (int i=0; i<phoneMapList.size(); i++) {
			Map<String, Object> map = phoneMapList.get(i);
			array[i] = String.valueOf(map.get("phone"));
		}
		responseObject.setData(array);
		
		return responseObject.getData();
	}
	
	/**
	 * 添加用户
	 * 
	 * @param username
	 * @param password
	 * @param phone
	 * @return
	 */
	@RequestMapping(value="/admin/addUser")
	public @ResponseBody ResponseObject addUser (
			@RequestParam(name="username", required=true) String username,
			@RequestParam(name="password", required=true) String password,
			@RequestParam(name="phone", required=true) String phone) {
		
		ResponseObject responseObject = new ResponseObject();
		
		//根据手机号获取一遍员工id
		StaffInfo staffInfo = staffInfoService.getStaffInfoByPhone(phone);
		if (null ==  staffInfo) {
			responseObject.setErrorCode(Constants.ONE);
			responseObject.setErrorMessage("手机号码不存在");
			return responseObject;
		}
		
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(username);
		userInfo.setPassword(password);
		userInfo.setStaffId(staffInfo.getId());
		int addRow = userInfoService.addUser(userInfo);
		
		if (addRow <= 0) {
			responseObject.setErrorCode(Constants.ONE);
			responseObject.setErrorMessage("添加用户失败，请过会儿重试吧~");
			return responseObject;
		}
		
		responseObject.setErrorCode(Constants.ZERO);
		responseObject.setErrorMessage("添加用户成功");
		return responseObject;
	}
	
	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/admin/deleteUser")
	public String deleteUser (@RequestParam(name="id", required=true) Integer id) {
		//删除用户
		userInfoService.deleteUser(id);
		return "redirect:/admin/users";
	}

	/**
	 * 用户没有权限
	 *
	 * @return
	 */
	@RequestMapping("/admin/refuse")
	public String refuse () {
		return "refuse";
	}
}