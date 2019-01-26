package com.bjpowernode.multidb;

import com.bjpowernode.multidb.datasource.DynamicDataSource;
import com.bjpowernode.multidb.datasource.ThreadLocalHolder;
import com.bjpowernode.multidb.model.UserInfo;
import com.bjpowernode.multidb.service.UserInfoService;
import com.bjpowernode.multidb.service.impl.UserInfoServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

//把springboot的自动数据源配置过滤掉，而使用我们配置的4个数据源
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Application.class, args);

		UserInfoService userInfoService = context.getBean("userInfoServiceImpl", UserInfoServiceImpl.class);

		//3310
		ThreadLocalHolder.setDataSourceKey(DynamicDataSource.DB3310);
		UserInfo userInfo = userInfoService.getUserInfoById(5);
		System.out.println(userInfo.getId() + "---" + userInfo.getName());

		//3308
		ThreadLocalHolder.setDataSourceKey(DynamicDataSource.DB3308);
		UserInfo u = new UserInfo();
		u.setId(1);
		u.setName("张三丰-update");
		int update = userInfoService.updateUserInfo(u);
		System.out.println("更新结果：" + update);
	}
}
