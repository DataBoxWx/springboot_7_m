package com.bjpowernode.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//扫描servlet所在的包
@ServletComponentScan(basePackages = {"com.bjpowernode.springboot.servlet", "com.bjpowernode.springboot.filter"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}