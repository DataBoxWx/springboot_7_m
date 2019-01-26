package com.bjpowernode.activemq;

import com.bjpowernode.activemq.service.MessageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName:Test
 * package:com.bjpowernode.activemq
 * Descrption:
 *
 * @Date:2018/8/3 17:00
 * @Author:724班
 */
public class Test {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        MessageService messageService = context.getBean("messageService", MessageService.class);

        while (true) {
            messageService.sendMessage();
        }
    }
}
