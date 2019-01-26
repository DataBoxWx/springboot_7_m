package com.bjpowernode.activemq.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * ClassName:ListenerService
 * package:com.bjpowernode.activemq.listener
 * Descrption:
 *
 * @Date:2018/8/4 14:57
 * @Author:724班
 */
@Component //就是spring容器中的一个bean
public class ListenerService {

    /**
     * 接收消息的方法
     *
     */
    @JmsListener(destination="${spring.jms.template.default-destination}", concurrency="16")
    public void receiveMessage(Message message) {
        //判断一下是不是文本消息
        if (message instanceof TextMessage) {
            String text = null;
            try {
                text = ((TextMessage) message).getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            System.out.println("接收到的消息是：" + text);
        }
    }
}
