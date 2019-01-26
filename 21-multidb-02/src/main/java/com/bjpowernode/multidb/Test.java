package com.bjpowernode.multidb;

import com.bjpowernode.multidb.datasource.DynamicDataSource;
import com.bjpowernode.multidb.datasource.ThreadLocalHolder;
import com.bjpowernode.multidb.model.UserInfo;
import com.bjpowernode.multidb.service.UserInfoService;
import com.bjpowernode.multidb.service.impl.UserInfoServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName:Test
 * package:com.bjpowernode.multidb
 * Descrption:
 *
 * @Date:2018/8/6 17:17
 * @Author:724班
 */
public class Test {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        UserInfoService userInfoService = context.getBean("userInfoServiceImpl", UserInfoServiceImpl.class);

        //3310
        ThreadLocalHolder.setDataSourceKey(DynamicDataSource.DB3310);
        UserInfo userInfo = userInfoService.getUserInfoById(5);
        System.out.println(userInfo.getId() + "---" + userInfo.getName());

        //3307
        ThreadLocalHolder.setDataSourceKey(DynamicDataSource.DB3307);
        UserInfo u = new UserInfo();
        u.setId(5);
        u.setName("张三丰-update");
        int update = userInfoService.updateUserInfo(u);
        System.out.println("更新结果：" + update);
    }
}