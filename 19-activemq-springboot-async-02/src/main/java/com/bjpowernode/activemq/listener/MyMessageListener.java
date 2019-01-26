package com.bjpowernode.activemq.listener;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * ClassName:MyMessageListener
 * package:com.bjpowernode.activemq.listener
 * Descrption:
 *
 * @Date:2018/8/3 17:37
 * @Author:724班
 */
@Component
public class MyMessageListener implements MessageListener {

    //回调方法，当消息监听器监听到消息后，会自动回调该方法，并且把消息传给该方法
    @Override
    public void onMessage(Message message) {
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