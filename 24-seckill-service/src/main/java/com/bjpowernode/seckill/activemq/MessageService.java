package com.bjpowernode.seckill.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * ClassName:MessageService
 * package:com.bjpowernode.activemq.service
 * Descrption:
 *
 * @Date:2018/8/4 14:39
 * @Author:724班
 */
@Component
public class MessageService {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 发送消息
     *
     */
    public void sendMessage(String ordersJSON) {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(ordersJSON);
            }
        });
    }
}