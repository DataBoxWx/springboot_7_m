package com.bjpowernode.p2p.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * P2P后台系统程序运行入口（采用SpringBoot框架开发）
 * 
 * @author yanglijun
 *
 */
//exclude表示过滤掉springboot默认的数据源配置，采用自己的多数据源配置
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class P2pAdminApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(P2pAdminApplication.class);

	/**
	 * SpringBoot框架采用main方法启动程序
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>11111111>>>>>>>>>>>");
		SpringApplication.run(P2pAdminApplication.class, args);
	}
}