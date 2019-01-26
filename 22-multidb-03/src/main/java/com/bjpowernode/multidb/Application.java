package com.bjpowernode.multidb;

import com.bjpowernode.multidb.model.UserInfo;
import com.bjpowernode.multidb.service.UserInfoService;
import com.bjpowernode.multidb.service.impl.UserInfoServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

//我们自己配置了4个数据源，把springboot自动配置的数据源过滤掉
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Application {

	public static void main(String[] args) {

		//run方法执行后，会返回一个spring的容器对象
		ApplicationContext context = SpringApplication.run(Application.class, args);


		UserInfoService userInfoService = context.getBean("userInfoServiceImpl", UserInfoServiceImpl.class);

		//3310
		UserInfo userInfo = userInfoService.getUserInfoById(5);
		System.out.println(userInfo.getId() + "---" + userInfo.getName());

		//3307
		UserInfo u = new UserInfo();
		u.setId(5);
		u.setName("张三丰-update");
		int update = userInfoService.updateUserInfo(u);
		System.out.println("更新结果：" + update);
	}
}
