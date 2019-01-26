package com.bjpowernode.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement //开启事务的自动配置支持
@SpringBootApplication
//扫描mapper包，把mapper包下的接口都变成spring容器中的一个bean
@MapperScan(basePackages = "com.bjpowernode.springboot.mapper")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
