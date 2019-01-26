package com.bjpowernode.springboot;

import com.bjpowernode.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {
		//在该run方法中运行我们的业务代码，也就是说该run方法就是我们的业务处理入口
		String hi = userService.syaHi("张无忌");

		System.out.println(hi);
	}

	public static void main(String[] args) {
		//启动springboot程序，启动spring容器，如果是web项目还会启动内嵌的tomcat
		//SpringApplication.run(Application.class, args);

		SpringApplication springApplication = new SpringApplication(Application.class);
		//springApplication.setBannerMode(Banner.Mode.OFF);//关闭logo日志的输出
		springApplication.run(args);
	}


	//第一种方式实现开发一个纯Java程序
	/*public static void main(String[] args) {

		//run方法执行后，会返回一个spring的容器对象
		ApplicationContext context = SpringApplication.run(Application.class, args);

		//那接下来就变成spring代码的开发

		//UserService userService = (UserService)context.getBean("userServiceImpl");

		//从spring容器中获取一个bean
		UserService userService = context.getBean("userServiceImpl", UserServiceImpl.class);

		//调用bean的方法
		String hi = userService.syaHi("张三丰");

		System.out.println(hi);

	}*/
}